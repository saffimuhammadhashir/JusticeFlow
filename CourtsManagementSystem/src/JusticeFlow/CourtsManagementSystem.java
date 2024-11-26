package JusticeFlow;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.apache.pdfbox.debugger.ui.ArrayEntry;

import java.util.List;

public class CourtsManagementSystem extends Application {

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
    List<BarApplication> AllBarApplications = new ArrayList();
    DatabaseHandler dbHandler = new DatabaseHandler();
    FileHandler fileHandler = new FileHandler(dbHandler);
    public User user;
    private Integer loggedinID = null;

    public void CLI_Register() {
        dbHandler.Register();
    }

    // CourtsManagementSystem() {
    // loadData();
    // }

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
            dbHandler.getAllBarApplications(AllBarApplications);
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
        System.out.println("\\Bar Applications:");
        for (BarApplication Barapp : AllBarApplications) {
            System.out.println(Barapp.toString());
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

    public void AddNewCase(Stage primaryStage, GUI_Menu gui) {
        Lawyer lawyer = new Lawyer();
        lawyer = lawyer.getRelevantLawyer(AllLawyers, user);
        if (lawyer != null) {
            lawyer.FileNewCase(dbHandler, fileHandler, AllCases, primaryStage, gui);
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

    public void ReviewUpcomingCaseRequests(Stage primaryStage, GUI_Menu gui) {
        Registrar registrar = new Registrar();
        registrar = registrar.getRelevantRegistrar(AllRegistrar, user);
        if (registrar != null) {
            registrar.ReviewCaseRequest(dbHandler, fileHandler, AllCases, AllSlot, AllJudges, AllWitnesses, AllCourts,
                    primaryStage, gui, this);
            System.out.println("Case has been successfully created.");

        } else {

            CourtAdministrator CourtAdmin = new CourtAdministrator();
            CourtAdmin = CourtAdmin.getRelevantCourtAdministrators(AllCourt_Administrators, user);
            if (CourtAdmin != null) {
                CourtAdmin.ReviewCaseRequest(dbHandler, fileHandler, AllCases, AllSlot, AllJudges, AllWitnesses,
                        AllCourts,
                        primaryStage, gui, this);
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

    public void viewMyNotifications() {
        user.viewMyNotifications(AllNotifications, AllCases, dbHandler);
    }

    public void viewMyNotifications(Stage primaryStage, GUI_Menu gui) {
        user.viewMyNotifications(AllNotifications, AllCases, dbHandler, primaryStage, gui);
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

    public void ViewCaseDetails(Scanner scanner, Stage primaryStage) {
        // Method to handle viewing case details
        Witness w = user.getRelevantWitness(AllWitnesses, user);
        // w.viewCases(scanner, AllCases, fileHandler);
        if (w != null) {
            w.viewCases(scanner, AllCases, fileHandler, dbHandler, primaryStage, primaryStage.getScene());
        }
    }

    /////////////////////////////////////// Lawyer
    /////////////////////////////////////// ////////////////////////////////////////////////////////////////////////////////

    public void CaseFilingScheduling() {
        // Method to handle case filing and scheduling
        System.out.println("Implement case filing and scheduling logic here.");

    }

    public void RegisterToBar(Scanner scanner) {
        // Method to handle lawyer's registration to the bar
        System.out.println("Implement lawyer registration to bar logic here.");
        String role = user.getRole();
        if ("Lawyer".equalsIgnoreCase(role)) {
            Lawyer l = user.getRelevantLawyer(AllLawyers, user);
            if (l != null) {
                l.RegistertoBar(AllBarAssociations, AllBarApplications, dbHandler, scanner);
            } else {
                System.out.println("Invalid User!");
            }

        } else if ("Registrar".equalsIgnoreCase(role)) {
            Registrar r = user.getRelevantRegistrar(AllRegistrar, user);
            if (r != null) {
                r.RegistertoBar(AllBarAssociations, AllBarApplications, dbHandler, scanner);
            } else {
                System.out.println("Invalid Userr!");
            }
        }

    }

    public void RegisterToBar(Stage primaryStage, GUI_Menu gui) {
        // Method to handle lawyer's registration to the bar
        System.out.println("Implement lawyer registration to bar logic here.");
        String role = user.getRole();
        if ("Lawyer".equalsIgnoreCase(role)) {
            Lawyer l = user.getRelevantLawyer(AllLawyers, user);
            if (l != null) {
                l.RegistertoBar(AllBarAssociations, AllBarApplications, dbHandler, primaryStage, gui);
            } else {
                System.out.println("Invalid User!");
            }

        } else if ("Registrar".equalsIgnoreCase(role)) {
            Registrar r = user.getRelevantRegistrar(AllRegistrar, user);
            if (r != null) {
                r.RegistertoBar(AllBarAssociations, AllBarApplications, dbHandler, primaryStage, gui,this);
            } else {
                System.out.println("Invalid Userr!");
            }
        }

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

    public void SubmitDocument(Scanner scanner, Stage primaryStage) {
        // Method to handle document submission
        String role = user.getRole();

        if ("Lawyer".equalsIgnoreCase(role)) {
            Lawyer l = user.getRelevantLawyer(AllLawyers, user);
            l.SubmitDocument(scanner, AllCases, fileHandler, primaryStage, primaryStage.getScene());
        } else if ("Probation Officer".equalsIgnoreCase(role)) {
            ProbationOfficer p = user.getRelevantProbationOfficer(AllProbationOfficers, user);
            p.SubmitDocument(scanner, AllCases, fileHandler, primaryStage, primaryStage.getScene());
        } else if ("Registrar".equalsIgnoreCase(role)) {
            Registrar r = user.getRelevantRegistrar(AllRegistrar, user);
            r.ApproveDocument(dbHandler, scanner, AllCases, fileHandler, primaryStage, primaryStage.getScene());
        }
    }

    public void ReOpenCaseOrAppeal(Stage primaryStage) {
        // Method to handle re-opening a case or appeal
        System.out.println("Implement Re-Open Case logic here.");
        String role = user.getRole();

        if ("Lawyer".equalsIgnoreCase(role)) {
            Lawyer l = user.getRelevantLawyer(AllLawyers, user);
            l.Re_OpenCase(AllCases, fileHandler, dbHandler, primaryStage, primaryStage.getScene());
        } else if ("Court Administrator".equalsIgnoreCase(role)) {
            CourtAdministrator c = user.getRelevantCourtAdministrators(AllCourt_Administrators, user);
            c.Re_OpenCase(AllCases, fileHandler, dbHandler, primaryStage, primaryStage.getScene());
        }
    }

    public void closeCase(Stage primaryStage) {
        // Method to handle requesting to retrieve record
        System.out.println("Implement Close Case logic here.");
        String role = user.getRole();

        if ("Court Administrator".equalsIgnoreCase(role)) {
            CourtAdministrator c = user.getRelevantCourtAdministrators(AllCourt_Administrators, user);
            c.closeCase(AllCases, fileHandler, dbHandler, primaryStage, primaryStage.getScene());
        }
    }

    /////////////////////////////////////// Judge
    /////////////////////////////////////// ////////////////////////////////////////////////////////////////////////////////
    public void TrackUpdates(Stage primaryStage, GUI_Menu gui) {
        // Method to handle tracking updates
        String role = user.getRole();
        System.out.println("Implement tracking updates logic here.");
        if ("Judge".equalsIgnoreCase(role)) {
            Judge jud = user.getRelevantJudge(AllJudges, user);
            if (jud != null) {
                jud.TrackUpdates(dbHandler, fileHandler, AllCases, AllSlot, AllClients, AllJudges, AllLawyers,
                        AllWitnesses, AllCourts, primaryStage, gui, this);
            }
        }
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

    public void ReviewDocumentLogJudgment(Scanner scanner, Stage primaryStage) {
        // Method to handle reviewing documents or logging judgments
        String role = user.getRole();

        if ("Judge".equalsIgnoreCase(role)) {
            Judge jud = user.getRelevantJudge(AllJudges, user);
            jud.LogJudgement(scanner, AllCases, AllSlot, fileHandler, primaryStage, primaryStage.getScene());
        } else if ("Juror".equalsIgnoreCase(role)) {
            Juror jur = user.getRelevantJuror(AllJurors, user);
            jur.LogJudgement(scanner, AllCases, AllSlot, fileHandler, primaryStage, primaryStage.getScene());
        } else if ("Registrar".equalsIgnoreCase(role)) {
            Registrar r = user.getRelevantRegistrar(AllRegistrar, user);
            r.ApproveJudgement(dbHandler, scanner, AllCases, fileHandler, primaryStage, primaryStage.getScene());
        }
    }

    /////////////////////////////////////// IT
    /////////////////////////////////////// Administrator/////////////////////////////////////////

    public void CaseReport(Scanner scanner) {
        // Method to handle scheduling IT system maintenance

        ITAdmin i = new ITAdmin();
        // i.CaseReport(AllCases, AllSlot,stage,scanner);
    }

    public void displayCaseFiles(Stage stage, GUI_Menu gui, Scanner scanner) {
        String hardcodedPath = "report/case_report"; // Replace with your hardcoded path
        File folder = new File(hardcodedPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid path or directory does not exist.");
            return;
        }

        // Root layout
        BorderPane root = new BorderPane();
        root.setPrefSize(1000, 600);
        root.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 20;");

        // Title at the top
        Label title = new Label("Cases Report Viewer");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #333;");
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setTop(title);

        // GridPane to hold file details
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(15);
        gridPane.setPadding(new Insets(20));
        gridPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dddddd; -fx-border-radius: 8px; "
                + "-fx-padding: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0.5, 0, 2);");

        int row = 0;
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                try {
                    // Get file creation time
                    BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    String creationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new Date(attributes.creationTime().toMillis()));

                    // File name and creation time label
                    Label fileLabel = new Label(file.getName() + " | Created: " + creationTime);
                    fileLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");

                    // Open button
                    Button openButton = new Button("Open");
                    openButton.setStyle("-fx-font-size: 16px; -fx-background-color: #4caf50; -fx-text-fill: white; "
                            + "-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                    openButton.setOnMouseEntered(e -> openButton.setStyle(
                            "-fx-font-size: 16px; -fx-background-color: #45a049; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
                    openButton.setOnMouseExited(e -> openButton.setStyle(
                            "-fx-font-size: 16px; -fx-background-color: #4caf50; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
                    openButton.setOnAction(event -> {
                        try {
                            // Open the file
                            new ProcessBuilder("explorer", file.getAbsolutePath()).start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    // Add elements to the grid
                    gridPane.add(fileLabel, 0, row);
                    gridPane.add(openButton, 1, row);

                    row++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // ScrollPane to handle large data
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #f5f5f5; -fx-border-color: transparent;");
        root.setCenter(scrollPane);

        // Bottom buttons
        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-font-size: 16px; -fx-background-color: #e53935; -fx-text-fill: white; "
                + "-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        returnButton.setOnMouseEntered(e -> returnButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #d32f2f; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        returnButton.setOnMouseExited(e -> returnButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #e53935; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        returnButton.setOnAction(event -> gui.GUI_startmenu(stage));

        Button newReportButton = new Button("New Report");
        newReportButton.setStyle("-fx-font-size: 16px; -fx-background-color: #1e88e5; -fx-text-fill: white; "
                + "-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        newReportButton.setOnMouseEntered(e -> newReportButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #1976d2; -fx-text-fill: white; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        newReportButton.setOnMouseExited(e -> newReportButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #1e88e5; -fx-text-fill: white; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        newReportButton.setOnAction(event -> CaseReport(scanner, stage, gui));

        HBox bottomBox = new HBox(15, returnButton, newReportButton);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(15));
        bottomBox.setStyle("-fx-background-color: #e8e8e8; -fx-border-color: #dddddd; -fx-border-radius: 8px;");

        root.setBottom(bottomBox);

        // Set the scene
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Case Files Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public void displayLawyerFiles(Stage stage, GUI_Menu gui, Scanner scanner) {
        String hardcodedPath = "report/Lawyer_report"; // Replace with your hardcoded path
        File folder = new File(hardcodedPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid path or directory does not exist.");
            return;
        }

        // Root layout
        BorderPane root = new BorderPane();
        root.setPrefSize(1000, 600);
        root.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 20;");

        // Title at the top
        Label title = new Label("Lawyers Report Viewer");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #333;");
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setTop(title);

        // GridPane to hold file details
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(15);
        gridPane.setPadding(new Insets(20));
        gridPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dddddd; -fx-border-radius: 8px; "
                + "-fx-padding: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0.5, 0, 2);");

        int row = 0;
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                try {
                    // Get file creation time
                    BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    String creationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new Date(attributes.creationTime().toMillis()));

                    // File name and creation time label
                    Label fileLabel = new Label(file.getName() + " | Created: " + creationTime);
                    fileLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");

                    // Open button
                    Button openButton = new Button("Open");
                    openButton.setStyle("-fx-font-size: 16px; -fx-background-color: #4caf50; -fx-text-fill: white; "
                            + "-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                    openButton.setOnMouseEntered(e -> openButton.setStyle(
                            "-fx-font-size: 16px; -fx-background-color: #45a049; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
                    openButton.setOnMouseExited(e -> openButton.setStyle(
                            "-fx-font-size: 16px; -fx-background-color: #4caf50; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
                    openButton.setOnAction(event -> {
                        try {
                            // Open the file
                            new ProcessBuilder("explorer", file.getAbsolutePath()).start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    // Add elements to the grid
                    gridPane.add(fileLabel, 0, row);
                    gridPane.add(openButton, 1, row);

                    row++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // ScrollPane to handle large data
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #f5f5f5; -fx-border-color: transparent;");
        root.setCenter(scrollPane);

        // Bottom buttons
        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-font-size: 16px; -fx-background-color: #e53935; -fx-text-fill: white; "
                + "-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        returnButton.setOnMouseEntered(e -> returnButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #d32f2f; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        returnButton.setOnMouseExited(e -> returnButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #e53935; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        returnButton.setOnAction(event -> gui.GUI_startmenu(stage));

        Button newReportButton = new Button("New Report");
        newReportButton.setStyle("-fx-font-size: 16px; -fx-background-color: #1e88e5; -fx-text-fill: white; "
                + "-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        newReportButton.setOnMouseEntered(e -> newReportButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #1976d2; -fx-text-fill: white; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        newReportButton.setOnMouseExited(e -> newReportButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #1e88e5; -fx-text-fill: white; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        newReportButton.setOnAction(event -> LawyerReport(scanner, stage, gui));

        HBox bottomBox = new HBox(15, returnButton, newReportButton);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(15));
        bottomBox.setStyle("-fx-background-color: #e8e8e8; -fx-border-color: #dddddd; -fx-border-radius: 8px;");

        root.setBottom(bottomBox);

        // Set the scene
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Case Files Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public void displayJudgeFiles(Stage stage, GUI_Menu gui, Scanner scanner) {
        String hardcodedPath = "report/Judge_report"; // Replace with your hardcoded path
        File folder = new File(hardcodedPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid path or directory does not exist.");
            return;
        }

        // Root layout
        BorderPane root = new BorderPane();
        root.setPrefSize(1000, 600);
        root.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 20;");

        // Title at the top
        Label title = new Label("Judges Report Viewer");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #333;");
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setTop(title);

        // GridPane to hold file details
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(15);
        gridPane.setPadding(new Insets(20));
        gridPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dddddd; -fx-border-radius: 8px; "
                + "-fx-padding: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0.5, 0, 2);");

        int row = 0;
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                try {
                    // Get file creation time
                    BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    String creationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new Date(attributes.creationTime().toMillis()));

                    // File name and creation time label
                    Label fileLabel = new Label(file.getName() + " | Created: " + creationTime);
                    fileLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #555;");

                    // Open button
                    Button openButton = new Button("Open");
                    openButton.setStyle("-fx-font-size: 16px; -fx-background-color: #4caf50; -fx-text-fill: white; "
                            + "-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                    openButton.setOnMouseEntered(e -> openButton.setStyle(
                            "-fx-font-size: 16px; -fx-background-color: #45a049; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
                    openButton.setOnMouseExited(e -> openButton.setStyle(
                            "-fx-font-size: 16px; -fx-background-color: #4caf50; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
                    openButton.setOnAction(event -> {
                        try {
                            // Open the file
                            new ProcessBuilder("explorer", file.getAbsolutePath()).start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    // Add elements to the grid
                    gridPane.add(fileLabel, 0, row);
                    gridPane.add(openButton, 1, row);

                    row++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // ScrollPane to handle large data
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #f5f5f5; -fx-border-color: transparent;");
        root.setCenter(scrollPane);

        // Bottom buttons
        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-font-size: 16px; -fx-background-color: #e53935; -fx-text-fill: white; "
                + "-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        returnButton.setOnMouseEntered(e -> returnButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #d32f2f; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        returnButton.setOnMouseExited(e -> returnButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #e53935; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        returnButton.setOnAction(event -> gui.GUI_startmenu(stage));

        Button newReportButton = new Button("New Report");
        newReportButton.setStyle("-fx-font-size: 16px; -fx-background-color: #1e88e5; -fx-text-fill: white; "
                + "-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        newReportButton.setOnMouseEntered(e -> newReportButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #1976d2; -fx-text-fill: white; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        newReportButton.setOnMouseExited(e -> newReportButton.setStyle(
                "-fx-font-size: 16px; -fx-background-color: #1e88e5; -fx-text-fill: white; -fx-text-fill: white;-fx-padding: 8 20; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        newReportButton.setOnAction(event -> JudgeReport(scanner, stage, gui));

        HBox bottomBox = new HBox(15, returnButton, newReportButton);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(15));
        bottomBox.setStyle("-fx-background-color: #e8e8e8; -fx-border-color: #dddddd; -fx-border-radius: 8px;");

        root.setBottom(bottomBox);

        // Set the scene
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Case Files Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public void CaseReport(Scanner scanner, Stage primaryStage, GUI_Menu gui) {
        // Method to handle scheduling IT system maintenance

        loadData();
        ITAdmin ii = new ITAdmin();
        ii.CaseReport(AllCases, AllSlot, primaryStage, scanner, gui, this);

        // Create a green label to display "Successful Download"
        Label successLabel = new Label("Case Report successfully downloaded in Project Directory!");
        successLabel.setStyle("-fx-text-fill: green; -fx-font-size: 16px; -fx-font-weight: bold;");

        // Get the current scene and layout of the primary stage
        Scene currentScene = primaryStage.getScene();
        VBox currentLayout = (VBox) currentScene.getRoot(); // Assuming your layout is a VBox

        // Check if the "Successful Download" label already exists and remove it
        for (int i = 0; i < currentLayout.getChildren().size(); i++) {
            if (currentLayout.getChildren().get(i) instanceof Label) {
                Label existingLabel = (Label) currentLayout.getChildren().get(i);
                if (existingLabel.getText().equals("Successful Download")) {
                    currentLayout.getChildren().remove(i);
                    break; // Stop after removing the label
                }
            }
        }

        // Add the new success label to the existing layout
        currentLayout.getChildren().add(successLabel);

        // Create a PauseTransition to hide the label after a delay (e.g., 3 seconds)
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            // Remove the success label after 3 seconds
            currentLayout.getChildren().remove(successLabel);

        });
        pause.play(); // Start the timer

        // Optional: Add some space before the label
        successLabel
                .setStyle("-fx-text-fill: green; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 20px 0 0 0;");

        // Update the scene with the modified layout
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }

    public void LawyerReport(Scanner scanner) {
        // Method to handle scheduling IT system maintenance

        ITAdmin i = new ITAdmin();
        // i.LawyerReport(AllCases, AllSlot, AllLawyers);
    }

    public void LawyerReport(Scanner scanner, Stage primaryStage, GUI_Menu gui) {
        // Method to handle scheduling IT system maintenance

        loadData();
        ITAdmin ii = new ITAdmin();
        ii.LawyerReport(AllCases, AllSlot, AllLawyers, primaryStage, scanner, gui, this);

        // Create a green label to display "Successful Download"
        Label successLabel = new Label("Lawyer Report successfully downloaded in Project Directory!");
        successLabel.setStyle("-fx-text-fill: green; -fx-font-size: 16px; -fx-font-weight: bold;");

        // Get the current scene and layout of the primary stage
        Scene currentScene = primaryStage.getScene();
        VBox currentLayout = (VBox) currentScene.getRoot(); // Assuming your layout is a VBox

        // Check if the "Successful Download" label already exists and remove it
        for (int i = 0; i < currentLayout.getChildren().size(); i++) {
            if (currentLayout.getChildren().get(i) instanceof Label) {
                Label existingLabel = (Label) currentLayout.getChildren().get(i);
                if (existingLabel.getText().equals("Successful Download")) {
                    currentLayout.getChildren().remove(i);
                    break; // Stop after removing the label
                }
            }
        }

        // Add the new success label to the existing layout
        currentLayout.getChildren().add(successLabel);

        // Create a PauseTransition to hide the label after a delay (e.g., 3 seconds)
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            // Remove the success label after 3 seconds
            currentLayout.getChildren().remove(successLabel);

        });
        pause.play(); // Start the timer

        // Optional: Add some space before the label
        successLabel
                .setStyle("-fx-text-fill: green; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 20px 0 0 0;");

        // Update the scene with the modified layout
        primaryStage.setScene(currentScene);
        primaryStage.show();

    }

    public void JudgeReport(Scanner scanner) {
        // Method to handle scheduling IT system maintenance

        ITAdmin i = new ITAdmin();
        // i.JudgeReport(AllCases, AllSlot, AllJudges);
    }

    public void JudgeReport(Scanner scanner, Stage primaryStage, GUI_Menu gui) {
        // Method to handle scheduling IT system maintenance

        loadData();
        ITAdmin ii = new ITAdmin();
        ii.JudgeReport(AllCases, AllSlot, AllJudges, primaryStage, scanner, gui, this);

        // Create a green label to display "Successful Download"
        Label successLabel = new Label("Judge Report successfully downloaded in Project Directory!");
        successLabel.setStyle("-fx-text-fill: green; -fx-font-size: 16px; -fx-font-weight: bold;");

        // Get the current scene and layout of the primary stage
        Scene currentScene = primaryStage.getScene();
        VBox currentLayout = (VBox) currentScene.getRoot(); // Assuming your layout is a VBox

        // Check if the "Successful Download" label already exists and remove it
        for (int i = 0; i < currentLayout.getChildren().size(); i++) {
            if (currentLayout.getChildren().get(i) instanceof Label) {
                Label existingLabel = (Label) currentLayout.getChildren().get(i);
                if (existingLabel.getText().equals("Successful Download")) {
                    currentLayout.getChildren().remove(i);
                    break; // Stop after removing the label
                }
            }
        }

        // Add the new success label to the existing layout
        currentLayout.getChildren().add(successLabel);

        // Create a PauseTransition to hide the label after a delay (e.g., 3 seconds)
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            // Remove the success label after 3 seconds
            currentLayout.getChildren().remove(successLabel);
        });
        pause.play(); // Start the timer

        // Optional: Add some space before the label
        successLabel
                .setStyle("-fx-text-fill: green; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 20px 0 0 0;");

        // Update the scene with the modified layout
        primaryStage.setScene(currentScene);
        primaryStage.show();
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

    public void TrackManageUpdates(Stage Primarystage, GUI_Menu gui) {
        // Method to handle tracking and managing updates
        CourtAdministrator c = user.getRelevantCourtAdministrators(AllCourt_Administrators, user);
        if (c != null) {
            System.out.println("Implement tracking/managing updates logic here.");
            c.TrackAndManageUpdates(AllCases, AllSlot, AllJudges, AllLawyers, AllClients, AllNotifications, dbHandler,
                    Primarystage, gui);
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

    public void ScheduleHearingWitness(Scanner scanner, Stage primaryStage, GUI_Menu gui) {
        // Method to handle scheduling hearings or witnesses
        String role = user.getRole();

        if ("Court Administrator".equalsIgnoreCase(role)) {
            CourtAdministrator c = user.getRelevantCourtAdministrators(AllCourt_Administrators, user);
            if (c != null) {
                c.scheduleHearing(scanner, AllCases, AllSlot, AllJudges, AllCourts, AllWitnesses, fileHandler,
                        dbHandler, primaryStage, gui, this);
            }
        } else if ("Lawyer".equalsIgnoreCase(role)) {
            Lawyer l = user.getRelevantLawyer(AllLawyers, user);
            l.scheduleWitness(dbHandler, scanner, AllCases, AllSlot, AllJudges, AllCourts, AllWitnesses, fileHandler,
                    primaryStage, primaryStage.getScene());
        } else if ("Registrar".equalsIgnoreCase(role)) {
            Registrar r = user.getRelevantRegistrar(AllRegistrar, user);
            r.scheduleHearing(scanner, AllCases, AllSlot, AllJudges, AllCourts, AllWitnesses, fileHandler,
                    dbHandler, primaryStage, gui, this);
        }
    }

    public void TrackCase(Stage primaryStage, GUI_Menu gui) {
        // Method to handle tracking a specific case
        System.out.println("Implement case tracking logic here.");
        Registrar registrar = new Registrar();
        registrar = registrar.getRelevantRegistrar(AllRegistrar, user);
        if (registrar != null) {
            registrar.UpdateCase(dbHandler, fileHandler, AllCases, AllSlot, AllClients, AllJudges, AllLawyers,
                    AllWitnesses, AllCourts,
                    primaryStage, gui, this);
            System.out.println("Case has been successfully updated.");

        } else {

            CourtAdministrator CourtAdmin = new CourtAdministrator();
            CourtAdmin = CourtAdmin.getRelevantCourtAdministrators(AllCourt_Administrators, user);
            if (CourtAdmin != null) {
                CourtAdmin.UpdateCase(dbHandler, fileHandler, AllCases, AllSlot, AllClients, AllJudges, AllLawyers,
                        AllWitnesses, AllCourts,
                        primaryStage, gui, this);
                System.out.println("Case has been successfully updated.");
            } else {
                Judge judge = new Judge();
                judge = judge.getRelevantJudge(AllJudges, user);
                if (judge != null) {
                    judge.TrackCase(dbHandler, fileHandler, AllCases, AllSlot, AllClients, AllJudges, AllLawyers,
                            AllWitnesses, AllCourts,
                            primaryStage, gui, this);
                    System.out.println("Case has been successfully updated.");
                } else {
                    Lawyer lawyer = new Lawyer();
                    lawyer = lawyer.getRelevantLawyer(AllLawyers, user);
                    if (lawyer != null) {
                        lawyer.TrackCase(dbHandler, fileHandler, AllCases, AllSlot, AllClients, AllJudges, AllLawyers,
                                AllWitnesses, AllCourts,
                                primaryStage, gui, this);
                        System.out.println("Case has been successfully updated.");
                    } else {
                        ProbationOfficer officer = new ProbationOfficer();
                        officer = officer.getRelevantProbationOfficer(AllProbationOfficers, user);
                        if (officer != null) {
                            officer.TrackCase(dbHandler, fileHandler, AllCases, AllSlot, AllClients, AllJudges,
                                    AllLawyers,
                                    AllWitnesses, AllCourts,
                                    primaryStage, gui, this);
                            System.out.println("Case has been successfully updated.");
                        } else {
                            Clients clients = new Clients();
                            clients = clients.getRelevantClients(AllClients,user);
                            if (clients != null) {
                                clients.TrackCase(dbHandler, fileHandler, AllCases, AllSlot, AllClients, AllJudges,
                                        AllLawyers,
                                        AllWitnesses, AllCourts,
                                        primaryStage, gui, this);
                                System.out.println("Case has been successfully updated.");
                            } else {
                                System.out.println("Invalid User!");
                            }
                        }
                    }
                }

            }

        }

    }

    public void ScheduleHearingWitness(Scanner scanner) {
        // Method to handle scheduling hearings or witnesses
        String role = user.getRole();

        if ("Court Administrator".equalsIgnoreCase(role)) {
            CourtAdministrator c = user.getRelevantCourtAdministrators(AllCourt_Administrators, user);
            // c.scheduleHearing(scanner, AllCases, AllSlot, AllJudges, AllCourts,
            // AllWitnesses, fileHandler, dbHandler);
        } else if ("Lawyer".equalsIgnoreCase(role)) {
            Lawyer l = user.getRelevantLawyer(AllLawyers, user);
            // l.scheduleWitness(scanner, AllCases, AllSlot, AllJudges, AllCourts,
            // AllWitnesses, fileHandler, dbHandler);
        } else if ("Registrar".equalsIgnoreCase(role)) {
            Registrar r = user.getRelevantRegistrar(AllRegistrar, user);
            // r.scheduleHearing(scanner, AllCases, AllSlot, AllJudges, AllCourts,
            // AllWitnesses, fileHandler, dbHandler);
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
                    System.out.println("1. Generate Report of Cases");
                    System.out.println("2. Display Notifications");
                    System.out.println("3. Generate Report for Lawyers");
                    System.out.println("4. Generate Report for Judges");
                    System.out.println("5. Log Out");
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
                            System.out.println("Generate Report for Lawyers selected");
                            LawyerReport(scanner);
                            break;
                        case 4:
                            System.out.println("Generate Report for Judges selected");
                            JudgeReport(scanner);
                            break;

                        case 5:
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
                            // TrackUpdates(); // Calling method to track updates
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
                            // TrackUpdates(); // Calling method to track updates
                            break;
                        case 3:
                            // Register to Bar
                            System.out.println("Register to Bar selected.");
                            RegisterToBar(scanner); // Calling method to register to the bar
                            break;
                        case 4:
                            // Submit Document
                            System.out.println("Submit Document selected.");
                            SubmitDocument(scanner); // Calling method to submit document
                            break;
                        case 5:
                            // Re-open Case/Appeal
                            System.out.println("Re-open Case/Appeal selected.");
                            // ReOpenCaseOrAppeal(); // Calling method to reopen case or appeal
                            break;
                        case 6:
                            // Request to Retrieve Record
                            System.out.println("Request to Retrieve Record selected.");
                            // RequestToRetrieveRecord(); // Calling method to request record retrieval
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
                            // TrackUpdates(); // Calling method to track updates
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
                            RegisterToBar(scanner); // Calling method to monitor Bar Registration
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

    public class GUI_Menu {
        CourtsManagementSystem system;

        Scanner scanner;
        Stage primaryStage;
        private static final String BASE_STYLE = "-fx-background-color: rgba(255, 255, 255, 0.1); "
                + "-fx-text-fill: white; "
                + "-fx-font-size: 18px; "
                + "-fx-font-family: 'Segoe UI', sans-serif; "
                + "-fx-padding: 10px 15px; "
                + "-fx-border-radius: 10px; "
                + "-fx-border-color: rgba(255, 255, 255, 0.2); "
                + "-fx-border-width: 1px; "
                + "-fx-background-radius: 10px; "
                + "-fx-effect: dropshadow(gaussian, #000000, 6, 0, 0, 8); "
                + "-fx-transition: all 0.3s ease-in-out;";

        private static final String FOCUSED_STYLE = "-fx-background-color: rgba(255, 255, 255, 0.2); "
                + "-fx-text-fill: white; "
                + "-fx-font-size: 18px; "
                + "-fx-font-family: 'Segoe UI', sans-serif; "
                + "-fx-padding: 10px 15px; "
                + "-fx-border-radius: 20px; "
                + "-fx-border-color: rgba(255, 255, 255, 0.7); "
                + "-fx-border-width: 1px; "
                + "-fx-background-radius: 20px; "
                + "-fx-effect: dropshadow(gaussian, #000000, 6, 0, 0, 10); "
                + "-fx-transition: all 0.3s ease-in-out;";

        private static final String COMBOBOX_BASE_STYLE = "-fx-background-color: rgba(255, 255, 255, 0.1); "
                + "-fx-text-fill: white; "
                + "-fx-font-size: 18px; "
                + "-fx-font-family: 'Segoe UI', sans-serif; "
                + "-fx-padding: 5px 15px; "
                + "-fx-border-radius: 10px; "
                + "-fx-border-color: rgba(255, 255, 255, 0.2); "
                + "-fx-border-width: 1px; "
                + "-fx-background-radius: 10px; "
                + "-fx-effect: dropshadow(gaussian, #000000, 6, 0, 0, 8); "
                + "-fx-transition: all 0.3s ease-in-out;"
                + "-fx-prompt-text-fill: white;";

        private static final String COMBOBOX_FOCUSED_STYLE = "-fx-background-color: rgba(255, 255, 255, 0.2); "
                + "-fx-text-fill: white; "
                + "-fx-font-size: 18px; "
                + "-fx-font-family: 'Segoe UI', sans-serif; "
                + "-fx-padding: 5px 15px; "
                + "-fx-border-radius: 20px; "
                + "-fx-border-color: rgba(255, 255, 255, 0.7); "
                + "-fx-border-width: 1px; "
                + "-fx-background-radius: 20px; "
                + "-fx-effect: dropshadow(gaussian, #000000, 6, 0, 0, 10); "
                + "-fx-transition: all 0.3s ease-in-out;"
                + "-fx-prompt-text-fill: white;";

        public void designComboBox(ComboBox<String> comboBox) {
            comboBox.setStyle(COMBOBOX_BASE_STYLE);

            // On focus gained (when the user clicks into the ComboBox)
            comboBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    comboBox.setStyle(COMBOBOX_FOCUSED_STYLE);
                } else {
                    comboBox.setStyle(COMBOBOX_BASE_STYLE);
                }
            });

            // Optional: To add focus style when a dropdown item is selected
            comboBox.getEditor().focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    comboBox.setStyle(COMBOBOX_FOCUSED_STYLE);
                } else {
                    comboBox.setStyle(COMBOBOX_BASE_STYLE);
                }
            });
        }

        public void designTextField(TextField textField) {
            textField.setStyle(BASE_STYLE);

            // On focus gained (when the user clicks into the field)
            textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    textField.setStyle(FOCUSED_STYLE);
                } else {
                    textField.setStyle(BASE_STYLE);
                }
            });
        }

        public void designPasswordField(PasswordField passwordField) {
            passwordField.setStyle(BASE_STYLE);

            // On focus gained (when the user clicks into the field)
            passwordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    passwordField.setStyle(FOCUSED_STYLE);
                } else {
                    passwordField.setStyle(BASE_STYLE);
                }
            });
        }

        public void showMessage(String message) {
            // Create a new Stage for the message dialog
            Stage messageStage = new Stage();
            messageStage.setTitle("Message");

            // Create a VBox layout for the message
            VBox layout = new VBox(15);
            layout.setPadding(new Insets(20));
            layout.setAlignment(Pos.CENTER);

            // Add a Label to display the message
            Label messageLabel = new Label(message);
            messageLabel.setWrapText(true); // Allow long messages to wrap
            messageLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");

            // Add a button to close the message dialog
            Button closeButton = new Button("OK");
            closeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            closeButton.setOnAction(e -> messageStage.close());

            layout.getChildren().addAll(messageLabel, closeButton);

            // Set the layout in a Scene
            Scene scene = new Scene(layout, 300, 150);
            messageStage.setScene(scene);

            // Make the dialog modal (blocks input to other windows)
            messageStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);

            // Show the message dialog
            messageStage.showAndWait();
        }

        public void designButton(Button button) {
            // Initial button style (Glassmorphism)
            button.setStyle(
                    "-fx-background-color: rgba(255, 255, 255, 0.7); " // Semi-transparent white background for glass
                                                                       // effect
                            + "-fx-text-fill: black; " // White text color
                            + "-fx-font-size: 20px; " // Font size
                            + "-fx-font-weight: bold; " // Bold text
                            + "-fx-padding: 6px 30px; " // Padding for spacing
                            + "-fx-border-radius: 15px; " // Rounded corners for smooth look
                            + "-fx-border-color: rgba(255, 255, 255, 0.6); " // Light border with slight transparency
                            + "-fx-border-width: 1px; " // Thin border for subtle effect
                            + "-fx-background-radius: 15px; " // Rounded corners for background
                            + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 10); " // Shadow effect for depth
                            + "-fx-transition: all 1.5s ease; "
                            + "-fx-min-width: 200px; " // Smooth transition for hover effect

            );

            // Hover effect with animation
            button.setOnMouseEntered(e -> {
                button.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 1.0); " // Lighter transparency on hover
                                + "-fx-text-fill: black; " // Text color changes to blue
                                + "-fx-font-size: 20px; "
                                + "-fx-font-weight: bold; "
                                + "-fx-padding: 6px 30px; "
                                + "-fx-border-radius: 20px; "
                                + "-fx-border-color: rgba(255, 255, 255, 0.8); " // Lighter border on hover
                                + "-fx-border-width: 1px; "
                                + "-fx-background-radius: 20px; "
                                + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 15); " // Enhanced shadow on
                                + "-fx-min-width: 200px; " // hover
                                + "-fx-transition: all 1.5s ease; "

                );
            });

            // Reset style when mouse exits
            button.setOnMouseExited(e -> {
                button.setStyle(
                        "-fx-background-color: rgba(255, 255, 255,0.7); " // Reset to original glass effect
                                + "-fx-text-fill: black; "
                                + "-fx-font-size: 20px; "
                                + "-fx-font-weight: bold; "
                                + "-fx-padding: 6px 30px; "
                                + "-fx-border-radius: 15px; "
                                + "-fx-border-color: rgba(255, 255, 255, 0.6); "
                                + "-fx-border-width: 1px; "
                                + "-fx-background-radius: 15px; "
                                + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 10); "
                                + "-fx-transition: all 1.5s ease; "
                                + "-fx-min-width: 200px; "

                );
            });
        }

        public void designButton_smallerdetailed(Button button) {
            // Initial button style (Glassmorphism with Subtle Neumorphism elements)
            button.setStyle(
                    // Semi-transparent white background for glassmorphism effect with slight blur
                    "-fx-background-color: rgba(255, 255, 255, 0.4); "
                            + "-fx-text-fill: #333333; " // Dark text for better contrast and readability
                            + "-fx-font-size: 18px; " // Larger font size for elegance
                            + "-fx-font-weight: bold; " // Bold text for prominence
                            + "-fx-font-family: 'Segoe UI', sans-serif; " // Elegant, modern font family
                            + "-fx-padding: 10px 40px; " // Increased padding for a more spacious button
                            + "-fx-border-radius: 25px; " // Fully rounded corners for a soft, smooth appearance
                            + "-fx-border-color: rgba(255, 255, 255, 0.3); " // Light white border for softness
                            + "-fx-border-width: 2px; " // Thin border for subtle elegance
                            + "-fx-background-radius: 25px; " // Fully rounded corners for the background
                            + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 15, 0.3, 0, 10); " // Soft shadow
                                                                                                       // for depth and
                                                                                                       // realism
                            + "-fx-transition: all 0.3s ease-in-out; " // Smooth transition effect
                            + "-fx-min-width: 280px; " // Minimum width for consistent look
                            + "-fx-min-height: 50px; " // Minimum height for proper clickability
                            + "-fx-text-alignment: center; " // Text alignment to the center for balance
                            + "-fx-opacity: 1; " // Full opacity initially for clarity
                            + "-fx-background-image: linear-gradient(to right, rgba(255, 255, 255, 0.2), rgba(255, 255, 255, 0.4)); " // Soft
                                                                                                                                      // gradient
                                                                                                                                      // background
            );

            // Hover effect with enhanced animation and dynamic visual feedback
            button.setOnMouseEntered(e -> {
                button.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.8); " // Lighter background on hover for prominence
                                + "-fx-text-fill: #1a1a1a; " // Slightly darker text for contrast during hover
                                + "-fx-font-size: 18px; "
                                + "-fx-font-weight: bold; "
                                + "-fx-font-family: 'Segoe UI', sans-serif; "
                                + "-fx-padding: 10px 40px; "
                                + "-fx-border-radius: 25px; "
                                + "-fx-border-color: rgba(255, 255, 255, 0.6); " // Slightly brighter border on hover
                                + "-fx-border-width: 2px; "
                                + "-fx-background-radius: 25px; "
                                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 20, 0.4, 0, 15); " // Enhanced
                                                                                                           // shadow to
                                                                                                           // indicate
                                                                                                           // depth
                                + "-fx-min-width: 280px; "
                                + "-fx-min-height: 50px; "
                                + "-fx-text-alignment: center; "
                                + "-fx-opacity: 1; "
                                + "-fx-transition: all 0.3s ease-in-out; "
                                + "-fx-background-image: linear-gradient(to right, rgba(255, 255, 255, 0.4), rgba(255, 255, 255, 0.6)); " // Dynamic
                                                                                                                                          // gradient
                                                                                                                                          // for
                                                                                                                                          // hover
                                                                                                                                          // effect
                );
            });

            // Reset the style on mouse exit (smooth reset for a clean experience)
            button.setOnMouseExited(e -> {
                button.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.4); " // Reset to original transparent background
                                + "-fx-text-fill: #333333; " // Dark text color for visibility
                                + "-fx-font-size: 18px; "
                                + "-fx-font-weight: bold; "
                                + "-fx-font-family: 'Segoe UI', sans-serif; "
                                + "-fx-padding: 10px 40px; "
                                + "-fx-border-radius: 25px; "
                                + "-fx-border-color: rgba(255, 255, 255, 0.3); "
                                + "-fx-border-width: 2px; "
                                + "-fx-background-radius: 25px; "
                                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 15, 0.3, 0, 10); " // Subtle
                                                                                                           // shadow
                                                                                                           // effect for
                                                                                                           // a soft
                                                                                                           // look
                                + "-fx-min-width: 280px; "
                                + "-fx-min-height: 50px; "
                                + "-fx-text-alignment: center; "
                                + "-fx-opacity: 1; "
                                + "-fx-transition: all 0.3s ease-in-out; "
                                + "-fx-background-image: linear-gradient(to right, rgba(255, 255, 255, 0.2), rgba(255, 255, 255, 0.4)); " // Subtle
                                                                                                                                          // gradient
                                                                                                                                          // effect
                );
            });

            // Focus state for keyboard accessibility and visual cue (soft focus effect)
            button.setOnKeyPressed(e -> {
                button.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.9); " // Brightened background on focus for
                                                                           // emphasis
                                + "-fx-text-fill: #000000; " // Dark text for clear visibility
                                + "-fx-font-size: 18px; "
                                + "-fx-font-weight: bold; "
                                + "-fx-font-family: 'Segoe UI', sans-serif; "
                                + "-fx-padding: 10px 40px; "
                                + "-fx-border-radius: 25px; "
                                + "-fx-border-color: rgba(255, 255, 255, 0.8); " // Brighter border color to indicate
                                                                                 // focus
                                + "-fx-border-width: 2px; "
                                + "-fx-background-radius: 25px; "
                                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 25, 0.4, 0, 20); " // Stronger
                                                                                                           // shadow to
                                                                                                           // highlight
                                                                                                           // focus
                                + "-fx-min-width: 280px; "
                                + "-fx-min-height: 50px; "
                                + "-fx-text-alignment: center; "
                                + "-fx-opacity: 1; "
                                + "-fx-transition: all 0.3s ease-in-out; "
                                + "-fx-background-image: linear-gradient(to right, rgba(255, 255, 255, 0.7), rgba(255, 255, 255, 0.9)); " // Subtle
                                                                                                                                          // focus
                                                                                                                                          // gradient
                                                                                                                                          // effect
                );
            });

