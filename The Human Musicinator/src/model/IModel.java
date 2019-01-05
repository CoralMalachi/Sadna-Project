package model;

import util.Record;
import util.User;
import util.Entity;
import util.Hint;
import java.util.List;

public interface IModel {
    boolean registerUser(User user);

    boolean loginUser(User user);

    boolean insertIntoRecordsTable(Record Record);

    Entity getEntity();

    List<Record> getHighScoreTable();

    void startGame(User user/*, Entity entity*/);

    boolean validateUserGuess(String userGuess);

    boolean checkUserGuess(String userGuess);

    Hint getHint();

    boolean isReady();

    int getScore();

    String getAnswer();

    void resetGame();
}