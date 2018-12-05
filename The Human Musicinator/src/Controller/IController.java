package Controller;

import Model.Hint;

/**
 * Created by user on 01/12/2018.
 */
public interface IController {
    void startMenu();

    void startGame();

    void endGame();

    Hint getHintFRomDB();

}
