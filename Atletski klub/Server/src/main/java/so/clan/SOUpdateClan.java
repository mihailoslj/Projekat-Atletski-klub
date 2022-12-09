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
 * Klasa za dodavanje izmenu vrednosti clana u bazi. 
 * Nasledjuje kasu AbstractSO i implementira metode validate() i execute()
 * @author Mihailo
 */
public class SOUpdateClan extends AbstractSO{
   
    /**
     * Provera validnosti parametra.
     * @param ado
     * @throws java.lang.Exception - ako prosledjeni objekat nije instacna klase Clan ili ako
     * u bazi vec postoji clan sa istim email-om i/ili brojem telefona
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        /**
     * Provera validnosti parametra.
     * @param ado
     * @throws java.lang.Exception - ako prosledjeni objekat nije instacna klase Clan ili ako
     * u bazi vec postoji clan sa istim email-om i/ili brojem telefona
     */
        if (!(ado instanceof Clan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Clan!");
        }

        Clan izmenjeniClan = (Clan) ado;

        ArrayList<Clan> clanovi = (ArrayList<Clan>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Clan clan : clanovi) {
            if (!clan.getClanID().equals(izmenjeniClan.getClanID())) {
                if (clan.getEmail().equals(izmenjeniClan.getEmail())) {
                    throw new Exception("Vec postoji clan s tim emailom!");
                }
                if (clan.getTelefon().equals(izmenjeniClan.getTelefon())) {
                    throw new Exception("Vec postoji clan s tim telefonom!");
                }
            }
        }

    }
    /**
     * Poziva metodu update klase DBBroker
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom izmena na bazom
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}
