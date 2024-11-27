/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */ 
package com.mycompany.vietpro;

import Interface.login;
import Process.user;
import com.mycompany.components.Home;
import com.mycompany.components.ManagerCategory;
import com.mycompany.components.ManagerComments;
import com.mycompany.components.ManagerOrders;
import com.mycompany.components.ManagerProducts;
import com.mycompany.components.ManagerUser;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author An Ninh
 */
public final class Homepage extends javax.swing.JFrame {

    private static user us;
    /**
     */
<<<<<<< HEAD
    private int posX = 0, posY = 0;

    public Homepage(user us) {
        this.us = us;
        setUndecorated(true);
        initComponents();
        setResizable(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                posX = e.getX();
                posY = e.getY();
            }
        });
=======
      private int posX = 0, posY = 0;   
       public Homepage(user us) {
           this.us = us;
           System.out.println("com.mycompany.vietpro.Homepage.<init>()"+ us.getEmail());
            setUndecorated(true);
            initComponents();
            setResizable(false);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    posX = e.getX();
                    posY = e.getY();
                }
            });
>>>>>>> b67c72cc9629bb942ca50c74ec2cf6b0746cf851

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Thay đổi vị trí cửa sổ dựa trên tọa độ chuột
                setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
            }
        });
        this.setLocationRelativeTo(null);
        setUpPanels();
    }
<<<<<<< HEAD
=======
            public static user getUser(){
                return Homepage.us;
            }
>>>>>>> b67c72cc9629bb942ca50c74ec2cf6b0746cf851

    public static user getUser() {
        return Homepage.us;
    }

    public void setUpPanels() {
        CardLayout cardlayout = new CardLayout();
        jPanel2.setLayout(cardlayout);
        ManagerProducts managerProducts = new ManagerProducts();
        managerProducts.setPopUp(this);
        jPanel2.add(new Home(), "Trang chủ");
        jPanel2.add(managerProducts, "Sản phẩm");
        jPanel2.add(new ManagerUser(), "Người dùng");
        jPanel2.add(new ManagerCategory(), "Danh mục");
        jPanel2.add(new ManagerOrders(), "Đơn hàng");
        jPanel2.add(new ManagerComments(), "Bình luận");

        cardlayout.show(jPanel2, "Trang chủ");
        //thiết lập điều hướng
        navbar2.setRouter1(jPanel2, "Trang chủ");
        navbar2.setRouter3(jPanel2, "Sản phẩm");
        navbar2.setRouter4(jPanel2, "Người dùng");
        navbar2.setRouter5(jPanel2, "Danh mục");
        navbar2.setRouter6(jPanel2, "Đơn hàng");
        navbar2.setRouter7(jPanel2, "Bình luận");

    }

    // Method to switch panels
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        header1 = new com.mycompany.components.Header();
        navbar2 = new com.mycompany.components.Navbar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1065, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 1020, 538));
        jPanel1.add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, -1));
        jPanel1.add(navbar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
<<<<<<< HEAD
            Homepage homepage = new Homepage(us);
            homepage.setVisible(true);
=======
            Homepage homepage;
                 if(us != null){
                    homepage=new Homepage(us);
                    homepage.setVisible(true);
                 }else{
                     new login().setVisible(true);
                 }
>>>>>>> b67c72cc9629bb942ca50c74ec2cf6b0746cf851
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.components.Header header1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.mycompany.components.Navbar navbar2;
    // End of variables declaration//GEN-END:variables

}
