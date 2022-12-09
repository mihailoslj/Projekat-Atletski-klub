/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.clan;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Clan;
import java.util.ArrayList;
import so.AbstractSO;

/**
 * Klasa za vracanje svih clanova iz baze.
 * Nasledjuje kasu AbstractSO i implementira metode validate() i execute()
 *
 * @author Mihailo
 */
public class SOGetAllClan extends AbstractSO{
    
    private ArrayList<Clan> lista;
    
    /**
     * Provera validnosti parametra.
     * @param ado
     * @throws java.lang.Exception - ako prosledjeni objekat nije instacna klase Clan 
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Clan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Clan!");
        }
    }
    
    /**
     * Poziva metodu slect klase DBBroker i prima listu clanova
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom selektovanja iz baze u bazu ili
     * prilikom kastovanja liste
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> clanovi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Clan>) (ArrayList<?>) clanovi;
    }
    
    /**
     * 
     * @return vraca listu clanova iz baze 
     */
    public ArrayList<Clan> getLista() {
        return lista;
    }
}
