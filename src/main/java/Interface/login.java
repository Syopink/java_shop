/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interface;

import Process.customers;
import Validate.UserDTO;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Process.user;
import com.mycompany.vietpro.CustomerLayout;
import com.mycompany.vietpro.Homepage;

/**
 *
 * @author Nguyen Gia Huy
 */
public class login extends javax.swing.JFrame {
    customers cs = new customers();
    private user us;
    /**
     * Creates new form menu
     */
    public login() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null); // Hiển thị giữa màn hình
        
        this.jLHidePassword.setVisible(false);
        setTextFieldLimit(jtxtCapcha, 6);
    }
    
    private void setTextFieldLimit(javax.swing.JTextField textField, int maxLength) {
    textField.setDocument(new javax.swing.text.PlainDocument() {
        @Override
        public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
            if (str != null && (getLength() + str.length() <= maxLength)) {
                super.insertString(offs, str, a);
            }
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

        bg = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtEmail = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jP = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jbtnLogin = new javax.swing.JButton();
        jCbox = new javax.swing.JCheckBox();
        jtxtRegister = new javax.swing.JLabel();
        jLShow = new javax.swing.JLabel();
        jLHidePassword = new javax.swing.JLabel();
        forgetPassword = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtxtCapcha = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        capcha = new Library.Capcha();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banner-6.png"))); // NOI18N
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 380, 380));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Đăng nhập");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Email");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jtxtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));
        bg.add(jtxtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 270, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 270, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Mật khẩu");
        bg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 270, -1));

        jP.setBorder(null);
        bg.add(jP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 240, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_icon2.png"))); // NOI18N
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jbtnLogin.setBackground(new java.awt.Color(0, 0, 0));
        jbtnLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jbtnLogin.setForeground(new java.awt.Color(255, 255, 255));
        jbtnLogin.setText("Đăng nhập");
        jbtnLogin.setToolTipText("");
        jbtnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoginActionPerformed(evt);
            }
        });
        bg.add(jbtnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 325, -1, -1));

        jCbox.setText("<html>Tôi đồng ý với</b>\n");
        bg.add(jCbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jtxtRegister.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/register_button.png"))); // NOI18N
        jtxtRegister.setText("Đăng ký");
        jtxtRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtxtRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtRegisterMouseClicked(evt);
            }
        });
        bg.add(jtxtRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 325, 90, 30));

        jLShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eye.png"))); // NOI18N
        jLShow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLShowMousePressed(evt);
            }
        });
        bg.add(jLShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, -1, -1));

        jLHidePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        jLHidePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLHidePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLHidePasswordMousePressed(evt);
            }
        });
        bg.add(jLHidePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, -1, -1));

        forgetPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        forgetPassword.setText("Quên mật khẩu?");
        forgetPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forgetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgetPasswordMouseClicked(evt);
            }
        });
        bg.add(forgetPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 362, 100, -1));

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mã xác nhận");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jtxtCapcha.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));
        bg.add(jtxtCapcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 120, 30));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));
        bg.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 120, -1));

        jLabel8.setText("<html><b>Điều khoản người dùng</b></html>");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bg.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 302, -1, -1));
        bg.add(capcha, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, -1, -1));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLoginActionPerformed
        // TODO add your handling code here:
        if (!jCbox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Bạn cần đồng ý với điều khoản người dùng để tiếp tục.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
                UserDTO user = new UserDTO();

        user.setEmail(jtxtEmail.getText());
        user.setPassword(new String(jP.getPassword()));

        if (user.getEmail().isBlank()) {
            JOptionPane.showMessageDialog(this, "Email không được để trống.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (user.getPassword().isBlank()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String enteredCaptcha = jtxtCapcha.getText(); // Lấy giá trị người dùng nhập
        String actualCaptcha = capcha.getCaptchaField(); // Lấy CAPTCHA hiện tại từ logic

        // Kiểm tra capcha
        if (!enteredCaptcha.equals(actualCaptcha)) {
        JOptionPane.showMessageDialog(this, "Mã CAPTCHA không đúng.", "Lỗi", JOptionPane.WARNING_MESSAGE);
        capcha.resetCaptcha();
        return;
        }
        try {
            String role = cs.login(user.getEmail(), user.getPassword());
            switch (role) {
                case "admin" -> {
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
                    us = cs.findUserByEmail(user.getEmail());
                    Homepage homepageFrame = new Homepage(us);  // Pass the email here
                    homepageFrame.setVisible(true);
                    this.dispose(); // Close the login frame
                    // chuyển giao diện admin
                }
                case "customer" -> {
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
                    us = cs.findUserByEmail(user.getEmail());
                    CustomerLayout customerFrame = new CustomerLayout(us);
                    customerFrame.setVisible(true);
                    this.dispose(); // Close the login frame
                }
                default -> {
                    JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu.", "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
                    capcha.resetCaptcha(); // Reset CAPTCHA khi người dùng nhập sai
                }

            }
            // chuyển giao diện người dùng
                    } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi kết nối tới cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                capcha.resetCaptcha(); // Reset CAPTCHA khi người dùng nhập sai

        }
    }//GEN-LAST:event_jbtnLoginActionPerformed

    private void jtxtRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtRegisterMouseClicked
        // TODO add your handling code here:
        register regFrame = new register();
        regFrame.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jtxtRegisterMouseClicked

    private void jLShowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLShowMousePressed
        // TODO add your handling code here:
        jLShow.setVisible(false);
        jLHidePassword.setVisible(true);
        jP.setEchoChar((char) 0);
    }//GEN-LAST:event_jLShowMousePressed

    private void jLHidePasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLHidePasswordMousePressed
        // TODO add your handling code here:
        // TODO add your handling code here:
        jLShow.setVisible(true);
        jLHidePassword.setVisible(false);
        jP.setEchoChar('*');
    }//GEN-LAST:event_jLHidePasswordMousePressed

    private void forgetPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetPasswordMouseClicked
        // TODO add your handling code here:
         forgetPassword forgetPasswordFrame = new forgetPassword();
        forgetPasswordFrame.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_forgetPasswordMouseClicked

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private Library.Capcha capcha;
    private javax.swing.JLabel forgetPassword;
    private javax.swing.JCheckBox jCbox;
    private javax.swing.JLabel jLHidePassword;
    private javax.swing.JLabel jLShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField jP;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton jbtnLogin;
    private javax.swing.JTextField jtxtCapcha;
    private javax.swing.JTextField jtxtEmail;
    private javax.swing.JLabel jtxtRegister;
    // End of variables declaration//GEN-END:variables
}
