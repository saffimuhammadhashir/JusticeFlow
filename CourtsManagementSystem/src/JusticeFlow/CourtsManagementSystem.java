package JusticeFlow;

import java.util.Scanner;

public class CourtsManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Instantiate the DatabaseHandler
        DatabaseHandler dbHandler = new DatabaseHandler();
        
        // Assuming Register() is a method to create a new user
        // dbHandler.Register(); 
        


        // Test login
        System.out.println("\nTesting login...");
        String isLoggedIn = dbHandler.Login();
        
        // Check if login is successful
        System.out.println("Login status: " + (!isLoggedIn.equals("None") ? "Success" : "Failure"));

        scanner.close();
    }
}
