/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package so.stavkaTermina;

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
public class SOGetAllStavkaTerminaTest {
    
    public SOGetAllStavkaTerminaTest() {
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
     * Test of validate method, of class SOGetAllStavkaTermina.
     */
    @Test
    public void testValidatePogresanObjekat() throws Exception {
        SOGetAllStavkaTermina pom = new SOGetAllStavkaTermina();
        Sala s = new Sala();
        Exception e = assertThrows(java.lang.Exception.class, () -> pom.validate(s));
        System.out.println(e.getMessage());
    }

    /**
     * Test of execute method, of class SOGetAllStavkaTermina.
     */
    @Test
    public void testExecute() throws Exception {
//        Long id = Long.parseLong("1");
//        Kategorija kategorija = new Kategorija(id, "neki naziv 1","neki opis 1");
//        Clan c1 = new Clan(id, "marko", "markovic",
//                "mare123@gmai.com", "2222332552533", kategorija);
//        Date datum = new Date();

//        Long id = Long.parseLong("1");
//        Kategorija kategorija = new Kategorija(id, "neki naziv 1","neki opis 1");
//        Clan c1 = new Clan(id, "marko", "markovic",
//                "mare123@gmai.com", "2222332552533", kategorija);
//        Sala s1 = new Sala(id, "naziv1");
//        Administrator a1 = new Administrator(Long.parseLong("1"), "ana", "anic", "ana", "ana123");
//        java.sql.Date datum1 = new java.sql.Date(System.currentTimeMillis() + 1000000);
//        Termin t1 = new Termin(null, "naziv 1", datum1, "opis 1", 3, s1, a1, null);
//        
//        isprazniTabelu();
//        ArrayList<StavkaTermina> listaStavki = new ArrayList<>();
//        listaStavki.add(new StavkaTermina(null, 1, "napomena 1", c1));       
//        listaStavki.add(new StavkaTermina(null, 2, "napomena 2", c1));
//        listaStavki.add(new StavkaTermina(null, 3, "napomena 3", c1));
//        listaStavki.add(new StavkaTermina(null, 4, "napomena 4", c1));
//        
//        t1.setStavkeTermina(listaStavki);
//        
//        PreparedStatement ps = DBBroker.getInstance().insert(t1);
//        ResultSet tableKeys = ps.getGeneratedKeys();
//        tableKeys.next();
//        Long terminID = tableKeys.getLong(1);
//        t1.setTerminID(terminID);
//        
//        for (StavkaTermina st : listaStavki) {
//            st.setTermin(t1);
//        }
//        
//        ubaciStavke(listaStavki);
//        
//        SOGetAllStavkaTermina so = new SOGetAllStavkaTermina();
//        
//        StavkaTermina st = new StavkaTermina();
//        st.setTermin(t1);
//        
//        so.templateExecute(st);
//        ArrayList<StavkaTermina> stavke = so.getLista();
////        isprazniTabelu();
//        
//        assertEquals(4, stavke.size());
    }

    private void isprazniTabelu() {
        try {
            String upit = "DELETE FROM stavkatermina";
            Statement s = DBBroker.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ubaciStavke(ArrayList<StavkaTermina> listaStavki) {
        try {
            String upit = "insert into stavkatermina values(?,?,?,?)";
            System.out.println(upit);
            PreparedStatement ps = DBBroker.getInstance().getConnection().prepareStatement(upit);
            for (StavkaTermina st : listaStavki) {
                ps.setLong(1, st.getTermin().getTerminID());
                ps.setInt(2, st.getRbStavke());
                ps.setString(3, st.getNapomena());
                ps.setLong(4, st.getClan().getClanID());
                
                ps.addBatch();
               }
            
            ps.executeBatch();
            DBBroker.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
