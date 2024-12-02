/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

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
            Float price = resultSet.getFloat("price");
            String thumbnail = resultSet.getString("thumbnail");
            String status = resultSet.getString("status");
            String createdAt = resultSet.getString("createdAt");

            Object[] row = new Object[7];
            row[0] = idProduct;
            row[1] = name;
            row[2] = createdAt;
            row[3] = price;
            row[4] = thumbnail;
            row[5] = status;
            row[6] = nameCate;
            
            resultList.add(row);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return resultList;
}
     public String addProduct(String name,String nameCate,String thumbnail,String status,float price){
        String query = "INSERT INTO products (name,idCate,thumbnail,status,price) VALUES (?,?,?,?,?)";
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
        statement.setFloat(5, price); 

                
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
    
    
    public String updateProduct(String names, float price, String thumbnail, String selectedStatus, String selectedCate, int idProduct) {
    // Corrected SQL query
    String query = "UPDATE products SET name = ?, thumbnail = ?, price = ?, status = ?, idCate = ? WHERE idProduct = ?";
    String queryIdCate = "SELECT idCate FROM categories WHERE title = ?";

    try (
        PreparedStatement statement = connection.prepareStatement(query);
        PreparedStatement findIdCate = connection.prepareStatement(queryIdCate);
    ) {
        // Find idCate by category title
        findIdCate.setString(1, selectedCate);  // Set selected category title
        ResultSet rs = findIdCate.executeQuery();
        int idCate = 0;
        
        if (rs.next()) {
            idCate = rs.getInt("idCate");
        } else {
            return "Category not found!";
        }

        // Set parameters for the update query
        statement.setString(1, names);  // Set product name
        statement.setString(2, thumbnail);  // Set thumbnail
        statement.setFloat(3, price);  // Set price
        statement.setString(4, selectedStatus);  // Set status
        statement.setInt(5, idCate);  // Set category ID
        statement.setInt(6, idProduct);  // Set product ID

        int rowsUpdated = statement.executeUpdate(); // Execute update query

        return rowsUpdated > 0 ? "CẬP NHẬT THÀNH CÔNG" : "CẬP NHẬT THẤT BẠI";
    } catch (Exception e) {
        e.printStackTrace();
        return "An error occurred: " + e.getMessage();
    }
}
}
