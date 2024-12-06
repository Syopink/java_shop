/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components;

import Database.ActionCate;
import Database.ActionProduct;
import Pojo.Product;
import com.mycompany.components.util.Option;
import com.mycompany.components.util.rowProducts;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
    private ActionCate ac=new ActionCate();
    private ActionProduct acp=new ActionProduct();
    private List<Object[]> categories = ac.getCate() ;
    private Option ops=new Option();
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private String selectCate="Chọn danh mục";
    private String selectRanges="Chọn khoảng giá";
    private String selectStatus="Chọn trạng thái";
    
 

    

    public ManagerProducts() {
        initComponents();
        SelectItemBox();
        buttonShowDialog();
        ImgUpLoad();
        initializeForm();
        addRows();
        addProduct();
        
        

    }
    
void initializeForm() {
    statusBox.setModel(new DefaultComboBoxModel<>(ops.StatusOptions()));
    CateBox.setModel(new DefaultComboBoxModel<>(cateList().toArray(new String[0])));
    statusBox.setModel(new DefaultComboBoxModel<>(ops.StatusOptions()));
    CateBox.setModel(new DefaultComboBoxModel<>(cateList().toArray(new String[0])));


    statusBox.setModel(new DefaultComboBoxModel<>(ops.StatusOptions()));

    DefaultComboBoxModel<String> cateModel = new DefaultComboBoxModel<>(cateList().toArray(new String[0]));
    cateModel.insertElementAt("Chọn danh mục", 0); 
    jCateBoxProducts.setModel(cateModel);  
    jCateBoxProducts.setSelectedIndex(0);  
    
    DefaultComboBoxModel<String> priceModel = new DefaultComboBoxModel<>(ops.PriceOptions());
    priceModel.insertElementAt("Chọn khoảng giá", 0);  
    jRangesPrice.setModel(priceModel);  
    jRangesPrice.setSelectedIndex(0);
    
    DefaultComboBoxModel<String> statusModel=new DefaultComboBoxModel<>(ops.StatusOptions());
    statusModel.insertElementAt("Chọn trạng thái", 0);
    jStatusBox.setModel(statusModel);
    jStatusBox.setSelectedIndex(0);
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

void SelectItemBox() {
    Filter.addActionListener(e -> {
        selectRanges = (String) jRangesPrice.getSelectedItem();
        this.selectCate = (String) jCateBoxProducts.getSelectedItem();
        this.selectStatus = (String) jStatusBox.getSelectedItem();
        if (!"Chọn khoảng giá".equals(selectRanges) ) {
            if (selectRanges != null && !selectRanges.trim().isEmpty()) {
                String[] parts = selectRanges.split("-");
                if (parts.length == 2) {
                    priceMin = new BigDecimal(parts[0].trim());  // Giá trị min
                    priceMax = new BigDecimal(parts[1].trim());  // Giá trị max
                } else {
                    priceMin = new BigDecimal(parts[0].trim());  // Chỉ có min
                    priceMax = new BigDecimal(Float.MAX_VALUE);  // Đặt max là giá trị lớn nhất
                }
            }
        } 
        
        addRows();  // Gọi hàm để hiển thị lại các hàng
    });
}
public void setPopUp(JFrame JP) {
    jDialog1.setResizable(true);
    jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
    jDialog1.setLocationRelativeTo(JP); 
    jDialog1.pack();  // Thay vì setSize, sử dụng pack để tự động điều chỉnh kích thước
    jDialog1.setVisible(isShowed);
        jDialog1.setLocationRelativeTo(null); // Hiển thị giữa màn hình
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
            jFileChooser1.setDialogTitle("Chọn hình ảnh");
            jFileChooser1.setAcceptAllFileFilterUsed(false);
            jFileChooser1.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "gif"));
        int result = jFileChooser1.showOpenDialog(this);
        if (result == jFileChooser1.APPROVE_OPTION) {
            File selectedFile = jFileChooser1.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image image = imageIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(image);
            jLabel12.setIcon(resizedIcon);
            jLabel12.setText("");
        }
    }

 
