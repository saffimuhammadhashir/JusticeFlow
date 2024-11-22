package JusticeFlow;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    public void SubmitDocument(Scanner scanner, List<Case> AllCases, FileHandler fileHandler, Stage primaryStage, Scene previousScene) {
    // Create main layout for the submit document process
    VBox mainLayout = new VBox(20);
    mainLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

    // Title label
    Label titleLabel = new Label("Submit Document for Case");
    titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");
    mainLayout.getChildren().add(titleLabel);

    // Show lawyer's cases in a scrollable VBox
    VBox lawyerCasesBox = new VBox(10);
    lawyerCasesBox.setStyle(
            "-fx-alignment: center; -fx-padding: 10px; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-border-radius: 10px; -fx-background-radius: 10px;");
    ScrollPane scrollPane = new ScrollPane(lawyerCasesBox);
    scrollPane.setPrefSize(600, 200);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

    boolean exists = false;
    for (Case c : AllCases) {
        if (c != null && c.getLawyerId() == lawyerID) {
            Label caseLabel = new Label(c.toString());
            caseLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
            lawyerCasesBox.getChildren().add(caseLabel);
            exists = true;
        }
    }

    if (!exists) {
        Label NoCase = new Label("You have no Cases.");
        NoCase.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        lawyerCasesBox.getChildren().add(NoCase);
    }

    mainLayout.getChildren().add(scrollPane);

    // Case ID input
    Label caseIdLabel = new Label("Enter Case ID:");
    caseIdLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
    TextField caseIdField = new TextField();
    caseIdField.setPromptText("Case ID");
    caseIdField.setPrefWidth(200);

    Button nextButton = new Button("Next");
    nextButton.setStyle(
            "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

    // Create the back button
    Button backButton = new Button("Back");
    backButton.setStyle("-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #FF6347; -fx-text-fill: white;");
    backButton.setOnAction(e -> {
        // Switch back to the previous scene
        primaryStage.setScene(previousScene);
    });

    // Create an HBox to place the back and next buttons in a line
    HBox buttonLayout = new HBox(20, backButton, nextButton);
    buttonLayout.setAlignment(Pos.CENTER); // Align buttons to the center

    // Create VBox to place everything (title, cases, buttons)
    VBox caseInputSection = new VBox(10, caseIdLabel, caseIdField, buttonLayout);
    caseInputSection.setStyle("-fx-alignment: center;");
    mainLayout.getChildren().add(caseInputSection);

    nextButton.setOnAction(e -> {
        // Clear previous error or success messages
        mainLayout.getChildren().removeIf(node -> node instanceof Label);

        try {
            int caseId = Integer.parseInt(caseIdField.getText());
            Case caseObj = new Case();

            // Check if case exists
            if (caseObj.doesCaseExist(caseId, AllCases)) {
                caseObj = caseObj.getCasebyID(caseId, AllCases);

                // Open file dialog to select a file
                File selectedFile = fileHandler.openFileDialog();
                if (selectedFile != null) {
                    try {
                        // Generate file hash
                        String fileHash = fileHandler.getFileHash(selectedFile.getAbsolutePath());

                        CaseFile my_file = new CaseFile(selectedFile.getAbsolutePath(), fileHash);
                        caseObj.addFile(my_file);
                        System.out.println("File added to case, waiting for Registrar to approve.");

                        // Update the database with file details
                        DatabaseHandler dbHandler = new DatabaseHandler();
                        dbHandler.saveFileDetails(caseObj.getCaseID(), selectedFile.getAbsolutePath(), fileHash,
                                false);
                        System.out.println("File Added in Database.");

                        // Show confirmation message in GUI
                        Label successLabel = new Label(
                                "File submitted successfully! Waiting for Registrar approval.");
                        successLabel
                                .setStyle("-fx-text-fill: #4CAF50; -fx-font-size: 16px; -fx-font-weight: bold;");
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

            } else {
                // Show error if case does not exist
                Label caseNotFoundLabel = new Label("Case with this ID does not exist.");
                caseNotFoundLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                mainLayout.getChildren().add(caseNotFoundLabel);
            }

        } catch (NumberFormatException ex) {
            // Show error if input is not a valid number
            Label invalidInputLabel = new Label("Invalid Case ID input.");
            invalidInputLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            mainLayout.getChildren().add(invalidInputLabel);
        }
    });

    Scene scene = new Scene(mainLayout, 1100, 650);
    mainLayout.setStyle(
            "-fx-padding: 80px; -fx-alignment: center; -fx-background-size: stretch; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-image: url('file:///D:/Github/JusticeFlow/CourtsManagementSystem/lib/resources/img(3).jpeg');");
    primaryStage.setScene(scene);
    primaryStage.show();
}

    public void scheduleWitness(Scanner scanner, List<Case> AllCases, List<Slot> AllSlots, List<Judge> AllJudges,
            List<Court> AllCourts, List<Witness> AllWitnesses, FileHandler fileHandler,
            DatabaseHandler databaseHandler, Stage primaryStage,Scene previousScene) {

        // Main layout container
        VBox mainLayout = new VBox(20);
        mainLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // Title label
        Label titleLabel = new Label("Schedule a Witness");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");
        mainLayout.getChildren().add(titleLabel);

        // Scrollable ViewBox for displaying lawyer's cases
        ScrollPane caseScrollPane = new ScrollPane();
        VBox caseViewBox = new VBox(10);
        caseViewBox.setStyle(
                "-fx-padding: 10px; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-border-radius: 10px; -fx-background-radius: 10px;");

        Label casesLabel = new Label("Your Cases:");
        casesLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        caseViewBox.getChildren().add(casesLabel);

        boolean hasCases = false;
        for (Case caseObj : AllCases) {
            if (caseObj.getLawyerId() == lawyerID) {
                hasCases = true;
                Label caseEntry = new Label(caseObj.toString());
                caseEntry.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5;");
                caseViewBox.getChildren().add(caseEntry);
            }
        }

        if (!hasCases) {
            Label noCasesLabel = new Label("No cases available for you.");
            noCasesLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
            caseViewBox.getChildren().add(noCasesLabel);
        }

        caseScrollPane.setContent(caseViewBox);
        caseScrollPane.setFitToWidth(true);
        caseScrollPane.setPrefHeight(300);
        mainLayout.getChildren().add(caseScrollPane);

        // Input field for CaseID
        Label caseIdLabel = new Label("Enter Case ID:");
        caseIdLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        TextField caseIdField = new TextField();
        caseIdField.setPromptText("Case ID");
        caseIdField.setPrefWidth(200);

        // Create "Back" button
        Button backButton = new Button("Back");
        backButton.setStyle(
                "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #FF6347; -fx-text-fill: white;");
        backButton.setOnAction(ev -> {
            primaryStage.setScene(previousScene); // Go back to case selection scene
        });

        Button nextButton = new Button("Next");
        nextButton.setStyle(
                "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

        // Create an HBox to place the "Back" and "Submit" buttons in a line
        HBox buttonLayout1 = new HBox(20, backButton, nextButton);
        buttonLayout1.setAlignment(Pos.CENTER); // Align buttons to the center
        
        VBox caseInputSection = new VBox(10, caseIdLabel, caseIdField, buttonLayout1);
        caseInputSection.setStyle("-fx-alignment: center;");
        mainLayout.getChildren().add(caseInputSection);

        nextButton.setOnAction(e -> {
            try {
                int caseId = Integer.parseInt(caseIdField.getText());
                Slot selectedSlot = AllSlots.stream()
                        .filter(slot -> slot.getCaseID() != null && slot.getCaseID() == caseId)
                        .findFirst().orElse(null);

                if (selectedSlot == null) {
                    System.out.println("No slot found for the selected case ID.");
                    return;
                }

                // Transition to witness selection
                VBox witnessLayout = new VBox(20);
                witnessLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

                Label witnessTitleLabel = new Label("Available Witnesses:");
                witnessTitleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");
                witnessLayout.getChildren().add(witnessTitleLabel);

                ScrollPane witnessScrollPane = new ScrollPane();
                VBox witnessViewBox = new VBox(10);
                witnessViewBox.setStyle(
                        "-fx-padding: 10px; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-border-radius: 10px; -fx-background-radius: 10px;");

                for (Witness witnessObj : AllWitnesses) {
                    Label witnessEntry = new Label(witnessObj.toString());
                    witnessEntry.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5;");
                    witnessViewBox.getChildren().add(witnessEntry);
                }

                witnessScrollPane.setContent(witnessViewBox);
                witnessScrollPane.setFitToWidth(true);
                witnessScrollPane.setPrefHeight(300);
                witnessLayout.getChildren().add(witnessScrollPane);

                Label witnessIdLabel = new Label("Enter Witness ID:");
                witnessIdLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
                TextField witnessIdField = new TextField();
                witnessIdField.setPromptText("Witness ID");
                witnessIdField.setPrefWidth(200);

                Button submitButton = new Button("Submit");
                submitButton.setStyle(
                        "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

                // Create an HBox to place the "Back" and "Submit" buttons in a line
                HBox buttonLayout = new HBox(20, backButton, submitButton);
                buttonLayout.setAlignment(Pos.CENTER); // Align buttons to the center


                VBox witnessInputSection = new VBox(10, witnessIdLabel, witnessIdField, buttonLayout);
                witnessInputSection.setStyle("-fx-alignment: center;");
                witnessLayout.getChildren().add(witnessInputSection);

                Scene witnessScene = new Scene(witnessLayout, 1100, 650);
                witnessLayout.setStyle(
                        "-fx-padding: 80px; -fx-alignment: center; -fx-background-size: stretch; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-image: url('file:///D:/Github/JusticeFlow/CourtsManagementSystem/lib/resources/img(3).jpeg');");
                primaryStage.setScene(witnessScene);

                submitButton.setOnAction(ev -> {
                    try {
                        int witnessId = Integer.parseInt(witnessIdField.getText());
                        Witness selectedWitness = AllWitnesses.stream()
                                .filter(w -> w.getWitnessID() == witnessId)
                                .findFirst().orElse(null);

                        if (selectedWitness == null) {
                            System.out.println("Invalid Witness ID.");
                            return;
                        }

                        selectedSlot.setWitnessID(witnessId);
                        selectedWitness.addCaseID(caseId);
                        databaseHandler.updateWitness(selectedWitness, selectedSlot);
                        databaseHandler.updateOrInsertSlots(AllSlots);

                        Label successLabel = new Label("Witness scheduled successfully!");
                        successLabel.setStyle("-fx-text-fill: #4CAF50; -fx-font-size: 16px; -fx-font-weight: bold;");
                        witnessLayout.getChildren().add(successLabel);

                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid Witness ID input.");
                    }
                });
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Case ID input.");
            }
        });

        Scene caseScene = new Scene(mainLayout, 1100, 650);
        mainLayout.setStyle(
                "-fx-padding: 80px; -fx-alignment: center; -fx-background-size: stretch; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-image: url('file:///D:/Github/JusticeFlow/CourtsManagementSystem/lib/resources/img(3).jpeg');");
        primaryStage.setScene(caseScene);
        primaryStage.show();
    }

    // public void scheduleWitness(Scanner scanner, List<Case> AllCases, List<Slot>
    // AllSlots, List<Judge> AllJudges,
    // List<Court> AllCourts, List<Witness> AllWitnesses, FileHandler fileHandler,
    // DatabaseHandler databaseHandler, Stage primaryStage) {

    // boolean scheduled = false;
    // for (Case caseObj : AllCases) {
    // if (caseObj != null) {
    // if (caseObj.getLawyerId() == lawyerID) {
    // scheduled = true;
    // }
    // }
    // }

    // if (scheduled) {
    // System.out.println("\nYour Cases:");
    // for (Case caseObj : AllCases) {
    // if (caseObj != null) {
    // if (caseObj.getLawyerId() == lawyerID) {
    // System.out.println(caseObj.toString());
    // }
    // }
    // }

    // System.out.print("Enter Case ID: ");
    // int cid = scanner.nextInt();

    // Slot s = new Slot();
    // boolean scheduled1 = false;
    // for (Slot slotObj : AllSlots) {
    // if (slotObj != null) {
    // if (slotObj.getCaseID() != null) {
    // if (slotObj.getCaseID() == cid) {
    // s = slotObj;
    // scheduled1 = true;
    // }
    // }
    // }
    // }

    // if (scheduled1) {
    // Witness w;

    // System.out.println("\nExisting Witnesses:");
    // int count=1;
    // for (Witness caseObj : AllWitnesses) {
    // System.out.println(count + ". " + caseObj.toString());
    // count++;
    // }
    // System.out.print("\nEnter WitnessID: ");
    // int wid = scanner.nextInt();

    // for (Witness caseObj : AllWitnesses) {
    // if (caseObj != null) {
    // if (caseObj.getWitnessID() == wid) {
    // w = caseObj;
    // System.out.println("Selected Witness with witnessID " + w.getWitnessID());
    // s.setWitnessID(w.getWitnessID());
    // w.addCaseID(cid);
    // databaseHandler.updateWitness(w, s);
    // databaseHandler.updateOrInsertSlots(AllSlots);
    // break;
    // }
    // }
    // }
    // } else {
    // System.out.println("No cases scheduled.");
    // }
    // } else {
    // System.out.println("No cases for you.");
    // }

    // // }

    // // } else {
    // // System.out.println("Case not Sheduled.");
    // // }
    // }

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
                BarApplication barrequest = new BarApplication(AllApplications.size(), this.lawyerID,
                        LocalDateTime.now().toString(), 0);
                AllApplications.add(barrequest); // Add the new BarApplication to the list
                dbHandler.updateBarApplication(barrequest);

            } else {
                print("Invalid Input!");
            }
        }
    }
}
