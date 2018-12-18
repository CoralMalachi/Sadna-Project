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
public class ChooseGameDifficultyController {
    @FXML
    private Button hardButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button easyButton;
    @FXML
    private Stage stage;
    @FXML
    private BorderPane root;

    @FXML
    public void pressHardButton() throws IOException {
        //todo: update the Controller (of the whole application) about the game difficulty
        this.stage = (Stage) hardButton.getScene().getWindow();
        this.root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Scene scene = new Scene(this.root);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        this.stage.setScene(scene);
        this.stage.show();
    }

    @FXML
    public void pressMediumButton() throws IOException{
        //todo: update the Controller (of the whole application) about the game difficulty
        this.stage = (Stage) mediumButton.getScene().getWindow();
        this.root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Scene scene = new Scene(this.root);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        this.stage.setScene(scene);
        this.stage.show();
    }

    @FXML
    public void pressEasyButton() throws IOException{
        //todo: update the Controller (of the whole application) about the game difficulty
        this.stage = (Stage) easyButton.getScene().getWindow();
        this.root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        Scene scene = new Scene(this.root);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        this.stage.setScene(scene);
        this.stage.show();
    }
}
