/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import konstante.ResponseStatus;

/**
 * Klasa koja sluzi za slanje serverskih odgovora od programa 'Server' ka programu 'Klijent'. 
 * Programi 'Klijent' i 'Server' komuniciraju preko socket-a.
 *
 * @author Mihailo
 */
public class Response {
   
    /** objekat koji sadrzi podatke koji su zatrazeni klijentskim zahtevom*/
    private Object data;
    /** exception koji se mozda desio tokom izvrsavanja sistemske operacije*/
    private Exception exc;
    /** oznava da li je sistemska operacija prosla bez greske. Vrednosti :['Success','Error']*/ 
    private ResponseStatus responseStatus;

    public Response() {
    }

    public Response(Object data, Exception exc, ResponseStatus responseStatus) {
        setData(data);
        setException(exc);
        setResponseStatus(responseStatus);
    }
    
    /**
     * @return vraca vrednost atributa data
     */
    public Object getData() {
        return data;
    }
    
    /**
     * Postavlja vrednost atributa data
     * @param data 
     */
    public void setData(Object data) {
        this.data = data;
    }
    /**
     * @return vraca vrednost atributa exc
     */
    public Exception getException() {
        return exc;
    }
    /**
     * Postavlja vrednost atribura exc
     * @param exc 
     */
    public void setException(Exception exc) {
        this.exc = exc;
    }
    /**
     * @return vraca vrednost atribura responseStatus
     */
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }
    /**
     * Postavlja vrednost atributa responseStatus
     * @param responseStatus 
     * @throws java.lang.RuntimeException ako responseStatus ima vrednosti izvan skupa: ['Succes',
     * 'Error']
     */
    public void setResponseStatus(ResponseStatus responseStatus) {
        if(!responseStatus.equals(ResponseStatus.Success) && !responseStatus.equals(ResponseStatus.Error)){
            throw new RuntimeException("Response status moze biti samo 'Success' ili 'Error'");
        }
        this.responseStatus = responseStatus;
    }
}
