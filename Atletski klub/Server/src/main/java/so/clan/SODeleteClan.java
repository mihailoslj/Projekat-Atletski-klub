/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.clan;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Clan;
import so.AbstractSO;

/**
 * Klasa za dodavanje brisanje clana iz baze. 
 * Nasledjuje kasu AbstractSO i implementira metode validate() i execute()
 *
 * @author Mihailo
 */
public class SODeleteClan extends AbstractSO{
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
     * Poziva metodu delete klase DBBroker
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom brisanje iz  baze
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
}
