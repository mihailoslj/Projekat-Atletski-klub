/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package so.sala;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Administrator;
import domen.Clan;
import domen.Kategorija;
import domen.Sala;
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
import so.clan.SOGetAllClanTest;
import so.kategorija.SOGetAllKategorija;

/**
 *
 * @author Mihailo
 */
public class SOGetAllSalaTest {
    
    public SOGetAllSalaTest() {
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
        SOGetAllSala pom = new SOGetAllSala();
        Administrator a = new Administrator();
        Exception e = assertThrows(java.lang.Exception.class, () -> pom.validate(a));
        System.out.println(e.getMessage());
    }

    /**
     * Test of execute method, of class SOGetAllSala.
     */
    @Test
    public void testExecute() throws Exception {
        isprazniTabelu();
        ArrayList<Sala> listaSala = new ArrayList<>();
        listaSala.add(new Sala(Long.parseLong("1"),"asdsa"));       
        listaSala.add(new Sala(Long.parseLong("2"),"asdsa"));
        listaSala.add(new Sala(Long.parseLong("3"),"asdsa"));
        listaSala.add(new Sala(Long.parseLong("4"),"asdsa"));

        ubaciSale(listaSala);
        
        ArrayList<AbstractDomainObject> sale = DBBroker.getInstance().select(new Sala());
        ArrayList<Sala> listaPom = (ArrayList<Sala>) (ArrayList<?>) sale;
        
        assertEquals(4, listaPom.size());
        isprazniTabelu();
    }


    private void isprazniTabelu() {
        try {
            String upit = "DELETE FROM sala";
            Statement s = DBBroker.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ubaciSale(ArrayList<Sala> listaSala) {
        try {
            String upit = "insert into sala values(?,?)";
            PreparedStatement ps = DBBroker.getInstance().getConnection().prepareStatement(upit);
            for (Sala sala : listaSala) {
                ps.setLong(1, sala.getSalaID());
                ps.setString(2, sala.getNazivSale());
                
                ps.addBatch();
               }
            
            ps.executeBatch();
            DBBroker.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
