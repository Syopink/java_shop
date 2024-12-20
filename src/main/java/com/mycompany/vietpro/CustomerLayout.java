/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.vietpro;


import Process.user;
import com.mycompany.CustomerLayout.CustomeCart;
import com.mycompany.CustomerLayout.CustomerOrder;
import com.mycompany.CustomerLayout.CustomerProducts;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author An Ninh
 */
public class CustomerLayout extends javax.swing.JFrame {
    private static user us;

    /**
     * Creates new form CustomerLayout
     */
    private int posX = 0, posY = 0;
    
    public CustomerLayout(user us) {
        this.us = us;
        int customerId = Integer.parseInt(us.getIdCustomer());  // Convert String to int
         setUndecorated(true);
        initComponents();
        setUpPanel();
        setResizable(false);
         addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                posX = e.getX();
                posY = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Thay đổi vị trí cửa sổ dựa trên tọa độ chuột
                setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
            }
        });
        this.setLocationRelativeTo(null);
    }
    public static user getUser() {
        return CustomerLayout.us;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmianPanel = new javax.swing.JPanel();
        customerNavbar1 = new com.mycompany.CustomerLayout.CustomerNavbar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1248, 750));

        javax.swing.GroupLayout jmianPanelLayout = new javax.swing.GroupLayout(jmianPanel);
        jmianPanel.setLayout(jmianPanelLayout);
        jmianPanelLayout.setHorizontalGroup(
            jmianPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1093, Short.MAX_VALUE)
        );
        jmianPanelLayout.setVerticalGroup(
            jmianPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customerNavbar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1254, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jmianPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(customerNavbar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jmianPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    void setUpPanel(){
        CardLayout cardlayout = new CardLayout();
        jmianPanel.setLayout(cardlayout);
        jmianPanel.add(new CustomerProducts(us),"Trang chủ");
        jmianPanel.add(new CustomeCart(Integer.parseInt(us.getIdCustomer()),us),"Giỏ hàng");
        jmianPanel.add(new CustomerOrder(Integer.parseInt(us.getIdCustomer())),"Đơn hàng");
 
        customerNavbar1.Router1( "Trang chủ",jmianPanel);
        customerNavbar1.Router2( "Giỏ hàng",jmianPanel);
        customerNavbar1.Router3( "Đơn hàng", jmianPanel);
    }
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
            java.util.logging.Logger.getLogger(CustomerLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerLayout(us).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.CustomerLayout.CustomerNavbar customerNavbar1;
    private javax.swing.JPanel jmianPanel;
    // End of variables declaration//GEN-END:variables
}
