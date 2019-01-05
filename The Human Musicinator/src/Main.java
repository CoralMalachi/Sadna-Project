import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import model.IModel;
public class Main extends Application {
    public Model model = new Model();
    public static void main(String[] args) {

        Application.launch(Main.class, args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/MainMenu.fxml"));
        Parent root = loader.load();

        Controller controller = loader.<Controller>getController();
        controller.setStage(primaryStage);
        controller.setModel(model);
        primaryStage.setTitle("The Human Musicinator");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
