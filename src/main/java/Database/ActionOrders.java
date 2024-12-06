package Database;

import Pojo.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author An Ninh
 */
public class ActionOrders {
        private final Connect cn = new Connect();
    private Connection conn;

     public ActionOrders() {
        this.conn = cn.connectSQL(); // Mở kết nối trong hàm tạo
    }

    public List<Object[]> getOrders() {
        String query = "SELECT * FROM products"; // Assuming you're getting order-related data from the "products" table
        List<Object[]> resultList = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            // Iterate through the result set
            while (resultSet.next()) {
                int idProduct = resultSet.getInt("idProduct");
                int idCustomer = resultSet.getInt("idCustomer");
                int idOrder = resultSet.getInt("idOrder");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String item = resultSet.getString("item");
                int isApproved=resultSet.getInt("isApproved");
                String address = resultSet.getString("address");
                String createdAt = resultSet.getString("createdAt");

                // Define an array to store the data (size 9 to accommodate all columns)
                Object[] row = new Object[9];  // Updated size
                row[0] = idOrder;
                row[1] = idCustomer;
                row[2] = idProduct;
                row[3] = name;
                row[4] = phone;
                row[5] = email;
                row[6] = address;
                row[7] = item;
                row[8] = createdAt;
                row[9] = isApproved;
                resultList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return resultList;
    }
    
    public List<Order> getOrdersByCustomerAndStatus(String idCustomer, boolean isDeleted) throws SQLException {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT o.idOrder, o.name AS customerName, o.email, o.address, o.phone, o.createdAt, p.name AS nameProduct " +
                 "FROM orders o " +
                 "JOIN products p ON o.idProduct = p.idProduct " +
                 "WHERE o.idCustomer = ? AND o.isDeleted = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, idCustomer);
        pstmt.setBoolean(2, isDeleted);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String idOrder = rs.getString("idOrder");
                String customerName = rs.getString("customerName");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Timestamp  createdAt = rs.getTimestamp("createdAt");
                String nameProduct = rs.getString("nameProduct");
                int isApproved = rs.getInt("isApproved");
                // Tạo đối tượng Order và thêm vào danh sách
                orders.add(new Order( idOrder,customerName, nameProduct, email, customerName, phone, address, nameProduct, createdAt,isApproved));
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy danh sách đơn hàng: " + e.getMessage());
        throw e;
    }
    
    return orders;
}
public List<Order> getAllOrders() throws SQLException {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT * FROM orders";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        // Duyệt qua kết quả trả về và tạo đối tượng Order cho mỗi bản ghi
        while (rs.next()) {
            String idOrder = rs.getString("idOrder");
            String idCustomer = rs.getString("idCustomer");
            String idProduct = rs.getString("idProduct");
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            String address = rs.getString("address");
            String item = rs.getString("item");
            Timestamp createdAt = rs.getTimestamp("createdAt");
            int isApproved = rs.getInt("isApproved");
            
            // Tạo đối tượng Order và thêm vào danh sách
            orders.add(new Order( idOrder,idCustomer, idProduct, email, name, phone, address, item, createdAt,isApproved));
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy danh sách đơn hàng: " + e.getMessage());
        throw e;
    }
    
    return orders;
}
public List<Order> searchOrdersByNameEmailOrPhone(String name, String email, String phone) throws SQLException {
    List<Order> orders = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM orders WHERE 1=1");
    
    // Thêm điều kiện tìm kiếm vào câu lệnh SQL dựa trên các tham số nhập vào
    if (!name.isEmpty()) {
        sql.append(" AND name LIKE ?");
    }
    if (!email.isEmpty()) {
        sql.append(" AND email LIKE ?");
    }
    if (!phone.isEmpty()) {
        sql.append(" AND phone LIKE ?");
    }

    try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
        int index = 1;
        if (!name.isEmpty()) {
            pstmt.setString(index++, "%" + name + "%");
        }
        if (!email.isEmpty()) {
            pstmt.setString(index++, "%" + email + "%");
        }
        if (!phone.isEmpty()) {
            pstmt.setString(index++, "%" + phone + "%");
        }

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String idOrder = rs.getString("idOrder");
                String idCustomer = rs.getString("idCustomer");
                String idProduct = rs.getString("idProduct");
                String customerName = rs.getString("name");
                String phoneNumber = rs.getString("phone");
                String emailAddr = rs.getString("email");
                String address = rs.getString("address");
                String item = rs.getString("item");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                int isApproved = rs.getInt("isApproved");
                orders.add(new Order(idOrder, idCustomer, idProduct, emailAddr, customerName, phoneNumber, address, item, createdAt,isApproved));
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi tìm kiếm đơn hàng: " + e.getMessage());
        throw e;
    }

