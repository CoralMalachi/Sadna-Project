package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.Record;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HighScoreTableController implements Initializable {
    private GeneralController generalController = GeneralController.getInstance();
    @FXML
    private TableView<Record> highScoreTable ;
    @FXML
    public TableColumn<Record, String> userNameCol;
    @FXML
    public TableColumn<Record, Integer> scoreCol;
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        //highScoreTable.setEditable(true);
        List<Record> galList = this.generalController.getModel().getHighScoreTable();

        ObservableList<Record> recordList = FXCollections.observableArrayList();
        for (Record r : galList){
            recordList.add(r);
        }
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userNameCol"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("scoreCol"));
        this.highScoreTable.setItems(recordList);

    }
    @FXML
    /**
     * The function exits from the game
     */
    public void pressExitButton(){
        System.exit(0);
    }

    @FXML
    /**
     * The function changes the current screen to the main menu
     */
    public void pressGoToMenuButton(){
        generalController.resetGame();
        changeScreen("../view/MainMenu.fxml");
    }

    private void changeScreen(String path){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            fxmlLoader.setRoot(fxmlLoader.load());
            this.generalController.getStage().hide();
            this.generalController.getStage().setScene(new Scene(fxmlLoader.getRoot()));
            this.generalController.getStage().show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
