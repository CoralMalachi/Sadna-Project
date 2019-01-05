package main;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {
    public static Stage stage = new Stage();
    public static void main(String[] args) {

        Application.launch(Main.class, args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/MainMenu.fxml"));
        Parent root = loader.load();
        Controller.setRoot(root);
        primaryStage.setTitle("The Human Musicinator");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
