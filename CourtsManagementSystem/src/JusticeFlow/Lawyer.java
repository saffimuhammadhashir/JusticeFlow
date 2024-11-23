package JusticeFlow;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import JusticeFlow.CourtsManagementSystem.GUI_Menu;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.nio.file.*;
import java.awt.*;
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lawyer extends User {
    private int lawyerID;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private Date dateOfBirth;
    private String gender;
    private int barAssociationID;
    private String email;
    private String phoneNumber;
    private int userID;

    public Lawyer() {
        super();
    }

    public Lawyer(
            int userID,
            String username,
            String password,
            String role,
            String email,
            String phoneNumber,
            boolean activate,
            int lawyerID,
            String firstName,
            String lastName,
            String licenseNumber,
            Date dateOfBirth,
            String gender,
            int barAssociationID) {
        // Call the superclass constructor
        super(userID, username, password, role, email, phoneNumber, activate);

        // Initialize Lawyer-specific fields
        this.lawyerID = lawyerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.barAssociationID = barAssociationID;
        this.userID = userID;
    }

    public int getLawyerID() {
        return lawyerID;
    }

    public void setLawyerID(int lawyerID) {
        this.lawyerID = lawyerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBarAssociationID() {
        return barAssociationID;
    }

    public void setBarAssociationID(int barAssociationID) {
        this.barAssociationID = barAssociationID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Lawyer {" +
                "lawyerID=" + lawyerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", BarID=" + barAssociationID +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userID=" + userID +
                '}';
    }

    public void FileNewCase(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases) {
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

        String caseStatus = "Pending";

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

        // Create the new Case object
        Case newCase = new Case(
                0, // Assuming ID will be auto-generated
                caseTitle,
                caseType,
                filingDate,
                courtDate,
                plaintiffID,
                defendantID,
                caseStatus,
                lawyerID);

        // Save or update the case in the database
        dbHandler.saveOrUpdateCase(newCase);
        newCase.updateCaseFiles(fileHandler, dbHandler);
        AllCases.add(newCase);
        // Confirmation
        System.out.println("Case has been successfully created.");
    }

    // public void SubmitDocument(Scanner scanner, List<Case> AllCases, FileHandler
    // fileHandler, Stage primaryStage, Scene previousScene) {
    // // Create main layout for the submit document process
    // VBox mainLayout = new VBox(20);
    // mainLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");
    public void FileNewCase(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases, Stage primaryStage,
            GUI_Menu gui) {
        primaryStage.setTitle("File New Case");
        Label PageTitle = new Label("File New Case");
        PageTitle.setStyle("-fx-font-size: 44px;-fx-alignment:center center;");
        // Create a GridPane to hold the UI elements
        VBox outerlayout = new VBox(25);
        HBox horizontalbox = new HBox(25);
        GridPane grid = new GridPane();
        VBox imagebox = new VBox(15);
        imagebox.setStyle(
                "-fx-background-size: contain; "
                        + "-fx-background-position: center; "
                        + "-fx-background-repeat: no-repeat; "
                        + "-fx-background-image: url('file:///E:/Github%20Projects/JusticeFlow/CourtsManagementSystem/lib/resources/pic1.png'); "
                        + "-fx-min-height:200px;"
                        + "-fx-min-width:400px;");
        imagebox.minWidthProperty().bind(grid.widthProperty());
        grid.setStyle(
                "-fx-background-color: rgba(0,0,0,0.8); "
                        + "-fx-background-radius: 20px; "
                        + "-fx-padding: 20px 50px; ");
        outerlayout.setStyle("-fx-alignment:center center;-fx-background-color: #f4f4f9;padding:10px 80px;");
        grid.setVgap(25);
        grid.setHgap(25);
        horizontalbox.setStyle("-fx-spacing:50px;");
        horizontalbox.getChildren().addAll(grid, imagebox);
        outerlayout.getChildren().addAll(PageTitle, horizontalbox);

        // Labels and Input Fields with a minimalistic design
        Label titleLabel = new Label("Case Title:");
        titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;-fx-text-fill: white;");
        TextField titleField = new TextField();
        titleField.setPromptText("Enter case title");
        titleField.setStyle("-fx-font-size: 14px; -fx-background-color: #ffffff; -fx-border-radius: 5px;");

        Label typeLabel = new Label("Case Type:");
        typeLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;-fx-text-fill: white;");
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("Civil", "Criminal", "Family", "Other");
        typeComboBox.setValue("Civil");
        typeComboBox.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #ffffff; -fx-border-radius: 5px;-fx-text-fill: white;");

        Label filingDateLabel = new Label("Filing Date:");
        filingDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;-fx-text-fill: white; ");
        DatePicker filingDatePicker = new DatePicker();
        filingDatePicker.setPromptText("yyyy-MM-dd");

        Label courtDateLabel = new Label("Court Date:");
        courtDateLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;-fx-text-fill: white;");
        DatePicker courtDatePicker = new DatePicker();
        courtDatePicker.setPromptText("yyyy-MM-dd");

        Label plaintiffIDLabel = new Label("Plaintiff ID:");
        plaintiffIDLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;-fx-text-fill: white;");
        TextField plaintiffIDField = new TextField();
        plaintiffIDField.setPromptText("Plaintiff ID");

        Label defendantIDLabel = new Label("Defendant ID:");
        defendantIDLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;-fx-text-fill: white;");
        TextField defendantIDField = new TextField();
        defendantIDField.setPromptText("Defendant ID");

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px;");
        submitButton.setMaxWidth(Double.MAX_VALUE); // Make button stretch to fill width

        Button returnButton = new Button("Close");
        returnButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px;");
        returnButton.setMaxWidth(Double.MAX_VALUE); // Make button stretch to fill width

        Label confirmationLabel = new Label();
        confirmationLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;");

        // Add controls to the grid
        designTextField(titleField);
        designTextField(plaintiffIDField);
        designTextField(defendantIDField);
        designComboBox(typeComboBox);
        designComboBox(typeComboBox);
        grid.add(titleLabel, 0, 0);
        grid.add(titleField, 1, 0);

        grid.add(typeLabel, 0, 1);
        grid.add(typeComboBox, 1, 1);

        grid.add(filingDateLabel, 0, 2);
        grid.add(filingDatePicker, 1, 2);

        grid.add(courtDateLabel, 0, 3);
        grid.add(courtDatePicker, 1, 3);

        grid.add(plaintiffIDLabel, 0, 4);
        grid.add(plaintiffIDField, 1, 4);

        grid.add(defendantIDLabel, 0, 5);
        grid.add(defendantIDField, 1, 5);

        grid.add(returnButton, 0, 6);
        grid.add(submitButton, 1, 6);

        VBox vbox = new VBox(20, outerlayout, confirmationLabel);
        vbox.setStyle("-fx-padding: 20; -fx-background-color: #f4f4f9;"); // Minimal background for vbox
        returnButton.setOnAction(event -> {
            gui.GUI_startmenu(primaryStage);
        });
        // Event handler for the submit button
        submitButton.setOnAction(event -> {
            // Get inputs
            String caseTitle = titleField.getText().isEmpty() ? "Default Case Title" : titleField.getText();
            String caseType = typeComboBox.getValue();
            Date filingDate = convertToDate(filingDatePicker.getValue());
            Date courtDate = convertToDate(courtDatePicker.getValue());
            int plaintiffID = parseID(plaintiffIDField.getText());
            int defendantID = parseID(defendantIDField.getText());

            // Case Status
            String caseStatus = "Pending";

            // Create new Case object (use your Case constructor)
            Case newCase = new Case(
                    AllCases.size() + 1, // ID will be auto-generated
                    caseTitle,
                    caseType,
                    filingDate,
                    courtDate,
                    plaintiffID,
                    defendantID,
                    caseStatus,
                    this.getLawyerID() // assuming lawyerID is 0 for now
            );

            // Simulate saving the case to the database
            dbHandler.saveOrUpdateCase(newCase);
            newCase.updateCaseFiles(fileHandler, dbHandler); // Uncomment when FileHandler is available
            AllCases.add(newCase); // Add the new case to the list (AllCases is a List)

            confirmationLabel.setText("Case has been successfully created.");
        });

        // Create the Scene and set it to the Stage
        Scene scene = new Scene(vbox, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private java.sql.Date convertToDate(java.time.LocalDate localDate) {
        // If the date is null, return the current date
        if (localDate == null) {
            return new java.sql.Date(System.currentTimeMillis()); // Default to current date
        }
        return java.sql.Date.valueOf(localDate); // Convert LocalDate to java.sql.Date
    }

    private Date getDateFromString(String dateInput) {
        if (dateInput.isEmpty()) {
            return new Date(System.currentTimeMillis()); // default to current date
        }
        try {
            return java.sql.Date.valueOf(dateInput);
        } catch (IllegalArgumentException e) {
            return new Date(System.currentTimeMillis()); // return current date on invalid input
        }
    }

    private int parseID(String idInput) {
        return idInput.isEmpty() ? 0 : Integer.parseInt(idInput); // default to 0 if empty
    }

    public void showWitness(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            List<Slot> AllSlots, List<Judge> AllJudges,
            List<Witness> AllWitnesses, List<Court> AllCourts,
            Stage primaryStage, Scene previousScene, Case cases) {

        Label titleLabel = new Label("Select Witness over here!");
        titleLabel.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333333; -fx-padding: 20px 0; -fx-alignment: center;");

        // ScrollPane containing the case list
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setFitToWidth(true);
        formScrollPane.setStyle("-fx-background-color: #f4f4f9;");

        VBox formLayout = new VBox(20); // Increased spacing for clarity
        formLayout.setStyle("-fx-padding: 20px;");

        // Iterate over all cases and add only pending cases
        for (Witness w : AllWitnesses) {
            // if (file.getStatus() == 0) {
            // Create a GridPane for each case
            GridPane eachCase = new GridPane();
            eachCase.setHgap(10); // Horizontal gap between columns
            eachCase.setVgap(10); // Vertical gap between rows
            // eachCase.setStyle(
            // "-fx-padding: 10px; -fx-background-color: #ffffff; -fx-border-radius: 10px;
            // -fx-effect: innershadow(gaussian, #000000, 5, 0.5, 0, 0);");
            eachCase.setStyle(
                    "-fx-padding: 20px; " +
                            "-fx-background-color: #f9f9f9; " +
                            "-fx-border-color: #dcdcdc; " +
                            "-fx-border-width: 1px; " +
                            "-fx-border-radius: 15px; " +
                            "-fx-background-radius: 15px; " +
                            "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 10, 0, 2, 2);");
            // Case Title Label
            Label witnessName = new Label(w.getFirstName() + " " + w.getLastName());
            witnessName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");

            Label witnessEmail = new Label("Email: " + w.getEmail());
            witnessEmail.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

            Label witnessAddress = new Label("Address: " + w.getAddress());
            witnessAddress.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

            // Add the case title to the first column, first row
            GridPane.setConstraints(witnessName, 0, 0);
            GridPane.setConstraints(witnessEmail, 0, 1);
            GridPane.setConstraints(witnessAddress, 0, 2);
            eachCase.getChildren().add(witnessName);
            eachCase.getChildren().add(witnessEmail);
            eachCase.getChildren().add(witnessAddress);

            // Approve and Reject Buttons
            Button approveButton = new Button("Select");
            approveButton.setStyle(
                    "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5px 15px; -fx-border-radius: 5px;");

            // Button Actions (for demonstration purposes, implement logic later)
            approveButton.setOnAction(e -> {
                try {
                    // dbHandler.updateFileDetails(cases.getCaseID(), file.getFileName(),
                    // file.getFileHash(), false,
                    // true);
                    boolean val = false;
                    Slot s = new Slot();
                    for (Slot sl : AllSlots) {
                        if (sl.getCaseID() != null) {
                            if (sl.getCaseID() == cases.getCaseID()) {
                                s = sl;
                                val = true;
                            }
                        }
                    }

                    if (val == true) {
                        dbHandler.updateWitness(w, s);
                        s.setWitnessID(w.getWitnessID());
                        dbHandler.updateOrInsertSlots(AllSlots);
                    }
                } catch (NumberFormatException ex) {
                    Label invalidInputLabel = new Label("Invalid input for Witness Number.");
                    invalidInputLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                    formLayout.getChildren().add(invalidInputLabel);
                }
            });

            // Add buttons to GridPane, starting from row 1
            GridPane.setConstraints(approveButton, 0, 3); // Place in column 1, row 1

            eachCase.getChildren().addAll(approveButton);

            // Add the GridPane to the formLayout
            formLayout.getChildren().add(eachCase);
            // }

        }
        // Set the VBox into the ScrollPane and display it
        formScrollPane.setContent(formLayout);
        Button returnButton = new Button("Close");
        returnButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px;");
        returnButton.setMaxWidth(Double.MAX_VALUE); // Make button stretch to fill width
        returnButton.setOnAction(e -> {
            primaryStage.setScene(previousScene);
        });
        // Setting up the scene with the scrollable content
        VBox rootLayout = new VBox(20);
        rootLayout.getChildren().addAll(titleLabel, formScrollPane, returnButton);
        rootLayout.setStyle("-fx-background-color: #f4f4f9; -fx-padding: 20px;");

        Scene registerScene = new Scene(rootLayout, 1000, 700);
        primaryStage.setScene(registerScene);
        primaryStage.show();
    }

    public void SubmitDocument(Scanner scanner, List<Case> AllCases, FileHandler fileHandler, Stage primaryStage,
            Scene previousScene) {

        // Create main layout for the submit document process
        VBox mainLayout = new VBox(20);
        mainLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // Title label
        Label titleLabel = new Label("Submit Document for Case");
        titleLabel.setStyle("-fx-text-fill: black; -fx-font-size: 22px; -fx-font-weight: bold;");
        mainLayout.getChildren().add(titleLabel);

        // ScrollPane containing the case list
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setFitToWidth(true);
        formScrollPane.setStyle("-fx-background-color: #f4f4f9;");

        VBox formLayout = new VBox(20); // Increased spacing for clarity
        formLayout.setStyle("-fx-padding: 20px;");

        // Iterate over all cases and add only pending cases
        for (Case cases : AllCases) {
            if (cases.getLawyerId() == lawyerID) {

                // Create a GridPane for each case
                GridPane eachCase = new GridPane();
                eachCase.setHgap(10); // Horizontal gap between columns
                eachCase.setVgap(10); // Vertical gap between rows
                // eachCase.setStyle(
                // "-fx-padding: 10px; -fx-background-color: #ffffff; -fx-border-radius: 10px;
                // -fx-effect: innershadow(gaussian, #000000, 5, 0.5, 0, 0);");
                eachCase.setStyle(
                        "-fx-padding: 20px; " +
                                "-fx-background-color: #f9f9f9; " +
                                "-fx-border-color: #dcdcdc; " +
                                "-fx-border-width: 1px; " +
                                "-fx-border-radius: 15px; " +
                                "-fx-background-radius: 15px; " +
                                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 10, 0, 2, 2);");
                // Case Title Label
                Label caseName = new Label(cases.getCaseTitle());
                caseName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label casetype = new Label("Case Type: " + cases.getCaseType());
                casetype.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label caseplaintiff = new Label("Plaintiff ID: " + cases.getPlaintiffID());
                caseplaintiff.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label casedefendant = new Label("Defendant ID: " + cases.getDefendantID());
                casedefendant.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label CaseFiling = new Label("Filing Date ID: " + cases.getFilingDate());
                CaseFiling.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: red;");

                // Add the case title to the first column, first row
                GridPane.setConstraints(caseName, 0, 0);
                GridPane.setConstraints(casetype, 0, 1);
                GridPane.setConstraints(caseplaintiff, 3, 0);
                GridPane.setConstraints(casedefendant, 3, 1);
                GridPane.setConstraints(CaseFiling, 5, 0);
                eachCase.getChildren().add(caseName);
                eachCase.getChildren().add(casetype);
                eachCase.getChildren().add(caseplaintiff);
                eachCase.getChildren().add(casedefendant);
                eachCase.getChildren().add(CaseFiling);

                // Approve and Reject Buttons
                Button approveButton = new Button("Select");
                approveButton.setStyle(
                        "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5px 15px; -fx-border-radius: 5px;");

                // Button Actions (for demonstration purposes, implement logic later)
                approveButton.setOnAction(e -> {
                    // showandscheduleslots(dbHandler, fileHandler, AllCases, AllSlots, AllJudges,
                    // AllWitnesses, AllCourts,
                    // primaryStage, gui, cases);
                    // cases.setCaseStatus("Opened");
                    // dbHandler.saveOrUpdateCase(cases);

                    // Open file dialog to select a file
                    File selectedFile = fileHandler.openFileDialog();
                    if (selectedFile != null) {
                        try {
                            // Generate file hash
                            String fileHash = fileHandler.getFileHash(selectedFile.getAbsolutePath());

                            CaseFile my_file = new CaseFile(selectedFile.getAbsolutePath(), fileHash);
                            cases.addFile(my_file);
                            System.out.println("File added to case, waiting for Registrar to approve.");

                            // // Update the database with file details
                            DatabaseHandler dbHandler = new DatabaseHandler();
                            dbHandler.saveFileDetails(cases.getCaseID(), selectedFile.getAbsolutePath(), fileHash,
                                    false);
                            System.out.println("File Added in Database.");

                            // Show confirmation message in GUI
                            Label successLabel = new Label(
                                    "File submitted successfully! Waiting for Registrar approval.");
                            successLabel.setStyle("-fx-text-fill: #4CAF50; -fx-font-size: 16px; -fx-font-weight:bold;");
                            mainLayout.getChildren().add(successLabel);

                        } catch (IOException | NoSuchAlgorithmException ex) {
                            ex.printStackTrace();
                            Label errorLabel = new Label("Error while processing the file.");
                            errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                            mainLayout.getChildren().add(errorLabel);
                        }
                    } else {
                        Label noFileLabel = new Label("No file selected.");
                        noFileLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        mainLayout.getChildren().add(noFileLabel);
                    }

                });

                // Add buttons to GridPane, starting from row 1
                GridPane.setConstraints(approveButton, 0, 3); // Place in column 1, row 1
                // GridPane.setConstraints(rejectButton, 1, 3); // Place in column 2, row 1

                eachCase.getChildren().addAll(approveButton);

                // Add the GridPane to the formLayout
                formLayout.getChildren().add(eachCase);

                // Add the case to the list of pending cases
                // PendingCases.add(cases);
            }
        }
        // Set the VBox into the ScrollPane and display it
        formScrollPane.setContent(formLayout);

        Button returnButton = new Button(
                "Close");
        returnButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px;");
        returnButton.setMaxWidth(Double.MAX_VALUE); // Make
                                                    // button
                                                    // stretch
                                                    // to
                                                    // fill
                                                    // width
        returnButton.setOnAction(e -> {
            // Switch back to the previous scene
            primaryStage.setScene(previousScene);
        });
        // Setting up the scene with the scrollable content
        VBox rootLayout = new VBox(
                20);
        rootLayout.getChildren().addAll(titleLabel, formScrollPane, returnButton);
        rootLayout.setStyle("-fx-background-color: #f4f4f9; -fx-padding: 20px;");

        Scene registerScene = new Scene(rootLayout, 1000, 700);
        primaryStage.setScene(registerScene);
        primaryStage.show();
    }

    public void scheduleWitness(DatabaseHandler dbHandler, Scanner scanner, List<Case> AllCases, List<Slot> AllSlots,
            List<Judge> AllJudges,
            List<Court> AllCourts, List<Witness> AllWitnesses, FileHandler fileHandler, Stage primaryStage,
            Scene previousScene) {

        // Create main layout for the submit document process
        VBox mainLayout = new VBox(20);
        mainLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // Title label
        Label titleLabel = new Label("Schedule Witness for Case");
        titleLabel.setStyle("-fx-text-fill: black; -fx-font-size: 22px; -fx-font-weight: bold;");
        mainLayout.getChildren().add(titleLabel);

        // ScrollPane containing the case list
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setFitToWidth(true);
        formScrollPane.setStyle("-fx-background-color: #f4f4f9;");

        VBox formLayout = new VBox(20); // Increased spacing for clarity
        formLayout.setStyle("-fx-padding: 20px;");

        // Iterate over all cases and add only lawyer's cases
        for (Case cases : AllCases) {
            if (cases.getLawyerId() == lawyerID) {

                // Create a GridPane for each case
                GridPane eachCase = new GridPane();
                eachCase.setHgap(10); // Horizontal gap between columns
                eachCase.setVgap(10); // Vertical gap between rows
                // eachCase.setStyle(
                // "-fx-padding: 10px; -fx-background-color: #ffffff; -fx-border-radius: 10px;
                // -fx-effect: innershadow(gaussian, #000000, 5, 0.5, 0, 0);");
                eachCase.setStyle(
                        "-fx-padding: 20px; " +
                                "-fx-background-color: #f9f9f9; " +
                                "-fx-border-color: #dcdcdc; " +
                                "-fx-border-width: 1px; " +
                                "-fx-border-radius: 15px; " +
                                "-fx-background-radius: 15px; " +
                                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 10, 0, 2, 2);");

                // Case Title Label
                Label caseName = new Label(cases.getCaseTitle());
                caseName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label casetype = new Label("Case Type: " + cases.getCaseType());
                casetype.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label caseplaintiff = new Label("Plaintiff ID: " + cases.getPlaintiffID());
                caseplaintiff.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label casedefendant = new Label("Defendant ID: " + cases.getDefendantID());
                casedefendant.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label CaseFiling = new Label("Filing Date ID: " + cases.getFilingDate());
                CaseFiling.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: red;");

                // Add the case title to the first column, first row
                GridPane.setConstraints(caseName, 0, 0);
                GridPane.setConstraints(casetype, 0, 1);
                GridPane.setConstraints(caseplaintiff, 3, 0);
                GridPane.setConstraints(casedefendant, 3, 1);
                GridPane.setConstraints(CaseFiling, 5, 0);
                eachCase.getChildren().add(caseName);
                eachCase.getChildren().add(casetype);
                eachCase.getChildren().add(caseplaintiff);
                eachCase.getChildren().add(casedefendant);
                eachCase.getChildren().add(CaseFiling);

                // Approve and Reject Buttons
                Button approveButton = new Button("Select");
                approveButton.setStyle(
                        "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5px 15px; -fx-border-radius: 5px;");

                // Button Actions (for demonstration purposes, implement logic later)
                approveButton.setOnAction(e -> {
                    showWitness(dbHandler, fileHandler, AllCases, AllSlots, AllJudges,
                            AllWitnesses, AllCourts,
                            primaryStage, primaryStage.getScene(), cases);
                    // cases.setCaseStatus("Opened");
                    dbHandler.saveOrUpdateCase(cases);

                });

                // Add buttons to GridPane, starting from row 1
                GridPane.setConstraints(approveButton, 0, 3); // Place in column 1, row 1
                // GridPane.setConstraints(rejectButton, 1, 3); // Place in column 2, row 1

                eachCase.getChildren().addAll(approveButton);

                // Add the GridPane to the formLayout
                formLayout.getChildren().add(eachCase);

                // Add the case to the list of pending cases
                // PendingCases.add(cases);
            }
        }
        // Set the VBox into the ScrollPane and display it
        formScrollPane.setContent(formLayout);

        Button returnButton = new Button(
                "Close");
        returnButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px;");
        returnButton.setMaxWidth(Double.MAX_VALUE); // Make
                                                    // button
                                                    // stretch
                                                    // to
                                                    // fill
                                                    // width
        returnButton.setOnAction(e -> {
            // Switch back to the previous scene
            primaryStage.setScene(previousScene);
        });
        // Setting up the scene with the scrollable content
        VBox rootLayout = new VBox(
                20);
        rootLayout.getChildren().addAll(titleLabel, formScrollPane, returnButton);
        rootLayout.setStyle("-fx-background-color: #f4f4f9; -fx-padding: 20px;");

        Scene registerScene = new Scene(rootLayout, 1000, 700);
        primaryStage.setScene(registerScene);
        primaryStage.show();

        // // Main layout container
        // VBox mainLayout = new VBox(20);
        // mainLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // // Title label
        // Label titleLabel = new Label("Schedule a Witness");
        // titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 22px;
        // -fx-font-weight: bold;");
        // mainLayout.getChildren().add(titleLabel);

        // // Scrollable ViewBox for displaying lawyer's cases
        // ScrollPane caseScrollPane = new ScrollPane();
        // VBox caseViewBox = new VBox(10);
        // caseViewBox.setStyle(
        // "-fx-padding: 10px; -fx-background-color: rgba(0, 0, 0, 0.5);
        // -fx-border-radius: 10px; -fx-background-radius: 10px;");

        // Label casesLabel = new Label("Your Cases:");
        // casesLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;
        // -fx-font-weight: bold;");
        // caseViewBox.getChildren().add(casesLabel);

        // boolean hasCases = false;
        // for (Case caseObj : AllCases) {
        // if (caseObj.getLawyerId() == lawyerID) {
        // hasCases = true;
        // Label caseEntry = new Label(caseObj.toString());
        // caseEntry.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-padding:
        // 5;");
        // caseViewBox.getChildren().add(caseEntry);
        // }
        // }

        // if (!hasCases) {
        // Label noCasesLabel = new Label("No cases available for you.");
        // noCasesLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        // caseViewBox.getChildren().add(noCasesLabel);
        // }

        // caseScrollPane.setContent(caseViewBox);
        // caseScrollPane.setFitToWidth(true);
        // caseScrollPane.setPrefHeight(300);
        // mainLayout.getChildren().add(caseScrollPane);

        // // Input field for CaseID
        // Label caseIdLabel = new Label("Enter Case ID:");
        // caseIdLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        // TextField caseIdField = new TextField();
        // caseIdField.setPromptText("Case ID");
        // caseIdField.setPrefWidth(200);

        // // Create "Back" button
        // Button backButton = new Button("Back");
        // backButton.setStyle(
        // "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px;
        // -fx-background-radius: 10px; -fx-background-color: #FF6347; -fx-text-fill:
        // white;");
        // backButton.setOnAction(ev -> {
        // primaryStage.setScene(previousScene); // Go back to case selection scene
        // });

        // Button nextButton = new Button("Next");
        // nextButton.setStyle(
        // "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px;
        // -fx-background-radius: 10px; -fx-background-color: #4CAF50; -fx-text-fill:
        // white;");

        // // Create an HBox to place the "Back" and "Submit" buttons in a line
        // HBox buttonLayout1 = new HBox(20, backButton, nextButton);
        // buttonLayout1.setAlignment(Pos.CENTER); // Align buttons to the center

        // VBox caseInputSection = new VBox(10, caseIdLabel, caseIdField,
        // buttonLayout1);
        // caseInputSection.setStyle("-fx-alignment: center;");
        // mainLayout.getChildren().add(caseInputSection);

        // nextButton.setOnAction(e -> {
        // try {
        // int caseId = Integer.parseInt(caseIdField.getText());
        // Slot selectedSlot = AllSlots.stream()
        // .filter(slot -> slot.getCaseID() != null && slot.getCaseID() == caseId)
        // .findFirst().orElse(null);

        // if (selectedSlot == null) {
        // System.out.println("No slot found for the selected case ID.");
        // return;
        // }

        // // Transition to witness selection
        // VBox witnessLayout = new VBox(20);
        // witnessLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // Label witnessTitleLabel = new Label("Available Witnesses:");
        // witnessTitleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 22px;
        // -fx-font-weight: bold;");
        // witnessLayout.getChildren().add(witnessTitleLabel);

        // ScrollPane witnessScrollPane = new ScrollPane();
        // VBox witnessViewBox = new VBox(10);
        // witnessViewBox.setStyle(
        // "-fx-padding: 10px; -fx-background-color: rgba(0, 0, 0, 0.5);
        // -fx-border-radius: 10px; -fx-background-radius: 10px;");

        // for (Witness witnessObj : AllWitnesses) {
        // Label witnessEntry = new Label(witnessObj.toString());
        // witnessEntry.setStyle("-fx-text-fill: white; -fx-font-size: 14px;
        // -fx-padding: 5;");
        // witnessViewBox.getChildren().add(witnessEntry);
        // }

        // witnessScrollPane.setContent(witnessViewBox);
        // witnessScrollPane.setFitToWidth(true);
        // witnessScrollPane.setPrefHeight(300);
        // witnessLayout.getChildren().add(witnessScrollPane);

        // Label witnessIdLabel = new Label("Enter Witness ID:");
        // witnessIdLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        // TextField witnessIdField = new TextField();
        // witnessIdField.setPromptText("Witness ID");
        // witnessIdField.setPrefWidth(200);

        // Button submitButton = new Button("Submit");
        // submitButton.setStyle(
        // "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px;
        // -fx-background-radius: 10px; -fx-background-color: #4CAF50; -fx-text-fill:
        // white;");

        // // Create an HBox to place the "Back" and "Submit" buttons in a line
        // HBox buttonLayout = new HBox(20, backButton, submitButton);
        // buttonLayout.setAlignment(Pos.CENTER); // Align buttons to the center

        // VBox witnessInputSection = new VBox(10, witnessIdLabel, witnessIdField,
        // buttonLayout);
        // witnessInputSection.setStyle("-fx-alignment: center;");
        // witnessLayout.getChildren().add(witnessInputSection);

        // Scene witnessScene = new Scene(witnessLayout, 1100, 650);
        // witnessLayout.setStyle(
        // "-fx-padding: 80px; -fx-alignment: center; -fx-background-size: stretch;
        // -fx-background-position: center; -fx-background-repeat: no-repeat;
        // -fx-background-image:
        // url('file:///E:/Github%20Projects/JusticeFlow/CourtsManagementSystem/lib/resources/img(3).jpeg');");
        // primaryStage.setScene(witnessScene);

        // submitButton.setOnAction(ev -> {
        // try {
        // int witnessId = Integer.parseInt(witnessIdField.getText());
        // Witness selectedWitness = AllWitnesses.stream()
        // .filter(w -> w.getWitnessID() == witnessId)
        // .findFirst().orElse(null);

        // if (selectedWitness == null) {
        // System.out.println("Invalid Witness ID.");
        // return;
        // }

        // selectedSlot.setWitnessID(witnessId);
        // selectedWitness.addCaseID(caseId);
        // databaseHandler.updateWitness(selectedWitness, selectedSlot);
        // databaseHandler.updateOrInsertSlots(AllSlots);

        // Label successLabel = new Label("Witness scheduled successfully!");
        // successLabel.setStyle("-fx-text-fill: #4CAF50; -fx-font-size: 16px;
        // -fx-font-weight: bold;");
        // witnessLayout.getChildren().add(successLabel);

        // } catch (NumberFormatException ex) {
        // System.out.println("Invalid Witness ID input.");
        // }
        // });
        // } catch (NumberFormatException ex) {
        // System.out.println("Invalid Case ID input.");
        // }
        // });

        // Scene caseScene = new Scene(mainLayout, 1100, 650);
        // mainLayout.setStyle(
        // "-fx-padding: 80px; -fx-alignment: center; -fx-background-size: stretch;
        // -fx-background-position: center; -fx-background-repeat: no-repeat;
        // -fx-background-image:
        // url('file:///E:/Github%20Projects/JusticeFlow/CourtsManagementSystem/lib/resources/img(3).jpeg');");
        // primaryStage.setScene(caseScene);
        // primaryStage.show();
    }

    public void SubmitDocument(Scanner scanner, List<Case> AllCases, FileHandler fileHandler) {
        Case c = new Case();

        // Prompt for Case ID
        System.out.print("Enter Case ID: ");
        int caseID = scanner.nextInt();

        // Check if case exists
        if (c.doesCaseExist(caseID, AllCases)) {
            c = c.getCasebyID(caseID, AllCases);

            // if (c.getLawyerId() == lawyerID) {
            // Open file dialog to select a file
            File selectedFile = fileHandler.openFileDialog();
            if (selectedFile != null) {
                try {
                    // Generate file hash
                    String fileHash = fileHandler.getFileHash(selectedFile.getAbsolutePath());
                    // System.out.println("File hash: " + fileHash);

                    CaseFile my_file = new CaseFile(selectedFile.getAbsolutePath(), fileHash);
                    c.addFile(my_file);
                    System.out.println("File added to case, waiting for Registrar to approve.");

                    System.out.println(c.toString());

                    DatabaseHandler d = new DatabaseHandler();
                    d.saveFileDetails(c.getCaseID(), selectedFile.getAbsolutePath(), fileHash, false);
                    System.out.println("File Added in Database.");
                    return;

                    // Perform further actions if needed
                } catch (IOException | NoSuchAlgorithmException e) {
                    System.err.println("Error while processing the file: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("No file selected.");
            }
            // } else {
            // System.out.println("You are not the authorised lawyer of this case.");
            // }
        } else {
            System.out.println("Case with this ID does not exist.");
        }
    }

    public void scheduleWitness(Scanner scanner, List<Case> AllCases, List<Slot> AllSlots, List<Judge> AllJudges,
            List<Court> AllCourts, List<Witness> AllWitnesses, FileHandler fileHandler,
            DatabaseHandler databaseHandler) {

        boolean scheduled = false;
        for (Case caseObj : AllCases) {
            if (caseObj != null) {
                if (caseObj.getLawyerId() == lawyerID) {
                    scheduled = true;
                }
            }
        }

        if (scheduled) {
            System.out.println("\nYour Cases:");
            for (Case caseObj : AllCases) {
                if (caseObj != null) {
                    if (caseObj.getLawyerId() == lawyerID) {
                        System.out.println(caseObj.toString());
                    }
                }
            }

            System.out.print("Enter Case ID: ");
            int cid = scanner.nextInt();

            Slot s = new Slot();
            boolean scheduled1 = false;
            for (Slot slotObj : AllSlots) {
                if (slotObj != null) {
                    if (slotObj.getCaseID() != null) {
                        if (slotObj.getCaseID() == cid) {
                            s = slotObj;
                            scheduled1 = true;
                        }
                    }
                }
            }

            if (scheduled1) {
                Witness w;

                System.out.println("\nExisting Witnesses:");
                int count = 1;
                for (Witness caseObj : AllWitnesses) {
                    System.out.println(count + ". " + caseObj.toString());
                    count++;
                }
                System.out.print("\nEnter WitnessID: ");
                int wid = scanner.nextInt();

                for (Witness caseObj : AllWitnesses) {
                    if (caseObj != null) {
                        if (caseObj.getWitnessID() == wid) {
                            w = caseObj;
                            System.out.println("Selected Witness with witnessID " + w.getWitnessID());
                            s.setWitnessID(w.getWitnessID());
                            w.addCaseID(cid);
                            databaseHandler.updateWitness(w, s);
                            databaseHandler.updateOrInsertSlots(AllSlots);
                            break;
                        }
                    }
                }
            } else {
                System.out.println("No cases scheduled.");
            }
        } else {
            System.out.println("No cases for you.");
        }

        // }

        // } else {
        // System.out.println("Case not Sheduled.");
        // }
    }

    public void RegistertoBar(List<BarAssociation> barassociationlist, List<BarApplication> AllApplications,
            DatabaseHandler dbHandler, Scanner scanner) {
        for (BarAssociation b : barassociationlist) {
            print(b.toString());
        }
        if (!barassociationlist.isEmpty()) {
            print("Select Bar ID :");
            Object var = GetInput(scanner);
            Integer id = (Integer) var;
            BarAssociation bar = BarAssociation.getbar(barassociationlist, id);
            if (bar != null) {
                // Use LocalDateTime.now() for the current machine time
                BarApplication barrequest = new BarApplication(AllApplications.size() + 1, this.lawyerID,
                        LocalDateTime.now().toString(), 0, bar.getBarAssociationID());
                AllApplications.add(barrequest); // Add the new BarApplication to the list
                dbHandler.updateBarApplication(barrequest);

            } else {
                print("Invalid Input!");
            }
        }
    }

    public void RegistertoBar(List<BarAssociation> barassociationlist, List<BarApplication> AllApplications,
            DatabaseHandler dbHandler, Stage primaryStage, GUI_Menu gui) {

        // Create the main layout for the UI
        VBox mainLayout = new VBox(25);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setStyle("-fx-background-color: #f9f9f9; -fx-alignment: center;");

        // Title Label
        Label titleLabel = new Label("Register to Bar Association");
        titleLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // List all Bar Associations in a scrollable list
        ListView<BarAssociation> barListView = new ListView<>();
        barListView.getItems().addAll(barassociationlist);
        Label selectedBarLabel = new Label("No Bar Selected");
        selectedBarLabel.setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #2c3e50; " +
                        "-fx-background-color: #ecf0f1; " + // Light background color
                        "-fx-padding: 20px; " + // Padding inside the label
                        "-fx-border-radius: 10px; " + // Rounded corners
                        "-fx-border-color: #bdc3c7; " + // Border color
                        "-fx-border-width: 1px; " + // Border width
                        "-fx-alignment: center; " + // Text centered inside the label
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 5, 0, 0, 2);"); // Subtle shadow for better
                                                                                            // contrast

        // Customizing the appearance of list items
        barListView.setCellFactory(param -> new ListCell<BarAssociation>() {
            @Override
            protected void updateItem(BarAssociation item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle(""); // Reset the style
                } else {
                    setText("Bar ID: " + item.getBarAssociationID() + " | " + item.getAssociationName());
                    setStyle("-fx-font-size: 16px; -fx-text-fill: #333333; -fx-padding: 10px;");

                    // Alternate background colors
                    if (getIndex() % 2 == 0) {
                        setStyle(getStyle() + "-fx-background-color: #f7f7f7;");
                    } else {
                        setStyle(getStyle() + "-fx-background-color: #ffffff;");
                    }

                    // Hover effect
                    setOnMouseEntered(e -> {
                        if (!isSelected()) {
                            setStyle(getStyle() + "-fx-background-color: #dce6f1; -fx-cursor: hand;");
                        }
                    });

                    setOnMouseExited(e -> {
                        if (!isSelected()) {
                            setStyle(getStyle().replace("-fx-background-color: #dce6f1;", ""));
                        }
                    });

                    // Keep selected item colored
                    selectedProperty().addListener((observable, oldValue, newValue) -> {
                        selectedBarLabel.setText("Selected Bar: " + "Bar ID: " + item.getBarAssociationID() + " | "
                                + item.getAssociationName());
                        if (newValue) {
                            setStyle(getStyle() + "-fx-background-color: #4CAF50; -fx-text-fill: white;");
                        } else {
                            if (getIndex() % 2 == 0) {
                                setStyle(getStyle() + "-fx-background-color: #f7f7f7;");
                            } else {
                                setStyle(getStyle() + "-fx-background-color: #ffffff;");
                            }
                        }
                    });
                }
            }
        });

        // Scroll pane to make the list scrollable
        ScrollPane barScrollPane = new ScrollPane(barListView);
        barScrollPane.setFitToWidth(true);
        barScrollPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ddd; -fx-border-width: 1px; " +
                "-fx-background-radius: 8px; -fx-border-radius: 8px; -fx-padding: 10px;-fx-max-height:200px;");

        // TabPane to show the selected bar association
        TabPane tabPane = new TabPane();
        Tab selectedBarTab = new Tab("Selected Bar");

        selectedBarTab.setContent(selectedBarLabel);
        tabPane.getTabs().add(selectedBarTab);
        selectedBarTab.setClosable(false);

        // Button to confirm registration
        Button registerButton = new Button("Register to Selected Bar");
        registerButton.setStyle(
                "-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 16px; " +
                        "-fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        registerButton.setOnAction(event -> {
            // Get selected bar ID from ListView
            BarAssociation selectedBar = barListView.getSelectionModel().getSelectedItem();

            if (selectedBar != null) {
                // Update tab with selected bar association details dynamically
                selectedBarLabel.setText("Selected Bar: " + selectedBar.getAssociationName() +
                        "\nBar ID: " + selectedBar.getBarAssociationID());

                // Create a new BarApplication and add it to the list
                LocalDateTime now = LocalDateTime.now();
                String applicationTime = now.toString();

                BarApplication barrequest = new BarApplication(AllApplications.size() + 1, this.lawyerID,
                        applicationTime, 0, selectedBar.getBarAssociationID());
                AllApplications.add(barrequest);
                dbHandler.updateBarApplication(barrequest);

                // Confirmation
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Registration Successful");
                confirmationAlert.setHeaderText("You have successfully registered to the Bar Association.");
                confirmationAlert.setContentText(
                        "Bar ID: " + selectedBar.getBarAssociationID() + " | " + selectedBar.getAssociationName());
                confirmationAlert.showAndWait();
            } else {
                // Show error if no bar is selected
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("No Bar Selected");
                errorAlert.setHeaderText("Please select a Bar Association to register.");
                errorAlert.showAndWait();
            }
        });

        // Button to close the registration window
        Button closeButton = new Button("Close");
        closeButton.setStyle(
                "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 16px; " +
                        "-fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        closeButton.setOnAction(event -> {
            gui.GUI_startmenu(primaryStage);
        });

        // Layout: Arrange UI elements
        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(registerButton, closeButton);

        mainLayout.getChildren().addAll(titleLabel, barScrollPane, tabPane, buttonLayout);

        // Scene setup
        Scene scene = new Scene(mainLayout, 1000, 600);
        primaryStage.setTitle("Register to Bar Association");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void Re_OpenCase(List<Case> AllCases, FileHandler fileHandler, DatabaseHandler dbHandler, Stage primaryStage,
            Scene previousScene) {
        // Title Label
        Label titleLabel = new Label("Re-Open Case!");
        titleLabel.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333333; -fx-padding: 20px 0; -fx-alignment: center;");

        // ScrollPane containing the case list
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setFitToWidth(true);
        formScrollPane.setStyle("-fx-background-color: #f4f4f9;");

        VBox formLayout = new VBox(20); // Increased spacing for clarity
        formLayout.setStyle("-fx-padding: 20px;");

        // Iterate over all cases and add only pending cases
        for (Case cases : AllCases) {
            if ("Closed".equalsIgnoreCase(cases.getCaseStatus()) && cases.getLawyerId() == this.lawyerID) {

                // Create a GridPane for each case
                GridPane eachCase = new GridPane();
                eachCase.setHgap(15); // Horizontal gap between columns
                eachCase.setVgap(15); // Vertical gap between rows
                eachCase.setStyle(
                        "-fx-padding: 20px; " +
                                "-fx-background-color: #f9f9f9; " +
                                "-fx-border-color: #dcdcdc; " +
                                "-fx-border-width: 1px; " +
                                "-fx-border-radius: 15px; " +
                                "-fx-background-radius: 15px; " +
                                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 10, 0, 2, 2);");

                // Case Title Label
                Label caseName = new Label(cases.getCaseTitle());
                caseName.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

                Label casetype = new Label("Case Type: " + cases.getCaseType());
                casetype.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #34495e;");

                Label caseplaintiff = new Label("Plaintiff ID: " + cases.getPlaintiffID());
                caseplaintiff.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #34495e;");

                Label casedefendant = new Label("Defendant ID: " + cases.getDefendantID());
                casedefendant.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #34495e;");

                Label CaseFiling = new Label("Filing Date: " + cases.getFilingDate());
                CaseFiling.setStyle("-fx-font-size: 16px; -fx-font-weight: normal; -fx-text-fill: #e74c3c;");

                // Set alignments for the GridPane
                GridPane.setConstraints(caseName, 0, 0, 2, 1); // Spanning across two columns
                GridPane.setConstraints(casetype, 0, 1);
                GridPane.setConstraints(caseplaintiff, 2, 1);
                GridPane.setConstraints(casedefendant, 0, 2);
                GridPane.setConstraints(CaseFiling, 2, 2);

                // Add components to the GridPane
                eachCase.getChildren().addAll(caseName, casetype, caseplaintiff, casedefendant, CaseFiling);

                // Approve and Reject Buttons
                Button approveButton = new Button("Request for Re-Open");
                approveButton.setStyle(
                        "-fx-background-color: #27ae60; " +
                                "-fx-text-fill: white; " +
                                "-fx-font-size: 14px; " +
                                "-fx-font-weight: bold; " +
                                "-fx-padding: 10px 20px; " +
                                "-fx-border-radius: 8px; " +
                                "-fx-background-radius: 8px;");

                // Add spacing and alignment for buttons
                GridPane.setConstraints(approveButton, 0, 3);
                // GridPane.setConstraints(rejectButton, 1, 3);
                GridPane.setMargin(approveButton, new Insets(10, 10, 10, 0)); // Add spacing around the buttons
                approveButton.setOnAction(e -> {
                    cases.setCaseStatus("Appeal");
                    dbHandler.saveOrUpdateCase(cases);
                    System.out.println("Case Status set to Appeal in Database");
                });

                // Add buttons to the GridPane
                eachCase.getChildren().addAll(approveButton);

                // Add the GridPane to the formLayout
                formLayout.getChildren().add(eachCase);
            }
        }
        // Set the VBox into the ScrollPane and display it
        formScrollPane.setContent(formLayout);
        Button returnButton = new Button("Close");
        returnButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px;");
        returnButton.setMaxWidth(Double.MAX_VALUE); // Make button stretch to fill width
        returnButton.setOnAction(e -> {
            primaryStage.setScene(previousScene);
        });
        // Setting up the scene with the scrollable content
        VBox rootLayout = new VBox(20);
        rootLayout.getChildren().addAll(titleLabel, formScrollPane, returnButton);
        rootLayout.setStyle("-fx-background-color: #f4f4f9; -fx-padding: 20px;");

        Scene registerScene = new Scene(rootLayout, 1000, 700);
        primaryStage.setScene(registerScene);
        primaryStage.show();
    }

}
