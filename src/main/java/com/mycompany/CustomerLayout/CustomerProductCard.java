/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.CustomerLayout;

import Pojo.Product;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
        jImageDetail = new javax.swing.JLabel();
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

        jImageDetail.setBackground(new java.awt.Color(255, 255, 255));
        jImageDetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jImageDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoVietpro.jpg"))); // NOI18N
        jImageDetail.setOpaque(true);
        jDialog1.getContentPane().add(jImageDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 59, 135, 155));

        jLabel3.setBackground(new java.awt.Color(180, 24, 37));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Chi tiết sản phẩm");
        jLabel3.setOpaque(true);
        jDialog1.getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 41));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tên sản phẩm: ");
        jDialog1.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 59, 90, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mô tả:");
        jDialog1.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 40, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Trạng thái:");
        jDialog1.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 60, 30));

        nameProduct.setText("???");
        jDialog1.getContentPane().add(nameProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 65, 82, -1));

        status.setText("???");
        jDialog1.getContentPane().add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 146, 82, -1));

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        Descripiton.setColumns(20);
        Descripiton.setRows(5);
        Descripiton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Descripiton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(Descripiton);

        jDialog1.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 183, 240, 110));

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
        jDialog1.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 96, 70, 30));

        nameCate1.setText("???");
        jDialog1.getContentPane().add(nameCate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 102, 82, -1));

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        image.setBackground(new java.awt.Color(255, 255, 255));
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
                    .addComponent(price1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
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
                .addGap(0, 8, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 220));
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
        String basePath = "D:\\Onedrive\\Documents\\NetBeansProjects\\VietPro3\\java_shop\\src\\main\\resources\\images";

// Gán thông tin sản phẩm vào các thành phần
        nameProduct.setText(product.getName());
        String ProductName=product.getName();
        if(ProductName.length() > 7){
          ProductName=ProductName.substring(0,7)+ "...";
        }
        name.setText(ProductName);
        Descripiton.setText(product.getDescriptions()); // Mô tả sản phẩm
        status.setText(product.getStatus()); // Trạng thái sản phẩm
        price1.setText(String.format("%.2f", product.getPrice()) + " VND");
        nameCate1.setText(String.valueOf(product.getCategoryTitle()));

// Lấy tên file ảnh
        String imageName = product.getThumbnail();
        if (imageName != null && !imageName.isEmpty()) {
            // Tạo đường dẫn đầy đủ đến file ảnh
            String thumbnailPath = basePath + File.separator + imageName;
            File imageFile = new File(thumbnailPath);
            System.out.println("thumbnailPath: " + thumbnailPath);
            if (imageFile.exists()) {
                image.setIcon(resizeImage(imageFile.getAbsolutePath(), 100, 150));
                jImageDetail.setIcon(resizeImage(imageFile.getAbsolutePath(), 100, 150));
            } else {
                image.setIcon(resizeImage(basePath + File.separator + "logoVietpro.jpg", 100, 150));
                jImageDetail.setIcon(resizeImage(basePath + File.separator + "logoVietpro.jpg", 100, 150));

            }
        } else {
            // Nếu `thumbnail` là null hoặc rỗng, sử dụng ảnh mặc định
            ImageIcon defaultIcon = new ImageIcon(basePath + File.separator + "logoVietpro.jpg");
            image.setIcon(defaultIcon);
            jImageDetail.setIcon(resizeImage(basePath + File.separator + "logoVietpro.jpg", 100, 150));

        }
    }

    private ImageIcon resizeImage(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public void setImageForLabel(JLabel label, String basePath, String imageName) {
        // Tạo đường dẫn đầy đủ
        String fullPath = basePath + File.separator + imageName;

        // Kiểm tra xem file ảnh có tồn tại hay không
        File imageFile = new File(fullPath);
        if (imageFile.exists()) {
            // Nếu ảnh tồn tại, tạo ImageIcon và gắn vào JLabel
            ImageIcon imageIcon = new ImageIcon(fullPath);
            label.setIcon(imageIcon);
        } else {
            // Nếu ảnh không tồn tại, gắn ảnh mặc định
            ImageIcon defaultIcon = new ImageIcon(basePath + File.separator + "logoVietpro.jpg");
            label.setIcon(defaultIcon);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Descripiton;
    private javax.swing.JLabel addCart;
    private javax.swing.JLabel buy;
    private javax.swing.JLabel image;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jImageDetail;
    private javax.swing.JLabel jLExit;
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
