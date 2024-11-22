/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.component;
import javax.swing.border.Border;

/**
 *
 * @author Nguyen Gia Huy
 */
import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    
     public RoundedButton() {
        this("Button");  // Gọi constructor với tham số, thiết lập chữ mặc định là "Button"
    }

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);  // Không vẽ nền mặc định của JButton
        setFocusPainted(false);       // Không vẽ viền khi được focus
        setForeground(Color.WHITE);   // Đặt màu chữ thành trắng
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Bật chế độ khử răng cưa để vẽ mịn hơn
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Đặt màu nền cho nút
        g2d.setColor(new Color(92, 137, 204));
        
        // Vẽ hình chữ nhật bo góc 8px
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
        
        // Giải phóng tài nguyên
        g2d.dispose();
        
        // Gọi phương thức của lớp cha để vẽ chữ
        super.paintComponent(g);
    }

    @Override
    public void setBorder(Border border) {
        // Đặt viền trống (padding) cho nút
        super.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    public static void main(String[] args) {
        // Tạo JFrame để thử nghiệm nút
        JFrame frame = new JFrame("Rounded Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Tạo và thêm RoundedButton vào JFrame
        RoundedButton button = new RoundedButton("My Button");
        frame.add(button);

        frame.setSize(200, 100);
        frame.setVisible(true);
    }
}
