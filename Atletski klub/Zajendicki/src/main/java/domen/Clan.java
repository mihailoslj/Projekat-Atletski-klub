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
public class Clan extends AbstractDomainObject{
    private Long clanID;
    private String imeClana;
    private String prezimeClana;
    private String email;
    private String telefon;
    private Kategorija kategorija;

    @Override
    public String toString() {
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

    @Override
    public String nazivTabele() {
        return " Clan ";
    }

    @Override
    public String alijas() {
        return " c ";
    }

    @Override
    public String join() {
        return " JOIN KATEGORIJA K ON (K.KATEGORIJAID = C.KATEGORIJAID) ";
    }

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

    @Override
    public String koloneZaInsert() {
        return " (imeClana, prezimeClana, email, telefon, kategorijaID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        if(clanID == null){
            throw new NullPointerException("ClanID ne sme biti null");
        }
        return " clanID = " + clanID;
    }

    @Override
    public String vrednostiZaInsert() {
        if(imeClana == null || prezimeClana == null || email == null || telefon == null || kategorija.getKategorijaID() == null){
            throw new NullPointerException("Nijedna od vrednosti za insert ne sme biti null");
        }
        return "'" + imeClana + "', '" + prezimeClana + "', "
                + "'" + email + "', '" + telefon + "', " + kategorija.getKategorijaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        if(imeClana == null || prezimeClana == null || email == null || telefon == null || kategorija.getKategorijaID() == null){
            throw new NullPointerException("Nijedna od vrednosti za update ne sme biti null");
        }
        return " email = '" + email + "', telefon = '" + telefon + "', KategorijaID = "
                + kategorija.getKategorijaID();
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getClanID() {
        return clanID;
    }

    public void setClanID(Long clanID) {
       
        this.clanID = clanID;
    }

    public String getImeClana() {
        return imeClana;
    }

    public void setImeClana(String imeClana) {
        if(imeClana == null){
            throw new NullPointerException("imeclana ne sme biti null");
        }
        if(imeClana.length() < 2){
            throw new RuntimeException("imeclana ne sme biti krace od 2 karaktera");
        }
        this.imeClana = imeClana;
    }

    public String getPrezimeClana() {
        return prezimeClana;
    }

    public void setPrezimeClana(String prezimeClana) {
        if(prezimeClana == null){
            throw new NullPointerException("prezimeClana ne sme biti null");
        }
        if(prezimeClana.length() < 2){
            throw new RuntimeException("prezimeClana ne sme biti krace od 2 karaktera");
        }
        this.prezimeClana = prezimeClana;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null){
            throw new NullPointerException("email ne sme biti null");
        }
        if(email.contains("@")){
            throw new RuntimeException("email mora sadrzati karakter '@'");
        }
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        if(telefon == null){
            throw new NullPointerException("Telefon ne sme biti null");
        }
        if(telefon.length() < 10){
            throw new RuntimeException("telefon mora imati duzinu od bar 10 karaktera");
        }
        this.telefon = telefon;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        if(kategorija == null){
            throw new NullPointerException("kategorija ne sme biti null");
        }
        this.kategorija = kategorija;
    }
}