            // Reset focus effect on key release
            button.setOnKeyReleased(e -> {
                button.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.4); " // Reset to normal transparent background
                                + "-fx-text-fill: #333333; "
                                + "-fx-font-size: 18px; "
                                + "-fx-font-weight: bold; "
                                + "-fx-font-family: 'Segoe UI', sans-serif; "
                                + "-fx-padding: 10px 40px; "
                                + "-fx-border-radius: 25px; "
                                + "-fx-border-color: rgba(255, 255, 255, 0.3); "
                                + "-fx-border-width: 2px; "
                                + "-fx-background-radius: 25px; "
                                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 15, 0.3, 0, 10); "
                                + "-fx-min-width: 280px; "
                                + "-fx-min-height: 50px; "
                                + "-fx-text-alignment: center; "
                                + "-fx-opacity: 1; "
                                + "-fx-transition: all 0.3s ease-in-out; "
                                + "-fx-background-image: linear-gradient(to right, rgba(255, 255, 255, 0.2), rgba(255, 255, 255, 0.4)); ");
            });
        }

        public void designButton_smaller(Button button) {
            // Initial button style (Glassmorphism)
            button.setStyle(
                    "-fx-background-color: rgba(255, 255, 255, 0.7); " // Semi-transparent white background for glass
                                                                       // effect
                            + "-fx-text-fill: black; " // White text color
                            + "-fx-font-size: 16px; " // Font size
                            + "-fx-font-weight: bold; " // Bold text
                            + "-fx-padding: 3px 30px; " // Padding for spacing
                            + "-fx-border-radius: 15px; " // Rounded corners for smooth look
                            + "-fx-border-color: rgba(255, 255, 255, 0.6); " // Light border with slight transparency
                            + "-fx-border-width: 1px; " // Thin border for subtle effect
                            + "-fx-background-radius: 15px; " // Rounded corners for background
                            + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 10); " // Shadow effect for depth
                            + "-fx-transition: all 1.5s ease; "
                            + "-fx-min-width: 300px; " // Smooth transition for hover effect

            );

            // Hover effect with animation
            button.setOnMouseEntered(e -> {
                button.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 1.0); " // Lighter transparency on hover
                                + "-fx-text-fill: black; " // Text color changes to blue
                                + "-fx-font-size: 16px; "
                                + "-fx-font-weight: bold; "
                                + "-fx-padding: 3px 30px; "
                                + "-fx-border-radius: 20px; "
                                + "-fx-border-color: rgba(255, 255, 255, 0.8); " // Lighter border on hover
                                + "-fx-border-width: 1px; "
                                + "-fx-background-radius: 20px; "
                                + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 15); " // Enhanced shadow on
                                + "-fx-min-width: 300px; " // hover
                                + "-fx-transition: all 1.5s ease; "

                );
            });

            // Reset style when mouse exits
            button.setOnMouseExited(e -> {
                button.setStyle(
                        "-fx-background-color: rgba(255, 255, 255,0.7); " // Reset to original glass effect
                                + "-fx-text-fill: black; "
                                + "-fx-font-size: 16px; "
                                + "-fx-font-weight: bold; "
                                + "-fx-padding: 3px 30px; "
                                + "-fx-border-radius: 15px; "
                                + "-fx-border-color: rgba(255, 255, 255, 0.6); "
                                + "-fx-border-width: 1px; "
                                + "-fx-background-radius: 15px; "
                                + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 10); "
                                + "-fx-transition: all 1.5s ease; "
                                + "-fx-min-width: 300px; "

                );
            });
        }

        public void styleLogoutButton(Button button) {
            button.setText("Logout"); // Set button text
            button.setStyle(
                    "-fx-background-color: rgba(255, 69, 58, 0.7); " // Semi-transparent red background
                            + "-fx-text-fill: white; " // White text color
                            + "-fx-font-size: 16px; " // Font size
                            + "-fx-font-weight: bold; " // Bold text
                            + "-fx-padding: 8px 20px; " // Padding for spacing
                            + "-fx-border-radius: 10px; " // Rounded corners for smooth look
                            + "-fx-border-color: rgba(255, 69, 58, 0.9); " // Subtle border in red
                            + "-fx-border-width: 1px; " // Thin border for a minimal effect
                            + "-fx-background-radius: 10px; " // Rounded corners for background
                            + "-fx-cursor: hand; " // Change cursor to pointer
                            + "-fx-transition: all 0.3s ease-in-out;" // Smooth animation for hover
            );

            // Set hover effect
            button.setOnMouseEntered(event -> button.setStyle(
                    "-fx-background-color: rgba(255, 69, 58, 1); " // Solid red background on hover
                            + "-fx-text-fill: white; " // White text on hover
                            + "-fx-font-size: 16px; "
                            + "-fx-font-weight: bold; "
                            + "-fx-padding: 8px 20px; "
                            + "-fx-border-radius: 10px; "
                            + "-fx-border-color: rgba(255, 255, 255, 0.7); " // White border on hover
                            + "-fx-border-width: 1px; "
                            + "-fx-background-radius: 10px; "
                            + "-fx-cursor: hand; "
                            + "-fx-transition: all 0.3s ease-in-out;"));

            // Revert to original style on mouse exit
            button.setOnMouseExited(event -> button.setStyle(
                    "-fx-background-color: rgba(255, 69, 58, 0.7); " // Semi-transparent red
                            + "-fx-text-fill: white; "
                            + "-fx-font-size: 16px; "
                            + "-fx-font-weight: bold; "
                            + "-fx-padding: 8px 20px; "
                            + "-fx-border-radius: 10px; "
                            + "-fx-border-color: rgba(255, 69, 58, 0.9); "
                            + "-fx-border-width: 1px; "
                            + "-fx-background-radius: 10px; "
                            + "-fx-cursor: hand; "
                            + "-fx-transition: all 0.3s ease-in-out;"));
        }

        public GUI_Menu(CourtsManagementSystem system, Stage primaryStage, Scanner scanner) {
            super();
            this.system = system;
            this.scanner = scanner;
            this.primaryStage = primaryStage;
            system.loadData();
            system.printAllObjects();

        }

        public void GUI_startmenu(Stage primaryStage) {
            String role = user.getRole();
            system.user = user;
            // Create a VBox layout to hold the buttons
            VBox layout = new VBox(10); // 10px spacing between buttons
            VBox layoutouter = new VBox(10);
            VBox layoutinner = new VBox(10);
            Scene scene = new Scene(layoutouter, 1100, 650); // Setting up the scene size
            layout.setStyle("-fx-alignment: top-center;");
            Label titleLabel = new Label();
            // Main menu for different roles
            if ("Court Administrator".equalsIgnoreCase(role)) {

                primaryStage.setTitle("Main Menu for Court Administrator");
                titleLabel = new Label("Court Administrator's Menu");
                titleLabel.setStyle("-fx-font-size: 38px; -fx-font-weight: bold;-fx-text-fill: white; ");
                Button caseFilingButton = new Button("Case Filing/Scheduling");
                Button trackUpdatesButton = new Button("Track/Manage Updates");
                Button trackCaseButton = new Button("Track Case");
                Button scheduleHearingButton = new Button("Schedule Hearing");
                Button ReOpenCaseButton = new Button("Re-Open Case");
                Button closeCaseButton = new Button("Close Case");
                Button displayNotificationsButton = new Button("Display Notifications");
                Button logoutButton = new Button("Log Out");
                designButton_smallerdetailed(caseFilingButton);
                designButton_smallerdetailed(trackUpdatesButton);
                designButton_smallerdetailed(trackCaseButton);
                designButton_smallerdetailed(scheduleHearingButton);
                designButton_smallerdetailed(ReOpenCaseButton);
                designButton_smallerdetailed(closeCaseButton);
                designButton_smallerdetailed(displayNotificationsButton);
                styleLogoutButton(logoutButton);

                caseFilingButton.setOnAction(e -> {
                    System.out.println("Case Filing/Scheduling selected.");
                    system.ReviewUpcomingCaseRequests(primaryStage, this); // Call your method
                });

                trackUpdatesButton.setOnAction(e -> {
                    System.out.println("Track/Manage Updates selected.");
                    system.TrackManageUpdates(primaryStage, this); // Call your method
                });

                trackCaseButton.setOnAction(e -> {
                    System.out.println("Update Case selection.");
                    system.TrackCase(primaryStage, this); // Call your method
                });

                scheduleHearingButton.setOnAction(e -> {
                    System.out.println("Schedule Hearing selected.");
                    system.ScheduleHearingWitness(scanner, primaryStage, this); // Call your method
                });

                ReOpenCaseButton.setOnAction(e -> {
                    System.out.println("Re-Open Case selected.");
                    system.ReOpenCaseOrAppeal(primaryStage); // Call your method
                });

                closeCaseButton.setOnAction(e -> {
                    System.out.println("Close Case selected.");
                    system.closeCase(primaryStage); // Call your method
                });

                displayNotificationsButton.setOnAction(e -> {
                    System.out.println("Display Notifications selected.");
                    system.viewMyNotifications(primaryStage, this); // Call your method
                });

                logoutButton.setOnAction(e -> {
                    System.out.println("Logging out...");
                    system.start(primaryStage); // Close the primaryStage for logout
                });

                // Add buttons to the layout

                layoutinner.getChildren().addAll(caseFilingButton, trackUpdatesButton, trackCaseButton,
                        scheduleHearingButton, ReOpenCaseButton,
                        closeCaseButton, displayNotificationsButton, logoutButton);

            } else if ("IT Admin".equalsIgnoreCase(role)) {
                primaryStage.setTitle("Main Menu for IT Administrator");
                titleLabel = new Label("IT Admin's Menu");
                titleLabel.setStyle("-fx-font-size: 38px; -fx-font-weight: bold;-fx-text-fill: white; ");
                Button caseReportButton = new Button("Generate Report of Cases");
                Button caseNotificationButton = new Button("Display Notifications");
                Button lawyerReportButton = new Button("Generate Report for Lawyers");
                Button judgeReportButton = new Button("Generate Report for Judges");
                Button logoutButton = new Button("Log Out");
                designButton_smallerdetailed(caseReportButton);
                designButton_smallerdetailed(caseNotificationButton);
                designButton_smallerdetailed(lawyerReportButton);
                designButton_smallerdetailed(judgeReportButton);
                styleLogoutButton(logoutButton);

                caseReportButton.setOnAction(e -> {
                    System.out.println("Generate Report of Cases selected.");
                    system.displayCaseFiles(primaryStage, this, scanner); // Call your method
                });

                caseNotificationButton.setOnAction(e -> {
                    System.out.println("Display Notifications selected.");
                    system.viewMyNotifications(primaryStage, this); // Call your method
                });

                lawyerReportButton.setOnAction(e -> {
                    System.out.println("Generate Report for Lawyers selected.");
                    system.displayLawyerFiles(primaryStage, this, scanner); // Call your method
                });

                judgeReportButton.setOnAction(e -> {
                    System.out.println("Generate Report for Judges selected.");
                    system.displayJudgeFiles(primaryStage, this, scanner); // Call your method
                });

                logoutButton.setOnAction(e -> {
                    System.out.println("Logging out...");
                    system.start(primaryStage); // Close the primaryStage for logout
                });

                layoutinner.getChildren().addAll(caseReportButton, caseNotificationButton, lawyerReportButton,
                        judgeReportButton, logoutButton);

            } else if ("Judge".equalsIgnoreCase(role)) {
                primaryStage.setTitle("Main Menu for Judge");
                titleLabel = new Label("Judge's Menu");
                titleLabel.setStyle("-fx-font-size: 38px; -fx-font-weight: bold;-fx-text-fill: white; ");
                Button trackUpdatesButton = new Button("Track Updates");
                Button trackCaseButton = new Button("Track Case");
                Button reviewDocumentButton = new Button("Review Document/Log Judgment");
                Button notificationButton = new Button("Display Notifications");
                Button logoutButton = new Button("Log Out");

                designButton_smallerdetailed(trackUpdatesButton);
                designButton_smallerdetailed(trackCaseButton);
                designButton_smallerdetailed(reviewDocumentButton);
                designButton_smallerdetailed(notificationButton);
                styleLogoutButton(logoutButton);

                trackUpdatesButton.setOnAction(e -> {
                    System.out.println("Track Updates selected.");
                    system.TrackUpdates(primaryStage, this); // Call your method
                });

                trackCaseButton.setOnAction(e -> {
                    System.out.println("Track Case selected.");
                    system.TrackCase(primaryStage, this); // Call your method
                });

                reviewDocumentButton.setOnAction(e -> {
                    System.out.println("Review Document/Log Judgment selected.");
                    system.ReviewDocumentLogJudgment(scanner, primaryStage); // Call your method
                });

                notificationButton.setOnAction(e -> {
                    System.out.println("Display Notifications selected.");
                    system.viewMyNotifications(primaryStage, this); // Call your method
                });

                logoutButton.setOnAction(e -> {
                    System.out.println("Logging out...");
                    system.start(primaryStage); // Close the primaryStage for logout
                });

                layoutinner.getChildren().addAll(trackUpdatesButton, trackCaseButton, reviewDocumentButton,
                        notificationButton, logoutButton);

            } else if ("Juror".equalsIgnoreCase(role)) {
                // Juror role buttons
                titleLabel = new Label("Juror's Menu");
                titleLabel.setStyle("-fx-font-size: 38px; -fx-font-weight: bold;-fx-text-fill: white; ");
                primaryStage.setTitle("Main Menu for Juror");

                Button btnReviewDocLogJudgment = new Button("Review Document/Log Judgment");
                btnReviewDocLogJudgment.setOnAction(e -> system.ReviewDocumentLogJudgment(scanner, primaryStage));

                Button btnViewNotifications = new Button("Display Notifications");
                btnViewNotifications.setOnAction(e -> system.viewMyNotifications(primaryStage, this));

                Button btnLogout = new Button("Log Out");

                designButton_smallerdetailed(btnReviewDocLogJudgment);
                designButton_smallerdetailed(btnViewNotifications);
                styleLogoutButton(btnLogout);

                btnLogout.setOnAction(e -> {
                    System.out.println("Logging out...");
                    system.start(primaryStage);
                });

                layoutinner.getChildren().addAll(btnReviewDocLogJudgment, btnViewNotifications, btnLogout);
            } else if ("Lawyer".equalsIgnoreCase(role)) {
                // Lawyer role buttons
                titleLabel = new Label("Lawyer's Menu");
                titleLabel.setStyle("-fx-font-size: 38px; -fx-font-weight: bold;-fx-text-fill: white; ");
                primaryStage.setTitle("Main Menu for Lawyer");
                Button btnCaseFiling = new Button("Case Filing/Scheduling");
                btnCaseFiling.setOnAction(e -> system.AddNewCase(primaryStage, this));

                Button btnTrackUpdates = new Button("Track Updates");
                btnTrackUpdates.setOnAction(e -> system.TrackCase(primaryStage, this));

                Button btnRegisterBar = new Button("Register to Bar");
                btnRegisterBar.setOnAction(e -> system.RegisterToBar(primaryStage, this));

                Button btnSubmitDoc = new Button("Submit Document");
                btnSubmitDoc.setOnAction(e -> system.SubmitDocument(scanner, primaryStage));

                Button btnReopenCase = new Button("Re-open Case");
                btnReopenCase.setOnAction(e -> system.ReOpenCaseOrAppeal(primaryStage));

                // Button btnRetrieveRecord = new Button("Request to Retrieve Record");
                // btnRetrieveRecord.setOnAction(e -> RequestToRetrieveRecord());

                Button btnAddWitness = new Button("Add Witness to Case");
                btnAddWitness.setOnAction(e -> system.ScheduleHearingWitness(scanner, primaryStage, this));

                Button btnViewNotifications = new Button("Display Notifications");
                btnViewNotifications.setOnAction(e -> system.viewMyNotifications(primaryStage, this));

                Button btnLogout = new Button("Log Out");
                designButton_smallerdetailed(btnCaseFiling);
                designButton_smallerdetailed(btnTrackUpdates);
                designButton_smallerdetailed(btnRegisterBar);
                designButton_smallerdetailed(btnSubmitDoc);
                designButton_smallerdetailed(btnReopenCase);
                // designButton_smallerdetailed(btnRetrieveRecord);
                designButton_smallerdetailed(btnAddWitness);
                designButton_smallerdetailed(btnViewNotifications);
                styleLogoutButton(btnLogout);

                btnLogout.setOnAction(e -> {
                    System.out.println("Logging out...");
                    system.start(primaryStage);
                });

                layoutinner.getChildren().addAll(btnCaseFiling, btnTrackUpdates, btnRegisterBar, btnSubmitDoc,
                        btnReopenCase, btnAddWitness, btnViewNotifications, btnLogout);
            } else if ("Probation Officer".equalsIgnoreCase(role)) {
                // Probation Officer role buttons
                titleLabel = new Label("Probation Officer's Menu");
                titleLabel.setStyle("-fx-font-size: 38px; -fx-font-weight: bold;-fx-text-fill: white; ");
                primaryStage.setTitle("Main Menu for Probation Officer");
                Button btnTrackCase = new Button("Track Case");
                btnTrackCase.setOnAction(e -> system.TrackCase(primaryStage, this));

                Button btnSubmitDoc = new Button("Submit Document");
                btnSubmitDoc.setOnAction(e -> SubmitDocument(scanner, primaryStage));

                Button btnViewNotifications = new Button("Display Notifications");
                btnViewNotifications.setOnAction(e -> system.viewMyNotifications(primaryStage, this));

                Button btnLogout = new Button("Log Out");
                designButton_smallerdetailed(btnTrackCase);
                designButton_smallerdetailed(btnSubmitDoc);
                designButton_smallerdetailed(btnViewNotifications);
                styleLogoutButton(btnLogout);

                btnLogout.setOnAction(e -> {
                    System.out.println("Logging out...");
                    system.start(primaryStage);
                });

                layoutinner.getChildren().addAll(btnTrackCase, btnSubmitDoc, btnViewNotifications, btnLogout);
            } else if ("Witness".equalsIgnoreCase(role)) {
                // Witness role buttons
                titleLabel = new Label("Witness's Menu");
                titleLabel.setStyle("-fx-font-size: 38px; -fx-font-weight: bold;-fx-text-fill: white; ");
                primaryStage.setTitle("Main Menu for Witness");
                Button btnViewCaseDetails = new Button("View Case Details");
                btnViewCaseDetails.setOnAction(e -> system.ViewCaseDetails(scanner, primaryStage));

                Button btnViewNotifications = new Button("Display Notifications");
                btnViewNotifications.setOnAction(e -> system.viewMyNotifications(primaryStage, this));

                Button btnLogout = new Button("Log Out");

                designButton_smallerdetailed(btnViewCaseDetails);
                designButton_smallerdetailed(btnViewNotifications);
                styleLogoutButton(btnLogout);
                btnViewCaseDetails.setOnAction(e -> system.TrackCase(primaryStage, this));
                btnViewNotifications.setOnAction(e -> system.TrackCase(primaryStage, this));
                btnLogout.setOnAction(e -> {
                    System.out.println("Logging out...");

                    system.start(primaryStage);
                });

                layoutinner.getChildren().addAll(btnViewCaseDetails, btnViewNotifications, btnLogout);

            } else if ("Client".equalsIgnoreCase(role)) {
                // Client role buttons
                titleLabel = new Label("Client's Menu");
                titleLabel.setStyle("-fx-font-size: 38px; -fx-font-weight: bold;-fx-text-fill: white; ");
                primaryStage.setTitle("Main Menu for Client");
                Button btnTrackUpdates = new Button("Track Updates");
                btnTrackUpdates.setOnAction(e -> system.TrackCase(primaryStage, this));

                // Button btnRequestReOpeningAppeal = new Button("Request for Case
                // Re-Opening/Appeal");
                // btnRequestReOpeningAppeal.setOnAction(e -> RequestCaseReopeningOrAppeal());

                Button btnViewNotifications = new Button("Display Notifications");
                btnViewNotifications.setOnAction(e -> system.viewMyNotifications(primaryStage, this));

                Button btnLogout = new Button("Log Out");

                designButton_smallerdetailed(btnTrackUpdates);
                // designButton_smallerdetailed(btnRequestReOpeningAppeal);
                designButton_smallerdetailed(btnViewNotifications);
                styleLogoutButton(btnLogout);

                btnLogout.setOnAction(e -> {
                    System.out.println("Logging out...");
                    system.start(primaryStage);
                });

                layoutinner.getChildren().addAll(btnTrackUpdates, btnViewNotifications, btnLogout);

            } else if ("Registrar".equalsIgnoreCase(role)) {
                // Registrar role buttons
                titleLabel = new Label("Registrar's Menu");
                titleLabel.setStyle("-fx-font-size: 38px; -fx-font-weight: bold;-fx-text-fill: white; ");
                primaryStage.setTitle("Main Menu for Registrar");
                Button btnCaseFiling = new Button("Case Filing/Scheduling");
                btnCaseFiling.setOnAction(e -> system.ReviewUpcomingCaseRequests(primaryStage, this));

                Button btnBarRegistration = new Button("Bar Registration");
                btnBarRegistration.setOnAction(e -> system.RegisterToBar(primaryStage, this));

                Button btnTrackCase = new Button("Track Case");
                btnTrackCase.setOnAction(e -> system.TrackCase(primaryStage, this));

                Button btnScheduleHearing = new Button("Schedule Hearing");
                btnScheduleHearing.setOnAction(e -> system.ScheduleHearingWitness(scanner, primaryStage, this));

                Button btnApproveDocument = new Button("Approve Document");
                btnApproveDocument.setOnAction(e -> system.SubmitDocument(scanner, primaryStage));

                Button btnApproveJudgement = new Button("Approve Judgement");
                btnApproveJudgement.setOnAction(e -> system.ReviewDocumentLogJudgment(scanner, primaryStage));

                Button btnViewNotifications = new Button("Display Notifications");
                btnViewNotifications.setOnAction(e -> system.viewMyNotifications(primaryStage, this));

                Button btnLogout = new Button("Log Out");

                designButton_smallerdetailed(btnCaseFiling);
                designButton_smallerdetailed(btnBarRegistration);
                designButton_smallerdetailed(btnTrackCase);
                designButton_smallerdetailed(btnScheduleHearing);
                designButton_smallerdetailed(btnApproveDocument);
                designButton_smallerdetailed(btnApproveJudgement);
                designButton_smallerdetailed(btnViewNotifications);
                styleLogoutButton(btnLogout);

                btnLogout.setOnAction(e -> {
                    System.out.println("Logging out...");

                    system.start(primaryStage);
                });

                layoutinner.getChildren().addAll(btnCaseFiling, btnBarRegistration, btnTrackCase,
                        btnScheduleHearing,
                        btnApproveDocument, btnApproveJudgement, btnViewNotifications, btnLogout);
            }

            // Set outer layout style
            layoutouter.setStyle(
                    "-fx-padding:10px 80px; "
                            + "-fx-alignment: center; "
                            + "-fx-background-size: stretch; "
                            + "-fx-background-position: center center; "
                            + "-fx-background-repeat: no-repeat; "
                            + "-fx-background-image: url('file:///E:/Github%20Projects/JusticeFlow/CourtsManagementSystem/lib/resources/img(3).jpeg'); "
                            + "-fx-background-color: rgba(0, 0, 0, 0.35); "
                            + "-fx-blur: 8px; "
                            + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 40, 0.4, 0, 25); ");

            // Inner layout (Softer corners and gradient background)
            layoutinner.setStyle(
                    """
                                    -fx-alignment: center;
                                    -fx-spacing: 5px;
                                    -fx-border-radius: 20px;
                                    -fx-background-radius: 20px;
                                    -fx-padding: 15px;
                                    -fx-border-color: rgba(255, 255, 255, 0.3);
                                    -fx-border-width: 3px;
                                    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.35), 20, 0.1, 0, 15);
                                    -fx-background-color: linear-gradient(to top, rgba(255, 255, 255,0.7), rgb(255, 255, 255,0.3));

                            """);

            // Main layout (Larger rounded corners with gradient and shadow)
            layout.setStyle(
                    """
                                    -fx-alignment: center;
                                    -fx-spacing: 30px;
                                    -fx-background-size: cover;
                                    -fx-background-position: center center;
                                    -fx-background-radius: 30px;
                                    -fx-border-radius: 30px;

                                    -fx-border-color: rgba(255, 255, 255, 0.4);
                                    -fx-border-width: 3px;
                                    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 25, 0.1, 0, 20);
                                    -fx-background-color: rgba(0, 0, 0, 0.55);
                                    -fx-blur: 10px;
                                    -fx-transition: all 0.5s ease-in-out;
                                    -fx-background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0.2), rgba(0, 0, 0, 0.3));
                            """);

            // Create the outer HBox layout
            HBox mainLayout = new HBox();
            layout.getChildren().addAll(titleLabel, mainLayout);
            mainLayout.setStyle("-fx-alignment: center; -fx-spacing: 0px; -fx-padding:0px 30px 5px 30px;");

            // Left side layout with fade-in animation
            // Create a Pane
            Pane leftLayout = new Pane();

            // Set Background with rounded corners
            leftLayout.setBackground(new Background(
                    new BackgroundImage(
                            new Image(
                                    "file:///E:/Github%20Projects/JusticeFlow/CourtsManagementSystem/lib/resources/2.png"),
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.CENTER,
                            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false))));
            leftLayout.setStyle(
                    """
                            -fx-border-radius: 15px;
                            -fx-border-color: rgba(255, 255, 255, 0.3);
                            -fx-border-width: 3px;
                            """);

            // Fade-in animation for leftLayout
            Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(leftLayout.opacityProperty(), 0)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(leftLayout.opacityProperty(), 1)));
            fadeIn.setCycleCount(1); // Play only once
            fadeIn.play();

            // Right side layout (Content)
            VBox rightLayout = new VBox();
            rightLayout.setStyle(
                    """
                                    -fx-alignment: center;
                                    -fx-spacing: 5px;
                                    -fx-border-radius: 20px;
                                    -fx-background-radius: 20px;
                                    -fx-padding: 25px;


                            """);

            // Add layoutinner to rightLayout
            rightLayout.getChildren().add(layoutinner);
            // HBox.setHgrow(rightLayout, Priority.ALWAYS);
            HBox.setHgrow(leftLayout, Priority.ALWAYS);
            // Add leftLayout and rightLayout to mainLayout
            mainLayout.getChildren().addAll(leftLayout, rightLayout);

            // Apply animation to the rightLayout (slide-in effect)
            TranslateTransition slideIn = new TranslateTransition();
            slideIn.setNode(rightLayout);
            slideIn.setDuration(Duration.seconds(1));
            slideIn.setToX(0); // Start from off-screen
            slideIn.setFromX(-500); // Move from the left side
            // slideIn.setInterpolator(Interpolator.EASE_IN_OUT);
            slideIn.play();

            // Add mainLayout to the outer layout (layoutouter)
            layoutouter.getChildren().add(layout);

            // Set up the scene and show the stage
            primaryStage.setScene(scene);
            primaryStage.show();

        }

        public void Register() {
            // Main VBox layout with minimal padding and light background
            VBox layout = new VBox(20);
            layout.setStyle("-fx-alignment: center; "
                    + "-fx-padding: 20px; "
                    + "-fx-background-radius: 15px; "
                    + "-fx-border-radius: 15px; "
                    + "-fx-border-color: #e0e0e0; "
                    + "-fx-border-width: 0px;"
                    + "-fx-background-image: url('file:///E:/Github%20Projects/JusticeFlow/CourtsManagementSystem/lib/resources/91674.jpg'); "
                    + "-fx-background-size: cover; " // Ensures the image covers the entire area
                    + "-fx-background-position: center center; " // Centers the image
                    + "-fx-background-repeat: no-repeat; "); // Prevents repeating the image
            // Title Label with modern font style
            Label titleLabel = new Label("Register a New User");
            titleLabel.setStyle(
                    "-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #f0f0f0; -fx-padding: 10px 0;");

            // Input Fields with a fixed width and minimal style
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter username");
            usernameField.setStyle(BASE_STYLE);
            usernameField.setMaxWidth(300);

            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Enter password");
            passwordField.setStyle(BASE_STYLE);
            passwordField.setMaxWidth(300);

            TextField emailField = new TextField();
            emailField.setPromptText("Enter email");
            emailField.setStyle(BASE_STYLE);
            emailField.setMaxWidth(300);

            TextField firstNameField = new TextField();
            firstNameField.setPromptText("Enter first name");
            firstNameField.setStyle(BASE_STYLE);
            firstNameField.setMaxWidth(300);

            TextField lastNameField = new TextField();
            lastNameField.setPromptText("Enter last name");
            lastNameField.setStyle(BASE_STYLE);
            lastNameField.setMaxWidth(300);

            DatePicker dateOfBirthPicker = new DatePicker();
            dateOfBirthPicker.setPromptText("Select date of birth");
            dateOfBirthPicker.setStyle(BASE_STYLE);
            dateOfBirthPicker.setMaxWidth(300);

            ComboBox<String> genderComboBox = new ComboBox<>();
            genderComboBox.getItems().addAll("Male", "Female", "Other");
            genderComboBox.setPromptText("Select gender");
            genderComboBox.setStyle(BASE_STYLE);
            genderComboBox.setMaxWidth(300);

            TextField phoneNumberField = new TextField();
            phoneNumberField.setPromptText("Enter phone number");
            phoneNumberField.setStyle(BASE_STYLE);
            phoneNumberField.setMaxWidth(300);

            ComboBox<String> roleComboBox = new ComboBox<>();
            roleComboBox.getItems().addAll("Judge", "Lawyer", "Clerk", "Court Administrator", "Registrar",
                    "Probation Officer", "Client", "Witness", "Juror");
            roleComboBox.setPromptText("Select role");
            roleComboBox.setStyle(BASE_STYLE);
            roleComboBox.setMaxWidth(300);

            // Dynamic Fields (will be shown based on role selection)
            TextField courtIDField = new TextField();
            courtIDField.setPromptText("Enter Court ID");
            courtIDField.setVisible(false);
            courtIDField.setStyle(BASE_STYLE);
            courtIDField.setMaxWidth(300);

            TextField licenseField = new TextField();
            licenseField.setPromptText("Enter License Number");
            licenseField.setVisible(false);
            licenseField.setStyle(BASE_STYLE);
            licenseField.setMaxWidth(300);

            TextField barAssociationIDField = new TextField();
            barAssociationIDField.setPromptText("Enter Bar Association ID");
            barAssociationIDField.setVisible(false);
            barAssociationIDField.setStyle(BASE_STYLE);
            barAssociationIDField.setMaxWidth(300);

            DatePicker dateOfAppointmentPicker = new DatePicker();
            dateOfAppointmentPicker.setPromptText("Select Date of Appointment");
            dateOfAppointmentPicker.setVisible(false);
            dateOfAppointmentPicker.setStyle(BASE_STYLE);
            dateOfAppointmentPicker.setMaxWidth(300);

            DatePicker dateOfHiringPicker = new DatePicker();
            dateOfHiringPicker.setPromptText("Select Date of Hiring");
            dateOfHiringPicker.setVisible(false);
            dateOfHiringPicker.setStyle(BASE_STYLE);
            dateOfHiringPicker.setMaxWidth(300);

            TextField addressField = new TextField();
            addressField.setPromptText("Enter Address");
            addressField.setVisible(false);
            addressField.setStyle(BASE_STYLE);
            addressField.setMaxWidth(300);

            // Update visibility of dynamic fields based on role selection
            roleComboBox.setOnAction(e -> {
                String role = roleComboBox.getValue();
                courtIDField.setVisible(false);
                licenseField.setVisible(false);
                barAssociationIDField.setVisible(false);
                dateOfAppointmentPicker.setVisible(false);
                dateOfHiringPicker.setVisible(false);
                addressField.setVisible(false);

                if (role.equalsIgnoreCase("Judge") || role.equalsIgnoreCase("Court Administrator") ||
                        role.equalsIgnoreCase("Registrar") || role.equalsIgnoreCase("Probation Officer")) {
                    courtIDField.setVisible(true);
                }

                if (role.equalsIgnoreCase("Lawyer")) {
                    licenseField.setVisible(true);
                    barAssociationIDField.setVisible(true);
                }

                if (role.equalsIgnoreCase("Judge")) {
                    dateOfAppointmentPicker.setVisible(true);
                }

                if (role.equalsIgnoreCase("Registrar") || role.equalsIgnoreCase("Court Administrator")) {
                    dateOfHiringPicker.setVisible(true);
                }

                if (role.equalsIgnoreCase("Client") || role.equalsIgnoreCase("Witness")
                        || role.equalsIgnoreCase("Juror")) {
                    addressField.setVisible(true);
                }
            });
            Label statusLabel = new Label("");
            statusLabel.setStyle(
                    "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #f0f0f0; -fx-padding: 10px 0;");

            // Register Button with minimalistic style
            Button registerButton = new Button("Register");
            registerButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; "
                    + "-fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-cursor: hand;");
            registerButton.setOnAction(e -> {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                String email = emailField.getText().trim();
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                LocalDate dateOfBirth = dateOfBirthPicker.getValue();
                String gender = genderComboBox.getValue();
                String phoneNumber = phoneNumberField.getText().trim();
                String role = roleComboBox.getValue();

                // Retrieve dynamic inputs
                Integer courtID = courtIDField.isVisible() ? Integer.parseInt(courtIDField.getText().trim()) : null;
                String license = licenseField.isVisible() ? licenseField.getText().trim() : null;
                Integer barAssociationID = barAssociationIDField.isVisible()
                        ? Integer.parseInt(barAssociationIDField.getText().trim())
                        : null;

                String dateOfAppointment = dateOfAppointmentPicker.getValue() != null
                        ? dateOfAppointmentPicker.getValue().toString()
                        : null;
                String dateOfHiring = dateOfHiringPicker.getValue() != null ? dateOfHiringPicker.getValue().toString()
                        : null;
                String address = addressField.isVisible() ? addressField.getText().trim() : null;

                // Database Interaction
                DatabaseHandler dbHandler = new DatabaseHandler();
                int isUserCreated = dbHandler.createUserWithRole(username, password, role, email, phoneNumber,
                        firstName, lastName, dateOfBirth != null ? dateOfBirth.toString() : null, gender,
                        dateOfAppointment, dateOfHiring, license, address, courtID, barAssociationID);

                if (isUserCreated != -1) {
                    statusLabel.setText("User created successfully!");
                    statusLabel.setStyle("-fx-text-fill: green;");
                    // Proceed to next steps if needed
                } else {
                    statusLabel.setText("User creation failed.");
                    statusLabel.setStyle("-fx-text-fill: red;");
                }
            });
            // Back Button
            Button backButton = new Button("Back");
            backButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 16px; "
                    + "-fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-cursor: hand;");
            backButton.setOnAction(e -> start(primaryStage));

            // Layout for buttons
            HBox buttonLayout = new HBox(20);
            buttonLayout.setStyle("-fx-padding:0px 20px; -fx-alignment: center;");
            designButton(registerButton);
            designButton(backButton);
            buttonLayout.getChildren().addAll(registerButton, backButton);

            // Scrollable Layout for form fields
            ScrollPane formScrollPane = new ScrollPane();
            formScrollPane.setContent(new VBox(25));
            VBox formLayout = new VBox(15);
            formLayout.setStyle(
                    "-fx-alignment: center;-fx-padding:10px 10px; -fx-spacing: 10px; -fx-background-color: black;");

            formLayout.getChildren().addAll(
                    firstNameField, lastNameField, emailField, phoneNumberField, usernameField, passwordField,
                    genderComboBox,
                    roleComboBox, dateOfBirthPicker, courtIDField, licenseField, barAssociationIDField,
                    dateOfAppointmentPicker, dateOfHiringPicker, addressField);
            formScrollPane.setContent(formLayout);
            formScrollPane.setFitToWidth(true);
            formScrollPane.setStyle(
                    "-fx-padding:0px 20px;-fx-alignment: center; -fx-spacing: 0px; -fx-border-radius: 20px;-fx-background-color: black;-fx-background-radius:20px;");

            // Outer Layout (title + form)
            VBox outerLayout = new VBox(15);
            outerLayout.setStyle("-fx-alignment: center; -fx-background-color: rgba(0,0,0,0.4); "
                    + "-fx-background-radius:20px;-fx-padding: 20px; -fx-border-radius: 20px; -fx-border-color: #e0e0e0; -fx-border-width: 0px;");
            outerLayout.getChildren().addAll(titleLabel, formScrollPane, statusLabel, buttonLayout);

            layout.getChildren().addAll(outerLayout);

            // Scene Setup with a fixed width and height
            Scene registerScene = new Scene(layout, 1000, 700);
            primaryStage.setScene(registerScene);
            primaryStage.show();
        }

        public void GUI_Login() {
            // Create a new VBox layout

            VBox layout = new VBox(25); // Vertical box layout with spacing of 25
            layout.setStyle("-fx-padding:0px 300px;"
                    + " -fx-alignment: center;"
                    + "-fx-background-image: url('file:///E:/Github%20Projects/JusticeFlow/CourtsManagementSystem/lib/resources/login.jpg'); "
                    + "-fx-background-size: cover; " // Ensures the image covers the entire area
                    + "-fx-background-position: center center; " // Centers the image
                    + "-fx-background-repeat: no-repeat; "); // Prevents repeating the image

            VBox layout1 = new VBox(30); // Vertical box layout with spacing of 25
            layout1.setStyle("-fx-padding: 50px; -fx-alignment: center;-fx-background-color: rgba(0,0,0,0.3);");
            // Title
            Label titleLabel = new Label("Login here!");
            titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;-fx-text-fill: #f0f0f0;");

            Label loginLabel = new Label("Please enter your credentials to log in.");
            loginLabel.setStyle(
                    "-fx-font-size: 16px; -fx-text-fill: #cccccc; -fx-font-family: 'Segoe UI', sans-serif; -fx-text-alignment: center;");

            // Username and Password Fields
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter your username");

            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Enter your password");

            // Login Button
            Button loginButton = new Button("Login");
            Label statusLabel = new Label(); // For displaying status messages

            // Login Button Action
            loginButton.setOnAction(e -> {
                String username = usernameField.getText();
                String password = passwordField.getText();

                // Assume dbHandler.Login(username, password) checks login credentials and
                // returns user ID
                loggedinID = dbHandler.Login(username, password);

                if (loggedinID != null) {
                    user = dbHandler.getUserById(loggedinID);
                    statusLabel.setText("Login Successful!");
                    statusLabel.setStyle("-fx-text-fill: green;");
                    GUI_startmenu(primaryStage);
                    return;
                    // CLI_Menu loggedin = new CLI_Menu(user,scanner); // Replace with actual JavaFX
                    // menu scene
                } else {
                    statusLabel.setText("Login Failed. Please try again.");
                    statusLabel.setStyle("-fx-text-fill: red;");
                }
            });

            // Back Button
            Button backButton = new Button("Back");
            backButton.setOnAction(e -> {
                // Navigate back to the main menu
                start(primaryStage); // Call the main menu method
            });

            HBox layout3 = new HBox(30);
            layout3.setStyle("-fx-padding: 20px 30px; "
                    + "-fx-alignment: center; ");
            designButton(loginButton);
            designButton(backButton);
            designTextField(usernameField);
            designPasswordField(passwordField);
            // Add all elements to the layout
            layout3.getChildren().addAll(loginButton, backButton);
            layout1.getChildren().addAll(titleLabel, loginLabel, usernameField, passwordField, layout3, statusLabel);
            layout.getChildren().add(layout1);
            // Create and set a new scene
            Scene loginScene = new Scene(layout, 1100, 600);
            primaryStage.setScene(loginScene);
            primaryStage.show();
        }

    }

    @Override
    public void start(Stage primaryStage) {

        CourtsManagementSystem system = new CourtsManagementSystem();
        Scanner scanner = new Scanner(System.in);
        GUI_Menu Gui = new GUI_Menu(system, primaryStage, scanner);

        // Title Label
        Label titleLabel1 = new Label("Welcome to Courts Management System");
        titleLabel1.setStyle("-fx-font-size: 45px; -fx-font-weight: bold; -fx-text-fill: #f0f0f0;");

        Label titleLabel2 = new Label("A PROJECT BY JUSTICE FLOW!");
        titleLabel2.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #d9dadb;");

        // Buttons
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Button exitButton = new Button("Exit");

        // Button Actions
        loginButton.setOnAction(e -> Gui.GUI_Login());
        registerButton.setOnAction(e -> Gui.Register());
        exitButton.setOnAction(e -> {
            System.out.println("Exiting program...");
            primaryStage.close();
        });

        // Layout
        VBox layout = new VBox(25); // Outer VBox with spacing of 25px
        VBox innerbox2 = new VBox(25); // Outer VBox with spacing of 25px
        HBox innerbox1 = new HBox(20); // Inner VBox with spacing of 25px

        // Add children to the innerbox
        Gui.designButton(loginButton);
        Gui.designButton(registerButton);
        Gui.designButton(exitButton);
        innerbox1.getChildren().addAll(loginButton, registerButton, exitButton);

        // Style innerbox to center its content and add padding
        // Horizontal layout with 20px spacing
        innerbox1.setStyle("-fx-padding: 20px 30px; "
                + "-fx-alignment: center; "
                + "-fx-background-color: rgba(0,0,0,0.3); "
                + "-fx-border-radius: 20px; "
                + "-fx-border-color: #000000; "
                + "-fx-border-width: 0px; "
                + "-fx-background-radius: 20px;");
        innerbox2.setStyle("-fx-padding: 100px 30px 30px 30px; "
                + "-fx-alignment: center; "
                + "-fx-background-color: rgba(0,0,0,0.3); "
                + "-fx-border-radius: 20px; "
                + "-fx-border-color: #000000; "
                + "-fx-border-width: 0px; "
                + "-fx-background-radius: 20px;");
        // Style outer layout to center innerbox within the window and add padding
        innerbox2.getChildren().addAll(titleLabel1, titleLabel2, innerbox1);
        layout.getChildren().addAll(innerbox2);
        layout.setStyle("-fx-padding: 50px; "
                + "-fx-alignment: center; "
                + "-fx-fill-width: true; "
                + "-fx-background-image: url('file:///E:/Github%20Projects/JusticeFlow/CourtsManagementSystem/lib/resources/awesome%207.jpg');"
                + "-fx-background-size: cover; " // Ensures the image covers the entire area
                + "-fx-background-position: center center; " // Centers the image
                + "-fx-background-repeat: no-repeat; "); // Prevents repeating the image

        // Scene
        Scene scene = new Scene(layout, 1100, 600);

        primaryStage.setScene(scene);

        // Stage setup
        primaryStage.setTitle("Courts Management System");
        primaryStage.show();
    }

    public void GUI_main(String[] args) {
        launch(args);
    }

    public static void CLI_main(String[] args) {
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
                    system.CLI_Register();
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

    public static void main(String[] args) {
        // CLI_main(args);
        launch(args);
    }

}
