    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components.util;

import Database.ActionCate;
import Database.ActionProduct;
import Pojo.Product;
import Process.product;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author An Ninh
 */
public class rowProducts extends javax.swing.JPanel {

    /**
     * Creates new form rowProducts
     */
private int id;
    private boolean isUpdate = false;
    private boolean isShowed = false;
    private ActionCate ac = new ActionCate();
    private ActionProduct acp = new ActionProduct();

    private Option ops = new Option();
    private List<Object[]> categories = ac.getCate();
 
    private boolean choose_file = false;

    private  String imageName;
    private Product currentProduct;
    private String targetDirectory;
    
    public rowProducts() {
        initComponents();
        buttonShowDialog();
        ImgUpLoad();
        initializeForm();
        image.setSize(100, 167);
        image.setPreferredSize(new java.awt.Dimension(100, 167));
        image.setMaximumSize(new java.awt.Dimension(100, 167));
        image.setMinimumSize(new java.awt.Dimension(100, 167));
    }

    void ImgUpLoad() {
        jToggleButton5.addActionListener(e -> uploadImage());
    }

    private void uploadImage() {
    jFileChooser1.setDialogTitle("Chọn hình ảnh");
    jFileChooser1.setAcceptAllFileFilterUsed(false);
    jFileChooser1.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "gif"));
    
    int result = jFileChooser1.showOpenDialog(this);
    if (result == jFileChooser1.APPROVE_OPTION) {
        File selectedFile = jFileChooser1.getSelectedFile();
        
        // Get the image file name
        String fileName = selectedFile.getName();
        
        // Define the target directory path
        this.targetDirectory = new pathImg().path();
        
        // Combine the directory path with the file name to form the full image path
        String imagePath = targetDirectory  + fileName;
        
        // Store the full path in the imageName variable
        this.imageName = imagePath;

        // Resize the image
        ImageIcon resizedIcon = resizeImage(imagePath, 100, 150);
        
        // Set the resized image to the thumbnail label
        update_thumbnail.setIcon(resizedIcon);
        update_thumbnail.setText("");
        
        // Mark the file as chosen
        choose_file = true;
        
        // Set the thumbnail image path for the current product
        currentProduct.setThumbnail(imageName);
    }
}

