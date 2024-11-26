package JusticeFlow;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import JusticeFlow.CourtsManagementSystem.GUI_Menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProbationOfficer extends User {
    private int officerID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Date hireDate;
    private int courtID;
    private String email;
    private String phoneNumber;
    private int userID;

    public ProbationOfficer() {
        super();
    }

    public ProbationOfficer(int userID, String username, String password, String role, String email, String phoneNumber,
            boolean activate,
            int officerID, String firstName, String lastName, Date dateOfBirth, String gender, Date hireDate,
            int courtID) {
        super(userID, username, password, role, email, phoneNumber, activate); // Calling the constructor of User class

        this.officerID = officerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.hireDate = hireDate;
        this.courtID = courtID;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userID = userID;
    }

    public int getOfficerID() {
        return officerID;
    }

    public void setOfficerID(int officerID) {
        this.officerID = officerID;
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getCourtID() {
        return courtID;
    }

    public void setCourtID(int courtID) {
        this.courtID = courtID;
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
        return "ProbationOfficer {" +
                "judgeID=" + officerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", courtID=" + courtID +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userID=" + userID +
                '}';
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
            if (cases.getLawyerId() == officerID) {

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

        // if (!exists) {
        // Label NoCase = new Label("You have no Cases.");
        // NoCase.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        // lawyerCasesBox.getChildren().add(NoCase);
        // }

        // mainLayout.getChildren().add(formScrollPane);

        // // Show lawyer's cases in a scrollable VBox
        // VBox lawyerCasesBox = new VBox(10);
        // lawyerCasesBox.setStyle(
        // "-fx-alignment: center; -fx-padding: 10px; -fx-background-color: rgba(0, 0,
        // 0, 0.5); -fx-border-radius: 10px; -fx-background-radius: 10px;");
        // ScrollPane scrollPane = new ScrollPane(lawyerCasesBox);
        // scrollPane.setPrefSize(600, 200);
        // scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // boolean exists = false;
        // for (Case c : AllCases) {
        // if (c != null && c.getLawyerId() == lawyerID) {
        // // Label caseLabel = new Label(c.toString());
        // // caseLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        // // lawyerCasesBox.getChildren().add(caseLabel);
        // exists = true;
        // }
        // }

        // if (!exists) {
        // Label NoCase = new Label("You have no Cases.");
        // NoCase.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        // lawyerCasesBox.getChildren().add(NoCase);
        // }

        // mainLayout.getChildren().add(scrollPane);

        // // Case ID input
        // Label caseIdLabel = new Label("Enter Case ID:");
        // caseIdLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        // TextField caseIdField = new TextField();
        // caseIdField.setPromptText("Case ID");
        // caseIdField.setPrefWidth(200);

        // Button nextButton = new Button("Next");
        // nextButton.setStyle(
        // "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px;
        // -fx-background-radius: 10px; -fx-background-color: #4CAF50; -fx-text-fill:
        // white;");

        // // Create the back button
        // Button backButton = new Button("Back");
        // backButton.setStyle(
        // "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px;
        // -fx-background-radius: 10px; -fx-background-color: #FF6347; -fx-text-fill:
        // white;");
        // backButton.setOnAction(e -> {
        // // Switch back to the previous scene
        // primaryStage.setScene(previousScene);
        // });

        // // Create an HBox to place the back and next buttons in a line
        // HBox buttonLayout = new HBox(20, backButton, nextButton);
        // buttonLayout.setAlignment(Pos.CENTER); // Align buttons to the center

        // // Create VBox to place everything (title, cases, buttons)
        // VBox caseInputSection = new VBox(10, caseIdLabel, caseIdField, buttonLayout);
        // caseInputSection.setStyle("-fx-alignment: center;");
        // mainLayout.getChildren().add(caseInputSection);

        // nextButton.setOnAction(e -> {
        // // Clear previous error or success messages
        // mainLayout.getChildren().removeIf(node -> node instanceof Label);

        // try {
        // int caseId = Integer.parseInt(caseIdField.getText());
        // Case caseObj = new Case();

        // // Check if case exists
        // if (caseObj.doesCaseExist(caseId, AllCases)) {
        // caseObj = caseObj.getCasebyID(caseId, AllCases);

        // // Open file dialog to select a file
        // File selectedFile = fileHandler.openFileDialog();
        // if (selectedFile != null) {
        // try {
        // // Generate file hash
        // String fileHash = fileHandler.getFileHash(selectedFile.getAbsolutePath());

        // CaseFile my_file = new CaseFile(selectedFile.getAbsolutePath(), fileHash);
        // caseObj.addFile(my_file);
        // System.out.println("File added to case, waiting for Registrar to approve.");

        // // Update the database with file details
        // DatabaseHandler dbHandler = new DatabaseHandler();
        // dbHandler.saveFileDetails(caseObj.getCaseID(),
        // selectedFile.getAbsolutePath(), fileHash,
        // false);
        // System.out.println("File Added in Database.");

        // // Show confirmation message in GUI
        // Label successLabel = new Label(
        // "File submitted successfully! Waiting for Registrar approval.");
        // successLabel
        // .setStyle("-fx-text-fill: #4CAF50; -fx-font-size: 16px; -fx-font-weight:
        // bold;");
        // mainLayout.getChildren().add(successLabel);

        // } catch (IOException | NoSuchAlgorithmException ex) {
        // ex.printStackTrace();
        // Label errorLabel = new Label("Error while processing the file.");
        // errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
        // mainLayout.getChildren().add(errorLabel);
        // }
        // } else {
        // Label noFileLabel = new Label("No file selected.");
        // noFileLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
        // mainLayout.getChildren().add(noFileLabel);
        // }

        // } else {
        // // Show error if case does not exist
        // Label caseNotFoundLabel = new Label("Case with this ID does not exist.");
        // caseNotFoundLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
        // mainLayout.getChildren().add(caseNotFoundLabel);
        // }

        // } catch (NumberFormatException ex) {
        // // Show error if input is not a valid number
        // Label invalidInputLabel = new Label("Invalid Case ID input.");
        // invalidInputLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
        // mainLayout.getChildren().add(invalidInputLabel);
        // }
        // });

        // Scene scene = new Scene(mainLayout, 1100, 650);
        // mainLayout.setStyle(
        // "-fx-padding: 80px; -fx-alignment: center; -fx-background-size: stretch;
        // -fx-background-position: center; -fx-background-repeat: no-repeat;
        // -fx-background-image:
        // url('file:///E:/Github%20Projects/JusticeFlow/CourtsManagementSystem/lib/resources/img(3).jpeg');");
        // primaryStage.setScene(scene);
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
        } else {
            System.out.println("Case with this ID does not exist.");
        }
    }

    public void TrackCase(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            List<Slot> AllSlots, List<Clients> allclients, List<Judge> AllJudges, List<Lawyer> AllLawyers,
            List<Witness> AllWitnesses, List<Court> AllCourts,
            Stage primaryStage, GUI_Menu gui, CourtsManagementSystem system) {
        List<Case> PendingCases = new ArrayList<>();

        List<Case> Lawyercases = new ArrayList<>();
        for (Case c : AllCases) {

            Lawyercases.add(c);
        }

        // Title Label
        Label titleLabel = new Label("Track Cases to be Managed");
        titleLabel.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333333; -fx-padding: 20px 0; -fx-alignment: center;");

        // ScrollPane containing the case list
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setFitToWidth(true);
        formScrollPane.setStyle("-fx-background-color: #f4f4f9;");

        VBox formLayout = new VBox(20); // Increased spacing for clarity
        formLayout.setStyle("-fx-padding: 20px;");

        // Iterate over all cases and add only pending cases
        for (Case cases : Lawyercases) {

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
            Button approveButton = new Button("View Case");
            approveButton.setStyle(
                    "-fx-background-color: #27ae60; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14px; " +
                            "-fx-font-weight: bold; " +
                            "-fx-padding: 10px 20px; " +
                            "-fx-border-radius: 8px; " +
                            "-fx-background-radius: 8px;");

            Button rejectButton = new Button("Manage Witness");
            rejectButton.setStyle(
                    "-fx-background-color: #27ae60; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14px; " +
                            "-fx-font-weight: bold; " +
                            "-fx-padding: 10px 20px; " +
                            "-fx-border-radius: 8px; " +
                            "-fx-background-radius: 8px;");
            approveButton.setOnAction(e -> {
                cases.DisplayDetailsLawyer(dbHandler, fileHandler, AllCases, AllSlots, allclients, AllJudges,
                        AllLawyers,
                        AllWitnesses, AllCourts,
                        primaryStage, gui, system);

            });
            rejectButton.setOnAction(e -> {
                cases.displayWitnesseslawyer(dbHandler, fileHandler, AllCases, AllSlots, allclients, AllJudges,
                        AllLawyers, AllWitnesses, AllCourts, primaryStage, gui, system, cases);

            });
            // Add spacing and alignment for buttons
            GridPane.setConstraints(approveButton, 0, 3);
            GridPane.setConstraints(rejectButton, 1, 3);
            GridPane.setMargin(approveButton, new Insets(10, 10, 10, 0)); // Add spacing around the buttons
            GridPane.setMargin(rejectButton, new Insets(10, 0, 10, 10));

            // Add buttons to the GridPane
            eachCase.getChildren().addAll(approveButton, rejectButton);

            // Add the GridPane to the formLayout
            formLayout.getChildren().add(eachCase);

            // Add the case to the list of pending cases
            PendingCases.add(cases);

        }
        // Set the VBox into the ScrollPane and display it
        formScrollPane.setContent(formLayout);
        Button returnButton = new Button("Close");
        returnButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px;");
        returnButton.setMaxWidth(Double.MAX_VALUE); // Make button stretch to fill width
        returnButton.setOnAction(e -> {
            gui.GUI_startmenu(primaryStage);
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
