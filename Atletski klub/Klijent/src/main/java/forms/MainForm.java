/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import FormClan.FormNoviClan;
import FormClan.FormPretragaClana;
import FormTermin.FormPretragaTermina;
import controller.ClientController;
import domen.Administrator;
import domen.Clan;
import domen.Sala;
import domen.StavkaTermina;
import domen.Termin;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import models.TableModelStavkeTermina;
import session.Session;

/**
 * Glavna forma klijentskog programa. Omogucuje unos novog termina, a preko menija pristu formama
 * za: pregled clanova (izmenu, dodavanje, brisanje), pregled termina(izmnu, brisanje) i odjavljivanje
 * sa sistema.
 *
 * @author Mihailo
 */
public class MainForm extends javax.swing.JFrame {
    
    Administrator ulogovani;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        setLocationRelativeTo(null);
        this.ulogovani = Session.getInstance().getUlogovani();
        lblUlogovani.setText("Ulogovani administrator: " + ulogovani);
        setTitle("Klijentska forma");
        popuniSale();
        popuniClanove();
        tblStavkeTermina.setModel(new TableModelStavkeTermina());
        postaviIkonice();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbSala = new javax.swing.JComboBox();
        txtNazivTermina = new javax.swing.JTextField();
        txtDatumVreme = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOpis = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtMax = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbClan = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNapomena = new javax.swing.JTextArea();
        btnObrisi = new javax.swing.JButton();
        btnDodaj = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblStavkeTermina = new javax.swing.JTable();
        btnSacuvaj = new javax.swing.JButton();
        lblUlogovani = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        miNoviClan = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        miPretragaClana = new javax.swing.JMenuItem();
        miPretragaTermina = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        miOdjava = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Unos termina"));
        jPanel1.setToolTipText("");

        jLabel1.setText("Naziv termina: ");

        jLabel2.setText("Sala: ");

        jLabel3.setText("Datum i vreme: ");

        txtNazivTermina.setText("Termin 1");

        txtDatumVreme.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm"))));
        txtDatumVreme.setText("10.12.2022 20:00");

        jLabel4.setText("Opis termina: ");

        txtOpis.setColumns(20);
        txtOpis.setRows(5);
        txtOpis.setText("Odlican!\n");
        jScrollPane1.setViewportView(txtOpis);

        jLabel5.setText("Max broj clanova: ");

        txtMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Unos stavki termina"));

        jLabel6.setText("Clan: ");

        jLabel7.setText("Napomena: ");

        txtNapomena.setColumns(20);
        txtNapomena.setRows(5);
        jScrollPane2.setViewportView(txtNapomena);

