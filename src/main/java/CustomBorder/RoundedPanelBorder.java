/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomBorder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class RoundedPanelBorder implements Border {
    private int radius; // Bán kính bo góc

    public RoundedPanelBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1); // Cung cấp lề cho viền
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.BLACK);  // Màu của viền (có thể thay đổi)
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius); // Vẽ viền bo góc
    }
}
