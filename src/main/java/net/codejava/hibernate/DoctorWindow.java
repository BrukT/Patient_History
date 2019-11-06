package net.codejava.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nicom
 */
public class DoctorWindow extends javax.swing.JFrame {

    /**
     * Creates new form DoctorWindow
     */
    
    private ClinicManagerEM jpaManager;
    private levelDBManager ldbManager;
    private String id;
    
    public DoctorWindow(ClinicManagerEM m, levelDBManager l, String i) {
        initComponents();
        jpaManager = m;
        ldbManager = l;
        id = i;       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        TFPatient = new javax.swing.JTextField();
        ButtonInsertTest = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        TFDoc = new javax.swing.JTextField();
        ButtonPatientTests = new javax.swing.JButton();
        ButtonInfo = new javax.swing.JButton();
        ButtonResult = new javax.swing.JButton();
        ButtonLogout = new javax.swing.JButton();
        ButtonDocTests = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Doctor");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Visit ID", "Doc Name", "Doc Surname", "Doc Mail", "P Name", "P Surname", "P Taxcode", "P Mail", "Date", "Type", "Result"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Patient tax code");

        ButtonInsertTest.setText("Insert test");
        ButtonInsertTest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonInsertTestMouseClicked(evt);
            }
        });

        jLabel2.setText("Doctor ID");

        TFDoc.setEnabled(false);

        ButtonPatientTests.setText("Show tests");
        ButtonPatientTests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonPatientTestsMouseClicked(evt);
            }
        });

        ButtonInfo.setText("Change info");
        ButtonInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonInfoMouseClicked(evt);
            }
        });

        ButtonResult.setText("Insert result");
        ButtonResult.setToolTipText("");
        ButtonResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonResultMouseClicked(evt);
            }
        });

        ButtonLogout.setText("Logout");
        ButtonLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonLogoutMouseClicked(evt);
            }
        });

        ButtonDocTests.setText("Show my tests");
        ButtonDocTests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonDocTestsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(TFDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(ButtonInfo)
                        .addGap(45, 45, 45)
                        .addComponent(ButtonDocTests))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TFPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ButtonInsertTest)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonPatientTests)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonResult)))
                .addContainerGap(223, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonLogout)
                .addGap(202, 202, 202))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TFDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonInfo)
                    .addComponent(ButtonDocTests))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TFPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonInsertTest)
                    .addComponent(ButtonPatientTests)
                    .addComponent(ButtonResult))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonLogout)
                .addGap(7, 7, 7))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonInfoMouseClicked
        DoctorInfo di = new DoctorInfo(jpaManager, ldbManager, Integer.parseInt(TFDoc.getText()), this);
        di.setVisible(true);
    }//GEN-LAST:event_ButtonInfoMouseClicked

    private void ButtonInsertTestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonInsertTestMouseClicked
        Patient p = jpaManager.readPatient(TFPatient.getText());
        if(p == null) {
            new ErrorWindow("Error: insert a correct tax code!").setVisible(true);
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date d = new Date();
            InsertVisit iv = new InsertVisit(jpaManager, ldbManager, TFDoc.getText(), TFPatient.getText(), sdf.format(d), this);
            iv.setVisible(true);
        }
    }//GEN-LAST:event_ButtonInsertTestMouseClicked

    private void ButtonPatientTestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonPatientTestsMouseClicked
        // TODO add your handling code here:
        Patient p = jpaManager.readPatient(TFPatient.getText());
        if(p == null) {
            new ErrorWindow("Error: insert a correct tax code!").setVisible(true);
        }
        else {
            //List<Examination> list = jpaManager.readPatientExaminations(TFPatient.getText());
            List<Examination> list = ldbManager.readPatientExamination(TFPatient.getText());
            DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
            d.setRowCount(0);
            int num_col = 11;
            Object[] row = new Object[num_col];

            for(int j=0; j<list.size(); ++j) {
                Examination e = list.get(j);
                row[0] = e.getId();
                row[1] = e.getDoctor().getName();
                row[2] = e.getDoctor().getSurname();
                row[3] = e.getDoctor().getEmail();
                row[4] = e.getPatient().getName();
                row[5] = e.getPatient().getSurname();
                row[6] = e.getPatient().getPatientId();
                row[7] = e.getPatient().getEmail();
                row[8] = e.getDate();
                row[9] = e.getType();
                row[10] = e.getResult();
                d.addRow(row);
            }
        }
    }//GEN-LAST:event_ButtonPatientTestsMouseClicked

    private void ButtonResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonResultMouseClicked
        // TODO add your handling code here:
        InsertResult ir = new InsertResult(jpaManager, ldbManager, this, Integer.parseInt(TFDoc.getText()));
        ir.setVisible(true);
    }//GEN-LAST:event_ButtonResultMouseClicked

    private void ButtonLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonLogoutMouseClicked
        new MainWindow(jpaManager, ldbManager).setVisible(true);
        this.setVisible(false);      
    }//GEN-LAST:event_ButtonLogoutMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        TFDoc.setText(id);
        
        //List<Examination> list = jpaManager.readDoctorExaminations(Integer.parseInt(id));
        List<Examination> list = ldbManager.readDoctorExamination(Integer.parseInt(TFDoc.getText()));
        DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
        d.setRowCount(0);
        int num_col = 11;
        Object[] row = new Object[num_col];
        
        for(int j=0; j<list.size(); ++j) {
            Examination e = list.get(j);
            row[0] = e.getId();
            row[1] = e.getDoctor().getName();
            row[2] = e.getDoctor().getSurname();
            row[3] = e.getDoctor().getEmail();
            row[4] = e.getPatient().getName();
            row[5] = e.getPatient().getSurname();
            row[6] = e.getPatient().getPatientId();
            row[7] = e.getPatient().getEmail();
            row[8] = e.getDate();
            row[9] = e.getType();
            row[10] = e.getResult();
            d.addRow(row);
        }
    }//GEN-LAST:event_formWindowOpened

    private void ButtonDocTestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonDocTestsMouseClicked
        // TODO add your handling code here:
        
        //List<Examination> list = jpaManager.readDoctorExaminations(Integer.parseInt(id));
        List<Examination> list = ldbManager.readDoctorExamination(Integer.parseInt(TFDoc.getText()));
        DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
        d.setRowCount(0);
        int num_col = 11;
        Object[] row = new Object[num_col];
        
        for(int j=0; j<list.size(); ++j) {
            Examination e = list.get(j);
            row[0] = e.getId();
            row[1] = e.getDoctor().getName();
            row[2] = e.getDoctor().getSurname();
            row[3] = e.getDoctor().getEmail();
            row[4] = e.getPatient().getName();
            row[5] = e.getPatient().getSurname();
            row[6] = e.getPatient().getPatientId();
            row[7] = e.getPatient().getEmail();
            row[8] = e.getDate();
            row[9] = e.getType();
            row[10] = e.getResult();
            d.addRow(row);
        }
    }//GEN-LAST:event_ButtonDocTestsMouseClicked

    public void updateTable(String id) {
        //List<Examination> list = jpaManager.readDoctorExaminations(Integer.parseInt(id));
        List<Examination> list = ldbManager.readDoctorExamination(Integer.parseInt(id));
        DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
        d.setRowCount(0);
        int num_col = 11;
        Object[] row = new Object[num_col];
        
        for(int j=0; j<list.size(); ++j) {
            Examination e = list.get(j);
            row[0] = e.getId();
            row[1] = e.getDoctor().getName();
            row[2] = e.getDoctor().getSurname();
            row[3] = e.getDoctor().getEmail();
            row[4] = e.getPatient().getName();
            row[5] = e.getPatient().getSurname();
            row[6] = e.getPatient().getPatientId();
            row[7] = e.getPatient().getEmail();
            row[8] = e.getDate();
            row[9] = e.getType();
            row[10] = e.getResult();
            d.addRow(row);
        } 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonDocTests;
    private javax.swing.JButton ButtonInfo;
    private javax.swing.JButton ButtonInsertTest;
    private javax.swing.JButton ButtonLogout;
    private javax.swing.JButton ButtonPatientTests;
    private javax.swing.JButton ButtonResult;
    private javax.swing.JTextField TFDoc;
    private javax.swing.JTextField TFPatient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
