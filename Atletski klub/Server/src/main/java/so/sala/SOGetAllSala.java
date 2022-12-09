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
 * Klasa za vracanje svih sala iz baze.
 * Nasledjuje kasu AbstractSO i implementira metode validate() i execute()
 *
 * @author Mihailo
 */
public class SOGetAllSala extends AbstractSO{
    private ArrayList<Sala> lista;
    
    /**
     * Provera validnosti parametra.
     * @param ado
     * @throws java.lang.Exception - ako prosledjeni objekat nije instacna klase Sala
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Sala)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sala!");
        }
    }
    
    /**
     * Poziva metodu slect klase DBBroker i prima listu sala
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom selektovanja iz baze u bazu ili
     * prilikom kastovanja liste
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> sale = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Sala>) (ArrayList<?>) sale;
    }
    
    /**
     * @return vraca listu sala iz baze 
     */
    public ArrayList<Sala> getLista() {
        return lista;
    }
}
