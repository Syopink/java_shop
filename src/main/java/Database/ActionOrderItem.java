/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author Nguyen Gia Huy
 */
public class ActionOrderItem {
     private final Connect cn = new Connect();
    private Connection conn;

    public ActionOrderItem() {
        this.conn = cn.connectSQL(); // Kết nối tới database
    }
    // Lấy chi tiết đơn hàng và các sản phẩm liên quan đến đơn hàng
public Order getOrderDetails(int idOrder) {
    Order order = null;
    List<OrderItem> orderItems = new ArrayList<>();

    // Truy vấn thông tin đơn hàng từ bảng orders_new
    String orderQuery = "SELECT * FROM orders_new WHERE idOrder = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(orderQuery)) {
        pstmt.setInt(1, idOrder);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int idCustomer = rs.getInt("idCustomer");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                BigDecimal totalPrice = rs.getBigDecimal("TotalPrice");
                int status = rs.getInt("status");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");

                order = new Order(idOrder, String.valueOf(idCustomer), null, email, name, phone, address, orderItems, createdAt, updatedAt, status, totalPrice);
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy đơn hàng: " + e.getMessage());
    }

    // Truy vấn các sản phẩm trong đơn hàng từ bảng OrderItems và bảng Products
    String itemQuery = "SELECT oi.idProduct, oi.quantity, oi.price, p.name AS productName " +
                        "FROM OrderItems oi " +
                        "JOIN Products p ON oi.idProduct = p.idProduct " +
                        "WHERE oi.idOrder = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(itemQuery)) {
        pstmt.setInt(1, idOrder);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int idProduct = rs.getInt("idProduct");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");
                BigDecimal price = rs.getBigDecimal("price");

                // Tạo đối tượng OrderItem và thêm vào danh sách
                OrderItem orderItem = new OrderItem(idProduct, productName, quantity, price);
                orderItems.add(orderItem);
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy sản phẩm trong đơn hàng: " + e.getMessage());
    }

    // Gán danh sách các sản phẩm cho đơn hàng
    if (order != null) {
        order.setOrderItems(orderItems);
    }

    return order;
}

}
