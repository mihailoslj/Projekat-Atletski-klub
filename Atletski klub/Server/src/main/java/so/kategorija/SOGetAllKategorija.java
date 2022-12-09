/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kategorija;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Kategorija;
import java.util.ArrayList;
import so.AbstractSO;

/**
 * Klasa za vracanje svih kategorija iz baze.
 * Nasledjuje kasu AbstractSO i implementira metode validate() i execute()
 *
 * @author Mihailo
 */
public class SOGetAllKategorija extends AbstractSO{
    
    private ArrayList<Kategorija> lista;
    
    /**
     * Provera validnosti parametra.
     * @param ado
     * @throws java.lang.Exception - ako prosledjeni objekat nije instacna klase Kategorija
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kategorija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kategorija!");
        }
    }
    
    /**
     * Poziva metodu slect klase DBBroker i prima listu kategorija
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom selektovanja iz baze u bazu ili
     * prilikom kastovanja liste
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> kategorije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Kategorija>) (ArrayList<?>) kategorije;
    }
    
    /**
     * @return vraca listu kategorija iz baze 
     */
    public ArrayList<Kategorija> getLista() {
        return lista;
    }
}
