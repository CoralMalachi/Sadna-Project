package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    static Stage stg;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));

        primaryStage.setTitle("The Human Musicinator");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}