package controller;

public interface IMusicinatorController{
    boolean checkPattern(String userGuess);
    boolean maskEntity();
    boolean resetGame();
    boolean hasGameEnded();
}