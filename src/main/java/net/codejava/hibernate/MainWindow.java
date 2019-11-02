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
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    
    private ClinicManagerEM jpaManager;
    private levelDBManager ldbManager;
    
    public MainWindow(ClinicManagerEM m, levelDBManager l) {
        initComponents();
        jpaManager = m;
        ldbManager = l;
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
        ButtonSIgnup = new javax.swing.JButton();
        ButtonLoginPat = new javax.swing.JButton();
        ButtonLoginDoc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clinic Examinations System");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Clinic Examinations Management System");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/patient_icon.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/doctor_icon.png"))); // NOI18N

        ButtonSIgnup.setText("Sign up");
        ButtonSIgnup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonSIgnupMouseClicked(evt);
            }
        });

        ButtonLoginPat.setText("Login");
        ButtonLoginPat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonLoginPatMouseClicked(evt);
            }
        });

        ButtonLoginDoc.setText("Login");
        ButtonLoginDoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonLoginDocMouseClicked(evt);
            }
        });
        ButtonLoginDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLoginDocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(69, 69, 69))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(ButtonLoginDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ButtonLoginPat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonSIgnup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(83, 83, 83))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonSIgnup)
                    .addComponent(ButtonLoginDoc))
                .addGap(27, 27, 27)
                .addComponent(ButtonLoginPat)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        jpaManager.exit();
        ldbManager.close();
    }//GEN-LAST:event_formWindowClosed

    private void ButtonLoginDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLoginDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonLoginDocActionPerformed

    private void ButtonSIgnupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonSIgnupMouseClicked
        new PatientSignUp(jpaManager, ldbManager).setVisible(true);
    }//GEN-LAST:event_ButtonSIgnupMouseClicked

    private void ButtonLoginPatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonLoginPatMouseClicked
        new LoginPatient(jpaManager, ldbManager, this).setVisible(true);
    }//GEN-LAST:event_ButtonLoginPatMouseClicked

    private void ButtonLoginDocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonLoginDocMouseClicked
        new LoginDoctor(jpaManager, ldbManager, this).setVisible(true);
    }//GEN-LAST:event_ButtonLoginDocMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonLoginDoc;
    private javax.swing.JButton ButtonLoginPat;
    private javax.swing.JButton ButtonSIgnup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables


    //-----------------MAIN METHOD
    public static void main(String[] args) {


            // code to run the program
            System.out.println("-----");
            System.out.println("Start");

            ClinicManagerEM jpaManager = new ClinicManagerEM();
            jpaManager.setup();

            System.out.println("-----");

            levelDBManager ldbManager = new levelDBManager();
            ldbManager.init("mystore");

            System.out.println("Current= doc:"+ldbManager.getDoctorId()+"\tex:"+ldbManager.getExaminationId()+"\tpat:"+ldbManager.getPatientId());

            //create patients
            jpaManager.createPatient("pietro", "ducange", "female", "pisa", "1980-01-23", "pietroducange@plasmon.it", "duc1", "pwd1");	
            ldbManager.putPatient("pietro", "ducange", "pietroducange@plasmon.it", "duc1");

            jpaManager.createPatient("enzo", "mingozzi", "male", "pisa", "1964-09-10", "enzomingozzi@skynet.com", "ming1", "pwd2");
            ldbManager.putPatient("enzo", "mingozzi", "enzomingozzi@skynet.com", "ming1");

            //create doctor
            jpaManager.createDoctor(1, "Jack", "The Reaper", "aaa@bb.cc", "doc1");
            ldbManager.putDoctor("Jack", "The Reaper", "aaa@bb.cc");
            jpaManager.createDoctor(2, "Lord", "Voldemort", "tom.riddle@student.hogwarts.uk", "doc2");
            ldbManager.putDoctor("Lord", "Voldemort", "tom.riddle@student.hogwarts.uk");

            ldbManager.dumpLevelDB();

            System.out.println("-----");

            System.out.println("Finished");

            MainWindow m = new MainWindow(jpaManager, ldbManager);
            m.setVisible(true);

    }
      
}