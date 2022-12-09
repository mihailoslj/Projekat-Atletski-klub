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
 * Model tabele Stavki termin. Prikazuje sve stavke termina za odredjeni termin iz baze. Nasledjuje apstraktnu klasu
 * AbstractTableModel i implementira njene metode. Koristi se u formama: 'MainForm' i 
 * 'FormDetaljiTermina'.
 *
 * @author Mihailo
 */
public class TableModelStavkeTermina extends AbstractTableModel{
    private ArrayList<StavkaTermina> lista;
     /**nazivi kolona tabele*/
    private String[] kolone = {"Rb", "Clan", "Napomena"};
    /** redni broj stavke */
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
    /**
     * dodaje stavku u listu, odnosno tablu i postavlja joj redni broj
     * @param st 
     */
    public void dodajStavke(StavkaTermina st) {
        rb = lista.size();
        st.setRbStavke(++rb);
        lista.add(st);
        fireTableDataChanged();
    }
    
    /**
     * proverava da li se prosledjeni clan vec nalazi u tabeli (da li vec pripada terminu)
     * @param c
     * @return 
     * <ul>
     *  <li>true ako clan postoji vec u tabeli</li>
     *  <li>false u suprotnom</li>
     * </ul>
     */
    public boolean postojiClan(Clan c) {
        for (StavkaTermina stavkaTermina : lista) {
            if(stavkaTermina.getClan().getClanID().equals(c.getClanID())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Brisa stavku iz liste(tabele)
     * @param row 
     */
    public void obrisiStavku(int row) {
        lista.remove(row);
        
        rb = 0;
        for (StavkaTermina stavkaTermina : lista) {
            stavkaTermina.setRbStavke(++rb);
        }
        
        fireTableDataChanged();
    }
    
    /**
     * @return vraca listu stavki, odnosno sadrzaj tabele
     */
    public ArrayList<StavkaTermina> getLista() {
        return lista;
    }
}
