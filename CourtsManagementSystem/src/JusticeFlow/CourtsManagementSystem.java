package JusticeFlow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.List;

public class CourtsManagementSystem {
    List<ITAdmin> AllITAdmins = new ArrayList<>();
    List<Judge> AllJudges = new ArrayList<>();
    List<Juror> AllJurors = new ArrayList<>();
    List<Lawyer> AllLawyers = new ArrayList<>();
    List<CourtAdministrator> AllCourt_Administrators = new ArrayList<>();
    List<Case> AllCases = new ArrayList<>();
    List<Plaintiff> AllPlaintiff = new ArrayList<>();
    List<Defendant> AllDefendant = new ArrayList<>();
    DatabaseHandler dbHandler = new DatabaseHandler();
    FileHandler fileHandler=new FileHandler(dbHandler);
    public void Register(){
        dbHandler.Register();
    }

    public void AddNewCase() {
        Scanner scanner = new Scanner(System.in);

        // Case Title
        System.out.print("Enter case title: ");
        String caseTitle = scanner.nextLine();
        if (caseTitle.isEmpty()) {
            caseTitle = "Default Case Title"; // default value if skipped
        }

        // Case Type
        System.out.print("Enter case type (Civil/Criminal/Family/Other) or press Enter to skip: ");
        String caseType = scanner.nextLine();
        if (caseType.isEmpty()) {
            caseType = "Civil"; // default value if skipped
        }

        // Case Status
        System.out.print("Enter case status (Pending/Closed/In Progress) or press Enter to skip: ");
        String caseStatus = scanner.nextLine();
        if (caseStatus.isEmpty()) {
            caseStatus = "Pending"; // default value if skipped
        }

        // Filing Date
        System.out.print("Enter filing date (yyyy-MM-dd) or press Enter to skip: ");
        String filingDateInput = scanner.nextLine();
        Date filingDate = null;
        if (!filingDateInput.isEmpty()) {
            try {
                filingDate = java.sql.Date.valueOf(filingDateInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Defaulting to current date.");
                filingDate = new Date(); // default to current date if invalid input
            }
        } else {
            filingDate = new Date(); // default to current date
        }

        // Court Date
        System.out.print("Enter court date (yyyy-MM-dd) or press Enter to skip: ");
        String courtDateInput = scanner.nextLine();
        Date courtDate = null;
        if (!courtDateInput.isEmpty()) {
            try {
                courtDate = java.sql.Date.valueOf(courtDateInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Defaulting to current date.");
                courtDate = new Date(); // default to current date if invalid input
            }
        } else {
            courtDate = new Date(); // default to current date
        }

        // Plaintiff ID
        System.out.print("Enter Plaintiff ID (or press Enter to skip): ");
        String plaintiffIDInput = scanner.nextLine();
        int plaintiffID = plaintiffIDInput.isEmpty() ? 0 : Integer.parseInt(plaintiffIDInput); // 0 for default value

        // Defendant ID
        System.out.print("Enter Defendant ID (or press Enter to skip): ");
        String defendantIDInput = scanner.nextLine();
        int defendantID = defendantIDInput.isEmpty() ? 0 : Integer.parseInt(defendantIDInput); // 0 for default value

        // Case Files (Optional - Skip if no files are to be added)
        

        // Create the new Case object
        Case newCase = new Case(
            0, // Assuming ID will be auto-generated
            caseTitle,
            caseType,
            caseStatus,
            filingDate,
            courtDate,
            plaintiffID,
            defendantID
        );
        newCase.updateCaseFiles(fileHandler,dbHandler);

        // Save or update the case in the database
        dbHandler.saveOrUpdateCase(newCase);



        // Confirmation
        System.out.println("Case has been successfully created.");
    }
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        CourtsManagementSystem system=new CourtsManagementSystem();
         system.Register();
        system.AddNewCase();
        system.dbHandler.Login();
    }
}
