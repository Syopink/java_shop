/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components;


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author An Ninh
 */
public class Navbar extends javax.swing.JPanel {

    /**
     * Creates new form Navbar
     */
 

    // Phương thức đặt lại màu nền cho tất cả các toggleButton về màu mặc định
    
    private Color defaultBackgroundColor = new Color(55,65,92); // Màu nền mặc định
    private Color selectedBackgroundColor = new Color(24,26,47); // Màu nền khi nút được chọn

    public Navbar() {
        initComponents();
        repaint();
    }
    
    private void updateBackgroundColor(javax.swing.JPanel selectedPanel) {
    // Đặt lại màu nền cho tất cả các toggleButton về màu mặc định
    toggleButtonRouter4.getPanel().setBackground(defaultBackgroundColor);
    toggleButtonRouter6.getPanel().setBackground(defaultBackgroundColor);
    toggleButtonRouter1.getPanel().setBackground(defaultBackgroundColor);
    toggleButtonRouter2.getPanel().setBackground(defaultBackgroundColor);
    toggleButtonRouter3.getPanel().setBackground(defaultBackgroundColor);
    toggleButtonRouter5.getPanel().setBackground(defaultBackgroundColor);
    toggleButtonRouter7.getPanel().setBackground(defaultBackgroundColor);

    
    // Đặt màu nền cho nút được chọn
    selectedPanel.setBackground(selectedBackgroundColor);
}   

    // Phương thức đặt lại màu nền cho tất cả các toggleButton về màu mặc định
   

    public void setRouter1(JPanel mainPanel, String text) {
        toggleButtonRouter4.set(text, mainPanel, "/img/icons8-home-24.png");
        toggleButtonRouter4.getPanel().setBackground(selectedBackgroundColor);

        toggleButtonRouter4.getLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateBackgroundColor(toggleButtonRouter4.getPanel());// Reset màu cho label của toggleButtonRouter6
            }
        });
    }

    public void setRouter3(JPanel mainPanel, String text) {
        toggleButtonRouter6.set(text, mainPanel, "/img/icons8-products-24.png");

        toggleButtonRouter6.getLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                             updateBackgroundColor(toggleButtonRouter6.getPanel());// Reset màu cho label của toggleButtonRouter6

            }
        });
    }
    
    public void setRouter4(JPanel mainPanel,String text){
            toggleButtonRouter1.set(text, mainPanel, "/img/icons8-user-24.png");

        toggleButtonRouter1.getLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 updateBackgroundColor(toggleButtonRouter1.getPanel());// Reset màu cho label của toggleButtonRouter6

            }
        });
    }
    
    public void setRouter5(JPanel mainPanel,String text){
        toggleButtonRouter2.set(text, mainPanel, "/img/icons8-folder-24.png");

        toggleButtonRouter2.getLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 updateBackgroundColor(toggleButtonRouter2.getPanel());// Reset màu cho label của toggleButtonRouter6

            }
        });
    }
    
    public void setRouter6(JPanel mainPanel,String text){
        toggleButtonRouter3.set(text, mainPanel, "/img/icons8-logistics-24.png");
        toggleButtonRouter3.getLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 updateBackgroundColor(toggleButtonRouter3.getPanel());// Reset màu cho label của toggleButtonRouter6

            }
        });
    }
    public void setRouter7(JPanel mainPanel,String text){
        toggleButtonRouter5.set(text, mainPanel, "/img/icons8-comments-24.png");
        toggleButtonRouter5.getLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 updateBackgroundColor(toggleButtonRouter5.getPanel());// Reset màu cho label của toggleButtonRouter6

            }
        });
    }
    public void setRouter8(JPanel mainPanel,String text){
        toggleButtonRouter7.set(text, mainPanel, "/img/icons8-user-24.png");
        toggleButtonRouter7.getLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 updateBackgroundColor(toggleButtonRouter7.getPanel());// Reset màu cho label của toggleButtonRouter6

            }
        });
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        toggleButtonRouter2 = new com.mycompany.components.Button.toggleButtonRouter();
        toggleButtonRouter1 = new com.mycompany.components.Button.toggleButtonRouter();
        toggleButtonRouter6 = new com.mycompany.components.Button.toggleButtonRouter();
        toggleButtonRouter4 = new com.mycompany.components.Button.toggleButtonRouter();
        toggleButtonRouter3 = new com.mycompany.components.Button.toggleButtonRouter();
        toggleButtonRouter5 = new com.mycompany.components.Button.toggleButtonRouter();
        toggleButtonRouter7 = new com.mycompany.components.Button.toggleButtonRouter();

        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(55, 65, 92));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(toggleButtonRouter2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, -1, 60));
        jPanel1.add(toggleButtonRouter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, 60));
        jPanel1.add(toggleButtonRouter6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, 60));
        jPanel1.add(toggleButtonRouter4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, 60));
        jPanel1.add(toggleButtonRouter3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, -1, 60));
        jPanel1.add(toggleButtonRouter5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, -1, 60));
        jPanel1.add(toggleButtonRouter7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, -1, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private com.mycompany.components.Button.toggleButtonRouter toggleButtonRouter1;
    private com.mycompany.components.Button.toggleButtonRouter toggleButtonRouter2;
    private com.mycompany.components.Button.toggleButtonRouter toggleButtonRouter3;
    private com.mycompany.components.Button.toggleButtonRouter toggleButtonRouter4;
    private com.mycompany.components.Button.toggleButtonRouter toggleButtonRouter5;
    private com.mycompany.components.Button.toggleButtonRouter toggleButtonRouter6;
    private com.mycompany.components.Button.toggleButtonRouter toggleButtonRouter7;
    // End of variables declaration//GEN-END:variables
}
