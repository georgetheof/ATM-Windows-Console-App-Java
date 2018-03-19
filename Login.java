/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainapp;

import java.io.Console;
import java.util.Scanner;

/**
 *
 * @author georg
 */
public class Login extends Database{
    private String name;
    private String pass;
    static int user;
    static boolean loginbool = false;

    public void Login(){
        Console console = System.console();
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 3; i++){
        System.out.println("Enter your username:");
        this.name = scan.next();
        System.out.println("Enter your password:");
        String passtmp;
        char[] passwordChars = console.readPassword();
        passtmp = new String(passwordChars);
        this.pass = super.EncryptPASS(passtmp);
        
//      this.pass = scan.next();
        for (int j = 0; j < 3; j++){
                if ((this.name.equals(super.GetName(j))) && (this.pass.equals(super.GetPass(j)))){
                    this.user = j;
                    System.out.println("You have logged in as " + super.GetName(j) + ".");
                    this.loginbool = true;
                    break;
                }
            }
            if (this.loginbool)
                break;
            else {
                if (i < 2)
                    System.out.println("Invalid username/password. You have " + (2-i) +" tries left...");
                else
                    System.out.println("You have failed to log in.");
            }
        }
    }
    public int GetUser(){
        return this.user;
    }
    public boolean GetLoginBool(){
        return this.loginbool;
    }
}