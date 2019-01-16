package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import util.Record;
import util.User;

public class WinController {
    private GeneralController generalController = GeneralController.getInstance();

    @FXML
    /**
     * The function initializes a new game
     */
    public void pressButtonPlayAgain(){
        if (getRecordOfCurrentUser().score > 0){
            generalController.insertIntoRecordsTable(getRecordOfCurrentUser());
        }
        User u = generalController.getUser();
        generalController.resetGame();
        generalController.startGame(u);
        changeScreen("/resources/GamePage.fxml");
    }

    @FXML
    /**
     * The function saves the details of the current user and changes the
     * current screen to the main menu
     */
    public void pressButtonMenu(){
        if (getRecordOfCurrentUser().score > 0){
            generalController.insertIntoRecordsTable(getRecordOfCurrentUser());
        }
        generalController.resetGame();
        changeScreen("/resources/MainMenu.fxml");
    }

    private void changeScreen(String path){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            fxmlLoader.setRoot(fxmlLoader.load());
            this.generalController.getStage().hide();
            this.generalController.getStage().setScene(new Scene(fxmlLoader.getRoot(), 900, 700));
            this.generalController.getStage().show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The function returns a Record object with the current user details
     */
    private Record getRecordOfCurrentUser(){
        Record newUser = new Record();
        newUser.username = generalController.getUser().username;
        newUser.score = generalController.getScore();
        return newUser;
    }
}
