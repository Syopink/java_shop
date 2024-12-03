/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Database.Connect;
import Pojo.Customer;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    // Kiểm tra xem email và số điện thoại đã tồn tại chưa
    if (isEmailExists(email)) {
        System.out.println("Email đã tồn tại!");
        return false; // Nếu email đã tồn tại, trả về false
    }
    
    if (isPhoneExists(phone)) {
        System.out.println("Số điện thoại đã tồn tại!");
        return false; // Nếu số điện thoại đã tồn tại, trả về false
    }
    
    // Mã hóa mật khẩu
    String hashedPassword = PasswordUtils.hashBCrypt(password);

    String sql = "INSERT INTO customers (email, password, isDeleted, createdAt, updatedAt, fullName, numberOfPhone, address) "
               + "VALUES (?, ?, 0, GETDATE(), GETDATE(), ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, email);
        pstmt.setString(2, hashedPassword);
        pstmt.setString(3, fullName);
        pstmt.setString(4, address);
        pstmt.setString(5, phone);
        pstmt.executeUpdate();
        return true; // Đăng ký thành công
    } catch (SQLException e) {
        System.err.println("Lỗi khi thêm người dùng: " + e.getMessage());
        return false; // Lỗi khi thực hiện truy vấn
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
    
        public boolean isPhoneExists(String phone) throws SQLException {
        String sql = "SELECT COUNT(*) FROM customers WHERE numberOfPhone = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phone); // Đặt giá trị email cần kiểm tra
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu số lượng > 0, nghĩa là email tồn tại
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra số điện thoại: " + e.getMessage());
        }

        return false; // Trả về false nếu có lỗi hoặc email không tồn tại
    }

    public boolean updatePassword(String email, String newPassword) throws SQLException {
        // Câu lệnh SQL để kiểm tra sự tồn tại của email
        String checkEmailSql = "SELECT * FROM customers WHERE email = ? AND isDeleted = '0'";

        // Câu lệnh SQL để cập nhật mật khẩu mới
        String updatePasswordSql = "UPDATE customers SET password = ?, updateAt = GETDATE() WHERE email = ? AND isDeleted = '0'";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkEmailSql); PreparedStatement updateStmt = conn.prepareStatement(updatePasswordSql)) {

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

        try (PreparedStatement checkStmt = conn.prepareStatement(checkEmailSql); PreparedStatement checkPhoneStmt = conn.prepareStatement(checkPhoneSql); PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

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

    public user findUserByEmail(String email) {
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
//
//    public List<Customer> getAllCustomers() throws SQLException {
//        List<Customer> customers = new ArrayList<>();
//        String sql = "SELECT * FROM customers WHERE isDeleted = '0' AND role = 'customer' ";  // Lấy danh sách khách hàng chưa bị xóa
//
//        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
//
//            while (rs.next()) {
//                String id = rs.getString("idCustomer");
//                String email = rs.getString("email");
//                String fullName = rs.getString("fullName");
//                String phone = rs.getString("numberOfPhone");
//                String address = rs.getString("address");
//                String role = rs.getString("role");
//
//                customers.add(new Customer(id, email, fullName, phone, address, role));  // Thêm khách hàng vào danh sách
//            }
//        } catch (SQLException e) {
//            System.err.println("Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
//            throw e;  // Ném lại ngoại lệ nếu có lỗi
//        }
//        return customers;
//    }
//
//    public List<Customer> getAllCustomersDeleted() throws SQLException {
//        List<Customer> customers = new ArrayList<>();
//        String sql = "SELECT * FROM customers WHERE isDeleted = '1' AND role = 'customer' ";  // Lấy danh sách khách hàng chưa bị xóa
//
//        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
//
//            while (rs.next()) {
//                String id = rs.getString("idCustomer");
//                String email = rs.getString("email");
//                String fullName = rs.getString("fullName");
//                String phone = rs.getString("numberOfPhone");
//                String address = rs.getString("address");
//                String role = rs.getString("role");
//
//                customers.add(new Customer(id, email, fullName, phone, address, role));  // Thêm khách hàng vào danh sách
//            }
//        } catch (SQLException e) {
//            System.err.println("Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
//            throw e;  // Ném lại ngoại lệ nếu có lỗi
//        }
//        return customers;
//    }
    
//    
//       public List<Customer> getAllUsers() throws SQLException {
//        List<Customer> customers = new ArrayList<>();
//        String sql = "SELECT * FROM customers WHERE isDeleted = '0' AND role = 'customer' ";  // Lấy danh sách khách hàng chưa bị xóa
//
//        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
//
//            while (rs.next()) {
//                String id = rs.getString("idCustomer");
//                String email = rs.getString("email");
//                String fullName = rs.getString("fullName");
//                String phone = rs.getString("numberOfPhone");
//                String address = rs.getString("address");
//                String role = rs.getString("role");
//
//                customers.add(new Customer(id, email, fullName, phone, address, role));  // Thêm khách hàng vào danh sách
//            }
//        } catch (SQLException e) {
//            System.err.println("Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
//            throw e;  // Ném lại ngoại lệ nếu có lỗi
//        }
//        return customers;
//    }
    
    public List<Customer> getAllByRoleAndStatus(String role, boolean isDeleted) throws SQLException {
    List<Customer> customers = new ArrayList<>();
    String sql = "SELECT * FROM customers WHERE role = ? AND isDeleted = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, role);
        pstmt.setBoolean(2, isDeleted);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("idCustomer");
                String email = rs.getString("email");
                String fullName = rs.getString("fullName");
                String phone = rs.getString("numberOfPhone");
                String address = rs.getString("address");
                customers.add(new Customer(id, email, fullName, phone, address, role));
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi lấy danh sách: " + e.getMessage());
        throw e;
    }
    return customers;
}
    
    public List<Customer> getAllCustomers() throws SQLException {
    return getAllByRoleAndStatus("customer", false);
}

public List<Customer> getAllCustomersDeleted() throws SQLException {
    return getAllByRoleAndStatus("customer", true);
}

public List<Customer> getAllUsers() throws SQLException {
    return getAllByRoleAndStatus("admin", false);
}

public List<Customer> getAllUsersDeleted() throws SQLException {
    return getAllByRoleAndStatus("admin", true);
}

//
//    public List<Customer> getAllUsersDeleted() throws SQLException {
//        List<Customer> customers = new ArrayList<>();
//        String sql = "SELECT * FROM customers WHERE isDeleted = '1' AND role = 'customer' ";  // Lấy danh sách khách hàng chưa bị xóa
//
//        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
//
//            while (rs.next()) {
//                String id = rs.getString("idCustomer");
//                String email = rs.getString("email");
//                String fullName = rs.getString("fullName");
//                String phone = rs.getString("numberOfPhone");
//                String address = rs.getString("address");
//                String role = rs.getString("role");
//
//                customers.add(new Customer(id, email, fullName, phone, address, role));  // Thêm khách hàng vào danh sách
//            }
//        } catch (SQLException e) {
//            System.err.println("Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
//            throw e;  // Ném lại ngoại lệ nếu có lỗi
//        }
//        return customers;
//    }
//    
    

    public boolean deleteCustomer(String id) throws SQLException {
        String query; // Câu truy vấn sẽ thay đổi tùy theo trạng thái
        boolean isDeleted = false;

        // Kiểm tra trạng thái hiện tại của khách hàng
        String checkStatusSql = "SELECT isDeleted FROM customers WHERE idCustomer = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkStatusSql)) {
            checkStmt.setString(1, id);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    isDeleted = rs.getBoolean("isDeleted");
                } else {
                    System.err.println("Khách hàng không tồn tại.");
                    return false; // Không thực hiện nếu khách hàng không tồn tại
                }
            }
        }

        // Quyết định hành động dựa trên trạng thái
        if (isDeleted) {
            // Xóa vĩnh viễn khách hàng
            query = "DELETE FROM customers WHERE idCustomer = ? AND isDeleted = '1'";
        } else {
            // Cập nhật trạng thái isDeleted thành 1
            query = "UPDATE customers SET isDeleted = '1', updatedAt = GETDATE() WHERE idCustomer = ? AND isDeleted = '0'";
        }

        // Thực thi truy vấn
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu thực hiện thành công
        } catch (SQLException e) {
            System.err.println("Lỗi khi xử lý khách hàng: " + e.getMessage());
            return false;
        }
    }

    public Customer getCustomerById(String idCustomer) throws SQLException {
        String sql = "SELECT * FROM customers WHERE idCustomer = ? AND isDeleted = '0'";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idCustomer);  // Đặt giá trị ID khách hàng

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String email = rs.getString("email");
                    String fullName = rs.getString("fullName");
                    String phone = rs.getString("numberOfPhone");
                    String address = rs.getString("address");
                    String role = rs.getString("role");

                    return new Customer(idCustomer, email, fullName, phone, address, role);  // Trả về đối tượng Customer
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy thông tin khách hàng: " + e.getMessage());
            throw e;  // Ném lại ngoại lệ nếu có lỗi
        }
        return null;  // Trả về null nếu không tìm thấy khách hàng
    }

    public Customer getCustomerByEmail(String email) throws SQLException {
        // Câu lệnh SQL để tìm khách hàng theo email
        String sql = "SELECT * FROM customers WHERE email = ? AND isDeleted = '0'";  // Chỉ tìm khách hàng chưa bị xóa (isDeleted = '0')

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);  // Đặt giá trị email vào câu lệnh SQL

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Nếu tìm thấy khách hàng
                    String idCustomer = rs.getString("idCustomer");
                    String fullName = rs.getString("fullName");
                    String phone = rs.getString("numberOfPhone");
                    String address = rs.getString("address");
                    String role = rs.getString("role");

                    // Tạo đối tượng Customer từ kết quả truy vấn và trả về
                    return new Customer(idCustomer, email, fullName, phone, address, role);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm khách hàng theo email: " + e.getMessage());
            throw e;  // Ném lại ngoại lệ nếu có lỗi
        }

        return null;  // Trả về null nếu không tìm thấy khách hàng
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        // Câu lệnh SQL để cập nhật thông tin khách hàng
        String sql = "UPDATE customers SET email = ?, fullName = ?, numberOfPhone = ?, address = ?, role = ? WHERE idCustomer = ? AND isDeleted = '0'";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Đặt giá trị các trường vào câu lệnh SQL
            pstmt.setString(1, customer.getEmail());
            pstmt.setString(2, customer.getFullName());
            pstmt.setString(3, customer.getNumberOfPhone());
            pstmt.setString(4, customer.getAddress());
            pstmt.setString(5, customer.getRole());
            pstmt.setString(6, customer.getId());

            // Thực thi câu lệnh UPDATE
            int rowsUpdated = pstmt.executeUpdate();

            return rowsUpdated > 0; // Trả về true nếu có bản ghi được cập nhật
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật thông tin khách hàng: " + e.getMessage());
            throw e;  // Ném lại ngoại lệ nếu có lỗi
        }
    }

    public boolean deleteCustomers(String idCustomer) throws SQLException {
        // Câu lệnh SQL để đánh dấu khách hàng là đã xóa
        String sql = "UPDATE customers SET isDeleted = '1' WHERE idCustomer = ? AND isDeleted = '0'";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Đặt giá trị idCustomer vào câu lệnh SQL
            pstmt.setString(1, idCustomer);

            // Thực thi câu lệnh UPDATE
            int rowsUpdated = pstmt.executeUpdate();

            return rowsUpdated > 0; // Trả về true nếu có bản ghi được xóa
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa khách hàng: " + e.getMessage());
            throw e;  // Ném lại ngoại lệ nếu có lỗi
        }
    }
