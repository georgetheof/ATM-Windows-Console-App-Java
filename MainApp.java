/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainapp;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author georg
 */
public class MainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  //throws IOException, InterruptedException {
        final Login login = new Login();
        final Menu menu = new Menu();
        final Database database = new Database();
        final Files files = new Files();
        final BankAcc bankacc = new BankAcc();
        Locale.setDefault(new Locale("el-GR"));
        
        database.DatabaseCONNECT();
        clearConsole();
        login.Login();
        if (login.GetLoginBool())
            files.userLogged(login.GetUser());
            clearConsole();
        try {
            menu.Menu(login.GetUser(), files);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final static void clearConsole(){
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
}