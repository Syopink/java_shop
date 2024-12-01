/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.components.util;

import Database.ActionCate;
import java.awt.Color;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author An Ninh
 */
public class rowCate extends javax.swing.JPanel {

    /**
     * Creates new form rowCate
     */

    private JTextField updateField ;
    private ActionCate ac=new ActionCate();
    private int id;
    private String text;

    public rowCate() {
        initComponents();
        
    }
    
    public JToggleButton deleteBtn(){
        return jToggleButton2;
    }
    
    
    public String Update(){
        return text;
    }
    
    public void set(int id,String text){
    jPanel2.setSize(707,44);
    this.id=id;
    this.text=text;
    jLabel1.setText(String.valueOf(id));
    jLabel2.setText(text);

    }
    
public void updateText() {
    // Thêm ItemListener cho jToggleButton1
    jToggleButton1.addItemListener((ItemEvent e) -> {
            
        // Kiểm tra trạng thái của toggle button
        if (jToggleButton1.isSelected()) {
            // Nếu jToggleButton1 được chọn (isSelected = true)
            updateField = new JTextField();
            updateField.setSize(596, 52);
            // Xóa jLabel2 và thêm JTextField vào jPanel3
            jPanel3.remove(jLabel2); // Xóa jLabel2
            jPanel3.add(updateField); // Thêm JTextField vào
            updateField.setText(this.text);
            // Cập nhật lại trạng thái của jToggleButton1
            jToggleButton1.setBackground(Color.GREEN);
            jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-approved-50.png")));

        } else {
            this.text = updateField.getText(); // Lấy nội dung từ JTextField
            jLabel2.setText(text);
            ac.updateCate(id, text);
            System.out.println(text);
            // Xóa JTextField và thêm lại jLabel2 vào jPanel3
            jPanel3.remove(updateField); // Xóa JTextField
            jPanel3.add(jLabel2); // Thêm jLabel2 lại

            // Cập nhật lại trạng thái của jToggleButton1
            jToggleButton1.setBackground(Color.CYAN);
            jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-pencil-24.png")));
            
        }

        // Cập nhật lại giao diện sau khi thay đổi
        jPanel3.revalidate(); 
        jPanel3.repaint();
    });
}
public void deleteCate(Runnable onSuccessCallback) {
    jToggleButton2.addActionListener(e -> {
        int idlb1 = Integer.valueOf(jLabel1.getText());
        String result = ac.DeleteCate(idlb1);
        javax.swing.JOptionPane.showMessageDialog(null, result);
        onSuccessCallback.run();  
    });
}

    // Phương thức thiết lập callback để thông báo khi xóa thành công
   

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 51, 51));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 50));

        jToggleButton1.setBackground(new java.awt.Color(0, 255, 255));
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-pencil-24.png"))); // NOI18N
        jToggleButton1.setBorder(null);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 48, 50));

        jToggleButton2.setBackground(new java.awt.Color(255, 0, 0));
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-trash-24.png"))); // NOI18N
        jToggleButton2.setBorder(null);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 50, 50));

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 150, 50));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 70));
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
       
        
        
        
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
}
