/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klasa koja sluzi za uspostavljanje koneckije sa programom 'Klijent' preko socket-a.
 * Nasledjuje apstraktnu klasu Thread, te je implementirana kao nit.
 *
 * @author Mihailo
 */
public class ThreadServer extends Thread {
    
    /** promenjiva preko koje se uspostavlja konekcija */
    private ServerSocket serverSocket;

    public ThreadServer() {
        try {
            serverSocket = new ServerSocket(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Override metode run() u kojoj cekamo da se klijent poveze.
     * @throws java.lang.Exception.class - ako dodje do greske prilikom povezivanja
     */
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao!");
                ThreadClient th = new ThreadClient(socket);
                th.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}