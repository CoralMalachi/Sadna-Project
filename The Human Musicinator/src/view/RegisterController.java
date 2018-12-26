package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
           isValidLastName(userLastName) ){
            //todo :use the controller function to add the user to the DB
            //go to login page
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setFullScreen(true);
                stage.show();
                Main.stg.close();
            } catch(Exception e) {
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

}


