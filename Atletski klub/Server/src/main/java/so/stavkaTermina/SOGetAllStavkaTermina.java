/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaTermina;

import so.AbstractSO;
import db.DBBroker;
import domen.AbstractDomainObject;
import domen.StavkaTermina;
import java.util.ArrayList;
import so.AbstractSO;
/**
 *
 * @author Mihailo
 */
public class SOGetAllStavkaTermina extends AbstractSO{
    private ArrayList<StavkaTermina> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaTermina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaTermina!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> termini = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaTermina>) (ArrayList<?>) termini;
    }

    public ArrayList<StavkaTermina> getLista() {
        return lista;
    }
}
