package view;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GamePageController {
    @FXML
    private TextField answerTextBox;
    @FXML
    private Label hintLabel;
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
}

