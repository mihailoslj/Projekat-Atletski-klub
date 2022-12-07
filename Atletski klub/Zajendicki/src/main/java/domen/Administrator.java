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
 * Klasa koja predstavlja Administratora sistema. Nasledjuje apstraktnu klasu AbstractDomainObject
 * i implementira njene metode.
 *
 * @author Mihailo
 */
public class Administrator extends AbstractDomainObject{

    private Long administratorID;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Administrator() {
    }

    public Administrator(Long administratorID, String ime, String prezime, String username, String password) {
        setAdministratorID(administratorID);
        setIme(ime);
        setPrezime(prezime);
        setUsername(username);
        setPassword(password);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
    /**
     * Poredi dva administratora po vrednosti administraorID
     * @param obj prima objekat kao nadklasu svi objekata
     * @return
     * <ul>
     *      <li>true ako su oba objekata klase Administrator i imaju isti administratorID
     *      <li>false ako u svim drugim slucajevima
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
        final Administrator other = (Administrator) obj;
        return Objects.equals(this.administratorID, other.administratorID);
    }
    /**
     * Vraca vrednost administratorID
     * @return administratorID
     */
    public Long getAdministratorID() {
        return administratorID;
    }
    
    //ne uvodim logicku kontrolu jer ce uvek biti null zbog autoinceremnt-a
    /**
     * Postavalja vrednost atributa administratorID
     * @param administratorID 
     */
    public void setAdministratorID(Long administratorID) {
        
        this.administratorID = administratorID;
    }
    /**
     * Vraca username administratora
     * @return String username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Postavalja vrednost username administratora
     * @param username 
     * @throws java.lang.NullPointerException ako je username null
     * @throws java.lang.RuntimeException ako je username krace od 2 karaktera
     */
    public void setUsername(String username) {
        if(username == null){
            throw new NullPointerException("username ne sme biti null");
        }
        if(username.length() < 2){
            throw new RuntimeException("Username ne sme biti krace od 2 karaktera");
        }
        this.username = username;
    }
    
    /**
     * Vraca password administratora
     * @return String password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Postavalja vrednost password administratora
     * @param password 
     * @throws java.lang.NullPointerException ako je password null
     * @throws java.lang.RuntimeException ako je password kraci od 5 karaktera
     */
    public void setPassword(String password) {
        if(password == null){
            throw new NullPointerException("password ne sme biti null");
        }
        if(password.length() < 5){
            throw new RuntimeException("password ne sme biti krace od 5 karaktera");
        }
        this.password = password;
    }
    
    /**
     * Vraca ime administratora
     * @return String ime
     * 
     */
    public String getIme() {
        
        return ime;
    }
    /**
     * Postavalja ime administratora
     * @param ime 
     * @throws java.lang.NullPointerException ako je ime null
     * @throws java.lang.RuntimeException ako je ime krace od 2 karaktera
     */
    public void setIme(String ime) {
        if(ime == null){
            throw new NullPointerException("ime ne sme biti null");
        }
        if(ime.length() < 2){
            throw new RuntimeException("ime ne sme biti krace od 2 karaktera");
        }
      
        this.ime = ime;
    }
    /**
     * Postavlja prezime administratora
     * @return String prezime
     */
    public String getPrezime() {
        return prezime;
    }
    
    /**
     * Postavalja prezime administratora
     * @param prezime 
     * @throws java.lang.NullPointerException ako je prezime null
     * @throws java.lang.RuntimeException ako je prezime krace od 2 karaktera
     */
    public void setPrezime(String prezime) {
        if(prezime == null){
            throw new NullPointerException("prezime ne sme biti null");
        }
        if(ime.length() < 2){
            throw new RuntimeException("ime ne sme biti krace od 2 karaktera");
        }
        
        this.prezime = prezime;
    }
    
    /**
     * Vraca ime i prezime administratora 
     * @return ime i prezime autora
     * @throws java.lang.NullPointerException ako su ime ili prezime null
     */
    @Override
    public String toString() {
        if(ime == null || prezime == null){
            throw new NullPointerException("Ime i prezime ne smeju biti null");
        }
        return ime + " " + prezime;
    }
    
    /**
     * 
     * @return vraca naziv tabele da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String nazivTabele() {
        return " administrator ";
    }
    
    /**
     * 
     * @return vraca alijas tabele da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String alijas() {
        return " a ";
    }
    
    /**
     * 
     * @return prazan upit jer administrator ne vuce nijednu drugu klasu
     */
    @Override
    public String join() {
        return "";
    }
    
    /**
     * Prima ResultSet(tabelu) i pretvara je u listu objekata Administrator i vraca je
     * @param rs
     * @return lista objekata Administrator
     * @throws SQLException ako dodje do greske prilikom izvrsavanja upita nad bazom
     */
    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            lista.add(a);
        }

        rs.close();
        return lista;
    }
    
    /**
     * 
     * @return Vraca nazive kolona radi formirnja upita u DBbroker-u 
     */
    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Username, Password) ";
    }

    /**
     * @return Vraca vrednost za primarni kljuc radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je administratorID null
     * @throws java.lang.RuntimeException ako je administorID manji od 1
     */
    @Override
    public String vrednostZaPrimarniKljuc() {
        if(administratorID == null){
            throw new NullPointerException("AdministratorID ne sme biti null kao vrednost za"
                    + "primarni kljuc");
        }
        if(administratorID < 1){
            throw new RuntimeException("administratorID ne sme biti manji od 1 kao vrednost za"
                    + "primarni kljuc");
        }
        return " AdministratorID = " + administratorID;
    }
    /**
     * @return Vraca vrednost za insert  radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je ime, prezime, username ili password null
     */
    @Override
    public String vrednostiZaInsert() {
        if(ime == null || prezime == null || username == null || password == null){
            throw new NullPointerException("Nijedna od vrednosti za insert ne sme biti null");
        }
        return "'" + ime + "', '" + prezime + "', "
                + "'" + username + "', '" + password + "'";
    }
    /**
     * @return Vraca vrednost za update  radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je ime, prezime, username ili password null
     */
    @Override
    public String vrednostiZaUpdate() {
        if(ime == null || prezime == null || username == null || password == null){
            throw new NullPointerException("Nijedna od vrednosti za update ne sme biti null");
        }
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }
    /**
     * @return Vraca vrednost za uslov radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je ime, prezime, username ili password null
     */
    @Override
    public String uslov() {
        return "";
    }
    
}
