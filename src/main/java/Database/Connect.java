package Database;

import java.sql.*;
import javax.swing.*;

public class Connect {

    private Connection conn = null;

    public Connection connectSQL() {
        if (conn != null) { // Kiểm tra nếu đã có kết nối
            return conn;
        }

        try {
             String userName = "sa";
            String password = "123456";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=VietPro;encrypt=true;trustServerCertificate=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy driver SQL Server.", "Thông báo", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kết nối CSDL thất bại: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return conn;
    }
}
