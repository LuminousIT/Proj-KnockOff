///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.luminous.ctrl;
//
//import com.luminous.Start;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.util.Duration;
//
///**
// * FXML Controller class
// *
// * @author samth
// */
//public class Authentication implements Initializable {
//
//    private Start app;
//
//    @FXML
//    Label countdown;
//    
//    @FXML  
//    Button submitButton;
//    
//    @FXML 
//    TextField pwd;
//    
//    Timeline tl;
//    Integer startTime = 30;
//  
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//
//        tl = new Timeline();
//        tl.setCycleCount(Timeline.INDEFINITE);
//        if (tl != null) {
//            tl.stop();
//        }
//        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                startTime--;
//                countdown.setText(startTime.toString() + " secs");
//                if (startTime <= 0) {
//                    tl.stop();
//                    countdown.setText("Time Up! Shutting down System...");
//                }
//            }
//            })
//       );
//        
//        tl.playFromStart();
//        
//        
//    }    
//
//    public void construct(Start aThis) {
//        app = aThis;
//        
//        submitButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent ae) {
//                  if (app.db.userExists(pwd.getText())) {
//                      app.stage.setOnCloseRequest(e -> Platform.exit());
//                  }
//                
//              }
//        });
//    }
//
//    
//}
