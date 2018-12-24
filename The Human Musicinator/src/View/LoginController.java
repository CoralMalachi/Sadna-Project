package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class LoginController {
    private boolean isValidIdNumber(String idNumber){
        return idNumber.matches("\\d{9}");
    }
    private boolean isUserExistInDB(String idNumber){
        //Todo : check if user exist in DB and return the answer
        return true;//I wrote this only in order to avoid compilation errors
    }
    @FXML
    private TextField idNumberTextBox;

    @FXML
    public void pressButtonLogin()
    {
        String idNumber = idNumberTextBox.getText();

        if(!isValidIdNumber(idNumber)){
            Alert wrongIdAlert = new Alert(AlertType.WARNING);
            wrongIdAlert.setTitle("Wrong ID number");
        /**/    wrongIdAlert.setHeaderText("Your Id number is not exist");
            wrongIdAlert.setContentText("Please register before trying to login or try again");
            wrongIdAlert.showAndWait();
        }

        if (isUserExistInDB(idNumber)) {
            //Todo: save the user in DB and restart game
            try {

                System.out.print("limor and coral");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChooseGameDifficulty.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                Main.stg.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }



}


