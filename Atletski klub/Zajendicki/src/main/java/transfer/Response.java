/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import konstante.ResponseStatus;

/**
 *
 * @author Mihailo
 */
public class Response {
   
    private Object data;
    private Exception exc;
    private ResponseStatus responseStatus;

    public Response() {
    }

    public Response(Object data, Exception exc, ResponseStatus responseStatus) {
        setData(data);
        setException(exc);
        setResponseStatus(responseStatus);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Exception getException() {
        return exc;
    }

    public void setException(Exception exc) {
        this.exc = exc;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        if(!responseStatus.equals(ResponseStatus.Success) && !responseStatus.equals(ResponseStatus.Error)){
            throw new RuntimeException("Response status moze biti samo 'Success' ili 'Error'");
        }
        this.responseStatus = responseStatus;
    }
}
