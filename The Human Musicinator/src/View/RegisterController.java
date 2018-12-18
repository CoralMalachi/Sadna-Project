package View;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Created by limor on 18/12/2018.
 */

public class RegisterController{
    @FXML
    private TextField firstNameTextBox;
    @FXML
    private TextField lastNameTextBox;
    @FXML
    private TextField idNumberTextBox;
    @FXML
    private Button submitButton;

    @FXML
    public void pressButtonSubmit(){
        String userFirstName = firstNameTextBox.getText();
        String userLastName = lastNameTextBox.getText();
        String idNumber = idNumberTextBox.getText();

        if(isValidFirstName(userFirstName) &&
           isValidLastName(userLastName) && isValidIdNumber(idNumber)){
            //todo lIMOR: add user to DB
            //go to login page
            try{
                Stage stage = (Stage) submitButton.getScene().getWindow();
                BorderPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(root);
                //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private boolean isValidFirstName(String firstName) {
        return firstName.matches( "[A-Z][a-zA-Z]*" );
    }

    private boolean isValidLastName(String lastName) {
        return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
    }

    private boolean isValidIdNumber(String idNumber){
        return idNumber.matches("\\d{9}");
    }
}


