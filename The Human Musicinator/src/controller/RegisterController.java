package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.User;

public class RegisterController {
    private GeneralController generalController = GeneralController.getInstance();
    @FXML
    private PasswordField passwordTextBox;
    @FXML
    private TextField userNameTextBox;
    @FXML
    /**
     * The function is responsible for the user registration
     */
    public void pressButtonSubmit(){
        Boolean flag = true;
        //check if the text boxes are empty
        if(this.userNameTextBox.getText() == null || this.userNameTextBox.getText().trim().isEmpty()){
            Alert emptyUserNameAlert = new Alert(Alert.AlertType.WARNING);
            emptyUserNameAlert.setTitle("Enter user name");
            emptyUserNameAlert.setContentText("Please enter user name");
            emptyUserNameAlert.showAndWait();
            flag = false;
        }

        if(this.passwordTextBox.getText() == null || this.passwordTextBox.getText().trim().isEmpty()){
            Alert emptyUserNameAlert = new Alert(Alert.AlertType.WARNING);
            emptyUserNameAlert.setTitle("Enter user password");
            emptyUserNameAlert.setContentText("Please enter your password");
            emptyUserNameAlert.showAndWait();
            flag = false;
        }

        String userName = this.userNameTextBox.getText();
        String userPassword = this.passwordTextBox.getText();
        if (flag){
            if(isValidUserDetails(userName, userPassword)) {
                User registerUser = new User();
                registerUser.username = userName;
                registerUser.password = userPassword;
                if (this.generalController.registerUser(registerUser)) {
                    changeScreen("../view/Login.fxml");
                } else {
                    Alert emptyUserNameAlert = new Alert(Alert.AlertType.WARNING);
                    emptyUserNameAlert.setTitle("The user name is already in use");
                    emptyUserNameAlert.setContentText("Please choose different user name");
                    emptyUserNameAlert.showAndWait();
                }
            }
        }
    }

    private void changeScreen(String path){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            fxmlLoader.setRoot(fxmlLoader.load());
            this.generalController.getStage().hide();
            this.generalController.getStage().setScene(new Scene(fxmlLoader.getRoot(), 900, 700));
            this.generalController.getStage().show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * The function returns true if the user details are valid or false otherwise
     */
    private boolean isValidUserDetails(String userName, String userPassword){
        if(!userName.matches("[a-zA-Z0-9]*")) {
            Alert wrongIdAlert = new Alert(Alert.AlertType.WARNING);
            wrongIdAlert.setTitle("Wrong user name");
            wrongIdAlert.setHeaderText("Your user name is incorrect");
            wrongIdAlert.setContentText("user name should made only of letters and numbers");
            wrongIdAlert.showAndWait();
            return false;
        }

        if(!userPassword.matches("[a-zA-Z0-9]*")) {
            Alert wrongIdAlert = new Alert(Alert.AlertType.WARNING);
            wrongIdAlert.setTitle("Wrong user password");
            wrongIdAlert.setHeaderText("Your password is incorrect");
            wrongIdAlert.setContentText("user password should made only of letters and numbers");
            wrongIdAlert.showAndWait();
            return false;
        }
        return true;
    }
}
