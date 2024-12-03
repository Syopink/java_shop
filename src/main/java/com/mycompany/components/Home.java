/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components;

import Database.Action;
import java.awt.Color;
import java.sql.SQLException;

/**
 *
 * @author An Ninh
 */
public class Home extends javax.swing.JPanel {
    Action ac = new Action();
    /**
     * Creates new form Home
     */
    public Home() throws SQLException {
        initComponents();
        setCardDashBoard();
    }

     public void setCardDashBoard() throws SQLException{
         
         int totalProducts = ac.countProducts();
         int totalCustomers = ac.countCustomers();
         double totalOrders = ac.getTotalRevenueForAllOrders();
        cardDashBoard1.setCard("Doanh thu",String.valueOf(totalOrders)+"VND", "/img/icons8-revenue-50.png",Color.green);
        cardDashBoard2.setCard("Người dùng",String.valueOf(totalCustomers), "/img/icons8-category-48.png",Color.red);
        cardDashBoard3.setCard("Sản phẩm",String.valueOf(totalProducts), "/img/icons8-products-50.png",Color.yellow);
        cardDashBoard4.setCard("Comments","550601", "/img/icons8-comments-50.png",Color.BLUE);
    }
     
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardDashBoard1 = new com.mycompany.components.Card.CardDashBoard();
        cardDashBoard2 = new com.mycompany.components.Card.CardDashBoard();
        cardDashBoard3 = new com.mycompany.components.Card.CardDashBoard();
        cardDashBoard4 = new com.mycompany.components.Card.CardDashBoard();

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cardDashBoard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cardDashBoard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cardDashBoard3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cardDashBoard4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardDashBoard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardDashBoard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardDashBoard3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardDashBoard4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 305, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.components.Card.CardDashBoard cardDashBoard1;
    private com.mycompany.components.Card.CardDashBoard cardDashBoard2;
    private com.mycompany.components.Card.CardDashBoard cardDashBoard3;
    private com.mycompany.components.Card.CardDashBoard cardDashBoard4;
    // End of variables declaration//GEN-END:variables
}
