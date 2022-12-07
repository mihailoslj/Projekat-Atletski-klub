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
 *  Klasa koja predstavlja termin(treninga). Nasledjuje apstraktnu klasu AbstractDomainObject
 * i implementira njene metode.
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
    /**
     * @return vraca naziv tabele u bazi za termin da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String nazivTabele() {
        return " Termin ";
    }
    /**
     * @return vraca alijas tabele u bazi za termin da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    /**
     * Poredi dva objekta Termin i utvrdjuje da li su isti
     * @param obj
     * @return 
     * <ul>
     *      <li>true ako su oba objekta klase Termin i imaju isti naziv i datum
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
        final Termin other = (Termin) obj;
        if (!Objects.equals(this.nazivTermina, other.nazivTermina)) {
            return false;
        }
        return Objects.equals(this.datumVreme, other.datumVreme);
    }

    /**
     * @return vraca join upit jer Termin vuce Salu, Administratora i Stavku termina,
     * a ona preostale klase 
     * da bi se formulisao upit u DBBroker-u
     */
    @Override
    public String join() {
        return " JOIN SALA S ON (S.SALAID = T.SALAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = T.ADMINISTRATORID) ";
    }
    
    /**
     * Prima ResultSet(tabelu) i pretvara je u listu objekata Termin i vraca je
     * @param rs
     * @return lista objekata Termin
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

            lista.add(t);
        }

        rs.close();
        return lista;
    }
    /**
     * @return Vraca nazive kolona radi formirnja upita u DBbroker-u 
     */
    @Override
    public String koloneZaInsert() {
        return " (nazivTermina, datumVreme, opisTermina, maxClanova, salaID, administratorID) ";
    }
    
    /**
     * @return Vraca vrednost za primarni kljuc radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je terminID null
     * @throws java.lang.RuntimeException ako je terminID manji od 1
     */
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
    /**
     * @return Vraca vrednost za insert  radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je nazivTermina, datumVreme, 
     * opisTermina, sala, salaID, administrator ili administratorID null
     */
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
    /**
     * @return Vraca vrednost za update radi formirnja upita u DBbroker-u 
     * @throws java.lang.NullPointerException ako je nazivTermina, datumVreme, 
     * opisTermina, sala ili salaID null
     */
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
    
    /**
     * @return vraca prazan String jer nemam sistemsku operaciju koja bi zahtevala dodatni 
     * uslov prilikom formiranja uslova koji ce se izvrsiti nad bazom u DBbroker-u
     */
    @Override
    public String uslov() {
        return "";
    }
    /**
     * @return vraca Long terminID
     */
    public Long getTerminID() {
        return terminID;
    }
    
    //ne uvodim logicku kontrolu jer ce uvek biti null zbog autoinceremnt-a
    /**
     * postavlja vrednost atributa terminID
     * @param terminID 
     */
    public void setTerminID(Long terminID) {
        this.terminID = terminID;
    }
    /**
     * @return vraca String nazivTermina
     */
    public String getNazivTermina() {
        return nazivTermina;
    }
    /**
     * Postavalja vrednost nazivTermina 
     * @param nazivTermina 
     * @throws java.lang.NullPointerException ako je nazivTermina null
     * @throws java.lang.RuntimeException ako je nazivTermina krace od 2 karaktera
     */
    public void setNazivTermina(String nazivTermina) {
        if(nazivTermina == null){
            throw new NullPointerException("navivtermina ne sme biti null");
        }
        if(nazivTermina.length() < 2){
            throw new RuntimeException("nazivtermina ne sme biti kraci od 2 karaktera");
        }
        this.nazivTermina = nazivTermina;
    }
    /**
     * @return vraca Date datumVreme
     */
    public Date getDatumVreme() {
        return datumVreme;
    }
    /**
     * Postavalja vrednost datumVreme 
     * @param datumVreme 
     * @throws java.lang.NullPointerException ako je datumVreme null
     * @throws java.lang.RuntimeException ako je datumVreme u proslosti
     */
    public void setDatumVreme(Date datumVreme) {
        if(datumVreme == null){
            throw new NullPointerException("datumVreme ne sme biti null");
        }
        if(datumVreme.before(new Date())){
            throw new RuntimeException("datumVreme termina ne moze biti u proslosti");
        }
        this.datumVreme = datumVreme;
    }
    /**
     * @return vraca String opisTermina
     */
    public String getOpisTermina() {
        return opisTermina;
    }
    /**
     * Postavalja vrednost opisTermina 
     * @param opisTermina 
     * @throws java.lang.NullPointerException ako je opisTermina null
     * @throws java.lang.RuntimeException ako je opisTermina krace od 2 karaktera
     */
    public void setOpisTermina(String opisTermina) {
        if(opisTermina == null){
            throw new NullPointerException("opisTermina ne sme biti null");
        }
        if(opisTermina.length() == 0){
            throw new RuntimeException("opisTermina ne sme biti prazan");
        }
        this.opisTermina = opisTermina;
    }
    /**
     * @return vraca int maxClanova
     */
    public int getMaxClanova() {
        return maxClanova;
    }
    /**
     * Postavalja vrednost maxClanova 
     * @param maxClanova 
     * @throws java.lang.RuntimeException ako je maxClanova manje od 1
     */
    public void setMaxClanova(int maxClanova) {
        if(maxClanova < 1){
            throw new RuntimeException("Max clanova ne sme biti manje od 1");
        }
        this.maxClanova = maxClanova;
    }
    
    /**
     * @return vraca objekat klase Sala
     */
    public Sala getSala() {
        return sala;
    }
    /**
     * Postavalja vrednost atributa sala 
     * @param sala 
     * @throws java.lang.NullPointerException ako je objekat Sala null
     */
    public void setSala(Sala sala) {
        if(sala == null){
            throw new NullPointerException("Objekat sala ne sme biti null");
        }
        this.sala = sala;
    }
    /**
     * @return vraca objekat klase Administrator
     */
    public Administrator getAdministrator() {
        return administrator;
    }
    /**
     * Postavalja vrednost atributa administrator 
     * @param administrator 
     * @throws java.lang.NullPointerException ako je objekat Administrator null
     */
    public void setAdministrator(Administrator administrator) {
        if(administrator == null){
            throw new NullPointerException("Objekat administrator ne sme biti null");
        }
        this.administrator = administrator;
    }
    /**
     * @return vraca ArrayList objekata klase StavkaTermina
     */
    public ArrayList<StavkaTermina> getStavkeTermina() {
        return stavkeTermina;
    }
    /**
     * Postavalja vrednost atributa stavkeTermina 
     * @param stavkeTermina 
     * @throws java.lang.RuntimeException ako je lista stavki prazna
     */
    public void setStavkeTermina(ArrayList<StavkaTermina> stavkeTermina) {
        if(stavkeTermina.isEmpty()){
            throw new RuntimeException("Termin mora sadrzati bar jednu stavku");
        }
        this.stavkeTermina = stavkeTermina;
    }
}
