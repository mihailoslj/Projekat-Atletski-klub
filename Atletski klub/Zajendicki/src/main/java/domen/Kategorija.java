/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mihailo
 */
public class Kategorija extends AbstractDomainObject{
    private Long kategorijaID;
    private String nazivKategorije;
    private String opisKategorije;

    @Override
    public String toString() {
        return nazivKategorije;
    }

    public Kategorija(Long kategorijaID, String nazivKategorije, String opisKategorije) {
        setKategorijaID(kategorijaID);
        setNazivKategorije(nazivKategorije);
        setOpisKategorije(opisKategorije);
    }

    public Kategorija() {
    }

    @Override
    public String nazivTabele() {
        return " Kategorija ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Kategorija k = new Kategorija(rs.getLong("KategorijaID"),
                    rs.getString("NazivKategorije"), rs.getString("OpisKategorije"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        if(kategorijaID == null){
            throw new NullPointerException("kategorijaID ne sme biti null kao vredmost za primarni kljuc");
        }
        return " KategorijaID = " + kategorijaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getKategorijaID() {
        return kategorijaID;
    }

    public void setKategorijaID(Long kategorijaID) {
        if(kategorijaID == null){
            throw new NullPointerException("KategorijaId ne sme biti null");
        }
        if(kategorijaID < 1){
            throw new RuntimeException("KategorijaID ne sme biti manja od 1");
        }
        this.kategorijaID = kategorijaID;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        if(kategorijaID == null){
            throw new NullPointerException("KategorijaId ne sme biti null");
        }
        if(kategorijaID < 1){
            throw new RuntimeException("KategorijaID ne sme biti manja od 1");
        }
        this.nazivKategorije = nazivKategorije;
    }

    public String getOpisKategorije() {
        return opisKategorije;
    }

    public void setOpisKategorije(String opisKategorije) {
        if(opisKategorije == null){
            throw new NullPointerException("opisKategorije ne sme biti null");
        }
        if(opisKategorije.length() < 4){
            throw new RuntimeException("opisKategorije ne sme biti kraci od 4 karaktera");
        }
        this.opisKategorije = opisKategorije;
    }
}
