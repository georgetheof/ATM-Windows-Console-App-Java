/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainapp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import static mainapp.Login.user;
import static mainapp.MainApp.clearConsole;

/**
 *
 * @author georg
 */
public class Menu extends BankAcc{
    static String datetmp = new SimpleDateFormat("ddMMyyyy").format(new Date());
    final StringBuilder date = new StringBuilder(datetmp).insert(2, "_").insert(5, "_"); 
    
    public void Menu(int user, Files file) throws IOException, InterruptedException{
        int actionnum;
        if (user == 0){
            do{
                System.out.println("1. View Cooperative's (super admin) internal bank account.");
                System.out.println("2. View Members' internal bank accounts.");
                System.out.println("3. Deposit to Members' bank account.");
                System.out.println("4. Withdraw from Members' bank account.");
                System.out.println("5. Send to the statement_admin_" + date + ".txt file today’s transactions.");
                System.out.println("0. Exit the application.");
                Scanner scan = new Scanner(System.in);
                System.out.println("Type a number between 0-5 and press ENTER.");
                actionnum = scan.nextInt();
                if ((actionnum < 0) || (5 < actionnum)){
                        System.out.println("Invalid number.");
                        Enter();
                    }
            }while ((actionnum < 0) || (5 < actionnum));
        }
        else{
            do{
                System.out.println("1. View user" + user + " bank account.");
                System.out.println("2. Deposit to Cooperative's internal bank account.");
                System.out.println("3. Deposit to Members' bank account.");
                System.out.println("4. Send to the statement_user_" + user + "_" + date + ".txt file today’s transactions.");
                System.out.println("0. Exit the application.");
                Scanner scan = new Scanner(System.in);  
                System.out.println("Type a number between 0-4 and press ENTER...");
                actionnum = scan.nextInt();
                if ((actionnum < 0) || (4 < actionnum)){
                        System.out.println("Invalid number.");
                        Enter();
                    }
            }while ((actionnum < 0) || (4 < actionnum));
        }
        Action(actionnum, file);
    }
 
    public void Action(int i, Files file) throws IOException, InterruptedException{
        if (user == 0){
            switch(i){
                case(1):
                    super.displayAcc(0);
                    Enter();
                    file.writeViewAcc(user, i);
                    Menu(user, file);
                    break;
                case(2):
                    super.displayAll();
                    Enter();
                    file.writeViewAcc(user, i);
                    Menu(user, file);
                    break;
                case(3):
                    super.depositAcc(user);
                    Enter();
                    for (int k = 0; k < super.depositTOusers.length; k++){
                        if(user != k)
                            file.writeDepAcc(user, i, super.GetDepositTOusers(k), super.GetDepositAMOUNTusers(k));
                    }
                    Menu(user, file);
                    break;
                case(4):
                    super.withdrawAcc();
                    Enter();
                    file.writeWithdraw(user, super.GetWithdrawFROM(), super.GetWithdrawAMOUNT());
                    Menu(user, file);
                    break;
                case(5):
                    System.out.println("Send file to the statement_admin_" + date + ".txt file today’s transactions.");
                    System.out.println("The file is located at: " + file.getFilePATH());
                    Enter();
                    file.writeFileOrExit(user, i);                    
                    Menu(user, file);
                    break;
                case(0):
                    file.writeFileOrExit(user, i);
                    clearConsole();
                    break;
            }
        }
        else{
            switch(i){
                case(1):
                    super.displayAcc(user);
                    Enter();
                    file.writeViewAcc(user, i);
                    Menu(user, file);
                    break;
                case(2):
                    super.depositToCoop(user);
                    Enter();
                    file.writeDepAcc(user, i, 0, super.GetDepositAMOUNT());
                    Menu(user, file);
                    break;
                case(3):
                    super.depositAcc(user);
                    Enter();
                    for (int k = 1; k < super.depositTOusers.length; k++){
                        if(user != k)
                            file.writeDepAcc(user, i, super.depositTOusers[k], super.depositAMOUNTusers[k]);
                    }
                    Menu(user, file);
                    break;
                case(4):
                    System.out.println("Send to the statement_user_" + user + "_" + date + ".txt file today’s transactions.");
                    System.out.println("The file is located at: " + file.getFilePATH());
                    Enter();
                    file.writeFileOrExit(user, i);
                    Menu(user, file);
                    break;
                case(0):
                    file.writeFileOrExit(user, i);
                    clearConsole();
                    break;
            }
        }
    }
    private void Enter() throws IOException, InterruptedException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Press ENTER to CONTINUE/RETURN..");
        scan.nextLine();
        clearConsole();
    }
}