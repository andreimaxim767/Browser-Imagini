/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicatie;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrei
 */
public class BrowserPoze extends javax.swing.JFrame {
    private List<File> poze = new ArrayList<File>();
    private int pozaCurenta;
    /**
     * Creates new form BrowserPoze
     */
    public BrowserPoze() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fc = new javax.swing.JFileChooser();
        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        l = new javax.swing.JLabel();
        bPrevious = new javax.swing.JButton();
        bNext = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miOpen = new javax.swing.JMenuItem();
        miLoad = new javax.swing.JMenuItem();
        miSave = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miDetalii = new javax.swing.JMenuItem();

        fc.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        l.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l.setText("No picture yet");
        jScrollPane1.setViewportView(l);

        bPrevious.setText("Previous");
        bPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPreviousActionPerformed(evt);
            }
        });

        bNext.setText("Next");
        bNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNextActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        miOpen.setText("Open dir...");
        miOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miOpenActionPerformed(evt);
            }
        });
        jMenu1.add(miOpen);

        miLoad.setText("Load file list...");
        miLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLoadActionPerformed(evt);
            }
        });
        jMenu1.add(miLoad);

        miSave.setText("Save file list...");
        miSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSaveActionPerformed(evt);
            }
        });
        jMenu1.add(miSave);
        jMenu1.add(jSeparator1);

        miExit.setText("Exit");
        jMenu1.add(miExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("View");

        miDetalii.setText("Detalii fisiere...");
        jMenu2.add(miDetalii);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bPrevious)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                .addComponent(bNext)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bPrevious)
                    .addComponent(bNext))
                .addGap(12, 12, 12))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void eroare(String err) {
        JOptionPane.showMessageDialog(
                this,
                err,
                "ERROR",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void miOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miOpenActionPerformed
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File dir = fc.getSelectedFile();
            if (!dir.isDirectory()) {
                eroare("Not a valid directory!");
            } else if (!dir.canRead() || !dir.canExecute()) {
                eroare("No permissions on the specified dir!");
            } else {
                FileFilter filtruJPG = new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.getName().toLowerCase().endsWith(".jpg");
                    }

                };
                File[] fisiere = dir.listFiles(filtruJPG);
                if (fisiere.length == 0) {
                    eroare("No pictures in the selected dir");
                }else{
                    poze = Arrays.asList(fisiere);
                    pozaCurenta = 0;
                    afiseazaPozaCurenta();
                }
            }
        }
    }//GEN-LAST:event_miOpenActionPerformed

    private void bNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNextActionPerformed
        pozaCurenta = ++pozaCurenta%poze.size();
        afiseazaPozaCurenta();
    }//GEN-LAST:event_bNextActionPerformed

    private void bPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPreviousActionPerformed
        pozaCurenta = pozaCurenta==0?poze.size()-1:pozaCurenta-1;
        afiseazaPozaCurenta();
    }//GEN-LAST:event_bPreviousActionPerformed

    private void miSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSaveActionPerformed
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            try {
                File f = fc.getSelectedFile();
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(poze);
                oos.close();
                fos.close();
            } catch (IOException ex) {
                eroare("Error saving data");
            }
        }
    }//GEN-LAST:event_miSaveActionPerformed

    private void miLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLoadActionPerformed
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            try {
                File f = fc.getSelectedFile();
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                poze = (List<File>)ois.readObject();
                ois.close();
                fis.close();
                if(poze.size()>0){
                    pozaCurenta=0;
                    afiseazaPozaCurenta();
                }
            } catch (Exception ex) {
                eroare("Error loading file");
            }
        }
    }//GEN-LAST:event_miLoadActionPerformed

    private void afiseazaPozaCurenta(){
        File f = poze.get(pozaCurenta);
        ImageIcon i = new ImageIcon(f.getAbsolutePath());
        l.setIcon(i);
        l.setText("");
    }
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
            java.util.logging.Logger.getLogger(BrowserPoze.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BrowserPoze.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BrowserPoze.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BrowserPoze.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BrowserPoze().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bNext;
    private javax.swing.JButton bPrevious;
    private javax.swing.JFileChooser fc;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel l;
    private javax.swing.JMenuItem miDetalii;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miLoad;
    private javax.swing.JMenuItem miOpen;
    private javax.swing.JMenuItem miSave;
    // End of variables declaration//GEN-END:variables
}