    return orders;
}

    public void updateApprove(int isSelectedApproved,String orderId){
        String query ="UPDATE orders SET isApproved = ? WHERE idOrder = ? ";
            try (
            PreparedStatement statement = conn.prepareStatement(query)
        )   {
            statement.setInt(1, isSelectedApproved); 
            statement.setString(2, orderId);
            statement.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
    }
}
    
    public void deleteOrder(String orderId) {
    String query = "DELETE FROM orders WHERE idOrder = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setString(1, orderId);
        statement.executeUpdate();
        System.out.println("Đã xóa đơn hàng với ID: " + orderId);
    } catch (SQLException e) {
        System.err.println("Lỗi khi xóa đơn hàng: " + e.getMessage());
    }
}
    
    public void addOrder(Order order) {
    String query = "INSERT INTO orders (idOrder, idCustomer, idProduct, name, phone, email, address, item, createdAt, isApproved) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setString(1, order.getIdOrder());
        statement.setString(2, order.getIdCustomer());
        statement.setString(3, order.getIdProduct());
        statement.setString(4, order.getName());
        statement.setString(5, order.getPhone());
        statement.setString(6, order.getEmail());
        statement.setString(7, order.getAddress());
        statement.setString(8, order.getItem());
        statement.setTimestamp(9, order.getCreatedAt());
        statement.setInt(10, order.getIsApproved());
        statement.executeUpdate();
        System.out.println("Đã thêm đơn hàng mới!");
    } catch (SQLException e) {
        System.err.println("Lỗi khi thêm đơn hàng: " + e.getMessage());
    }
}
    
    public void updateOrder(Order order) {
    String query = "UPDATE orders SET idCustomer = ?, idProduct = ?, name = ?, phone = ?, email = ?, address = ?, " +
                   "item = ?, createdAt = ?, isApproved = ? WHERE idOrder = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setString(1, order.getIdCustomer());
        statement.setString(2, order.getIdProduct());
        statement.setString(3, order.getName());
        statement.setString(4, order.getPhone());
        statement.setString(5, order.getEmail());
        statement.setString(6, order.getAddress());
        statement.setString(7, order.getItem());
        statement.setTimestamp(8, order.getCreatedAt());
        statement.setInt(9, order.getIsApproved());
        statement.setString(10, order.getIdOrder());
        statement.executeUpdate();
        System.out.println("Đã cập nhật đơn hàng với ID: " + order.getIdOrder());
    } catch (SQLException e) {
        System.err.println("Lỗi khi cập nhật đơn hàng: " + e.getMessage());
    }
}
public List<Order> getOrdersByApprovalStatus(int isApproved) {
    List<Order> orders = new ArrayList<>();
    String query = "SELECT * FROM orders WHERE isApproved = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setInt(1, isApproved);
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String idOrder = rs.getString("idOrder");
                String idCustomer = rs.getString("idCustomer");
                String idProduct = rs.getString("idProduct");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String item = rs.getString("item");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                orders.add(new Order(idOrder, idCustomer, idProduct, email, name, phone, address, item, createdAt, isApproved));
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy danh sách đơn hàng theo trạng thái: " + e.getMessage());
    }
    return orders;
}

public int getTotalOrdersCount() {
    String query = "SELECT COUNT(*) AS total FROM orders";
    try (PreparedStatement statement = conn.prepareStatement(query);
         ResultSet rs = statement.executeQuery()) {
        if (rs.next()) {
            return rs.getInt("total");
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi đếm tổng số đơn hàng: " + e.getMessage());
    }
    return 0;
}


}

