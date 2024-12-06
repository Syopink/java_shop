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
 * Quản lý các thao tác với bảng orders_new và OrderItems.
 */
public class ActionOrders {
    private final Connect cn = new Connect();
    private Connection conn;

    public ActionOrders() {
        this.conn = cn.connectSQL(); // Kết nối tới database
    }

    // Lấy danh sách tất cả đơn hàng
 public List<Order> getAllOrders() throws SQLException {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT * FROM orders_new";

    try (PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            int idOrder = rs.getInt("idOrder");
            int idCustomer = rs.getInt("idCustomer");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            BigDecimal totalPrice = rs.getBigDecimal("TotalPrice");
            int status = rs.getInt("status");
            Timestamp createdAt = rs.getTimestamp("createdAt");
            Timestamp updatedAt = rs.getTimestamp("updatedAt");

            // Lấy danh sách OrderItem cho từng đơn hàng
            List<OrderItem> orderItems = getOrderItemsByOrderId(idOrder);

            // Tạo đối tượng Order
            orders.add(new Order(idOrder, String.valueOf(idCustomer), null, email, name, phone, address, orderItems, createdAt, updatedAt, status, totalPrice));
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy danh sách đơn hàng: " + e.getMessage());
    }

    return orders;
}

    // Helper method to get OrderItems by Order ID
    private List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT oi.idProduct, oi.quantity, oi.price, p.name AS productName " +
                     "FROM OrderItems oi " +
                     "JOIN Products p ON oi.idProduct = p.idProduct " +
                     "WHERE oi.idOrder = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idProduct = rs.getInt("idProduct");
                    String productName = rs.getString("productName");
                    int quantity = rs.getInt("quantity");
                    BigDecimal price = rs.getBigDecimal("price");

                    // Create OrderItem and add it to the list
                    OrderItem orderItem = new OrderItem(idProduct, productName, quantity, price);
                    orderItems.add(orderItem);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy chi tiết sản phẩm của đơn hàng: " + e.getMessage());
        }

        return orderItems;
    }

    // Thêm đơn hàng mới
    public void addOrder(Order order) {
        String query = "INSERT INTO orders_new(idCustomer, name, email, phone, address, TotalPrice, status, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(order.getIdCustomer()));
            statement.setString(2, order.getName());
            statement.setString(3, order.getEmail());
            statement.setString(4, order.getPhone());
            statement.setString(5, order.getAddress());
            statement.setBigDecimal(6, order.getTotalPrice()); // Sử dụng BigDecimal cho TotalPrice
            statement.setInt(7, order.getStatus());
            statement.setTimestamp(8, order.getCreatedAt());
            statement.setTimestamp(9, order.getUpdatedAt());
            statement.executeUpdate();
            System.out.println("Đã thêm đơn hàng mới!");
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm đơn hàng: " + e.getMessage());
        }
    }

    // Xóa đơn hàng theo ID
    public void deleteOrder(String orderId) {
        String query = "DELETE FROM orders_new WHERE idOrder = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(orderId));
            statement.executeUpdate();
            System.out.println("Đã xóa đơn hàng với ID: " + orderId);
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa đơn hàng: " + e.getMessage());
        }
    }

    // Lấy danh sách đơn hàng theo trạng thái
    public List<Order> getOrdersByStatus(int status) {
        List<Order> orders_new = new ArrayList<>();
        String query = "SELECT * FROM orders_new WHERE status = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, status);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int idOrder = rs.getInt("idOrder");
                    int idCustomer = rs.getInt("idCustomer");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    BigDecimal totalPrice = rs.getBigDecimal("TotalPrice"); // Sử dụng BigDecimal cho TotalPrice
                    Timestamp createdAt = rs.getTimestamp("createdAt");
                    Timestamp updatedAt = rs.getTimestamp("updatedAt");

                orders_new.add(new Order(idOrder, String.valueOf(idCustomer), email, name, phone, address,  status,createdAt, updatedAt, totalPrice));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách đơn hàng theo trạng thái: " + e.getMessage());
        }

        return orders_new;
    }

    // Cập nhật trạng thái đơn hàng
