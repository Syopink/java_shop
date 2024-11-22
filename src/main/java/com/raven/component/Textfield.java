/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.component;

/**
 *
 * @author Nguyen Gia Huy
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class Textfield extends JTextField {
    
    // Constructor để tạo một JTextField với kích thước cụ thể
    public Textfield(int columns) {
        super(columns);
        setOpaque(false);  // Để nền trong suốt
    }
    
    public Textfield() {
    this(20); // hoặc bất kỳ số cột mặc định nào bạn muốn
}

    // Vẽ lại các góc để tạo hiệu ứng bo góc
    @Override
    protected void paintComponent(Graphics g) {
        // Tạo một đối tượng Graphics2D để xử lý vẽ
        Graphics2D g2d = (Graphics2D) g;
        // Vẽ nền của JTextField với màu nền mặc định
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);  // 8px bo góc
        super.paintComponent(g);
    }

    // Định nghĩa border bo góc
    @Override
    public void setBorder(Border border) {
        super.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Set margin nếu cần
    }

    public static void main(String[] args) {
        // Tạo một cửa sổ JFrame để thử nghiệm
        JFrame frame = new JFrame("Rounded JTextField Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        // Tạo và thêm RoundedTextField vào JFrame
        Textfield textField = new Textfield(20);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        frame.add(textField);
        
        frame.setSize(300, 100);
        frame.setVisible(true);
    }
}
