package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import util.Hint;
import util.Record;
import java.io.IOException;
import model.IModel;
import util.User;

public class Controller{
    //members
    @FXML
    private Stage stage = new Stage();
    @FXML
    private TextField answerTextBox;
    @FXML
    private ScrollPane hintsScrollPane;
    @FXML
    private TextField passwordTextBox;
    @FXML
    private TextField userNameTextBox;
    @FXML
    private TextField scoreTextBox;
    @FXML
    private TableView<Record> highScoreTable ;

    private IModel model;


    //functions
    public Controller(IModel model){
        this.model = model;
        this.highScoreTable = new TableView<Record>();
    }

    @FXML
    /**
     * The function gives a hint to the user
     */
    public void pressGetHintButton(){
        Hint hint = model.getHint();
        updateCurrentScore();
        if (model.getScore() == 0){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Lose.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                this.stage.setScene(new Scene(root));
                this.stage.setFullScreen(true);
                this.stage.show();
                Main.stg.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        //add the hint to the scroll pane
        this.hintsScrollPane.addEventHandler(null, );
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
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Win.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    this.stage.setScene(new Scene(root));
                    this.stage.setFullScreen(true);
                    this.stage.show();
                    Main.stg.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //todo : decrease the user score
                //todo Limor : show another hint
                updateCurrentScore();
                if (model.getScore() == 0) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Lose.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        this.stage.setScene(new Scene(root));
                        this.stage.setFullScreen(true);
                        this.stage.show();
                        Main.stg.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //show message to user that it's guess is incorrect
                    Alert wrongAnswerAlert = new Alert(Alert.AlertType.WARNING);
                    wrongAnswerAlert.setTitle("Wrong answer");
                    wrongAnswerAlert.setContentText("Try to guess again");
                    wrongAnswerAlert.showAndWait();
                    if (model.getScore() == 0) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Lose.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            this.stage.setScene(new Scene(root));
                            this.stage.setFullScreen(true);
                            this.stage.show();
                            Main.stg.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/GamePage.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                this.stage.setScene(new Scene(root));
                this.stage.setFullScreen(true);
                //update the initial score of the user
                updateCurrentScore();
                this.stage.show();
                Main.stg.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
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
        addRecordToHighScoreTable();
        model.resetGame();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/GamePage.fxml"));
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
    /**
     * The function changes the current screen to the registration page
     */
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
    /**
     * The function changes the current screen to the login page
     */
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
    /**
     * The function changes the current screen to the high score table page
     */
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
            if (model.registerUser(registerUser)){
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
        //todo: use the controller function to add 'newRecord' to the high score table
        addRecordToHighScoreTable();
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

    /**
     * The function updates the user score
     */
    private void updateCurrentScore(){
        this.scoreTextBox.setText(String.valueOf(model.getScore()));
    }

    /**
     * The function returns a Record object with the current user details
     */
    private Record getRecordOfCurrentUser(){
        Record newUser = new Record();
        //todo: use the controller function to get the user name
        newUser.username = getUserName();
        //todo: use the controller function to get the current score of the user
        newUser.score = model.getScore();
        return newUser;
    }

    //todo: write addHintToScrollBarFunction

    /**
     * The function adds a record(with the current user details) to the high score table
     */
    private void addRecordToHighScoreTable(){
        this.highScoreTable.getItems().add(getRecordOfCurrentUser());
    }
}