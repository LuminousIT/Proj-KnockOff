/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luminous;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luminous
 */
public class DBHandler {

    private Connection con;
    private Start app;
    
     public DBHandler(Start appH) {
        app = appH;
     }
    public DBHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getConnection() {
        if (con != null) {
            return -1;
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/knockoff", "root", "");
            return -1;
        } catch (SQLException ex) {
            System.out.println(ex);
            int code = ex.getErrorCode();
            switch (code) {
                case 1049: return createDefaultDB();
            }
            return code;
        }
        
    }
    
    public int createDefaultDB() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            
            PreparedStatement prep = con.prepareStatement("create database Knockoff");
            prep.executeUpdate();
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Knockoff", "root", "");
            prep = con.prepareStatement("CREATE TABLE signup ( id INT(6) NOT NULL AUTO_INCREMENT PRIMARY KEY, password varchar(20), passConfirm varchar(20) )");
            prep.executeUpdate();
            
            return -1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
            return ex.getErrorCode();
        }
    }
    
    public boolean signUpMethod(String pass, String pass2) {
        if (con == null) {
            getConnection();
        }
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Knockoff", "root", "");
            
            PreparedStatement prep = con.prepareStatement("Insert into signup (password, passConfirm) values (?, ?) ");
            prep.setString(1, pass);
            prep.setString(2, pass2);
            prep.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            //extra code added to check prob with not running on other pc
            int code = ex.getErrorCode();
            switch (code) {
                case 1048: createDefaultDB(); return true;
            }
            return false;
        } catch(NullPointerException ex) {
            app.baseCtrl.errorMsg.setText("Network Problem, check Server");
            return false;
        }
        
    }
    
    public boolean userExists(String pass) {
        if (con == null) {
            getConnection();
        }
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Knockoff", "root", "");
            
            PreparedStatement prep = con.prepareStatement("select password from signup");
            ResultSet rst = prep.executeQuery();
            if (rst.next()) {
                if (pass.equals(rst.getString("password"))) {
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean dbExist() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Knockoff", "root", "");
            if (con == null) {
                return false;
            }
           PreparedStatement prep = con.prepareStatement("select * from signup");
           ResultSet rst = prep.executeQuery();
           if (rst.next()) {
               return true;
           } else {return false;}
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
