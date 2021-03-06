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
    @FXML
    private Label maskEntityLabel = new Label();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.scoreLabel.setText(String.valueOf(generalController.getScore()));
        this.maskEntityLabel.setText(this.generalController.getModel().getMaskedEntityName());
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
    public void pressGetHintButton() {
        if (this.generalController.getModel().getNumRemainingHints() <= 0) {
            Alert noMoreHintAlert = new Alert(Alert.AlertType.WARNING);
            noMoreHintAlert.setTitle("There are no more hints");
            noMoreHintAlert.setContentText("Game Over");
            noMoreHintAlert.showAndWait();
            changeScreen("/resources/Lose.fxml");
        } else {
            Hint hint = generalController.getHint();
            if (hint != null && hint.info != null && hint.hintType != null) {
                updateCurrentScore();
                String description = getDescriptionOfHint(hint.hintType);
                this.textArea.appendText(description + hint.info + "\n");
            }
        }
    }



    private String getDescriptionOfHint(String hintType){
        switch(hintType){
            case "bornPlaceQuery": {
                return "The artist was born in: ";
            }
            case "genderQuery": {
                return "The artist's gender is: ";
            }
            case "artistTypeQuery":{
                return "The type of the artist is(Person/Band): ";
            }
            case "artistBornAreaTypeQuery":{
                return "The artist was born in a: ";
            }
            case "englishAlbumOfArtist": {
                return "Name of one of the artist's albums is: ";
            }
            case "englishSingleOfArtist":{
                return "Name of one of the artist's singles is: ";
            }
            case "numberOfArtistAlbumsQuery": {
                return "The number of albums of the artist is: ";
            }
            case "numberOfSingleAlbumsQuery" : {
                return "The number of the artist's singles is: ";
            }
            case "mostFreqReleasesAreaOfArtistQuery" : {
                return "The most frequent releases area of artist is: ";
            }
            case "mostActiveYearArtistQuery" : {
                return "The artist's most active year is: ";
            }
            case "mostFreqLanguageAlbumsArtist" :{
                return "The most common language in the artist's albums is: ";
            }
            case "mostFreqLanguageSinglesArtist":{
                return "The most common language in the artist's singles is: ";
            }
            case "artistAliasNameQuery":{
                return "The stage name of the artist is: ";
            }
            default:{
                return "Another hint is: ";
            }
        }
    }

    @FXML
    /**
     * The function checks the user's pattern:
     *If he was right, he won.
     *If he was wrong, he can keep trying to guess.
     */
    public void pressCheckPatternButton() {
        if (generalController.validateUserGuess(this.answerTextBox.getText())) {
            if (generalController.checkUserGuess(this.answerTextBox.getText())) {
                changeScreen("/resources/Win.fxml");
            } else {
                Alert wrongAnswerAlert = new Alert(Alert.AlertType.WARNING);
                wrongAnswerAlert.setTitle("Wrong answer");
                wrongAnswerAlert.setContentText("Try to guess again");
                wrongAnswerAlert.showAndWait();
            }
        } else {
            Alert wrongAnswerAlert = new Alert(Alert.AlertType.WARNING);
            wrongAnswerAlert.setTitle("Invalid input");
            wrongAnswerAlert.setContentText("Your answer isn't in the valid format");
            wrongAnswerAlert.showAndWait();
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
            this.generalController.getStage().setScene(new Scene(fxmlLoader.getRoot(), 900, 700));
            this.generalController.getStage().show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
