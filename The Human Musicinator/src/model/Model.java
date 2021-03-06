package model;


import java.io.*;
import java.sql.*;

import util.User;
import util.Record;
import util.Entity;
import util.Hint;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Model implements model.IModel {
    private static String JDBC_DRIVER;
    private static String DB_URL;

    private static String USER;
    private static String PASS;

    private model.GameState state;

    private static String entityQuery = "select artist.id,artist.name from artist inner join artist_meta on artist_meta.id=artist.id where artist_meta.rating>85 and artist_meta.rating_count>3 order by rand() limit 1";

    /**
     * Constructor.
     */
    public Model() {
        readConfig();
        try {
            Class.forName(JDBC_DRIVER);            
            this.state = new GameState();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readConfig() {
        //File file = new File("../../src/config.txt");
        try (InputStream in = getClass().getResourceAsStream("/config.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            JDBC_DRIVER = br.readLine().replaceFirst("JDBC_DRIVER=", "");
            DB_URL = br.readLine().replaceFirst("DB_URL=", "");
            USER = br.readLine().replaceFirst("USER=", "");
            PASS = br.readLine().replaceFirst("PASS=", "");
        } catch (Exception e) {
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
             PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM users WHERE Username = ?");
             PreparedStatement registerStmt = conn.prepareStatement("INSERT INTO users VALUES ('0', ?, ?)")) {
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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE Username = ? AND Password = ?")) {
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
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Highscore VALUES ('0', ?, ?)")) {
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
     * @return The List of the scores, null if there's an error.
     */
    public List<Record> getHighScoreTable() {
        List<Record> scores = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Highscore ORDER BY Score DESC")) {
            ResultSet rs = stmt.executeQuery();
            // Creating a list of records from the ResultSet.
            while (rs.next()) {
                String username = rs.getString("Username");
                int score = rs.getInt("Score");
                Record r = new Record();
                r.username = username;
                r.score = score;
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

    public Entity getEntity() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(entityQuery)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Entity entity = new Entity();
                entity.id = rs.getInt("id");
                entity.name = rs.getString("name");
                return entity;
            }
            return null;                 
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Hint executeQuery(String query) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, state.getEntity().id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Hint hint = new Hint();
                hint.info = rs.getString(1);
                return hint;
            }
            return null;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Hint> getHintList() {
        List<Hint> hintList = new ArrayList<>();
        Random random = new Random();
        int numOfHints = state.getMaxNumOfHints();
        List<String> keys = new ArrayList<>(Queries.HINT_QUERY_MAP.keySet());
        for (int i = 0; i < numOfHints; i++) {
            String randomKey = keys.get(random.nextInt(keys.size()));
            keys.remove(randomKey);
            String randomQuery = Queries.HINT_QUERY_MAP.get(randomKey);
            Hint hint = executeQuery(randomQuery);
            if (hint != null && hint.info != null && isStringValid(hint.info) ) {
                hint.hintType = randomKey;
                hintList.add(hint);
            } else {
                i--;
            }
        }        
        return hintList;
    }

    public boolean isStringValid(String artistName){
        return artistName.matches("[a-zA-Z0-9 ]*");
    }

    public void startGame(User user) {
        this.state.setUser(user);
        Entity e;
        List<Hint> hintList = null;
        do {
            e = getEntity();
            if (e != null) {
                this.state.setEntity(e);
                hintList = getHintList();
            }
        } while (hintList == null || e == null || !isStringValid(e.name) || hintList.size() < state.getMaxNumOfHints());
        this.state.setEntity(e);
        this.state.setHintList(hintList);
    }

    public boolean validateUserGuess(String userGuess) {
        return state.validateUserGuess(userGuess);
    }

    public boolean checkUserGuess(String userGuess) {
        return state.checkUserGuess(userGuess);
    }

    public Hint getHint() {
        return state.getHint();
    }

    public boolean isReady() {
        return state.isReady();
    }

    public int getScore() { return  state.getScore(); }

    public int getNumRemainingHints() { return state.getNumRemainingHints();}

    public String getAnswer() {return state.getAnswer();}

    public void resetGame() {state.resetGame();}

    public User getUser() {return state.getUser();}

    public String getMaskedEntityName() {
        return state.getMaskedEntityName();
    }
}