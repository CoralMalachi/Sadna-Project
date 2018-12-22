package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//todo Coral : what is this page ? only an example? I wrote the page "MainMenu" instead of this.
//todo Coral : in the main menu we need 4 buttons according to the flow graph. look what I wrote.
public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    static Stage stg;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stg = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


//    public static void main(String[] args) {
//        launch(args);
//    }
}
