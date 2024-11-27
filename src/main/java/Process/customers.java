/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import utils.PasswordUtils;

/**
 *
 * @author Nguyen Gia Huy
 */
public class customers {
    private final Connect cn = new Connect();
    private Connection conn;
    
    public customers() {
        this.conn = cn.connectSQL(); // Mở kết nối trong hàm tạo
    }
    public String login(String email, String password) throws SQLException {
    String customerQuery = "SELECT * FROM customers WHERE email = ? AND isDeleted = '0'";

    try (PreparedStatement customerStmt = conn.prepareStatement(customerQuery)) {
        // Kiểm tra bảng Customers
        customerStmt.setString(1, email);
        try (ResultSet customerRs = customerStmt.executeQuery()) {
            if (customerRs.next()) {
                String hashedPassword = customerRs.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    return customerRs.getString("role"); // Trả về vai trò nếu đăng nhập thành công
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi đăng nhập: " + e.getMessage());
        throw e;
    }

    return "none"; // Trả về none nếu không khớp
}


    public boolean registerCustomer(String email, String password, String fullName, String address, String phone) throws SQLException {
                String hashedPassword = PasswordUtils.hashBCrypt(password);

        String sql = "INSERT INTO customers (email, password, isDeleted, createdAt, updatedAt, fullName, numberOfPhone, address) "
                + "VALUES (?, ?, 0, GETDATE(), GETDATE(), ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, fullName);
            pstmt.setString(5, phone);
            pstmt.setString(4, address);
            pstmt.executeUpdate(); 
            return true;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm người dùng: " + e.getMessage());
            return false;
        }
    }
    
   public boolean isEmailExists(String email) throws SQLException {
    String sql = "SELECT COUNT(*) FROM customers WHERE email = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, email); // Đặt giá trị email cần kiểm tra
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0; // Trả về true nếu số lượng > 0, nghĩa là email tồn tại
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi kiểm tra email: " + e.getMessage());
    }

    return false; // Trả về false nếu có lỗi hoặc email không tồn tại
}
    public boolean updatePassword(String email, String newPassword) throws SQLException {
    // Câu lệnh SQL để kiểm tra sự tồn tại của email
    String checkEmailSql = "SELECT * FROM customers WHERE email = ? AND isDeleted = '0'";

    // Câu lệnh SQL để cập nhật mật khẩu mới
    String updatePasswordSql = "UPDATE customers SET password = ?, updateAt = GETDATE() WHERE email = ? AND isDeleted = '0'";

    try (PreparedStatement checkStmt = conn.prepareStatement(checkEmailSql); 
         PreparedStatement updateStmt = conn.prepareStatement(updatePasswordSql)) {

        // Kiểm tra sự tồn tại của email
        checkStmt.setString(1, email);
        try (ResultSet rs = checkStmt.executeQuery()) {
            if (rs.next()) {
                // Nếu email tồn tại, cập nhật mật khẩu mới
                String hashedPassword = PasswordUtils.hashBCrypt(newPassword);
                updateStmt.setString(1, hashedPassword);
                updateStmt.setString(2, email);
                int rowsAffected = updateStmt.executeUpdate();
                
                // Nếu có dòng bị ảnh hưởng, tức là cập nhật thành công
                return rowsAffected > 0;
            } else {
                // Nếu email không tồn tại
                System.err.println("Email không tồn tại.");
                return false;
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi cập nhật mật khẩu: " + e.getMessage());
        return false;
    }
}
public String getCustomerNameByEmail(String email) throws SQLException {
    String customerQuery = "SELECT fullName FROM customers WHERE email = ? AND isDeleted = '0'";

    try (PreparedStatement stmt = conn.prepareStatement(customerQuery)) {
        stmt.setString(1, email);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("fullName"); // Trả về tên đầy đủ của khách hàng
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy tên người dùng: " + e.getMessage());
        throw e;
    }

    return null; // Trả về null nếu không tìm thấy người dùng
}

  // Cập nhật thông tin người dùng
    public boolean updateUserInfo(String email, String fullName, String address, String phone, String password) {
        String checkEmailSql = "SELECT * FROM customers WHERE email = ? AND isDeleted = '0'";
            String checkPhoneSql = "SELECT * FROM customers WHERE numberOfPhone = ? AND email != ? AND isDeleted = '0'";  // Check if phone exists for other users
        String updateSql = "UPDATE customers SET fullName = ?, address = ?, numberOfPhone = ?, updatedAt = GETDATE()";

        // Nếu có mật khẩu mới, thêm vào câu lệnh cập nhật
        if (password != null && !password.isEmpty()) {
            String hashedPassword = PasswordUtils.hashBCrypt(password); // Băm mật khẩu mới
            updateSql += ", password = ?"; // Thêm mật khẩu vào câu lệnh UPDATE
        }

        updateSql += " WHERE email = ? AND isDeleted = '0'"; // Cập nhật theo email

        try (PreparedStatement checkStmt = conn.prepareStatement(checkEmailSql); 
             PreparedStatement checkPhoneStmt = conn.prepareStatement(checkPhoneSql); 
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // Kiểm tra sự tồn tại của email
           checkStmt.setString(1, email);
        try (ResultSet rs = checkStmt.executeQuery()) {
            if (rs.next()) {
                // Kiểm tra xem số điện thoại đã tồn tại chưa (cho người dùng khác)
                checkPhoneStmt.setString(1, phone);
                checkPhoneStmt.setString(2, email);
                try (ResultSet phoneRs = checkPhoneStmt.executeQuery()) {
                    if (phoneRs.next()) {
                        System.err.println("Số điện thoại đã tồn tại.");
                        return false;  // Nếu số điện thoại đã tồn tại, trả về false
                    }
                }

                // Cập nhật thông tin người dùng
                updateStmt.setString(1, fullName);
                updateStmt.setString(2, address);
                updateStmt.setString(3, phone);

                // Nếu mật khẩu mới có, thêm vào câu lệnh UPDATE
                if (password != null && !password.isEmpty()) {
                    String hashedPassword = PasswordUtils.hashBCrypt(password); // Băm mật khẩu
                    updateStmt.setString(4, hashedPassword);
                    updateStmt.setString(5, email);
                } else {
                    updateStmt.setString(4, email);
                }

                int rowsAffected = updateStmt.executeUpdate();
                return rowsAffected > 0; // Trả về true nếu có dòng bị ảnh hưởng
            } else {
                System.err.println("Email không tồn tại.");
                return false;  // Nếu email không tồn tại, trả về false
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi cập nhật thông tin người dùng: " + e.getMessage());
        return false;  // Nếu có lỗi, trả về false
    }
    }
    
    public boolean isPhoneUnique(String phone, String email) {
    String checkPhoneSql = "SELECT * FROM customers WHERE numberOfPhone = ? AND email != ? AND isDeleted = '0'";

    try (PreparedStatement stmt = conn.prepareStatement(checkPhoneSql)) {
        stmt.setString(1, phone);
        stmt.setString(2, email);  // Exclude the current user

        try (ResultSet rs = stmt.executeQuery()) {
            return !rs.next(); // If no rows are returned, the phone number is unique
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi kiểm tra số điện thoại: " + e.getMessage());
        return false;  // If there's an error, assume phone is not unique
    }
}
public user findUserByEmail(String email){
    String detailsQuery = "SELECT * from customers WHERE email = ?";    
    user us = new user();
        try (PreparedStatement stmt = conn.prepareStatement(detailsQuery)) {
        stmt.setString(1, email);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
               String name = rs.getString("fullName");
               String role = rs.getString("role");
               String phone = rs.getString("numberOfPhone");
               String address = rs.getString("address");
               us = new user(email, name, role, address, phone);
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy tên người dùng: " + e.getMessage());
    }    
        return us;
}
}
