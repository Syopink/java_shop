/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components;

import Database.Action;
import com.mycompany.components.util.Option;
import com.mycompany.components.util.rowCate;
import com.mycompany.components.util.rowProducts;
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author An Ninh
 */
public class ManagerProducts extends javax.swing.JPanel {

    /**
     * Creates new form ManagerProducts
     */
    private boolean isShowed=false;
    private boolean isOpen =false;
    private Action ac=new Action();
    private List<Object[]> categories = ac.getCate() ;
    private Option ops=new Option();

    public ManagerProducts() {
        initComponents();
        buttonShowDialog();
        ImgUpLoad();
        initializeForm();
        addRows();
        addProduct();
    }
    
    void buttonShowDialog() {
    AddProduct.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(AddProduct);
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

void ImgUpLoad(){
        jToggleButton2.addActionListener(new ActionListener(){
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
            jLabel12.setIcon(resizedIcon);
            jLabel12.setText("");
        }
    }

 void initializeForm() {
    statusBox.setModel(new DefaultComboBoxModel<>(ops.StatusOptions()));
    CateBox.setModel(new DefaultComboBoxModel<>(cateList().toArray(new String[0])));
}
 
 
    public void addRows(){
    // Lấy dữ liệu từ cơ sở dữ liệu
     List<Object[]> products = ac.getProducts();
    JPanel panelRows = new JPanel();
    panelRows.setLayout(new BoxLayout(panelRows,BoxLayout.Y_AXIS));
    panelRows.removeAll();
    // Duyệt qua danh sách categories và thêm các dòng vào giao diện
    for (Object[] row : products) {
        rowProducts rowComponent = new rowProducts();
        int idProduct = (int) row[0];    // Lấy giá trị idCate
        String name = (String) row[1]; // Lấy giá trị name
        float price =(float) row[3];
        String thumbnail = (String)row[4] ;
        String status = (String) row[5] ;
        String nameCate  = (String) row[6]  ;
        rowComponent.set(idProduct, nameCate, name, price, thumbnail, status);
        panelRows.add(rowComponent,0);
        deleteCate(rowComponent,rowComponent.getIdProduct());
        rowComponent.updateProduct(this::addRows);
    }
    jScrollPane2.setViewportView(panelRows);
    // Cập nhật lại giao diện sau khi thêm các dòng vào
    jScrollPane2.revalidate();
    jScrollPane2.repaint();
}
    List<String> cateList(){
        List<String> catelist = new ArrayList<>();
        for (Object[] row : categories) {
             String nameCate = (String) row[1];
             catelist.add(nameCate);
            }
        return catelist;
    }
 
   void addProduct() {
    AddNewProduct.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            // Directly get the text from the nameProduct field
            String names = jTextField2.getText();  
            if (names.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Product name cannot be empty.");
                return;  // Exit if the product name is empty
            }
            float price = Float.valueOf(pricePro.getText());
            File selectedFile = jFileChooser1.getSelectedFile();
            String thumbnail = selectedFile.getAbsolutePath();
            System.out.println(".mouseClicked() : " + thumbnail);
            

            String selectedStatus = (String) statusBox.getSelectedItem();
            String selectedCate = (String) CateBox.getSelectedItem();
            String result = ac.addProduct(names, selectedCate, thumbnail, selectedStatus, price);
            javax.swing.JOptionPane.showMessageDialog(null, result);
            
            addRows();
        }
    });
}
   

   public void deleteCate(rowProducts rpd,int id) {
    rpd.getDeleteToggle().addActionListener(e -> {
        System.out.println("com.mycompany.components.ManagerProducts.deleteCate() : "+id);
        String result = ac.DeleteProduct(id);
        javax.swing.JOptionPane.showMessageDialog(null, result);
        addRows();
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

        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        CateBox = new javax.swing.JComboBox<>();
        status = new javax.swing.JLabel();
        AddNewProduct = new javax.swing.JToggleButton();
        jLabel12 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        statusBox = new javax.swing.JComboBox<>();
        pricePro = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jDialog2 = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        AddProduct = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        rowProducts2 = new com.mycompany.components.util.rowProducts();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Tên :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 49, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Giá :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 67, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Ảnh :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 70, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Danh mục");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 90, -1));

        jPanel2.add(CateBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 90, -1));

        status.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        status.setText("Trạng thái ");
        jPanel2.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 90, -1));

        AddNewProduct.setBackground(new java.awt.Color(55, 65, 92));
        AddNewProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AddNewProduct.setForeground(new java.awt.Color(255, 255, 255));
        AddNewProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-upload-30.png"))); // NOI18N
        AddNewProduct.setBorder(null);
        AddNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewProductActionPerformed(evt);
            }
        });
        jPanel2.add(AddNewProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 470, -1));

        jLabel12.setBackground(new java.awt.Color(153, 153, 153));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-image-50.png"))); // NOI18N
        jLabel12.setToolTipText("");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel12.setOpaque(true);
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 130, 160));

        jToggleButton2.setText("Choose File");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 100, 43));

        jPanel4.setBackground(new java.awt.Color(55, 65, 92));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Thêm sản phẩm");

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

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 60));

        jPanel2.add(statusBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 90, -1));

        pricePro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pricePro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        pricePro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceProActionPerformed(evt);
            }
        });
        jPanel2.add(pricePro, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 150, 30));

        jTextField2.setText("jTextField2");
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 160, 40));

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Danh sách sản phẩm");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 383, 61));

        AddProduct.setBackground(new java.awt.Color(153, 255, 102));
        AddProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AddProduct.setForeground(new java.awt.Color(255, 255, 255));
        AddProduct.setText("Thêm sản phẩm");
        AddProduct.setBorder(null);
        AddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProductActionPerformed(evt);
            }
        });
        jPanel1.add(AddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 116, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Lọc:");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(51, 51, 51)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 108, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Giá ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 148, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tên");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 182, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 182, 308, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 147, 117, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Loại");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 148, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 147, 122, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 148, -1, -1));

        jScrollPane2.setBorder(null);
        jScrollPane2.setViewportView(rowProducts2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 284, 780, 250));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Tên sản phẩm");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 100, 42));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Hành động");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 110, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("ID");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Trạng thái");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 80, 40));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Ảnh");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 80, 40));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Danh mục");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 80, 40));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Giá");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 80, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 780, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProductActionPerformed
      
    }//GEN-LAST:event_AddProductActionPerformed

    private void AddNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewProductActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_AddNewProductActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void priceProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceProActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton AddNewProduct;
    private javax.swing.JToggleButton AddProduct;
    private javax.swing.JComboBox<String> CateBox;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTextField pricePro;
    private com.mycompany.components.util.rowProducts rowProducts2;
    private javax.swing.JLabel status;
    private javax.swing.JComboBox<String> statusBox;
    // End of variables declaration//GEN-END:variables
}
