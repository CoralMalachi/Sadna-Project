package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class MainMenuController {
    private GeneralController generalController = GeneralController.getInstance();

    @FXML
    /**
     * The function changes the current screen to the registration page
     */
    public void pressRegisterButton() throws IOException {
        changeScreen("../view/Register.fxml");
    }

    @FXML
    /**
     * The function exits from the game
     */
    public void pressExitButton(){
        System.exit(0);
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

    @FXML
    /**
     * The function changes the current screen to the login page
     */
    public void pressLoginButton() throws IOException{
        changeScreen("../view/Login.fxml");
    }

    @FXML
    /**
     * The function changes the current screen to the high score table page
     */
    public void pressShowHighScoreTableButton() throws IOException{
        changeScreen("../view/HighScoreTable.fxml");
    }
}
