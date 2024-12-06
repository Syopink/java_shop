/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.CustomerLayout;

import Interface.frmChangeInformation;
import Interface.login;
import Process.user;
import com.mycompany.vietpro.CustomerLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author An Ninh
 */
public class CustomerNavbar extends javax.swing.JPanel {
    private user us;
    private Color defaultBackgroundColor = new Color(255, 87, 34); // Màu cam mặc định
    private Color selectedBackgroundColor = new Color(24, 26, 47); // Màu nền khi chọn
   private boolean isRouter1Selected = false; // Trạng thái router 1
    private boolean isRouter2Selected = false; // Trạng thái router 2
    private boolean isRouter3selected=false;
    /**
     * Creates new form CustomerNavbar
     */
    public CustomerNavbar() {
       us = CustomerLayout.getUser();
        initComponents();
        updateUserInfo(us); // Gọi hàm cập nhật thông tin người dùng
        Menu(); // Khởi tạo menu với các lựa chọn 
                addListeners(); // Thêm sự kiện cho router
        updateRouterBackground(); // Cập nhật màu nền ban đầu

    }
    
    
    
    public void Router1(String text, JPanel mainPanel) {
        customerToggleRouter1.set(text, mainPanel);
        isRouter1Selected = true;
        isRouter2Selected = false;
        isRouter3selected =false;
        updateRouterBackground();
    }

    public void Router2(String text, JPanel mainPanel) {
        customerToggleRouter2.set(text, mainPanel);
        isRouter1Selected = false;
        isRouter2Selected = true;
        isRouter3selected=false;
        updateRouterBackground();
    }
    public void Router3(String text, JPanel mainPanel) {
        customerToggleRouter3.set(text, mainPanel);
        isRouter1Selected = false;
        isRouter2Selected = false;
        isRouter3selected=true;
        updateRouterBackground();
    }
    public void updateUserInfo(user us) {
         if (us != null) {
        jName.setText(us.getFullName() != null ? us.getFullName() : "Name");
        jRole.setText("admin".equals(us.getRole()) ? "Admin" : "Khách hàng");
    } else {
        jName.setText("Name");
        jRole.setText("Role");
    }
    }
     void Menu() {
        // Tạo menu
        jPopupMenu.setSize(200, 200);
        JMenuItem logout = new JMenuItem("Đăng xuất");
        JMenuItem changeInformation = new JMenuItem("Sửa thông tin");

        jPopupMenu.add(logout);
        jPopupMenu.add(changeInformation);

        // Xử lý sự kiện đăng xuất
        logout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JFrame homepageFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                homepageFrame.dispose();
                login loginFrame = new login();  
                loginFrame.setVisible(true);
            }
        });
        
        changeInformation.addActionListener(e -> {
            JFrame homepageFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            homepageFrame.dispose();
            frmChangeInformation frmChangeInfor = new frmChangeInformation();  
            frmChangeInfor.setVisible(true);
        });

        // Tạo popup menu khi nhấn chuột vào panel
        jPanel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    jPopupMenu.show(jPanel2, e.getX(), e.getY());
                }
            }
        });
    }
    
        private void addListeners() {
        // Router 1
        customerToggleRouter1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                customerToggleRouter1.setBackground(selectedBackgroundColor); // Hiệu ứng hover
                customerToggleRouter1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Con trỏ hand
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isRouter1Selected) {
                    customerToggleRouter1.setBackground(defaultBackgroundColor); // Quay lại màu cũ nếu không chọn
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                isRouter1Selected = true;
                isRouter2Selected = false;
                updateRouterBackground();
            }
        });

        // Router 2
        customerToggleRouter2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                customerToggleRouter2.setBackground(selectedBackgroundColor);
                customerToggleRouter2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isRouter2Selected) {
                    customerToggleRouter2.setBackground(defaultBackgroundColor);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                isRouter1Selected = false;
                isRouter2Selected = true;
                updateRouterBackground();
            }
        });
        customerToggleRouter3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                customerToggleRouter3.setBackground(selectedBackgroundColor);
                customerToggleRouter3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isRouter3selected) {
                    customerToggleRouter2.setBackground(defaultBackgroundColor);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                isRouter1Selected = false;
                isRouter2Selected = false;
                isRouter3selected=true;
                updateRouterBackground();
            }
        });
    }

    private void updateRouterBackground() {
        customerToggleRouter1.setBackground(isRouter1Selected ? selectedBackgroundColor : defaultBackgroundColor);
        customerToggleRouter2.setBackground(isRouter2Selected ? selectedBackgroundColor : defaultBackgroundColor);
        customerToggleRouter3.setBackground(isRouter3selected ? selectedBackgroundColor : defaultBackgroundColor);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jName = new javax.swing.JLabel();
        jRole = new javax.swing.JLabel();
        jAvatar = new javax.swing.JLabel();
        customerToggleRouter1 = new com.mycompany.CustomerLayout.CustomerToggleRouter();
        customerToggleRouter2 = new com.mycompany.CustomerLayout.CustomerToggleRouter();
        customerToggleRouter3 = new com.mycompany.CustomerLayout.CustomerToggleRouter();

        jPanel1.setBackground(new java.awt.Color(253, 164, 129));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoVietpro.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("VietPro");

        jPanel2.setOpaque(false);

        jName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jName.setForeground(new java.awt.Color(255, 255, 255));
        jName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jName.setText("Name");

        jRole.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jRole.setForeground(new java.awt.Color(255, 255, 255));
        jRole.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jRole.setText("role");

        jAvatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-user-30.png"))); // NOI18N
        jAvatar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jName, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(jRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRole, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
            .addComponent(jAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(293, 293, 293)
                .addComponent(customerToggleRouter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customerToggleRouter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customerToggleRouter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(customerToggleRouter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(customerToggleRouter3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerToggleRouter2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.CustomerLayout.CustomerToggleRouter customerToggleRouter1;
    private com.mycompany.CustomerLayout.CustomerToggleRouter customerToggleRouter2;
    private com.mycompany.CustomerLayout.CustomerToggleRouter customerToggleRouter3;
    private javax.swing.JLabel jAvatar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JLabel jRole;
    // End of variables declaration//GEN-END:variables
}
