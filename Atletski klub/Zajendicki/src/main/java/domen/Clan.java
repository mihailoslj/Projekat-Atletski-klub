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
        this.clanID = clanID;
        this.imeClana = imeClana;
        this.prezimeClana = prezimeClana;
        this.email = email;
        this.telefon = telefon;
        this.kategorija = kategorija;
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
        return " clanID = " + clanID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + imeClana + "', '" + prezimeClana + "', "
                + "'" + email + "', '" + telefon + "', " + kategorija.getKategorijaID();
    }

    @Override
    public String vrednostiZaUpdate() {
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
        this.imeClana = imeClana;
    }

    public String getPrezimeClana() {
        return prezimeClana;
    }

    public void setPrezimeClana(String prezimeClana) {
        this.prezimeClana = prezimeClana;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }
}
