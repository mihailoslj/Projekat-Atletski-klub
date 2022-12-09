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
 *
 * @author Mihailo
 */
public class TableModelTermini extends AbstractTableModel implements Runnable{
    
    private ArrayList<Termin> lista;
    private String[] kolone = {"ID", "Naziv termina", "Datum i vreme", "Max clanova", "Sala"};
    private String parametar = "";

    public TableModelTermini() {
        try {
            lista = ClientController.getInstance().getAllTermin();
        } catch (Exception ex) {
            Logger.getLogger(TableModelTermini.class.getName()).log(Level.SEVERE, null, ex);
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

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

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
