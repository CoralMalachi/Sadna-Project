package main;

import controller.GeneralController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {
    public static Stage stage = new Stage();
    public static void main(String[] args) {
        GeneralController generalController = GeneralController.getInstance();
        Application.launch(Main.class, args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        //initialize the general controller of the application
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/MainMenu.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("The Human Musicinator");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
