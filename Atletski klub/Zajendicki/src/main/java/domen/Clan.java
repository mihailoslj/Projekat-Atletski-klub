/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *  Klasa koja predstavlja Clana atletskog kluba. Nasledjuje apstraktnu klasu AbstractDomainObject
 *  i implementira njene metode.
 * @author Mihailo
 */
public class Clan extends AbstractDomainObject{
    private Long clanID;
    private String imeClana;
    private String prezimeClana;
    private String email;
    private String telefon;
    /** Kategorija kojoj pripada clan*/
    private Kategorija kategorija;

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    
    /**
     * Poredi dva objekta Clan i utvrdjuje da li su isti
     * @param obj
     * @return 
     * <ul>
     *      <li>true ako su oba objekta klase Clan i imaju isti clanID
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
        final Clan other = (Clan) obj;
        return Objects.equals(this.clanID, other.clanID);
    }
    
    /**
     * Vraca String sa svim vaznim informacijama o clanu
     * @return ime, prezime i naziv kategorije kojoj pripada clan
     * @throws java.lang.NullPointerException ako je ime, prezime, kategorija ili kategorijaID null
     */
    @Override
    public String toString() {
        if(imeClana == null || prezimeClana == null || kategorija == null
                || kategorija.getKategorijaID() == null){
            throw new NullPointerException("Nijedna od vrednosti za toString() ne sme biti null");
        }
        return imeClana + " " + prezimeClana + " (Kategorija: " + kategorija.getNazivKategorije() + ")";
    }
    
    public Clan(Long clanID, String imeClana, String prezimeClana, String email, String telefon, Kategorija kategorija) {
        setClanID(clanID);
        setImeClana(imeClana);
        setPrezimeClana(prezimeClana);
        setEmail(email);
        setTelefon(telefon);
        setKategorija(kategorija);
    }

    public Clan() {
    }
    /**
     * 
     * @return vraca naziv tabele u bazi za clana da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String nazivTabele() {
        return " Clan ";
    }
    /**
     * @return vraca alijas tabele u bazi za clana da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String alijas() {
        return " c ";
    }
    /**
     * @return vraca join upit jer Clan vuce Kategorij-u, da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String join() {
        return " JOIN KATEGORIJA K ON (K.KATEGORIJAID = C.KATEGORIJAID) ";
    }
    /**
     * Prima ResultSet(tabelu) i pretvara je u listu objekata Clan i vraca je
     * @param rs
     * @return lista objekata Clan
     * @throws SQLException ako dodje do greske prilikom izvrsavanja upita nad bazom
     */
    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Kategorija k = new Kategorija(rs.getLong("KategorijaID"),
                    rs.getString("NazivKategorije"), rs.getString("OpisKategorije"));

            Clan c = new Clan(rs.getLong("clanID"), rs.getString("imeClana"),
                    rs.getString("prezimeClana"), rs.getString("email"), rs.getString("telefon"),
                    k);

