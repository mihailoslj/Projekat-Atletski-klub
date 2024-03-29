/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Administrator;
import domen.Clan;
import domen.Kategorija;
import domen.Sala;
import domen.StavkaTermina;
import domen.Termin;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import konstante.Operation;
import konstante.ResponseStatus;
import session.Session;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author Mihailo
 */
public class ClientController {
     private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public ArrayList<StavkaTermina> getAllStavkaTermina(Termin t) throws Exception {
        return (ArrayList<StavkaTermina>) sendRequest(Operation.GET_ALL_STAVKA_TERMINA, t);
    }

    public ArrayList<Sala> getAllSala() throws Exception {
        return (ArrayList<Sala>) sendRequest(Operation.GET_ALL_SALA, null);
    }

    public ArrayList<Clan> getAllClan() throws Exception {
        return (ArrayList<Clan>) sendRequest(Operation.GET_ALL_CLAN, null);
    }

    public ArrayList<Kategorija> getAllKategorija() throws Exception {
        return (ArrayList<Kategorija>) sendRequest(Operation.GET_ALL_KATEGORIJA, null);
    }

    public void addClan(Clan clan) throws Exception {
       sendRequest(Operation.ADD_CLAN, clan);
    }

    public void deleteClan(Clan clan) throws Exception {
        sendRequest(Operation.DELETE_CLAN, clan);
    }

    public void updateClan(Clan clan) throws Exception {
        sendRequest(Operation.UPDATE_CLAN, clan);
    }

    public ArrayList<Termin> getAllTermin() throws Exception {
        return (ArrayList<Termin>) sendRequest(Operation.GET_ALL_TERMIN, null);
    }

    /**
     *
     * @param termin
     * @throws Exception
     */
    public void deleteTermin(Termin termin) throws Exception {
        sendRequest(Operation.DELETE_TERMIN, termin);
    }

    public void updateTermin(Termin termin) throws Exception {
        sendRequest(Operation.UPDATE_TERMIN, termin);
    }

    public void addTermin(Termin termin) throws Exception {
        sendRequest(Operation.ADD_TERMIN, termin);
    }
}
