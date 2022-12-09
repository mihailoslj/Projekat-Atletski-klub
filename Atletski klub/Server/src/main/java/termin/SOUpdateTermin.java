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
 * Klasa za izmenu vrednosti odredjenog termina u bazi. 
 * Nasledjuje kasu AbstractSO i implementira metode validate() i execute()
 * @author Mihailo
 */
public class SOUpdateTermin extends AbstractSO{
    
    /**
     * Provera validnosti parametra.
     * @param ado
     * @throws java.lang.Exception - ako prosledjeni objekat nije instacna klase Termin ili ako je
     * datum termina u proslosti ili ako termin nema clanova
     */
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
    
    /**
     * Update-uju se vrednosti odredjenog termina u bazi. 
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom trenskakcija nad bazom
     */
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
