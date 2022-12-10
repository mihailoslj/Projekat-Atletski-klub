/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package so.clan;

import controller.ServerController;
import db.DBBroker;
import domen.AbstractDomainObject;
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

/**
 *
 * @author Mihailo
 */
public class SOAddClanTest {
    
    Kategorija k;
    Clan c;
    SOAddClan ado;
    
    
    public SOAddClanTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        Long id = Long.parseLong("1");
        k = new Kategorija(id, "naziv kat 1", "opis kat 1");
        c = new Clan(null, "ana", "anic", "ana@gmail.com", "06011112222", k);
        ado = new SOAddClan();
    }
    
    @AfterEach
    public void tearDown() {
        c = null;
        k = null;
        ado = null;
    }

    /**
     * Test of validate method, of class SOAddClan.
     */
    @Test
    public void testValidatePogresanObjekat() throws Exception {
        assertThrows(java.lang.Exception.class, () -> ado.validate(k));
    }
    
    /**
     * Test of validate method, of class SOAddClan.
     * Rucno ubacena jedna Kategorija u bazu sa kategorijaID
     */
    @Test
    public void testValidateEmail() throws Exception {
        
        SOAddClan sac = new SOAddClan();
        
        Long id = Long.parseLong("1");
        Kategorija k = new Kategorija(id, "naziv kat 1", "opis kat 1");
        Clan c1 = new Clan(null, "marko", "markovic", "ana@gmail.com", "060600606060", k);
        
        SOAddClan soadd = new SOAddClan();
        soadd.execute(c1);
        
        Clan c2 = new Clan(null, "asd","sad","dsa@adsdsd","1122020200",k);
        c2.setEmail("ana@gmail.com");
        
        Exception e = assertThrows(java.lang.Exception.class, () -> sac.validate(c2));
        isprazniTabelu();
        System.out.println(e.getMessage());
        
    }
    
    /**
     * Test of validate method, of class SOAddClan.
     * Rucno ubacena jedna Kategorija u bazu sa kategorijaID
     */
    @Test
    public void testValidateTelefon() throws Exception {
        isprazniTabelu();
        isprazniKategorije();
        SOAddClan sac = new SOAddClan();
        
        Long id = Long.parseLong("1");
        Kategorija kategorija = new Kategorija(id, "neki naziv 1","neki opis 1");
        Clan c1 = new Clan(null, "marko", "markovic",
                "mare@gmai.com", "1112222233333", kategorija);
        
        dodajKategoriju(kategorija);
        
        sac.templateExecute(c1);
        
        Clan c2 = new Clan(null, "dsasa","asdasd","sad@","31132131232134", kategorija);
        c2.setKategorija(kategorija);
        c2.setTelefon("1112222233333");
        
        Exception e = assertThrows(java.lang.Exception.class, () -> sac.validate(c2));
        isprazniTabelu();
        System.out.println(e.getMessage());
    }

    /**
     * Test of execute method, of class SOAddClan.
     */
    @Test
    public void testExecute() throws Exception {
        isprazniTabelu();
        isprazniKategorije();
        Long id = Long.parseLong("1");
        Kategorija kategorija = new Kategorija(id, "neki naziv 1","neki opis 1");
        Clan c1 = new Clan(null, "marko", "markovic",
                "mare123@gmai.com", "2222332552533", kategorija);
        
        dodajKategoriju(kategorija);
        
        SOAddClan pom = new SOAddClan();
        pom.execute(c1);
        DBBroker.getInstance().getConnection().commit();
        
        ArrayList<AbstractDomainObject> clanovi = DBBroker.getInstance().select(c1);
        ArrayList<Clan> lista = (ArrayList<Clan>) (ArrayList<?>) clanovi;
        
        Clan c2 = new Clan();
        for (Clan c : lista) {
            if(c.getClanID().equals(c1)){
                c2 = c;
            }
        }
        isprazniTabelu();
        assertEquals(c2, c1);
        
    }

    private void isprazniTabelu() {
        isprazniKategorije();
        try {
            String upit = "DELETE FROM clan";
            Statement s = DBBroker.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void isprazniKategorije() {
        try {
            String upit = "DELETE FROM kategorija";
            Statement s = DBBroker.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
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
