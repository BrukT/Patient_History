package net.codejava.hibernate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nicom
 */
public class LoginPatient extends javax.swing.JFrame {

    /**
     * Creates new form LoginPatient
     */
    private ClinicManagerEM manager;
    
    public LoginPatient(ClinicManagerEM m) {
        initComponents();
        manager = m;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TFTaxcode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ButtonLogin = new javax.swing.JButton();
        PFPass = new javax.swing.JPasswordField();

        setTitle("Patient Login");

        jLabel1.setText("Tax code");

        TFTaxcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFTaxcodeActionPerformed(evt);
            }
        });

        jLabel2.setText("Password");

        ButtonLogin.setText("Login");
        ButtonLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonLoginMouseClicked(evt);
            }
        });
        ButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLoginActionPerformed(evt);
            }
        });

        PFPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PFPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TFTaxcode)
                            .addComponent(PFPass, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(ButtonLogin)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TFTaxcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(PFPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(ButtonLogin)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonLoginActionPerformed

    private void TFTaxcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFTaxcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFTaxcodeActionPerformed

    private void PFPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PFPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PFPassActionPerformed

    private void ButtonLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonLoginMouseClicked
        // TODO add your handling code here:
        if(manager.loginPatient(TFTaxcode.getText(), PFPass.getText())) {
            new PatientWindow(manager, TFTaxcode.getText()).setVisible(true);
            setVisible(false);
        }
    }//GEN-LAST:event_ButtonLoginMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonLogin;
    private javax.swing.JPasswordField PFPass;
    private javax.swing.JTextField TFTaxcode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
