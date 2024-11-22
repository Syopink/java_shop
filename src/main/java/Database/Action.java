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
        return rowsUpdated > 0 ? "Cập nhật thành công" : "Cập nhật thất bại";
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
        return rowsUpdated > 0 ? "Thêm mới thành công" : "Thêm mới thất bại";
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
        return rowsUpdated > 0 ? "Xóa thành công" : "Xóa thất bại";
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
//public String filterProducts(String selectedCate, Float Min, Float Max) {
//    String queryRanges = "SELECT * FROM products WHERE price > ? AND price < ?";
//    String queryOutRanges = "SELECT * FROM products WHERE price > ?";
//    String queryCateRange = "SELECT * FROM products WHERE idCate = ?";
//    String queryIdCate = "SELECT idCate FROM categories WHERE title = ?";
//    String queryCateRangeWithPrice = "SELECT * FROM products WHERE idCate = ? AND price > ? AND price < ?";
//    String queryCateRangeWithPriceOut = "SELECT * FROM products WHERE idCate = ? AND price > ?";
//
//    try (
//        PreparedStatement statementRanges = connection.prepareStatement(queryRanges);
//        PreparedStatement statementOutRanges = connection.prepareStatement(queryOutRanges);
//        PreparedStatement statementIdCate = connection.prepareStatement(queryIdCate);
//        PreparedStatement statementCateRange = connection.prepareStatement(queryCateRange);
//        PreparedStatement statementCateRangeWithPrice = connection.prepareStatement(queryCateRangeWithPrice);
//        PreparedStatement statementCateRangeWithPriceOut = connection.prepareStatement(queryCateRangeWithPriceOut);
//    ) {
//        ResultSet rs;
//
//        // Xử lý khi có `selectedCate`
//        int idCate = 0;
//        if (selectedCate != null && !selectedCate.isEmpty()) {
//            statementIdCate.setString(1, selectedCate);
//            rs = statementIdCate.executeQuery();
//            if (rs.next()) {
//                idCate = rs.getInt("idCate");
//            } else {
//                return "Category not found!";
//            }
//        }
//
//        // Kiểm tra điều kiện và thực hiện truy vấn tương ứng
//        if (selectedCate != null && Min != null && Max != null) {
//            // Truy vấn theo idCate và khoảng giá
//            statementCateRangeWithPrice.setInt(1, idCate);
//            statementCateRangeWithPrice.setFloat(2, Min);
//            statementCateRangeWithPrice.setFloat(3, Max);
//            rs = statementCateRangeWithPrice.executeQuery();
//        } else if (selectedCate != null && Min != null) {
//            // Truy vấn theo idCate và giá min
//            statementCateRangeWithPriceOut.setInt(1, idCate);
//            statementCateRangeWithPriceOut.setFloat(2, Min);
//            rs = statementCateRangeWithPriceOut.executeQuery();
//        } else if (Min != null && Max != null) {
//            // Truy vấn theo khoảng giá khi không có `selectedCate`
//            statementRanges.setFloat(1, Min);
//            statementRanges.setFloat(2, Max);
//            rs = statementRanges.executeQuery();
//        } else if (Min != null) {
//            // Truy vấn theo giá min khi không có `selectedCate`
//            statementOutRanges.setFloat(1, Min);
//            rs = statementOutRanges.executeQuery();
//        } else {
//            return "Invalid query conditions!";
//        }
//
//        // Xử lý kết quả truy vấn
//        StringBuilder result = new StringBuilder();
//        while (rs.next()) {
//            result.append("Product ID: ").append(rs.getInt("id")).append("\n");
//            result.append("Product Name: ").append(rs.getString("name")).append("\n");
//            result.append("Product Price: ").append(rs.getFloat("price")).append("\n");
//        }
//        return result.length() > 0 ? result.toString() : "Không tìm thấy sản phẩm";
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        return "Error occurred during query execution.";
//    }
//}


    }

