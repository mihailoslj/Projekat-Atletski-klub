/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package FormClan;

import controller.ClientController;
import domen.Clan;
import domen.Kategorija;
import forms.MainForm;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Mihailo
 */
public class FormDetaljiClana extends javax.swing.JDialog {
    
    private Clan c;
    /**
     * Creates new form FormDetaljiClana
     */
    public FormDetaljiClana(JDialog parent, boolean modal, Clan c) {
        super(parent, modal);
        initComponents();
        this.c = c;
        setLocationRelativeTo(null);
        txtIme.setText(c.getImeClana());
        txtPrezime.setText(c.getPrezimeClana());
        txtEmail.setText(c.getEmail());
        txtTelefon.setText(c.getTelefon());
        popuniKategorije();
        cmbKategorija.getModel().setSelectedItem(c.getKategorija());
        setTitle("Detalji clana");
        txtIme.setEditable(false);
        txtPrezime.setEditable(false);
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
        cmbKategorija = new javax.swing.JComboBox();
        txtIme = new javax.swing.JTextField();
        txtPrezime = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtTelefon = new javax.swing.JTextField();
        btnOtkazi = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        lblUpozorenje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Kategorija: ");

        jLabel2.setText("Ime: ");

        jLabel3.setText("Prezime: ");

        jLabel4.setText("Email: ");

        jLabel5.setText("Telefon");

        txtIme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtImeMousePressed(evt);
            }
        });
        txtIme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImeActionPerformed(evt);
            }
        });

        txtPrezime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPrezimeMousePressed(evt);
            }
        });
        txtPrezime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrezimeActionPerformed(evt);
            }
        });

        btnOtkazi.setText("Otkazi");
        btnOtkazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOtkaziActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obrisi clana");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnIzmeni.setText("Izmeni clana");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        lblUpozorenje.setBackground(new java.awt.Color(255, 0, 0));
        lblUpozorenje.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbKategorija, 0, 317, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIme))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrezime))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefon)
                            .addComponent(lblUpozorenje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOtkazi, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbKategorija, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUpozorenje, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOtkazi)
                    .addComponent(btnObrisi)
                    .addComponent(btnIzmeni))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOtkaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOtkaziActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnOtkaziActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da "
                + "obrisete ovog clana?", "Konfirmacija", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.NO_OPTION) {
            return;
        }

        if (result == JOptionPane.YES_OPTION) {
            try {
                ClientController.getInstance().deleteClan(c);
                FormPretragaClana fp = (FormPretragaClana) getParent();
                fp.refreshTable();
                fp.popuniClanove();
                JOptionPane.showMessageDialog(this, "Uspesno obrisan clan.");
                
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ne mozete da obrisete ovog clana!");
//                Logger.getLogger(FormDetaljiClana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        
        if (txtEmail.getText().isEmpty() || txtTelefon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena!");
            return;
        }

        String email = txtEmail.getText();
        String telefon = txtTelefon.getText();
        Kategorija kategorija = (Kategorija) cmbKategorija.getSelectedItem();

        c.setEmail(email);
        c.setTelefon(telefon);
        c.setKategorija(kategorija);

        try {
            ClientController.getInstance().updateClan(c);
            FormPretragaClana fp = (FormPretragaClana) getParent();
            fp.refreshTable();
            fp.popuniClanove();
            JOptionPane.showMessageDialog(this, "Uspesno izmenjen clan.");
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(FormDetaljiClana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void txtImeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImeActionPerformed
    }//GEN-LAST:event_txtImeActionPerformed

    private void txtPrezimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrezimeActionPerformed
        
    }//GEN-LAST:event_txtPrezimeActionPerformed

    private void txtImeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtImeMousePressed
        lblUpozorenje.setText("Dozvoljeno je menjati email i telefon!");
        lblUpozorenje.setForeground(Color.RED);
    }//GEN-LAST:event_txtImeMousePressed

    private void txtPrezimeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPrezimeMousePressed
        lblUpozorenje.setText("Dozvoljeno je menjati email i telefon!");
        lblUpozorenje.setForeground(Color.RED);
    }//GEN-LAST:event_txtPrezimeMousePressed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnOtkazi;
    private javax.swing.JComboBox cmbKategorija;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblUpozorenje;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtPrezime;
    private javax.swing.JTextField txtTelefon;
    // End of variables declaration//GEN-END:variables

    private void popuniKategorije() {
        
        try {
            ArrayList<Kategorija> kategorije = ClientController.getInstance().getAllKategorija();

            cmbKategorija.removeAllItems();

            for (Kategorija kategorija : kategorije) {
                cmbKategorija.addItem(kategorija);
            }

        } catch (Exception ex) {
            Logger.getLogger(FormNoviClan.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
