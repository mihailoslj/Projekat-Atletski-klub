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
 *
 * @author Mihailo
 */
public class TableModelClanovi extends AbstractTableModel implements Runnable{
    
    private ArrayList<Clan> lista;
    private String[] kolone = {"ID", "Kategorija", "Ime", "Prezime", "Email", "Telefon"};
    private String parametar = "";

    public TableModelClanovi() {
        try {
            lista = ClientController.getInstance().getAllClan();
        } catch (Exception ex) {
            Logger.getLogger(TableModelClanovi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

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

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

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

    public ArrayList<Clan> getLista() {
        return lista;
    }

    public void postaviListu(ArrayList<Clan> listaClanova) {
        lista = listaClanova;
        fireTableDataChanged();
    }
    
    
}
