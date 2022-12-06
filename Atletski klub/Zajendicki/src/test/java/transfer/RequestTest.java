/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package transfer;

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
public class RequestTest {
    
    Request r;
    
    public RequestTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        r = new Request();
    }
    
    @AfterEach
    public void tearDown() {
        r = null;
    }

    /**
     * Test of setData method, of class Request.
     */
    @Test
    public void testSetData() {
        Object o1 = new Object();
        r.setData(o1);
        
        assertEquals(o1, r.getData());
    }

    /**
     * Test of setOperation method, of class Request.
     */
    @Test
    public void testSetOperationManjeOdJedan() {
        assertThrows(java.lang.RuntimeException.class, ()-> r.setOperation(-1));
    }
    /**
     * Test of setOperation method, of class Request.
     */
    @Test
    public void testSetOperation() {
        r.setOperation(1);
        assertEquals(1, r.getOperation());
    }
    
}
