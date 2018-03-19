/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author georg
 */
public class Database {
    private static final String JDBC_DRIVER = "com.mysql.jdb.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/catalog?autoReconnect=true&useSSL=false";    
    private static final String USER = "root";
    private static final String PASS = "root";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static Connection connUP = null;
    private static Statement stmtUP = null;
    private static MessageDigest md;
    
    protected void DatabaseCONNECT() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            
            String sqlACCOUNTS;
            sqlACCOUNTS = "SELECT * FROM afdemp_java_1.users;";
            ResultSet rs = stmt.executeQuery(sqlACCOUNTS);
            int i = 0;
            while(rs.next()){
                SetName(rs.getString("username"),i);
                SetPass(rs.getString("password"),i);
                i++;
            }
            
            String sqlBALANCE;
            sqlBALANCE = "SELECT * FROM afdemp_java_1.accounts;";
            ResultSet rs2 = stmt.executeQuery(sqlBALANCE);
            int k = 0;
            while(rs2.next()){
                databal[k] = rs2.getDouble("amount");
                k++;
            }
            
            rs.close();
            rs2.close();
            stmt.close();
            conn.close();
        }catch(SQLException | ClassNotFoundException se){
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn != null)
                conn.close();
            }catch(SQLException se){
            }
        }
    }
    
    static String dataname[] = new String[3];
    static String datapass[] = new String[3];
    static double databal[] = new double[3];
    
    public void SetName(String name, int i){
        this.dataname[i] = name;
    }
    public void SetPass(String pass, int i){
        this.datapass[i] = EncryptPASS(pass);
    }
    public void SetBal(double bal, int i){
        this.databal[i] = bal;
        String update;
        int id = i + 1;
        update = "UPDATE afdemp_java_1.accounts SET amount = '" + bal + "' "
            + "WHERE id = '" + id + "';";
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            connUP = DriverManager.getConnection(DB_URL,USER,PASS);
            stmtUP = connUP.createStatement();
            
            int tmp = stmtUP.executeUpdate(update);
            
            connUP.close();
            stmtUP.close();
        } catch (SQLException ex) {
            //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String GetName(int x){
        return this.dataname[x];
    }
    public String GetPass(int x){
        return this.datapass[x];
    }
    public double GetBal(int x){
        return this.databal[x];
    }
    
    public String EncryptPASS(String pass){
        try {
        md = MessageDigest.getInstance("MD5");
        byte[] passBytes = pass.getBytes();
        md.reset();
        byte[] digested = md.digest(passBytes);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<digested.length;i++){
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;
    }
}