/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import controller.ServerController;
import domen.Administrator;
import domen.Clan;
import domen.Termin;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import konstante.ResponseStatus;
import konstante.Operation;

/**
 * Klasa zaduzena za prijem klijentskih zahteva i slanje serverskih odgovora (preko klasa Request i Response).
 * Implementirana je kao nit te nasledjuje apstraktnu klasu Thread.
 *
 * @author Mihailo
 */
public class ThreadClient extends Thread{
    
    /** socket preko koga se primaju/salju podaci*/
    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    /**
     * Overide metode run() klase Thread. Primaju se podaci preko ObjectInputSream-a i salju
     * odgovori preko ObjectOutputSream-a.
     */
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metoda za primanje zahteva/slanje odgovora. Zahtevi se citaju iz kase Request i razvrstavaju
     * prema prosledjenoj vrednosti konstranti iz interfejsa Operation. Odgovor se salje preko
     * objekta Response.
     * @param request
     * @return Response
     * @throws java.lang.Exception.class - ako dodje do greske prilikom primanja/slanja podataka.
     * Response status se postavalja na 'Error'
     */
    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.LOGIN:
                    Administrator administrator = (Administrator) request.getData();
                    Administrator ulogovani = ServerController.getInstance().login(administrator);
                    response.setData(ulogovani);
                    break;
                case Operation.GET_ALL_STAVKA_TERMINA:
                    response.setData(ServerController.getInstance().getAllStavkaTermina((Termin) request.getData()));
                    break;
                case Operation.GET_ALL_SALA:
                    response.setData(ServerController.getInstance().getAllSala());
                    break;
                case Operation.GET_ALL_CLAN:
                    response.setData(ServerController.getInstance().getAllClan());
                    break;
                case Operation.GET_ALL_KATEGORIJA:
                    response.setData(ServerController.getInstance().getAllKategorija());
                    break;
                case Operation.ADD_CLAN:
                    ServerController.getInstance().addClan((Clan) request.getData());
                    break;
                case Operation.DELETE_CLAN:
                    ServerController.getInstance().deleteClan((Clan) request.getData());
                    break;
                case Operation.UPDATE_CLAN:
                    ServerController.getInstance().updateClan((Clan) request.getData());
                    break;
                case Operation.ADD_TERMIN:
                    ServerController.getInstance().addTermin((Termin) request.getData());
                    break;
                case Operation.GET_ALL_TERMIN:
                    response.setData(ServerController.getInstance().getAllTermin());
                    break;
                case Operation.DELETE_TERMIN:
                    ServerController.getInstance().deleteTermin((Termin) request.getData());
                    break;
                case Operation.UPDATE_TERMIN:
                    ServerController.getInstance().updateTermin((Termin) request.getData());
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(e);
        }
        return response;
    }
}
