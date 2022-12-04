/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mihailo
 */
public class Termin extends AbstractDomainObject{
    private Long terminID;
    private String nazivTermina;
    private Date datumVreme;
    private String opisTermina;
    private int maxClanova;
    private Sala sala;
    private Administrator administrator;
    private ArrayList<StavkaTermina> stavkeTermina;

    public Termin(Long terminID, String nazivTermina, Date datumVreme, String opisTermina, int maxClanova, Sala sala, Administrator administrator, ArrayList<StavkaTermina> stavkeTermina) {
        this.terminID = terminID;
        this.nazivTermina = nazivTermina;
        this.datumVreme = datumVreme;
        this.opisTermina = opisTermina;
        this.maxClanova = maxClanova;
        this.sala = sala;
        this.administrator = administrator;
        this.stavkeTermina = stavkeTermina;
    }

    public Termin() {
    }

    @Override
    public String nazivTabele() {
        return " Termin ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN SALA S ON (S.SALAID = T.SALAID) "
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

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (nazivTermina, datumVreme, opisTermina, maxClanova, salaID, administratorID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " terminID = " + terminID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivTermina + "', '" + new Timestamp(datumVreme.getTime()) + "', "
                + "'" + opisTermina + "', " + maxClanova + ", " + sala.getSalaID() + ", "
                + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " nazivTermina = '" + nazivTermina + "', "
                + "datumVreme = '" + new Timestamp(datumVreme.getTime()) + "', "
                + "opisTermina = '" + opisTermina + "', maxClanova = " + maxClanova + ", "
                + "SalaID = " + sala.getSalaID();
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getTerminID() {
        return terminID;
    }

    public void setTerminID(Long terminID) {
        this.terminID = terminID;
    }

    public String getNazivTermina() {
        return nazivTermina;
    }

    public void setNazivTermina(String nazivTermina) {
        this.nazivTermina = nazivTermina;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public String getOpisTermina() {
        return opisTermina;
    }

    public void setOpisTermina(String opisTermina) {
        this.opisTermina = opisTermina;
    }

    public int getMaxClanova() {
        return maxClanova;
    }

    public void setMaxClanova(int maxClanova) {
        this.maxClanova = maxClanova;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<StavkaTermina> getStavkeTermina() {
        return stavkeTermina;
    }

    public void setStavkeTermina(ArrayList<StavkaTermina> stavkeTermina) {
        this.stavkeTermina = stavkeTermina;
    }
}
