package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Main.stg.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pressMediumButton() throws IOException {
        //todo: update the Controller (of the whole application) about the game difficulty
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Main.stg.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pressEasyButton() throws IOException {
        //todo: update the Controller (of the whole application) about the game difficulty
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Main.stg.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
