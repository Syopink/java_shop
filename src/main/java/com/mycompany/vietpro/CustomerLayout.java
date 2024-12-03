/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.vietpro;

import com.mycompany.CustomerLayout.CustomeCart;
import com.mycompany.CustomerLayout.CustomerProducts;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author An Ninh
 */
public class CustomerLayout extends javax.swing.JFrame {

    /**
     * Creates new form CustomerLayout
     */
    private int posX = 0, posY = 0;
    
    public CustomerLayout() {
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jmianPanelLayout = new javax.swing.GroupLayout(jmianPanel);
        jmianPanel.setLayout(jmianPanelLayout);
        jmianPanelLayout.setHorizontalGroup(
            jmianPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 811, Short.MAX_VALUE)
        );
        jmianPanelLayout.setVerticalGroup(
            jmianPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        getContentPane().add(jmianPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 71, -1, 390));
        getContentPane().add(customerNavbar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    void setUpPanel(){
        CardLayout cardlayout = new CardLayout();
        jmianPanel.setLayout(cardlayout);
        jmianPanel.add(new CustomerProducts(),"Trang chủ");
        jmianPanel.add(new CustomeCart(),"Giỏ hàng");
        cardlayout.show(jmianPanel,"Trang chủ");
        customerNavbar1.Router1(jmianPanel, "Trang chủ");
        customerNavbar1.Router2(jmianPanel, "Giỏ hàng");
        
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
                new CustomerLayout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.CustomerLayout.CustomerNavbar customerNavbar1;
    private javax.swing.JPanel jmianPanel;
    // End of variables declaration//GEN-END:variables
}