public List<Customer> searchByRole(String role, String name, String email, boolean isDeleted) throws SQLException {
    StringBuilder sql = new StringBuilder("SELECT * FROM customers WHERE role = ? AND isDeleted = ?");
    List<Object> params = new ArrayList<>();
    
    params.add(role);
    params.add(isDeleted ? 1 : 0);
    
    if (!name.isEmpty()) {
        sql.append(" AND fullName LIKE ?");
        params.add("%" + name + "%");
    }
    
    if (!email.isEmpty()) {
        sql.append(" AND email LIKE ?");
        params.add("%" + email + "%");
    }
    
    List<Customer> customers = new ArrayList<>();
    try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
        for (int i = 0; i < params.size(); i++) {
            pstmt.setObject(i + 1, params.get(i));
        }
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String idCustomer = rs.getString("idCustomer");
                String fullName = rs.getString("fullName");
                String emailAddr = rs.getString("email");
                String phone = rs.getString("numberOfPhone");
                String address = rs.getString("address");
                customers.add(new Customer(idCustomer, emailAddr, fullName, phone, address, role));
            }
        }
    } catch (SQLException e) {
        System.err.println("Lỗi khi tìm kiếm: " + e.getMessage());
        throw e;
    }
    return customers;
}

public List<Customer> searchCustomer(String name, String email, boolean isDeleted) throws SQLException {
    return searchByRole("customer", name, email, isDeleted);
}

