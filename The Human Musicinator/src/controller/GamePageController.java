package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import util.Hint;

import java.net.URL;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {
    private GeneralController generalController = GeneralController.getInstance();
    @FXML
    private Label scoreLabel = new Label();
    @FXML
    private TextField answerTextBox = new TextField();
    @FXML
    private TextArea textArea = new TextArea();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.scoreLabel.setText(String.valueOf(generalController.getScore()));
        this.answerTextBox.setText(this.generalController.getModel().getMaskedEntityName());
    }
    @FXML
    /**
     * The function updates the user score
     */
    private void updateCurrentScore(){
        this.scoreLabel.setText(String.valueOf(generalController.getScore()));
    }

    @FXML
    /**
     * The function gives a hint to the user
     */
    public void pressGetHintButton(){
        Hint hint = generalController.getHint();
        updateCurrentScore();
        if (generalController.getScore() == 0){
            changeScreen("../view/Lose.fxml");
        } else {
            this.textArea.appendText(hint.info+"\n");
        }
    }

    @FXML
    /**
     * The function checks the user's pattern:
     *If he was right, he won.
     *If he was wrong, he can keep trying to guess.
     */
    public void pressCheckPatternButton() {
        if(generalController.validateUserGuess(this.answerTextBox.getText())){
            if (generalController.checkUserGuess(this.answerTextBox.getText())) {
                changeScreen("../view/Win.fxml");
            } else {
                updateCurrentScore();
                if (generalController.getScore() == 0) {
                    changeScreen("../view/Lose.fxml");
                    //show message to user that it's guess is incorrect
                    Alert wrongAnswerAlert = new Alert(Alert.AlertType.WARNING);
                    wrongAnswerAlert.setTitle("Wrong answer");
                    wrongAnswerAlert.setContentText("Try to guess again");
                    wrongAnswerAlert.showAndWait();
                    if (generalController.getScore() == 0) {
                        changeScreen("../view/Lose.fxml");
                    }
                }
            }
        } else {
            Alert answerNotAccordingToPattern = new Alert(Alert.AlertType.WARNING);
            answerNotAccordingToPattern.setTitle("Your guess doesn't match the pattern of the answer");
            answerNotAccordingToPattern.setContentText("Try again");
            answerNotAccordingToPattern.showAndWait();
        }
    }

    @FXML
    /**
     * The function gives the user the artist's name
     */
    public void pressCheatButton(){
        String artistName = generalController.getAnswer();
        this.answerTextBox.setText(artistName);
    }

    private void changeScreen(String path){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            fxmlLoader.setRoot(fxmlLoader.load());
            this.generalController.getStage().hide();
            this.generalController.getStage().setScene(new Scene(fxmlLoader.getRoot()));
            this.generalController.getStage().show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
