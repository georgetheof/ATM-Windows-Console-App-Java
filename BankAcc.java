/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainapp;

import java.util.Scanner;

/**
 *
 * @author georg
 */
public class BankAcc extends Database {
    static double depositAMOUNT; //from users to Coop
    static int withdrawFROM;
    static double withdrawAMOUNT;
    static int depositTOusers[] = new int[3];
    static double depositAMOUNTusers[] = new double[3];
    
    public void displayAcc(int i){
        System.out.println(super.GetName(i) + " account balance is: " + super.GetBal(i) + "€");
    }
    public void displayAll(){
        for (int i = 1; i < super.dataname.length; i++){
            System.out.println(super.dataname[i] + " account balance is: " + super.databal[i] + "€");
        }
    }
    
    public void depositToCoop(int user){
        System.out.println("Insert the amount of money you would like to deposit to " + super.GetName(0) + ":");
        Scanner scan = new Scanner(System.in);
        double depAMOUNTtmp;
        do{
            depAMOUNTtmp = scan.nextDouble();
            if (depAMOUNTtmp < 0)
                System.out.println("Invalid amount. Try again:");
            else if (((super.GetBal(user) - depAMOUNTtmp) < 0 ) || super.GetBal(user) < depAMOUNTtmp){
                System.out.println("Your account's total is less than the amount you entered. Try again:");
            }
        }while (((super.GetBal(user) - depAMOUNTtmp) < 0 ) || super.GetBal(user) < depAMOUNTtmp || depAMOUNTtmp < 0);
        super.SetBal((super.GetBal(0) + depAMOUNTtmp), 0);
        super.SetBal((super.GetBal(user) - depAMOUNTtmp), user);
        SetDepositAMOUNT(depAMOUNTtmp);
    }
    
    public void depositAcc(int user){
        double depAMOUNTtmp;
        for (int i = 1; i < super.dataname.length; i++){
            if (user != i){
                SetDepositTOusers(i);
                System.out.println("Insert the amount of money you would like to deposit to " + super.GetName(i));
                Scanner scan = new Scanner(System.in);
                do{
                    depAMOUNTtmp = scan.nextDouble();
                    if (depAMOUNTtmp < 0)
                        System.out.println("Invalid amount. Try again:");
                    else if (((super.GetBal(user) - depAMOUNTtmp) < 0) || super.GetBal(user) < depAMOUNTtmp){
                        System.out.println("Your account's total is less than the amount you entered. Try again:");
                    }
                }while (((super.GetBal(user) - depAMOUNTtmp) < 0) || super.GetBal(user) < depAMOUNTtmp || depAMOUNTtmp < 0);
            SetDepositAMOUNTusers(i, depAMOUNTtmp);
            super.SetBal((super.GetBal(i) + depAMOUNTtmp), i);
            super.SetBal((super.GetBal(user) - depAMOUNTtmp), user);
            }
        }
    }
      
    public void withdrawAcc(){
        System.out.println("Select a number (corresponding to the current user) to withdraw money from: ");
        int withFROMtmp;
        double withAMOUNTtmp;
        for (int i = 1; i < super.dataname.length; i++){
        System.out.println(i + ". " + super.GetName(1) + "(balance: " + super.GetBal(i) + "€)");
        }
        Scanner scan = new Scanner(System.in);
        do{
            withFROMtmp = scan.nextInt();
            if ((withFROMtmp < 1) || (super.dataname.length < withFROMtmp)){
                System.out.println("Invalid number. Try again:");
            }
        }while ((withFROMtmp < 1) || (super.dataname.length < withFROMtmp));
        System.out.println("Insert the amount of money you would like to withdraw from " + super.GetName(withFROMtmp) + ":");
        do{
            withAMOUNTtmp = scan.nextDouble();
            if (withAMOUNTtmp < 0)
                System.out.println("Invalid number. Try again:");
            else if (super.GetBal(withFROMtmp) < withAMOUNTtmp){
                System.out.println("The account does not have that much money. Try again:");
            }
        }while (super.GetBal(withFROMtmp) < withAMOUNTtmp || withAMOUNTtmp < 0);
        super.SetBal((super.GetBal(0) + withAMOUNTtmp), 0);
        super.SetBal((super.GetBal(withFROMtmp) - withAMOUNTtmp),withFROMtmp);
        SetWithdrawFROM(withFROMtmp);
        SetWithdrawAMOUNT(withAMOUNTtmp);
    }
    
    public void SetDepositAMOUNT(double i){
        this.depositAMOUNT = i;
    }
    public double GetDepositAMOUNT(){
        return this.depositAMOUNT;
    }
    
    public void SetWithdrawAMOUNT(double i){
        this.withdrawAMOUNT = i;
    }
    public double GetWithdrawAMOUNT(){
        return this.withdrawAMOUNT;
    }
    
    public void SetWithdrawFROM(int i){
        this.withdrawFROM = i;
    }
    public int GetWithdrawFROM(){
        return this.withdrawFROM;
    }
    
    public void SetDepositTOusers(int i){
        this.depositTOusers[i] = i;
    }
    public int GetDepositTOusers(int i){
        return this.depositTOusers[i];
    }
    
    public void SetDepositAMOUNTusers(int i, double amount){
        this.depositAMOUNTusers[i] = amount;
    }
    public double GetDepositAMOUNTusers(int i){
        return this.depositAMOUNTusers[i];
    }
}