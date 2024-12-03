/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Gia Huy
 */
public class Action {

    private final Connect cn = new Connect();
    private Connection conn;

    public Action() {
        this.conn = cn.connectSQL(); // Mở kết nối trong hàm tạo
    }

    public int countProducts() throws SQLException {
        String query = "SELECT COUNT(*) AS total_products FROM products";
        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total_products");
            }
        }
        return 0;
    }

    public int countCustomers() throws SQLException {
        String query = "SELECT COUNT(*) AS total_customers FROM customers WHERE role = 'customer' AND isdeleted = '0'";
        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total_customers");
            }
        }
        return 0;
    }

public double getTotalRevenueForAllOrders() throws SQLException {
    String query = "SELECT SUM(p.price) AS totalPrice " +
                   "FROM VietPro.dbo.orders o " +
                   "JOIN VietPro.dbo.products p ON o.idProduct = p.idProduct";  // Không có điều kiện WHERE để tính tổng cho tất cả đơn hàng
    
    double totalRevenue = 0.0;  // Khởi tạo tổng doanh thu mặc định là 0

    try (PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
            totalRevenue = rs.getDouble("totalPrice");  // Lấy tổng doanh thu từ kết quả truy vấn
        }
    }
    
    return totalRevenue;  // Trả về tổng doanh thu cho tất cả các đơn hàng
}

}
