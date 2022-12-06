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
public class ClanTest {
    
    Connection connection;
    Clan c;
    Kategorija k;
    
    public ClanTest() {
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
        c = new Clan();
        k = new Kategorija();
    }
    
    @AfterEach
    public void tearDown() {
        c = null;
        k = null;
    }

    /**
     * Test of toString method, of class Clan.
     */
    @Test
    public void testToStringSveNull() {
        assertThrows(java.lang.NullPointerException.class, ()-> c.toString());
    }

    /**
     * Test of vratiListu method, of class Clan.
     */
    @Test
    public void testVratiListuPraznaTabela() throws Exception {
        
        String upit = "SELECT * FROM " + c.nazivTabele() + " " + c.alijas()
                + " " + c.join() + " " + c.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        
        assertEquals(0, c.vratiListu(rs).size());
    }

    /**
     * Test of vrednostZaPrimarniKljuc method, of class Clan.
     */
    @Test
    public void testVrednostZaPrimarniKljucNull() {
        assertThrows(java.lang.NullPointerException.class, ()-> c.vrednostZaPrimarniKljuc());
    }
    
    /**
     * Test of vrednostZaPrimarniKljuc method, of class Clan.
     */
    @Test
    public void testVrednostZaPrimarniKljuc() {
        long l = 1;
        c.setClanID(l);
        assertEquals(" clanID = 1", c.vrednostZaPrimarniKljuc());
    }

    /**
     * Test of vrednostiZaInsert method, of class Clan.
     */
    @Test
    public void testVrednostiZaInsertSveNull() {
        assertThrows(java.lang.NullPointerException.class, () -> c.vrednostiZaInsert());
    }
    /**
     * Test of vrednostiZaInsert method, of class Clan.
     */
    @Test
    public void testVrednostiZaInsertKategorijaNull() {
        long l = 1;
        c.setClanID(l);
        c.setEmail("JANKO@GMAIL.COM");
        c.setImeClana("janko");
        c.setPrezimeClana("jankovic");
        c.setTelefon("+38164646464");
        assertThrows(java.lang.NullPointerException.class, () -> c.vrednostiZaInsert());
    }

    /**
     * Test of vrednostiZaUpdate method, of class Clan.
     */
    @Test
    public void testVrednostiZaUpdateSveNull() {
        assertThrows(java.lang.NullPointerException.class, () -> c.vrednostiZaUpdate());
    }
    
    /**
     * Test of vrednostiZaInsert method, of class Clan.
     */
    @Test
    public void testVrednostiZaUpdateKategorijaNull() {
        long l = 1;
        c.setClanID(l);
        c.setEmail("JANKO@GMAIL.COM");
        c.setImeClana("janko");
        c.setPrezimeClana("jankovic");
        c.setTelefon("+38164646464");
        assertThrows(java.lang.NullPointerException.class, () -> c.vrednostiZaInsert());
    }


    /**
     * Test of setImeClana method, of class Clan.
     */
    @Test
    public void testSetImeClanaNull() {
        assertThrows(java.lang.NullPointerException.class, () -> c.setImeClana(null));
    }
    
    /**
     * Test of setImeClana method, of class Clan.
     */
    @Test
    public void testSetImeClanaDuzina() {
        assertThrows(java.lang.RuntimeException.class, () -> c.setImeClana("a"));
    }
    
    /**
     * Test of setImeClana method, of class Clan.
     */
    @Test
    public void testSetImeClana() {
        c.setImeClana("marko");
        assertEquals("marko", c.getImeClana());
    }


    /**
     * Test of setPrezimeClana method, of class Clan.
     */
    @Test
    public void testSetPrezimeClanaNull() {
        assertThrows(java.lang.NullPointerException.class, () -> c.setPrezimeClana(null));
    }
    
    /**
     * Test of setPrezimeClana method, of class Clan.
     */
    @Test
    public void testSetPrezimeClanaDuzina() {
        assertThrows(java.lang.RuntimeException.class, () -> c.setPrezimeClana("a"));
    }
    
    
    /**
     * Test of setPrezimeClana method, of class Clan.
     */
    @Test
    public void testSetPrezimeClana() {
        c.setImeClana("markovic");
        assertEquals("markovic", c.getImeClana());
    }

    /**
     * Test of setEmail method, of class Clan.
     */
    @Test
    public void testSetEmailNull() {
         assertThrows(java.lang.NullPointerException.class, () -> c.setEmail(null));
    }
    
    /**
     * Test of setEmail method, of class Clan.
     */
    @Test
    public void testSetEmailFormat() {
         assertThrows(java.lang.RuntimeException.class, () -> c.setEmail("a"));
    }
    
    /**
     * Test of setEmail method, of class Clan.
     */
    @Test
    public void testSetEmail() {
         c.setEmail("nekimejl@gmail.com");
         assertEquals("nekimejl@gmail.com", c.getEmail());
    }

    /**
     * Test of setTelefon method, of class Clan.
     */
    @Test
    public void testSetTelefonNull() {
        assertThrows(java.lang.NullPointerException.class, () -> c.setTelefon(null));
    }
    
    /**
     * Test of setTelefon method, of class Clan.
     */
    @Test
    public void testSetTelefonFormat() {
        assertThrows(java.lang.RuntimeException.class, () -> c.setTelefon("abc123"));
    }
    /**
     * Test of setTelefon method, of class Clan.
     */
    @Test
    public void testSetTelefonDuzina() {
        assertThrows(java.lang.RuntimeException.class, () -> c.setTelefon("0646412"));
    }
    
    /**
     * Test of setTelefon method, of class Clan.
     */
    @Test
    public void testSetTelefon() {
        c.setTelefon("+38106412345678");
        assertEquals("+38106412345678", c.getTelefon());
    }


    /**
     * Test of setKategorija method, of class Clan.
     */
    @Test
    public void testSetKategorijaNull() {
        assertThrows(java.lang.NullPointerException.class, ()-> c.setKategorija(null));
    }
    /**
     * Test of setKategorija method, of class Clan.
     */
    @Test
    public void testSetKategorija() {
        Kategorija k1 = new Kategorija();
        
        k.setNazivKategorije("Junior");
        k1.setNazivKategorije("Junior");

        c.setKategorija(k);

        assertEquals(k1, c.getKategorija());
    }
}
