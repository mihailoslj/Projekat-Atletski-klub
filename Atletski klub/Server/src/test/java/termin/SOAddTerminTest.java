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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import so.clan.SOGetAllClanTest;

/**
 *
 * @author Mihailo
 */
public class SOAddTerminTest {
    
    public SOAddTerminTest() {
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
     * Test of validate method, of class SOAddTermin.
     */
    @Test
    public void testValidatePogresanObjekat() throws Exception {
        SODeleteTermin pom = new SODeleteTermin();
        Kategorija k = new Kategorija();
        assertThrows(java.lang.Exception.class, ()-> pom.validate(k));
    }
    
    /**
     * Test of validate method, of class SOAddTermin.
     */
    @Test
    public void testValidateDatumUProslosti() throws Exception {
        Date datum = new Date();
        ArrayList<StavkaTermina> listaStavki = new ArrayList<>();
        listaStavki.add(new StavkaTermina(null, 1, "dsa", new Clan()));
        Termin t = new Termin(null, "dsa", datum, "sadad", 3, new Sala(), new Administrator(), listaStavki);
        SOAddTermin pom = new SOAddTermin();
        assertThrows(java.lang.Exception.class, ()-> pom.validate(t));
    }
    
    @Test
    public void testValidateNemaStavki() throws Exception {
        Date datum = new Date();
        ArrayList<StavkaTermina> listaStavki = new ArrayList<>();
        Termin t = new Termin(null, "dsa", datum, "sadad", 3, new Sala(), new Administrator(), null);
        SOAddTermin pom = new SOAddTermin();
        assertThrows(java.lang.Exception.class, ()-> pom.validate(t));
    }

    /**
     * Test of execute method, of class SOAddTermin.
     */
    @Test
    public void testExecute() throws Exception {
        isprazniTabelu();
        ArrayList<Termin> listaTermina = new ArrayList<>();
        
        Long id = Long.parseLong("1");
        Kategorija kategorija = new Kategorija(id, "neki naziv 1","neki opis 1");
        Clan c1 = new Clan(id, "marko", "markovic",
                "mare123@gmai.com", "2222332552533", kategorija);
        Sala s1 = new Sala(id, "naziv1");
        Administrator a1 = new Administrator(Long.parseLong("1"), "ana", "anic", "ana", "ana123");
        java.sql.Date datum1 = new java.sql.Date(System.currentTimeMillis() + 1000000);
        Termin t1 = new Termin(null, "naziv 1", datum1, "opis 1", 3, s1, a1, null);
        
        ArrayList<StavkaTermina> listaStavki = new ArrayList<>();
        listaStavki.add(new StavkaTermina(t1, 1, "napomena 1", c1));       
        listaStavki.add(new StavkaTermina(t1, 2, "napomena 2", c1));
        listaStavki.add(new StavkaTermina(t1, 3, "napomena 3", c1));
        listaStavki.add(new StavkaTermina(t1, 4, "napomena 4", c1));

        t1.setStavkeTermina(listaStavki);
        
        PreparedStatement ps = DBBroker.getInstance().insert(t1);
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long terminID = tableKeys.getLong(1);

        t1.setTerminID(terminID);
        
        ArrayList<AbstractDomainObject> listaPom = DBBroker.getInstance().select(new Termin());
        ArrayList<Termin> termini = (ArrayList<Termin>) (ArrayList<?>) listaPom;
        
        boolean b = false;
        for (Termin termin : termini) {
            if(termin.getTerminID().equals(t1.getTerminID())){
                b = true;
            }
        }
        isprazniTabelu();
        assertTrue(b);
    }

    private void isprazniTabelu() {
        try {
            String upit = "DELETE FROM termin";
            Statement s = DBBroker.getInstance().getConnection().createStatement();
            System.out.println(upit);
            s.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
