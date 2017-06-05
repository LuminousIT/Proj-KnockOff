/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luminous.ctrl;

import com.luminous.Start;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author Luminous
 */
public class Base implements Initializable {

    private Start app;
    
    @FXML
    FlowPane content;
    
    @FXML
    AnchorPane contentPane;

    @FXML
    TextField pass;
    
    @FXML
    TextField pass2;
    
    @FXML
    public Label errorMsg;
    
    @FXML
    private Button submitButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    public void construct(Start aThis) {
        app = aThis;
        
    }
    
    @FXML
    void goSubmit(ActionEvent event) {
        
//        String a = pass.getText().trim();
//         errorMsg.setText("Successful Login");
        if (!pass.getText().trim().isEmpty() && !pass.getText().trim().isEmpty()) {
            if(pass.getText().trim().equals(pass2.getText().trim())) {
                boolean a = app.db.signUpMethod(pass.getText(), pass2.getText());
                if (a){app.showAuthenticationPage();}
                if (app.db.userExists(pass.getText())) {
                    errorMsg.setText("Successful Login");
                    app.showAuthenticationPage();
                }
                errorMsg.setText("try again");
                
                //content.getChildren().setAll(app.showAuthenticationPage());
                //Platform.runLater(() -> content.getChildren().setAll(app.showAuthenticationPage()));
            }
            else {
                errorMsg.setText("Password MisMatch! Try Again");
                
            }
        } else {
        
         errorMsg.setText("Empty Field. Type Password");
        }
    } 
    
    

}
