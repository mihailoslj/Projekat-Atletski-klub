/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domen.Termin;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 * Model tabele Termina. Prikazuje sve termine iz baze. Nasledjuje apstraktnu klasu
 * AbstractTableModel i implementira njene metode. Koristi se u formami 'FormDetaljiTermina'.
 *
 * @author Mihailo
 */
public class TableModelTermini extends AbstractTableModel implements Runnable{
    
    private ArrayList<Termin> lista;
    /**nazivi kolona tabele*/
    private String[] kolone = {"ID", "Naziv termina", "Datum i vreme", "Max clanova", "Sala"};
    /** parametar za pretragu clanova*/
    private String parametar = "";

    public TableModelTermini() {
        try {
            lista = ClientController.getInstance().getAllTermin();
        } catch (Exception ex) {
            Logger.getLogger(TableModelTermini.class.getName()).log(Level.SEVERE, null, ex);
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
        Termin t = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        switch (column) {
            case 0:
                return t.getTerminID();
            case 1:
                return t.getNazivTermina();
            case 2:
                return sdf.format(t.getDatumVreme());
            case 3:
                return t.getMaxClanova();
            case 4:
                return t.getSala();

            default:
                return null;
        }
    }
    
    /** vraca selektovan termin iz tabele */
    public Termin getSelectedTermin(int row) {
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
            Logger.getLogger(TableModelTermini.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Postavlja vrednost parametra
     * @param parametar 
     */
    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }
    
    /**
     * osvezava listu (odnosno tabelu) na svakih 10 sekundi tako sto vrsi poziv ka bazi da vrati 
     * sve termine
     */
    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllTermin();
            if (!parametar.equals("")) {
                ArrayList<Termin> novaLista = new ArrayList<>();
                for (Termin t : lista) {
                    if (t.getNazivTermina().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(t);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
