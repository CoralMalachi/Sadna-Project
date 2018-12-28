package model;

import util.Record;
import util.User;
import java.util.List;

public interface IModel {
    boolean registerUser(User user);

    boolean loginUser(User user);

    boolean insertIntoRecordsTable(Record Record);

    //List<Hint> getHintList(String difficulty);

    //Entity getEntity(String difficulty);

    List<Record> getRecords(String difficulty);
}