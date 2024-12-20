/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Pojo.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    String query = "SELECT p.idOrder, SUM(o.price) AS TotalPrice " +
               "FROM VietPro.dbo.OrderItems o " +
               "JOIN VietPro.dbo.orders_new p ON o.idOrder = p.idOrder " +
               "GROUP BY p.idOrder";

  // Không có điều kiện WHERE để tính tổng cho tất cả đơn hàng
                   
    double totalRevenue = 0.0;  // Khởi tạo tổng doanh thu mặc định là 0

    try (PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery();
            ) {
        if (rs.next()) {
            totalRevenue = rs.getDouble("TotalPrice");  // Lấy tổng doanh thu từ kết quả truy vấn
        }
    }
    
    return totalRevenue;  // Trả về tổng doanh thu cho tất cả các đơn hàng
}

public List<String> getAllCategories() throws SQLException {
    List<String> categories = new ArrayList<>();
    String query = "SELECT DISTINCT title FROM categories";
    try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            categories.add(rs.getString("title"));
        }
    }
    return categories;
}

public List<String> getAllStatuses() throws SQLException {
    List<String> statuses = new ArrayList<>();
    String query = "SELECT DISTINCT status FROM products";
    try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            statuses.add(rs.getString("status"));
        }
    }
    return statuses;
}

public List<String> getPriceRanges() {
    return Arrays.asList("Dưới 1.000.000", "1.000.000 - 5.000.000", "5.000.000 - 10.000.000", "Trên 10.000.000");
}

public List<Product> getFilteredProducts(String category, String status, String priceRange, String name) throws SQLException {
    List<Product> products = new ArrayList<>();
    String query = "SELECT p.*, c.title FROM products p " + 
                   "JOIN categories c ON p.idCate = c.idCate WHERE 1=1"; // Default query

    // Log the filters being passed in
    System.out.println("Generated filters:");
    System.out.println("Category: " + category);
    System.out.println("Status: " + status);
    System.out.println("Price Range: " + priceRange);
    System.out.println("Product Name Search: " + name);

    // Constructing the query
    if (!category.isEmpty()) {
        query += " AND c.title = ?";
    }
    if (status != null && !status.isEmpty() && !status.equals("Tất cả")) {
        query += " AND p.status = ?";
    }
    if (!priceRange.isEmpty()) {
        switch (priceRange) {
            case "Dưới 1.000.000" -> query += " AND p.price < 1000000";
            case "1.000.000 - 5.000.000" -> query += " AND p.price BETWEEN 1000000 AND 5000000";
            case "5.000.000 - 10.000.000" -> query += " AND p.price BETWEEN 5000000 AND 10000000";
            case "Trên 10.000.000" -> query += " AND p.price > 10000000";
            default -> {
            }
        }
    }
    if (name != null && !name.isEmpty()) {
        query += " AND p.name LIKE ?";
    }

    // Log the final query
    System.out.println("Final SQL query: " + query);

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        int index = 1;

        // Set parameters for the query
        if (!category.isEmpty()) {
            stmt.setString(index++, category);
        }
        if (!status.isEmpty()) {
            stmt.setString(index++, status);
        }
        if (name != null && !name.isEmpty()) {
            stmt.setString(index++, "%" + name + "%");
        }

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getInt("idProduct"));
                product.setName(rs.getString("name"));
                product.setThumbnail(rs.getString("thumbnail"));
                product.setCategoryTitle(rs.getString("title"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setDescriptions(rs.getString("descriptions"));
                product.setStatus(rs.getString("status"));
                product.setPromotion(rs.getString("promotion"));
                product.setWarranty(rs.getString("warranty"));
                product.setAccessories(rs.getString("accessories"));
                product.setCreatedAt(rs.getTimestamp("createdAt"));
                product.setUpdatedAt(rs.getTimestamp("updatedAt"));
                products.add(product);
            }
        }
    }

    return products;

}

}
