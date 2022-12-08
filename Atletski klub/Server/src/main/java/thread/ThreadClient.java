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
 *
 * @author Mihailo
 */
public class ThreadClient extends Thread{
    
    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

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
