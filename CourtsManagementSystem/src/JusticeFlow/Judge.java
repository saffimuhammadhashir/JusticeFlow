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

public class Judge extends User {
    private int judgeID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Date appointmentDate;
    private int courtID;
    private String email;
    private String phoneNumber;
    private int userID;

    public Judge() {

    }

    public Judge(int userID, String username, String password, String role, String email, String phoneNumber,
            boolean activate,
            int judgeID, String firstName, String lastName, Date dateOfBirth, String gender,
            Date appointmentDate, int courtID) {
        super(userID, username, password, role, email, phoneNumber, activate); // Calling the constructor of User class

        this.judgeID = judgeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.appointmentDate = appointmentDate;
        this.courtID = courtID;
        this.userID = userID;
    }

    public int getJudgeID() {
        return judgeID;
    }

    public void setJudgeID(int judgeID) {
        this.judgeID = judgeID;
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

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
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
        return "Judge {" +
                "judgeID=" + judgeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", courtID=" + courtID +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userID=" + userID +
                '}';
    }

    public void LogJudgement(Scanner scanner, List<Case> AllCases, List<Slot> AllSlots, FileHandler fileHandler,
            Stage primaryStage, Scene previousScene) {

        // Create main layout for the log judgment process
        VBox mainLayout = new VBox(20);
        mainLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // Title label
        Label titleLabel = new Label("Log Judgment for Case");
        titleLabel.setStyle("-fx-text-fill: black; -fx-font-size: 22px; -fx-font-weight: bold;");

        // ScrollPane for displaying the list of cases
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setFitToWidth(true);
        formScrollPane.setStyle("-fx-background-color: #f4f4f9;");

        VBox formLayout = new VBox(20); // Container for case entries
        formLayout.setStyle("-fx-padding: 20px;");

        // Iterate over all cases and add relevant ones
        for (Case cases : AllCases) {
            boolean slotExists = false;
            for (Slot s : AllSlots) {
                if (s.getJudgeID() != null && s.getJudgeID() == judgeID && s.getCaseID() == cases.getCaseID()) {
                    slotExists = true;
                    break;
                }
            }
            if (slotExists) {
                // Create a GridPane for displaying case details
                GridPane eachCase = new GridPane();
                eachCase.setHgap(10);
                eachCase.setVgap(10);
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

                // Case details
                Label caseName = new Label(cases.getCaseTitle());
                caseName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                Label casetype = new Label("Case Type: " + cases.getCaseType());
                casetype.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333;");

                Label caseplaintiff = new Label("Plaintiff ID: " + cases.getPlaintiffID());
                caseplaintiff.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333;");

                Label casedefendant = new Label("Defendant ID: " + cases.getDefendantID());
                casedefendant.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333;");

                Label caseFiling = new Label("Filing Date: " + cases.getFilingDate());
                caseFiling.setStyle("-fx-font-size: 16px; -fx-text-fill: red;");

                // Add details to the GridPane
                eachCase.add(caseName, 0, 0);
                eachCase.add(casetype, 0, 1);
                eachCase.add(caseplaintiff, 1, 0);
                eachCase.add(casedefendant, 1, 1);
                eachCase.add(caseFiling, 2, 0);

                // Select Button
                Button selectButton = new Button("Select");
                selectButton.setStyle(
                        "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5px 15px; -fx-border-radius: 5px;");
                selectButton.setOnAction(e -> {
                    File selectedFile = fileHandler.openFileDialog();
                    if (selectedFile != null) {
                        try {
                            String fileHash = fileHandler.getFileHash(selectedFile.getAbsolutePath());
                            CaseFile judgmentFile = new CaseFile(selectedFile.getAbsolutePath(), fileHash, 2);
                            cases.addJudgement(judgmentFile);

                            DatabaseHandler dbHandler = new DatabaseHandler();
                            dbHandler.addJudgement(cases.getCaseID(), judgmentFile.getFileName(),
                                    judgmentFile.getFileHash(), 2);

                            Label successLabel = new Label(
                                    "Judgment submitted successfully! Waiting for Registrar approval.");
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
                });

                // Add button to the GridPane
                eachCase.add(selectButton, 0, 3);

                // Add the case entry to the formLayout
                formLayout.getChildren().add(eachCase);
            }
        }

        // Add the formLayout to the ScrollPane
        formScrollPane.setContent(formLayout);

        // Close button
        Button returnButton = new Button("Close");
        returnButton.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px;");
        returnButton.setMaxWidth(Double.MAX_VALUE);
        returnButton.setOnAction(e -> primaryStage.setScene(previousScene));

        // Assemble the final layout
        mainLayout.getChildren().addAll(titleLabel, formScrollPane, returnButton);

        // Set the scene
        Scene registerScene = new Scene(mainLayout, 1000, 700);
        primaryStage.setScene(registerScene);
        primaryStage.show();
    }

    public void LogJudgement(Scanner scanner, List<Case> AllCases, FileHandler fileHandler) {
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

                    CaseFile my_file = new CaseFile(selectedFile.getName(), fileHash);
                    c.addJudgement(my_file);
                    System.out.println("Judgement added to case, waiting for Registrar to approve.");

                    System.out.println(c.toString());

                    DatabaseHandler d = new DatabaseHandler();
                    d.addJudgement(c.getCaseID(), selectedFile.getName(), fileHash, 2);
                    System.out.println("Judgement Added in Database.");
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

    public void TrackCase(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            List<Slot> AllSlots, List<Clients> allclients, List<Judge> AllJudges, List<Lawyer> AllLawyers,
            List<Witness> AllWitnesses, List<Court> AllCourts,
            Stage primaryStage, GUI_Menu gui, CourtsManagementSystem system) {
        List<Case> PendingCases = new ArrayList<>();

        List<Integer> caseids = new ArrayList<>();
        for (Slot s : AllSlots) {
            if (s.getJudgeID() != null && s.getJudgeID() == this.judgeID) {
                if (!caseids.contains(s.getCaseID())) {
                    caseids.add(s.getCaseID());
                }
            }
        }
        List<Case> Judgecases = new ArrayList<>();
        for (Case c : AllCases) {
            if (caseids.contains(c.getCaseID())) {
                Judgecases.add(c);
            }
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
        for (Case cases : Judgecases) {

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

            Button rejectButton = new Button("Block Case");
            rejectButton.setStyle(
                    "-fx-background-color: #e74c3c; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14px; " +
                            "-fx-font-weight: bold; " +
                            "-fx-padding: 10px 20px; " +
                            "-fx-border-radius: 8px; " +
                            "-fx-background-radius: 8px;");
            approveButton.setOnAction(e -> {
                cases.DisplayDetailsJudge(dbHandler, fileHandler, AllCases, AllSlots, allclients, AllJudges, AllLawyers,
                        AllWitnesses, AllCourts,
                        primaryStage, gui, system);

            });

            rejectButton.setOnAction(e -> {
                cases.setCaseStatus("Not Allowed");
                dbHandler.saveOrUpdateCase(cases);
                TrackCase(dbHandler, fileHandler, AllCases, AllSlots, allclients, AllJudges, AllLawyers, AllWitnesses,
                        AllCourts,
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

    public void TrackUpdates(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            List<Slot> AllSlots, List<Clients> allclients, List<Judge> AllJudges, List<Lawyer> AllLawyers,
            List<Witness> AllWitnesses, List<Court> AllCourts,
            Stage primaryStage, GUI_Menu gui, CourtsManagementSystem system) {
        List<Case> PendingCases = new ArrayList<>();

        VBox rootLayout = new VBox(20);
        List<Integer> caseids = new ArrayList<>();
        for (Slot s : AllSlots) {
            if (s.getJudgeID() != null && s.getJudgeID() == this.judgeID) {
                if (!caseids.contains(s.getCaseID())) {
                    caseids.add(s.getCaseID());
                }
            }
        }
        List<Case> Judgecases = new ArrayList<>();
        for (Case c : AllCases) {
            if (caseids.contains(c.getCaseID())) {
                Judgecases.add(c);
            }
        }

        // Title Label
        Label titleLabel = new Label("Manage Case Updates");
        titleLabel.setStyle(
                "-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333333; -fx-padding: 20px 0; -fx-alignment: center;");

        // ScrollPane containing the case list
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setFitToWidth(true);
        formScrollPane.setStyle("-fx-background-color: #f4f4f9;");

        VBox formLayout = new VBox(20); // Increased spacing for clarity
        formLayout.setStyle("-fx-padding: 20px;");
        Scene registerScene = new Scene(rootLayout, 1000, 700);
        // Iterate over all cases and add only pending cases
        for (Case cases : Judgecases) {

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
            Button approveButton = new Button("Log Judgement");
            approveButton.setStyle(
                    "-fx-background-color: #27ae60; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14px; " +
                            "-fx-font-weight: bold; " +
                            "-fx-padding: 10px 20px; " +
                            "-fx-border-radius: 8px; " +
                            "-fx-background-radius: 8px;");
            Button approveButton2 = new Button("Manage Judgements");
            approveButton2.setStyle(
                    "-fx-background-color: #27ae60; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14px; " +
                            "-fx-font-weight: bold; " +
                            "-fx-padding: 10px 20px; " +
                            "-fx-border-radius: 8px; " +
                            "-fx-background-radius: 8px;");

            Button rejectButton = new Button("Block Case");
            rejectButton.setStyle(
                    "-fx-background-color: #e74c3c; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14px; " +
                            "-fx-font-weight: bold; " +
                            "-fx-padding: 10px 20px; " +
                            "-fx-border-radius: 8px; " +
                            "-fx-background-radius: 8px;");
            approveButton.setOnAction(e -> {
                File selectedFile = fileHandler.openFileDialog();
                if (selectedFile != null) {
                    try {
                        String fileHash = fileHandler.getFileHash(selectedFile.getAbsolutePath());
                        CaseFile judgmentFile = new CaseFile(selectedFile.getAbsolutePath(), fileHash, 2);
                        cases.addJudgement(judgmentFile);

                        dbHandler.addJudgement(cases.getCaseID(), judgmentFile.getFileName(),
                                judgmentFile.getFileHash(), 2);

                        Label successLabel = new Label(
                                "Judgment submitted successfully! Waiting for Registrar approval.");
                        successLabel
                                .setStyle("-fx-text-fill: #4CAF50; -fx-font-size: 16px; -fx-font-weight: bold;");
                        // mainLayout.getChildren().add(successLabel);

                    } catch (IOException | NoSuchAlgorithmException ex) {
                        ex.printStackTrace();
                        Label errorLabel = new Label("Error while processing the file.");
                        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                        // mainLayout.getChildren().add(errorLabel);
                    }
                } else {
                    Label noFileLabel = new Label("No file selected.");
                    noFileLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
                    // mainLayout.getChildren().add(noFileLabel);
                }
            });

            rejectButton.setOnAction(e -> {
                cases.setCaseStatus("Not Allowed");
                dbHandler.saveOrUpdateCase(cases);
                TrackUpdates(dbHandler, fileHandler, AllCases, AllSlots, allclients, AllJudges, AllLawyers,
                        AllWitnesses,
                        AllCourts,
                        primaryStage, gui, system);

            });

            approveButton2.setOnAction(e -> {
              
                cases.viewCaseDetailsJudge(dbHandler, fileHandler, AllCases, AllSlots, allclients, AllJudges, AllLawyers, AllWitnesses, AllCourts, primaryStage, gui, system);
                // dbHandler.saveOrUpdateCase(cases);

            });
            // Add spacing and alignment for buttons
            GridPane.setConstraints(approveButton2, 0, 3);
            GridPane.setConstraints(approveButton, 1, 3);
            GridPane.setConstraints(rejectButton, 2, 3);
            GridPane.setMargin(approveButton, new Insets(10, 10, 10, 0)); // Add spacing around the buttons
            GridPane.setMargin(approveButton2, new Insets(10, 10, 10, 0));
            GridPane.setMargin(rejectButton, new Insets(10, 10, 10, 0));

            // Add buttons to the GridPane
            eachCase.getChildren().addAll(approveButton2,approveButton, rejectButton);

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

        rootLayout.getChildren().addAll(titleLabel, formScrollPane, returnButton);
        rootLayout.setStyle("-fx-background-color: #f4f4f9; -fx-padding: 20px;");

        primaryStage.setScene(registerScene);
        primaryStage.show();

    }

}
