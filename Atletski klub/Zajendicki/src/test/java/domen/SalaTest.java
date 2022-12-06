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
public class SalaTest {
    Connection connection;
    
    Sala s;
    
    public SalaTest() {
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
    }
    
    @AfterEach
    public void tearDown() {
        s = null;
    }

    /**
     * Test of toString method, of class Sala.
     */
    @Test
    public void testToStringNull() {
        assertThrows(java.lang.NullPointerException.class,()-> s.toString());
    }
    
    /**
     * Test of toString method, of class Sala.
     */
    @Test
    public void testToString() {
      s.setNazivSale("Pionir");
        assertEquals("Pionir", s.getNazivSale());
    }

    /**
     * Test of vratiListu method, of class Sala.
     */
    @Test
    public void testVratiListuPraznaTabela() throws Exception {
        String upit = "SELECT * FROM " + s.nazivTabele() + " " + s.alijas()
                + " " + s.join() + " " + s.uslov();
        System.out.println(upit);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        
        assertEquals(0, s.vratiListu(rs).size());
    }

    /**
     * Test of vrednostZaPrimarniKljuc method, of class Sala.
     */
    @Test
    public void testVrednostZaPrimarniKljucNull() {
        assertThrows(java.lang.NullPointerException.class,()-> s.vrednostZaPrimarniKljuc());
    }
    
    /**
     * Test of vrednostZaPrimarniKljuc method, of class Sala.
     */
    @Test
    public void testVrednostZaPrimarniKljucManjeOdJedan() {
        long id = -1;
        s.setSalaID(id);
        assertThrows(java.lang.RuntimeException.class,()-> s.vrednostZaPrimarniKljuc());
    }
    /**
     * Test of vrednostZaPrimarniKljuc method, of class Sala.
     */
    @Test
    public void testVrednostZaPrimarniKljuc() {
        long id = 1;
        s.setSalaID(id);
        assertEquals(" SalaID = 1", s.vrednostZaPrimarniKljuc());
    }


    /**
     * Test of setNazivSale method, of class Sala.
     */
    @Test
    public void testSetNazivSaleNull() {
       assertThrows(java.lang.NullPointerException.class,()-> s.setNazivSale(null));
    }
    /**
     * Test of setNazivSale method, of class Sala.
     */
    @Test
    public void testSetNazivSaleDuzina() {
       assertThrows(java.lang.RuntimeException.class,()-> s.setNazivSale("a"));
    }
    /**
     * Test of setNazivSale method, of class Sala.
     */
    @Test
    public void testSetNazivSale() {
       s.setNazivSale("Pionir");
        assertEquals("Pionir", s.getNazivSale());
    }
    
}
