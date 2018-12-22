package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class LoseController {
    //todo Coral: why do you get playAgainEvent parameter? do we need it? you just need
    public void pressButtonPlayAgain(ActionEvent playAgainEvent){
        //todo : use the "resetGame" function in the controller
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
    }
}
