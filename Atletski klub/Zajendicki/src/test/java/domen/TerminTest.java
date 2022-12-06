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
public class TerminTest {
    Sala s;
    Administrator a;
    ArrayList<StavkaTermina> listaStavki;
    Termin t;
    
    Connection connection;
    
    public TerminTest() {
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
        s = new Sala();
        a = new Administrator();
        
        listaStavki = new ArrayList<>();
        StavkaTermina st1 = new StavkaTermina();
        StavkaTermina st2 = new StavkaTermina();
        listaStavki.add(st1);
        listaStavki.add(st2);
        
        t = new Termin();
    }
    
    @AfterEach
    public void tearDown() {
        s = null;
        a = null;
        listaStavki = null;
        t = null;
    }

    /**
     * Test of vratiListu method, of class Termin.
     */
    @Test
    public void testVratiListu() throws Exception {
        s.setSalaID(Long.parseLong("1"));
        a.setAdministratorID(Long.parseLong("1"));
        t.setSala(s);
        t.setAdministrator(a);
        String upit = "SELECT * FROM " + t.nazivTabele() + " " + t.alijas()
                + " " + t.join() + " " + t.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        
        assertEquals(0, t.vratiListu(rs).size());
    }


    /**
     * Test of vrednostZaPrimarniKljuc method, of class Termin.
     */
    @Test
    public void testVrednostZaPrimarniKljucNull() {
        assertThrows(java.lang.NullPointerException.class,()-> t.vrednostZaPrimarniKljuc());
    }
    /**
     * Test of vrednostZaPrimarniKljuc method, of class Termin.
     */
    @Test
    public void testVrednostZaPrimarniKljucManjeOdJedan() {
        long id = -1;
        t.setTerminID(id);
        assertThrows(java.lang.RuntimeException.class,()-> t.vrednostZaPrimarniKljuc());
    }
    /**
     * Test of vrednostZaPrimarniKljuc method, of class Termin.
     */
    @Test
    public void testVrednostZaPrimarniKljuc() {
        long id = 1;
        t.setTerminID(id);
        assertEquals(" terminID = 1", t.vrednostZaPrimarniKljuc());
    }

    /**
     * Test of vrednostiZaInsert method, of class Termin.
     */
    @Test
    public void testVrednostiZaInsertSveNull() {
        assertThrows(java.lang.NullPointerException.class,()-> t.vrednostiZaInsert());
    }
    /**
     * Test of vrednostiZaInsert method, of class Termin.
     */
    @Test
    public void testVrednostiZaInsertSalaNull() {
        t.setAdministrator(a);
        t.setDatumVreme(new Date());
        t.setMaxClanova(3);
        t.setNazivTermina("naziv");
        t.setOpisTermina("opiss");
        t.setStavkeTermina(listaStavki);
        assertThrows(java.lang.NullPointerException.class,()-> t.vrednostiZaInsert());
    }
    /**
     * Test of vrednostiZaInsert method, of class Termin.
     */
    @Test
    public void testVrednostiZaInsertAdministratorNull() {
        t.setSala(s);
        t.setDatumVreme(new Date());
        t.setMaxClanova(3);
        t.setNazivTermina("naziv");
        t.setOpisTermina("opiss");
        t.setStavkeTermina(listaStavki);
        assertThrows(java.lang.NullPointerException.class,()-> t.vrednostiZaInsert());
    }

    /**
     * Test of vrednostiZaUpdate method, of class Termin.
     */
    @Test
    public void testVrednostiZaUpdateSveNull() {
       assertThrows(java.lang.NullPointerException.class,()-> t.vrednostiZaUpdate());
    }
    /**
     * Test of vrednostiZaUpdate method, of class Termin.
     */
    @Test
    public void testVrednostiZaUpdateSalaNull() {
       t.setAdministrator(a);
        t.setDatumVreme(new Date());
        t.setMaxClanova(3);
        t.setNazivTermina("naziv");
        t.setOpisTermina("opiss");
        t.setStavkeTermina(listaStavki);
        assertThrows(java.lang.NullPointerException.class,()-> t.vrednostiZaUpdate());
    }

