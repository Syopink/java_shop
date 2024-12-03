/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components;

import Pojo.Customer;
import Process.customers;
import com.mycompany.components.util.rowsCustomer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author An Ninh
 */
public class ManagerCustomer extends javax.swing.JPanel {

    private List<rowsCustomer> rowsList = new ArrayList<>(); // Danh sách các hàng khách hàng
    private Customer customer;
    private boolean showingDeleted = false; // Trạng thái hiện tại (false = rowCustomer, true = rowCustomer2)

    customers cs = new customers();  // Khởi tạo đối tượng customers

    /**
     * Creates new form ManagerUser
     */
    public ManagerCustomer() {
        initComponents();
        initCustomerList(); // Tải dữ liệu khách hàng khi khởi tạo

    }

    private void initCustomerList() {
        try {
            customers cs = new customers();
            List<Customer> customerList = cs.getAllCustomers(); // Lấy toàn bộ khách hàng
            loadCustomers(customerList); // Hiển thị lên giao diện
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading customers: " + e.getMessage());
        }
    }

    private void loadCustomers(List<Customer> customers) {
        rowsList.clear();
        jScrollPane1.getViewport().removeAll(); // Xóa nội dung cũ nếu có

        // Tạo một JPanel mới để chứa các hàng
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new javax.swing.BoxLayout(listPanel, javax.swing.BoxLayout.Y_AXIS)); // Sắp xếp theo chiều dọc

        int index = 1; // Bắt đầu từ số thứ tự 1
        for (Customer customer : customers) {
            rowsCustomer row = new rowsCustomer(index, customer, new customers()); // Truyền số thứ tự vào rowsCustomer
            rowsList.add(row); // Thêm vào danh sách quản lý
            listPanel.add(row); // Thêm hàng vào giao diện
            index++; // Tăng số thứ tự
        }
        listPanel.setPreferredSize(new java.awt.Dimension(listPanel.getPreferredSize().width, 400));  // Thiết lập chiều cao cố định cho listPanel

        // Đặt JPanel mới vào JScrollPane
        jScrollPane1.setViewportView(listPanel);

