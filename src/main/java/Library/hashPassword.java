/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Nguyen Gia Huy
 */
public class hashPassword {

    // Hàm hash SHA-256
    public static String hashSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi khi hash SHA-256: " + e.getMessage());
        }
    }

    // Hàm hash bằng BCrypt
    public static String hashBCrypt(String input) {
        return BCrypt.hashpw(input, BCrypt.gensalt(12)); // Độ mạnh là 12
    }

    // Hàm xác thực mật khẩu với hash BCrypt
    public static boolean verifyBCrypt(String input, String hashedPassword) {
        return BCrypt.checkpw(input, hashedPassword);
    }

    // Hàm kết hợp SHA-256 và BCrypt
    public static String hashWithSHA256AndBCrypt(String input) {
        // Hash lần 1: SHA-256
        String sha256Hash = hashSHA256(input);
        // Hash lần 2: BCrypt
        return hashBCrypt(sha256Hash);
    }

    // Hàm xác thực mật khẩu đã mã hóa bằng SHA-256 và BCrypt
    public static boolean verifyWithSHA256AndBCrypt(String input, String hashedPassword) {
        // Hash lần 1: SHA-256
        String sha256Hash = hashSHA256(input);
        // Kiểm tra kết quả SHA-256 với hash BCrypt
        return verifyBCrypt(sha256Hash, hashedPassword);
    }
}
