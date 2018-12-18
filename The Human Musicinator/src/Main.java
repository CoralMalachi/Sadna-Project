import model.Model;
import util.User;

public class Main {
    public static void main(String[] args) {
        Model m = new Model();
        User u = new User();
        u.username = "Test";
        u.password = "Pass";
        if (m.registerUser(u)) {
            System.out.println("User registered.");
        } else {
            System.out.println("Username is already taken.");
        }
        User u2 = new User();
        u2.username = "Test2";
        u2.password = "Pass2";
        if (m.loginUser(u2)) {
            System.out.println("User logged in.");
        } else {
            System.out.println("Username or password are incorrect.");
        }        
    }
}
