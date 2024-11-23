package JusticeFlow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import JusticeFlow.CourtsManagementSystem.GUI_Menu;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CourtAdministrator extends User {
    private int adminID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Date hireDate;
    private int courtID;
    private String email;
    private String phoneNumber;
    private int userID;

    public CourtAdministrator() {
        super();
    }

    public CourtAdministrator(int userID, String username, String password, String role, String email,
            String phoneNumber, boolean activate,
            int adminID, String firstName, String lastName, Date dateOfBirth, String gender,
            Date hireDate, int courtID) {
        super(userID, username, password, role, email, phoneNumber, activate); // Calling the constructor of User class

        this.adminID = adminID;
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

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
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
        return "Court Admin {" +
                "judgeID=" + adminID +
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

                // Add spacing and alignment for buttons
                GridPane.setConstraints(approveButton, 0, 3);
                GridPane.setConstraints(rejectButton, 1, 3);
                GridPane.setMargin(approveButton, new Insets(10, 10, 10, 0)); // Add spacing around the buttons
                GridPane.setMargin(rejectButton, new Insets(10, 0, 10, 10));
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

    public void showHearingSlots(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            List<Slot> AllSlots, List<Judge> AllJudges, List<Witness> AllWitnesses, List<Court> AllCourts,
            Stage primaryStage, GUI_Menu gui, Case cases) {
        
        Label titleLabel = new Label("Select Slot from here!");
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
                        gui.GUI_startmenu(primaryStage);
                        // ReviewCaseRequest(dbHandler, fileHandler, AllCases, AllSlots, AllJudges, AllWitnesses,
                        //         AllCourts,
                        //         primaryStage, gui);
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


    public void scheduleHearing(Scanner scanner, List<Case> AllCases, List<Slot> AllSlots, List<Judge> AllJudges,
            List<Court> AllCourts, List<Witness> AllWitnesses, FileHandler fileHandler,
            DatabaseHandler dbHandler, Stage primaryStage, GUI_Menu gui) {


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
            if (cases.getCaseStatus().equalsIgnoreCase("Open")||cases.getCaseStatus().equalsIgnoreCase("Opened")) {

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
                    showHearingSlots(dbHandler, fileHandler, AllCases, AllSlots, AllJudges, AllWitnesses, AllCourts,
                            primaryStage, gui, cases);
                    // cases.setCaseStatus("Opened");
                    dbHandler.saveOrUpdateCase(cases);
                    // showFiles(dbHandler, fileHandler, AllCases, primaryStage, previousScene, cases);
                    // cases.setCaseStatus("Opened");
                    // dbHandler.saveOrUpdateCase(cases);

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
            gui.GUI_startmenu(primaryStage);
        });
        // Setting up the scene with the scrollable content
        VBox rootLayout = new VBox(20);
        rootLayout.getChildren().addAll(titleLabel, formScrollPane, returnButton);
        rootLayout.setStyle("-fx-background-color: #f4f4f9; -fx-padding: 20px;");

        Scene registerScene = new Scene(rootLayout, 1000, 700);
        primaryStage.setScene(registerScene);
        primaryStage.show();

        // // Main layout container
        // VBox mainLayout = new VBox(20);
        // mainLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // // Title label
        // Label titleLabel = new Label("Schedule a Hearing");
        // titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");
        // mainLayout.getChildren().add(titleLabel);

        // // Scrollable ViewBox for displaying cases
        // ScrollPane caseScrollPane = new ScrollPane();
        // VBox caseViewBox = new VBox(10);
        // caseViewBox.setStyle(
        //         "-fx-padding: 10px; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-border-radius: 10px; -fx-background-radius: 10px;");

        // Label casesLabel = new Label("Available Cases:");
        // casesLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        // caseViewBox.getChildren().add(casesLabel);

        // for (Case caseObj : AllCases) {
        //     Label caseEntry = new Label(caseObj.toString());
        //     caseEntry.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5;");
        //     caseViewBox.getChildren().add(caseEntry);
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
        //         "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #FF6347; -fx-text-fill: white;");
        // backButton.setOnAction(ev -> {
        //     primaryStage.setScene(prevScene); // Go back to case selection scene
        // });

        // Button nextButton = new Button("Next");
        // nextButton.setStyle(
        //         "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

        // // Create an HBox to place the "Back" and "Submit" buttons in a line
        // HBox buttonLayout1 = new HBox(20, backButton, nextButton);
        // buttonLayout1.setAlignment(Pos.CENTER); // Align buttons to the center

        // VBox caseInputSection = new VBox(10, caseIdLabel, caseIdField, buttonLayout1);
        // caseInputSection.setStyle("-fx-alignment: center;");
        // mainLayout.getChildren().add(caseInputSection);

        // // Transition to slots after CaseID input
        // nextButton.setOnAction(e -> {
        //     try {
        //         int caseId = Integer.parseInt(caseIdField.getText());
        //         boolean caseExists = AllCases.stream().anyMatch(c -> c.getCaseID() == caseId);
        //         if (!caseExists) {
        //             System.out.println("Invalid Case ID.");
        //             return;
        //         }

        //         VBox slotLayout = new VBox(20);
        //         slotLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        //         Label slotTitleLabel = new Label("Available Slots:");
        //         slotTitleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");
        //         slotLayout.getChildren().add(slotTitleLabel);

        //         ScrollPane slotScrollPane = new ScrollPane();
        //         VBox slotViewBox = new VBox(10);
        //         slotViewBox.setStyle(
        //                 "-fx-padding: 10px; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-border-radius: 10px; -fx-background-radius: 10px;");

        //         for (Slot slotObj : AllSlots) {
        //             Label slotEntry = new Label(slotObj.toString());
        //             slotEntry.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5;");
        //             slotViewBox.getChildren().add(slotEntry);
        //         }

        //         slotScrollPane.setContent(slotViewBox);
        //         slotScrollPane.setFitToWidth(true);
        //         slotScrollPane.setPrefHeight(300);
        //         slotLayout.getChildren().add(slotScrollPane);

        //         Label slotIdLabel = new Label("Enter Slot ID:");
        //         slotIdLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        //         TextField slotIdField = new TextField();
        //         slotIdField.setPromptText("Slot ID");
        //         slotIdField.setPrefWidth(200);

        //         Button submitButton = new Button("Submit");
        //         submitButton.setStyle(
        //                 "-fx-font-size: 14px; -fx-padding: 5px 15px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

        //         // Create an HBox to place the "Back" and "Submit" buttons in a line
        //         HBox buttonLayout = new HBox(20, backButton, submitButton);
        //         buttonLayout.setAlignment(Pos.CENTER); // Align buttons to the center

        //         Label successLabel = new Label();
        //         successLabel.setStyle("-fx-text-fill: lime; -fx-font-size: 14px; -fx-font-weight: bold;");

        //         VBox slotInputSection = new VBox(10, slotIdLabel, slotIdField, buttonLayout, successLabel);
        //         slotInputSection.setStyle("-fx-alignment: center;");
        //         slotLayout.getChildren().add(slotInputSection);

        //         // Set scene to slot selection
        //         Scene slotScene = new Scene(slotLayout, 1100, 650);
        //         slotLayout.setStyle(
        //                 "-fx-padding: 80px; -fx-alignment: center; -fx-background-size: stretch; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-image: url('file:///D:/Github/JusticeFlow/CourtsManagementSystem/lib/resources/img(3).jpeg');");
        //         primaryStage.setScene(slotScene);

        //         submitButton.setOnAction(ev -> {
        //             try {
        //                 int slotId = Integer.parseInt(slotIdField.getText());
        //                 Slot selectedSlot = AllSlots.stream().filter(s -> s.getSlotID() == slotId).findFirst()
        //                         .orElse(null);

        //                 if (selectedSlot == null || selectedSlot.getCaseID() != null) {
        //                     System.out.println("Invalid or unavailable Slot ID.");
        //                     return;
        //                 }

        //                 selectedSlot.setCaseID(caseId);
        //                 databaseHandler.updateOrInsertSlots(AllSlots);
        //                 System.out.println("Hearing Scheduled for Slot ID: " + slotId);

        //                 // Show success message
        //                 successLabel.setText("Hearing successfully scheduled for Slot ID: " + slotId + "!");
        //             } catch (NumberFormatException ex) {
        //                 System.out.println("Invalid Slot ID input.");
        //             }
        //         });
        //     } catch (NumberFormatException ex) {
        //         System.out.println("Invalid Case ID input.");
        //     }
        // });

        // // Set scene and display
        // Scene caseScene = new Scene(mainLayout, 1100, 650);
        // mainLayout.setStyle(
        //         "-fx-padding: 80px; -fx-alignment: center; -fx-background-size: stretch; -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-background-image: url('file:///D:/Github/JusticeFlow/CourtsManagementSystem/lib/resources/img(3).jpeg');");
        // primaryStage.setScene(caseScene);
        // primaryStage.show();
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

    public void TrackAndManageUpdates(List<Case> Allcases, List<Slot> AllSlots, List<Judge> AllJudges,
            List<Lawyer> AllLawyers, List<Clients> AllClients, List<Notification> AllNotifications,
            DatabaseHandler dbHandler, Scanner scanner) {
        for (Case c : Allcases) {
            print(c.toString());
        }
        print("Select Any Case From Above");
        Object val1 = GetInput(scanner);
        print((int) val1);

        Case tempcase = dbHandler.findCaseByID(Allcases, (Integer) val1);
        if (tempcase != null) {
            print("Input Notification Msg:");
            Object notification = GetInput(scanner);
            String message = (String) notification;
            print(message);
            List<Integer> case_stakeholders = tempcase.getStakeholders(AllClients, AllSlots, AllJudges, AllLawyers);

            int count = 1;
            for (Notification n : AllNotifications) {
                count++;
            }
            if (case_stakeholders.size() > 0) {
                for (Integer i : case_stakeholders) {
                    Notification newnotification = new Notification(count, tempcase.getCaseID(), i, this.getUserID(),
                            "Courts Administrator", message);
                    AllNotifications.add(newnotification);
                    dbHandler.updateOrCreateNotification(newnotification);
                    count++;
                }
            }

        } else {
            print("Invalid Case id!");
        }
    }

    public void sendnotification(List<Case> AllCases, List<Slot> AllSlots, List<Judge> AllJudges,
            List<Lawyer> AllLawyers, List<Clients> AllClients, List<Notification> AllNotifications,
            DatabaseHandler dbHandler, Stage primaryStage, GUI_Menu gui, Case cases) {
        // Retrieve stakeholders for the case
        List<Integer> caseStakeholders = cases.getStakeholders(AllClients, AllSlots, AllJudges, AllLawyers);
        List<User> notificationStakeholders = new ArrayList<>();
        for (Integer id : caseStakeholders) {
            notificationStakeholders.add(dbHandler.getUserById(id));
        }

        // Create a new stage for the notification interface
        Stage notificationStage = new Stage();

        // Title and Stakeholder Section
        Label titleLabel = new Label("Send Notification");
        titleLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
        // Stakeholder Label
        Label stakeholderLabel = new Label("All Stakeholders:");
        stakeholderLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: blue;");

        // Scroll Pane for Stakeholders
        ScrollPane stakeholderScrollPane = new ScrollPane();
        stakeholderScrollPane.setFitToWidth(true);
        stakeholderScrollPane
                .setStyle("-fx-background: #f7f8fc; -fx-border-color: #ddd; -fx-padding: 10px;-fx-max-height:200px;");

        // Stakeholder Container
        VBox stakeholderLayout = new VBox(15); // Spacing between each stakeholder card
        stakeholderLayout.setPadding(new Insets(15));
        stakeholderLayout.setStyle("-fx-background-color: #f7f8fc; -fx-border-radius: 5px;");

        // Iterate through stakeholders and create cards
        for (User user : notificationStakeholders) {
            // Card container for each stakeholder
            VBox stakeholderCard = new VBox(5);
            stakeholderCard.setPadding(new Insets(10));
            stakeholderCard.setStyle(
                    "-fx-background-color: #ffffff; " +
                            "-fx-border-color: #ccc; " +
                            "-fx-border-width: 1px; " +
                            "-fx-border-radius: 5px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");

            // Stakeholder details
            Label nameLabel = new Label("Name: " + user.getUsername());
            nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

            Label emailLabel = new Label("Email: " + user.getEmail());
            emailLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");

            Label roleLabel = new Label("Role: " + user.getRole());
            roleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");

            Label phoneLabel = new Label("Phone: " + user.getPhoneNumber());
            phoneLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");

            // Add details to the card
            stakeholderCard.getChildren().addAll(nameLabel, emailLabel, roleLabel, phoneLabel);

            // Add card to the main layout
            stakeholderLayout.getChildren().add(stakeholderCard);
        }

        // Set content for the ScrollPane
        stakeholderScrollPane.setContent(stakeholderLayout);

        // Notification Message Section
        Label messageLabel = new Label("Notification Message:");
        messageLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextArea messageTextArea = new TextArea();
        messageTextArea.setPromptText("Enter your notification message here...");
        messageTextArea.setWrapText(true);
        messageTextArea.setPrefHeight(150);

        // Buttons for Send and Close
        Button sendButton = new Button("Send Notification");
        sendButton.setStyle(
                "-fx-background-color: #4caf50; -fx-text-fill: white;-fx-padding: 10px;-fx-font-size: 14px;-fx-border-radius: 5px;");

        sendButton.setOnAction(event -> {
            String notificationMessage = messageTextArea.getText();

            if (notificationMessage.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Notification message cannot be empty.", ButtonType.OK);
                alert.showAndWait();
            } else if (caseStakeholders.size() > 0) {
                for (Integer id : caseStakeholders) {
                    Notification newNotification = new Notification(
                            AllNotifications.size() + 1,
                            cases.getCaseID(),
                            id,
                            this.getUserID(),
                            "Courts Administrator",
                            notificationMessage);
                    AllNotifications.add(newNotification);
                    dbHandler.updateOrCreateNotification(newNotification);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Notification sent successfully.", ButtonType.OK);
                alert.showAndWait();
                TrackAndManageUpdates(AllCases, AllSlots, AllJudges, AllLawyers, AllClients, AllNotifications,
                        dbHandler,
                        primaryStage, gui);
            }
        });

        Button closeButton = new Button("Close");
        closeButton.setStyle(
                "-fx-background-color: #f44336; ; -fx-text-fill: white;-fx-padding: 10px;-fx-font-size: 14px;-fx-border-radius: 5px;");
        closeButton.setOnAction(event -> {
            TrackAndManageUpdates(AllCases, AllSlots, AllJudges, AllLawyers, AllClients, AllNotifications, dbHandler,
                    primaryStage, gui);

        });

        // Button Layout
        HBox buttonLayout = new HBox(10, sendButton, closeButton);
        buttonLayout.setStyle("-fx-alignment: center;");
        buttonLayout.setPadding(new Insets(10));

        // Main Layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getChildren().addAll(titleLabel, stakeholderLabel, stakeholderScrollPane, messageLabel,
                messageTextArea, buttonLayout);

        // Scene and Stage Setup
        Scene scene = new Scene(mainLayout, 1000, 600);
        primaryStage.setTitle("Send Notification");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void TrackAndManageUpdates(List<Case> AllCases, List<Slot> AllSlots, List<Judge> AllJudges,
            List<Lawyer> AllLawyers, List<Clients> AllClients, List<Notification> AllNotifications,
            DatabaseHandler dbHandler, Stage primaryStage, GUI_Menu gui) {

        List<Case> PendingCases = new ArrayList<>();

        // Title Label
        Label titleLabel = new Label("Send and Manage Updates");
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
            Button approveButton = new Button("Send Notification");
            approveButton.setStyle(
                    "-fx-background-color: #27ae60; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14px; " +
                            "-fx-font-weight: bold; " +
                            "-fx-padding: 10px 20px; " +
                            "-fx-border-radius: 8px; " +
                            "-fx-background-radius: 8px;");

            approveButton.setOnAction(e -> {
                sendnotification(AllCases, AllSlots, AllJudges, AllLawyers, AllClients, AllNotifications, dbHandler,
                        primaryStage, gui, cases);
            });

            // Add spacing and alignment for the button
            GridPane.setConstraints(approveButton, 0, 3);
            GridPane.setMargin(approveButton, new Insets(15, 10, 15, 0)); // Add spacing around the button

            // Add button to the GridPane
            eachCase.getChildren().addAll(approveButton);

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

}