        btnObrisi.setText("Obrisi clana");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnDodaj.setText("Dodaj clana");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        tblStavkeTermina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblStavkeTermina);

        btnSacuvaj.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSacuvaj.setText("Sacuvaj termin");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbClan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane3))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(btnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbClan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnObrisi))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDodaj)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSacuvaj)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbSala, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNazivTermina)
                            .addComponent(txtDatumVreme)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                            .addComponent(txtMax))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNazivTermina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDatumVreme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblUlogovani.setText("Ulogovani:");

        miNoviClan.setText("Clan");

        jMenuItem2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mihailo\\Desktop\\projekat napredno prog\\Atletski klub\\Klijent\\src\\main\\java\\ikonice\\novo.png")); // NOI18N
        jMenuItem2.setText("Novi clan");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        miNoviClan.add(jMenuItem2);

        miPretragaClana.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mihailo\\Desktop\\projekat napredno prog\\Atletski klub\\Klijent\\src\\main\\java\\ikonice\\pretraga.png")); // NOI18N
        miPretragaClana.setText("Pretraga clana");
        miPretragaClana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPretragaClanaActionPerformed(evt);
            }
        });
        miNoviClan.add(miPretragaClana);

        jMenuBar1.add(miNoviClan);

        miPretragaTermina.setText("Termin");

        jMenuItem4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mihailo\\Desktop\\projekat napredno prog\\Atletski klub\\Klijent\\src\\main\\java\\ikonice\\pretraga.png")); // NOI18N
        jMenuItem4.setText("Pretraga termina");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        miPretragaTermina.add(jMenuItem4);

        jMenuBar1.add(miPretragaTermina);

        miOdjava.setText(" Odjava sa sistema");

        jMenuItem1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mihailo\\Desktop\\projekat napredno prog\\Atletski klub\\Klijent\\src\\main\\java\\ikonice\\logout.png")); // NOI18N
        jMenuItem1.setText("Odjavi se");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        miOdjava.add(jMenuItem1);

        jMenuBar1.add(miOdjava);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(lblUlogovani)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblUlogovani)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da "
                + "se odjavite sa sistema?", "Konfirmacija", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.NO_OPTION) {
            return;
        }

        if (result == JOptionPane.YES_OPTION) {
            new LoginForma().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        int row = tblStavkeTermina.getSelectedRow();

        if (row != -1) {
            TableModelStavkeTermina tm = (TableModelStavkeTermina) tblStavkeTermina.getModel();
            tm.obrisiStavku(row);

            if (tm.getLista().isEmpty()) {
                txtMax.setEnabled(true);
            }

        }
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed
        
        if (txtMax.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Morate prvo uneti maximalan broj clanova!");
            return;
        }

        Clan c = (Clan) cmbClan.getSelectedItem();
        String napomena = txtNapomena.getText();

        if (napomena.isEmpty()) {
            napomena = "/";
        }

        StavkaTermina st = new StavkaTermina(null, 1, napomena, c);

        TableModelStavkeTermina tm = (TableModelStavkeTermina) tblStavkeTermina.getModel();

        if (tm.postojiClan(c)) {
            JOptionPane.showMessageDialog(this, "Vec ste uneli tog clana za ovaj termin!");
            return;
        }

        int maxClanova = Integer.parseInt(txtMax.getText());

        if (tm.getLista().size() == maxClanova) {
            JOptionPane.showMessageDialog(this, "Ne mozete uneti vise od naznacenog broja clanova!");
            return;
        }

        tm.dodajStavke(st);

        if (!tm.getLista().isEmpty()) {
            txtMax.setEnabled(false);
        }
    }//GEN-LAST:event_btnDodajActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new FormNoviClan(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void miPretragaClanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPretragaClanaActionPerformed
        new FormPretragaClana(this, true).setVisible(true);
    }//GEN-LAST:event_miPretragaClanaActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        (new FormPretragaTermina(this, true)).setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
        
        if (txtNazivTermina.getText().isEmpty() || txtDatumVreme.getText().isEmpty()
                || txtMax.getText().isEmpty() || txtOpis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Morate popuniti sva polja!");
            return;
        }

        try {
            Sala s = (Sala) cmbSala.getSelectedItem();
            String nazivTermina = txtNazivTermina.getText();

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date datumVreme = sdf.parse(txtDatumVreme.getText());

            String opisTermina = txtOpis.getText();
            int maxClanova = Integer.parseInt(txtMax.getText());

            TableModelStavkeTermina tm = (TableModelStavkeTermina) tblStavkeTermina.getModel();

            Termin t = new Termin(null, nazivTermina, datumVreme, opisTermina,
                    maxClanova, s, ulogovani, tm.getLista());

            ClientController.getInstance().addTermin(t);
            resetujFormu();
            JOptionPane.showMessageDialog(this, "Uspesno sacuvan termin.");

        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSacuvajActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JComboBox cmbClan;
    private javax.swing.JComboBox cmbSala;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblUlogovani;
    private javax.swing.JMenu miNoviClan;
    private javax.swing.JMenu miOdjava;
    private javax.swing.JMenuItem miPretragaClana;
    private javax.swing.JMenu miPretragaTermina;
    private javax.swing.JTable tblStavkeTermina;
    private javax.swing.JFormattedTextField txtDatumVreme;
    private javax.swing.JFormattedTextField txtMax;
    private javax.swing.JTextArea txtNapomena;
    private javax.swing.JTextField txtNazivTermina;
    private javax.swing.JTextArea txtOpis;
    // End of variables declaration//GEN-END:variables
    
    /**
     * poziva bazu da vrati se sale i popunjava njima cmbSala
     */
    private void popuniSale() {
        try {
            ArrayList<Sala> sale = ClientController.getInstance().getAllSala();

            cmbSala.removeAllItems();

            for (Sala sala : sale) {
                cmbSala.addItem(sala);
            }

        } catch (Exception ex) {
            Logger.getLogger(FormNoviClan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * poziva bazu da vrati sve clanove i njima popunja cmbClanovi
     */
    public void popuniClanove() {
         try {
            ArrayList<Clan> clanovi = ClientController.getInstance().getAllClan();

            cmbClan.removeAllItems();

            for (Clan clan : clanovi) {
                cmbClan.addItem(clan);
            }

        } catch (Exception ex) {
            Logger.getLogger(FormNoviClan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void resetujFormu() {
        txtNazivTermina.setText("");
        txtDatumVreme.setText("");
        txtMax.setText("");
        txtOpis.setText("");
        TableModelStavkeTermina tm = (TableModelStavkeTermina) tblStavkeTermina.getModel();
        tm.getLista().clear();
        tm.fireTableDataChanged();
    }

    private void postaviIkonice() {
        ImageIcon novo = new ImageIcon("novo.png");
        miNoviClan.setIcon(novo);
        
        
//        ImageIcon pretraga = new ImageIcon("/ikonice/pretraga.png");
//        miPretragaTermina.setIcon(pretraga);
//        miPretragaTermina.setIcon(pretraga);
//        
//        ImageIcon odjava = new ImageIcon("/ikonice/logout.png");
//        miOdjava.setIcon(odjava);
 
    }
}
