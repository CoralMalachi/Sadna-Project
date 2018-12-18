package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WonPageController {
    //todo Coral : why do you get playAgainEvent parameter? you don't use it
    public void pressButtonPlayAgain(ActionEvent playAgainEvent){
        //todo Coral : you should write the code that reset the game +
        //todo Coral : change to "ChooseGameDifficulty.fxml" window .
        System.out.print("move to play page");
        //todo Coral : don't forget to finish the game-page that you started to write.
        //todo Coral : I am talking about the page that you wrote it's buttons in Hebrew.
    }

    //todo Coral : why do you get backMenuEvent parameter? you don't use it
    public void pressButtonMenu(ActionEvent backMenuEvent){
        try {
            //todo Coral : use "MainMenu.fxml" - this is the updated man menu of the game
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
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
