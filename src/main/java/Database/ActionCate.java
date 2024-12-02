package Database;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ActionCate {
    
    private Connect conn;
    private Connection connection ;

    public ActionCate() {
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



    }

