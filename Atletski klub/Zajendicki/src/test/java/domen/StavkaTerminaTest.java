/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package domen;

import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 *
 * @author Mihailo
 */
public class StavkaTerminaTest {
    Termin t;
    Clan c;
    Kategorija k;
    StavkaTermina st;
    
    Connection connection;
    
    public StavkaTerminaTest() {
        try {
            String url = "jdbc:mysql://localhost:3306/testiranje_atletskiklub";
            connection = DriverManager.getConnection(url, "root", "");
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        t = new Termin();
        c = new Clan();
        k = new Kategorija();
        st = new StavkaTermina();
    }
    
    @AfterEach
    public void tearDown() {
        t = null;
        c = null;
        k = null;
        st = null;
    }

    /**
     * Test of vratiListu method, of class StavkaTermina.
     */
    @Test
    public void testVratiListuPraznaTabela() throws Exception {
        t.setTerminID(Long.parseLong("1"));
        st.setTermin(t);
        String upit = "SELECT * FROM " + st.nazivTabele() + " " + st.alijas()
                + " " + st.join() + " " + st.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        
        assertEquals(0, st.vratiListu(rs).size());
    }



    /**
     * Test of vrednostZaPrimarniKljuc method, of class StavkaTermina.
     */
    @Test
    public void testVrednostZaPrimarniKljucNull() {
        assertThrows(java.lang.NullPointerException.class,()-> st.vrednostZaPrimarniKljuc());
    }
    /**
     * Test of vrednostZaPrimarniKljuc method, of class StavkaTermina.
     */
    @Test
    public void testVrednostZaPrimarniKljucNullId() {
        st.setTermin(t);
        assertThrows(java.lang.NullPointerException.class,()-> st.vrednostZaPrimarniKljuc());
    }
    /**
     * Test of vrednostZaPrimarniKljuc method, of class StavkaTermina.
     */
    @Test
    public void testVrednostZaPrimarniKljuc() {
        long id = 1;
        t.setTerminID(id);
        st.setTermin(t);
        assertEquals(" terminID = 1", st.vrednostZaPrimarniKljuc());
    }

    /**
     * Test of vrednostiZaInsert method, of class StavkaTermina.
     */
    @Test
    public void testVrednostiZaInsertSveNull() {
        assertThrows(java.lang.NullPointerException.class,()-> st.vrednostiZaInsert());
    }
    
    /**
     * Test of vrednostiZaInsert method, of class StavkaTermina.
     */
    @Test
    public void testVrednostiZaInsertClanNull() {
        st.setNapomena("napomena");
        st.setRbStavke(1);
        st.setTermin(t);
        assertThrows(java.lang.NullPointerException.class,()-> st.vrednostiZaInsert());
    }

    /**
     * Test of uslov method, of class StavkaTermina.
     */
    @Test
    public void testUslovTerminNull() {
        assertThrows(java.lang.NullPointerException.class,()-> st.uslov());
    }
   
    /**
     * Test of uslov method, of class StavkaTermina.
     */
    @Test
    public void testUslov() {
        long id = 1;
        t.setTerminID(id);
        st.setTermin(t);
        assertEquals(" WHERE T.TERMINID = 1", st.uslov());
    }

    /**
     * Test of setTermin method, of class StavkaTermina.
     */
    @Test
    public void testSetTermin() {
        Termin t1 = new Termin();
        st.setTermin(t);
        
        t.setNazivTermina("termin 1");
        t.setDatumVreme(new Date());
        
        t1.setNazivTermina("termin 1");
        t1.setDatumVreme(new Date());
        
        assertEquals(t, st.getTermin());
    }

    /**
     * Test of setRbStavke method, of class StavkaTermina.
     */
    @Test
    public void testSetRbStavkeManjiOdJedan() {
        int rb = -1;
        assertThrows(java.lang.RuntimeException.class,()-> st.setRbStavke(rb));
    }
    
    /**
     * Test of setRbStavke method, of class StavkaTermina.
     */
    @Test
    public void testSetRbStavke() {
        int rb = 1;
        st.setRbStavke(rb);
        assertEquals(rb, st.getRbStavke());
    }
 

    /**
     * Test of setNapomena method, of class StavkaTermina.
     */
    @Test
    public void testSetNapomenaNull() {
        assertThrows(java.lang.NullPointerException.class,()-> st.setNapomena(null));
    }
    /**
     * Test of setNapomena method, of class StavkaTermina.
     */
    @Test
    public void testSetNapomenaPrazna() {
        String napomena = "";
        assertThrows(java.lang.RuntimeException.class,()-> st.setNapomena(napomena));
    }
    /**
     * Test of setNapomena method, of class StavkaTermina.
     */
    @Test
    public void testSetNapomena() {
        String napomena = "obavezna zasebna obuca";
        st.setNapomena(napomena);
        assertEquals("obavezna zasebna obuca", st.getNapomena());
    }

    /**
     * Test of setClan method, of class StavkaTermina.
     */
    @Test
    public void testSetClanNull() {
        assertThrows(java.lang.NullPointerException.class,()-> st.setClan(null));
    }
    /**
     * Test of setClan method, of class StavkaTermina.
     */
    @Test
    public void testSetClan() {
        Clan c1 = new Clan();
        long id = 1;
        c.setClanID(id);
        c1.setClanID(id);
        
        st.setClan(c1);
        
        assertEquals(c, st.getClan());
    }
    
}
