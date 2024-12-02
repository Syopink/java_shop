/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components.util;

import Database.ActionOrders;
import Pojo.Customer;
import Pojo.Order;
import Process.customers;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author An Ninh
 */
public class rowOrder extends javax.swing.JPanel {
  private List<rowsUser> rowsList = new ArrayList<>();  // Danh sách các rowsCustomer

    private int index;

    private Order order;
    private ActionOrders Ao;

    /**
     * Creates new form rowOrder
     */
    public rowOrder() {
        initComponents();
    }
    
      public rowOrder(int index, Order order, ActionOrders Ao) {
        this.index = index;
        this.Ao = Ao;
        this.order = order;
        initComponents();
        loadData();
    }
      private void loadData() {
        // Hiển thị số thứ tự
        setIndex(index); // Gọi setIndex để cập nhật id
        setOrderData(order);
    }
      
 private void setOrderData(Order order) {
        id.setText(String.valueOf(order.getIdOrder())); // Hiển thị ID đơn hàng
        name.setText(order.getName()); // Hiển thị tên người mua
        email.setText(order.getEmail()); // Hiển thị email
        jLabel6.setText(order.getAddress()); // Hiển thị địa chỉ
        jLabel7.setText(order.getPhone()); // Hiển thị số điện thoại
        jLabel2.setText(order.getItem()); // Hiển thị sản phẩm
        jLabel8.setText(order.getFormattedCreatedAt()); // Hiển thị ngày mua (Timestamp)
    }
       
         public void setIndex(int index) {
        this.index = index;
        // Cập nhật lại dữ liệu hiển thị số thứ tự trong giao diện
        id.setText(String.valueOf(index));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(952, 43));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name.setText("Người mua");
        jPanel6.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 100, 42));

        email.setText("Email");
        jPanel6.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 90, 42));

        jLabel6.setText("Địa chỉ");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 130, 40));

        jLabel7.setText("Số điện thoại");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 110, 40));

        id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        id.setText("ID");
        jPanel6.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 43));

        jLabel8.setText("Ngày mua");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 120, 40));

        jLabel2.setText("Sản phẩm");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 110, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel email;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel name;
    // End of variables declaration//GEN-END:variables
}
