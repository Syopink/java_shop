/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomBorder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class RoundedTextFieldBorder implements Border {
    private int radius; // Bán kính bo góc

    public RoundedTextFieldBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.GRAY);  // Màu viền, có thể thay đổi theo ý muốn
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);  // Vẽ viền bo góc
    }
}

