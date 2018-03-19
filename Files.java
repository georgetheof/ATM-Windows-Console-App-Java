/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static mainapp.Login.user;

/**
 *
 * @author georg
 */
public class Files extends Database {
    private static String writetmp;
    private static String filename;
    public static String filePATH;

    public String getFilePATH() {
        return filePATH;
    }

    public void setFilePATH(String filePATH) {
        Files.filePATH = filePATH;
    }
    
    public void userLogged(int user){
        writetmp = super.GetName(user) + " logged in @" +GetTime();
        writeLine(writetmp);
    }
    
    public void writeViewAcc(int user, int i){

        switch(i){
            case(1):
                writetmp = super.GetName(user) + " viewed his/her internal bank account. @" + GetTime();
                writeLine(writetmp);
                break;
            case(2):
                if (user == 0){
                    writetmp = super.GetName(user) + " viewed Members' internal bank accounts. @" + GetTime();
                    writeLine(writetmp);
                }
            break;
        }
    }
    
    public void writeDepAcc(int user, int i, int depositTO, double depositAMOUNT){
        switch(i){
            case(2):
                if (i != 0){
                    writetmp = super.GetName(user) + " deposited " + depositAMOUNT + "€ to Cooperative's internal bank account. @" + GetTime();
                    writeLine(writetmp);
                }
                break;
            case(3):
                writetmp = super.GetName(user) + " deposited " + depositAMOUNT + "€ to " + super.GetName(depositTO) + ". @" + GetTime();
                writeLine(writetmp);
                break;
        }
    }
    
    public void writeWithdraw(int user, int withdrawFROM, double withdrawAMOUNT){
        writetmp = super.GetName(user) + " withdrew " + withdrawAMOUNT + "€ from " + super.GetName(withdrawFROM) + ". @" + GetTime();
        writeLine(writetmp);
    }
    
    public void writeFileOrExit(int user, int i){
        String datetmp2 = new SimpleDateFormat("ddMMyyyy").format(new Date());
        final StringBuilder date2 = new StringBuilder(datetmp2).insert(2, "_").insert(5, "_");
        
        switch(i){
            case(4):
                if (user != 0){
                    writetmp = super.GetName(user) + " send to the statement_user_" + user + "_" + date2 + ".txt file today’s transactions. @" + GetTime();
                    writeLine(writetmp);
                }
                break;
            case(5):
                if (user == 0){
                    writetmp = super.GetName(user) + " send to the statement_admin_" + date2 + ".txt file today’s transactions. @" + GetTime();
                    writeLine(writetmp);
                }
                break;
            case(0):
                writetmp = super.GetName(user) + " exited the application @" + GetTime();
                writeLine(writetmp);
                break;
        }
    }
    
    private StringBuilder GetTime(){
        String time = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(Calendar.getInstance().getTime());
        final StringBuilder timeAction = new StringBuilder(time).insert(4, "-").insert(7, "-").insert(13, ":").insert(16, ":").insert(19, ".");
        return timeAction;
    }
    
    private void writeLine(String line){
        String datetmpFILE = new SimpleDateFormat("ddMMyyyy").format(new Date());
        final StringBuilder dateFILE = new StringBuilder(datetmpFILE).insert(2, "_").insert(5, "_");
        
        BufferedWriter writer = null;
        try {
            //create a temporary file
            if (user == 0){
                filename = ("statement_" + super.GetName(0) + "_" + dateFILE + ".txt");
            }
            else{
                filename = ("statement_user_" + user + "_" + dateFILE + ".txt");
            }
            
            File fileNAME = new File(filename);
            //This will output the full path where the file will be written to...
            //System.out.println(fileNAME.getCanonicalPath());
            setFilePATH(fileNAME.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(fileNAME,true));
            writer.append(line);
            writer.newLine();
        } catch (Exception e) {
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}