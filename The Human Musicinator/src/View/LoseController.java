package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//todo Coral: change the name to "LoseController" with big L in the start
public class LoseController {
    //todo Coral: why do you get playAgainEvent parameter? do we need it? you just need
    //todo Coral: to reset the game (function in the controller) + change the window to
    //todo Coral: "chooseGameDifficulty" /
    //todo Coral: please change the name of the fxml file to "Lose.fxml".
    //todo Coral : there is convention that if the fxml file name is 'x' so the appropriate controller is "xController.java"
    public void pressButtonPlayAgain(ActionEvent playAgainEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChooseGameDifficulty.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Main.stg.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    //todo Coral: why do you get backMenuEvent parameter ? do we need it?
    public void pressButtonMenu(ActionEvent backMenuEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Main.stg.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        //System.out.print("Login");
        //Pane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    }


}
