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
    private User user;
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

    public void Login(Scanner scanner) {
        loggedinID = dbHandler.Login(scanner); // Assuming this retrieves the user ID after login
        System.out.println("Login status: " + loggedinID);
        if (loggedinID != null) {
            user = dbHandler.getUserById(loggedinID);
            System.out.println("Login Successful.");
            CLI_Menu loggedin = new CLI_Menu(user, scanner); // CLI Menu

        } else {
            System.out.println("Login failed.");
        }
    }

    private class CLI_Menu {
        private User user = null;
        Scanner scanner = null;

        CLI_Menu(User user, Scanner scanner) {
            this.user = user;
            this.scanner = scanner;
            startmenu();
        }

        void startmenu() {
            String role = user.getRole();
            while (true && user.isActivate()) {

                if ("Court Administrator".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For Court Administrator -----");
                    System.out.println("1. Case Filing/Scheduling");
                    System.out.println("2. Track/Manage Updates");
                    System.out.println("3. Track Case");
                    System.out.println("4. Schedule Hearing/Witness");
                    System.out.println("5. View IT System Maintenance Schedule");
                    System.out.println("6. Retrieve Record");
                    System.out.println("7. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Case Filing/Scheduling
                            System.out.println("Case Filing/Scheduling selected.");
                            AddNewCase(); // Calling method to add new case
                            break;
                        case 2:
                            // Track/Manage Updates
                            System.out.println("Track/Manage Updates selected.");
                            TrackManageUpdates(); // Calling method to track/manage updates
                            break;
                        case 3:
                            // Track Case
                            System.out.println("Track Case selected.");
                            TrackCase(); // Calling method to track a case
                            break;
                        case 4:
                            // Schedule Hearing/Witness
                            System.out.println("Schedule Hearing/Witness selected.");
                            ScheduleHearingWitness(); // Calling method to schedule hearings or witnesses
                            break;
                        case 5:
                            // View IT System Maintenance Schedule
                            System.out.println("View IT System Maintenance Schedule selected.");
                            ViewITMaintenanceSchedule(); // Calling method to view the IT system maintenance schedule
                            break;
                        case 6:
                            // Retrieve Record
                            System.out.println("Retrieve Record selected.");
                            RetrieveRecord(); // Calling method to retrieve records
                            break;
                        case 7:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }

                } else if ("IT Admin".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For IT Administrator -----");
                    System.out.println("1. Schedule IT System Maintenance");
                    System.out.println("2. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Schedule IT System Maintenance
                            System.out.println("Schedule IT System Maintenance selected.");
                            ScheduleITSystemMaintenance(); // Calling method to schedule maintenance
                            break;
                        case 2:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } else if ("Judge".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For Judge -----");
                    System.out.println("1. Track Updates");
                    System.out.println("2. Track Case");
                    System.out.println("3. Scheduling Hearing/Witness");
                    System.out.println("4. Review Document/Log Judgment");
                    System.out.println("5. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Track Updates
                            System.out.println("Track Updates selected.");
                            TrackUpdates(); // Calling method to track updates
                            break;
                        case 2:
                            // Track Case
                            System.out.println("Track Case selected.");
                            TrackCase(); // Calling method to track a case
                            break;
                        case 3:
                            // Scheduling Hearing/Witness
                            System.out.println("Scheduling Hearing/Witness selected.");
                            ScheduleHearingWitness(); // Calling method to schedule hearing or witnesses
                            break;
                        case 4:
                            // Review Document/Log Judgment
                            System.out.println("Review Document/Log Judgment selected.");
                            ReviewDocumentLogJudgment(); // Calling method to review documents and log judgments
                            break;
                        case 5:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }

                } else if ("Juror".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For Juror -----");
                    System.out.println("1. Review Document/Log Judgment");
                    System.out.println("2. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Review Document/Log Judgment
                            System.out.println("Review Document/Log Judgment selected.");
                            ReviewDocumentLogJudgment(); // Calling method to review documents and log judgments
                            break;
                        case 2:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }

                } else if ("Lawyer".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For Lawyer -----");
                    System.out.println("1. Case Filing/Scheduling");
                    System.out.println("2. Track Updates");
                    System.out.println("3. Register to Bar");
                    System.out.println("4. Submit Document");
                    System.out.println("5. Re-open Case/Appeal");
                    System.out.println("6. Request to Retrieve Record");
                    System.out.println("7. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Case Filing/Scheduling
                            System.out.println("Case Filing/Scheduling selected.");
                            CaseFilingScheduling(); // Calling method for case filing and scheduling
                            break;
                        case 2:
                            // Track Updates
                            System.out.println("Track Updates selected.");
                            TrackUpdates(); // Calling method to track updates
                            break;
                        case 3:
                            // Register to Bar
                            System.out.println("Register to Bar selected.");
                            RegisterToBar(); // Calling method to register to the bar
                            break;
                        case 4:
                            // Submit Document
                            System.out.println("Submit Document selected.");
                            SubmitDocument(); // Calling method to submit document
                            break;
                        case 5:
                            // Re-open Case/Appeal
                            System.out.println("Re-open Case/Appeal selected.");
                            ReOpenCaseOrAppeal(); // Calling method to reopen case or appeal
                            break;
                        case 6:
                            // Request to Retrieve Record
                            System.out.println("Request to Retrieve Record selected.");
                            RequestToRetrieveRecord(); // Calling method to request record retrieval
                            break;
                        case 7:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }

                } else if ("Probation Officer".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For Probation Officer -----");
                    System.out.println("1. Track Case");
                    System.out.println("2. Submit Document");
                    System.out.println("3. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Track Case
                            System.out.println("Track Case selected.");
                            TrackCase(); // Calling method to track a case
                            break;
                        case 2:
                            // Submit Document
                            System.out.println("Submit Document selected.");
                            SubmitDocument(); // Calling method to submit a document
                            break;
                        case 3:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }

                } else if ("Witness".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For Witness -----");
                    System.out.println("1. View Case Details");
                    System.out.println("2. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // View Case Details
                            System.out.println("View Case Details selected.");
                            ViewCaseDetails(); // Calling method to view case details
                            break;
                        case 2:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } else if ("Client".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For Client -----");
                    System.out.println("1. Track Updates");
                    System.out.println("2. Request for Case Re-Opening/Appeal");
                    System.out.println("3. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Track Updates
                            System.out.println("Track Updates selected.");
                            TrackUpdates(); // Calling method to track updates
                            break;
                        case 2:
                            // Request for Case Re-Opening/Appeal
                            System.out.println("Request for Case Re-Opening/Appeal selected.");
                            RequestCaseReopeningOrAppeal(); // Calling method for case reopening/appeal
                            break;
                        case 3:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } else if ("Registrar".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For Registrar -----");
                    System.out.println("1. Case Filing/Scheduling");
                    System.out.println("2. Track/Manage Updates");
                    System.out.println("3. Track Case");
                    System.out.println("4. Schedule Hearing/Witness");
                    System.out.println("5. View IT System Management Schedule");
                    System.out.println("6. Retrieve Record");
                    System.out.println("7. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Case Filing/Scheduling
                            System.out.println("Case Filing/Scheduling selected.");
                            CaseFilingScheduling(); // Calling method for case filing/scheduling
                            break;
                        case 2:
                            // Track/Manage Updates
                            System.out.println("Track/Manage Updates selected.");
                            TrackManageUpdates(); // Calling method to track/manage updates
                            break;
                        case 3:
                            // Track Case
                            System.out.println("Track Case selected.");
                            TrackCase(); // Calling method to track a case
                            break;
                        case 4:
                            // Schedule Hearing/Witness
                            System.out.println("Schedule Hearing/Witness selected.");
                            ScheduleHearingWitness(); // Calling method for scheduling hearing or witness
                            break;
                        case 5:
                            // View IT System Management Schedule
                            System.out.println("View IT System Management Schedule selected.");
                            ViewITSystemManagementSchedule(); // Calling method to view IT system management schedule
                            break;
                        case 6:
                            // Retrieve Record
                            System.out.println("Retrieve Record selected.");
                            RetrieveRecord(); // Calling method to retrieve a record
                            break;
                        case 7:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }

            }
        }

    }

    /////////////////////////////////////// Registrar
    /////////////////////////////////////// ////////////////////////////////////////////////////////////////////////////////

    public void ViewITSystemManagementSchedule() {
        // Method to view IT system management schedule
        System.out.println("Implement logic to view IT system management schedule here.");
    }

    /////////////////////////////////////// Client
    /////////////////////////////////////// ////////////////////////////////////////////////////////////////////////////////

    public void RequestCaseReopeningOrAppeal() {
        // Method to handle case reopening or appeal request
        System.out.println("Implement logic for requesting case reopening/appeal here.");
    }

    /////////////////////////////////////// Witness
    /////////////////////////////////////// ////////////////////////////////////////////////////////////////////////////////

    // Sample method prototypes for Witness menu options
    public void ViewCaseDetails() {
        // Method to handle viewing case details
        System.out.println("Implement logic to view case details here.");
    }

    /////////////////////////////////////// Lawyer
    /////////////////////////////////////// ////////////////////////////////////////////////////////////////////////////////

    public void CaseFilingScheduling() {
        // Method to handle case filing and scheduling
        System.out.println("Implement case filing and scheduling logic here.");
    }

    public void RegisterToBar() {
        // Method to handle lawyer's registration to the bar
        System.out.println("Implement lawyer registration to bar logic here.");
    }

    public void SubmitDocument() {
        // Method to handle document submission
        System.out.println("Implement document submission logic here.");
    }

    public void ReOpenCaseOrAppeal() {
        // Method to handle re-opening a case or appeal
        System.out.println("Implement case reopening/appeal logic here.");
    }

    public void RequestToRetrieveRecord() {
        // Method to handle requesting to retrieve record
        System.out.println("Implement request to retrieve record logic here.");
    }

    /////////////////////////////////////// Judge
    /////////////////////////////////////// ////////////////////////////////////////////////////////////////////////////////
    public void TrackUpdates() {
        // Method to handle tracking updates
        System.out.println("Implement tracking updates logic here.");
    }

    public void ReviewDocumentLogJudgment() {
        // Method to handle reviewing documents or logging judgments
        System.out.println("Implement reviewing documents/logging judgment logic here.");
    }

    /////////////////////////////////////// IT
    /////////////////////////////////////// Administrator/////////////////////////////////////////

    public void ScheduleITSystemMaintenance() {
        // Method to handle scheduling IT system maintenance
        System.out.println("Implement scheduling of IT system maintenance here.");
    }

    /////////////////////////////////////// Court
    /////////////////////////////////////// Administrator/////////////////////////////////////////
    public void TrackManageUpdates() {
        // Method to handle tracking and managing updates
        System.out.println("Implement tracking/managing updates logic here.");
    }

    public void TrackCase() {
        // Method to handle tracking a specific case
        System.out.println("Implement case tracking logic here.");
    }

    public void ScheduleHearingWitness() {
        // Method to handle scheduling hearings or witnesses
        System.out.println("Implement hearing/witness scheduling logic here.");
    }

    public void ViewITMaintenanceSchedule() {
        // Method to view the IT system maintenance schedule
        System.out.println("Implement viewing IT system maintenance schedule here.");
    }

    public void RetrieveRecord() {
        // Method to retrieve case or user records
        System.out.println("Implement record retrieval logic here.");
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
