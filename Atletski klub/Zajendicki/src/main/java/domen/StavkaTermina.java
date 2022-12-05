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
public class StavkaTermina extends AbstractDomainObject{
    private Termin termin;
    private int rbStavke;
    private String napomena;
    private Clan clan;

    public StavkaTermina(Termin termin, int rbStavke, String napomena, Clan clan) {
        setTermin(termin);
        setRbStavke(rbStavke);
        setNapomena(napomena);
        setClan(clan);
    }

    public StavkaTermina() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaTermina ";
    }

    @Override
    public String alijas() {
        return " st ";
    }

    @Override
    public String join() {
        return " JOIN CLAN C USING (CLANID) "
                + "JOIN KATEGORIJA K ON (K.KATEGORIJAID = C.KATEGORIJAID) "
                + "JOIN TERMIN T USING (TERMINID) "
                + "JOIN SALA S ON (S.SALAID = T.SALAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = T.ADMINISTRATORID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Sala s = new Sala(rs.getLong("SalaID"), rs.getString("nazivSale"));

            Termin t = new Termin(rs.getLong("terminID"), rs.getString("nazivTermina"),
                    rs.getTimestamp("datumVreme"), rs.getString("opisTermina"),
                    rs.getInt("maxClanova"), s, a, null);

            Kategorija k = new Kategorija(rs.getLong("KategorijaID"),
                    rs.getString("NazivKategorije"), rs.getString("OpisKategorije"));

            Clan c = new Clan(rs.getLong("clanID"), rs.getString("imeClana"),
                    rs.getString("prezimeClana"), rs.getString("email"), rs.getString("telefon"),
                    k);

            StavkaTermina st = new StavkaTermina(t, rs.getInt("rbStavke"),
                    rs.getString("napomena"), c);

            lista.add(st);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (terminID, rbStavke, napomena, clanID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        if(termin.getTerminID() == null || termin == null){
            throw new NullPointerException("Termin ne sme biti null");
        }
        if(termin.getTerminID() < 1){
            throw new RuntimeException("TerminID ne sme biti manji od 1");
        }
        return " terminID = " + termin.getTerminID();
    }

    @Override
    public String vrednostiZaInsert() {
        if(termin == null || termin.getTerminID() == null || napomena == null ||
                clan == null || clan.getClanID() == null){
            throw new NullPointerException("Nijedan od parametara za insert ne sme biti null");
        }
        return " " + termin.getTerminID() + ", " + rbStavke + ", "
                + "'" + napomena + "', " + clan.getClanID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        if(termin == null || termin.getTerminID() == null){
            throw new NullPointerException("Termin ne me biti null za uslov");
        }
        return " WHERE T.TERMINID = " + termin.getTerminID();
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        if(rbStavke < 1) {
            throw new RuntimeException("rb stavke ne sme biti manji od 1");
        }
        this.rbStavke = rbStavke;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        if(napomena == null) {
            throw new NullPointerException("napomena ne sme biti null");
        }
        if(napomena.length() < 1){
            throw new RuntimeException("Napomena ne sme biti kraca od jednog karaktera");
        }
        this.napomena = napomena;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        if(clan == null){
            throw new NullPointerException("Objekat clan ne sme biti null");
        }
        this.clan = clan;
    }
}
