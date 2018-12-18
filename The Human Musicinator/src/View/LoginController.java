package View;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * Created by limor on 18/12/2018.
 */

public class LoginController {
    @FXML
    private TextField idNumberTextBox;

    @FXML
    public void pressButtonLogin(){
        String idNumber = idNumberTextBox.getText();

        if(!isValidIdNumber(idNumber)){
            Alert wrongIdAlert = new Alert(AlertType.WARNING);
            wrongIdAlert.setTitle("Wrong ID number");
            wrongIdAlert.setHeaderText("Your Id number is not exist");
            wrongIdAlert.setContentText("Please register before trying to login or try again");
            wrongIdAlert.showAndWait();
        }

        if (isUserExistInDB(idNumber)) {
            //Todo: use the controller function that starts the game
        }
    }

    private boolean isValidIdNumber(String idNumber){
        return idNumber.matches("\\d{9}");
    }
    private boolean isUserExistInDB(String idNumber){
        //Todo : check if user exist in DB and return the answer
        return true;//I wrote this only in order to avoid compilation errors
    }

}


