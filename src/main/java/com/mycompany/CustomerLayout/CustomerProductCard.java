/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.CustomerLayout;

import Pojo.Product;
import java.awt.Color;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author An Ninh
 */
public class CustomerProductCard extends javax.swing.JPanel {

    private boolean isSelect = false;

    /**
     * Creates new form CustomerProductCard
     */
    public CustomerProductCard() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLExit = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nameProduct = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Descripiton = new javax.swing.JTextArea();
        addCart = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nameCate1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        buy = new javax.swing.JLabel();
        price1 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();

        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLExit.setForeground(new java.awt.Color(255, 255, 255));
        jLExit.setText("X");
        jLExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLExitMouseClicked(evt);
            }
        });
        jDialog1.getContentPane().add(jLExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 5, 20, 30));

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoVietpro.jpg"))); // NOI18N
        jLabel2.setOpaque(true);
        jDialog1.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 59, 123, 146));

        jLabel3.setBackground(new java.awt.Color(180, 24, 37));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Chi tiết sản phẩm");
        jLabel3.setOpaque(true);
        jDialog1.getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 41));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tên sản phẩm: ");
        jDialog1.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 59, 90, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mô tả:");
        jDialog1.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 40, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Trạng thái:");
        jDialog1.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 140, 60, 30));

        nameProduct.setText("???");
        jDialog1.getContentPane().add(nameProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 65, 82, -1));

        status.setText("???");
        jDialog1.getContentPane().add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 146, 82, -1));

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        Descripiton.setColumns(20);
        Descripiton.setRows(5);
        Descripiton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Descripiton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(Descripiton);

        jDialog1.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 183, 240, 110));

        addCart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addCart.setText("Thêm vào giỏ ");
        addCart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jDialog1.getContentPane().add(addCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 88, 30));

        jLabel8.setBackground(new java.awt.Color(24, 26, 47));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Mua ngay");
        jLabel8.setOpaque(true);
        jDialog1.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 320, 85, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Danh mục:");
        jDialog1.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 96, 70, 30));

        nameCate1.setText("???");
        jDialog1.getContentPane().add(nameCate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 102, 82, -1));

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        image.setBackground(new java.awt.Color(153, 153, 153));
        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoVietpro.jpg"))); // NOI18N
        image.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        image.setOpaque(true);

        buy.setBackground(new java.awt.Color(51, 51, 51));
        buy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-products-24.png"))); // NOI18N
        buy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buy.setOpaque(true);
        buy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buyMouseClicked(evt);
            }
        });

        price1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        price1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        price1.setText("Giá:");

        name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(price1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price1))
                    .addComponent(buy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 210));
    }// </editor-fold>//GEN-END:initComponents

    private void buyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buyMouseClicked
        // TODO add your handling code here:
if (!jDialog1.isDisplayable()) { // Kiểm tra nếu dialog chưa hiển thị
    jDialog1.setUndecorated(true); // Đặt chế độ không viền
}
        jDialog1.revalidate();
        jDialog1.repaint();
        isSelect = true;

        Descripiton.setEditable(false);
        Descripiton.setBackground(new Color(0, 0, 0, 0));
        Descripiton.setFocusable(false); // Không cho nhận focus
        Descripiton.setBorder(BorderFactory.createEmptyBorder());  // Loại bỏ viền

        jDialog1.setSize(560, 380);
        jDialog1.setResizable(false);
        jDialog1.setLocationRelativeTo(null);
        jDialog1.setVisible(isSelect);

    }//GEN-LAST:event_buyMouseClicked

    private void jLExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLExitMouseClicked
        // TODO add your handling code here:
        jDialog1.dispose(); // dialog1 là đối tượng JDialog mà bạn muốn đóng
    }//GEN-LAST:event_jLExitMouseClicked
    public void setProductData(Product product) {
        nameProduct.setText(product.getName());
        name.setText(product.getName());
        // Đảm bảo rằng mô tả (description) và trạng thái (status) được gán đúng
        Descripiton.setText(product.getDescriptions()); // Mô tả sản phẩm
        status.setText(product.getStatus()); // Trạng thái sản phẩm
        price1.setText(String.format("%.2f", product.getPrice()) + " VND");
        nameCate1.setText(String.valueOf(product.getCategoryTitle()));
        String thumbnailPath = product.getThumbnail();
        if (thumbnailPath != null && !thumbnailPath.isEmpty()) {
            // Nếu đường dẫn hợp lệ, tạo ImageIcon
            File imageFile = new File(thumbnailPath);
            if (imageFile.exists()) {
                ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());
                // Hiển thị hình ảnh hoặc thực hiện các hành động khác
            } else {
                // Nếu không tìm thấy hình ảnh, hiển thị hình ảnh mặc định
                ImageIcon imageIcon = new ImageIcon("path/to/default/image.jpg");
            }
        } else {
            // Nếu thumbnail là null hoặc rỗng, sử dụng hình ảnh mặc định
            ImageIcon imageIcon = new ImageIcon("path/to/default/image.jpg");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Descripiton;
    private javax.swing.JLabel addCart;
    private javax.swing.JLabel buy;
    private javax.swing.JLabel image;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLExit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nameCate1;
    private javax.swing.JLabel nameProduct;
    private javax.swing.JLabel price1;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
