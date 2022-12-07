/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 * Klasa koja sluzi za slanje klijentskih zahteva od programa 'Klijent' ka programu 'Server'. 
 * Programi 'Klijent' i 'Server' komuniciraju preko socket-a.
 * 
 * @author Mihailo
 */
public class Request implements Serializable{
    
    /**broj koji oznacava sistemsku operacju koja treba da se izvrsi*/
    private int operation;
    /**prosledjeni objekat u okviru klijentskog zahteva*/
    private Object data;

    public Request() {
    }
    
    public Request(int operation, Object data) {
        setOperation(operation);
        setData(data);
    }
    
    /**
     * @return vraca Objekat koji je deo klijentskog zahteva
     */
    public Object getData() {
        return data;
    }
    /**
     * @return vraca int koji oznacava sistemsku operaciju 
     */
    public int getOperation() {
        return operation;
    }
    
    /**
     * Postavlja vrednost atribura Data
     * @param data 
     */
    public void setData(Object data) {
        this.data = data;
    }
    /**
     * Postavlja vrednost atribura operation
     * @param operation 
     * @throws java.lang.RuntimeException ako je operation manji od 1
     */
    public void setOperation(int operation) {
        if(operation < 1){
            throw new RuntimeException("operation ne sme biti manji od 1");
        }
        this.operation = operation;
    }
}
