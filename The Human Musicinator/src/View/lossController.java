package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class lossController {
    public void pressButtonPlayAgain(ActionEvent playAgainEvent){
        System.out.print("move to play page");

    }

    public void pressButtonMenu(ActionEvent backMenuEvent){
        try {
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
