package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import util.Record;

public class HighScoreTableController {
    private GeneralController generalController = GeneralController.getInstance();
    @FXML
    private TableView<Record> highScoreTable ;

    @FXML
    /**
     * The function exits from the game
     */
    public void pressExitButton(){
        System.exit(0);
    }

    @FXML
    /**
     * The function changes the current screen to the main menu
     */
    public void pressGoToMenuButton(){
        generalController.resetGame();
        changeScreen("../view/MainMenu.fxml");
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
