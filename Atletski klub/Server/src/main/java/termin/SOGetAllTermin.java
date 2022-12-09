/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package termin;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Termin;
import java.util.ArrayList;
import so.AbstractSO;

/**
 * Klasa za vracanje svih termina iz baze.
 * Nasledjuje kasu AbstractSO i implementira metode validate() i execute()
 *
 * @author Mihailo
 */
public class SOGetAllTermin extends AbstractSO{
    
    private ArrayList<Termin> lista;
    
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
     * Poziva metodu slect klase DBBroker i prima listu termina
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom selektovanja iz baze u bazu ili
     * prilikom kastovanja liste
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> termini = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Termin>) (ArrayList<?>) termini;
    }
    
    /**
     * @return vraca listu termina iz baze 
     */
    public ArrayList<Termin> getLista() {
        return lista;
    }
}