public void updateApprove(int isSelectedApproved, int orderId) {
    String query = "UPDATE orders_new SET status = ? WHERE idOrder = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setInt(1, isSelectedApproved);  // Sử dụng int cho trạng thái
        statement.setInt(2, orderId);  // Sử dụng int cho idOrder
        statement.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Lỗi khi cập nhật trạng thái đơn hàng: " + e.getMessage());
    }
}
    // Lấy tên sản phẩm theo ID
    public String getProductNameById(int productId) {
        String productName = null;
        String query = "SELECT name FROM products WHERE idProduct = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, productId); // Set the productId as parameter
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    productName = rs.getString("name"); // Get the product name
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy tên sản phẩm theo ID: " + e.getMessage());
        }

        return productName;
    }

    // Tìm kiếm đơn hàng theo tên, email hoặc số điện thoại
    public List<Order> searchOrdersByNameEmailOrPhone(String name, String email, String phone) throws SQLException {
        List<Order> orders_new = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM orders_new WHERE 1 = 1");

        // Add conditions based on the provided parameters
        if (name != null && !name.isEmpty()) {
            queryBuilder.append(" AND name LIKE ?");
        }
        if (email != null && !email.isEmpty()) {
            queryBuilder.append(" AND email LIKE ?");
        }
        if (phone != null && !phone.isEmpty()) {
            queryBuilder.append(" AND phone LIKE ?");
        }

        String query = queryBuilder.toString();

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            int index = 1;

            // Set the parameters for the PreparedStatement
            if (name != null && !name.isEmpty()) {
                statement.setString(index++, "%" + name + "%"); // Use LIKE for partial matching
            }
            if (email != null && !email.isEmpty()) {
                statement.setString(index++, "%" + email + "%");
            }
            if (phone != null && !phone.isEmpty()) {
                statement.setString(index++, "%" + phone + "%");
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int idOrder = rs.getInt("idOrder");
                    int idCustomer = rs.getInt("idCustomer");
                    String orderName = rs.getString("name");
                    String orderEmail = rs.getString("email");
                    String orderPhone = rs.getString("phone");
                    String address = rs.getString("address");
                    BigDecimal totalPrice = rs.getBigDecimal("TotalPrice"); // Use BigDecimal for TotalPrice
                    Timestamp createdAt = rs.getTimestamp("createdAt");
                    Timestamp updatedAt = rs.getTimestamp("updatedAt");
                    int status = rs.getInt("status");

                orders_new.add(new Order(idOrder, String.valueOf(idCustomer), orderEmail, orderName, orderPhone, address,  status,createdAt, updatedAt, totalPrice));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while searching orders_new: " + e.getMessage());
        }

        return orders_new;
    }
   
    
    
    
    
    
    // customer order
    public List<Order> getAllOrdersByIdCustomer(int idCustomer) throws SQLException {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT * FROM orders_new WHERE idCustomer = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idCustomer); // Gán tham số idCustomer

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int idOrder = rs.getInt("idOrder");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                BigDecimal totalPrice = rs.getBigDecimal("TotalPrice");
                int status = rs.getInt("status");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");

                // Lấy danh sách OrderItem cho từng đơn hàng
                List<OrderItem> orderItems = getOrderItemsByOrderId(idOrder);
                System.out.println("a" + orderItems);
                // Tạo đối tượng Order
                orders.add(new Order(idOrder, String.valueOf(idCustomer), null, email, name, phone, address, orderItems, createdAt, updatedAt, status, totalPrice));
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy danh sách đơn hàng theo idCustomer: " + e.getMessage());
    }

    return orders;
}
    
    
    public boolean placeOrder(int idCustomer, List<OrderItem> cartItems, String name, String email, String phone, String address) {
    // Bắt đầu giao dịch để đảm bảo tính toàn vẹn của các thao tác
    try {
        conn.setAutoCommit(false); // Tắt tự động commit (để bắt đầu giao dịch)

        // 1. Tính tổng giá trị đơn hàng
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem item : cartItems) {
            totalPrice = totalPrice.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        // 2. Thêm đơn hàng vào bảng `orders_new`
        String insertOrderSql = "INSERT INTO orders_new (idCustomer, name, email, phone, address, TotalPrice, status, createdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertOrderSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, idCustomer);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, address);
            stmt.setBigDecimal(6, totalPrice);
            stmt.setInt(7, 0);  // Trạng thái mặc định là "Chưa xử lý" (0)
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(8, timestamp);
            stmt.setTimestamp(9, timestamp);

            // Thực thi lệnh thêm đơn hàng
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                conn.rollback(); // Hủy giao dịch nếu không thể thêm đơn hàng
                return false;
            }

            // Lấy idOrder vừa tạo
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int idOrder = -1;
            if (generatedKeys.next()) {
                idOrder = generatedKeys.getInt(1);
            }

            // 3. Thêm các sản phẩm vào bảng `OrderItems`
            String insertOrderItemSql = "INSERT INTO OrderItems (idOrder, idProduct, quantity, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmtItem = conn.prepareStatement(insertOrderItemSql)) {
                for (OrderItem item : cartItems) {
                    stmtItem.setInt(1, idOrder);
                    stmtItem.setInt(2, item.getIdProduct());
                    stmtItem.setInt(3, item.getQuantity());
                    stmtItem.setBigDecimal(4, item.getPrice());
                    stmtItem.addBatch();  // Thêm vào batch
                }
                // Thực thi batch chèn OrderItems
                int[] rowsInsertedItems = stmtItem.executeBatch();
            }

            // Commit giao dịch
            conn.commit();
            return true;
        } catch (SQLException e) {
            conn.rollback();  // Nếu có lỗi, rollback lại giao dịch
            System.err.println("Lỗi khi thêm đơn hàng: " + e.getMessage());
            return false;
        }
    } catch (SQLException e) {
        try {
            conn.rollback();  // Nếu có lỗi, rollback lại giao dịch
        } catch (SQLException ex) {
            System.err.println("Lỗi rollback giao dịch: " + ex.getMessage());
        }
        System.err.println("Lỗi khi xử lý đơn hàng: " + e.getMessage());
        return false;
    } finally {
        try {
            conn.setAutoCommit(true);  // Bật lại auto commit
        } catch (SQLException e) {
            System.err.println("Lỗi khi khôi phục trạng thái auto commit: " + e.getMessage());
        }
    }
}

    
}
