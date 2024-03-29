/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.hibernate;

import java.util.List;

/**
 *
 * @author nicom
 */
public class InsertResult extends javax.swing.JFrame {

    /**
     * Creates new form InsertResult
     */
    private ClinicManagerEM jpaManager;
    private levelDBManager ldbManager;
    private DoctorWindow docWind;
    private int docid;
    
    public InsertResult(ClinicManagerEM m, levelDBManager l, DoctorWindow dw, int d) {
        initComponents();
        jpaManager = m;
        ldbManager = l;
        docWind = dw;
        docid = d;
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
        TFVisit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        CBResult = new javax.swing.JComboBox<>();
        ButtonSave = new javax.swing.JButton();

        setTitle("Insert result");
        setResizable(false);

        jLabel1.setText("Visit ID");

        jLabel2.setText("Result");

        CBResult.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "not available", "negative", "positive" }));

        ButtonSave.setText("Save");
        ButtonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonSaveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CBResult, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TFVisit)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(ButtonSave)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFVisit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CBResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(ButtonSave)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonSaveMouseClicked
        // TODO add your handling code here:
      
        boolean check = false;
        List<Examination> list = ldbManager.readDoctorExamination(docid);
        for(int i=0; i<list.size(); ++i) {
            String id = Integer.toString(list.get(i).getId());
            if(id.equals(TFVisit.getText())) {
                check = true;
                break;
            }         
        }
        if(!check) {
            new ErrorWindow("Error: insert a correct visit ID.").setVisible(true);
        }
        else {
            ldbManager.updateExamination(Integer.parseInt(TFVisit.getText()), CBResult.getSelectedItem().toString());
            //jpaManager.updateExamination(Integer.parseInt(TFVisit.getText()), CBResult.getSelectedItem().toString()); //TO COMMENT
            docWind.updateTable(Integer.toString(docid));
            setVisible(false);
            ldbManager.dumpLevelDB();
        }
    }//GEN-LAST:event_ButtonSaveMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonSave;
    private javax.swing.JComboBox<String> CBResult;
    private javax.swing.JTextField TFVisit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
