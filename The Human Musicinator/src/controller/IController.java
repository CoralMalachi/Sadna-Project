package controller;

import util.Hint;
import util.Record;
import util.User;

public interface IController {
    Hint getHint();

    int getScore();

    boolean validateUserGuess(String userGuess);

    boolean checkUserGuess(String userGuess);

    String getAnswer();

    void resetGame();

    void startGame(User user);

    boolean loginUser(User user);

    boolean insertIntoRecordsTable(Record record);

    boolean registerUser(User user);
}