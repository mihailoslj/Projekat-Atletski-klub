/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package domen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class KategorijaTest {
    Connection connection;
    Kategorija k;
    
    public KategorijaTest() {
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
        k = new Kategorija();
    }
    
    @AfterEach
    public void tearDown() {
        k = null;
    }

    /**
     * Test of toString method, of class Kategorija.
     */
    @Test
    public void testToStringNull() {
        assertThrows(java.lang.NullPointerException.class,()-> k.toString());
    }
    /**
     * Test of toString method, of class Kategorija.
     */
    @Test
    public void testToString() {
        k.setNazivKategorije("kategorija 1");
        assertEquals("kategorija 1", k.toString());
    }

    /**
     * Test of vratiListu method, of class Kategorija.
     */
    @Test
    public void testVratiListuPraznaLista() throws Exception {
        String upit = "SELECT * FROM " + k.nazivTabele() + " " + k.alijas()
                + " " + k.join() + " " + k.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        
        assertEquals(0, k.vratiListu(rs).size());
    }
    
//    /**
//     * Test of vratiListu method, of class Kategorija.
//     */
//    @Test
//    public void testVratiListuDveKategorije() throws Exception {
//        k.setNazivKategorije("kategorija 4");
//        k.setOpisKategorije("opis 4");
//        
//        Kategorija k1 = new Kategorija(null, "kategorija 5", "opis 4");
//        
//        ubaciUlistu(k);
//        ubaciUlistu(k1);
//        
//        String upit = "SELECT * FROM " + k.nazivTabele() + " " + k.alijas()
//                + " " + k.join() + " " + k.uslov();
//        System.out.println(upit);
//        Statement s = connection.createStatement();
//        ResultSet rs = s.executeQuery(upit);
//        
//        assertEquals(2, k.vratiListu(rs).size());
//    }


    /**
     * Test of vrednostZaPrimarniKljuc method, of class Kategorija.
     */
    @Test
    public void testVrednostZaPrimarniKljucNull() {
        assertThrows(java.lang.NullPointerException.class,()-> k.vrednostZaPrimarniKljuc());
    }
    /**
     * Test of vrednostZaPrimarniKljuc method, of class Kategorija.
     */
    @Test
    public void testVrednostZaPrimarniKljucIdManjiODJedan() {
        long id = -1;
        k.setKategorijaID(id);
        assertThrows(java.lang.RuntimeException.class,()-> k.vrednostZaPrimarniKljuc());
    }
    /**
     * Test of vrednostZaPrimarniKljuc method, of class Kategorija.
     */
    @Test
    public void testVrednostZaPrimarniKljuc() {
        long id = 1;
        k.setKategorijaID(id);
        assertEquals(" KategorijaID = 1", k.vrednostZaPrimarniKljuc());
    }
    /**
     * Test of setNazivKategorije method, of class Kategorija.
     */
    @Test
    public void testSetNazivKategorijeNull() {
        assertThrows(java.lang.NullPointerException.class,()-> k.setNazivKategorije(null));
    }
    /**
     * Test of setNazivKategorije method, of class Kategorija.
     */
    @Test
    public void testSetNazivKategorijeKraceOdDva() {
        assertThrows(java.lang.RuntimeException.class,()-> k.setNazivKategorije("a"));
    }
    /**
     * Test of setNazivKategorije method, of class Kategorija.
     */
    @Test
    public void testSetNazivKategorije() {
        k.setNazivKategorije("junior");
        assertEquals("junior", k.getNazivKategorije());
    }


    /**
     * Test of setOpisKategorije method, of class Kategorija.
     */
    @Test
    public void testSetOpisKategorijeNull() {
        assertThrows(java.lang.NullPointerException.class,()-> k.setOpisKategorije(null));
    }
    /**
     * Test of setOpisKategorije method, of class Kategorija.
     */
    @Test
    public void testSetOpisKategorijeKraceOdCetiri() {
        assertThrows(java.lang.RuntimeException.class,()-> k.setOpisKategorije("aaa"));
    }
    /**
     * Test of setOpisKategorije method, of class Kategorija.
     */
    @Test
    public void testSetOpisKategorije() {
        k.setOpisKategorije("kategorija za decu mladju od 13 godina i pocetnike");
        assertEquals("kategorija za decu mladju od 13 godina i pocetnike", k.getOpisKategorije());
    }
    
//    public boolean ubaciUlistu(Kategorija k){
//        try {
//            String upit = "INSERT INTO " + k.nazivTabele() + " "
//                    +"(nazivKategorije, opisKategorije)"+ " VALUES(?,?)";
//            
//            
//            System.out.println(upit);
//            PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
//            
//            ps.setString(1, k.getNazivKategorije());
//            ps.setString(2, k.getOpisKategorije());
//            
//            ps.executeUpdate();
//            connection.commit();
//            return true;
//        } catch (SQLException ex) {
//            Logger.getLogger(KategorijaTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
    
}
