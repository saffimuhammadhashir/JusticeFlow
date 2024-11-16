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
    List<Bailiff> AllBailiffs = new ArrayList<>();
    List<Clerk> AllClerks = new ArrayList<>();
    List<CourtReporter> AllCourtReporters = new ArrayList<>();
    List<ProbationOfficer> AllProbationOfficers = new ArrayList<>();
    List<Prosecutor> AllProsecutors = new ArrayList<>();
    List<Plaintiff> AllPlaintiff = new ArrayList<>();
    List<Defendant> AllDefendant = new ArrayList<>();
    List<Witness> AllWitnesses = new ArrayList<>();
    List<BarAssociation> AllBarAssociations = new ArrayList<>();
    List<Court> AllCourts = new ArrayList<>();
    List<UserApplication> AllUserApplications = new ArrayList<>();
    List<Notification> AllNotifications = new ArrayList<>();
    DatabaseHandler dbHandler = new DatabaseHandler();
    FileHandler fileHandler = new FileHandler(dbHandler);

    private Integer loggedinID = null;

    public void Register() {
        dbHandler.Register();
    }

    CourtsManagementSystem() {
        loadData();
    }

    public void loadData() {
        if (dbHandler != null) {
            dbHandler.getAllCourts(AllCourts);
            dbHandler.getAllCases(AllCases);
            dbHandler.getAllJudges(AllJudges);
            dbHandler.getAllLawyers(AllLawyers);
            dbHandler.getAllCourtAdministrators(AllCourt_Administrators);
            dbHandler.getAllJurors(AllJurors);
            dbHandler.getAllBailiffs(AllBailiffs);
            dbHandler.getAllClerks(AllClerks);
            dbHandler.getAllCourtReporters(AllCourtReporters);
            dbHandler.getAllDefendants(AllDefendant);
            dbHandler.getAllPlaintiffs(AllPlaintiff);
            dbHandler.getAllProbationOfficers(AllProbationOfficers);
            dbHandler.getAllProsecutors(AllProsecutors);
            dbHandler.getAllWitnesses(AllWitnesses);
            dbHandler.getAllBarAssociations(AllBarAssociations);
            dbHandler.getAllNotifications(AllNotifications);
            dbHandler.getAllUserApplications(AllUserApplications);

        } else {
            System.out.println("DatabaseHandler is not initialized!");
        }
    }

    public void printAllObjects() {
        System.out.println("----- All Users -----");

        // Print IT Admins
        System.out.println("IT Admins:");
        for (ITAdmin admin : AllITAdmins) {
            System.out.println(admin.toString());
        }

        // Print Judges
        System.out.println("\nJudges:");
        for (Judge judge : AllJudges) {
            System.out.println(judge.toString());
        }

        // Print Jurors
        System.out.println("\nJurors:");
        for (Juror juror : AllJurors) {
            System.out.println(juror.toString());
        }

        // Print Lawyers
        System.out.println("\nLawyers:");
        for (Lawyer lawyer : AllLawyers) {
            System.out.println(lawyer.toString());
        }

        // Print Court Administrators
        System.out.println("\nCourt Administrators:");
        for (CourtAdministrator admin : AllCourt_Administrators) {
            System.out.println(admin.toString());
        }

        // Print Plaintiffs
        System.out.println("\nPlaintiffs:");
        for (Plaintiff plaintiff : AllPlaintiff) {
            System.out.println(plaintiff.toString());
        }

        // Print Defendants
        System.out.println("\nDefendants:");
        for (Defendant defendant : AllDefendant) {
            System.out.println(defendant.toString());
        }

        // Print Bailiffs
        System.out.println("\nBailiffs:");
        for (Bailiff bailiff : AllBailiffs) {
            System.out.println(bailiff.toString());
        }

        // Print Bar Associations
        System.out.println("\nBar Associations:");
        for (BarAssociation barAssociation : AllBarAssociations) {
            System.out.println(barAssociation.toString());
        }

        // Print Cases
        System.out.println("\nCases:");
        for (Case caseObj : AllCases) {
            System.out.println(caseObj.toString());
        }

        // Print Clerks
        System.out.println("\nClerks:");
        for (Clerk clerk : AllClerks) {
            System.out.println(clerk.toString());
        }

        // Print Courts
        System.out.println("\nCourts:");
        for (Court court : AllCourts) {
            System.out.println(court.toString());
        }

        // Print Court Reporters
        System.out.println("\nCourt Reporters:");
        for (CourtReporter reporter : AllCourtReporters) {
            System.out.println(reporter.toString());
        }

        // Print Probation Officers
        System.out.println("\nProbation Officers:");
        for (ProbationOfficer officer : AllProbationOfficers) {
            System.out.println(officer.toString());
        }

        // Print Prosecutors
        System.out.println("\nProsecutors:");
        for (Prosecutor prosecutor : AllProsecutors) {
            System.out.println(prosecutor.toString());
        }

        // Print User Applications
        System.out.println("\nUser Applications:");
        for (UserApplication userApp : AllUserApplications) {
            System.out.println(userApp.toString());
        }

        // Print Witnesses
        System.out.println("\nWitnesses:");
        for (Witness witness : AllWitnesses) {
            System.out.println(witness.toString());
        }

        // Print Notifications
        System.out.println("\nNotifications:");
        for (Notification notification : AllNotifications) {
            System.out.println(notification.toString());
        }

        System.out.println("---------------------");
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
                defendantID);
        newCase.updateCaseFiles(fileHandler, dbHandler);

        // Save or update the case in the database
        dbHandler.saveOrUpdateCase(newCase);
        AllCases.add(newCase);
        // Confirmation
        System.out.println("Case has been successfully created.");
    }

    // public static void main(String[] args) {
    // // Scanner scanner = new Scanner(System.in);
    // CourtsManagementSystem system = new CourtsManagementSystem();
    // system.Register();
    // // system.AddNewCase();
    // system.dbHandler.Login();
    // }

    // Main menu displayed after successful login
    // Main menu displayed after successful login
    private void showMenu(Scanner scanner,User user) {
        while (true && user.isActivate()) {
            System.out.println("\n----- Main Menu -----");
            System.out.println("1. Add New Case");
            System.out.println("2. View All Cases");
            System.out.println("3. Log Out");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after reading an integer

            switch (choice) {
                case 1:
                    // Add new case functionality
                    System.out.println("Adding new case...");
                    AddNewCase(); // Placeholder method for adding a case
                    break;
                case 2: 
                    // View all cases functionality
                    System.out.println("Viewing all Objects...");
                    printAllObjects(); // Placeholder method for viewing all cases
                    break;
                case 3:
                    System.out.println("Logging out...");
                    loggedinID = null; // Reset user session ID on logout
                    return; // Exit from the main menu (logout)
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void Login(Scanner scanner) {
        loggedinID = dbHandler.Login(scanner); // Assuming this retrieves the user ID after login
        System.out.println("Login status: " + loggedinID);
        if (loggedinID != null) {
            User user=dbHandler.getUserById(loggedinID);
            System.out.println("Login Successful.");
            showMenu(scanner,user); // Show the main menu after login
        } else {
            System.out.println("Login failed.");
        }
    }

    public static void main(String[] args) {
        CourtsManagementSystem system = new CourtsManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----- Welcome to Courts Management System -----");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline after reading an integer

            switch (choice) {
                case 1:
                    system.Login(scanner);
                    return; // Exit after login attempt
                case 2:
                    system.Register();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    scanner.close(); // Close scanner before exiting
                    return; // Exit the program
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
