package controller;

public interface IMusicinatorController{
    boolean checkPattern(String userGuess);
    boolean maskEntity();
//    boolean checkRegistration(UserInfo userInfo);
//    boolean checkLogin(UserInfo userInfo);
//    boolean initGame(GameState gameState);
    boolean resetGame();
    boolean hasGameEnded();
}