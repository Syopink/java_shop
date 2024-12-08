/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.CustomerLayout;

import Database.ActionCartProduct;
import Database.ActionOrders;
import Pojo.CartProduct;
import Pojo.OrderItem;
import Process.product;
import Process.user;
import com.mycompany.components.util.pathImg;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author An Ninh
 */
public class CustomerCardCart extends javax.swing.JPanel {

    /**
     * Creates new form CustomerCardCart
     */
    private CartProduct cartProduct;
    private ActionCartProduct accp;
    private int enteredValue;
    private String idCartProduct;
    private String idProductss;
    private user us;
    private String imagePath = new pathImg().path(); // Đường dẫn tương đối

    ActionOrders Ao = new ActionOrders();
    public CustomerCardCart(CartProduct cartProduct,ActionCartProduct accp,user us) {
        initComponents();
        this.cartProduct=cartProduct;
        this.accp=accp;
        this.us=us;
        remove();
        changeQuantity();
        loadData();
    }
    
    public CartProduct getCartProduct(){
        return this.cartProduct;
    }

    public boolean isSelected() {
        return jRChose.isSelected();
    }
     public void setRChoseSelected(boolean selected) {
        jRChose.setSelected(selected);  // Setter để thay đổi trạng thái chọn của jRChose
    }
     private void loadData() {
        setCartProductData(cartProduct);
    }
    private ImageIcon resizeImage(String imagePath) {
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            return new ImageIcon(imagePath + "default.png"); // Ảnh mặc định khi lỗi
        }
    }
     
     public void setCartProductData(CartProduct cartProduct){
         nameProduct.setText(cartProduct.getNameProduct());
         Price.setText(String.valueOf(cartProduct.getTotalPrice()));
         statusLabel.setText(cartProduct.getCategory());
         jTextField1.setText(String.valueOf(cartProduct.getQuantity()));
         statusLabel.setText(cartProduct.getStatus());
         Cate1.setText(cartProduct.getCategory());
         String imageFile = imagePath + (cartProduct.getThumbnail()!= null ? cartProduct.getThumbnail(): "default.png");
        image.setIcon(resizeImage(imageFile));
         this.idCartProduct=cartProduct.getIdCartProduct();
         this.idProductss=cartProduct.getIdProduct();
     }
     
    public void changeQuantity() {
    jTextField1.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            // Kiểm tra nếu người dùng nhấn Enter (phím mã 10)
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    try {
        enteredValue = Integer.valueOf(jTextField1.getText());
        System.out.println("Giá trị nhập vào: " + enteredValue);

        accp.updateProductQuantity(idCartProduct, enteredValue);

        jTextField1.setText(String.valueOf(enteredValue));
        BigDecimal unitPrice = cartProduct.getTotalPrice(); // Lấy giá của sản phẩm
        BigDecimal updatedTotalPrice = unitPrice.multiply(BigDecimal.valueOf(enteredValue)); // Tính tổng giá

            Price.setText(String.valueOf(updatedTotalPrice));

    } catch (NumberFormatException ex) {
        // Hiển thị lỗi nếu người dùng nhập không phải số
        JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ!");
    }
}
        }
    });
}

     
      public void remove(){
         
     remove.addMouseListener(new MouseAdapter() {
         
    @Override
    public void mouseClicked(MouseEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(
            null, 
            "Bạn có chắc chắn muốn xóa không?", 
            "Xác nhận", 
            JOptionPane.YES_NO_OPTION
        );
JOptionPane.showMessageDialog(CustomerCardCart.this, "Xóa thành công!");
        if (confirmed == JOptionPane.YES_OPTION) {
            String confirmDelete= accp.deleteProductFromCart(idCartProduct);
            if("Thành công".equals(confirmDelete)){
                CustomerCardCart.this.setVisible(false);
                JOptionPane.showMessageDialog(CustomerCardCart.this, "Xóa thành công!");
                
            }
        }
    }
});
     }
     
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nameProduct = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Price = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        remove = new javax.swing.JLabel();
        Buy = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jRChose = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Cate1 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        image.setBackground(new java.awt.Color(153, 153, 153));
        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoVietpro.jpg"))); // NOI18N
        image.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên sản phẩm  :");

        nameProduct.setText("???");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Giá                   :");

        Price.setText("???");

        statusLabel.setText("???");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Danh mục  :");

        remove.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        remove.setForeground(new java.awt.Color(255, 51, 51));
        remove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        remove.setText("Xóa");
        remove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51)));
        remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Buy.setBackground(new java.awt.Color(55, 65, 92));
        Buy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Buy.setForeground(new java.awt.Color(255, 255, 255));
        Buy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Buy.setText("Mua");
        Buy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buy.setOpaque(true);
        Buy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuyMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setToolTipText("");
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        jRChose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRChoseActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Số lượng :");

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Trạng thái :");

        Cate1.setText("???");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Cate1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Buy, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRChose))
                    .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cate1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(Buy, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jRChose)))
                        .addContainerGap(28, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(image, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRChoseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRChoseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRChoseActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void BuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuyMouseClicked
        // TODO add your handling code here:
        String productName = nameProduct.getText();
        String productStatus = cartProduct.getStatus();
        BigDecimal productPrice = (cartProduct.getTotalPrice());
        int id = (Integer.valueOf(idProductss));
    // Kiểm tra xem sản phẩm có sẵn để mua hay không
    if (productStatus.equalsIgnoreCase("Còn hàng")) {
        // Xác nhận hành động mua
        int option = JOptionPane.showConfirmDialog(this, 
            "Bạn có muốn mua " + productName + " với giá " + productPrice + " VND?", 
            "Xác nhận mua hàng", 
            JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // Chuẩn bị dữ liệu cho phương thức placeOrder
            String idCustomer = us.getIdCustomer(); // Giả sử user `us` chứa thông tin khách hàng đang đăng nhập
            String customerName = us.getFullName(); // Tên khách hàng
            String customerEmail = us.getEmail(); // Email khách hàng
            String customerPhone = us.getNumberOfPhone(); // Số điện thoại khách hàng
            String customerAddress = us.getAddress(); // Địa chỉ khách hàng

            // Tạo danh sách OrderItem (giả định sản phẩm hiện tại là duy nhất trong đơn hàng)
            List<OrderItem> cartItems = new ArrayList<>();
            OrderItem item = new OrderItem(id,productName, 1,productPrice); // Số lượng mặc định là 1
            cartItems.add(item);

            // Gọi phương thức placeOrder
            boolean orderSuccess = Ao.placeOrder(Integer.parseInt(idCustomer), cartItems, customerName, customerEmail, customerPhone, customerAddress);

            // Hiển thị kết quả
            if (orderSuccess) {
                accp.deleteProductFromCart(idCartProduct);
                this.setVisible(false);
                JOptionPane.showMessageDialog(this, "Mua hàng thành công! Đơn hàng đã được đặt.");
                        cartItems.clear(); // Reset the cart
            } else {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi trong quá trình đặt hàng.");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Xin lỗi, sản phẩm này không có sẵn để mua.");
    }
    }//GEN-LAST:event_BuyMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Buy;
    private javax.swing.JLabel Cate1;
    private javax.swing.JLabel Price;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRChose;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel nameProduct;
    private javax.swing.JLabel remove;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
