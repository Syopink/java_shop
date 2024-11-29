package Database;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Action {
    
    private Connect conn;
    private Connection connection ;

    public Action() {
        conn = new Connect();
        connection = conn.connectSQL();
    }
    
        public List<Object[]> getCate(){
            String query = "SELECT * FROM categories";
            List<Object[]> resultList = new ArrayList<>();
        try (
            
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                 int idCate = resultSet.getInt("idCate");
                 String name = resultSet.getString("title");
                 String createdAt = resultSet.getString("createdAt");
                 String updatedAt = resultSet.getString("updatedAt");
                 

                Object[] row = new Object[4];
                row[0] = idCate;  
                row[1] = name;
                row[2] = createdAt;    
                row[3] = updatedAt;    
                

                resultList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
        }
        
        public String updateCate(int id,String title){
            String query ="UPDATE categories SET title = ? WHERE idCate = ? ";
        try (
        PreparedStatement statement = connection.prepareStatement(query)
    ) {
        statement.setString(1, title); // Gán giá trị cho title
        statement.setInt(2, id); // Gán giá trị cho id
        int rowsUpdated = statement.executeUpdate(); // Thực thi lệnh UPDATE
        return rowsUpdated > 0 ? "CẬP NHẬT THÀNH CÔNG" : "CẬP NHẬT THẤT BẠI";
    } catch (Exception e) {
        e.printStackTrace();
        return "An error occurred: " + e.getMessage();
    }
        }   
        
     public String addCate(String title){
        String query = "INSERT INTO categories (title, descriptions) VALUES (?, ?)";

        try (
        PreparedStatement statement = connection.prepareStatement(query)
    ) {
        statement.setString(1, title); // Gán giá trị cho title
        statement.setString(2, "Dien thoai"); // Gán giá trị cho id
        int rowsUpdated = statement.executeUpdate(); // Thực thi lệnh UPDATE
        return rowsUpdated > 0 ? "THÀNH CÔNG" : "THÊM THẤT BẠI";
    } catch (Exception e) {
        e.printStackTrace();
        return "An error occurred: " + e.getMessage();
    }
       }    
    public String DeleteCate(int id){
        String query = "DELETE FROM categories WHERE idCate = ?";

        try (
        PreparedStatement statement = connection.prepareStatement(query)
    ) {
        statement.setInt(1, id); // Gán giá trị cho title
        int rowsUpdated = statement.executeUpdate(); // Thực thi lệnh UPDATE
        return rowsUpdated > 0 ? "XÓA THÀNH CÔNG" : "xÓA THẤT BẠI";
    } catch (Exception e) {
        e.printStackTrace();
        return "An error occurred: " + e.getMessage();
    }
       } 
    
    
    //Products
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
        return rowsUpdated > 0 ? "THÀNH CÔNG" : "THÊM THẤT BẠI";
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
        return rowsUpdated > 0 ? "THÊM THÀNH CÔNG" : "THÊM THẤT BẠI";
    } catch (Exception e) {
        e.printStackTrace();
        return "An error occurred: " + e.getMessage();
    }
       } 
    public String updateProduct(String names,float price,String thumbnail,String selectedStatus,String selectedCate,int idProduct){
        String query ="UPDATE products SET name = ?,SET thumbnail =? ,SET price =?,SET status =?,SET idCate =? WHERE idProduct = ? ";
        String queryIdCate="SELECT idCate FROM categories WHERE title = ?";

        try (
        PreparedStatement statement = connection.prepareStatement(query);
        PreparedStatement findIdCate=connection.prepareStatement(queryIdCate);
    ) {
        statement.setString(0, names); // Gán giá trị cho title
        ResultSet rs = findIdCate.executeQuery();
        int idCate = 0;
        if (rs.next()) {
            idCate = rs.getInt("idCate");
        } else {
            return "Category not found!";
        }        
        statement.setString(1, thumbnail); // Gán giá trị cho id
        statement.setString(2, selectedStatus); // Gán giá trị cho id
        statement.setInt(3, idCate); // Gán giá trị cho id
        statement.setInt(4, idProduct); // Gán giá trị cho id
        int rowsUpdated = statement.executeUpdate(); // Thực thi lệnh UPDATE
        return rowsUpdated > 0 ? "CẬP NHẬT THÀNH CÔNG" : "CẬP NHẬT THẤT BẠI";
    } catch (Exception e) {
        e.printStackTrace();
        return "An error occurred: " + e.getMessage();
    }
        } 
    
    }
