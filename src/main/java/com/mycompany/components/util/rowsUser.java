/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components.util;

import Pojo.Customer;
import Process.customers; // DAL
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author An Ninh
 */
public class rowsUser extends javax.swing.JPanel {

    private List<rowsUser> rowsList = new ArrayList<>();  // Danh sách các rowsCustomer

    private int index;
    private boolean selected;   // Represents whether this row is selected or not

    private Customer customer;
    private customers cs;

    /**
     * Creates new form rows
     */
    public rowsUser() {
        initComponents();
    }
      public void setSelected(boolean selected) {
        this.selected = selected;
    }

//       public rowsCustomer(Customer customer, customers cs) {
//        this.customer = customer;
//        this.cs = cs;
//        initComponents();
//        setCustomerData(customer); // Hiển thị dữ liệu khách hàng lên giao diện
//        addEventListeners(); // Thêm các sự kiện cho các nút
//    }
    public rowsUser(int index, Customer customer, customers cs) {
        this.index = index;
        this.cs = cs;
        this.customer = customer;
        initComponents();
        loadData();
        addEventListeners(); // Thêm các sự kiện cho các nút

    }

    public boolean isSelected() {
        return jRChose.isSelected();
    }
    
    public void setRChoseSelected(boolean selected) {
    jRChose.setSelected(selected);  // Setter để thay đổi trạng thái chọn của jRChose
}

    public Customer getCustomer() {
        return customer; // Trả về đối tượng Customer của hàng này
    }

    private void loadData() {
        // Hiển thị số thứ tự
        setIndex(index); // Gọi setIndex để cập nhật id
        setCustomerData(customer);
    }

    private void setCustomerData(Customer customer) {
        id.setText(String.valueOf(customer.getId())); // ID
        name.setText(customer.getFullName()); // Tên đầy đủ
        email.setText(customer.getEmail()); // Email
    }

    private void jbtnUpdateActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        String updatedName = JOptionPane.showInputDialog(this, "Nhập tên mới:", customer.getFullName());
        String updatedEmail = JOptionPane.showInputDialog(this, "Nhập email mới:", customer.getEmail());

        if (updatedName != null && !updatedName.isEmpty() && updatedEmail != null && !updatedEmail.isEmpty()) {
            customer.setFullName(updatedName);
            customer.setEmail(updatedEmail);

            boolean isUpdated = cs.updateCustomer(customer); // Gọi phương thức cập nhật từ lớp `customers`
            if (isUpdated) {
                setCustomerData(customer); // Cập nhật lại dữ liệu hiển thị
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        }
    }

    // Xử lý sự kiện khi nút Delete được nhấn
    private void jbtnDeleteActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn xóa người dùng này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean isDeleted = cs.deleteCustomer(customer.getId()); // Gọi phương thức xóa từ lớp `customers`
            if (isDeleted) {
                this.setVisible(false); // Ẩn hàng này khỏi giao diện
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                updateIndexes(rowsList);  // Cập nhật lại số thứ tự sau khi xóa
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        }
    }

    private void addEventListeners() {
        jbtnUpdate.addActionListener(evt -> {
            try {
                jbtnUpdateActionPerformed(evt);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật: " + e.getMessage());
            }
        });

        jbtnDelete.addActionListener(evt -> {
            try {
                jbtnDeleteActionPerformed(evt);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + e.getMessage());
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
        name = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        jbtnUpdate = new javax.swing.JToggleButton();
        jbtnDelete = new javax.swing.JToggleButton();
        jRChose = new javax.swing.JRadioButton();
        id = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(900, 38));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name.setText("jLabel2");
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 135, 40));

        email.setText("jLabel3");
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 0, 170, 40));

        jbtnUpdate.setBackground(new java.awt.Color(0, 255, 255));
        jbtnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-pencil-24.png"))); // NOI18N
        jbtnUpdate.setBorder(null);
        jbtnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnUpdate.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel1.add(jbtnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 40, 40));
        jbtnUpdate.getAccessibleContext().setAccessibleDescription("");

        jbtnDelete.setBackground(new java.awt.Color(255, 0, 0));
        jbtnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-trash-24.png"))); // NOI18N
        jbtnDelete.setToolTipText("");
        jbtnDelete.setBorder(null);
        jbtnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jbtnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, 40, 40));

        jRChose.setForeground(new java.awt.Color(0, 204, 255));
        jRChose.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel1.add(jRChose, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, -1, 40));

        id.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        id.setText("        jLabel1");
        id.setPreferredSize(new java.awt.Dimension(61, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
public static void updateIndexes(List<rowsUser> rowsList) {
        int count = 1;
        for (rowsUser row : rowsList) {
            row.setIndex(count);  // Cập nhật lại index cho từng row
            row.loadData();  // Hiển thị lại dữ liệu khi index thay đổi
            count++;
        }
    }

    public void setIndex(int index) {
        this.index = index;
        // Cập nhật lại dữ liệu hiển thị số thứ tự trong giao diện
        id.setText(String.valueOf(index));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel email;
    private javax.swing.JLabel id;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRChose;
    private javax.swing.JToggleButton jbtnDelete;
    private javax.swing.JToggleButton jbtnUpdate;
    private javax.swing.JLabel name;
    // End of variables declaration//GEN-END:variables
}
