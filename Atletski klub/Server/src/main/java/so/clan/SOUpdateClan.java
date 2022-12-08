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
 *
 * @author Mihailo
 */
public class SOUpdateClan extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
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

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}
