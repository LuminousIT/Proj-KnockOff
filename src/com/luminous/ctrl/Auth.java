/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luminous.ctrl;

import com.luminous.Start;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author luminous
 */
public class Auth implements Initializable {

    private Start app;

    @FXML
    Label countdown, errMsg;
    
    @FXML  
    Button submitButton;
    
    @FXML 
    TextField pwd;
    
    File file;
    
    Timeline tl;
    int attempts = 1;
    Integer startTime = 30;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        Stage stage = (Stage) app.stage.getScene().getWindow();
//        //stage.initStyle(StageStyle.TRANSPARENT);
        
        tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);
        if (tl != null) {
            tl.stop();
        }
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startTime--;
                countdown.setText(startTime.toString() + " secs");
                if (startTime <= 0) {
                    tl.stop();
                    countdown.setText("Time Up! Shutting down System...");
                    boolean success = storeAttempts(); //returns true only when attempts >= 4;
                 
                    //if attempts == 4; shutdown system. // You can decide to fry harddisk at this point too ;)
                    if (success) {
                        System.out.println("i go shutdown ooo");
//                        try {
//                            Runtime.getRuntime().exec("shutdown.exe -s -t 0");
//                        } catch (IOException ex) {
//                            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
//                         }
                    } else {
                           Stage stage = (Stage) app.stage.getScene().getWindow();
                           stage.close();
                    }
                }
            }
            })
       );
        
        tl.playFromStart();
        
    }    

    public void construct(Start aThis) {
        app = aThis;
        
        
    }
    
    @FXML
    void submitCon(ActionEvent ae) {
        if (app.db.userExists(pwd.getText())) {
            System.out.println(pwd.getText() + " Exists");
//            app.stage.setOnCloseRequest(e -> Platform.exit());
            Stage stage = (Stage) app.stage.getScene().getWindow();
            stage.close();
            System.out.println("hey");
        }
        else {
            errMsg.setText("Wrong Password. Try Again");
            System.out.println("Wrong Password. Try Again");
        }
        
    }    
    
    public boolean storeAttempts() {
        File demo = new File(app.systemDirectory,"test.txt"); //for some reason i dont get, if you declare this as global var, app wont build.
        try {
            
            if (demo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(demo));
                String a = br.readLine();
                System.out.println(a);
                int temp = Integer.parseInt(a);
                if (temp < 4) {
                    int b = ++temp;
                    System.out.println("value of temp++" + b);
                    PrintWriter pw2 = new PrintWriter(demo);
                    pw2.write(String.valueOf(b));
                    //pw2.write(temp);
                    pw2.close();
                    return false;
                    //shutdownOrCloseApp();
//                    if (temp >= 4) {
//                        shutdownOrCloseApp();
//                        boolean success = demo.delete();//You can spell doom to the PC at this point.
//                        if (!success) System.out.print("Deletion failed");
//                    }
                } else {
                    return true;
                }
                
            }
            else {
            PrintWriter pw = new PrintWriter(demo);
            pw.write("1");
            pw.close();
            return false;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public void shutdownOrCloseApp() {
        //closeApp
//        Stage stage = (Stage) app.stage.getScene().getWindow();
//        stage.close();
        
        //shuutdownSys
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process proc = runtime.exec("shutdown -s -t 0");
//            System.exit(0);
//        } catch (IOException ex) {
//            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

}
