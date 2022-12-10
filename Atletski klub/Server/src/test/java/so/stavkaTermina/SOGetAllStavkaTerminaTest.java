/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package so.stavkaTermina;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Clan;
import domen.Kategorija;
import domen.Sala;
import domen.StavkaTermina;
import domen.Termin;
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
        Long id = Long.parseLong("1");
        Kategorija kategorija = new Kategorija(id, "neki naziv 1","neki opis 1");
        Clan c1 = new Clan(id, "marko", "markovic",
                "mare123@gmai.com", "2222332552533", kategorija);
        Termin t1 = new Termin();
        t1.setTerminID(id);
        
        isprazniTabelu();
        ArrayList<StavkaTermina> listaStavki = new ArrayList<>();
        listaStavki.add(new StavkaTermina(t1, 1, "napomena 1", c1));       
        listaStavki.add(new StavkaTermina(t1, 2,"napomena 2", c1));
        listaStavki.add(new StavkaTermina(t1, 3, "napomena 3", c1));
        listaStavki.add(new StavkaTermina(t1, 4, "napomena 4", c1));

        ubaciStavke(listaStavki);
        
        ArrayList<AbstractDomainObject> stavke = DBBroker.getInstance().select(new Kategorija());
        ArrayList<StavkaTermina> listaPom = (ArrayList<StavkaTermina>) (ArrayList<?>) stavke;
        isprazniTabelu();
        
        assertEquals(4, listaPom.size());
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
