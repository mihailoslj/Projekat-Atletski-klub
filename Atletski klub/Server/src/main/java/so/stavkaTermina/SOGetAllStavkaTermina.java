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
 * Klasa za vracanje stavki termina, nekog termina.
 * Nasledjuje kasu AbstractSO i implementira metode validate() i execute()
 * @author Mihailo
 */
public class SOGetAllStavkaTermina extends AbstractSO{
    private ArrayList<StavkaTermina> lista;
    
    /**
     * Provera validnosti parametra.
     * @param ado
     * @throws java.lang.Exception - ako prosledjeni objekat nije instacna klase StavkaTermina
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaTermina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaTermina!");
        }
    }
    
    /**
     * Poziva metodu slect klase DBBroker i prima listu stavki termina
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom selektovanja iz baze u bazu ili
     * prilikom kastovanja liste
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> termini = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaTermina>) (ArrayList<?>) termini;
    }
    
    /**
     * @return vraca listu stavki termina iz baze 
     */
    public ArrayList<StavkaTermina> getLista() {
        return lista;
    }
}
