/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package termin;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.StavkaTermina;
import domen.Termin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.AbstractSO;

/**
 * Klasa za dodavanje termina u bazu.
 * Nasledjuje kasu AbstractSO i implementira metode validate() i execute()
 *
 * @author Mihailo
 */
public class SOAddTermin extends AbstractSO{
    
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
     * Poziva se metoda insert klase DBBroker i ubacuje termin u bazu.
     * Prvo se ubacuje termin, pa onda na osnovu auto-generisanog primarnog kljuca (terminID) se 
     * ubaciju i njegove stavke.
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom ubacivanja termina ili prilikom
     * ubacivanja njegovih stavki
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long terminID = tableKeys.getLong(1);

        Termin t = (Termin) ado;
        t.setTerminID(terminID);

        for (StavkaTermina stavkaTermina : t.getStavkeTermina()) {
            stavkaTermina.setTermin(t);
            DBBroker.getInstance().insert(stavkaTermina);
        }

    }
}
