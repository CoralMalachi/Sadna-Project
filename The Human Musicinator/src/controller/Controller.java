package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

    private boolean isValidIdNumber(String idNumber){
        return idNumber.matches("\\d{9}");
    }
    private boolean isUserExistInDB(String idNumber){
        //Todo : check if user exist in DB and return the answer
        return true;//I wrote this only in order to avoid compilation errors
    }

    @FXML
    public void pressButtonLogin()
    {
        String idNumber = this.idNumberTextBox.getText();

        if(!isValidIdNumber(idNumber)){
            Alert wrongIdAlert = new Alert(Alert.AlertType.WARNING);
            wrongIdAlert.setTitle("Wrong ID number");
            wrongIdAlert.setHeaderText("Your Id number is not exist");
            wrongIdAlert.setContentText("Please register before trying to login or try again");
            wrongIdAlert.showAndWait();
        }

        if (isUserExistInDB(idNumber)) {
            //Todo: save the user in DB and restart game
            try {

                System.out.print("limor and coral");
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
        }
    }

    //todo Coral: why do you get playAgainEvent parameter? do we need it? you just need
    public void pressButtonPlayAgain(ActionEvent playAgainEvent){
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
        String userName = this.userNameTextBox.getText();
        String userPassword = this.passwordTextBox.getText();

        if(isValidUserName(userName) &&
                isValidLastName(userPassword) ){
            //todo :use the controller function to add the user to the DB
            //go to login page
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

    private boolean isValidUserName(String firstName) {
        return firstName.matches( "[A-Z][a-zA-Z]*" );
    }

    private boolean isValidLastName(String lastName) {
        return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
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