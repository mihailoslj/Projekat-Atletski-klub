/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.sala;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Sala;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Mihailo
 */
public class SOGetAllSala extends AbstractSO{
     private ArrayList<Sala> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Sala)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sala!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> sale = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Sala>) (ArrayList<?>) sale;
    }

    public ArrayList<Sala> getLista() {
        return lista;
    }
}
