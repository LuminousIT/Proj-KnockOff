/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luminous;

import com.luminous.ctrl.Auth;
//import com.luminous.ctrl.Authentication;
import com.luminous.ctrl.Base;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author Luminous
 */
public class Start extends Application {

    private AnchorPane baseView;
    public Base baseCtrl;
    public DBHandler db;
    public Stage signupStage, stage;
      
    @FXML
    private AnchorPane contentPane;
    private FlowPane auth;
    private Auth authCtrl;
    public File systemDirectory;
    
    @Override
    public void init() {
        systemDirectory = new File(System.getenv("LOCALAPPDATA"), "fold");
        
        if (!systemDirectory.exists()){
            new File(systemDirectory, "innerfold").mkdirs();
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        db = new DBHandler();
        signupStage = new Stage();
        if (!db.dbExist()) {showSignUpPage();}
        else {showAuthenticationPage();}
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void showSignUpPage() {
        FXMLLoader loader = new FXMLLoader(Start.class.getResource("view/Base.fxml"), null, new JavaFXBuilderFactory());
        InputStream in = Start.class.getResourceAsStream("view/Base.fxml");
        try {
            baseView = (AnchorPane)loader.load(in);
            baseCtrl = (Base) loader.getController();
            baseCtrl.construct(this);
             baseView.setStyle("-fx-background-color: silver");
            Scene scene = new Scene(baseView);
            signupStage.setScene(scene);
            signupStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        //scene.setFill(Color.SILVER);    
    }
    
    public void showAuthenticationPage() {
        
        signupStage.close();
        
        FXMLLoader loader = new FXMLLoader(Start.class.getResource("view/Auth.fxml"), null, new JavaFXBuilderFactory());
        InputStream in = Start.class.getResourceAsStream("view/Auth.fxml");
        
        try {
            
            auth = (FlowPane)loader.load(in);
            authCtrl = (Auth) loader.getController();
            System.out.println("i reach here");
            authCtrl.construct(this);
            System.out.println("i reach here too");
             auth.setStyle("-fx-background-color: silver");
            Scene sce = new Scene(auth);
            stage.setMaximized(true);
            stage.initStyle(StageStyle.UNDECORATED);
            //stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(sce);
            stage.setAlwaysOnTop(true);
            stage.setOpacity(.8D);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    event.consume();
                }
            });
//            stage.initOwner(stage);
//            stage.initModality(Modality.APPLICATION_MODAL);
            
            //stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            System.out.println("here " + ex);
            
        }
       
        
    }
}