    /**
     * Test of setNazivTermina method, of class Termin.
     */
    @Test
    public void testSetNazivTerminaNull() {
       assertThrows(java.lang.NullPointerException.class,()-> t.setNazivTermina(null));
    }
    /**
     * Test of setNazivTermina method, of class Termin.
     */
    @Test
    public void testSetNazivTerminaKraceOdDva() {
       assertThrows(java.lang.RuntimeException.class,()-> t.setNazivTermina("a"));
    }
    /**
     * Test of setNazivTermina method, of class Termin.
     */
    @Test
    public void testSetNazivTermina() {
       t.setNazivTermina("naziv 1");
        assertEquals("naziv 1", t.getNazivTermina());
    }

    /**
     * Test of setDatumVreme method, of class Termin.
     */
    @Test
    public void testSetDatumVremeNull() {
        assertThrows(java.lang.NullPointerException.class,()-> t.setDatumVreme(null));
    }
    /**
     * Test of setDatumVreme method, of class Termin.
     */
    @Test
    public void testSetDatumVremeDatumUProslosti() {
        Date proslost = new Date(System.currentTimeMillis() - 1000000);
        assertThrows(java.lang.RuntimeException.class,()-> t.setDatumVreme(proslost));
    }
    /**
     * Test of setDatumVreme method, of class Termin.
     */
    @Test
    public void testSetDatumVreme() {
        Date trenutnoVreme = new Date();
        t.setDatumVreme(trenutnoVreme);
        assertEquals(trenutnoVreme, t.getDatumVreme());
    }

    /**
     * Test of setOpisTermina method, of class Termin.
     */
    @Test
    public void testSetOpisTerminaNull() {
        assertThrows(java.lang.NullPointerException.class,()-> t.setOpisTermina(null));
    }
    /**
     * Test of setOpisTermina method, of class Termin.
     */
    @Test
    public void testSetOpisTerminaPrazan() {
        assertThrows(java.lang.RuntimeException.class,()-> t.setOpisTermina(""));
    }
    /**
     * Test of setOpisTermina method, of class Termin.
     */
    @Test
    public void testSetOpisTermina() {
        t.setOpisTermina("opis");
        assertEquals("opis", t.getOpisTermina());
    }

    /**
     * Test of setMaxClanova method, of class Termin.
     */
    @Test
    public void testSetMaxClanovaManjeOdJedan() {
        assertThrows(java.lang.RuntimeException.class,()-> t.setMaxClanova(-1));
    }
    /**
     * Test of setMaxClanova method, of class Termin.
     */
    @Test
    public void testSetMaxClanova() {
        int max = 1;
        t.setMaxClanova(max);
        assertEquals(1, t.getMaxClanova());
    }

    /**
     * Test of setSala method, of class Termin.
     */
    @Test
    public void testSetSalaNull() {
        assertThrows(java.lang.NullPointerException.class,()-> t.setSala(null));
    }
    /**
     * Test of setSala method, of class Termin.
     */
    @Test
    public void testSetSala() {
        Sala s1 = new Sala();
        long id = 1;
        s.setSalaID(id);
        s1.setSalaID(id);
        
        t.setSala(s1);
        
        assertEquals(s, t.getSala());
    }

    /**
     * Test of setAdministrator method, of class Termin.
     */
    @Test
    public void testSetAdministratorNull() {
       assertThrows(java.lang.NullPointerException.class,()-> t.setAdministrator(null));
    }
    /**
     * Test of setAdministrator method, of class Termin.
     */
    @Test
    public void testSetAdministrator() {
        Administrator a1 = new Administrator();
        long id = 1;
        a.setAdministratorID(id);
        a1.setAdministratorID(id);
        
        t.setAdministrator(a1);
        
        assertEquals(a, t.getAdministrator());
    }

    /**
     * Test of setStavkeTermina method, of class Termin.
     */
    @Test
    public void testSetStavkeTerminaPraznaLista() {
        listaStavki = new ArrayList<>();
        assertThrows(java.lang.RuntimeException.class, ()-> t.setStavkeTermina(listaStavki));
    }
    /**
     * Test of setStavkeTermina method, of class Termin.
     */
    @Test
    public void testSetStavkeTermina() {
        t.setStavkeTermina(listaStavki);
        assertEquals(2, t.getStavkeTermina().size());
    }
    
}
