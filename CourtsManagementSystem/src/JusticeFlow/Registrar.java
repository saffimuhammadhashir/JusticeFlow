package JusticeFlow;

import java.util.List;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import JusticeFlow.CourtsManagementSystem.GUI_Menu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Registrar extends User {
    private int RegistrarID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Date hireDate;
    private int courtID;
    private String email;
    private String phoneNumber;
    private int userID;

    public Registrar() {
        super();
    }

    public Registrar(int userID, String username, String password, String role, String email, String phoneNumber,
            boolean activate, int RegistrarID, String firstName, String lastName, Date dateOfBirth,
            String gender, Date hireDate, int courtID) {
        super(userID, username, password, role, email, phoneNumber, activate); // Call to User class constructor

        this.RegistrarID = RegistrarID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.hireDate = hireDate;
        this.courtID = courtID;
        this.userID = userID;
    }

    public int getRegistrarID() {
        return RegistrarID;
    }

    public void setRegistrarID(int RegistrarID) {
        this.RegistrarID = RegistrarID;
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
        return "Registrar {" +
                "judgeID=" + RegistrarID +
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

    public void ApproveDocument(DatabaseHandler dbHandler, Scanner scanner, List<Case> AllCases,
            FileHandler fileHandler, Stage primaryStage,
            Scene previousScene) {

        // Title Label
        Label titleLabel = new Label("Approve Document for Case!");
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
            if (cases.getFiles() != null) {

                // Create a GridPane for each case
                GridPane eachCase = new GridPane();
                eachCase.setHgap(10); // Horizontal gap between columns
                eachCase.setVgap(10); // Vertical gap between rows
                eachCase.setStyle(
                        "-fx-padding: 10px; -fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-effect: innershadow(gaussian, #000000, 5, 0.5, 0, 0);");

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

                    showFiles(dbHandler, fileHandler, AllCases, primaryStage, previousScene, cases);
                    // cases.setCaseStatus("Opened");
                    dbHandler.saveOrUpdateCase(cases);

                });

                // Add buttons to GridPane, starting from row 1
                GridPane.setConstraints(approveButton, 0, 3); // Place in column 1, row 1

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

    public void ApproveJudgement(DatabaseHandler dbHandler, Scanner scanner, List<Case> AllCases, FileHandler fileHandler, Stage primaryStage,
            Scene previousScene) {
        // Title Label
        Label titleLabel = new Label("Approve Judgement for Case!");
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
            if (cases.getJudgements() != null) {

                // Create a GridPane for each case
                GridPane eachCase = new GridPane();
                eachCase.setHgap(10); // Horizontal gap between columns
                eachCase.setVgap(10); // Vertical gap between rows
                eachCase.setStyle(
                        "-fx-padding: 10px; -fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-effect: innershadow(gaussian, #000000, 5, 0.5, 0, 0);");

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

                    showJudgements(dbHandler, fileHandler, AllCases, primaryStage, previousScene, cases);
                    // cases.setCaseStatus("Opened");
                    dbHandler.saveOrUpdateCase(cases);

                });

                // Add buttons to GridPane, starting from row 1
                GridPane.setConstraints(approveButton, 0, 3); // Place in column 1, row 1

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

    public void scheduleHearing(Scanner scanner, List<Case> AllCases, List<Slot> AllSlots, List<Judge> AllJudges,
            List<Court> AllCourts, List<Witness> AllWitnesses, FileHandler fileHandler,
            DatabaseHandler databaseHandler, Stage primaryStage, Scene prevScene) {

        // Main layout container
        VBox mainLayout = new VBox(20);
        mainLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // Title label
        Label titleLabel = new Label("Schedule a Hearing");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");
        mainLayout.getChildren().add(titleLabel);

        // Scrollable ViewBox for displaying cases
        ScrollPane caseScrollPane = new ScrollPane();
        VBox caseViewBox = new VBox(10);
        caseViewBox.setStyle(
                "-fx-padding: 10px; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-border-radius: 10px; -fx-background-radius: 10px;");

        Label casesLabel = new Label("Available Cases:");
        casesLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        caseViewBox.getChildren().add(casesLabel);

        for (Case caseObj : AllCases) {
            Label caseEntry = new Label(caseObj.toString());
            caseEntry.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5;");
            caseViewBox.getChildren().add(caseEntry);
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
            primaryStage.setScene(prevScene); // Go back to case selection scene
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

        // Transition to slots after CaseID input
        nextButton.setOnAction(e -> {
            try {
                int caseId = Integer.parseInt(caseIdField.getText());
                boolean caseExists = AllCases.stream().anyMatch(c -> c.getCaseID() == caseId);
                if (!caseExists) {
                    System.out.println("Invalid Case ID.");
                    return;
                }

                VBox slotLayout = new VBox(20);
                slotLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

                Label slotTitleLabel = new Label("Available Slots:");
                slotTitleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");
                slotLayout.getChildren().add(slotTitleLabel);

                ScrollPane slotScrollPane = new ScrollPane();
                VBox slotViewBox = new VBox(10);
                slotViewBox.setStyle(
                        "-fx-padding: 10px; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-border-radius: 10px; -fx-background-radius: 10px;");

                for (Slot slotObj : AllSlots) {
                    Label slotEntry = new Label(slotObj.toString());
                    slotEntry.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5;");
                    slotViewBox.getChildren().add(slotEntry);
                }

                slotScrollPane.setContent(slotViewBox);
                slotScrollPane.setFitToWidth(true);
                slotScrollPane.setPrefHeight(300);
                slotLayout.getChildren().add(slotScrollPane);

                Label slotIdLabel = new Label("Enter Slot ID:");
                slotIdLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
                TextField slotIdField = new TextField();
                slotIdField.setPromptText("Slot ID");
                slotIdField.setPrefWidth(200);

                Button submitButton = new Button("Submit");
                submitButton.setStyle(
                        "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

                // Create an HBox to place the "Back" and "Submit" buttons in a line
                HBox buttonLayout = new HBox(20, backButton, submitButton);
                buttonLayout.setAlignment(Pos.CENTER); // Align buttons to the center

                Label successLabel = new Label();
                successLabel.setStyle("-fx-text-fill: lime; -fx-font-size: 14px; -fx-font-weight: bold;");

                VBox slotInputSection = new VBox(10, slotIdLabel, slotIdField, buttonLayout, successLabel);
                slotInputSection.setStyle("-fx-alignment: center;");
                slotLayout.getChildren().add(slotInputSection);

                // Set scene to slot selection
                Scene slotScene = new Scene(slotLayout, 1100, 650);
                slotLayout.setStyle(
                        "-fx-padding: 80px; -fx-alignment: center; -fx-background-size: stretch; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-image: url('file:///D:/Github/JusticeFlow/CourtsManagementSystem/lib/resources/img(3).jpeg');");
                primaryStage.setScene(slotScene);

                submitButton.setOnAction(ev -> {
                    try {
                        int slotId = Integer.parseInt(slotIdField.getText());
                        Slot selectedSlot = AllSlots.stream().filter(s -> s.getSlotID() == slotId).findFirst()
                                .orElse(null);

                        if (selectedSlot == null || selectedSlot.getCaseID() != null) {
                            System.out.println("Invalid or unavailable Slot ID.");
                            return;
                        }

                        selectedSlot.setCaseID(caseId);
                        databaseHandler.updateOrInsertSlots(AllSlots);
                        System.out.println("Hearing Scheduled for Slot ID: " + slotId);

                        // Show success message
                        successLabel.setText("Hearing successfully scheduled for Slot ID: " + slotId + "!");
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid Slot ID input.");
                    }
                });
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Case ID input.");
            }
        });

        // Set scene and display
        Scene caseScene = new Scene(mainLayout, 1100, 650);
        mainLayout.setStyle(
                "-fx-padding: 80px; -fx-alignment: center; -fx-background-size: stretch; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-image: url('file:///D:/Github/JusticeFlow/CourtsManagementSystem/lib/resources/img(3).jpeg');");
        primaryStage.setScene(caseScene);
        primaryStage.show();
    }

    public void ApproveDocument(Scanner scanner, List<Case> AllCases, FileHandler fileHandler) {
        // Print
        boolean exists = false;
        // System.out.println("\nCases:");
        for (Case caseObj : AllCases) {
            if (caseObj.getFiles() != null) {
                for (CaseFile fileObj : caseObj.getFiles()) {
                    if (fileObj.getStatus() == 0) {
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    break;
                }
            }
        }

        if (exists) {
            System.out.println("\nCases:");
            for (Case caseObj : AllCases) {
                if (caseObj.getFiles() != null) {
                    System.out.println(caseObj.toString());
                }
            }

            System.out.print("\nEnter CaseID: ");
            int cid = scanner.nextInt();

            Case c = new Case();
            c = c.getCasebyID(cid, AllCases);
            List<CaseFile> files = c.getFiles();

            int count = 1;
            System.out.print("Files in this Case: ");
            for (CaseFile file : files) {
                StringBuilder fileDetails = new StringBuilder();
                fileDetails.append("").append(file.toString());
                System.out.println(count + ". " + fileDetails.toString());
                count++;
            }

            System.out.print("Enter Document Number to approve: ");
            int id = scanner.nextInt();

            count = 1;
            for (CaseFile file : files) {
                if (id == count) {
                    if (file.getStatus() == 0) {
                        file.setStatus(1);
                        DatabaseHandler d = new DatabaseHandler();
                        d.updateFileDetails(c.getCaseID(), file.getFileName(), file.getFileHash(), false, true);
                        System.out.println("File Status updated in Database.");
                        return;
                    }
                }
                count++;
            }

        } else {
            System.out.println("No Document To Approve.");
            return;
        }
    }

    public void ApproveJudgement(Scanner scanner, List<Case> AllCases, FileHandler fileHandler) {
        // Print
        boolean exists = false;
        // System.out.println("\nCases:");
        for (Case caseObj : AllCases) {
            if (caseObj.getJudgements() != null) {
                for (CaseFile fileObj : caseObj.getJudgements()) {
                    if (fileObj.getStatus() == 2) {
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    break;
                }
            }
        }

        if (exists) {
            System.out.println("\nCases:");
            for (Case caseObj : AllCases) {
                if (caseObj.getJudgements() != null) {
                    System.out.println(caseObj.toString());
                }
            }

            System.out.print("\nEnter CaseID: ");
            int cid = scanner.nextInt();

            Case c = new Case();
            c = c.getCasebyID(cid, AllCases);
            List<CaseFile> judgements = c.getJudgements();

            int count = 1;
            System.out.println("Judgements in this Case: ");
            for (CaseFile judgement : judgements) {
                StringBuilder fileDetails = new StringBuilder();
                fileDetails.append("").append(judgement.toString());
                System.out.println(count + ". " + fileDetails.toString());
                count++;
            }

            System.out.print("Enter Document Number to approve: ");
            int id = scanner.nextInt();

            count = 1;
            for (CaseFile judgement : judgements) {
                if (id == count) {
                    if (judgement.getStatus() == 2) {
                        judgement.setStatus(3);
                        DatabaseHandler d = new DatabaseHandler();
                        d.updateJudgementDetails(c.getCaseID(), judgement.getFileName(), judgement.getFileHash(), 2, 3);
                        System.out.println("File Status updated in Database.");
                        return;
                    }
                }
                count++;
            }

        } else {
            System.out.println("No Judgement To Approve.");
            return;
        }
    }

    public void scheduleHearing(Scanner scanner, List<Case> AllCases, List<Slot> AllSlots, List<Judge> AllJudges,
            List<Court> AllCourts, List<Witness> AllWitnesses, FileHandler fileHandler,
            DatabaseHandler databaseHandler) {

        System.out.println("\nCases:");
        for (Case caseObj : AllCases) {
            System.out.println(caseObj.toString());
        }

        System.out.print("\nEnter CaseID: ");
        int cid = scanner.nextInt();

        System.out.println("\nSlots:");
        for (Slot slotObj : AllSlots) {
            System.out.println(slotObj.toString());
        }

        System.out.print("\nEnter SlotID: ");
        int sid = scanner.nextInt();
        scanner.nextLine();

        Slot s = new Slot();
        for (Slot slotObj : AllSlots) {
            if (slotObj.getSlotID() == sid) {
                s = slotObj;
            }
        }

        if (s.getJudgeID() == null) {
            int jid;
            int courtid;
            for (Slot slotObj : AllSlots) {
                if (slotObj != null) {
                    if (slotObj.getCaseID() != null) {
                        if (slotObj.getCaseID() == cid) {
                            jid = slotObj.getJudgeID();
                            courtid = slotObj.getCourtId();
                            s.setJudgeID(jid);
                            s.setCourtId(courtid);
                        }
                    }
                }
            }

            s.setCaseID(cid);

            databaseHandler.updateOrInsertSlots(AllSlots);
            System.out.println("Hearing Scheduled for Slot " + s.getSlotID());
        } else {
            System.out.println("Slot not free.");
        }
    }

    public void ReviewCaseRequest(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            List<Slot> AllSlots, List<Judge> AllJudges, List<Witness> AllWitnesses, List<Court> AllCourts,
            Scanner scanner) {
        List<Case> PendingCases = new ArrayList<>();
        for (Case cases : AllCases) {
            if ("Pending".equalsIgnoreCase(cases.getCaseStatus())) {
                PendingCases.add(cases);
            }
        }

        if (!PendingCases.isEmpty()) {
            for (Case c : PendingCases) {
                System.out.println(c.toString());
                System.out.println("\n------------------------------------------- \n");
            }

            System.out.print("Select Case: ");
            int caseID = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            System.out.print("Select Approve or Reject: ");
            String caseDecision = scanner.nextLine(); // Now this works correctly

            Case cases = dbHandler.findCaseByID(AllCases, caseID);

            if ("Approve".equalsIgnoreCase(caseDecision)) {
                cases.setCaseStatus("Opened");
                List<Slot> possibleSlots = new ArrayList<>();
                dbHandler.saveOrUpdateCase(cases);
                for (Witness w : AllWitnesses) {
                    for (Integer caseid : w.CaseID) {
                        if (caseid.equals(cases.getCaseID())) {
                            for (Judge j : AllJudges) {
                                for (Court c : AllCourts) {

                                    Slot.PossibleSchedule(AllSlots, j, w, c, possibleSlots);

                                }
                            }
                        }
                    }
                }
                if (possibleSlots.isEmpty()) {
                    for (Judge j : AllJudges) {
                        for (Court c : AllCourts) {

                            Slot.PossibleSchedule(AllSlots, j, null, c, possibleSlots);

                        }
                    }
                }
                while (possibleSlots.size() > 50) {
                    Slot lastSlot = Slot.getLastSlotAtFarthestTime(possibleSlots);
                    Slot.removeSlotsWithSameIDOneByOne(lastSlot, possibleSlots);
                }
                System.out.println("\n\n\n********************************************************\n\n\n");
                int Count = 1;
                for (Slot s : possibleSlots) {
                    if (s == null) {
                        break;
                    }
                    System.out.println(Count + ". " + s.toString());
                    Count++;
                }
                System.out.print("\n\nSelect Slot: ");
                int slotid = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character left by nextInt()
                Count = 1;
                for (Slot s : possibleSlots) {
                    if (s == null) {
                        break;
                    }
                    if (Count == slotid) {
                        boolean selected = false;
                        for (Slot orgs : AllSlots) {
                            if (orgs.getSlotID() == s.getSlotID()) {
                                orgs.setCaseID(cases.getCaseID());
                                orgs.setJudgeID(s.getJudgeID());
                                orgs.setWitnessID(s.getWitnessID());
                                orgs.setCourtId(s.getCourtId());
                                selected = true;
                            }
                        }
                        if (selected) {
                            dbHandler.updateOrInsertSlots(AllSlots);
                            System.out.println("Slot Selected!");
                            return;
                        }
                    }

                    Count++;
                }

                // ApproveCase(dbHandler, fileHandler, caseID);
            } else if ("Reject".equalsIgnoreCase(caseDecision)) {
                cases.setCaseStatus("Not Allowed");
                dbHandler.saveOrUpdateCase(cases);
                // RejectCase(dbHandler, fileHandler, caseID);
            } else {
                System.out.println("Invalid Input!");
            }
        } else {
            System.out.println("No Pending Requests!");
        }
    }

    public void showandscheduleslots(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            List<Slot> AllSlots, List<Judge> AllJudges, List<Witness> AllWitnesses, List<Court> AllCourts,
            Stage primaryStage, GUI_Menu gui, Case cases) {
        Label titleLabel = new Label("Select Slots from here!");
        titleLabel.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333333; -fx-padding: 20px 0; -fx-alignment: center;");

        // ScrollPane containing the case list
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setFitToWidth(true);
        formScrollPane.setStyle("-fx-background-color: #f4f4f9;");

        VBox formLayout = new VBox(20); // Increased spacing for clarity
        formLayout.setStyle("-fx-padding: 20px;");
        System.out.println(cases.toString());
        List<Slot> possibleSlots = new ArrayList<>();
        int caseID = cases.getCaseID();
        for (Witness w : AllWitnesses) {
            for (Integer caseid : w.CaseID) {
                if (caseid.equals(cases.getCaseID())) {
                    for (Judge j : AllJudges) {
                        for (Court c : AllCourts) {

                            Slot.PossibleSchedule(AllSlots, j, w, c, possibleSlots);

                        }
                    }
                }
            }
        }
        if (possibleSlots.isEmpty()) {
            for (Judge j : AllJudges) {
                for (Court c : AllCourts) {

                    Slot.PossibleSchedule(AllSlots, j, null, c, possibleSlots);

                }
            }
        }
        while (possibleSlots.size() > 50) {
            Slot lastSlot = Slot.getLastSlotAtFarthestTime(possibleSlots);
            Slot.removeSlotsWithSameIDOneByOne(lastSlot, possibleSlots);
        }
        for (Slot slot : possibleSlots) {
            if (ValidSlotTime(AllSlots, slot)) {

                // Create a GridPane for each case
                GridPane eachCase = new GridPane();
                eachCase.setHgap(10); // Horizontal gap between columns
                eachCase.setVgap(10); // Vertical gap between rows
                eachCase.setStyle(
                        "-fx-padding: 10px; -fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-effect: innershadow(gaussian, #000000, 5, 0.5, 0, 0);");

                // Case Title Label
                Label caseName = new Label(slot.dayName.toString());
                caseName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label casetype = new Label("Judge ID : " + slot.getJudgeID());
                casetype.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label caseplaintiff = new Label("Witness ID : " + slot.getWitnessID());
                caseplaintiff.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label casedefendant = new Label("Court ID: " + slot.getCourtId());
                casedefendant.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label CaseFiling = new Label("Start Time: " + slot.getStartTime());
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
                    boolean isSlotUpdated = false; // Flag to track if the slot is updated

                    for (Slot org : new ArrayList<>(AllSlots)) { // Iterate over a copy to avoid concurrent modification
                        if (org.getSlotID() == slot.getSlotID()) {
                            if (org.getCaseID() != null) { // Slot is already assigned
                                slot.setSlotID(generateUniqueSlotID(AllSlots)); // Generate a unique Slot ID
                                AllSlots.add(slot);
                            } else { // Slot is available
                                org.setCaseID(cases.getCaseID());
                                org.setJudgeID(slot.getJudgeID());
                                org.setWitnessID(slot.getWitnessID());
                                org.setCourtId(slot.getCourtId());
                            }
                            isSlotUpdated = true;
                            break; // Exit the loop once the slot is processed
                        }
                    }

                    if (isSlotUpdated) {
                        dbHandler.updateOrInsertSlots(AllSlots); // Update the database with changes
                        // ReviewCaseRequest(dbHandler, fileHandler, AllCases, AllSlots, AllJudges,
                        // AllWitnesses,
                        // AllCourts,
                        // primaryStage, gui);
                    }

                });

                // Add buttons to GridPane, starting from row 1
                GridPane.setConstraints(approveButton, 0, 3); // Place in column 1, row 1
                // Place in column 2, row 1

                eachCase.getChildren().addAll(approveButton);

                // Add the GridPane to the formLayout
                formLayout.getChildren().add(eachCase);
            }
        }

        // Set the VBox into the ScrollPane and display it
        formScrollPane.setContent(formLayout);
        VBox rootLayout = new VBox(20);
        rootLayout.getChildren().addAll(titleLabel, formScrollPane);
        rootLayout.setStyle("-fx-background-color: #f4f4f9; -fx-padding: 20px;");

        Scene registerScene = new Scene(rootLayout, 1000, 700);
        primaryStage.setScene(registerScene);
        primaryStage.show();
        //
    }

    public void showFiles(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            Stage primaryStage, Scene previousScene, Case cases) {
        Label titleLabel = new Label("Approve Files over here!");
        titleLabel.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333333; -fx-padding: 20px 0; -fx-alignment: center;");

        // ScrollPane containing the case list
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setFitToWidth(true);
        formScrollPane.setStyle("-fx-background-color: #f4f4f9;");

        VBox formLayout = new VBox(20); // Increased spacing for clarity
        formLayout.setStyle("-fx-padding: 20px;");

        // Iterate over all cases and add only pending cases
        for (CaseFile file : cases.getFiles()) {
            if (file.getStatus() == 0) {
                // Create a GridPane for each case
                GridPane eachCase = new GridPane();
                eachCase.setHgap(10); // Horizontal gap between columns
                eachCase.setVgap(10); // Vertical gap between rows
                eachCase.setStyle(
                        "-fx-padding: 10px; -fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-effect: innershadow(gaussian, #000000, 5, 0.5, 0, 0);");

                // Case Title Label
                Label caseName = new Label(cases.getCaseTitle());
                caseName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label casetype = new Label("File Name: " + file.getFileName());
                casetype.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                // Add the case title to the first column, first row
                GridPane.setConstraints(caseName, 0, 0);
                GridPane.setConstraints(casetype, 0, 1);
                eachCase.getChildren().add(caseName);
                eachCase.getChildren().add(casetype);

                // Approve and Reject Buttons
                Button approveButton = new Button("Approve");
                approveButton.setStyle(
                        "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5px 15px; -fx-border-radius: 5px;");

                // Button Actions (for demonstration purposes, implement logic later)
                approveButton.setOnAction(e -> {
                    try {
                        dbHandler.updateFileDetails(cases.getCaseID(), file.getFileName(), file.getFileHash(), false, true);
                    } catch (NumberFormatException ex) {
                        Label invalidInputLabel = new Label("Invalid input for Document Number.");
                        invalidInputLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        formLayout.getChildren().add(invalidInputLabel);
                    }
                });

                // Add buttons to GridPane, starting from row 1
                GridPane.setConstraints(approveButton, 0, 3); // Place in column 1, row 1

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

    public void showJudgements(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            Stage primaryStage, Scene previousScene, Case cases) {
        Label titleLabel = new Label("Approve Judgements over here!");
        titleLabel.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333333; -fx-padding: 20px 0; -fx-alignment: center;");

        // ScrollPane containing the case list
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setFitToWidth(true);
        formScrollPane.setStyle("-fx-background-color: #f4f4f9;");

        VBox formLayout = new VBox(20); // Increased spacing for clarity
        formLayout.setStyle("-fx-padding: 20px;");

        // Iterate over all cases and add only pending cases
        for (CaseFile file : cases.getJudgements()) {
            if (file.getStatus() == 2) {
                // Create a GridPane for each case
                GridPane eachCase = new GridPane();
                eachCase.setHgap(10); // Horizontal gap between columns
                eachCase.setVgap(10); // Vertical gap between rows
                eachCase.setStyle(
                        "-fx-padding: 10px; -fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-effect: innershadow(gaussian, #000000, 5, 0.5, 0, 0);");

                // Case Title Label
                Label caseName = new Label(cases.getCaseTitle());
                caseName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label casetype = new Label("File Name: " + file.getFileName());
                casetype.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                // Add the case title to the first column, first row
                GridPane.setConstraints(caseName, 0, 0);
                GridPane.setConstraints(casetype, 0, 1);
                eachCase.getChildren().add(caseName);
                eachCase.getChildren().add(casetype);

                // Approve and Reject Buttons
                Button approveButton = new Button("Approve");
                approveButton.setStyle(
                        "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5px 15px; -fx-border-radius: 5px;");

                // Button Actions (for demonstration purposes, implement logic later)
                approveButton.setOnAction(e -> {
                    try {
                        dbHandler.updateJudgementDetails(cases.getCaseID(), file.getFileName(), file.getFileHash(), 2, 3);
                    } catch (NumberFormatException ex) {
                        Label invalidInputLabel = new Label("Invalid input for Document Number.");
                        invalidInputLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        formLayout.getChildren().add(invalidInputLabel);
                    }
                });

                // Add buttons to GridPane, starting from row 1
                GridPane.setConstraints(approveButton, 0, 3); // Place in column 1, row 1

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


    private int generateUniqueSlotID(List<Slot> allSlots) {
        return allSlots.stream().mapToInt(Slot::getSlotID).max().orElse(0) + 1;
    }

    private boolean ValidSlotTime(List<Slot> AllSlots, Slot slot) {
        for (Slot s : AllSlots) {
            if (s.getCourtId() == slot.getCourtId() &&
                    s.getDayName().equals(slot.getDayName()) && // Use equals() for string comparison
                    s.getStartTime().equals(slot.getStartTime())) { // Use equals() for time comparison
                return false;
            }

        }
        return true;
    }

    public void ReviewCaseRequest(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            List<Slot> AllSlots, List<Judge> AllJudges, List<Witness> AllWitnesses, List<Court> AllCourts,
            Stage primaryStage, GUI_Menu gui, CourtsManagementSystem system) {

        List<Case> PendingCases = new ArrayList<>();

        // Title Label
        Label titleLabel = new Label("Approve Cases to be Filed!");
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
            if ("Pending".equalsIgnoreCase(cases.getCaseStatus())) {

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
                Button approveButton = new Button("Approve");
                approveButton.setStyle(
                        "-fx-background-color: #27ae60; " +
                                "-fx-text-fill: white; " +
                                "-fx-font-size: 14px; " +
                                "-fx-font-weight: bold; " +
                                "-fx-padding: 10px 20px; " +
                                "-fx-border-radius: 8px; " +
                                "-fx-background-radius: 8px;");

                Button rejectButton = new Button("Reject");
                rejectButton.setStyle(
                        "-fx-background-color: #e74c3c; " +
                                "-fx-text-fill: white; " +
                                "-fx-font-size: 14px; " +
                                "-fx-font-weight: bold; " +
                                "-fx-padding: 10px 20px; " +
                                "-fx-border-radius: 8px; " +
                                "-fx-background-radius: 8px;");
                approveButton.setOnAction(e -> {
                    // showandscheduleslots(dbHandler, fileHandler, AllCases, AllSlots, AllJudges,
                    // AllWitnesses, AllCourts,
                    // primaryStage, gui, cases);
                    Slot.newSlotCreation(AllSlots, AllJudges, AllCourts, AllWitnesses, cases, dbHandler, primaryStage,
                            gui, system);

                });
                rejectButton.setOnAction(e -> {
                    cases.setCaseStatus("Not Allowed");
                    dbHandler.saveOrUpdateCase(cases);
                    ReviewCaseRequest(dbHandler, fileHandler, AllCases, AllSlots, AllJudges, AllWitnesses, AllCourts,
                            primaryStage, gui, system);

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

    public void UpdateCase(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            List<Slot> AllSlots, List<Judge> AllJudges, List<Witness> AllWitnesses, List<Court> AllCourts,
            Scanner scanner) {

        if (!AllCases.isEmpty()) {
            for (Case c : AllCases) {
                System.out.println(c.toString());
                System.out.println("\n------------------------------------------- \n");
            }

            System.out.print("Select Case: ");
            int caseID = scanner.nextInt();
            scanner.nextLine();

            Case cases = dbHandler.findCaseByID(AllCases, caseID);

            if (cases != null) {
                for (Slot s : AllSlots) {
                    print(s.toString());
                }
                System.out.print("Select Slot Id: ");
                int slotID = scanner.nextInt();
                scanner.nextLine();

                for (Slot s : AllSlots) {
                    if (s.getSlotID() == slotID) {

                        print(s.toString());
                        Slot.removeprevious(AllSlots, cases, s);
                        dbHandler.updateOrInsertSlots(AllSlots);
                        System.out.println("Slot Selected!");
                        return;
                    }
                }

            } else {
                System.out.println("Invalid Input!");
            }
        } else {
            System.out.println("No Pending Requests!");
        }
    }

    public BarApplication getApplicationById(List<BarApplication> AllApplications, int id) {
        for (BarApplication b : AllApplications) {
            if (b.getApplicationTableId() == id) {
                return b;
            }
        }
        return null;
    }

    public void RegistertoBar(List<BarAssociation> barassociationlist, List<BarApplication> AllApplications,
            DatabaseHandler dbHandler, Scanner scanner) {
        boolean running = true;
        while (running) {
            // Show all applications
            for (BarApplication b : AllApplications) {
                print(b.toString());
            }
            // Approve
            print("Enter the Application ID to approve:");
            int Id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Prompt user with options
            print("\nSelect an option:");
            print("1. Approve");
            print("2. Reject");
            print("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:

                    BarApplication approvedApp = getApplicationById(AllApplications, Id);
                    if (approvedApp != null) {
                        approvedApp.setStatus(1); // Assuming 1 means approved
                        print("Approved Application ID: " + Id);
                        dbHandler.updateBarApplication(approvedApp);
                    } else {
                        print("Invalid Application ID!");
                    }
                    break;
                case 2:

                    BarApplication rejectedApp = getApplicationById(AllApplications, Id);
                    if (rejectedApp != null) {
                        rejectedApp.setStatus(2); // Assuming -1 means rejected
                        print("Rejected Application ID: " + Id);
                        dbHandler.updateBarApplication(rejectedApp);
                    } else {
                        print("Invalid Application ID!");
                    }
                    break;
                case 3:
                    // Exit to previous function
                    running = false;
                    break;
                default:
                    print("Invalid option! Please try again.");
                    break;
            }
        }
    }

    public void RegistertoBar(List<BarAssociation> barAssociationList, List<BarApplication> AllApplications,
            DatabaseHandler dbHandler, Stage primaryStage, GUI_Menu gui) {

        // Create the primary layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #f9f9f9; -fx-alignment: center;");

        // Title label
        Label titleLabel = new Label("Bar Association Applications");
        titleLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        mainLayout.getChildren().add(titleLabel);

        // ScrollPane for the list of applications
        VBox applicationList = new VBox(15);
        applicationList.setStyle("-fx-alignment: center;");

        for (BarApplication application : AllApplications) {
            HBox applicationBox = new HBox(15);
            applicationBox.setPadding(new Insets(10));
            applicationBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ddd; -fx-border-width: 1px;");
            applicationBox.setAlignment(Pos.CENTER);

            // Display application details
            Label applicationLabel = new Label(
                    "Application ID: " + application.getApplicationTableId() +
                            " | Name: " + application.getLawyerId() + " | Barid: " + application.getBarId() +
                            " | Status: " + (application.getStatus() == 0 ? "Pending"
                                    : (application.getStatus() == 2 ? "Rejected" : "Approved")));
            applicationLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555;-fx-alignment: center;");
            applicationLabel.setWrapText(true);
            applicationLabel.setMaxWidth(400);

            // Approve button
            Button approveButton = new Button("Approve");
            approveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            approveButton.setOnAction(event -> {
                application.setStatus(1);
                dbHandler.updateBarApplication(application);
                applicationLabel.setText(
                        "Application ID: " + application.getApplicationTableId() +
                                " | Name: " + application.getLawyerId() +
                                " | Status: Approved");
            });

            // Reject button
            Button rejectButton = new Button("Reject");
            rejectButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");
            rejectButton.setOnAction(event -> {
                application.setStatus(2);
                dbHandler.updateBarApplication(application);
                applicationLabel.setText(
                        "Application ID: " + application.getApplicationTableId() +
                                " | Name: " + application.getLawyerId() +
                                " | Status: Rejected");
            });

            // Add components to the application box
            applicationBox.getChildren().addAll(applicationLabel, approveButton, rejectButton);
            applicationList.getChildren().add(applicationBox);
        }

        // ScrollPane to handle the dynamic content
        ScrollPane scrollPane = new ScrollPane(applicationList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        // Close button
        Button returnButton = new Button("Close");
        returnButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px;");
        returnButton.setMaxWidth(Double.MAX_VALUE); // Make button stretch to fill width
        returnButton.setOnAction(e -> {
            gui.GUI_startmenu(primaryStage);
        });

        // Add elements to the main layout
        mainLayout.getChildren().addAll(scrollPane, returnButton);

        // Set up the scene and show the stage
        Scene scene = new Scene(mainLayout, 1000, 600);
        primaryStage.setTitle("Register to Bar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}