/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Administrator;
import domen.Clan;
import domen.Kategorija;
import domen.Sala;
import domen.StavkaTermina;
import domen.Termin;
import java.util.ArrayList;
import so.clan.SOAddClan;
import so.clan.SODeleteClan;
import so.clan.SOGetAllClan;
import so.clan.SOUpdateClan;
import so.kategorija.SOGetAllKategorija;
import so.login.SOLogin;
import so.sala.SOGetAllSala;
import so.stavkaTermina.SOGetAllStavkaTermina;
import termin.SOAddTermin;
import termin.SODeleteTermin;
import termin.SOGetAllTermin;
import termin.SOUpdateTermin;

/**
 * Kasa koja sluzi kao kontroler u programu 'Server': prihvata zahteve iz klase ThreadClient i posledjuje
 * DBBroker-u, a od njega kasnije prima odgovor i vraca ThreadClient-u. Implementirana je kao singleton.
 *
 * @author Mihailo
 */
public class ServerController {
    private static ServerController instance;

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }
    
    /**
     * Vraca listu svih administratora u bazi. I proverava da li se ijedan poklapa sa prosledjenim.
     * @param administrator
     * @return Administrator
     * @throws java.lang.Exception - ako se desi greska priliko metode templateExecute()
     */
    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }
    /**
     * Vraca listu stavki termina za odgovarajucu vrednost terminID
     * @param t
     * @return StavkaTermina
     * @throws java.lang.Exception - ako se desi greska priliko metode templateExecute()
     */
    public ArrayList<StavkaTermina> getAllStavkaTermina(Termin t) throws Exception {
        SOGetAllStavkaTermina so = new SOGetAllStavkaTermina();
        
        StavkaTermina st = new StavkaTermina();
        st.setTermin(t);
        
        so.templateExecute(st);
        return so.getLista();
    }
    
    /**
     * Vraca listu svih sala u bazi
     * @return listu sala
     * @throws java.lang.Exception - ako se desi greska priliko metode templateExecute()
     */
    public ArrayList<Sala> getAllSala() throws Exception {
        SOGetAllSala so = new SOGetAllSala();
        so.templateExecute(new Sala());
        return so.getLista();
    }
    
    /**
     * Vraca listu svih clanova u bazi
     * @return listu clanova
     * @throws java.lang.Exception - ako se desi greska priliko metode templateExecute()
     */
    public ArrayList<Clan> getAllClan() throws Exception {
        SOGetAllClan so = new SOGetAllClan();
        so.templateExecute(new Clan());
        return so.getLista();
    }
    
    /**
     * Vraca listu svih kategorija u bazi
     * @return listu kategorija
     * @throws java.lang.Exception 
     */
    public ArrayList<Kategorija> getAllKategorija() throws Exception {
        SOGetAllKategorija so = new SOGetAllKategorija();
        so.templateExecute(new Kategorija());
        return so.getLista();
    }
    
    /**
     * Ubacuje novog clana u bazu
     * @param clan
     * @throws java.lang.Exception - ako se desi greska priliko metode templateExecute()
     */
    public void addClan(Clan clan) throws Exception {
        (new SOAddClan()).templateExecute(clan);
    }
    /**
     * Brise clana iz baze prema vrednosti clanID
     * @param clan
     * @throws java.lang.Exception - ako se desi greska priliko metode templateExecute()
     */
    public void deleteClan(Clan clan) throws Exception {
        (new SODeleteClan()).templateExecute(clan);
    }
    
    /**
     * Menja vrednosti odredjenog clana u bazi prema clanID
     * @param clan
     * @throws java.lang.Exception - ako se desi greska priliko metode templateExecute()
     */
    public void updateClan(Clan clan) throws Exception {
        (new SOUpdateClan()).templateExecute(clan);
    }
    
    public void addTermin(Termin termin) throws Exception {
       (new SOAddTermin()).templateExecute(termin);
    }
    /**
     * Vraca listu svih termina iz baze
     * @return lista termina
     * @throws java.lang.Exception - ako se desi greska priliko metode templateExecute() 
     */
    public ArrayList<Termin> getAllTermin() throws Exception {
        SOGetAllTermin so = new SOGetAllTermin();
        so.templateExecute(new Termin());
        return so.getLista();
    }
    
    /**
     * Brise termin iz baze prema vrednosti terminID
     * @param termin
     * @throws java.lang.Exception - ako se desi greska priliko metode templateExecute()
     */
    public void deleteTermin(Termin termin) throws Exception {
        (new SODeleteTermin()).templateExecute(termin);
    }
    
    /**
     * Menja vrednosti termina u bazi prema vrednosti terminID
     * @param termin
     * @throws java.lang.Exception - ako se desi greska priliko metode templateExecute()
     */
    public void updateTermin(Termin termin) throws Exception {
        (new SOUpdateTermin()).templateExecute(termin);
    }

    

 

   
    
}
