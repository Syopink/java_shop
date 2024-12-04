/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import Pojo.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Gia Huy
 */
public class product {
     private final Connect cn = new Connect();
    private Connection conn;

    public product() {
        this.conn = cn.connectSQL(); // Mở kết nối trong hàm tạo
    }
    
    
public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.idProduct, p.idCate, c.title, p.name, p.thumbnail, p.descriptions, p.price, p.status, p.featured, p.promotion, p.warranty, p.accessories, p.createdAt, p.updatedAt "
                     + "FROM products p "
                     + "JOIN categories c ON p.idCate = c.idCate";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getInt("idProduct"));
                product.setIdCate(rs.getString("idCate"));
                product.setCategoryTitle(rs.getString("title"));  // Lưu giá trị title từ bảng categories
                product.setName(rs.getString("name"));
                product.setThumbnail(rs.getString("thumbnail"));
                product.setDescriptions(rs.getString("descriptions"));
                product.setPrice(rs.getFloat("price"));  
                product.setStatus(rs.getString("status"));
                product.setFeatured(rs.getBoolean("featured"));
                product.setPromotion(rs.getString("promotion"));
                product.setWarranty(rs.getString("warranty"));
                product.setAccessories(rs.getString("accessories"));
                product.setCreatedAt(rs.getTimestamp("createdAt"));
                product.setUpdatedAt(rs.getTimestamp("updatedAt"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
