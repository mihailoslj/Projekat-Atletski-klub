/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.DBBroker;
import domen.AbstractDomainObject;
import java.sql.SQLException;

/**
 * Apstraktna klasa koju ce da implementiraju sve klase namenski kreirane za izvrsenje sistemskih
 * operacija. 
 *
 * @author Mihailo
 */
public abstract class AbstractSO {
    /**
     * Metoda kojom se vrsi validacija nad parametrima i baca greska ako je potrebno.
     * @param ado
     * @throws java.lang.Exception - ako paratar/ri nisu dobri
     */
    protected abstract void validate(AbstractDomainObject ado) throws Exception;
    /**
     * Metoda kojom se poziva odgovarajuca metoda DBBroker-a i primaju povratni podaci ako ih ima.
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom neke transakcije nad bazom ili
     * kastovanje povratnih podataka (ako ih ima)
     */
    protected abstract void execute(AbstractDomainObject ado) throws Exception;
    
    /**
     * Metoda koja poziva redom metode validate() i execute() i potvrdjuje transakcuju ako nije 
     * doslo do greske; u suprotnom ponistava transakciju nad bazom.
     * @param ado
     * @throws java.lang.Exception - ako se desi greska u metodama validete() ili execute()
     */
    public void templateExecute(AbstractDomainObject ado) throws Exception {
        try {
            validate(ado);
            execute(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }
    
    /**
     * potvrdjuje transakciju nad bazom
     * @throws java.sql.SQLException - ako dodje do greske prilikom commit-ovanja
     */
    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }
    
    /**
     * ponistava transajciju nad bazom
     * @throws java.sql.SQLException - ako dodje do greske prilikom rollback-ovanja
     */
    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
