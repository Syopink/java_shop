package Database;

import Pojo.Order;
import Pojo.OrderItem;
import java.math.BigDecimal;
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
    String query = "SELECT * FROM orders_new"; // Truy vấn bảng orders_new
    List<Object[]> resultList = new ArrayList<>();

    try (PreparedStatement statement = conn.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {

        // Duyệt qua từng dòng kết quả
        while (resultSet.next()) {
            String idOrder = resultSet.getString("idOrder");
            String idCustomer = resultSet.getString("idCustomer");
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            BigDecimal totalPrice = resultSet.getBigDecimal("TotalPrice");
            Timestamp createdAt = resultSet.getTimestamp("createdAt");
            int status = resultSet.getInt("status");

            // Lưu dữ liệu vào mảng Object
            Object[] row = new Object[9];
            row[0] = idOrder;
            row[1] = idCustomer;
            row[2] = name;
            row[3] = phone;
            row[4] = email;
            row[5] = address;
            row[6] = totalPrice;
            row[7] = createdAt;
            row[8] = status;

            resultList.add(row);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return resultList;
}

    
   public List<Order> getOrdersByCustomerAndStatus(String idCustomer, int status) throws SQLException {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT idOrder, idCustomer, name, email, phone, address, TotalPrice, createdAt, updateAt, status " +
                 "FROM orders_new " +
                 "WHERE idCustomer = ? AND status = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, idCustomer);
        pstmt.setInt(2, status);

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String idOrder = rs.getString("idOrder");
                String customerName = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                BigDecimal totalPrice = rs.getBigDecimal("TotalPrice");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updatedAt = rs.getTimestamp("updateAt");
                int orderStatus = rs.getInt("status");

                // Thêm đối tượng Order vào danh sách
                orders.add(new Order(idOrder, idCustomer, customerName, email, phone, address, totalPrice, createdAt, updatedAt, orderStatus));
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
    String sql = "SELECT * FROM orders_new";

    try (PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            String idOrder = rs.getString("idOrder");
            String idCustomer = rs.getString("idCustomer");
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            String address = rs.getString("address");
            BigDecimal totalPrice = rs.getBigDecimal("TotalPrice");
            Timestamp createdAt = rs.getTimestamp("createdAt");
            Timestamp updatedAt = rs.getTimestamp("updateAt");
            int status = rs.getInt("status");

            // Thêm đối tượng Order vào danh sách
            orders.add(new Order(idOrder, idCustomer, name, email, phone, address, totalPrice, createdAt, updatedAt, status));
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy danh sách đơn hàng: " + e.getMessage());
        throw e;
    }

    return orders;
}

public List<Order> searchOrdersByNameEmailOrPhone(String name, String email, String phone) throws SQLException {
    List<Order> orders = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM orders_new WHERE 1=1");
    
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
                String customerName = rs.getString("name");
                String phoneNumber = rs.getString("phone");
                String emailAddr = rs.getString("email");
                String address = rs.getString("address");
                BigDecimal totalPrice = rs.getBigDecimal("TotalPrice");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updateAt = rs.getTimestamp("updateAt");
                int status = rs.getInt("status");

                // Tạo đối tượng Order và thêm vào danh sách
                orders.add(new Order(idOrder, idCustomer, customerName, emailAddr, phoneNumber, address, totalPrice, createdAt, updateAt, status));
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi tìm kiếm đơn hàng: " + e.getMessage());
        throw e;
    }

    return orders;
}


    public void updateApprove(int isSelectedApproved,String orderId){
        String query ="UPDATE orders SET status = ? WHERE idOrder = ? ";
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
    
    public List<OrderItem> getOrderItemsByOrderId(String idOrder) throws SQLException {
    List<OrderItem> orderItems = new ArrayList<>();
    String sql = "SELECT idOrderItem, idOrder, idProduct, quantity, price FROM OrderItems WHERE idOrder = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, idOrder);  // Thực thi câu lệnh với idOrder được truyền vào

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String idOrderItem = rs.getString("idOrderItem");
                String idProduct = rs.getString("idProduct");
                int quantity = rs.getInt("quantity");
                BigDecimal price = rs.getBigDecimal("price");

                // Tạo đối tượng OrderItem và thêm vào danh sách
                orderItems.add(new OrderItem(idOrderItem, idOrder, idProduct, quantity, price));
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy dữ liệu chi tiết đơn hàng: " + e.getMessage());
        throw e;
    }

    return orderItems;
}

}

