/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Mihailo
 */
public class Request implements Serializable{
    
    private int operation;
    private Object data;

    public Request() {
    }
    
    public Request(int operation, Object data) {
        setOperation(operation);
        setData(data);
    }

    public Object getData() {
        return data;
    }

    public int getOperation() {
        return operation;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setOperation(int operation) {
        if(operation < 1){
            throw new RuntimeException("operation ne sme biti manji od 1");
        }
        this.operation = operation;
    }
}
