/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package so.clan;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Clan;
import domen.Kategorija;
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
public class SODeleteClanTest {
    
    public SODeleteClanTest() {
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
     * Test of validate method, of class SOGetAllClan.
     */
    @Test
    public void testValidatePogresanObjekat() throws Exception {
        SOGetAllClan pom = new SOGetAllClan();
        Kategorija k = new Kategorija();
        Exception e = assertThrows(java.lang.Exception.class, () -> pom.validate(k));
        System.out.println(e.getMessage());
    }

    /**
     * Test of execute method, of class SODeleteClan.
     */
    @Test
    public void testExecute() throws Exception {
        isprazniTabelu();
        isprazniKategorije();
        Kategorija k = new Kategorija(Long.parseLong("1"), "neki naziv 1", "neki opis 1");
        Clan c1 = new Clan(null, "jana", "janica", "j@gm.cm",
                "4564456464645", k);
        
        dodajKategoriju(k);
        
        PreparedStatement ps = DBBroker.getInstance().insert(c1);
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long clanId = tableKeys.getLong(1);
        
        c1.setClanID(clanId);
        
        SODeleteClan pom = new SODeleteClan();
        pom.execute(c1);
        
        ArrayList<AbstractDomainObject> clanovi = DBBroker.getInstance().select(new Clan());
        ArrayList<Clan> listaClanova = (ArrayList<Clan>) (ArrayList<?>) clanovi;
        
        boolean b = true;
        for (Clan clan : listaClanova) {
            if(clan.getEmail().equals(c1.getEmail())){
                b = false;
            }
        }
        assertTrue(b);
    }

    private void isprazniTabelu() {
            isprazniKategorije();
        try {
            
            String upit = "DELETE FROM clan";
            Statement s = DBBroker.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(SODeleteClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void isprazniKategorije() {
        try {
            String upit = "DELETE FROM kategorija";
            Statement s = DBBroker.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(SODeleteClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void dodajKategoriju(Kategorija k) {
        try {
            String upit = "INSERT INTO kategorija values(?,?,?)";
            System.out.println(upit);
            PreparedStatement ps = DBBroker.getInstance().getConnection().prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, k.getKategorijaID());
            ps.setString(2, k.getNazivKategorije());
            ps.setString(3, k.getOpisKategorije());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SOUpdateClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
