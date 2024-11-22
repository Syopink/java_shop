/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interface;

import Process.customers;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Validate.Validate;
import Library.Library;
import Process.sendMail;
import javax.mail.MessagingException;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

/**
 *
 * @author Nguyen Gia Huy
 */
public class forgetPassword extends javax.swing.JFrame {

    private String verificationCode;
    customers cs = new customers();
    Validate validate = new Validate();
    Library lib = new Library();
    sendMail sm = new sendMail();

    /**
     * Creates new form forgetPassword
     */
    public forgetPassword() {
        initComponents();
        setResizable(false);
        this.jLHideConfirmPassword.setVisible(false);
        this.jLHideNewPassword.setVisible(false);
        setLocationRelativeTo(null); // Hiển thị giữa màn hình
        initLimitText();
    }

    public void initLimitText() {
        setTextFieldLimit(txt1);
        setTextFieldLimit(txt2);
        setTextFieldLimit(txt3);
        setTextFieldLimit(txt4);
    }

    private void setTextFieldLimit(javax.swing.JTextField textField) {
        textField.setDocument(new javax.swing.text.PlainDocument() {
            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
                if (str != null) {
                    // Kiểm tra chuỗi chỉ chứa số
                    if (str.matches("\\d+") && (getLength() + str.length() <= 1)) {
                        super.insertString(offs, str, a);
                    }
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

        jLabel7 = new javax.swing.JLabel();
        parentPanel = new javax.swing.JPanel();
        B1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtEmail = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jbtnContinue = new javax.swing.JButton();
        jLReturnB1 = new javax.swing.JLabel();
        B2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jlResend = new javax.swing.JLabel();
        txt4 = new javax.swing.JTextField();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt3 = new javax.swing.JTextField();
        jbtnContinueStepTwo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLReturnB2 = new javax.swing.JLabel();
        B3 = new javax.swing.JPanel();
        jLHideNewPassword = new javax.swing.JLabel();
        jLShowNewPassword = new javax.swing.JLabel();
        jLHideConfirmPassword = new javax.swing.JLabel();
        jLShowConfirmPassword = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jbtnUpdateNewPassword = new javax.swing.JButton();
        jtxtConfirmPassword = new javax.swing.JPasswordField();
        jtxtNewPassword = new javax.swing.JPasswordField();
        B4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jbtnUpdateSuccess = new javax.swing.JButton();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        parentPanel.setBackground(new java.awt.Color(255, 255, 255));
        parentPanel.setLayout(new java.awt.CardLayout());

        B1.setBackground(new java.awt.Color(255, 255, 255));
        B1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_icon2.png"))); // NOI18N
        B1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Quên mật khẩu");
        B1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Email");
        B1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jtxtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));
        B1.add(jtxtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 310, 30));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));
        B1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 305, -1));

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("<html>Nhập email của bạn để tiếp tục quá trình xác minh, chúng tôi sẽ </br>gửi mã 4 chữ số đến email của bạn.</html>");
        B1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 310, -1));

        jbtnContinue.setBackground(new java.awt.Color(0, 0, 0));
        jbtnContinue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jbtnContinue.setForeground(new java.awt.Color(255, 255, 255));
        jbtnContinue.setText("Tiếp tục");
        jbtnContinue.setToolTipText("");
        jbtnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnContinueActionPerformed(evt);
            }
        });
        B1.add(jbtnContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 305, -1));

        jLReturnB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/return.png"))); // NOI18N
        jLReturnB1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLReturnB1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLReturnB1MouseClicked(evt);
            }
        });
        B1.add(jLReturnB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        parentPanel.add(B1, "card9");

        B2.setBackground(new java.awt.Color(255, 255, 255));
        B2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Xác thực");
        B2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jlResend.setForeground(new java.awt.Color(102, 102, 102));
        jlResend.setText("<html> <span style=\"color:red; cursor:pointer;\">Gửi lại</span></html>\n");
        jlResend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlResend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlResendMouseClicked(evt);
            }
        });
        B2.add(jlResend, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 40, -1));

        txt4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt4.setPreferredSize(new java.awt.Dimension(50, 50));
        txt4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt4KeyReleased(evt);
            }
        });
        B2.add(txt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        txt1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt1.setPreferredSize(new java.awt.Dimension(50, 50));
        txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt1KeyReleased(evt);
            }
        });
        B2.add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        txt2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt2.setPreferredSize(new java.awt.Dimension(50, 50));
        txt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt2KeyReleased(evt);
            }
        });
        B2.add(txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 110, -1, -1));

        txt3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt3.setPreferredSize(new java.awt.Dimension(50, 50));
        txt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt3KeyReleased(evt);
            }
        });
        B2.add(txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));

        jbtnContinueStepTwo.setBackground(new java.awt.Color(0, 0, 0));
        jbtnContinueStepTwo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jbtnContinueStepTwo.setForeground(new java.awt.Color(255, 255, 255));
        jbtnContinueStepTwo.setText("Tiếp tục");
        jbtnContinueStepTwo.setToolTipText("");
        jbtnContinueStepTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnContinueStepTwoActionPerformed(evt);
            }
        });
        B2.add(jbtnContinueStepTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 290, -1));

        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("<html>Nhập 4 mã chữ số đã gửi đến email của bạn.</html>");
        B2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 75, 260, -1));

        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("<html>Nếu bạn chưa nhận được mã!</html>\n");
        B2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 170, -1));

        jLReturnB2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/return.png"))); // NOI18N
        jLReturnB2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLReturnB2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLReturnB2MouseClicked(evt);
            }
        });
        B2.add(jLReturnB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        parentPanel.add(B2, "card3");

        B3.setBackground(new java.awt.Color(255, 255, 255));
        B3.setPreferredSize(new java.awt.Dimension(125, 125));
        B3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLHideNewPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        jLHideNewPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLHideNewPasswordMousePressed(evt);
            }
        });
        B3.add(jLHideNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, -1));

        jLShowNewPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eye.png"))); // NOI18N
        jLShowNewPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLShowNewPasswordMousePressed(evt);
            }
        });
        B3.add(jLShowNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, -1));

        jLHideConfirmPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        jLHideConfirmPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLHideConfirmPasswordMousePressed(evt);
            }
        });
        B3.add(jLHideConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        jLShowConfirmPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eye.png"))); // NOI18N
        jLShowConfirmPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLShowConfirmPasswordMousePressed(evt);
            }
        });
        B3.add(jLShowConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("Đổi mật khẩu");
        B3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, -1, -1));

        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("<html>Đặt mật khẩu mới cho tài khoản của bạn để bạn có thể đăng nhập</br> và truy cập tất cả các tính năng. </html>");
        B3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 310, -1));

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Nhập mật khẩu mới");
        B3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));
        B3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 305, -1));

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 0, 0));
        B3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 305, -1));

        jLabel13.setBackground(new java.awt.Color(204, 204, 204));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Xác nhận mật khẩu");
        B3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jbtnUpdateNewPassword.setBackground(new java.awt.Color(0, 0, 0));
        jbtnUpdateNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jbtnUpdateNewPassword.setForeground(new java.awt.Color(255, 255, 255));
        jbtnUpdateNewPassword.setText("Cập nhật ");
        jbtnUpdateNewPassword.setToolTipText("");
        jbtnUpdateNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateNewPasswordActionPerformed(evt);
            }
        });
        B3.add(jbtnUpdateNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 235, 305, -1));

        jtxtConfirmPassword.setBorder(null);
        B3.add(jtxtConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 310, 30));

        jtxtNewPassword.setBorder(null);
        B3.add(jtxtNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 310, 30));

        parentPanel.add(B3, "card4");

        B4.setBackground(new java.awt.Color(255, 255, 255));
        B4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checked (1).png"))); // NOI18N
        B4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 280, 130));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Thành công");
        B4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 300, -1));

        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("<html>Mật khẩu của bạn đã được thay đổi thành công. </html>");
        B4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 300, -1));

        jbtnUpdateSuccess.setBackground(new java.awt.Color(0, 0, 0));
        jbtnUpdateSuccess.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jbtnUpdateSuccess.setForeground(new java.awt.Color(255, 255, 255));
        jbtnUpdateSuccess.setText("Xác nhận");
        jbtnUpdateSuccess.setToolTipText("");
        jbtnUpdateSuccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateSuccessActionPerformed(evt);
            }
        });
        B4.add(jbtnUpdateSuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 305, -1));

        parentPanel.add(B4, "card5");

        getContentPane().add(parentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 280));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased
        // TODO add your handling code here:
        if (!txt1.getText().isEmpty()) {
            txt2.requestFocus();
        }
    }//GEN-LAST:event_txt1KeyReleased

    private void txt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyReleased
        // TODO add your handling code here:
        if (!txt2.getText().isEmpty()) {
            txt3.requestFocus();
        }
    }//GEN-LAST:event_txt2KeyReleased

    private void txt3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt3KeyReleased
        // TODO add your handling code here:
        if (!txt3.getText().isEmpty()) {
            txt4.requestFocus();
        }
    }//GEN-LAST:event_txt3KeyReleased

    private void txt4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt4KeyReleased
        // TODO add your handling code here:
        if (!txt4.getText().isEmpty()) {
            jbtnContinueStepTwo.requestFocus();
        }
    }//GEN-LAST:event_txt4KeyReleased

    private void jbtnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnContinueActionPerformed
        // Xử lý khi nhấn "Tiếp tục" trong bước 1
        String email = jtxtEmail.getText().trim();
        if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "Email không được bỏ trống.");
        } else if (!validate.isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ.");
        } else {
            try {
                if (!cs.isEmailExists(email)) {
                    JOptionPane.showMessageDialog(this, "Email không tồn tại.");
                } else {
                    verificationCode = lib.generateVerificationCode(); // Mã xác minh được tạo ở đây
                    sm.sendEmail(email, verificationCode);
                    parentPanel.removeAll();
                    parentPanel.add(B2);
                    parentPanel.repaint();
                    parentPanel.revalidate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jbtnContinueActionPerformed

    private void jbtnContinueStepTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnContinueStepTwoActionPerformed
        // TODO add your handling code here:
        // Lấy giá trị từ 4 ô textfield
        String enteredCode = txt1.getText() + txt2.getText() + txt3.getText() + txt4.getText();
        System.out.println("entercode: " + enteredCode);
        System.out.println("verificode: " + verificationCode);
        // Mã xác thực được gửi qua email (sử dụng biến verificationCode đã tạo trước đó)
        if (enteredCode.equals(verificationCode)) {
            // Mã xác thực đúng, tiếp tục qua bước đổi mật khẩu
            parentPanel.removeAll();
            parentPanel.add(B3);
            parentPanel.repaint();
            parentPanel.revalidate();
        } else {
            // Mã xác thực sai
            JOptionPane.showMessageDialog(this, "Mã xác thực không đúng. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnContinueStepTwoActionPerformed

    private void jbtnUpdateNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateNewPasswordActionPerformed
        String newPassword = jtxtNewPassword.getText().trim();
        String confirmPassword = jtxtConfirmPassword.getText().trim();
        System.out.println("newpasss(): " + newPassword);
        String email = jtxtEmail.getText().trim();
        System.out.println("email(): " + email);
        if (newPassword.equals("") || confirmPassword.equals("")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được bỏ trông");
        } else if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp");
        } else {
            if (email != null) {
                try {
                    cs.updatePassword(email, newPassword); // Truyền email và mật khẩu mới
                    parentPanel.removeAll();
                    parentPanel.add(B4);
                    parentPanel.repaint();
                    parentPanel.revalidate();
                } catch (SQLException ex) {
                    ex.printStackTrace(); // Log lỗi cho developer
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi đổi mật khẩu. Vui lòng thử lại.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ.");
            }
        }
    }//GEN-LAST:event_jbtnUpdateNewPasswordActionPerformed

    private void jbtnUpdateSuccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateSuccessActionPerformed
        login logFrame = new login();
        logFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jbtnUpdateSuccessActionPerformed

    private void jlResendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlResendMouseClicked
        // TODO add your handling code here:
        String email = jtxtEmail.getText().trim();
        try {
            sm.sendEmail(email, verificationCode);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Không thể gửi email. Vui lòng kiểm tra lại kết nối.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jlResendMouseClicked

    private void togglePasswordVisibility(JLabel showLabel, JLabel hideLabel, JPasswordField passwordField, boolean isVisible) {
        showLabel.setVisible(!isVisible);
        hideLabel.setVisible(isVisible);
        passwordField.setEchoChar(isVisible ? (char) 0 : '*');
    }
    private void jLShowConfirmPasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLShowConfirmPasswordMousePressed
        togglePasswordVisibility(jLShowConfirmPassword, jLHideConfirmPassword, jtxtConfirmPassword, true);
    }//GEN-LAST:event_jLShowConfirmPasswordMousePressed

    private void jLHideConfirmPasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLHideConfirmPasswordMousePressed
        togglePasswordVisibility(jLShowConfirmPassword, jLHideConfirmPassword, jtxtConfirmPassword, false);

    }//GEN-LAST:event_jLHideConfirmPasswordMousePressed

    private void jLHideNewPasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLHideNewPasswordMousePressed
        togglePasswordVisibility(jLShowNewPassword, jLHideNewPassword, jtxtNewPassword, false);

    }//GEN-LAST:event_jLHideNewPasswordMousePressed

    private void jLShowNewPasswordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLShowNewPasswordMousePressed
        togglePasswordVisibility(jLShowNewPassword, jLHideNewPassword, jtxtNewPassword, true);

    }//GEN-LAST:event_jLShowNewPasswordMousePressed

    private void jLReturnB2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLReturnB2MouseClicked
        parentPanel.removeAll();
        parentPanel.add(B1);
        parentPanel.repaint();
        parentPanel.revalidate();
    }//GEN-LAST:event_jLReturnB2MouseClicked

    private void jLReturnB1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLReturnB1MouseClicked
        // TODO add your handling code here:
        login loginFrame = new login();
        loginFrame.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jLReturnB1MouseClicked

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(forgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(forgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(forgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(forgetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new forgetPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel B1;
    private javax.swing.JPanel B2;
    private javax.swing.JPanel B3;
    private javax.swing.JPanel B4;
    private javax.swing.JLabel jLHideConfirmPassword;
    private javax.swing.JLabel jLHideNewPassword;
    private javax.swing.JLabel jLReturnB1;
    private javax.swing.JLabel jLReturnB2;
    private javax.swing.JLabel jLShowConfirmPassword;
    private javax.swing.JLabel jLShowNewPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton jbtnContinue;
    private javax.swing.JButton jbtnContinueStepTwo;
    private javax.swing.JButton jbtnUpdateNewPassword;
    private javax.swing.JButton jbtnUpdateSuccess;
    private javax.swing.JLabel jlResend;
    private javax.swing.JPasswordField jtxtConfirmPassword;
    private javax.swing.JTextField jtxtEmail;
    private javax.swing.JPasswordField jtxtNewPassword;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    // End of variables declaration//GEN-END:variables
}
