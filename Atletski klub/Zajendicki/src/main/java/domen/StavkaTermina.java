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
 * Klasa koja predstavlja stavku termina(slab objekat). Nasledjuje apstraktnu klasu AbstractDomainObject
 * i implementira njene metode.
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
    
    /**
     * @return vraca naziv tabele u bazi za stavku termina da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String nazivTabele() {
        return " StavkaTermina ";
    }
    /**
     * @return vraca alijas tabele u bazi za stavku termina da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String alijas() {
        return " st ";
    }
    /**
     * @return vraca join upit jer Stavka termina
     * vuce Termin i Clana, a on Kategoriju da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String join() {
        return " JOIN CLAN C USING (CLANID) "
                + "JOIN KATEGORIJA K ON (K.KATEGORIJAID = C.KATEGORIJAID) "
                + "JOIN TERMIN T USING (TERMINID) "
                + "JOIN SALA S ON (S.SALAID = T.SALAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = T.ADMINISTRATORID) ";
    }
    
    /**
     * Prima ResultSet(tabelu) i pretvara je u listu objekata Stavka termina i vraca je
     * @param rs
     * @return lista objekata Stavka termina
     * @throws SQLException ako dodje do greske prilikom izvrsavanja upita nad bazom
     */
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
    /**
     * @return Vraca nazive kolona radi formirnja upita u DBbroker-u 
     */
    @Override
    public String koloneZaInsert() {
        return " (terminID, rbStavke, napomena, clanID) ";
    }
    /**
     * @return Vraca vrednost za primarni kljuc radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je termin ili terminID null
     * @throws java.lang.RuntimeException ako je terminID manji od 1
     */
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
    /**
     * @return Vraca vrednost za insert radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je termin, terminID, napomena,
     * clan ili clanID null
     */
    @Override
    public String vrednostiZaInsert() {
        if(termin == null || termin.getTerminID() == null || napomena == null ||
                clan == null || clan.getClanID() == null){
            throw new NullPointerException("Nijedan od parametara za insert ne sme biti null");
        }
        return " " + termin.getTerminID() + ", " + rbStavke + ", "
                + "'" + napomena + "', " + clan.getClanID() + " ";
    }
    /**
     * @return vraca prazan string zato sto nemam sistemsku operaciju za update Stavke table
     */
    @Override
    public String vrednostiZaUpdate() {
        return "";
    }
    /**
     * @return vraca string koji se koristi za formiranje upita na bazom u DBbroker-u
     */
    @Override
    public String uslov() {
        return " WHERE T.TERMINID = " + termin.getTerminID();
    }
    /**
     * Vraca vrednost termin
     * @return Objekat termin
     */
    public Termin getTermin() {
        return termin;
    }
    
    /**
     * Postavlja vrednost jakog objekta termin. Dozvoljena je null vrednost zato sto 
     * ce se prvo kreirati stavka termina a tek nakon toga termin.
     * @param termin 
     */
    public void setTermin(Termin termin) {
        this.termin = termin;
    }
    /**
     * Vraca redni broj stavke
     * @return int rbStavke
     */
    public int getRbStavke() {
        return rbStavke;
    }
    /**
     * Postavlja redni broj stabke
     * @param  rbStavke
     * @throws java.lang.RuntimeException ako je redni broj manji od 1
     */
    public void setRbStavke(int rbStavke) {
        if(rbStavke < 1) {
            throw new RuntimeException("rb stavke ne sme biti manji od 1");
        }
        this.rbStavke = rbStavke;
    }
    /**
     * Vraca napomenu stavke
     * @return String napomena
     */
    public String getNapomena() {
        return napomena;
    }
    /**
     * Postavalja vrednost napomena stavke
     * @param napomena 
     * @throws java.lang.NullPointerException ako je napomena null
     * @throws java.lang.RuntimeException ako je napomena krace od 2 karaktera
     */
    public void setNapomena(String napomena) {
        if(napomena == null) {
            throw new NullPointerException("napomena ne sme biti null");
        }
        if(napomena.length() == 0){
            throw new RuntimeException("Napomena ne sme biti prazna");
        }
        this.napomena = napomena;
    }
    /**
     * Vraca vrednost Clan
     * @return Objekat Clan
     */
    public Clan getClan() {
        return clan;
    }
    /**
     * Postavalja vrednost za clana stabke
     * @param clan 
     * @throws java.lang.NullPointerException ako je clan null
     */
    public void setClan(Clan clan) {
        if(clan == null){
            throw new NullPointerException("Objekat clan ne sme biti null");
        }
        this.clan = clan;
    }
}
