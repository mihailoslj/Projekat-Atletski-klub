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
import java.util.Objects;

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
        setTerminID(terminID);
        setNazivTermina(nazivTermina);
        setDatumVreme(datumVreme);
        setOpisTermina(opisTermina);
        setMaxClanova(maxClanova);
        setSala(sala);
        setAdministrator(administrator);
        setStavkeTermina(stavkeTermina);
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
    public int hashCode() {
        int hash = 7;
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
        final Termin other = (Termin) obj;
        return Objects.equals(this.terminID, other.terminID);
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
        if(terminID == null){
            throw new NullPointerException("vrednost terminID ne sme biti null za primarni kljuc");
        }
        if(terminID < 1){
            throw new RuntimeException("terminID ne sme biti manji od 1 za primarni kljuc");
        }
        return " terminID = " + terminID;
    }

    @Override
    public String vrednostiZaInsert() {
        if(nazivTermina == null || datumVreme == null || opisTermina == null || 
                sala == null || sala.getSalaID() == null || administrator == null ||
                administrator.getAdministratorID() == null){
            throw new NullPointerException("nijedna od vrednosti za insert ne sme biti null");
        }
        return "'" + nazivTermina + "', '" + new Timestamp(datumVreme.getTime()) + "', "
                + "'" + opisTermina + "', " + maxClanova + ", " + sala.getSalaID() + ", "
                + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        if(nazivTermina == null || datumVreme == null || opisTermina == null || 
                sala == null || sala.getSalaID() == null){
            throw new NullPointerException("nijedna od vrednosti za update ne sme biti null");
        }
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
    
    //ne uvodim logicku kontrolu jer ce uvek biti null zbog autoinceremnt-a
    public void setTerminID(Long terminID) {
        this.terminID = terminID;
    }

    public String getNazivTermina() {
        return nazivTermina;
    }

    public void setNazivTermina(String nazivTermina) {
        if(nazivTermina == null){
            throw new NullPointerException("navivtermina ne sme biti null");
        }
        if(nazivTermina.length() < 2){
            throw new RuntimeException("nazivtermina ne sme biti kraci od 2 karaktera");
        }
        this.nazivTermina = nazivTermina;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        if(datumVreme == null){
            throw new NullPointerException("datumVreme ne sme biti null");
        }
        if(datumVreme.before(new Date())){
            throw new RuntimeException("datumVreme termina ne moze biti u proslosti");
        }
        this.datumVreme = datumVreme;
    }

    public String getOpisTermina() {
        return opisTermina;
    }

    public void setOpisTermina(String opisTermina) {
        if(opisTermina == null){
            throw new NullPointerException("opisTermina ne sme biti null");
        }
        if(opisTermina.length() == 0){
            throw new RuntimeException("opisTermina ne sme biti prazan");
        }
        this.opisTermina = opisTermina;
    }

    public int getMaxClanova() {
        return maxClanova;
    }

    public void setMaxClanova(int maxClanova) {
        if(maxClanova < 1){
            throw new RuntimeException("Max clanova ne sme biti manje od 1");
        }
        this.maxClanova = maxClanova;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        if(sala == null){
            throw new NullPointerException("Objekat sala ne sme biti null");
        }
        this.sala = sala;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        if(administrator == null){
            throw new NullPointerException("Objekat administrator ne sme biti null");
        }
        this.administrator = administrator;
    }

    public ArrayList<StavkaTermina> getStavkeTermina() {
        return stavkeTermina;
    }

    public void setStavkeTermina(ArrayList<StavkaTermina> stavkeTermina) {
        if(stavkeTermina.isEmpty()){
            throw new RuntimeException("Termin mora sadrzati bar jednu stavku");
        }
        this.stavkeTermina = stavkeTermina;
    }
}