public List<Customer> searchUser(String name, String email, boolean isDeleted) throws SQLException {
    return searchByRole("admin", name, email, isDeleted);
}


    public boolean deleteCustomers(List<Customer> customersToDelete) throws SQLException {
        if (customersToDelete == null || customersToDelete.isEmpty()) {
            throw new IllegalArgumentException("Danh sách không hợp lệ.");
        }

        String checkStatusSql = "SELECT isDeleted FROM customers WHERE idCustomer = ?";
        String updateSql = "UPDATE customers SET isDeleted = '1', updatedAt = GETDATE() WHERE idCustomer = ? AND isDeleted = '0'";
        String deleteSql = "DELETE FROM customers WHERE idCustomer = ? AND isDeleted = '1'";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkStatusSql); PreparedStatement updateStmt = conn.prepareStatement(updateSql); PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {

            int rowsAffected = 0;

            for (Customer customer : customersToDelete) {
                checkStmt.setString(1, String.valueOf(customer.getId()));  // Đặt giá trị ID của khách hàng cần kiểm tra trạng thái

                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        boolean isDeleted = rs.getBoolean("isDeleted");

                        if (isDeleted) {
                            // Nếu khách hàng đã bị xóa (isDeleted = 1), thực hiện xóa vĩnh viễn
                            deleteStmt.setString(1, String.valueOf(customer.getId()));
                            rowsAffected += deleteStmt.executeUpdate();
                        } else {
                            // Nếu khách hàng chưa bị xóa (isDeleted = 0), thực hiện cập nhật trạng thái thành isDeleted = 1
                            updateStmt.setString(1, String.valueOf(customer.getId()));
                            rowsAffected += updateStmt.executeUpdate();
                        }
                    } else {
                        System.err.println("Khách hàng không tồn tại với ID: " + customer.getId());
                    }
                }
            }

            return rowsAffected > 0;  // Trả về true nếu có ít nhất một khách hàng bị xử lý (cập nhật/xóa)
        } catch (SQLException e) {
            System.err.println("Lỗi khi xử lý khách hàng: " + e.getMessage());
            return false;  // Trả về false nếu có lỗi
        }
    }

    public boolean restoreCustomers(List<Customer> customersToRestore) throws SQLException {
        String restoreSql = "UPDATE customers SET isDeleted = '0', updatedAt = GETDATE() WHERE idCustomer = ? AND isDeleted = '1'";

        try (PreparedStatement pstmt = conn.prepareStatement(restoreSql)) {
            int rowsAffected = 0;

            for (Customer customer : customersToRestore) {
                pstmt.setString(1, String.valueOf(customer.getId()));  // Đặt ID khách hàng cần phục hồi
                rowsAffected += pstmt.executeUpdate();  // Cộng dồn số dòng bị ảnh hưởng
            }

            return rowsAffected > 0;  // Trả về true nếu có ít nhất 1 khách hàng được phục hồi
        } catch (SQLException e) {
            System.err.println("Lỗi khi phục hồi: " + e.getMessage());
            return false;  // Trả về false nếu có lỗi
        }
    }
}
