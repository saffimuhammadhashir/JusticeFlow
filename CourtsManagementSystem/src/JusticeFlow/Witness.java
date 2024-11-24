package JusticeFlow;

import java.util.List;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Date;

public class Witness extends User {
    private int witnessID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private int userID;
    public List<Integer> CaseID = new ArrayList<>();

    public Witness(int userID, String username, String password, String role, String email, String phoneNumber,
            boolean activate,
            int witnessID, String firstName, String lastName, Date dateOfBirth, String gender, String address) {
        super(userID, username, password, role, email, phoneNumber, activate); // Calling the constructor of User class

        this.witnessID = witnessID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userID = userID;

    }

    public void addCaseID(int i) {
        CaseID.add(i);
    }

    public int getWitnessID() {
        return witnessID;
    }

    public void setWitnessID(int witnessID) {
        this.witnessID = witnessID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    private boolean hasCase(int num) {
        for (int i : CaseID) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }

    public void viewCases(Scanner scanner, List<Case> AllCases, FileHandler fileHandler) {
        boolean exists = false;
        for (Case caseObj : AllCases) {
            if (hasCase(caseObj.getCaseID())) {
                exists = true;
            }
        }

        if (exists) {
            System.out.println("\nCases:");
            for (Case caseObj : AllCases) {
                if (caseObj != null) {
                    if (hasCase(caseObj.getCaseID())) {
                        System.out.println(caseObj.toString());
                    }
                }
            }
        }
    }

    public void viewCases(Scanner scanner, List<Case> AllCases, FileHandler fileHandler, DatabaseHandler dbHandler,
            Stage primaryStage, Scene previousScene) {

        // Title Label
        Label titleLabel = new Label("Your Cases!");
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
            boolean exists = false;
            if (hasCase(cases.getCaseID())) {
                exists = true;
            }
            if (exists) {

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
                // Button approveButton = new Button("Request for Re-Open");
                // approveButton.setStyle(
                // "-fx-background-color: #27ae60; " +
                // "-fx-text-fill: white; " +
                // "-fx-font-size: 14px; " +
                // "-fx-font-weight: bold; " +
                // "-fx-padding: 10px 20px; " +
                // "-fx-border-radius: 8px; " +
                // "-fx-background-radius: 8px;");

                // // Add spacing and alignment for buttons
                // GridPane.setConstraints(approveButton, 0, 3);
                // // GridPane.setConstraints(rejectButton, 1, 3);
                // GridPane.setMargin(approveButton, new Insets(10, 10, 10, 0)); // Add spacing
                // around the buttons
                // approveButton.setOnAction(e -> {
                // cases.setCaseStatus("Appeal");
                // dbHandler.saveOrUpdateCase(cases);
                // System.out.println("Case Status set to Appeal in Database");
                // });

                // Add buttons to the GridPane
                // eachCase.getChildren().addAll(approveButton);

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
