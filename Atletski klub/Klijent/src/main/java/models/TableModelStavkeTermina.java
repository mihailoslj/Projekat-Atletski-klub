/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


import controller.ClientController;
import domen.Clan;
import domen.StavkaTermina;
import domen.Termin;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mihailo
 */
public class TableModelStavkeTermina extends AbstractTableModel{
     private ArrayList<StavkaTermina> lista;
    private String[] kolone = {"Rb", "Clan", "Napomena"};
    private int rb = 0;

    public TableModelStavkeTermina() {
        lista = new ArrayList<>();
    }

    public TableModelStavkeTermina(Termin t) {
        try {
            lista = ClientController.getInstance().getAllStavkaTermina(t);
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkeTermina.class.getName()).log(Level.SEVERE, null, ex);
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
        StavkaTermina st = lista.get(row);

        switch (column) {
            case 0:
                return st.getRbStavke();
            case 1:
                return st.getClan().getImeClana() + " " + st.getClan().getPrezimeClana();
            case 2:
                return st.getNapomena();

            default:
                return null;
        }
    }

    public void dodajStavke(StavkaTermina st) {
        rb = lista.size();
        st.setRbStavke(++rb);
        lista.add(st);
        fireTableDataChanged();
    }

    public boolean postojiClan(Clan c) {
        for (StavkaTermina stavkaTermina : lista) {
            if(stavkaTermina.getClan().getClanID().equals(c.getClanID())){
                return true;
            }
        }
        return false;
    }

    public void obrisiStavku(int row) {
        lista.remove(row);
        
        rb = 0;
        for (StavkaTermina stavkaTermina : lista) {
            stavkaTermina.setRbStavke(++rb);
        }
        
        fireTableDataChanged();
    }

    public ArrayList<StavkaTermina> getLista() {
        return lista;
    }
}
