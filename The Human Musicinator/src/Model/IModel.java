package Model;

/**
 * Created by user on 01/12/2018.
 */
public interface IModel {
    void createDB();

    Hint getHintFromDB(String tableName, Difficulty difficulty);

    boolean registerUser(User user);

    boolean loginUser(User user);

    boolean addScoreToTable(int score, User user);

    boolean addGameData();//num retries, type os music

}
