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
    //private final int MAX_SCORE = 50;

    public GameState() {        
        this.resetGame();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        this.maskedEntityName = Masker.mask(entity.name, 3, "*");
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void setHintList(List<Hint> hints) {
        this.hints = hints;
    }

    public String getMaskedEntityName(){
        return this.maskedEntityName;
    }

    public Hint getHint() {
        try{
            Hint h = this.hints.get(numOfHintsGiven);
            updateHintsGivenCount();
            return h;
        } catch (Exception e){
            //todo : get another hint and return it - this is the problem
            return null;
        }
    }

    public boolean getIsFinished() {
        return this.isFinished;
    }

    public boolean getHasWon() {
        return this.hasWon;
    }
    public int getMaxNumOfHints() {
        return this.maxNumOfHints;
    }

    public int getScore() {
        return (this.maxNumOfHints - this.numOfHintsGiven) * 10;
    }

    public int getNumRemainingHints() {
        return this.maxNumOfHints - this.numOfHintsGiven;
    }

    private void updateHintsGivenCount() {
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
    public boolean validateUserGuess(String userGuess) {
        String guess = userGuess.toLowerCase();
        String maskedEntityName = this.maskedEntityName.toLowerCase();
        if (guess.length() != this.entity.name.length()){
            return false;
        }
        ArrayList<String> userGuessList = new ArrayList<String>(Arrays.asList(guess.split("")));
        ArrayList<String> maskedEntityList = new ArrayList<String>(Arrays.asList(maskedEntityName.split("")));
        for (int i = 0; i < this.entity.name.length(); i++){
            if (!maskedEntityList.get(i).equals("*")){
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
    public boolean checkUserGuess(String userGuess) {
        String guess = userGuess.toLowerCase();
        String entityName = this.entity.name.toLowerCase();
        ArrayList<String> userGuessList = new ArrayList<String>(Arrays.asList(guess.split("")));
        ArrayList<String> entityList = new ArrayList<String>(Arrays.asList(entityName.split("")));
        for (int i = 0; i < entityName.length(); i++){
            if (!userGuessList.get(i).equals(entityList.get(i))){
                return false;
            }
        }
        this.hasWon = true;
        this.isFinished = true;
        return true;
    }

    // Cheat - returns the name of the entity.
    public String getAnswer() {
        return this.entity.name;
    }

    public void resetGame() {
        this.user = null;
        this.entity = null;
        this.hasWon = false;
        this.isFinished = false;
        this.numOfHintsGiven = 0;
        this.maxNumOfHints = 7;

    }

    public boolean isReady() {
        if (user != null && entity != null) {
            return true;
        }
        return false;
    }

}
