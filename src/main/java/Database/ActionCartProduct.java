/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Pojo.CartProduct;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author An Ninh
 */
public class ActionCartProduct {
     private final Connect cn = new Connect();
    private Connection conn;
    private CartProduct cartProduct=new CartProduct();
    public ActionCartProduct() {
        this.conn = cn.connectSQL(); // Kết nối tới database
    }
    
    public List<CartProduct> getCartProduct(int idCustomer) {
    List<CartProduct> cartProducts = new ArrayList<>();
    String query = "SELECT idCartProduct, idProduct , nameProduct, quantity, category, TotalPrice,idCustomer,status FROM cartProduct WHERE idCustomer = ?"; // Truy vấn để lấy sản phẩm trong giỏ hàng của khách hàng
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, idCustomer); 

        try (ResultSet rs = stmt.executeQuery()) { 
            while (rs.next()) {
                CartProduct cartProduct = new CartProduct();
                
                cartProduct.setIdCartProduct(rs.getString("idCartProduct"));
                cartProduct.setNameProduct(rs.getString("nameProduct"));
                cartProduct.setQuantity(rs.getInt("quantity"));
                cartProduct.setCategory(rs.getString("category"));
                cartProduct.setTotalPrice(rs.getBigDecimal("TotalPrice")); 
                cartProduct.setIdProduct(rs.getString("idProduct"));
                cartProduct.setStatus(rs.getString("status"));
                cartProduct.setIdCustomer(rs.getString("idCustomer"));
                cartProducts.add(cartProduct);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    
    return cartProducts; 
}
    
    public String addProductToCart(String idProduct, String idCustomer, String nameProduct, int quantity, String category, BigDecimal TotalPrice,String status) {
    String query = "INSERT INTO cartProduct(idProduct, idCustomer, nameProduct, quantity, category, TotalPrice,status) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, idProduct);
        stmt.setString(2, idCustomer);
        stmt.setString(3, nameProduct);
        stmt.setInt(4, quantity);
        stmt.setString(5, category);
        stmt.setBigDecimal(6, TotalPrice);
        stmt.setString(7, status);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            return "Sản phẩm đã được thêm vào giỏ hàng thành công.";
        } else {
            return "Không thể thêm sản phẩm vào giỏ hàng.";
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return "Lỗi: " + e.getMessage();
    }
}



    
    public String updateProductQuantity(String idCartProduct, int newQuantity) {
    String query = "UPDATE cartProduct SET quantity = ? WHERE idCartProduct = ?";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
      
        stmt.setInt(1, newQuantity);
        stmt.setString(2, idCartProduct);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            return "Thành công"; 
        } else {
            return "Không thành công: Không tìm thấy sản phẩm";
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
        return "Lỗi: " + e.getMessage();
    }
}

    public String deleteProductFromCart(String idCartProduct) {
    String query = "DELETE FROM cartProduct WHERE idCartProduct = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setString(1, idCartProduct);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            return "Thành công"; 
        } else {
            return "Không thành công: Không xóa được sản phẩm ra khỏi giỏ hàng";
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In ra lỗi nếu có
        return "Lỗi: " + e.getMessage();
    }
}
public boolean deleteCartProducts(List<CartProduct> cartToDelete) throws SQLException {
    if (cartToDelete == null || cartToDelete.isEmpty()) {
        throw new IllegalArgumentException("Danh sách không hợp lệ.");
    }

    String deleteSql = "DELETE FROM cartProduct WHERE idCartProduct = ?";  // Xóa dữ liệu trong bảng cartProduct

    try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
        int rowsAffected = 0;

        for (CartProduct cartProduct : cartToDelete) {
            deleteStmt.setString(1, cartProduct.getIdCartProduct());  // Đảm bảo sử dụng đúng kiểu dữ liệu (int)
            rowsAffected += deleteStmt.executeUpdate();
        }

        return rowsAffected > 0;  // Trả về true nếu có ít nhất một bản ghi bị xóa
    } catch (SQLException e) {
        System.err.println("Lỗi khi xóa sản phẩm trong giỏ hàng: " + e.getMessage());
        return false;  // Trả về false nếu có lỗi
    }
}





}
