package model;

import java.sql.*;
import util.User;
import util.Record;
import java.util.List;
import java.util.ArrayList;

public class Model {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

    private static final String USER = "root";
    private static final String PASS = "root";

    /**
     * Constructor.
     */
    public Model() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Registers a user - inserts his information to the database.
     * @param user The user to be registered.
     * @return True is succeeded, false otherwise.
     */
    public boolean registerUser(User user) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM UserList WHERE UserName = ?");
             PreparedStatement registerStmt = conn.prepareStatement("INSERT INTO Userlist VALUES ('0', ?, ?)")) {
            // Checks if this username is already taken, returns false if it is.
            checkStmt.setString(1, user.username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return false;
            }
            // The username is not taken, inserting the user's information to the database.
            registerStmt.setString(1, user.username);
            registerStmt.setString(2, user.password);
            registerStmt.executeUpdate();
            return true;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }                
    }

    /**
     * Checks if the inserted user information exists.
     * @param user The user that is trying to log in.
     * @return True if the inserted information exists and valid, false otherwise.
     */
    public boolean loginUser(User user) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Userlist WHERE UserName = ? AND UserPassword = ?")) {
            stmt.setString(1, user.username);
            stmt.setString(2, user.password);
            ResultSet rs = stmt.executeQuery();
            // Resultset is empty, meaning that the inserted data does not match any registered user.
            if (rs.next() == false) {
                return false;
            }
            return true;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }        
    }

    /**
     * Inserts a new record into the matching difficulty's highscore table.
     * @param record The record to be inserted.
     * @return True if succeeded, false otherwise.
     */
    public boolean insertIntoRecordsTable(Record record) {
        String table = record.difficulty + "records";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + table + " VALUES ('0', ?, ?)")) {
            stmt.setString(1, record.username);
            stmt.setString(2, String.valueOf(record.score));
            stmt.executeUpdate();
            return true;
        } catch(SQLException se) {
            se.printStackTrace();
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }        
    }

    /**
     * Returns a highscore table based on the difficulty received.
     * @param difficulty The difficulty level of the requested highscore table.
     * @return The List of the scores, null if there's an error.
     */
    public List<Record> getRecords(String difficulty) {
        String table = difficulty + "records";
        List<Record> scores = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + table + " ORDER BY Score DESC")) {
            ResultSet rs = stmt.executeQuery();
            // Creating a list of records from the ResultSet.
            while (rs.next()) {
                String username = rs.getString("Username");
                int score = rs.getInt("Score");
                Record r = new Record();
                r.username = username;
                r.score = score;
                r.difficulty = difficulty;
                scores.add(r);
            }
            return scores;
        } catch(SQLException se) {
            se.printStackTrace();
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}