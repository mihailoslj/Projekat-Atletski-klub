/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package termin;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.StavkaTermina;
import domen.Termin;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author Mihailo
 */
public class SOUpdateTermin extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }

        Termin t = (Termin) ado;

        if (!t.getDatumVreme().after(new Date())) {
            throw new Exception("Termin mora biti u buducnosti!");
        }

        if (t.getStavkeTermina().isEmpty()) {
            throw new Exception("Termin mora imati barem jednog clana!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        DBBroker.getInstance().update(ado);

        Termin t = (Termin) ado;
        
        DBBroker.getInstance().delete(t.getStavkeTermina().get(0));

        for (StavkaTermina stavkaTermina : t.getStavkeTermina()) {
            DBBroker.getInstance().insert(stavkaTermina);
        }

    }
}
