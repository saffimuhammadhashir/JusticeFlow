package JusticeFlow;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import JusticeFlow.CourtsManagementSystem.GUI_Menu;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
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

    public void FileNewCase(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> AllCases, Stage primaryStage,GUI_Menu gui) {
        primaryStage.setTitle("File New Case");
        Label PageTitle = new Label("File New Case");
        PageTitle.setStyle("-fx-font-size: 44px;-fx-alignment:center center;");
        // Create a GridPane to hold the UI elements
        VBox outerlayout=new VBox(25);
        HBox horizontalbox=new HBox(25);
        GridPane grid = new GridPane();
        VBox imagebox=new VBox(15);
        imagebox.setStyle(
            "-fx-background-size: contain; "
            + "-fx-background-position: center; "
            + "-fx-background-repeat: no-repeat; "
            + "-fx-background-image: url('file:///E:/Github%20Projects/JusticeFlow/CourtsManagementSystem/lib/resources/pic1.png'); "
            + "-fx-min-height:200px;"
            + "-fx-min-width:400px;"
        );
        imagebox.minWidthProperty().bind(grid.widthProperty());
        grid.setStyle(
            "-fx-background-color: rgba(0,0,0,0.8); "
            + "-fx-background-radius: 20px; "
            + "-fx-padding: 20px 50px; "
        );
        outerlayout.setStyle("-fx-alignment:center center;-fx-background-color: #f4f4f9;padding:10px 80px;");
        grid.setVgap(25);
        grid.setHgap(25);
        horizontalbox.setStyle("-fx-spacing:50px;");
        horizontalbox.getChildren().addAll(grid,imagebox);
        outerlayout.getChildren().addAll(PageTitle,horizontalbox);


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
        typeComboBox.setStyle("-fx-font-size: 14px; -fx-background-color: #ffffff; -fx-border-radius: 5px;-fx-text-fill: white;");

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
        submitButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px;");
        submitButton.setMaxWidth(Double.MAX_VALUE); // Make button stretch to fill width

        Button returnButton = new Button("Close");
        returnButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px;");
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
        returnButton.setOnAction(event -> { gui.GUI_startmenu(primaryStage);});
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
