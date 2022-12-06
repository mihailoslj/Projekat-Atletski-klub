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

    public Long getAdministratorID() {
        return administratorID;
    }
    
    //ne uvodim logicku kontrolu jer ce uvek biti null zbog autoinceremnt-a
    public void setAdministratorID(Long administratorID) {
        
        this.administratorID = administratorID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username == null){
            throw new NullPointerException("username ne sme biti null");
        }
        if(username.length() < 2){
            throw new RuntimeException("Username ne sme biti krace od 2 karaktera");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password == null){
            throw new NullPointerException("password ne sme biti null");
        }
        if(password.length() < 5){
            throw new RuntimeException("password ne sme biti krace od 5 karaktera");
        }
        this.password = password;
    }
    
    public String getIme() {
        
        return ime;
    }

    public void setIme(String ime) {
        if(ime == null){
            throw new NullPointerException("ime ne sme biti null");
        }
        if(ime.length() < 2){
            throw new RuntimeException("ime ne sme biti krace od 2 karaktera");
        }
      
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        if(prezime == null){
            throw new NullPointerException("prezime ne sme biti null");
        }
        if(ime.length() < 2){
            throw new RuntimeException("ime ne sme biti krace od 2 karaktera");
        }
        
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        if(ime == null || prezime == null){
            throw new NullPointerException("Ime i prezime ne smeju biti null");
        }
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return " administrator ";
    }

    @Override
    public String alijas() {
        return " a ";
    }

    @Override
    public String join() {
        return "";
    }

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

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Username, Password) ";
    }

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

    @Override
    public String vrednostiZaInsert() {
        if(ime == null || prezime == null || username == null || password == null){
            throw new NullPointerException("Nijedna od vrednosti za insert ne sme biti null");
        }
        return "'" + ime + "', '" + prezime + "', "
                + "'" + username + "', '" + password + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        if(ime == null || prezime == null || username == null || password == null){
            throw new NullPointerException("Nijedna od vrednosti za update ne sme biti null");
        }
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }
    
}
