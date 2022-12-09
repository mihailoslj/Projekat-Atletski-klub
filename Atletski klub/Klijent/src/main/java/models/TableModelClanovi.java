/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import javax.swing.table.AbstractTableModel;
import controller.ClientController;
import domen.Clan;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Model tabele Clanovi. Prikazuje sve clanove iz baze. Nasledjuje apstraktnu klasu
 * AbstractTableModel i implementira njene metode. Koristi se u formi 'FormPretragaClana'.
 *
 * @author Mihailo
 */
public class TableModelClanovi extends AbstractTableModel implements Runnable{
    
    private ArrayList<Clan> lista;
    /**nazivi kolona tabele*/
    private String[] kolone = {"ID", "Kategorija", "Ime", "Prezime", "Email", "Telefon"};
    /** parametar za pretragu clanova*/
    private String parametar = "";

    public TableModelClanovi() {
        try {
            lista = ClientController.getInstance().getAllClan();
        } catch (Exception ex) {
            Logger.getLogger(TableModelClanovi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**vraca broj clanova*/
    @Override
    public int getRowCount() {
        return lista.size();
    }
    
    /** braca broj kolona(atributa)*/
    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }
    
    /**postavlja vrednosti u tabeli*/
    @Override
    public Object getValueAt(int row, int column) {
        Clan c = lista.get(row);

        switch (column) {
            case 0:
                return c.getClanID();
            case 1:
                return c.getKategorija();
            case 2:
                return c.getImeClana();
            case 3:
                return c.getPrezimeClana();
            case 4:
                return c.getEmail();
            case 5:
                return c.getTelefon();

            default:
                return null;
        }
    }
    
    /**
     * @param row
     * @return listu clanova iz tabele
     */
    public Clan getSelectedClan(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelClanovi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * postavlja vrednost parametra
     * @param parametar 
     */
    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }
    
    /**
     * osvezava i popunjava tabelu na svakih 10 sekundi vrseci poziv ka bazi da se vrate svi clanovi
     */
    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllClan();
            if (!parametar.equals("")) {
                ArrayList<Clan> novaLista = new ArrayList<>();
                for (Clan c : lista) {
                    if (c.getImeClana().toLowerCase().contains(parametar.toLowerCase())
                            || c.getPrezimeClana().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(c);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * @return listu clanova iz baze
     */
    public ArrayList<Clan> getLista() {
        return lista;
    }
    
}
