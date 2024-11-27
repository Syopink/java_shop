/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Capcha extends JPanel {
    private JTextField captchaField;
    private JButton refreshButton;

    public Capcha() {
        // Cấu hình JPanel
        setLayout(new FlowLayout());
        setBackground(Color.WHITE); // Đặt nền trắng cho JPanel

        // Tạo JTextField (hiển thị Captcha)
        captchaField = new JTextField(6);
        captchaField.setFont(new Font("Segoe UI ", Font.BOLD, 16)); // Đặt font size nhỏ hơn (18)
        captchaField.setHorizontalAlignment(JTextField.CENTER);
        captchaField.setEditable(false); // Không cho phép chỉnh sửa
        captchaField.setText(generateCaptcha()); // Đặt giá trị CAPTCHA ban đầu
        captchaField.setEnabled(false);
        // Tạo nút "Tạo mới" để tạo Captcha mới
        refreshButton = new JButton("Tạo mới");
        refreshButton.setFont(new Font("Segoe UI ", Font.PLAIN, 12)); // Đặt font cho nút
        refreshButton.addActionListener(e -> captchaField.setText(generateCaptcha())); // Tạo CAPTCHA mới khi nhấn nút

        // Thêm các thành phần vào JPanel
        add(new JLabel("")); // Thêm khoảng cách
        add(captchaField);
        add(refreshButton);
    }

    // Hàm tạo mã Captcha ngẫu nhiên (6 ký tự chữ và số)
    private String generateCaptcha() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length()))); // Thêm ký tự ngẫu nhiên vào CAPTCHA
        }
        return captcha.toString(); // Trả về mã CAPTCHA mới
    }

    // Trả về giá trị CAPTCHA hiện tại (mới được tạo ra hoặc đã được thay đổi)
    public String getCaptchaField() {
        return captchaField.getText(); // Trả về giá trị CAPTCHA hiện tại trong captchaField
    }
    public void resetCaptcha() {
    captchaField.setText(generateCaptcha()); // Gọi hàm tạo captcha mới và đặt lại giá trị
}
}
