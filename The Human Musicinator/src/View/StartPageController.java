package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//todo Coral : I already wrote this page in "MainMenuController.java"
//todo Coral: i'm not sure if we need to use ActionEvents at all because we don't use them

public class StartPageController {
    public void pressButtonRegister(ActionEvent login_event){
        //TODO: forward to register page (by limor)
        //todo Coral: I wrote the Register page

//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
//            Parent root = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//            Main.stg.close();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        System.out.print("Register");
        //Pane root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    }

    public void pressButtonLogin (ActionEvent register_event){
        System.out.print("Login!");
        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("a.fxml"));
//            Parent root = (Parent) fxmlLoader.load();

            //new Login().setVisible(true);

            Stage stage = new Stage();
            //stage.setScene(new Scene(root));
            //stage.show();
            //Main.stg.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pressButtonScores (ActionEvent show_scores_event){
        System.out.print("Show Score!");
    }
}

