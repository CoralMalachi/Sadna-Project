package controller;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Main;
import model.Model;
import model.IModel;
import util.Hint;
import util.Record;
import util.User;

public class GeneralController implements IController {
    private IModel model;
    private Stage stage;
    private static GeneralController instance;
    public static GeneralController getInstance(){
        if(instance == null ){
            instance = new GeneralController();
        }
        return instance;
    }


    private GeneralController(){
        this.model = new Model();
        this.stage = Main.stage;
    }

     public IModel getModel(){
        return this.model;
     }

     public Stage getStage(){
         return this.stage;
     }

     public Hint getHint(){
         return this.model.getHint();
     }

     public int getScore(){
         return this.model.getScore();
     }

     public boolean validateUserGuess(String userGuess){
         return this.model.validateUserGuess(userGuess);
     }

     public boolean checkUserGuess(String userGuess){
         return this.model.checkUserGuess(userGuess);
     }

     public String getAnswer(){
         return this.model.getAnswer();
     }

     public void resetGame(){
         this.model.resetGame();
     }

     public void startGame(User user){
         this.model.startGame(user);
     }

     public boolean loginUser(User user){
         return this.model.loginUser(user);
     }

     public boolean insertIntoRecordsTable(Record record){
         return this.model.insertIntoRecordsTable(record);
     }

     public User getUser(){
         return this.model.getUser();
     }

     public boolean registerUser(User user){
         return this.model.registerUser(user);
     }
}