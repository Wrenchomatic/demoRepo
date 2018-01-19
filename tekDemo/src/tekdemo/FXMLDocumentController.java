/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tekdemo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Antti
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private TextField textfield = new TextField();
    @FXML private Button button = new Button();
    @FXML private Label label = new Label();
   
    @FXML
    private void onEnter(ActionEvent ae){
     showTextWindow(); ////generates a window with the text written in the textfield
    }    
    @FXML
    protected void escButtonAction(ActionEvent event) {
        System.exit(0); // exits the program on press of button
    }
    
    @FXML
    protected void textButtonAction(ActionEvent event) {
        showTextWindow(); //generates a window with the text written in the textfield
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void showTextWindow() { //function that takes the written string from the textfield and generates a window for it
    
        String text = textfield.getText();
        System.out.println("Enter pressed") ;
        
        //final Stage primaryStage; //unecessary
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
     // dialog.initOwner(primaryStage); //unecessary
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(text));
        Scene dialogScene = new Scene(dialogVbox, 300, 100);
        dialog.setScene(dialogScene);
        dialog.show();
        System.out.println("text is : " + text);
    }
}
