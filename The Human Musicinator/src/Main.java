//import model.Model;
//import util.DifficultyConstants;
//import util.Record;
//import util.User;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        Model m = new Model();
//        User u = new User();
//        u.username = "Test";
//        u.password = "Pass";
//        if (m.registerUser(u)) {
//            System.out.println("User registered.");
//        } else {
//            System.out.println("Username is already taken.");
//        }
//        User u2 = new User();
//        u2.username = "Test2";
//        u2.password = "Pass2";
//        if (m.loginUser(u2)) {
//            System.out.println("User logged in.");
//        } else {
//            System.out.println("Username or password are incorrect.");
//        }
//        // Record r = new Record();
//        // r.username = "Test";
//        // r.score = 9000;
//        // r.difficulty = DifficultyConstants.HARD;
//        // if(m.insertIntoRecordsTable(r)) {
//        //     System.out.println("Record inserted.");
//        // }
//
//        // r.username = "Test2";
//        // r.score = 5000;
//        // r.difficulty = DifficultyConstants.HARD;
//        // if(m.insertIntoRecordsTable(r)) {
//        //     System.out.println("Record inserted.");
//        // }
//
//        // r.username = "Test3";
//        // r.score = 12000;
//        // r.difficulty = DifficultyConstants.HARD;
//        // if(m.insertIntoRecordsTable(r)) {
//        //     System.out.println("Record inserted.");
//        // }
//
//        // r.username = "Test4";
//        // r.score = 8500;
//        // r.difficulty = DifficultyConstants.HARD;
//        // if(m.insertIntoRecordsTable(r)) {
//        //     System.out.println("Record inserted.");
//        // }
//        List<Record> scores = m.getRecords(DifficultyConstants.HARD);
//        for (Record score : scores) {
//            System.out.println(score.username + "   " + score.score);
//        }
//    }
//}
