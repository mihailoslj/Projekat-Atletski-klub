/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Administrator;
import java.util.ArrayList;
import so.AbstractSO;

/**
 * Klasa za login: proverava da li prosledjeni administrator postoji u bazi.
 * Nasledjuje kasu AbstractSO i implementira metode validate() i execute()
 * 
 * @author Mihailo
 */
public class SOLogin extends AbstractSO{

    Administrator ulogovani;
    
    /**
     * Provera validnosti parametra.
     * @param ado
     * @throws java.lang.Exception - ako prosledjeni objekat nije instacna klase Administrator
     */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
    }
    
    /**
     * Poziva se metoda select klase DBBroker i prima lista svih administratora iz baze.
     * Proverva se da li prosledjeni administrator odgovara kom iz baze(preme korisnickom imenu i lozinci)
     * i vraca taj administrator; u suprotnom baca se greska.
     * @param ado
     * @throws java.lang.Exception - ako dodje do greske prilikom selektovanja iz baze ili ako
     * kredencijali prosledjenog admina ne odgovaraju nijednom iz baze
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
                return;
            }
        }
        throw new Exception("Sistem ne može da pronađe administratora na osovu unetih podataka.");
    }
    
    /**
     * @return objekat administrator
     */
    public Administrator getUlogovani() {
        return ulogovani;
    }
}
