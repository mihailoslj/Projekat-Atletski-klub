/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package so.clan;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Clan;
import domen.Kategorija;
import static java.lang.Thread.sleep;
import java.sql.Array;
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
public class SOGetAllClanTest {
    
    public SOGetAllClanTest() {
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
     * Test of execute method, of class SOGetAllClan.
     * tabela clanova je ispraznjena za ovaj test. U bazi je vec kategorija sa ovim vrednostima
     */
    @Test
    public void testExecute() throws Exception {
        isprazniTabelu();
        isprazniKategorije();
        Kategorija k = new Kategorija(Long.parseLong("1"), "neki naziv 1", "nekiOpis 1");
        
        dodajKategoriju(k);
        
        ArrayList<Clan> listaClanova = new ArrayList<>();
        listaClanova.add(new Clan(null, "imeime", "prezime", "sad@asd", "01243456789", k));       
        listaClanova.add(new Clan(null, "imeime", "prezime", "saad@asd", "01253456789", k));
        listaClanova.add(new Clan(null, "imeime", "prezime", "sabd@asd", "01234356789", k));
        listaClanova.add(new Clan(null, "imeime", "prezime", "sadq@asd", "01223456789", k));

        for (Clan clan : listaClanova) {
            (new SOAddClan()).execute(clan);
        }
        
        ArrayList<AbstractDomainObject> clanovi = DBBroker.getInstance().select(new Clan());
        ArrayList<Clan> listaPom = (ArrayList<Clan>) (ArrayList<?>) clanovi;
        
        boolean b = true;
        for(int i = 0; i < listaPom.size(); i++){
            if(!listaClanova.get(i).getEmail().equals(listaPom.get(i).getEmail())){
                b = false;
            }
        }
        isprazniTabelu();
        assertTrue(b);
    }


    private void isprazniTabelu() {
        isprazniKategorije();
        try {
            String upit = "DELETE FROM clan";
            System.out.println(upit);
            Statement s = DBBroker.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
        } catch (SQLException ex) {
            Logger.getLogger(SOGetAllClanTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void isprazniKategorije() {
        try {
            String upit = "DELETE FROM kategorija";
            System.out.println(upit);
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
