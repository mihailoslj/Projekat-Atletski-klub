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
public class AdministratorTest {

    Administrator a;
    Connection connection;

    public AdministratorTest() {
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
        a = new Administrator();
    }

    @AfterEach
    public void tearDown() {
        a = null;
    }

    /**
     * Test of setUsername method, of class Administrator.
     */
    @Test
    public void testSetUsernameNull() {
        assertThrows(java.lang.NullPointerException.class, () -> a.setUsername(null));
    }

    /**
     * Test of setUsername method, of class Administrator.
     */
    @Test
    public void testSetUsernameDuzina() {
        assertThrows(java.lang.RuntimeException.class, () -> a.setUsername("a"));
    }

    /**
     * Test of setPassword method, of class Administrator.
     */
    @Test
    public void testSetPasswordNull() {
        assertThrows(java.lang.NullPointerException.class, () -> a.setPassword(null));
    }

    /**
     * Test of setPassword method, of class Administrator.
     */
    @Test
    public void testSetPasswordDuzina() {
        assertThrows(java.lang.RuntimeException.class, () -> a.setPassword("a"));
    }

    /**
     * Test of setIme method, of class Administrator.
     */
    @Test
    public void testSetImeNull() {
        assertThrows(java.lang.NullPointerException.class, () -> a.setIme(null));
    }

    /**
     * Test of setIme method, of class Administrator.
     */
    @Test
    public void testSetImeDuzina() {
        assertThrows(java.lang.RuntimeException.class, () -> a.setIme("a"));
    }

    /**
     * Test of setPrezime method, of class Administrator.
     */
    @Test
    public void testSetPrezimeNull() {
        assertThrows(java.lang.NullPointerException.class, () -> a.setPrezime("a"));

    }

    /**
     * Test of setPrezime method, of class Administrator.
     */
    @Test
    public void testSetPrezimeDuzina() {
        assertThrows(java.lang.RuntimeException.class, () -> a.setPrezime("a"));
    }

    /**
     * Test of toString method, of class Administrator.
     */
    @Test
    public void testToStringNemaNista() {
        assertThrows(java.lang.NullPointerException.class, () -> a.toString());
    }

    /**
     * Test of toString method, of class Administrator.
     */
    @Test
    public void testToStringNemaPrezime() {
        a.setIme("Petar");
        assertThrows(java.lang.NullPointerException.class, () -> a.toString());
    }
    
    /**
     * Test of toString method, of class Administrator.
     */
    @Test
    public void testToString() {
        a.setIme("Petar");
        a.setPrezime("Peric");
        assertEquals("Petar Peric", a.toString());
    }

    /**
     * Test of vratiListu method, of class Administrator. Testira se na pomocnoj bazi koja u 
     * ovom testu ne sadrzi nijednog administratora.
     */
    @Test
    public void testVratiListuPraznaLista() throws Exception {
        
        String upit = "SELECT * FROM administrator";
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        
        assertEquals(0, a.vratiListu(rs).size());
    }

    /**
     * Test of vrednostZaPrimarniKljuc method, of class Administrator.
     */
    @Test
    public void testVrednostZaPrimarniKljucNull() {
        a.setAdministratorID(null);
        assertThrows(java.lang.NullPointerException.class, () -> a.vrednostZaPrimarniKljuc());
    }
    /**
     * Test of vrednostZaPrimarniKljuc method, of class Administrator.
     */
    @Test
    public void testVrednostZaPrimarniKljucManjeOd1() {
        long id = -1;
        a.setAdministratorID(id);
        assertThrows(java.lang.RuntimeException.class, () -> a.vrednostZaPrimarniKljuc());
    }

    /**
     * Test of vrednostiZaInsert method, of class Administrator.
     */
    @Test
    public void testVrednostiZaInsert() {
        assertThrows(java.lang.NullPointerException.class,() -> a.vrednostiZaInsert());
    }

    /**
     * Test of vrednostiZaUpdate method, of class Administrator.
     */
    @Test
    public void testVrednostiZaUpdate() {
        assertThrows(java.lang.NullPointerException.class,() -> a.vrednostiZaUpdate());
    }

    

}
