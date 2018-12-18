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
    public void pressHardButton() throws IOException {
        this.stage = (Stage) registerButton.getScene().getWindow();
        this.root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(this.root);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        this.stage.setScene(scene);
        this.stage.show();
    }

    @FXML
    public void pressLoginButton() throws IOException{
        this.stage = (Stage) loginButton.getScene().getWindow();
        this.root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(this.root);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        this.stage.setScene(scene);
        this.stage.show();
    }

    @FXML
    public void pressShowHighScoreTableButton() throws IOException{
        this.stage = (Stage) highScoreTableButton.getScene().getWindow();
        BorderPane root;
        this.root = FXMLLoader.load(getClass().getResource("HighScoreTable.fxml"));
        Scene scene = new Scene(this.root);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        this.stage.setScene(scene);
        this.stage.show();
    }
}
