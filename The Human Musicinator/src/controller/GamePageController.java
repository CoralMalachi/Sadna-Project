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
    private Button getHintButton = new Button();

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
        if (hint == null ||  hint.info == null || hint.hintType == null){
            Alert noMoreHintAlert = new Alert(Alert.AlertType.WARNING);
            noMoreHintAlert.setTitle("There are no more hints");
            noMoreHintAlert.setContentText("Please try to guess or use the cheat button");
            noMoreHintAlert.showAndWait();
            this.getHintButton.disabledProperty();
        } else {
            updateCurrentScore();
            if (generalController.getScore() == 0){
                changeScreen("../view/Lose.fxml");
            } else {
                String description = getDescriptionOfHint(hint.hintType);
                this.textArea.appendText(description + hint.info+"\n");
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
                return "The type of the artist is: ";
            }
            case "artistBornAreaTypeQuery":{
                return "The was born in: ";
            }
            case "englishAlbumOfArtist": {
                return "The name of the artist's English album is: ";
            }
            case "englishSingleOfArtist":{
                return "The name of the artist's English single is: ";
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
            case "mostFreqLanguageSinglesArtist: ":{
                return "The most common language in the artist's singles is: ";
            }
            case "artistAliasNameQuery":{
                return "The stage name of the artist is: ";
            }
        }
        return null;
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
