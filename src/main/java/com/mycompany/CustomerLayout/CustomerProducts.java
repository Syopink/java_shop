/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.CustomerLayout;

import Database.Action;
import Pojo.Customer;
import Pojo.Product;
import Process.product;
import Process.user;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author An Ninh
 */
public class CustomerProducts extends javax.swing.JPanel {

    private List<Product> productList = new ArrayList<>(); // Danh sách sản phẩm
    private product pr = new product();
    Action action = new Action();
    private Customer customer;
    private user us;

    /**
     * Creates new form CustomerProducts
     */
    public CustomerProducts(user us) {
            this.us = us;  // Lưu đối tượng user vào biến trong lớp
        initComponents();
            loadComboBoxData(); // Đổ dữ liệu vào các JComboBox
        loadProducts();
    }

    private void loadProducts() {
        // Xóa nội dung cũ nếu có
        jScrollPane1.getViewport().removeAll();

        // Tạo JPanel để chứa các hàng sản phẩm
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 5, 10, 10));
        // Tạo đối tượng product và gọi phương thức getAllProducts
        productList = pr.getAllProducts(); // Lấy tất cả sản phẩm từ cơ sở dữ liệu

        // Tạo các hàng và thêm vào giao diện
        for (Product product : productList) {
            CustomerProductCard productCard = new CustomerProductCard(us);
            productCard.setProductData(product); // Truyền dữ liệu sản phẩm vào card
            listPanel.add(productCard);
        }

        // Đặt JPanel vào JScrollPane
        jScrollPane1.setViewportView(listPanel);

        // Làm mới giao diện
        listPanel.revalidate();
        listPanel.repaint();
    }
    
    
    private void loadComboBoxData() {

    try {
        // Lấy danh mục sản phẩm
        List<String> categories = action.getAllCategories();
        jBoxCate.removeAllItems();
        jBoxCate.addItem("Tất cả"); // Thêm mục "Tất cả" để không lọc theo danh mục
        for (String category : categories) {
            jBoxCate.addItem(category);
        }

        // Lấy trạng thái sản phẩm
        List<String> statuses = action.getAllStatuses();
        jBoxStatus.removeAllItems();
        jBoxStatus.addItem("Tất cả"); // Thêm mục "Tất cả" để không lọc theo danh mục
        for (String status : statuses) {
            jBoxStatus.addItem(status);
        }

        // Lấy danh sách giá (giá trị cố định hoặc từ cơ sở dữ liệu)
        List<String> priceRanges = action.getPriceRanges();
        jBoxRangePrice.removeAllItems();
        jBoxRangePrice.addItem("Tất cả"); // Thêm mục "Tất cả" để không lọc theo trạng thái
        for (String range : priceRanges) {
            jBoxRangePrice.addItem(range);
        }

    } catch (SQLException ex) {
        ex.printStackTrace(); // Xử lý lỗi kết nối
    }
}
private String normalizeFilter(String filterValue) {
    return (filterValue == null || filterValue.trim().isEmpty() || filterValue.equals("Tất cả")) ? "" : filterValue;
}
    
    private List<Product> filterProducts() {
    String selectedCategory = normalizeFilter((String) jBoxCate.getSelectedItem());
String selectedStatus = normalizeFilter((String) jBoxStatus.getSelectedItem());
String selectedPriceRange = normalizeFilter((String) jBoxRangePrice.getSelectedItem());
    String searchName = jtFindName.getText().trim();

    // Lọc sản phẩm dựa trên các bộ lọc
    Action action = new Action();
    try {
                return action.getFilteredProducts(selectedCategory, selectedStatus, selectedPriceRange, searchName);
 } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return new ArrayList<>(); // Trả về danh sách rỗng nếu lỗi
}
    

    private void displayFilteredProducts(List<Product> filteredProducts) {
    // Xóa nội dung cũ nếu có
    jScrollPane1.getViewport().removeAll();

    // Tạo JPanel để chứa các hàng sản phẩm đã lọc
    JPanel listPanel = new JPanel();
    listPanel.setLayout(new GridLayout(0, 5, 10, 10)); // 5 sản phẩm mỗi hàng, khoảng cách 10px giữa các ô

    // Tạo các thẻ sản phẩm cho các sản phẩm đã lọc và thêm vào giao diện
    for (Product product : filteredProducts) {
        // Kiểm tra nếu sản phẩm đã có, nếu chưa thì tạo mới

        CustomerProductCard productCard = new CustomerProductCard(us);
        productCard.setProductData(product); // Truyền dữ liệu sản phẩm vào card
        listPanel.add(productCard);
    }

    // Đặt JPanel vào JScrollPane
    jScrollPane1.setViewportView(listPanel);

    // Làm mới giao diện
    listPanel.revalidate();
    listPanel.repaint();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jBoxCate = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBoxStatus = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jBoxRangePrice = new javax.swing.JComboBox<>();
        jSeacrhProduct = new javax.swing.JLabel();
        jFilterProduct = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtFindName = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 700));

        jBoxCate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Danh mục:");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Trạng thái:");

        jBoxStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Giá:");

        jBoxRangePrice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSeacrhProduct.setBackground(new java.awt.Color(51, 51, 51));
        jSeacrhProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSeacrhProduct.setForeground(new java.awt.Color(255, 255, 255));
        jSeacrhProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jSeacrhProduct.setText("Tìm kiếm");
        jSeacrhProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSeacrhProduct.setOpaque(true);

        jFilterProduct.setBackground(new java.awt.Color(51, 51, 51));
        jFilterProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFilterProduct.setForeground(new java.awt.Color(255, 255, 255));
        jFilterProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jFilterProduct.setText("Lọc");
        jFilterProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jFilterProduct.setOpaque(true);
        jFilterProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFilterProductMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tên :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtFindName, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBoxRangePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jBoxCate, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFilterProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeacrhProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(330, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBoxStatus)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBoxRangePrice)
                    .addComponent(jFilterProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBoxCate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtFindName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeacrhProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jFilterProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFilterProductMouseClicked
        // TODO add your handling code here:
        List<Product> filteredProducts = filterProducts();
    displayFilteredProducts(filteredProducts); // Hiển thị sản phẩm đã lọc
    }//GEN-LAST:event_jFilterProductMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jBoxCate;
    private javax.swing.JComboBox<String> jBoxRangePrice;
    private javax.swing.JComboBox<String> jBoxStatus;
    private javax.swing.JLabel jFilterProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jSeacrhProduct;
    private javax.swing.JTextField jtFindName;
    // End of variables declaration//GEN-END:variables
}
