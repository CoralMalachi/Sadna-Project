package model;

import util.Hint;
import util.User;
import util.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState {
    private Entity entity;
    private String maskedEntityName;
    private User user;
    private int numOfHintsGiven;
    private int maxNumOfHints;
    private List<Hint> hints;
    private boolean isFinished;
    private boolean hasWon;
    private String difficulty;

    // Change to initializer methods and add abject state (Ready/NotReady for use) for protection.
    public GameState(Entity entity, User user, String difficulty){
        this.entity = entity;
        this.maskedEntityName = Masker.mask(entity.name, 5, "*"); // Todo: maybe change masking ratio to be dependant on difficulty.
        this.user = user;
        this.difficulty = difficulty;
        this.hasWon = false;
        this.isFinished = false;
        this.numOfHintsGiven = 0;
        this.maxNumOfHints = 10;  // Todo: maybe change this to be dependant on difficulty.
        // this.getHintListFromDB();
    }

    // Todo: complete this method.
    // private List<Hint> getHintListFromDB(){
    //     List<Hint> HintsList = new ArrayList<Hint>();
    //     String entity = this.entity; // Should use this
    //     String difficulty = this.difficulty; // Should use this
    //     return HintsList;
    // }

    public void updateHintsGivenCount(){
        if (this.numOfHintsGiven <= this.maxNumOfHints){
            this.numOfHintsGiven += 1;
        } else {
            this.isFinished = true;
        }
    }

    /**
     * Returns true if the user's guess fits the given masked entity pattern, false otherwise.
     * @param userGuess The user's guess.
     * @return True if the user's guess fits the given masked entity pattern, false otherwise.
     */
    public boolean validateUserGuess(String userGuess){
        if (userGuess.length() != this.entity.name.length()){
            return false;
        }
        ArrayList<String> userGuessList = new ArrayList<String>(Arrays.asList(userGuess.split("")));
        ArrayList<String> maskedEntityList = new ArrayList<String>(Arrays.asList(this.maskedEntityName.split("")));
        for (int i = 0; i < this.entity.name.length(); i++){
            if (maskedEntityList.get(i).equals("*")){
                if (!maskedEntityList.get(i).equals(userGuessList.get(i))){
                    return false;
                }
            } else {
                if (userGuessList.get(i).equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if the user's guess is the entity, false otherwise.
     * @param userGuess The user's guess.
     * @return True if the user's guess is the entity, false otherwise.
     */
    public boolean checkUserGuess(String userGuess){
        ArrayList<String> userGuessList = new ArrayList<String>(Arrays.asList(userGuess.split("")));
        ArrayList<String> entityList = new ArrayList<String>(Arrays.asList(this.entity.name.split("")));
        for (int i = 0; i < this.entity.name.length(); i++){
            if (!userGuessList.get(i).equals(entityList.get(i))){
                return false;
            }
        }
        this.hasWon = true;
        this.isFinished = true;
        return true;
    }

}
