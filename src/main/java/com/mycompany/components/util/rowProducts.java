    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components.util;

import Database.Action;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
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
    private boolean isUpdate =false;
    private boolean isShowed=false;
    private Action ac=new Action();
    private Option ops =new Option();
    private List<Object[]> categories =ac.getCate();

    public rowProducts() {
        initComponents();
        buttonShowDialog();
        ImgUpLoad();
        image.setSize(100,167);
        image.setPreferredSize(new java.awt.Dimension(100,167));
        image.setMaximumSize(new java.awt.Dimension(100,167));
        
// Optionally, you can set the minimum size as well if you want to ensure it doesn't shrink below that size
        image.setMinimumSize(new java.awt.Dimension(100,167));
    }
    
    void ImgUpLoad(){
        jToggleButton5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                uploadImage();
            }
        });
}
    
 private void uploadImage() {
        // Tạo JFileChooser để chọn ảnh
        jFileChooser1.setDialogTitle("Chọn hình ảnh");
            jFileChooser1.setAcceptAllFileFilterUsed(false);
            jFileChooser1.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "gif"));
        int result = jFileChooser1.showOpenDialog(this);
        if (result == jFileChooser1.APPROVE_OPTION) {
            File selectedFile = jFileChooser1.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image image = imageIcon.getImage().getScaledInstance(260, 260, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(image);
            jLabel13.setIcon(resizedIcon);
            jLabel13.setText("");
        }
    }
    

public JToggleButton getDeleteToggle(){
    return deletetoggle;
}    

public Integer getIdProduct(){
    return id;
}

    void buttonShowDialog() {
    UpdateToggle.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(UpdateToggle);
            isShowed = true;
            setPopUp(parentFrame);
        }
    });
}

public void setPopUp(JFrame JP) {
    jDialog1.setSize(560, 500);  
    jDialog1.setResizable(false);
    jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
    // Đặt vị trí của jDialog1 ở giữa parent frame
    jDialog1.setLocationRelativeTo(JP); 
    // Hiển thị hoặc ẩn jDialog1 dựa trên giá trị của isShowed
    jDialog1.setVisible(isShowed);
}

void initializeForm() {
    StatusBox.setModel(new DefaultComboBoxModel<>(ops.StatusOptions()));
    CateBox.setModel(new DefaultComboBoxModel<>(cateList().toArray(new String[0])));
}

List<String> cateList(){
        List<String> catelist = new ArrayList<>();
        for (Object[] row : categories) {
             String nameCate = (String) row[1];
             catelist.add(nameCate);
            }
        return catelist;
    }


public void set(int id, String cate, String name, float price, String thumbnail, String status) {
    jPanel2.setSize(707, 44);
    this.id = id;

    if (thumbnail != null && !thumbnail.isEmpty()) {
        image.setIcon(new javax.swing.ImageIcon(thumbnail));  // Tạo ImageIcon từ đường dẫn hợp lệ
    } 

    // Cập nhật các label
    jLabel1.setText(String.valueOf(id));
    jLabel2.setText(name);
    jLabel3.setText(String.valueOf(price));
    jLabel4.setText(status);
    jLabel5.setText(cate);
}

public void updateProduct(Runnable onSuccessCallbac){
    updateToggleRp.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            // Directly get the text from the nameProduct field
            String names = namechange.getText();
            float price = Float.parseFloat(pricePro.getText());
            if (names.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống");
                return;  
            }

            File selectedFile = jFileChooser1.getSelectedFile();
            if (selectedFile == null) {
                javax.swing.JOptionPane.showMessageDialog(null, "Vui lòng chọn một ảnh đại diện");
                return;
            }
            
            String thumbnail = selectedFile.getAbsolutePath();
            int idProduct = Integer.valueOf(jLabel1.getText());

            String selectedStatus = (String) StatusBox.getSelectedItem();
            String selectedCate = (String) CateBox.getSelectedItem();
            String result = ac.updateProduct(names, price, thumbnail, selectedStatus, selectedCate, idProduct);
            javax.swing.JOptionPane.showMessageDialog(null, result);
            onSuccessCallbac.run();
        }
    });
}


//void update(){
//    updateToggleRp.addMouseListener(new java.awt.event.MouseAdapter() {
//        @Override
//        public void mouseClicked(java.awt.event.MouseEvent e) {
//            // Directly get the text from the nameProduct field
//            String names = jTextField2.getText();  
//            if (names.isEmpty()) {
//                javax.swing.JOptionPane.showMessageDialog(null, "Product name cannot be empty.");
//                return;  // Exit if the product name is empty
//            }
//            float price = Float.valueOf(pricePro.getText());
//            File selectedFile = jFileChooser1.getSelectedFile();
//            String thumbnail = selectedFile.getAbsolutePath();
//            System.out.println(".mouseClicked() : " + thumbnail);
//            
//
//            String selectedStatus = (String) statusBox.getSelectedItem();
//            String selectedCate = (String) CateBox.getSelectedItem();
//            String result = ac.addProduct(names, selectedCate, thumbnail, selectedStatus, price);
//            javax.swing.JOptionPane.showMessageDialog(null, result);
//            
//            addRows();
//        }
//    });
//}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        namechange = new javax.swing.JTextField();
        pricePro = new javax.swing.JTextField();
        StatusBox = new javax.swing.JComboBox<>();
        updateToggleRp = new javax.swing.JToggleButton();
        jLabel13 = new javax.swing.JLabel();
        jToggleButton5 = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        CateBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jDialog2 = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel2 = new javax.swing.JPanel();
        deletetoggle = new javax.swing.JToggleButton();
        UpdateToggle = new javax.swing.JToggleButton();
        image = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Tên :");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 49, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Giá :");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 67, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Ảnh :");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 70, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Trạng thái: ");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 100, -1));

        namechange.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(namechange, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 120, 36));

        pricePro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel3.add(pricePro, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 150, 24));

        StatusBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(StatusBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 90, -1));

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
        jPanel3.add(updateToggleRp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 510, -1));

        jLabel13.setBackground(new java.awt.Color(153, 153, 153));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-image-50.png"))); // NOI18N
        jLabel13.setToolTipText("");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel13.setOpaque(true);
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 169, 130, 130));

        jToggleButton5.setText("Choose File");
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 120, 43));

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
                .addContainerGap(329, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 60));

        CateBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(CateBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 90, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Loại :");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 70, -1));

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jLabel4.setText("jLabel4");

        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    }//GEN-LAST:event_UpdateToggleActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void updateToggleRpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateToggleRpActionPerformed

    }//GEN-LAST:event_updateToggleRpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CateBox;
    private javax.swing.JComboBox<String> StatusBox;
    private javax.swing.JToggleButton UpdateToggle;
    private javax.swing.JToggleButton deletetoggle;
    private javax.swing.JLabel image;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JTextField namechange;
    private javax.swing.JTextField pricePro;
    private javax.swing.JToggleButton updateToggleRp;
    // End of variables declaration//GEN-END:variables
}
