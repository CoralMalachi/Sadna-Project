package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.Main;
import model.Model;
import util.Hint;
import util.Record;
import java.io.IOException;
import model.IModel;
import util.User;

public class Controller{
    //members
    @FXML
    private TextField answerTextBox;
    @FXML
    private ScrollPane hintsScrollPane;
    @FXML
    private TextField passwordTextBox;
    @FXML
    private TextField userNameTextBox;
    @FXML
    private Label scoreLabel;// add new Label();
    @FXML
    private TableView<Record> highScoreTable ;

    private IModel model =  Model.getInstance();
    private Stage stage = Main.stage;
    private FXMLLoader fxmlLoader;
    public static Parent root;

    //functions
    public Controller(){
    }

    public static void setRoot(Parent newRoot){
        root = newRoot;
    }


    @FXML
    /**
     * The function gives a hint to the user
     */
    public void pressGetHintButton(){
        Hint hint = model.getHint();
        updateCurrentScore();
        if (model.getScore() == 0){
            changeScreen("../view/Lose.fxml");
        }
        //todo: correct - add the hint to the scroll pane
        //this.hintsScrollPane.addEventHandler(null, );
    }

    @FXML
    /**
     * The function checks the user's pattern:
     *If he was right, he won.
     *If he was wrong, he can keep trying to guess.
     */
    public void pressCheckPatternButton() {
        if(model.validateUserGuess(this.answerTextBox.getText())){
            if (model.checkUserGuess(this.answerTextBox.getText())) {
                changeScreen("../view/Win.fxml");
            } else {
                //todo Limor : show another hint
                updateCurrentScore();
                if (model.getScore() == 0) {
                    changeScreen("../view/Lose.fxml");
                    //show message to user that it's guess is incorrect
                    Alert wrongAnswerAlert = new Alert(Alert.AlertType.WARNING);
                    wrongAnswerAlert.setTitle("Wrong answer");
                    wrongAnswerAlert.setContentText("Try to guess again");
                    wrongAnswerAlert.showAndWait();
                    if (model.getScore() == 0) {
                        changeScreen("../view/Lose.fxml");
                    }
                }
            }
        }
        //todo Limor : show message to user - not according to pattern
    }

    @FXML
    /**
     * The function gives the user the artist's name
     */
    public void pressCheatButton(){
        String artistName = model.getAnswer();
        this.answerTextBox.setText(artistName);
    }

    @FXML
    /**
     * The function changes the current screen to the main menu
     */
    public void pressGoToMenuButton(){
        //todo: use the controller function that reset the game
        model.resetGame();
        changeScreen("../view/MainMenu.fxml");
    }

    @FXML
    /**
     * The function exits from the game
     */
    public void pressExitButton(){
        System.exit(0);
    }

    /**
     * The function returns true is the user exist in the DB or false otherwise
     */
    private boolean isUserExistInDB(String userName, String userPassword){
        User loginUser = new User();
        loginUser.username = userName;
        loginUser.password = userPassword;
        return model.loginUser(loginUser);
    }

    @FXML
    /**
     * The function is responsible for the user login
     */
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
            User newUser = new User();
            newUser.username = userName;
            newUser.password = userPassword;
            model.startGame(newUser);
            changeScreen("../view/GamePage.fxml");
            updateCurrentScore();
        } else {
            Alert userNotExistAlert = new Alert(Alert.AlertType.WARNING);
            userNotExistAlert.setTitle("Please register before trying to login");
            userNotExistAlert.setContentText("If you are already registered, check the details you entered");
            userNotExistAlert.showAndWait();
        }
    }

    @FXML
    /**
     * The function initializes a new game
     */
    public void pressButtonPlayAgain(){
        model.insertIntoRecordsTable(getRecordOfCurrentUser());
        User u = model.getUser();
        model.resetGame();
        model.startGame(u);
        changeScreen("../view/GamePage.fxml");
    }

    @FXML
    /**
     * The function changes the current screen to the registration page
     */
    public void pressRegisterButton() throws IOException {
        changeScreen("../view/Register.fxml");
    }

    @FXML
    /**
     * The function changes the current screen to the login page
     */
    public void pressLoginButton() throws IOException{
        changeScreen("../view/Login.fxml");
    }

    @FXML
    /**
     * The function changes the current screen to the high score table page
     */
    public void pressShowHighScoreTableButton() throws IOException{
        changeScreen("../view/HighScoreTable.fxml");
    }

    @FXML
    /**
     * The function is responsible for the user registration
     */
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
            User registerUser = new User();
            registerUser.username = userName;
            registerUser.password = userPassword;
            if (this.model.registerUser(registerUser)){
                changeScreen("../view/Login.fxml");
            } else {
                Alert emptyUserNameAlert = new Alert(Alert.AlertType.WARNING);
                emptyUserNameAlert.setTitle("The user name is already in use");
                emptyUserNameAlert.setContentText("Please choose different user name");
                emptyUserNameAlert.showAndWait();
            }
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

    @FXML
    /**
     * The function saves the details of the current user and changes the
     * current screen to the main menu
     */
    public void pressButtonMenu(){
        model.insertIntoRecordsTable(getRecordOfCurrentUser());
        //todo Limor: use getHighScoreTable that Gal Wrote
        changeScreen("../view/MainMenu.fxml");
    }

    @FXML
    /**
     * The function updates the user score
     */
    private void updateCurrentScore(){
        this.scoreLabel.setText(String.valueOf(model.getScore()));
        System.out.println("the text in the label is" + this.scoreLabel.getText());
    }

    /**
     * The function returns a Record object with the current user details
     */
    private Record getRecordOfCurrentUser(){
        Record newUser = new Record();
        newUser.username = model.getUser().username;
        newUser.score = model.getScore();
        return newUser;
    }

    //todo Limor: write addHintToScrollBarFunction

    private void changeScreen(String path){
        try {
            this.fxmlLoader = new FXMLLoader(getClass().getResource(path));
            this.fxmlLoader.setRoot(this.fxmlLoader.load());
            this.stage.hide();
            this.stage.setScene(new Scene(this.fxmlLoader.getRoot()));
            this.stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}