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

/**
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

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public ArrayList<StavkaTermina> getAllStavkaTermina(Termin t) throws Exception {
        SOGetAllStavkaTermina so = new SOGetAllStavkaTermina();
        
        StavkaTermina st = new StavkaTermina();
        st.setTermin(t);
        
        so.templateExecute(st);
        return so.getLista();
    }

    public ArrayList<Sala> getAllSala() throws Exception {
        SOGetAllSala so = new SOGetAllSala();
        so.templateExecute(new Sala());
        return so.getLista();
    }

    public ArrayList<Clan> getAllClan() throws Exception {
        SOGetAllClan so = new SOGetAllClan();
        so.templateExecute(new Clan());
        return so.getLista();
    }

    public ArrayList<Kategorija> getAllKategorija() throws Exception {
        SOGetAllKategorija so = new SOGetAllKategorija();
        so.templateExecute(new Kategorija());
        return so.getLista();
    }

    public void addClan(Clan clan) throws Exception {
        (new SOAddClan()).templateExecute(clan);
    }

    public void deleteClan(Clan clan) throws Exception {
        (new SODeleteClan()).templateExecute(clan);
    }

    public void updateClan(Clan clan) throws Exception {
        (new SOUpdateClan()).templateExecute(clan);
    }

 

   
    
}
