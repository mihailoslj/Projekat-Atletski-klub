/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package konstante;

/**
 * Interfejs koji sluzi da se definisu konstante koje definisu sistemske operacije.
 * Koristi se programu 'Server' da bi se prepoznala sistemska operacija i pozvala odgovarajuca metoda.
 * @author Mihailo
 */
public interface Operation {
    
    public static final int LOGIN = 0;

    public static final int GET_ALL_ADMINISTRATOR = 1;

    public static final int ADD_CLAN = 2;
    public static final int DELETE_CLAN = 3;
    public static final int UPDATE_CLAN = 4;
    public static final int GET_ALL_CLAN = 5;

    public static final int ADD_TERMIN = 6;
    public static final int DELETE_TERMIN = 7;
    public static final int UPDATE_TERMIN = 8;
    public static final int GET_ALL_TERMIN = 9;

    public static final int GET_ALL_STAVKA_TERMINA = 10;

    public static final int GET_ALL_KATEGORIJA = 11;

    public static final int GET_ALL_SALA = 12;
}
