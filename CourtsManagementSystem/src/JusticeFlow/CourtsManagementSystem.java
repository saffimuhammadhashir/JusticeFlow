package JusticeFlow;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CourtsManagementSystem {

    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        DatabaseHandler dbHandler = new DatabaseHandler();
dbHandler.Register();
        // System.out.println("Welcome to the Courts Management System!");

        // Case hardcodedCase = new Case(
        //         19,
        //         "Hardcoded Case Title",
        //         "Civil",
        //         "Pending",
        //         new Date(),
        //         new Date(),
        //         1,
        //         2,
        //         new ArrayList<>());

        // dbHandler.saveOrUpdateCase(hardcodedCase);
        // hardcodedCase.updateCaseFiles();

        // System.out.println("Hardcoded case added to the database:");
        // System.out.println(hardcodedCase);

        // System.out.println("Thank you for using the Courts Management System.");
        // scanner.close();
    }
}
