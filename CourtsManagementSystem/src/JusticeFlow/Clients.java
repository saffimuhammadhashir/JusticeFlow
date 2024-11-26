package JusticeFlow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import JusticeFlow.CourtsManagementSystem.GUI_Menu;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Clients extends User {
    private int clientID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private int userID;

    public Clients() {
    }

    public Clients(int userID, String username, String password, String role, String email, String phoneNumber,
            boolean activate,
            int clientID, String firstName, String lastName, Date dateOfBirth, String gender,
            String address) {
        super(userID, username, password, role, email, phoneNumber, activate); // Calling the constructor of User class

        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userID = userID;
    }

    public int getclientID() {
        return clientID;
    }

    public void setclientID(int clientID) {
        this.clientID = clientID;
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

    @Override
    public String toString() {
        return "client {" +
                "judgeID=" + clientID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userID=" + userID +
                '}';
    }

    public void TrackCase(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            List<Slot> AllSlots, List<Clients> allclients, List<Judge> AllJudges, List<Lawyer> AllLawyers,
            List<Witness> AllWitnesses, List<Court> AllCourts,
            Stage primaryStage, GUI_Menu gui, CourtsManagementSystem system) {
        List<Case> PendingCases = new ArrayList<>();

        List<Case> Lawyercases = new ArrayList<>();
        for (Case c : AllCases) {
            if((c.getPlaintiffID()!=0 && c.getPlaintiffID()==this.clientID) || (c.getDefendantID()!=0 && c.getDefendantID()==this.clientID))
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
