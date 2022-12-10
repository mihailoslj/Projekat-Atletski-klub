/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Klasa koja predstavlja salu u kojoj se odrzavaju treninzi(termini).
 * Nasledjuje apstraktnu klasu AbstractDomainObject
 * i implementira njene metode.
 *
 * @author Mihailo
 */
public class Sala extends AbstractDomainObject{
    private Long salaID;
    private String nazivSale;
    
    /**
     * Vraca naziv sale
     * @return String nazivSale
     * @throws java.lang.NullPointerException ako je nazivSale null
     */
    @Override
    public String toString() {
        if(nazivSale == null){
            throw new NullPointerException("Naziv sale ne sme biti null");
        }
        return nazivSale;
    }

    public Sala(Long salaID, String nazivSale) {
        setSalaID(salaID);
        setNazivSale(nazivSale);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
    /**
     * Poredi dva objekta Sala i utvrdjuje da li su isti
     * @param obj
     * @return 
     * <ul>
     *      <li>true ako su oba objekta klase Sala i imaju isti salaID
     *      <li>false u svakom drugom slucaju
     * </ul>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sala other = (Sala) obj;
        return Objects.equals(this.salaID, other.salaID);
    }

    public Sala() {
    }
    
    /**
     * @return vraca naziv tabele u bazi za salu da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String nazivTabele() {
        return " Sala ";
    }
    /**
     * 
     * @return vraca alijas tabele u bazi za salu da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String alijas() {
        return " s ";
    }
    /**
     * 
     * @return vraca prazan string jer klasa Sala ne vuce nijednu drugu klasu 
     */
    @Override
    public String join() {
        return "";
    }
    
    /**
     * Prima ResultSet(tabelu) i pretvara je u listu objekata Sala i vraca je
     * @param rs
     * @return lista objekata Sala
     * @throws SQLException ako dodje do greske prilikom izvrsavanja upita nad bazom
     */
    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Sala s = new Sala(rs.getLong("SalaID"), rs.getString("nazivSale"));

            lista.add(s);
        }

        rs.close();
        return lista;
    }
    /**
     * @return vraca prazan string jer nemam sistemsku operaciju za unos sale 
     */
    @Override
    public String koloneZaInsert() {
        return "";
    }
    
    /**
     * @return Vraca vrednost za primarni kljuc radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je salaID null
     * @throws java.lang.RuntimeException ako je salaID manji od 1
     */
    @Override
    public String vrednostZaPrimarniKljuc() {
        if(salaID == null){
            throw new NullPointerException("Vrednost salaID za primarni kljuc ne sme biti null");
        }
        if(salaID < 1){
            throw new RuntimeException("Vrednost salaID za primarni kljuc ne sme biti "
                    + "manja od 1");
        }
        return " SalaID = " + salaID;
    }
    
    /**
     * @return vraca prazan string jer nemam sistemsku operaciju za insert sale 
     */
    @Override
    public String vrednostiZaInsert() {
        return "";
    }
    /**
     * @return vraca prazan string jer nemam sistemsku operaciju za update sale 
     */
    @Override
    public String vrednostiZaUpdate() {
        return "";
    }
    /**
     * @return vraca prazan string jer nemam sistemsku operaciju koja bi zahtevala uslov pri
     * formiranju upita u bazi
     */
    @Override
    public String uslov() {
        return "";
    }
    /**
     * Vraca vrednost salaID
     * @return Long salaID
     */
    public Long getSalaID() {
        return salaID;
    }
    
    //ne uvodim logicku kontrolu jer ce uvek biti null zbog autoinceremnt-a
    /**
     * Postavalja vrednost atributa salaID
     * @param salaID 
     */
    public void setSalaID(Long salaID) {
        this.salaID = salaID;
    }
    /**
     * Vraca vrednost nazivSale
     * @return String nazivSale
     */
    public String getNazivSale() {
        return nazivSale;
    }
    /**
     * Postavalja vrednost nazivSale clana
     * @param nazivSale 
     * @throws java.lang.NullPointerException ako je nazivSale null
     * @throws java.lang.RuntimeException ako je nazivSale krace od 2 karaktera
     */
    public void setNazivSale(String nazivSale) {
        if(nazivSale == null){
            throw new NullPointerException("Naziv sale za ne sme biti null");
        }
        if(nazivSale.length() < 2){
            throw new RuntimeException("naziv sale salaID za ne sme biti "
                    + "manja od 2 karaktera");
        }
        this.nazivSale = nazivSale;
    }
}
