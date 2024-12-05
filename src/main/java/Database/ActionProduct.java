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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author An Ninh
 */
public class ActionProduct {
    private Connect conn;
    private Connection connection ;

    public ActionProduct() {
        conn = new Connect();
        connection = conn.connectSQL();
    }
    
    public List<Object[]> getProducts() {
    String query = "SELECT * FROM products ";
    String query2 = "SELECT title FROM categories WHERE idCate = ? ";
    List<Object[]> resultList = new ArrayList<>();

    try (
        PreparedStatement statement = connection.prepareStatement(query);
        PreparedStatement statement2 = connection.prepareStatement(query2);
        ResultSet resultSet = statement.executeQuery();
    ) {
        while (resultSet.next()) {
            int idProduct = resultSet.getInt("idProduct");
            int idCate = resultSet.getInt("idCate");

            // Đặt tham số cho câu truy vấn con
            statement2.setInt(1, idCate);

            // Thực thi câu truy vấn con và lấy tên danh mục
            ResultSet resultFind = statement2.executeQuery();
            String nameCate = "";
            if (resultFind.next()) {
                nameCate = resultFind.getString("title");
            }
            String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");  // Change to BigDecimal
            String thumbnail = resultSet.getString("thumbnail");
            String status = resultSet.getString("status");
            String createdAt = resultSet.getString("createdAt");
            boolean featured = resultSet.getBoolean("featured");
            String promotion = resultSet.getString("promotion");
            String warranty = resultSet.getString("warranty");
            String accessories = resultSet.getString("accessories");
            Object[] row = new Object[11];
            row[0] = idProduct;
            row[1] = name;
            row[2] = createdAt;
            row[3] = price;
            row[4] = thumbnail;
            row[5] = status;
            row[6] = nameCate;
            row[7] = featured;
            row[8] = promotion;
            row[9] = warranty;
            row[10] = accessories;
            resultList.add(row);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return resultList;
}
     public String addProduct(String name,String nameCate,String thumbnail,String status,BigDecimal price, String descriptions,String promotion, String warranty, String accessories ){
        String query = "INSERT INTO products (name,idCate,thumbnail,status,price, descriptions, promotion, warranty, accessories) VALUES (?,?,?,?,?,?,?,?,?)";
        String queryIdCate="SELECT idCate FROM categories WHERE title = ?";
        try (
        PreparedStatement statement = connection.prepareStatement(query);
        PreparedStatement findIdCate=connection.prepareStatement(queryIdCate);
                
    ) {
        findIdCate.setString(1, nameCate);
        ResultSet rs = findIdCate.executeQuery();
        int idCate = 0;
        if (rs.next()) {
            idCate = rs.getInt("idCate");
        } else {
            return "Category not found!";
        }
        statement.setString(1, name); 
        statement.setInt(2, idCate); 
        statement.setString(3, thumbnail); 
        statement.setString(4, status); 
        statement.setBigDecimal(5, price);  
        statement.setString(6, descriptions);
        statement.setString(7, promotion);
        statement.setString(8, warranty);
        statement.setString(9, accessories);
                
        int rowsUpdated = statement.executeUpdate(); // Thực thi lệnh UPDATE
        return rowsUpdated > 0 ? "Thêm mới thành công" : "Thêm mới thất bại";
    } catch (Exception e) {
        e.printStackTrace();
        return "An error occurred: " + e.getMessage();
    }
       } 
    
    public String DeleteProduct(int id){
        String query = "DELETE FROM products WHERE idProduct = ?";

        try (
        PreparedStatement statement = connection.prepareStatement(query)
    ) {
        statement.setInt(1, id); // Gán giá trị cho title
        int rowsUpdated = statement.executeUpdate(); 
        return rowsUpdated > 0 ? "Xóa thành công" : "Xóa thất bại";
    } catch (Exception e) {
        e.printStackTrace();
        return "An error occurred: " + e.getMessage();
    }
       } 
    
  public String updateProduct(Product product) {
    String query = "UPDATE products SET name = ?, thumbnail = ?, price = ?, status = ?, idCate = ?, descriptions = ?, promotion = ?, warranty = ?, accessories = ? WHERE idProduct = ?";
    String queryIdCate = "SELECT idCate FROM categories WHERE title = ?";

    try (
        PreparedStatement statement = connection.prepareStatement(query);
        PreparedStatement findIdCate = connection.prepareStatement(queryIdCate);
    ) {
        // Tìm idCate theo tên danh mục từ đối tượng Product
        findIdCate.setString(1, product.getCategoryTitle());  // Lấy tên danh mục từ Product
        ResultSet rs = findIdCate.executeQuery();
        int idCate = 0;

        if (rs.next()) {
            idCate = rs.getInt("idCate");
        } else {
            return "Category not found!";
        }

        // Thiết lập các tham số cho câu lệnh UPDATE từ đối tượng Product
        statement.setString(1, product.getName());  // Tên sản phẩm
        statement.setString(2, product.getThumbnail());  // Hình ảnh sản phẩm
        statement.setBigDecimal(3, product.getPrice());  // Giá sản phẩm
        statement.setString(4, product.getStatus());  // Trạng thái sản phẩm
        statement.setInt(5, idCate);  // ID danh mục
        statement.setString(6, product.getDescriptions());  // Mô tả sản phẩm
        statement.setString(7, product.getPromotion());  // Khuyến mãi
        statement.setString(8, product.getWarranty());  // Bảo hành
        statement.setString(9, product.getAccessories());  // Phụ kiện
        statement.setInt(10, product.getIdProduct());  // ID sản phẩm

        // Thực thi câu lệnh UPDATE
        int rowsUpdated = statement.executeUpdate();

        return rowsUpdated > 0 ? "CẬP NHẬT THÀNH CÔNG" : "CẬP NHẬT THẤT BẠI";
    } catch (Exception e) {
        e.printStackTrace();
        return "An error occurred: " + e.getMessage();
    }
}
}