private ImageIcon resizeImage(String imagePath, int width, int height) {
    ImageIcon icon = new ImageIcon(imagePath);
    Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImage);
}


    public JToggleButton getDeleteToggle() {
        return deletetoggle;
    }

    public Integer getIdProduct() {
        return id;
    }

    public void setPopUp(JFrame parentFrame) {
        jDialog1.setResizable(true);
        jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog1.setLocationRelativeTo(parentFrame);
        jDialog1.pack();
        jDialog1.setVisible(isShowed);
    }

    void initializeForm() {
        List<Object[]> categories = ac.getCate();
        StatusBox.setModel(new DefaultComboBoxModel<>(ops.StatusOptions()));
        CateBox.setModel(new DefaultComboBoxModel<>(cateList(categories).toArray(new String[0])));
    }

    List<String> cateList(List<Object[]> categories ) {
        List<String> catelist = new ArrayList<>();
        for (Object[] row : categories) {
            String nameCate = (String) row[1];
            catelist.add(nameCate);
        }
        return catelist;
    }

    public void set(Product product) {
        this.currentProduct = product;

        jLabel1.setText(String.valueOf(product.getIdProduct()));
        name_product.setText(product.getName());
        jLabel3.setText(String.valueOf(product.getPrice()));
        status_product.setText(product.getStatus());
        category_product.setText(product.getCategoryTitle());

        if (product.getThumbnail() != null && !product.getThumbnail().isEmpty()) {
            ImageIcon resizedIcon = resizeImage(new pathImg().path()+ product.getThumbnail(), 100, 150);
            image.setIcon(resizedIcon);
        } else {
            String defaultThumbnailPath = "path/to/default/thumbnail.jpg";
            ImageIcon defaultIcon = resizeImage(defaultThumbnailPath, 100, 150);
            image.setIcon(defaultIcon);
        }
    }

    void buttonShowDialog() {
        UpdateToggle.addActionListener(e -> {
            Product product = getSelectedProduct();
            if (product != null) {
                namechange.setText(product.getName());
                pricePro.setText(product.getPrice().toString());
                jtdescription.setText(product.getDescriptions());
                jtPromotion.setText(product.getPromotion());
                jtWarranty.setText(product.getWarranty());
                jtAccessories.setText(product.getAccessories());
                StatusBox.setSelectedItem(product.getStatus());
                CateBox.setSelectedItem(product.getCategoryTitle());
                System.out.println("com.mycompany.components.util.rowProducts.buttonShowDialog() : "+product.getAccessories());
                
                
                if (product.getThumbnail() != null && !product.getThumbnail().isEmpty()) {
                    File imgFile = new File(product.getThumbnail()+new pathImg().path());
                    if (imgFile.exists()) {
                        ImageIcon imageIcon = new ImageIcon(imgFile.getAbsolutePath());
                        Image image = imageIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
                        ImageIcon resizedIcon = new ImageIcon(image);
                        update_thumbnail.setIcon(resizedIcon);
                        update_thumbnail.setText("");
                    }
                }
                isShowed = true;
                setPopUp(null);
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm.");
            }
        });
    }

    public Product getSelectedProduct() {
        return currentProduct;
    }

    public void updateProduct(Runnable onSuccessCallback) {
        updateToggleRp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String name = namechange.getText();
                String priceText = pricePro.getText();

                if (name.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống");
                    return;
                }

                BigDecimal price = new BigDecimal(priceText);

                String thumbnail = choose_file ? jFileChooser1.getSelectedFile().getName()  : currentProduct.getThumbnail();

                String status = (String) StatusBox.getSelectedItem();
                String category = (String) CateBox.getSelectedItem();
                String description = jtdescription.getText();
                String promotion = jtPromotion.getText();
                String warranty = jtWarranty.getText();
                String accessories = jtAccessories.getText();

                currentProduct.setName(name);
                currentProduct.setPrice(price);
                currentProduct.setThumbnail(thumbnail);
                currentProduct.setStatus(status);
                currentProduct.setCategoryTitle(category);
                currentProduct.setDescriptions(description);
                currentProduct.setPromotion(promotion);
                currentProduct.setWarranty(warranty);
                currentProduct.setAccessories(accessories);
                currentProduct.setThumbnail(thumbnail);
                
             
                String result = acp.updateProduct(currentProduct);
                javax.swing.JOptionPane.showMessageDialog(null, result);

                onSuccessCallback.run();
            }
        });
    }

    public void deleteProduct(Runnable callback) {
        deletetoggle.addActionListener(e -> {
            String result = acp.DeleteProduct(id);
            javax.swing.JOptionPane.showMessageDialog(null, result);
            callback.run();
        });
    }

    // Các lớp con (Option, ActionCate, ActionProduct, Product) sẽ được gộp vào đây
  

    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        namechange = new javax.swing.JTextField();
        pricePro = new javax.swing.JTextField();
        StatusBox = new javax.swing.JComboBox<>();
        updateToggleRp = new javax.swing.JToggleButton();
        update_thumbnail = new javax.swing.JLabel();
        jToggleButton5 = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        CateBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtdescription = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jtWarranty = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jtAccessories = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jtPromotion = new javax.swing.JTextField();
        jDialog2 = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel2 = new javax.swing.JPanel();
        deletetoggle = new javax.swing.JToggleButton();
        UpdateToggle = new javax.swing.JToggleButton();
        image = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        name_product = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        status_product = new javax.swing.JLabel();
        category_product = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Tên :");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 49, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Giá:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 67, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Trạng thái: ");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 100, -1));

        namechange.setActionCommand("null");
        namechange.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(namechange, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 160, 36));

        pricePro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(pricePro, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 160, 30));

        StatusBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusBoxActionPerformed(evt);
            }
        });
        jPanel3.add(StatusBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 132, 90, -1));

        updateToggleRp.setBackground(new java.awt.Color(55, 65, 92));
        updateToggleRp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        updateToggleRp.setForeground(new java.awt.Color(255, 255, 255));
        updateToggleRp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-upload-30.png"))); // NOI18N
        updateToggleRp.setBorder(null);
        updateToggleRp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateToggleRpActionPerformed(evt);
            }
        });
        jPanel3.add(updateToggleRp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, 510, -1));

        update_thumbnail.setBackground(new java.awt.Color(153, 153, 153));
        update_thumbnail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        update_thumbnail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-image-50.png"))); // NOI18N
        update_thumbnail.setToolTipText("");
        update_thumbnail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        update_thumbnail.setOpaque(true);
        jPanel3.add(update_thumbnail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, 180));

        jToggleButton5.setText("Choose File");
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 140, 43));

        jPanel4.setBackground(new java.awt.Color(55, 65, 92));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Sửa sản phẩm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 60));

        CateBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CateBoxActionPerformed(evt);
            }
        });
        jPanel3.add(CateBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 82, 90, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Danh mục:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 90, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Thông tin:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 90, -1));

        jtdescription.setColumns(20);
        jtdescription.setRows(5);
        jScrollPane1.setViewportView(jtdescription);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 290, 90));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Bảo hành:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, -1, 30));

        jtWarranty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtWarranty.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(jtWarranty, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 160, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Phụ kiện đi kèm:");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, 30));

        jtAccessories.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtAccessories.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(jtAccessories, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 226, 160, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Khuyến mãi:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, -1, 30));

        jtPromotion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtPromotion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(jtPromotion, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, 160, 30));

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setOpaque(false);

        deletetoggle.setBackground(new java.awt.Color(255, 0, 0));
        deletetoggle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-trash-24.png"))); // NOI18N
        deletetoggle.setBorder(null);

        UpdateToggle.setBackground(new java.awt.Color(0, 255, 255));
        UpdateToggle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-pencil-24.png"))); // NOI18N
        UpdateToggle.setBorder(null);
        UpdateToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateToggleActionPerformed(evt);
            }
        });

        image.setBackground(new java.awt.Color(153, 153, 153));
        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-image-50.png"))); // NOI18N
        image.setOpaque(true);

        jLabel1.setText("jLabel1");

        name_product.setText("jLabel2");

        jLabel3.setText("jLabel3");

        status_product.setText("jLabel4");

        category_product.setText("jLabel5");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(name_product, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(status_product, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(category_product, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UpdateToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deletetoggle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(category_product, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status_product, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name_product, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UpdateToggle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(deletetoggle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateToggleActionPerformed
        // TODO add your handling code here:
        isUpdate=true;
        List<Object[]> categories = ac.getCate();  // Lấy danh mục từ cơ sở dữ liệu
        CateBox.setModel(new DefaultComboBoxModel<>(cateList(categories).toArray(new String[0]))); 
    }//GEN-LAST:event_UpdateToggleActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        // TODO add your handling code here:
        
        choose_file=!choose_file;
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void updateToggleRpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateToggleRpActionPerformed

    }//GEN-LAST:event_updateToggleRpActionPerformed

    private void CateBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CateBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CateBoxActionPerformed

    private void StatusBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CateBox;
    private javax.swing.JComboBox<String> StatusBox;
    private javax.swing.JToggleButton UpdateToggle;
    private javax.swing.JLabel category_product;
    private javax.swing.JToggleButton deletetoggle;
    private javax.swing.JLabel image;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JTextField jtAccessories;
    private javax.swing.JTextField jtPromotion;
    private javax.swing.JTextField jtWarranty;
    private javax.swing.JTextArea jtdescription;
    private javax.swing.JLabel name_product;
    private javax.swing.JTextField namechange;
    private javax.swing.JTextField pricePro;
    private javax.swing.JLabel status_product;
    private javax.swing.JToggleButton updateToggleRp;
    private javax.swing.JLabel update_thumbnail;
    // End of variables declaration//GEN-END:variables
}