            lista.add(c);
        }

        rs.close();
        return lista;
    }
    /**
     * @return Vraca nazive kolona radi formirnja upita u DBbroker-u 
     */
    @Override
    public String koloneZaInsert() {
        return " (imeClana, prezimeClana, email, telefon, kategorijaID) ";
    }
    /**
     * @return Vraca vrednost za primarni kljuc radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je clanID null
     * @throws java.lang.RuntimeException ako je clandID manji od 1
     */
    @Override
    public String vrednostZaPrimarniKljuc() {
        if(clanID == null){
            throw new NullPointerException("ClanID ne sme biti null");
        }
        if(clanID < 1){
            throw new RuntimeException("ClanID ne sme biti manji od 1");
        }
        return " clanID = " + clanID;
    }
    
    /**
     * @return Vraca vrednost za insert  radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je ime, prezime,
     * teleforn, kategorija ili kategorijaID null
     */
    @Override
    public String vrednostiZaInsert() {
        if(imeClana == null || prezimeClana == null || email == null || telefon == null || kategorija == null ||
                kategorija.getKategorijaID() == null){
            throw new NullPointerException("Nijedna od vrednosti za insert ne sme biti null");
        }
        return "'" + imeClana + "', '" + prezimeClana + "', "
                + "'" + email + "', '" + telefon + "', " + kategorija.getKategorijaID();
    }
    /**
     * @return Vraca vrednost za update radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je ime, prezime,
     * teleforn, kategorija ili kategorijaID null
     */
    @Override
    public String vrednostiZaUpdate() {
        if(imeClana == null || prezimeClana == null || email == null || telefon == null || kategorija == null ||
                kategorija.getKategorijaID() == null){
            throw new NullPointerException("Nijedna od vrednosti za update ne sme biti null");
        }
        return " email = '" + email + "', telefon = '" + telefon + "', KategorijaID = "
                + kategorija.getKategorijaID();
    }
    
    /**
     * @return Vraca vrednost za uslov radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je ime, prezime, username ili password null
     */
    @Override
    public String uslov() {
        return "";
    }
    /**
     * Vraca vrednost clanID
     * @return clanID
     */
    public Long getClanID() {
        return clanID;
    }
    
    //ne uvodim logicku kontrolu jer ce uvek biti null zbog autoinceremnt-a
    /**
     * Postavalja vrednost atributa clanID
     * @param clanID 
     */
    public void setClanID(Long clanID) {
        this.clanID = clanID;
    }
    
    /**
     * Vraca ime clana
     * @return String ime
     */
    public String getImeClana() {
        return imeClana;
    }
    
    /**
     * Postavalja vrednost ime clana
     * @param imeClana 
     * @throws java.lang.NullPointerException ako je ime null
     * @throws java.lang.RuntimeException ako je ime krace od 2 karaktera
     */
    public void setImeClana(String imeClana) {
        if(imeClana == null){
            throw new NullPointerException("imeclana ne sme biti null");
        }
        if(imeClana.length() < 2){
            throw new RuntimeException("imeclana ne sme biti krace od 2 karaktera");
        }
        this.imeClana = imeClana;
    }
    
    /**
     * Vraca prezime clana
     * @return String prezime
     */
    public String getPrezimeClana() {
        return prezimeClana;
    }
    /**
     * Postavalja vrednost prezime clana
     * @param prezimeClana 
     * @throws java.lang.NullPointerException ako je prezime null
     * @throws java.lang.RuntimeException ako je prezime krace od 2 karaktera
     */
    public void setPrezimeClana(String prezimeClana) {
        if(prezimeClana == null){
            throw new NullPointerException("prezimeClana ne sme biti null");
        }
        if(prezimeClana.length() < 2){
            throw new RuntimeException("prezimeClana ne sme biti krace od 2 karaktera");
        }
        this.prezimeClana = prezimeClana;
    }
    /**
     * Vraca email clana
     * @return String email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Postavalja vrednost email clana
     * @param email 
     * @throws java.lang.NullPointerException ako je email null
     * @throws java.lang.RuntimeException ako je email ne sadrzi karakter '@'
     */
    public void setEmail(String email) {
        if(email == null){
            throw new NullPointerException("email ne sme biti null");
        }
        if(!email.contains("@")){
            throw new RuntimeException("email mora sadrzati karakter '@'");
        }
        this.email = email;
    }
    /**
     * Vraca telefon clana
     * @return String telefon
     */
    public String getTelefon() {
        return telefon;
    }
    /**
     * Postavalja vrednost telefon clana
     * @param telefon 
     * @throws java.lang.NullPointerException ako je telefon null
     * @throws java.lang.RuntimeException ako je telefon krace od 10 karaktera
     */
    public void setTelefon(String telefon) {
        if(telefon == null){
            throw new NullPointerException("Telefon ne sme biti null");
        }
        if(Pattern.matches("[a-zA-Z+]+", telefon)){
            throw new RuntimeException("Telefon moze sadrzati samo brojeve i znak '+'");
        }
        if(telefon.length() < 10){
            throw new RuntimeException("telefon mora imati duzinu od bar 10 karaktera");
        }
        this.telefon = telefon;
    }
    /**
     * Vraca kateogoriju clana
     * @return objekat klase Kategorija
     */
    public Kategorija getKategorija() {
        return kategorija;
    }
    /**
     * Postavalja vrednost za kategoriju clana
     * @param kategorija 
     * @throws java.lang.NullPointerException ako je kategorija null
     */
    public void setKategorija(Kategorija kategorija) {
        if(kategorija == null){
            throw new NullPointerException("kategorija ne sme biti null");
        }
        this.kategorija = kategorija;
    }
}
