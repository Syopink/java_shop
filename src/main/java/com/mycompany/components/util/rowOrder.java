/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components.util;

import Database.ActionOrders;
import Pojo.Customer;
import Pojo.Order;
import Pojo.OrderItem;
import Process.customers;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

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
    private String isSelectOption;

    int changeApprove(String isApproved) {
        if ("Tạm dừng".equals(isApproved)) {
            return 0;
        } else if ("Duyệt".equals(isApproved)) {
            return 1;
        } else {
            return 2;
        }
    }

    private void setOrderData(Order order) {
        id.setText(String.valueOf(order.getIdOrder())); // Hiển thị ID đơn hàng
        name.setText(order.getName()); // Hiển thị tên người mua
        email.setText(order.getEmail()); // Hiển thị email
        email.setToolTipText(order.getEmail()); // Tooltip cho Email        jaddress.setText(order.getAddress()); // Hiển thị địa chỉ
        jPhone.setText(order.getPhone()); // Hiển thị số điện thoại
        jCreate.setText(order.getFormattedCreatedAt()); // Hiển thị ngày mua (Timestamp)
        jaddress.setText(order.getAddress()); // Hiển thị địa chỉ
        jaddress.setToolTipText(order.getAddress()); // Tooltip cho Địa chỉ
        StringBuilder productNames = new StringBuilder();
        List<OrderItem> items = order.getOrderItems();
        if (items == null) {
            items = new ArrayList<>(); // Đảm bảo không null
        }
        if (items != null && !items.isEmpty()) {
            for (OrderItem item : items) {
                System.out.println("Sản phẩm: " + item.getProductName());
                productNames.append(item.getProductName()).append(", ");
            }
            System.out.println("Danh sách sản phẩm trong order.getOrderItems(): " + items.size());

            // Loại bỏ dấu phẩy cuối cùng nếu có
            if (productNames.length() > 0) {
                productNames.setLength(productNames.length() - 2);  // Xóa dấu phẩy cuối
                jNameProduct.setToolTipText(productNames.toString()); // Gắn toàn bộ tên sản phẩm vào tooltip
            } else {
                productNames.append("Không có sản phẩm");
                jNameProduct.setToolTipText("Không có sản phẩm");
            }
            jNameProduct.setText(productNames.toString()); // Gắn chuỗi tên sản phẩm
            jNameProduct.setToolTipText(productNames.toString()); // Thêm tooltip để hiển thị toàn bộ tên
            System.out.println("order.getItem()" + order.getOrderItems());
            if (order.getStatus() == 0) {
                jisApprove.setText("Chờ xét duyệt");
                jisApprove.setBackground(Color.orange);
            } else if (order.getStatus() == 1) {
                jisApprove.setText("Chờ vận chuyển");
                jisApprove.setBackground(Color.green);
            } else {
                jisApprove.setText("Đơn hàng bị hủy");
                jisApprove.setBackground(Color.red);
            }

            JPopupMenu popupMenu = new JPopupMenu();

            // Thêm các mục lựa chọn vào PopupMenu
            JMenuItem option1 = new JMenuItem("Tạm dừng");
            JMenuItem option2 = new JMenuItem("Duyệt");
            JMenuItem option3 = new JMenuItem("Hủy đơn");

            // Thêm các item vào menu
            popupMenu.add(option1);
            popupMenu.add(option2);
            popupMenu.add(option3);

            // Thêm ActionListener cho các item
            option1.addActionListener(e -> handleSelection("Tạm dừng"));
            option2.addActionListener(e -> handleSelection("Duyệt"));
            option3.addActionListener(e -> handleSelection("Hủy đơn"));

            jisApprove.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) { // Chỉ hiển thị menu khi nhấp chuột phải
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    showPopupMenu(e);
                }

                private void showPopupMenu(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            });
        }
    }

    public void setIndex(int index) {
        this.index = index;
        id.setText(String.valueOf(index));
    }

    private void updateApprovalStatus(int status) {
        if (status == 0) {
            jisApprove.setText("Chờ xét duyệt");
            jisApprove.setBackground(Color.orange);

        } else if (status == 1) {
            jisApprove.setText("Chờ vận chuyển");
            jisApprove.setBackground(Color.green);
        } else {
            jisApprove.setText("Đơn hàng bị hủy");
            jisApprove.setBackground(Color.red);
        }
    }

    private void handleSelection(String selectedOption) {
        isSelectOption = selectedOption;
        int newApprovalStatus = changeApprove(isSelectOption);

        // Cập nhật trạng thái đơn hàng
        order.setStatus(newApprovalStatus);

        // Cập nhật cơ sở dữ liệu
        ActionOrders aco = new ActionOrders();
        aco.updateApprove(newApprovalStatus, order.getIdOrder());

        // Cập nhật giao diện người dùng
        updateApprovalStatus(newApprovalStatus);

        // Thông báo cho người dùng
        JOptionPane.showMessageDialog(this, "Đã chọn: " + selectedOption);
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
        jaddress = new javax.swing.JLabel();
        jPhone = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jCreate = new javax.swing.JLabel();
        jNameProduct = new javax.swing.JLabel();
        jisApprove = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(952, 43));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name.setText("Người mua");
        jPanel6.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 100, 42));

        email.setText("Email");
        jPanel6.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 90, 42));

        jaddress.setText("Địa chỉ");
        jPanel6.add(jaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 130, 40));

        jPhone.setText("Số điện thoại");
        jPanel6.add(jPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 110, 40));

        id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        id.setText("ID");
        jPanel6.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 43));

        jCreate.setText("Ngày mua");
        jPanel6.add(jCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 120, 40));

        jNameProduct.setText("Sản phẩm");
        jPanel6.add(jNameProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 110, 40));

        jisApprove.setBackground(new java.awt.Color(153, 153, 153));
        jisApprove.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jisApprove.setForeground(new java.awt.Color(255, 255, 255));
        jisApprove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jisApprove.setText("chờ xét");
        jisApprove.setOpaque(true);
        jPanel6.add(jisApprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 100, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel email;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jCreate;
    private javax.swing.JLabel jNameProduct;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel jPhone;
    private javax.swing.JLabel jaddress;
    private javax.swing.JLabel jisApprove;
    private javax.swing.JLabel name;
    // End of variables declaration//GEN-END:variables
}
