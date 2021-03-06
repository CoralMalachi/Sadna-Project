package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.User;

public class LoginController {
    private GeneralController generalController = GeneralController.getInstance();
    @FXML
    private PasswordField passwordTextBox;
    @FXML
    private TextField userNameTextBox;

    @FXML
    /**
     * The function is responsible for the user login
     */
    public void pressButtonLogin() {
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

        if(!userName.matches("[a-zA-Z0-9]*"))
        {
            Alert wrongIdAlert = new Alert(Alert.AlertType.WARNING);
            wrongIdAlert.setTitle("Wrong user name");
            wrongIdAlert.setHeaderText("Your user name is incorrect");
            wrongIdAlert.setContentText("user name should made only of letters and numbers");
            wrongIdAlert.showAndWait();
        }

        if(!userPassword.matches("[a-zA-Z0-9]*")) {
            Alert wrongIdAlert = new Alert(Alert.AlertType.WARNING);
            wrongIdAlert.setTitle("Wrong user password");
            wrongIdAlert.setHeaderText("Your password is incorrect");
            wrongIdAlert.setContentText("user password should made only of letters and numbers");
            wrongIdAlert.showAndWait();
        }
        if(flag){
            if (isUserExistInDB(userName, userPassword)) {
                User newUser = new User();
                newUser.username = userName;
                newUser.password = userPassword;
                generalController.startGame(newUser);
                changeScreen("/resources/GamePage.fxml");
            } else {
                Alert userNotExistAlert = new Alert(Alert.AlertType.WARNING);
                userNotExistAlert.setTitle("Please register before trying to login");
                userNotExistAlert.setContentText("Username/Password not correct");
                userNotExistAlert.showAndWait();
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
     * The function returns true is the user exist in the DB or false otherwise
     */
    private boolean isUserExistInDB(String userName, String userPassword){
        User loginUser = new User();
        loginUser.username = userName;
        loginUser.password = userPassword;
        return generalController.loginUser(loginUser);
    }
}
