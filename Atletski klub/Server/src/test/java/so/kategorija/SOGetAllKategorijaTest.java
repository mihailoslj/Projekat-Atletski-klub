/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package so.kategorija;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Administrator;
import domen.Clan;
import domen.Kategorija;
import java.sql.PreparedStatement;
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
import so.clan.SOGetAllClan;
import so.clan.SOGetAllClanTest;

/**
 *
 * @author Mihailo
 */
public class SOGetAllKategorijaTest {
    
    public SOGetAllKategorijaTest() {
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
     * Test of validate method, of class SOGetAllKategorija.
     */
    @Test
    public void testValidatePogresanObjekat() throws Exception {
        SOGetAllKategorija pom = new SOGetAllKategorija();
        Administrator a = new Administrator();
        Exception e = assertThrows(java.lang.Exception.class, () -> pom.validate(a));
        System.out.println(e.getMessage());
    }

    /**
     * Test of execute method, of class SOGetAllKategorija.
     */
    @Test
    public void testExecute() throws Exception {
        isprazniTabelu();
        ArrayList<Kategorija> listaKategorija = new ArrayList<>();
        listaKategorija.add(new Kategorija(Long.parseLong("1"), "sadsaads", "sadsad"));       
        listaKategorija.add(new Kategorija(Long.parseLong("2"), "sadsaads", "sadsad"));
        listaKategorija.add(new Kategorija(Long.parseLong("3"), "sadsaads", "sadsad"));
        listaKategorija.add(new Kategorija(Long.parseLong("4"), "sadsaads", "sadsad"));

        ubaciKategorije(listaKategorija);
        
        ArrayList<AbstractDomainObject> kategorije = DBBroker.getInstance().select(new Kategorija());
        ArrayList<Clan> listaPom = (ArrayList<Clan>) (ArrayList<?>) kategorije;
        isprazniTabelu();
        
        assertEquals(4, listaPom.size());
        
    }

    private void isprazniTabelu() {
        try {
            String upit = "DELETE FROM kategorija";
            Statement s = DBBroker.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ubaciKategorije(ArrayList<Kategorija> listaKategorija) {
        try {
            String upit = "insert into kategorija values(?,?,?)";
            PreparedStatement ps = DBBroker.getInstance().getConnection().prepareStatement(upit);
            for (Kategorija kat : listaKategorija) {
                ps.setLong(1, kat.getKategorijaID());
                ps.setString(2, kat.getNazivKategorije());
                ps.setString(3, kat.getOpisKategorije());
                
                ps.addBatch();
            }
            ps.executeBatch();
            DBBroker.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
