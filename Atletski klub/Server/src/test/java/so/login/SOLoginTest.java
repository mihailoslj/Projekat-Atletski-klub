/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package so.login;

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
import so.sala.SOGetAllSala;

/**
 *
 * @author Mihailo
 */
public class SOLoginTest {
    
    public SOLoginTest() {
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
     * Test of validate method, of class SOLogin.
     */
    @Test
    public void testValidatePogresanObjekat() throws Exception {
        SOLogin pom = new SOLogin();
        Sala s = new Sala();
        Exception e = assertThrows(java.lang.Exception.class, () -> pom.validate(s));
        System.out.println(e.getMessage());
    }

    /**
     * Test of execute method, of class SOLogin.
     */
    @Test
    public void testExecute() throws Exception {
        isprazniTabelu();
        ArrayList<Administrator> listaAdmina = new ArrayList<>();
        listaAdmina.add(new Administrator(Long.parseLong("1"), "ana", "anic", "ana", "ana123"));       
        listaAdmina.add(new Administrator(Long.parseLong("2"), "marko", "markovic", "marko", "marko123"));

        ubaciAdmine(listaAdmina);
        
        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().select(new Administrator());
        
        Administrator a = listaAdmina.get(0);
        
        boolean b = false;
        
        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                b = true;
            }
            
        }
        isprazniTabelu();
        
        assertTrue(b);
        
    }

    private void isprazniTabelu() {
        try {
            String upit = "DELETE FROM administrator";
            Statement s = DBBroker.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ubaciAdmine(ArrayList<Administrator> listaAdmina) {
        try {
            String upit = "insert into administrator values(?,?,?,?,?)";
            PreparedStatement ps = DBBroker.getInstance().getConnection().prepareStatement(upit);
            for (Administrator a : listaAdmina) {
                ps.setLong(1, a.getAdministratorID());
                ps.setString(2, a.getIme());
                ps.setString(3, a.getPrezime());
                ps.setString(4, a.getUsername());
                ps.setString(5, a.getPassword());
                
                ps.addBatch();
               }
            
            ps.executeBatch();
            DBBroker.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
