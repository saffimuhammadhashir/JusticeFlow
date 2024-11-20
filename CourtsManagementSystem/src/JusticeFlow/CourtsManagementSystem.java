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
    List<Registrar> AllRegistrar = new ArrayList<>();
    List<ProbationOfficer> AllProbationOfficers = new ArrayList<>();
    List<Clients> AllClients = new ArrayList<>();
    List<Witness> AllWitnesses = new ArrayList<>();
    List<Slot> AllSlot = new ArrayList<>();
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
            dbHandler.getAllRegistrar(AllRegistrar);
            dbHandler.getAllClients(AllClients);
            dbHandler.getAllProbationOfficers(AllProbationOfficers);
            dbHandler.getAllWitnesses(AllWitnesses);
            dbHandler.getAllBarAssociations(AllBarAssociations);
            dbHandler.getAllNotifications(AllNotifications);
            dbHandler.getAllUserApplications(AllUserApplications);
            dbHandler.getAllFiles(AllCases);
            dbHandler.getAllSlots(AllSlot);
            dbHandler.getWitnessData(AllWitnesses);
            Slot.manageslot(AllSlot, dbHandler);

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

        // Print Defendants
        System.out.println("\nDefendants:");
        for (Clients client : AllClients) {
            System.out.println(client.toString());
        }

        // Print Bailiffs
        System.out.println("Registrar:");
        for (Registrar Registrar : AllRegistrar) {
            System.out.println(Registrar.toString());
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

        // Print Courts
        System.out.println("\nCourts:");
        for (Court court : AllCourts) {
            System.out.println(court.toString());
        }

        // Print Probation Officers
        System.out.println("\nProbation Officers:");
        for (ProbationOfficer officer : AllProbationOfficers) {
            System.out.println(officer.toString());
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

        // Print Notifications
        System.out.println("\n Slots:");
        for (Slot slots : AllSlot) {
            if (slots == null) {
                break;
            }
            System.out.println(slots.toString());
        }

        System.out.println("---------------------");
    }

    public void AddNewCase() {
        Lawyer lawyer = new Lawyer();
        lawyer = lawyer.getRelevantLawyer(AllLawyers, user);
        if (lawyer != null) {
            lawyer.FileNewCase(dbHandler, fileHandler, AllCases);
            System.out.println("Case has been successfully created.");
        } else {
            System.out.println("Lawyer Not Found!");
        }
    }

    public void ReviewUpcomingCaseRequests(Scanner scanner) {
        Registrar registrar = new Registrar();
        registrar = registrar.getRelevantRegistrar(AllRegistrar, user);
        if (registrar != null) {
            registrar.ReviewCaseRequest(dbHandler, fileHandler, AllCases, AllSlot, AllJudges, AllWitnesses, AllCourts,
                    scanner);
            System.out.println("Case has been successfully created.");

        } else {

            CourtAdministrator CourtAdmin = new CourtAdministrator();
            CourtAdmin = CourtAdmin.getRelevantCourtAdministrators(AllCourt_Administrators, user);
            if (CourtAdmin != null) {
                CourtAdmin.ReviewCaseRequest(dbHandler, fileHandler, AllCases, AllSlot, AllJudges, AllWitnesses,
                        AllCourts, scanner);
                System.out.println("Case has been successfully created.");
            } else {
                System.out.println("Invalid User!");
            }

        }
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
                    System.out.println("4. Schedule Hearing");
                    System.out.println("5. View IT System Maintenance Schedule");
                    System.out.println("6. Retrieve Record");
                    System.out.println("7. Display Notifications");
                    System.out.println("8. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Case Filing/Scheduling
                            System.out.println("Case Filing/Scheduling selected.");
                            ReviewUpcomingCaseRequests(this.scanner); // Calling method to add new case
                            break;
                        case 2:
                            // Track/Manage Updates
                            System.out.println("Track/Manage Updates selected.");
                            TrackManageUpdates(scanner); // Calling method to track/manage updates
                            break;
                        case 3:
                            // Track Case
                            System.out.println("Track Case selected.");
                            TrackCase(scanner); // Calling method to track a case
                            break;
                        case 4:
                            // Schedule Hearing/Witness
                            System.out.println("Schedule Hearing selected.");
                            ScheduleHearingWitness(scanner); // Calling method to schedule hearings or witnesses
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
                            System.out.println("Display Notifications");
                            viewMyNotifications();
                            break;
                        case 8:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }

                } else if ("IT Admin".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For IT Administrator -----");
                    System.out.println("1. Schedule IT System Maintenance");
                    System.out.println("2. Display Notifications");
                    System.out.println("3. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Schedule IT System Maintenance
                            System.out.println("Generate Report of Cases selected.");
                            CaseReport(scanner); // Calling method to schedule maintenance
                            break;
                        case 2:
                            System.out.println("Display Notifications");
                            viewMyNotifications();
                            break;

                        case 3:
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
                    System.out.println("3. Review Document/Log Judgment");
                    System.out.println("4. Display Notifications");
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
                            TrackCase(scanner); // Calling method to track a case
                            break;
                        case 3:
                            // Review Document/Log Judgment
                            System.out.println("Review Document/Log Judgment selected.");
                            ReviewDocumentLogJudgment(scanner); // Calling method to review documents and log judgments
                            break;
                        case 4:
                            System.out.println("Display Notifications");
                           viewMyNotifications();
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
                    System.out.println("2. Display Notifications");
                    System.out.println("3. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Review Document/Log Judgment
                            System.out.println("Review Document/Log Judgment selected.");
                            ReviewDocumentLogJudgment(scanner); // Calling method to review documents and log judgments
                            break;
                        case 2:
                            System.out.println("Display Notifications");
                           viewMyNotifications();
                           break;
                       case 3:
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
                    System.out.println("7. Add Witness to Case");
                    System.out.println("8. Display Notifications");
                    System.out.println("9. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Case Filing/Scheduling
                            System.out.println("Case Filing.");
                            AddNewCase(); // Calling method for case filing and scheduling
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
                            SubmitDocument(scanner); // Calling method to submit document
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
                            // Request to Retrieve Record
                            System.out.println("Add Witness to Case selected.");
                            ScheduleHearingWitness(scanner); // Calling method to add Witness
                            break;
                        case 8:
                            System.out.println("Display Notifications");
                           viewMyNotifications();
                           break;
                       case 9:
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
                    System.out.println("3. Display Notifications");
                    System.out.println("4. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Track Case
                            System.out.println("Track Case selected.");
                            TrackCase(scanner); // Calling method to track a case
                            break;
                        case 2:
                            // Submit Document
                            System.out.println("Submit Document selected.");
                            SubmitDocument(scanner); // Calling method to submit a document
                            break;
                        case 3:
                            System.out.println("Display Notifications");
                           viewMyNotifications();
                           break;
                        case 4:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }

                } else if ("Witness".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For Witness -----");
                    System.out.println("1. View Case Details");
                    System.out.println("2. Display Notifications");
                    System.out.println("3. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // View Case Details
                            System.out.println("View Case Details selected.");
                            ViewCaseDetails(scanner); // Calling method to view case details
                            break;
                        case 2:
                            System.out.println("Display Notifications");
                           viewMyNotifications();
                           break;
                       case 3:
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
                    System.out.println("3. Display Notifications");
                    System.out.println("4. Log Out");
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
                            System.out.println("Display Notifications");
                           viewMyNotifications();
                           break;
                        case 4:
                            // Log Out
                            System.out.println("Logging out...");
                            return; // Exit the menu and logout
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } else if ("Registrar".equalsIgnoreCase(role)) {
                    System.out.println("\n----- Main Menu For Registrar -----");
                    System.out.println("1. Case Filing/Scheduling");
                    System.out.println("2. Bar Registration");
                    System.out.println("3. Track Case");
                    System.out.println("4. Schedule Hearing");
                    System.out.println("5. Approve Document");
                    System.out.println("6. Approve Judgement");
                    System.out.println("7. Display Notifications");
                    System.out.println("8. Log Out");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after reading an integer

                    switch (choice) {
                        case 1:
                            // Case Filing/Scheduling
                            System.out.println("Case Filing/Scheduling selected.");
                            ReviewUpcomingCaseRequests(this.scanner); // Calling method for case filing/scheduling
                            break;
                        case 2:
                            // Track/Manage Updates
                            System.out.println("Bar Registration");
                            RegisterToBar(); // Calling method to monitor Bar Registration
                            break;
                        case 3:
                            // Track Case
                            System.out.println("Track Case selected.");
                            TrackCase(scanner); // Calling method to track a case
                            break;
                        case 4:
                            // Schedule Hearing/Witness
                            System.out.println("Schedule Hearing selected.");
                            ScheduleHearingWitness(scanner); // Calling method for scheduling hearing or witness
                            break;
                        case 5:
                            // View IT System Management Schedule
                            System.out.println("Approve Document selected.");
                            SubmitDocument(scanner); // Calling method to approve document
                            break;
                        case 6:
                            // Retrieve Record
                            System.out.println("Retrieve Record selected.");
                            ReviewDocumentLogJudgment(scanner); // Calling method to approve Judgement
                            break;
                        case 7:
                            viewMyNotifications();
                            break;
                        case 8:
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

    public void viewMyNotifications() {
        user.viewMyNotifications(AllNotifications, AllCases, dbHandler);
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
    public void ViewCaseDetails(Scanner scanner) {
        // Method to handle viewing case details
        Witness w = user.getRelevantWitness(AllWitnesses, user);
        // w.viewCases(scanner, AllCases, fileHandler);
        w.viewCases(scanner, AllCases, fileHandler);
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

    public void SubmitDocument(Scanner scanner) {
        // Method to handle document submission
        String role = user.getRole();

        if ("Lawyer".equalsIgnoreCase(role)) {
            Lawyer l = user.getRelevantLawyer(AllLawyers, user);
            l.SubmitDocument(scanner, AllCases, fileHandler);
        } else if ("Probation Officer".equalsIgnoreCase(role)) {
            ProbationOfficer p = user.getRelevantProbationOfficer(AllProbationOfficers, user);
            p.SubmitDocument(scanner, AllCases, fileHandler);
        } else if ("Registrar".equalsIgnoreCase(role)) {
            Registrar r = user.getRelevantRegistrar(AllRegistrar, user);
            r.ApproveDocument(scanner, AllCases, fileHandler);
        }
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

    public void ReviewDocumentLogJudgment(Scanner scanner) {
        // Method to handle reviewing documents or logging judgments
        String role = user.getRole();

        if ("Judge".equalsIgnoreCase(role)) {
            Judge jud = user.getRelevantJudge(AllJudges, user);
            jud.LogJudgement(scanner, AllCases, fileHandler);
        } else if ("Juror".equalsIgnoreCase(role)) {
            Juror jur = user.getRelevantJuror(AllJurors, user);
            jur.LogJudgement(scanner, AllCases, fileHandler);
        } else if ("Registrar".equalsIgnoreCase(role)) {
            Registrar r = user.getRelevantRegistrar(AllRegistrar, user);
            r.ApproveJudgement(scanner, AllCases, fileHandler);
        }
    }

    /////////////////////////////////////// IT
    /////////////////////////////////////// Administrator/////////////////////////////////////////

    public void CaseReport(Scanner scanner) {
        // Method to handle scheduling IT system maintenance

        ITAdmin i = new ITAdmin();
        // i.CaseReport(scanner, AllCases, fileHandler, dbHandler);
    }

    /////////////////////////////////////// Court
    /////////////////////////////////////// Administrator/////////////////////////////////////////
    public void TrackManageUpdates(Scanner scanner) {
        // Method to handle tracking and managing updates

        CourtAdministrator c = user.getRelevantCourtAdministrators(AllCourt_Administrators, user);
        if (c != null) {
            System.out.println("Implement tracking/managing updates logic here.");
            c.TrackAndManageUpdates(AllCases, AllSlot, AllJudges, AllLawyers, AllClients, AllNotifications, dbHandler,
                    scanner);
        } else {
            System.out.println("Invalid User!");
        }
    }

    public void TrackCase(Scanner scanner) {
        // Method to handle tracking a specific case
        System.out.println("Implement case tracking logic here.");
        Registrar registrar = new Registrar();
        registrar = registrar.getRelevantRegistrar(AllRegistrar, user);
        if (registrar != null) {
            registrar.UpdateCase(dbHandler, fileHandler, AllCases, AllSlot, AllJudges, AllWitnesses, AllCourts,
                    scanner);
            System.out.println("Case has been successfully updated.");

        } else {

            CourtAdministrator CourtAdmin = new CourtAdministrator();
            CourtAdmin = CourtAdmin.getRelevantCourtAdministrators(AllCourt_Administrators, user);
            if (CourtAdmin != null) {
                CourtAdmin.UpdateCase(dbHandler, fileHandler, AllCases, AllSlot, AllJudges, AllWitnesses,
                        AllCourts, scanner);
                System.out.println("Case has been successfully updated.");
            } else {
                System.out.println("Invalid User!");
            }

        }
    }

    public void ScheduleHearingWitness(Scanner scanner) {
        // Method to handle scheduling hearings or witnesses
        String role = user.getRole();

        if ("Court Administrator".equalsIgnoreCase(role)) {
            CourtAdministrator c = user.getRelevantCourtAdministrators(AllCourt_Administrators, user);
            c.scheduleHearing(scanner, AllCases, AllSlot, AllJudges, AllCourts, AllWitnesses, fileHandler, dbHandler);
        }
         else if ("Lawyer".equalsIgnoreCase(role)) {
            Lawyer l = user.getRelevantLawyer(AllLawyers, user);
            l.scheduleWitness(scanner, AllCases, AllSlot, AllJudges, AllCourts, AllWitnesses, fileHandler, dbHandler);
        }
        else if ("Registrar".equalsIgnoreCase(role)) {
            Registrar r = user.getRelevantRegistrar(AllRegistrar, user);
            r.scheduleHearing(scanner, AllCases, AllSlot, AllJudges, AllCourts, AllWitnesses, fileHandler, dbHandler);
        }
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

        system.printAllObjects();

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
