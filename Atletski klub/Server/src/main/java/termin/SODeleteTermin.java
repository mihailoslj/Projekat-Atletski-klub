/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package termin;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Termin;
import so.AbstractSO;

/**
 *  Klasa za dodavanje brisanje termina iz baze.
 * Nasledjuje kasu AbstractSO i implementira metode validate() i execute()
 * @author Mihailo
 */
public class SODeleteTermin extends AbstractSO{
    
    /**
     * Provera validnosti parametra.
     * @param ado
     * @throws java.lang.Exception - ako prosledjeni objekat nije instacna klase Termin
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }
    }
    
    /**
     * Poziva se metoda delete klase DBBroker i brise termin iz baze.
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom brisanja iz baze 
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
}
