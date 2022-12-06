/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package transfer;

import konstante.ResponseStatus;
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
public class ResponseTest {
    
    Response r;
    public ResponseTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        r = new Response();
    }
    
    @AfterEach
    public void tearDown() {
        r = null;
    }


    /**
     * Test of setData method, of class Response.
     */
    @Test
    public void testSetData() {
        r.setData(1);
        assertEquals(1, r.getData());
    }

    /**
     * Test of setException method, of class Response.
     */
    @Test
    public void testSetException() {
        Exception exc = new NullPointerException();
        r.setException(exc);
        assertSame(exc, r.getException());
    }
    /**
     * Test of setResponseStatus method, of class Response.
     */
    @Test
    public void testSetResponseStatus() {
       r.setResponseStatus(ResponseStatus.Success);
       assertSame(ResponseStatus.Success, r.getResponseStatus()); 
    }
    /**
     * Test of setResponseStatus method, of class Response.
     */
    @Test
    public void testSetResponseStatus2() {
       r.setResponseStatus(ResponseStatus.Error);
       assertSame(ResponseStatus.Error, r.getResponseStatus()); 
    }
    
    
}
