package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by limor on 18/12/2018.
 */
public class HighScoreTableController {
    @FXML
    private Button backToMenuButton;
    @FXML
    private Stage stage;
    @FXML
    private BorderPane root;

    @FXML
    public void pressGoToMenuButton(){
        //todo: use the controller function that reset the game
        try {
            //start new game - go to choosing game difficulty form
            this.stage = (Stage) backToMenuButton.getScene().getWindow();
            this.root = FXMLLoader.load(getClass().getResource("ChooseGameDifficulty.fxml"));
            Scene scene = new Scene(this.root);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            this.stage.setScene(scene);
            this.stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void pressExitButton(){
        System.exit(0);
    }
}