void addComponents(JPanel panelRows, Product product, Runnable callback){
    // Tạo một rowProducts mới
    rowProducts rowComponent = new rowProducts();
    
    // Sử dụng đối tượng Product để thiết lập thông tin cho rowComponent
    rowComponent.set(product);
    
    // Thêm rowComponent vào panelRows
    panelRows.add(rowComponent, 0);
    
    // Gọi các phương thức update và delete với callback
    rowComponent.updateProduct(callback);
    rowComponent.deleteProduct(callback);
}
 
    
void filter(Product product, JPanel panelRows) {
    boolean isInPriceRange = selectRanges.equals("Chọn khoảng giá") || (product.getPrice().compareTo(priceMin) >= 0 && product.getPrice().compareTo(priceMax) <= 0);
    boolean isInCategory = selectCate.equals("Chọn danh mục") || selectCate.equals(product.getCategoryTitle());
    boolean isInStatus = selectStatus.equals("Chọn trạng thái") || selectStatus.equals(product.getStatus());

    if (isInPriceRange && isInCategory && isInStatus) {
        addComponents(panelRows, product, this::addRows);
    }
}
public void addRows(){
    // Lấy dữ liệu từ cơ sở dữ liệu
    List<Object[]> products = acp.getProducts();
    
    // Tạo một JPanel cho các sản phẩm
    JPanel panelRows = new JPanel();
    panelRows.setLayout(new BoxLayout(panelRows, BoxLayout.Y_AXIS));
    panelRows.removeAll();
    
    // Duyệt qua danh sách sản phẩm và thêm các dòng vào giao diện
    for (Object[] row : products) {
        // Chuyển đổi các giá trị trong mảng Object[] thành một đối tượng Product
        int idProduct = (int) row[0]; 
        String name = (String) row[1];
        BigDecimal price = (BigDecimal) row[3];
        String thumbnail = (String) row[4];
        String status = (String) row[5];
        String nameCate = (String) row[6];
        boolean featured=(boolean) row[7];
        String promotion=(String) row[8];
        String warranty=(String) row[9];
        String accessories=(String) row[10];
        // Tạo đối tượng Product
        Product product = new Product();
        product.setIdProduct(idProduct);
        product.setName(name);
        product.setPrice(price);
        product.setThumbnail(thumbnail);
        product.setStatus(status);
        product.setCategoryTitle(nameCate);
        product.setFeatured(featured);
        product.setPromotion(promotion);
        product.setWarranty(warranty);
        product.setAccessories(accessories);

        
        // Gọi phương thức filter với đối tượng Product
        filter(product, panelRows);
    }
    
    // Cập nhật giao diện
    jScrollPane2.setViewportView(panelRows);
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
                javax.swing.JOptionPane.showMessageDialog(null, "Tên không được bỏ trống.");
                return;  // Exit if the product name is empty
            }

            // Use BigDecimal for price
            BigDecimal price = new BigDecimal(pricePro.getText());

            File selectedFile = jFileChooser1.getSelectedFile();
            String thumbnail = selectedFile != null ? selectedFile.getAbsolutePath() : "";
            String selectedStatus = (String) statusBox.getSelectedItem();
            String selectedCate = (String) CateBox.getSelectedItem();
            String description = jtdescription.getText();
            String promotion = jtPromotion.getText();
            String warranty = jtWarranty.getText();
            String accessories = jtAccessories.getText();
            String result = acp.addProduct(names, selectedCate, thumbnail, selectedStatus, price, description,promotion,warranty,accessories);
            javax.swing.JOptionPane.showMessageDialog(null, result);

            addRows();
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

        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtdescription = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jtWarranty = new javax.swing.JTextField();
        jtAccessories = new javax.swing.JTextField();
        jtPromotion = new javax.swing.JTextField();
        jDialog2 = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        AddProduct = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        rowProducts2 = new com.mycompany.components.util.rowProducts();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jRangesPrice = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jCateBoxProducts = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jStatusBox = new javax.swing.JComboBox<>();
        Filter = new javax.swing.JToggleButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Tên:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 49, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Giá:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 67, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Danh mục:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 90, -1));

        jPanel2.add(CateBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, 90, -1));

        status.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        status.setText("Trạng thái:");
        jPanel2.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 90, -1));

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
        jPanel2.add(AddNewProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 470, -1));

        jLabel12.setBackground(new java.awt.Color(153, 153, 153));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-image-50.png"))); // NOI18N
        jLabel12.setToolTipText("");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel12.setOpaque(true);
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 130, 160));

        jToggleButton2.setText("Choose File");
        jPanel2.add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 130, 43));

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
                .addContainerGap(495, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 60));

        jPanel2.add(statusBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 90, -1));

        pricePro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pricePro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(pricePro, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 142, 160, 30));

        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 76, 160, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Thông tin:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, -1, -1));

        jtdescription.setColumns(20);
        jtdescription.setRows(5);
        jScrollPane1.setViewportView(jtdescription);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, 340, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Bảo hành:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Khuyến mãi:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Phụ kiện đi kèm:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 246, -1, -1));

        jtWarranty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtWarranty.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jtWarranty, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 192, 160, 30));

        jtAccessories.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtAccessories.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jtAccessories, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 160, 30));

        jtPromotion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtPromotion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jtPromotion, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 283, 160, 30));

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

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
        setPreferredSize(new java.awt.Dimension(1100, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1240, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("DANH SÁCH SẢN PHẨM");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 410, 61));

        AddProduct.setBackground(new java.awt.Color(153, 204, 255));
        AddProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AddProduct.setForeground(new java.awt.Color(255, 255, 255));
        AddProduct.setText("Thêm");
        AddProduct.setBorder(null);
        jPanel1.add(AddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 100, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 148, -1, -1));

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

        jScrollPane2.setBorder(null);
        jScrollPane2.setViewportView(rowProducts2);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 950, 250));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 960, 310));

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Giá:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Loại");

        jCateBoxProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCateBoxProductsActionPerformed(evt);
            }
        });

        jLabel3.setText("Trạng thái :");

        Filter.setBackground(new java.awt.Color(153, 204, 255));
        Filter.setForeground(new java.awt.Color(255, 255, 255));
        Filter.setSelected(true);
        Filter.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRangesPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCateBoxProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jStatusBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jRangesPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCateBoxProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStatusBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 600, 120));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void AddNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewProductActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_AddNewProductActionPerformed

    private void jCateBoxProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCateBoxProductsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCateBoxProductsActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFileChooser1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton AddNewProduct;
    private javax.swing.JToggleButton AddProduct;
    private javax.swing.JComboBox<String> CateBox;
    private javax.swing.JToggleButton Filter;
    private javax.swing.JComboBox<String> jCateBoxProducts;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JComboBox<String> jRangesPrice;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jStatusBox;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTextField jtAccessories;
    private javax.swing.JTextField jtPromotion;
    private javax.swing.JTextField jtWarranty;
    private javax.swing.JTextArea jtdescription;
    private javax.swing.JTextField pricePro;
    private com.mycompany.components.util.rowProducts rowProducts2;
    private javax.swing.JLabel status;
    private javax.swing.JComboBox<String> statusBox;
    // End of variables declaration//GEN-END:variables
}