        // Làm mới giao diện
        listPanel.revalidate();
        listPanel.repaint();
    }

    private void searchCustomers() throws SQLException {
        String name = jTextField1.getText().trim(); // Lấy giá trị từ ô nhập tên
        String email = jTextField2.getText().trim(); // Lấy giá trị từ ô nhập email
        customers cs = new customers(); // Tạo đối tượng xử lý DAL

        // Kiểm tra trạng thái đang hiển thị khách hàng đã xóa hay chưa
        boolean isDeleted = showingDeleted;  // Trạng thái xóa hay chưa xóa

        if (name.isEmpty() && email.isEmpty()) {
            // Nếu không nhập gì, hiển thị toàn bộ danh sách khách hàng theo trạng thái xóa
            List<Customer> allCustomers = cs.searchCustomer(name, email, isDeleted); // Tìm kiếm với isDeleted
            loadCustomers(allCustomers); // Hiển thị toàn bộ danh sách
            return;
        }

        // Tìm kiếm khách hàng với tên, email và trạng thái xóa
        List<Customer> filteredList = cs.searchCustomer(name, email, isDeleted); // Tìm kiếm với isDeleted
        if (filteredList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng nào phù hợp!");
        } else {
            loadCustomers(filteredList); // Hiển thị kết quả tìm kiếm
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rows1 = new com.mycompany.components.util.rows();
        bg = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rowsCustomer = new com.mycompany.components.util.rowsCustomer();
        jScrollPane2 = new javax.swing.JScrollPane();
        rowsCustomer1 = new com.mycompany.components.util.rowsCustomer();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jbtnSearch = new javax.swing.JButton();
        jbtnDel = new javax.swing.JButton();
        jDel = new javax.swing.JLabel();
        jRestore = new javax.swing.JLabel();
        jbtnSelectAll = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 0));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1100, 600));

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setOpaque(false);
        bg.setPreferredSize(new java.awt.Dimension(1240, 600));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("DANH SÁCH KHÁCH HÀNG");
        bg.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 6, 680, 73));

        jPanel3.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Họ & Tên");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("Email");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Hành động");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, -1, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("ID");
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);
        jScrollPane1.setViewportView(rowsCustomer);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 946, -1));

        jScrollPane2.setBorder(null);
        jScrollPane2.setOpaque(false);
        jScrollPane2.setViewportView(rowsCustomer1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 30, 940, -1));

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 198, -1, 396));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jLabel11.setInheritsPopupMenu(false);
        bg.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 151, -1, -1));

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tên:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Email:");

        jbtnSearch.setBackground(new java.awt.Color(153, 204, 255));
        jbtnSearch.setText("Tìm kiếm");
        jbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(435, Short.MAX_VALUE)
                .addComponent(jbtnSearch)
                .addGap(14, 14, 14))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnSearch)
                .addGap(7, 7, 7))
        );

        bg.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 85, -1, -1));

        jbtnDel.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jbtnDel.setText("X");
        jbtnDel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDelActionPerformed(evt);
            }
        });
        bg.add(jbtnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 220, -1, -1));

        jDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-delete-30.png"))); // NOI18N
        jDel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDelMouseClicked(evt);
            }
        });
        bg.add(jDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 250, -1, -1));

        jRestore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-restore-30.png"))); // NOI18N
        jRestore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRestore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRestoreMouseClicked(evt);
            }
        });
        bg.add(jRestore, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 220, -1, -1));

        jbtnSelectAll.setText("A");
        jbtnSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSelectAllActionPerformed(evt);
            }
        });
        bg.add(jbtnSelectAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSearchActionPerformed
        // TODO add your handling code here:
        try {
            searchCustomers(); // Gọi phương thức tìm kiếm
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm khách hàng: " + e.getMessage());
        }
    }//GEN-LAST:event_jbtnSearchActionPerformed

    private void jbtnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDelActionPerformed
        // TODO add your handling code here:
        // Tạo danh sách các khách hàng cần xóa
        List<Customer> customersToDelete = new ArrayList<>();
        customers cs = new customers();  // Khởi tạo đối tượng customers

        // Kiểm tra tất cả các hàng trong rowsList và tìm các khách hàng đã chọn
        for (rowsCustomer row : rowsList) {
            if (row.isSelected()) {  // Kiểm tra xem row có được chọn không
                customersToDelete.add(row.getCustomer());  // Lấy khách hàng từ row
            }
        }

        if (!customersToDelete.isEmpty()) {
            // Xóa khách hàng trong cơ sở dữ liệu
            try {
                // Xóa khách hàng trong cơ sở dữ liệu thông qua DAL
                cs.deleteCustomers(customersToDelete);
                JOptionPane.showMessageDialog(this, "Đã xóa thành công khách hàng.");
                initCustomerList();  // Làm mới danh sách khách hàng
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa khách hàng: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không có khách hàng nào được chọn.");
        }
    }//GEN-LAST:event_jbtnDelActionPerformed

    private void jDelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDelMouseClicked
        // TODO add your handling code here:
        showingDeleted = !showingDeleted;  // Chuyển đổi trạng thái giữa đã xóa và chưa xóa
        Boolean active = false;

        try {
            List<Customer> customerList;

            if (showingDeleted) {
                // Hiển thị khách hàng đã xóa (isDeleted = '1')
                customerList = cs.getAllCustomersDeleted(); // Lấy danh sách khách hàng đã xóa

                // Kiểm tra nếu không có khách hàng đã xóa
                if (customerList.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không có khách hàng đã xóa!");
                    showingDeleted = false;  // Đặt lại trạng thái về chưa xóa
                    customerList = cs.getAllCustomers();  // Lấy lại danh sách khách hàng chưa xóa
                } else {
                    jLabel28.setText("DANH SÁCH KHÁCH HÀNG ĐÃ XÓA");
                    jbtnDel.setVisible(active);      // Ẩn nút xóa
                    jRestore.setVisible(!active);    // Hiển thị nút phục hồi
                }
            } else {
                // Hiển thị khách hàng chưa xóa (isDeleted = '0')
                customerList = cs.getAllCustomers(); // Lấy danh sách khách hàng chưa xóa
                jLabel28.setText("DANH SÁCH KHÁCH HÀNG");
                jbtnDel.setVisible(!active);      // Hiển thị nút xóa
                jRestore.setVisible(active);     // Ẩn nút phục hồi
            }

            // Tải danh sách khách hàng lên giao diện
            loadCustomers(customerList);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách khách hàng: " + e.getMessage());
        }
    }//GEN-LAST:event_jDelMouseClicked
    private List<Customer> getSelectedCustomers() {
        List<Customer> selectedCustomers = new ArrayList<>();

        // Kiểm tra tất cả các hàng trong rowsList và tìm các khách hàng đã được chọn
        for (rowsCustomer row : rowsList) {
            if (row.isSelected()) {  // Kiểm tra xem row có được chọn không
                selectedCustomers.add(row.getCustomer());  // Lấy khách hàng từ row
            }
        }

        return selectedCustomers;
    }
    private void jRestoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRestoreMouseClicked
        // TODO add your handling code here:
        try {
            // Lấy danh sách các khách hàng đã chọn (có thể sử dụng phương thức isSelected() trong các hàng khách hàng)
            List<Customer> customersToRestore = getSelectedCustomers();  // Hàm này cần lấy danh sách khách hàng đã được chọn
            List<Customer> customerList = new ArrayList<>();

            // Kiểm tra nếu có khách hàng được chọn để phục hồi
            if (customersToRestore.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một khách hàng để phục hồi.");
                return;
            }

            // Thực hiện phục hồi khách hàng (set isDeleted = 0)
            boolean isRestored = cs.restoreCustomers(customersToRestore);  // Phương thức restoreCustomers bạn phải định nghĩa

            if (isRestored) {
                JOptionPane.showMessageDialog(this, "Khách hàng đã được phục hồi thành công!");

                // Chuyển lại về danh sách khách hàng chưa xóa
                showingDeleted = false;  // Đặt trạng thái về chưa xóa

                // Cập nhật lại giao diện và hiển thị danh sách khách hàng chưa xóa
                customerList = cs.getAllCustomers();  // Lấy lại danh sách khách hàng chưa xóa
                loadCustomers(customerList);  // Tải lại danh sách lên giao diện
                jLabel28.setText("DANH SÁCH KHÁCH HÀNG");

                jbtnDel.setVisible(true);  // Hiển thị nút xóa
                jRestore.setVisible(false);  // Ẩn nút phục hồi
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi phục hồi khách hàng.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi phục hồi khách hàng: " + e.getMessage());
        }
    }//GEN-LAST:event_jRestoreMouseClicked

    private void jbtnSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSelectAllActionPerformed
 // Duyệt qua từng dòng trong danh sách rowsList và đảo ngược trạng thái chọn của nó
    for (rowsCustomer row : rowsList) {
        boolean currentSelected = row.isSelected(); // Lấy trạng thái hiện tại của radio button

        // Đảo ngược trạng thái chọn (nếu đang chọn thì bỏ chọn, ngược lại thì chọn)
        row.setRChoseSelected(!currentSelected);
    }

    // Tùy chọn: Cập nhật giao diện hoặc thực hiện các cập nhật khác sau khi thay đổi trạng thái chọn
    this.revalidate();  // Kiểm tra lại bố cục của panel
    this.repaint();     // Vẽ lại panel để hiển thị các thay đổi
    }//GEN-LAST:event_jbtnSelectAllActionPerformed
    private void updateCustomerList() {
        try {
            // Kiểm tra trạng thái xem có đang hiển thị khách hàng đã xóa hay không
            List<Customer> customerList;

            if (showingDeleted) {
                // Nếu đang hiển thị khách hàng đã xóa, không cần thay đổi
                customerList = cs.getAllCustomersDeleted(); // Lấy danh sách khách hàng đã xóa
            } else {
                // Nếu không, lấy danh sách khách hàng chưa xóa
                customerList = cs.getAllCustomers(); // Lấy danh sách khách hàng chưa xóa
            }

            // Cập nhật lại giao diện với danh sách khách hàng
            loadCustomers(customerList);  // Gọi phương thức để tải lại danh sách lên giao diện

            // Nếu đang hiển thị khách hàng đã xóa, thay đổi tiêu đề và các nút
            if (showingDeleted) {
                jLabel28.setText("DANH SÁCH KHÁCH HÀNG ĐÃ XÓA");
                jbtnDel.setVisible(false);    // Ẩn nút xóa
                jRestore.setVisible(true);    // Hiển thị nút phục hồi
            } else {
                jLabel28.setText("DANH SÁCH KHÁCH HÀNG");
                jbtnDel.setVisible(true);     // Hiển thị nút xóa
                jRestore.setVisible(false);   // Ẩn nút phục hồi
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật danh sách khách hàng: " + e.getMessage());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jDel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel jRestore;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton jbtnDel;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JButton jbtnSelectAll;
    private com.mycompany.components.util.rows rows1;
    private com.mycompany.components.util.rowsCustomer rowsCustomer;
    private com.mycompany.components.util.rowsCustomer rowsCustomer1;
    // End of variables declaration//GEN-END:variables
}
