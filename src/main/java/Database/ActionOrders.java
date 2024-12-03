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
public class ActionOrders {
    private Connect conn;
    private Connection connection;
    
    public ActionOrders(){
        conn = new Connect();
        connection = conn.connectSQL();
    }

    public List<Object[]> getOrders() {
        String query = "SELECT * FROM products"; // Assuming you're getting order-related data from the "products" table
        List<Object[]> resultList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            // Iterate through the result set
            while (resultSet.next()) {
                int idProduct = resultSet.getInt("idProduct");
                int idCustomer = resultSet.getInt("idCustomer");
                int idOrder = resultSet.getInt("idOrder");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String item = resultSet.getString("item");
                String address = resultSet.getString("address");
                String createdAt = resultSet.getString("createdAt");

                // Define an array to store the data (size 9 to accommodate all columns)
                Object[] row = new Object[9];  // Updated size
                row[0] = idOrder;
                row[1] = idCustomer;
                row[2] = idProduct;
                row[3] = name;
                row[4] = phone;
                row[5] = email;
                row[6] = address;
                row[7] = item;
                row[8] = createdAt;
                
                resultList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return resultList;
    }
     
}

