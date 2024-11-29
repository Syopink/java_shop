/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components;

import Database.ActionCate;
import Database.ActionProduct;
import com.mycompany.components.util.Option;
import com.mycompany.components.util.rowProducts;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
    private float priceMin;
    private float priceMax;
    private String selectCate;
    private boolean isFilter =false;
    private String selectRanges;
    
 

    

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
        if (!"Chọn khoảng giá".equals(selectRanges) ) {
            isFilter = true;
            if (selectRanges != null && !selectRanges.trim().isEmpty()) {
                String[] parts = selectRanges.split("-");
                if (parts.length == 2) {
                    priceMin = Float.parseFloat(parts[0].trim());  // Giá trị min
                    priceMax = Float.parseFloat(parts[1].trim());  // Giá trị max
                } else {
                    priceMin = Float.parseFloat(parts[0].trim());  // Chỉ có min
                    priceMax = Float.MAX_VALUE;  // Đặt max là giá trị lớn nhất
                }
            }
        } else {
            isFilter = false;
        }
        
        addRows();  // Gọi hàm để hiển thị lại các hàng
    });
}

public void setPopUp(JFrame JP) {
    jDialog1.setSize(560, 500);  
    jDialog1.setResizable(false);
    jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
    jDialog1.setLocationRelativeTo(JP); 
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

 
    void addComponents(JPanel panelRows,int idProduct,String nameCate,String name,float price,String thumbnail,String status,Runnable callback){
        rowProducts rowComponent = new rowProducts();
        rowComponent.set(idProduct, nameCate, name, price, thumbnail, status);
        panelRows.add(rowComponent,0);
        deleteCate(rowComponent,rowComponent.getIdProduct());
        rowComponent.updateProduct(callback);
    }
    
 
    
void filter(int idProduct, String name, float price, String thumbnail, String status, String nameCate, JPanel panelRows) {
    if (!isFilter) {
        // Nếu không áp dụng bộ lọc, thêm tất cả sản phẩm
        addComponents(panelRows, idProduct, nameCate, name, price, thumbnail, status, this::addRows);
    } else {
        // Kiểm tra các điều kiện khi áp dụng bộ lọc
        boolean isInPriceRange = price >= this.priceMin && price <= this.priceMax;
        boolean isInCategory = !selectCate.equals("Chọn danh mục") && selectCate.equals(nameCate);  // Sửa lại điều kiện so với cũ
        List<String> listStatus = Arrays.asList(ops.StatusOptions()); // Giả sử ops.StatusOptions() trả về mảng các trạng thái hợp lệ
        boolean selectStatus = listStatus.contains(status);

        // Kiểm tra điều kiện để thêm sản phẩm vào panel
        if ((isInCategory && isInPriceRange && selectStatus) ||  // Tất cả các điều kiện lọc đều thỏa mãn
            (isInCategory && selectRanges.equals("Chọn khoảng giá")) || // Lọc theo danh mục và không có khoảng giá
            (isInPriceRange && selectCate.equals("Chọn danh mục")) || // Lọc theo khoảng giá và không có danh mục
            selectStatus) { // Lọc theo trạng thái
            addComponents(panelRows, idProduct, nameCate, name, price, thumbnail, status, this::addRows);
        }
    }
}



 
 
    public void addRows(){
    // Lấy dữ liệu từ cơ sở dữ liệu
     List<Object[]> products = acp.getProducts();
    JPanel panelRows = new JPanel();
    panelRows.setLayout(new BoxLayout(panelRows,BoxLayout.Y_AXIS));
    panelRows.removeAll();
    // Duyệt qua danh sách categories và thêm các dòng vào giao diện
    for (Object[] row : products) {
        int idProduct = (int) row[0]; 
        String name = (String) row[1];
        float price =(float) row[3];
        String thumbnail = (String)row[4] ;
        String status = (String) row[5] ;
        String nameCate  = (String) row[6]  ;
        System.out.println("Price: " + price + ", Min: " + this.priceMin + ", Max: " + this.priceMax);
        filter(idProduct, name, price, thumbnail, status, nameCate, panelRows);
    }
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
                javax.swing.JOptionPane.showMessageDialog(null, "Product name cannot be empty.");
                return;  // Exit if the product name is empty
            }
            float price = Float.valueOf(pricePro.getText());
            File selectedFile = jFileChooser1.getSelectedFile();
            String thumbnail = selectedFile.getAbsolutePath();
            System.out.println(".mouseClicked() : " + thumbnail);
            

            String selectedStatus = (String) statusBox.getSelectedItem();
            String selectedCate = (String) CateBox.getSelectedItem();
            String result = acp.addProduct(names, selectedCate, thumbnail, selectedStatus, price);
            javax.swing.JOptionPane.showMessageDialog(null, result);
            
            addRows();
        }
    });
}
   

   public void deleteCate(rowProducts rpd,int id) {
    rpd.getDeleteToggle().addActionListener(e -> {
        System.out.println("com.mycompany.components.ManagerProducts.deleteCate() : "+id);
        String result = acp.DeleteProduct(id);
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
        jLabel5 = new javax.swing.JLabel();
        jCateBoxProducts = new javax.swing.JComboBox<>();
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
        jLabel11 = new javax.swing.JLabel();
        jRangesPrice = new javax.swing.JComboBox<>();
        Filter = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jStatusBox = new javax.swing.JComboBox<>();

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
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Loại");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 30, -1));

        jCateBoxProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCateBoxProductsActionPerformed(evt);
            }
        });
        jPanel1.add(jCateBoxProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 122, -1));

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

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Giá khoảng");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 20));

        jPanel1.add(jRangesPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 370, -1));

        Filter.setSelected(true);
        Filter.setText("Lọc");
        Filter.setOpaque(false);
        Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterActionPerformed(evt);
            }
        });
        jPanel1.add(Filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 50, 30));

        jLabel3.setText("Trạng thái :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 70, 20));

        jPanel1.add(jStatusBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 160, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 793, -1));
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

    private void jCateBoxProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCateBoxProductsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCateBoxProductsActionPerformed

    private void FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FilterActionPerformed


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
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JComboBox<String> jRangesPrice;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jStatusBox;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTextField pricePro;
    private com.mycompany.components.util.rowProducts rowProducts2;
    private javax.swing.JLabel status;
    private javax.swing.JComboBox<String> statusBox;
    // End of variables declaration//GEN-END:variables
}
