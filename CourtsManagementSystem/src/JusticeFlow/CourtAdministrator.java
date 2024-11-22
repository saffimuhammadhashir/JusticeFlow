package JusticeFlow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import JusticeFlow.CourtsManagementSystem.GUI_Menu;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

            // Create a GridPane for each case
            GridPane eachCase = new GridPane();
            eachCase.setHgap(10); // Horizontal gap between columns
            eachCase.setVgap(10); // Vertical gap between rows
            eachCase.setStyle(
                    "-fx-padding: 10px; -fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-effect: innershadow(gaussian, #000000, 5, 0.5, 0, 0);");

            // Case Title Label
            Label caseName = new Label(slot.dayName);
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
                for (Slot org : AllSlots) {
                    if (org.getSlotID() == slot.getSlotID()) {
                        org.setCaseID(cases.getCaseID());
                        org.setJudgeID(slot.getJudgeID());
                        org.setWitnessID(slot.getWitnessID());
                        org.setCourtId(slot.getCourtId());

                        dbHandler.updateOrInsertSlots(AllSlots);

                        ReviewCaseRequest(dbHandler, fileHandler, AllCases, AllSlots, AllJudges, AllWitnesses,
                                AllCourts, primaryStage, gui);
                    }
                }

            });

            // Add buttons to GridPane, starting from row 1
            GridPane.setConstraints(approveButton, 0, 3); // Place in column 1, row 1
            // Place in column 2, row 1

            eachCase.getChildren().addAll(approveButton);

            // Add the GridPane to the formLayout
            formLayout.getChildren().add(eachCase);
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

    public void ReviewCaseRequest(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases,
            List<Slot> AllSlots, List<Judge> AllJudges, List<Witness> AllWitnesses, List<Court> AllCourts,
            Stage primaryStage, GUI_Menu gui) {

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
                Button approveButton = new Button("Approve");
                approveButton.setStyle(
                        "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5px 15px; -fx-border-radius: 5px;");
                Button rejectButton = new Button("Reject");
                rejectButton.setStyle(
                        "-fx-background-color: #f44336; -fx-text-fill: white; -fx-padding: 5px 15px; -fx-border-radius: 5px;");

                // Button Actions (for demonstration purposes, implement logic later)
                approveButton.setOnAction(e -> {
                    showandscheduleslots(dbHandler, fileHandler, AllCases, AllSlots, AllJudges, AllWitnesses, AllCourts,
                            primaryStage, gui, cases);
                    cases.setCaseStatus("Opened");
                    dbHandler.saveOrUpdateCase(cases);

                });
                rejectButton.setOnAction(e -> {
                    cases.setCaseStatus("Not Allowed");
                    dbHandler.saveOrUpdateCase(cases);
                    ReviewCaseRequest(dbHandler, fileHandler, AllCases, AllSlots, AllJudges, AllWitnesses, AllCourts,
                            primaryStage, gui);

                });

                // Add buttons to GridPane, starting from row 1
                GridPane.setConstraints(approveButton, 0, 3); // Place in column 1, row 1
                GridPane.setConstraints(rejectButton, 1, 3); // Place in column 2, row 1

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
