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
public class SOAddClan extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Clan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Clan!");
        }

        Clan noviClan = (Clan) ado;

        ArrayList<Clan> clanovi = (ArrayList<Clan>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Clan clan : clanovi) {
            if (clan.getEmail().equals(noviClan.getEmail())) {
                throw new Exception("Vec postoji clan s tim emailom!");
            }
            if (clan.getTelefon().equals(noviClan.getTelefon())) {
                throw new Exception("Vec postoji clan s tim telefonom!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}
