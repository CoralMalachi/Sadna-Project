package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller implements IMusicinatorController{

    @FXML
    private Stage stage = new Stage();
    @FXML
    private TextField answerTextBox;
    @FXML
    private Label hintLabel;
    @FXML
    private TextField passwordTextBox;
    @FXML
    private TextField userNameTextBox;

    public boolean checkPattern(String userGuess){
        return true;
    }

    public boolean maskEntity(){
        return true;
    }

//    public boolean checkRegistration(UserInfo userInfo){
//        return true;
//    }
//
//    public boolean checkLogin(UserInfo userInfo){
//        return true;
//    }
//
//    public boolean initGame(GameState gameState){
//        return true;
//    }

    public boolean resetGame(){
        return true;
    }

    public boolean hasGameEnded(){
        return true;
    }

    @FXML
    public void pressHardButton() throws IOException {
        //todo: update the controller (of the whole application) about the game difficulty
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/StartPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            this.stage.setScene(new Scene(root));
            this.stage.setFullScreen(true);
            this.stage.show();
            Main.stg.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pressMediumButton() throws IOException {
        //todo: update the controller (of the whole application) about the game difficulty
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/StartPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            this.stage.setScene(new Scene(root));
            this.stage.setFullScreen(true);
            this.stage.show();
            Main.stg.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pressEasyButton() throws IOException {
        //todo: update the controller (of the whole application) about the game difficulty
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/StartPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            this.stage.setScene(new Scene(root));
            this.stage.setFullScreen(true);
            this.stage.show();
            Main.stg.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void pressGetHintButton(){
        //todo: use the controller function to get hint from the db
        //decrease the score of the user
        String hint = "hint";//get the hint from controller
        this.hintLabel.setText(hint);
    }

    @FXML
    public void pressCheckPatternButton(){
        //todo: use the controller function to check the pattern
    }

    @FXML
    public void pressCheatButton(){
        //todo: use the controller function to get the name of the artist
        String artistName = "the answer";
        this.answerTextBox.setText(artistName);
    }

    @FXML
    public void pressGoToMenuButton(){
        //todo: use the controller function that reset the game
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MainMenu.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            this.stage.setScene(new Scene(root));
            this.stage.setFullScreen(true);
            this.stage.show();
            Main.stg.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pressExitButton(){
        System.exit(0);
    }

    private boolean isUserExistInDB(String userName, String userPassword){
        //Todo : use the controller function to check if the user exist in the DB
        return true;//I wrote this only in order to avoid compilation errors
    }

    @FXML
    public void pressButtonLogin() {
        //check if the text boxes are empty
        if(this.userNameTextBox.getText() == null || this.userNameTextBox.getText().trim().isEmpty()){
            Alert emptyUserNameAlert = new Alert(Alert.AlertType.WARNING);
            emptyUserNameAlert.setTitle("Enter user name");
            emptyUserNameAlert.setContentText("Please enter user name");
            emptyUserNameAlert.showAndWait();
        }

        if(this.passwordTextBox.getText() == null || this.passwordTextBox.getText().trim().isEmpty()){
            Alert emptyUserNameAlert = new Alert(Alert.AlertType.WARNING);
            emptyUserNameAlert.setTitle("Enter user password");
            emptyUserNameAlert.setContentText("Please enter your password");
            emptyUserNameAlert.showAndWait();
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

        if (isUserExistInDB(userName, userPassword)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ChooseGameDifficulty.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                this.stage.setScene(new Scene(root));
                this.stage.setFullScreen(true);
                this.stage.show();
                Main.stg.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert userNotExistAlert = new Alert(Alert.AlertType.WARNING);
            userNotExistAlert.setTitle("Register before login");
            userNotExistAlert.setContentText("Please register before you try to login");
            userNotExistAlert.showAndWait();
        }
    }

    public void pressButtonPlayAgain(){
        //todo : use the "resetGame" function in the controller
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ChooseGameDifficulty.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            this.stage.setScene(new Scene(root));
            this.stage.setFullScreen(true);
            this.stage.show();
            Main.stg.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pressRegisterButton() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Register.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            this.stage.setScene(new Scene(root));
            this.stage.setFullScreen(true);
            this.stage.show();
            Main.stg.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pressLoginButton() throws IOException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            this.stage.setScene(new Scene(root));
            this.stage.setFullScreen(true);
            this.stage.show();
            Main.stg.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pressShowHighScoreTableButton() throws IOException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/HighScoreTable.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            this.stage.setScene(new Scene(root));
            this.stage.setFullScreen(true);
            this.stage.show();
            Main.stg.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void pressButtonSubmit(){
        //check if the text boxes are empty
        if(this.userNameTextBox.getText() == null || this.userNameTextBox.getText().trim().isEmpty()){
            Alert emptyUserNameAlert = new Alert(Alert.AlertType.WARNING);
            emptyUserNameAlert.setTitle("Enter user name");
            emptyUserNameAlert.setContentText("Please enter user name");
            emptyUserNameAlert.showAndWait();
        }

        if(this.passwordTextBox.getText() == null || this.passwordTextBox.getText().trim().isEmpty()){
            Alert emptyUserNameAlert = new Alert(Alert.AlertType.WARNING);
            emptyUserNameAlert.setTitle("Enter user password");
            emptyUserNameAlert.setContentText("Please enter your password");
            emptyUserNameAlert.showAndWait();
        }

        String userName = this.userNameTextBox.getText();
        String userPassword = this.passwordTextBox.getText();

        if(isValidUserDetails(userName, userPassword)){
            //todo :use the controller function to add the user to the DB
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                this.stage.setScene(new Scene(root));
                this.stage.setFullScreen(true);
                this.stage.show();
                Main.stg.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

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

    public void pressButtonMenu(ActionEvent backMenuEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MainMenu.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            this.stage.setScene(new Scene(root));
            this.stage.setFullScreen(true);
            this.stage.show();
            Main.stg.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}