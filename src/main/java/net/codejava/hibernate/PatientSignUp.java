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
public class PatientSignUp extends javax.swing.JFrame {

    /**
     * Creates new form PatientSignIn
     */
    
    private ClinicManagerEM manager;
    private String taxCode;
    
    public PatientSignUp(ClinicManagerEM m) {
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TFName = new javax.swing.JTextField();
        TFSurname = new javax.swing.JTextField();
        TFTaxcode = new javax.swing.JTextField();
        TFBirth = new javax.swing.JTextField();
        TFSex = new javax.swing.JTextField();
        TFCity = new javax.swing.JTextField();
        TFMail = new javax.swing.JTextField();
        ButtonSave = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PFPass = new javax.swing.JPasswordField();

        setTitle("Sign Up");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Name");

        jLabel2.setText("Surname");

        jLabel3.setText("Tax code");

        jLabel4.setText("Birth date");

        jLabel5.setText("Sex");

        jLabel7.setText("Email");

        TFSurname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFSurnameActionPerformed(evt);
            }
        });

        TFTaxcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFTaxcodeActionPerformed(evt);
            }
        });

        ButtonSave.setText("Save");
        ButtonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonSaveMouseClicked(evt);
            }
        });
        ButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSaveActionPerformed(evt);
            }
        });

        jLabel8.setText("City");

        jLabel6.setText("Password");

        PFPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PFPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(ButtonSave)
                .addGap(145, 145, 145))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFTaxcode, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TFSurname, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TFName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PFPass)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFSex)
                            .addComponent(TFCity)
                            .addComponent(TFMail)
                            .addComponent(TFBirth))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TFSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TFTaxcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(PFPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TFBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TFSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(ButtonSave)
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TFSurnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFSurnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFSurnameActionPerformed

    private void TFTaxcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFTaxcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFTaxcodeActionPerformed

    private void ButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonSaveActionPerformed

    private void ButtonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonSaveMouseClicked
        // TODO add your handling code here:
        String name, surname, sex, city, birth, mail, taxcode, pass;
        name = TFName.getText();
        surname = TFSurname.getText();
        sex = TFSex.getText();
        city = TFCity.getText();
        birth = TFBirth.getText();
        mail = TFMail.getText();
        taxcode = TFTaxcode.getText();
        pass = PFPass.getText();
        
        if(name.equals(""))
            name = null;
        if(surname.equals(""))
            surname = null;
        if(sex.equals(""))
            sex = null;
        if(city.equals(""))
            city = null;
        if(birth.equals(""))
            birth = null;
        if(mail.equals(""))
            mail = null;
        if(taxcode.equals(""))
            taxcode = null;
        if(pass.equals(""))
            pass = null;
        
        if(TFName.isEnabled()) 
            manager.createPatient(name, surname, sex, city, birth, mail, taxcode, pass);
        else 
            manager.updatePatientInfo(taxcode, city, mail, pass);
       
        setVisible(false);
    }//GEN-LAST:event_ButtonSaveMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        if(!TFName.isEnabled()) {
            Patient p = manager.readPatient(taxCode);
            TFName.setText(p.getName());
            TFSurname.setText(p.getSurname());
            TFTaxcode.setText(taxCode);
            TFBirth.setText(p.getBirthDate());
            TFSex.setText(p.getSex());
            TFCity.setText(p.getCity());
            TFMail.setText(p.getEmail());
        } 
    }//GEN-LAST:event_formWindowOpened

    private void PFPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PFPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PFPassActionPerformed

    void disable_textFields() {
        TFName.setEnabled(false);
        TFSurname.setEnabled(false);
        TFTaxcode.setEnabled(false);
        TFBirth.setEnabled(false);
        TFSex.setEnabled(false);
    }
    
    void set_taxcode(String t) {
        taxCode = t;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonSave;
    private javax.swing.JPasswordField PFPass;
    private javax.swing.JTextField TFBirth;
    private javax.swing.JTextField TFCity;
    private javax.swing.JTextField TFMail;
    private javax.swing.JTextField TFName;
    private javax.swing.JTextField TFSex;
    private javax.swing.JTextField TFSurname;
    private javax.swing.JTextField TFTaxcode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
