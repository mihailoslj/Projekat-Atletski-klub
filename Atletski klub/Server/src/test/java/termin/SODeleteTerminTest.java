/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package termin;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Administrator;
import domen.Clan;
import domen.Kategorija;
import domen.Sala;
import domen.StavkaTermina;
import domen.Termin;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mihailo
 */
public class SODeleteTerminTest {
    
    public SODeleteTerminTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of validate method, of class SODeleteTermin.
     */
    @Test
    public void testValidatePogresanObjekat() throws Exception {
        SODeleteTermin pom = new SODeleteTermin();
        Kategorija k = new Kategorija();
        assertThrows(java.lang.Exception.class, ()-> pom.validate(k));
    }

    /**
     * Test of execute method, of class SODeleteTermin.
     */
    @Test
    public void testExecute() throws Exception {
        Long id = Long.parseLong("1");
        Kategorija kategorija = new Kategorija(id, "neki naziv 1","neki opis 1");
        Clan c1 = new Clan(id, "marko", "markovic",
                "mare123@gmai.com", "2222332552533", kategorija);
        Sala s1 = new Sala(id, "naziv1");
        Administrator a1 = new Administrator(Long.parseLong("1"), "ana", "anic", "ana", "ana123");
        Date datum1 = new Date(System.currentTimeMillis() + 1000000);
        Termin t1 = new Termin(null, "naziv 1", datum1, "opis 1", 3, s1, a1, null);
        
        ArrayList<StavkaTermina> listaStavki = new ArrayList<>();
        listaStavki.add(new StavkaTermina(t1, 1, "napomena 1", c1));       
        listaStavki.add(new StavkaTermina(t1, 2,"napomena 2", c1));
        listaStavki.add(new StavkaTermina(t1, 3, "napomena 3", c1));
        listaStavki.add(new StavkaTermina(t1, 4, "napomena 4", c1));

        t1.setStavkeTermina(listaStavki);
        
        PreparedStatement ps = DBBroker.getInstance().insert(t1);
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long terminID = tableKeys.getLong(1);
        
        t1.setTerminID(terminID);
        
        SODeleteTermin pom = new SODeleteTermin();
        pom.execute(t1);
        
        ArrayList<AbstractDomainObject> termini = DBBroker.getInstance().select(new Termin());
        ArrayList<Termin> listaPom = (ArrayList<Termin>) (ArrayList<?>) termini;
        
        boolean b = true;
        for (Termin t: listaPom) {
            if(t.getTerminID().equals(t1.getTerminID())){
                b = false;
            }
        }
        assertTrue(b);
    }

    private void isprazniTabelu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
