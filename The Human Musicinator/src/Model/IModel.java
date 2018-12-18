package model;

import java.util.List;

public interface IModel {
    boolean registerUser(User user);
    
    boolean loginUser(User user);

    boolean insertIntoRecordsTable(Record Record);

    List<Hint> getHintList(int difficulty);

    Entity getEntity(int difficulty);

    List<Record> getRecords(int difficulty);


}