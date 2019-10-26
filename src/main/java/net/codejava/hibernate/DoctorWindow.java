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
    
    private ClinicManagerEM manager;
    
    public DoctorWindow(ClinicManagerEM m) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        TFPatient = new javax.swing.JTextField();
        ButtonInsertTest = new javax.swing.JButton();
        ButtonDocTests = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        TFDoc = new javax.swing.JTextField();
        ButtonPatientTests = new javax.swing.JButton();
        ButtonInfo = new javax.swing.JButton();
        ButtonResult = new javax.swing.JButton();

        setTitle("Doctor");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Visit ID", "Doc Name", "Doc Surname", "Doc Mail", "P Name", "P Surname", "P Taxcode", "Date", "Type", "Result"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false, false
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
        ButtonInsertTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonInsertTestActionPerformed(evt);
            }
        });

        ButtonDocTests.setText("Show doctor tests");
        ButtonDocTests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonDocTestsMouseClicked(evt);
            }
        });
        ButtonDocTests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDocTestsActionPerformed(evt);
            }
        });

        jLabel2.setText("Doctor ID");

        TFDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFDocActionPerformed(evt);
            }
        });

        ButtonPatientTests.setText("Show tests");
        ButtonPatientTests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonPatientTestsMouseClicked(evt);
            }
        });
        ButtonPatientTests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPatientTestsActionPerformed(evt);
            }
        });

        ButtonInfo.setText("Change info");
        ButtonInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonInfoMouseClicked(evt);
            }
        });
        ButtonInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonInfoActionPerformed(evt);
            }
        });

        ButtonResult.setText("Insert result");
        ButtonResult.setToolTipText("");
        ButtonResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonResultMouseClicked(evt);
            }
        });
        ButtonResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonResultActionPerformed(evt);
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
                        .addGap(18, 18, 18)
                        .addComponent(ButtonDocTests)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonInfo))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TFPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonInsertTest)
                    .addComponent(ButtonPatientTests)
                    .addComponent(ButtonResult))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonInsertTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonInsertTestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonInsertTestActionPerformed

    private void ButtonDocTestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDocTestsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDocTestsActionPerformed

    private void TFDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFDocActionPerformed

    private void ButtonDocTestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonDocTestsMouseClicked
       
        List<Examination> list = manager.readDoctorExaminations(Integer.parseInt(TFDoc.getText()));
        DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
        d.setRowCount(0);
        int num_col = 10;
        Object[] row = new Object[num_col];
        
        for(int j=0; j<list.size(); ++j) {
            Examination e = list.get(j);
            row[0] = e.getId();
            row[1] = e.getDoctor().getName();
            row[2] = e.getDoctor().getSurname();
            row[3] = e.getDoctor().getEmail();
            row[4] = e.getPatient().getName();
            row[5] = e.getPatient().getSurname();
            row[6] = e.getPatient().getTaxCode();
            row[7] = e.getDate();
            row[8] = e.getType();
            row[9] = e.getResult();
            d.addRow(row);
        }
        
    }//GEN-LAST:event_ButtonDocTestsMouseClicked

    private void ButtonPatientTestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPatientTestsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonPatientTestsActionPerformed

    private void ButtonInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonInfoMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DoctorInfo di = new DoctorInfo(manager);
                di.set_id(Integer.parseInt(TFDoc.getText()));
                di.setVisible(true);
      
            }
        });
    }//GEN-LAST:event_ButtonInfoMouseClicked

    private void ButtonInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonInfoActionPerformed

    private void ButtonInsertTestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonInsertTestMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date d = new Date();
                InsertVisit iv = new InsertVisit(manager, TFDoc.getText(), TFPatient.getText(), sdf.format(d));
                iv.setVisible(true);
            }
        });
    }//GEN-LAST:event_ButtonInsertTestMouseClicked

    private void ButtonPatientTestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonPatientTestsMouseClicked
        // TODO add your handling code here:
        List<Examination> list = manager.readPatientExaminations(TFPatient.getText());
        DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
        d.setRowCount(0);
        int num_col = 10;
        Object[] row = new Object[num_col];
        
        for(int j=0; j<list.size(); ++j) {
            Examination e = list.get(j);
            row[0] = e.getId();
            row[1] = e.getDoctor().getName();
            row[2] = e.getDoctor().getSurname();
            row[3] = e.getDoctor().getEmail();
            row[4] = e.getPatient().getName();
            row[5] = e.getPatient().getSurname();
            row[6] = e.getPatient().getTaxCode();
            row[7] = e.getDate();
            row[8] = e.getType();
            row[9] = e.getResult();
            d.addRow(row);
        }
    }//GEN-LAST:event_ButtonPatientTestsMouseClicked

    private void ButtonResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonResultActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonResultActionPerformed

    private void ButtonResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonResultMouseClicked
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InsertResult ir = new InsertResult(manager);
                ir.setVisible(true);
            }
        });
    }//GEN-LAST:event_ButtonResultMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonDocTests;
    private javax.swing.JButton ButtonInfo;
    private javax.swing.JButton ButtonInsertTest;
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
