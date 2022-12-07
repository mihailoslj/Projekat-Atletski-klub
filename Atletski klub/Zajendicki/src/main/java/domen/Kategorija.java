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
 * Klasa koja predstavlja kategoriju kojoj pripada jedan clan u okviru atletskog kluba.
 * Nasledjuje apstraktnu klasu AbstractDomainObject
 * i implementira njene metode.
 *
 * @author Mihailo
 */
public class Kategorija extends AbstractDomainObject{
    private Long kategorijaID;
    private String nazivKategorije;
    private String opisKategorije;
    
    /**
     * Vraca naziv kategorije
     * @return String nazivKategorije
     * @throws java.lang.NullPointerException ako je nazivKategorije null
     */
    @Override
    public String toString() {
        if(nazivKategorije == null){
            throw new NullPointerException("naziv kategorije ne sme biti null");
        }
        return nazivKategorije;
    }

    public Kategorija(Long kategorijaID, String nazivKategorije, String opisKategorije) {
        setKategorijaID(kategorijaID);
        setNazivKategorije(nazivKategorije);
        setOpisKategorije(opisKategorije);
    }

    public Kategorija() {
    }
    /**
     * @return vraca naziv tabele u bazi za kategoriju da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String nazivTabele() {
        return " Kategorija ";
    }
    /**
     * @return vraca alijas tabele u bazi za kategoriju da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String alijas() {
        return " k ";
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    /**
     * Poredi dva objekta Kategorija i utvrdjuje da li su isti
     * @param obj
     * @return 
     * <ul>
     *      <li>true ako su oba objekta klase Kategorija i imaju isti naziv
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
        final Kategorija other = (Kategorija) obj;
        return Objects.equals(this.nazivKategorije, other.nazivKategorije);
    }


    /**
     * 
     * @return vraca prazan string jer klasa Kategorija ne vuce nijednu drugu klasu 
     */
    @Override
    public String join() {
        return "";
    }
    /**
     * Prima ResultSet(tabelu) i pretvara je u listu objekata Kategorija i vraca je
     * @param rs
     * @return lista objekata Kategorija
     * @throws SQLException ako dodje do greske prilikom izvrsavanja upita nad bazom
     */
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
    
    /**
     * 
     * @return vraca prazan string jer nemam sistemsku operaciju za unos kategorije 
     */
    @Override
    public String koloneZaInsert() {
        return "";
    }
    /**
     * @return Vraca vrednost za primarni kljuc radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je kategorijaID null
     * @throws java.lang.RuntimeException ako je kategorijaID manji od 1
     */
    @Override
    public String vrednostZaPrimarniKljuc() {
        if(kategorijaID == null){
            throw new NullPointerException("kategorijaID ne sme biti null kao vredmost za primarni kljuc");
        }
        if(kategorijaID < 1){
            throw new RuntimeException("KategorijaID ne sme biti manja od 1");
        }
        return " KategorijaID = " + kategorijaID;
    }
    
    /**
     * 
     * @return vraca prazan string jer nemam sistemsku operaciju za insert kategorije 
     */
    @Override
    public String vrednostiZaInsert() {
        return "";
    }
    /**
     * @return vraca prazan string jer nemam sistemsku operaciju za update kategorije 
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
     * Vraca vrednost kategorijaID
     * @return Long kategorijaID
     */
    public Long getKategorijaID() {
        return kategorijaID;
    }
    //ne uvodim logicku kontrolu jer ce uvek biti null zbog autoinceremnt-a
    /**
     * Postavalja vrednost atributa kategorijID
     * @param kategorijaID 
     */
    public void setKategorijaID(Long kategorijaID) {
        
        this.kategorijaID = kategorijaID;
    }
    
    /**
     * Vraca vrednost nazivKategorije
     * @return String naziv Kategorije
     */
    public String getNazivKategorije() {
        return nazivKategorije;
    }
    /**
     * Postavalja vrednost nazivKategorije 
     * @param nazivKategorije 
     * @throws java.lang.NullPointerException ako je nazivKategorije null
     * @throws java.lang.RuntimeException ako je nazivKategorije krace od 2 karaktera
     */
    public void setNazivKategorije(String nazivKategorije) {
        if(nazivKategorije == null){
            throw new NullPointerException("KategorijaId ne sme biti null");
        }
        if(nazivKategorije.length() < 2){
            throw new RuntimeException("Naziv kategorije ne sme biti kraci od 2 karaktera");
        }
        this.nazivKategorije = nazivKategorije;
    }
    /**
     * Vraca vrednost opisKategorije
     * @return String opisKategorije
     */
    public String getOpisKategorije() {
        return opisKategorije;
    }
    /**
     * Postavalja vrednost opisKategorije 
     * @param opisKategorije 
     * @throws java.lang.NullPointerException ako je opisKategorije null
     * @throws java.lang.RuntimeException ako je opisKategorije krace od 2 karaktera
     */
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
