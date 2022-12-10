/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;
import domen.Administrator;
import java.io.IOException;
import java.net.Socket;
/**
 * Klasa koja sluzi za uspostavljanje koneckije sa programom 'Server' preko socket-a.
 * Nasledjuje apstraktnu klasu Thread, te je implementirana kao nit. Implementirana je kao singleton.
 *
 * @author Mihailo
 */
public class Session {
    private static Session instance;
    /** socket preko koga se uspostavlja veza sa Server */
    private Socket socket;
    /** admnistrator koji je trenutno ulogovan */
    private Administrator ulogovani;

    private Session() {
        try {
            socket = new Socket("localhost", 10000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }
    /**
     * @return vraca socket
     */
    public Socket getSocket() {
        return socket;
    }
    
    /**
     * Postavalja ulogovanog administratora
     * @param ulogovani 
     */
    public void setUlogovani(Administrator ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    /**
     * @return vraca ulogovanog administratora
     */
    public Administrator getUlogovani() {
        return ulogovani;
    }
}
