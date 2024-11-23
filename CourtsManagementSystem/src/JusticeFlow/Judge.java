package JusticeFlow;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

    public void LogJudgement(Scanner scanner, List<Case> AllCases, List<Slot> AllSlots, FileHandler fileHandler, Stage primaryStage,Scene previousScene) {
        // Create the main layout for logging a judgment
        VBox mainLayout = new VBox(20);
        mainLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // Title label
        Label titleLabel = new Label("Log Judgement for Case");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");
        mainLayout.getChildren().add(titleLabel);


        VBox lawyerCasesBox = new VBox(10);
        lawyerCasesBox.setStyle(
                "-fx-padding: 10px; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-border-radius: 10px; -fx-background-radius: 10px;");
        ScrollPane scrollPane = new ScrollPane(lawyerCasesBox);
        scrollPane.setPrefSize(600, 200);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        boolean exists = false;
        for (Case c : AllCases) {
            boolean slotExists=false;
            for (Slot s : AllSlots){
                if (s.getJudgeID()!=null){
                    if (s.getJudgeID() == judgeID){
                        slotExists=true;
                        break;
                    }
                }
            }
            if (slotExists==true) {
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

        // Case ID input section
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
            // Clear previous error or success messages
            mainLayout.getChildren().removeIf(node -> node instanceof Label);

            try {
                int caseId = Integer.parseInt(caseIdField.getText());
                Case caseObj = new Case();

                // Check if the case exists
                if (caseObj.doesCaseExist(caseId, AllCases)) {
                    caseObj = caseObj.getCasebyID(caseId, AllCases);

                    // Open file dialog to select a judgment file
                    File selectedFile = fileHandler.openFileDialog();
                    if (selectedFile != null) {
                        try {
                            // Generate file hash
                            String fileHash = fileHandler.getFileHash(selectedFile.getAbsolutePath());

                            // Add judgment to the case
                            CaseFile judgmentFile = new CaseFile(selectedFile.getAbsolutePath(), fileHash);
                            caseObj.addJudgement(judgmentFile);
                            System.out.println("Judgment added to the case, waiting for Registrar approval.");

                            // Update the database
                            DatabaseHandler dbHandler = new DatabaseHandler();
                            dbHandler.addJudgement(caseObj.getCaseID(), selectedFile.getAbsolutePath(), fileHash, 2);
                            System.out.println("Judgment added to the database.");

                            // Show success message in GUI
                            Label successLabel = new Label(
                                    "Judgment added successfully! Waiting for Registrar approval.");
                            successLabel
                                    .setStyle("-fx-text-fill: #4CAF50; -fx-font-size: 16px; -fx-font-weight: bold;");
                            mainLayout.getChildren().add(successLabel);

                        } catch (IOException | NoSuchAlgorithmException ex) {
                            ex.printStackTrace();
                            Label errorLabel = new Label("Error while processing the judgment file.");
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

    // public void LogJudgement(Scanner scanner, List<Case> AllCases, FileHandler
    // fileHandler) {
    // Case c = new Case();

    // // Prompt for Case ID
    // System.out.print("Enter Case ID: ");
    // int caseID = scanner.nextInt();

    // // Check if case exists
    // if (c.doesCaseExist(caseID, AllCases)) {
    // c = c.getCasebyID(caseID, AllCases);

    // // if (c.getLawyerId() == lawyerID) {
    // // Open file dialog to select a file
    // File selectedFile = fileHandler.openFileDialog();
    // if (selectedFile != null) {
    // try {
    // // Generate file hash
    // String fileHash = fileHandler.getFileHash(selectedFile.getAbsolutePath());
    // // System.out.println("File hash: " + fileHash);

    // CaseFile my_file = new CaseFile(selectedFile.getAbsolutePath(), fileHash);
    // c.addJudgement(my_file);
    // System.out.println("Judgement added to case, waiting for Registrar to
    // approve.");

    // System.out.println(c.toString());

    // DatabaseHandler d = new DatabaseHandler();
    // d.addJudgement(c.getCaseID(), selectedFile.getAbsolutePath(), fileHash, 2);
    // System.out.println("Judgement Added in Database.");
    // return;

    // // Perform further actions if needed
    // } catch (IOException | NoSuchAlgorithmException e) {
    // System.err.println("Error while processing the file: " + e.getMessage());
    // e.printStackTrace();
    // }
    // } else {
    // System.out.println("No file selected.");
    // }
    // // } else {
    // // System.out.println("You are not the authorised lawyer of this case.");
    // // }
    // } else {
    // System.out.println("Case with this ID does not exist.");
    // }
    // }
}
