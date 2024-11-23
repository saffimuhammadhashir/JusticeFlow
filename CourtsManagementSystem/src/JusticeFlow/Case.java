package JusticeFlow;

import java.util.List;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import JusticeFlow.CourtsManagementSystem.GUI_Menu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

public class Case {

    private int caseID;
    private String caseTitle;
    private String caseType;
    private String caseStatus;
    private Date filingDate;
    private Date courtDate;
    private int plaintiffID = 0;
    private int defendantID = 0;
    private List<CaseFile> files;
    private List<CaseFile> judgements;
    private int Lawyerid = 0;
    private List<Witness> linkedWitnesses;
    // Changed to CaseFile from File
    // New attribute for CaseState (e.g., Pending, Filed)

    // Constructor initializes all attributes of a case, including details like case
    // title,
    // type, status, filing and court dates, IDs of plaintiff and defendant,
    // associated files, and case state.
    public Case(int caseID, String caseTitle, String caseType,
            Date filingDate, Date courtDate, int plaintiffID, int defendantID, String caseState) {

        this(caseID, caseTitle, caseType, caseState, filingDate, courtDate, plaintiffID, defendantID,
                new ArrayList<>());
        this.judgements = new ArrayList<>();
    }

    public Case(int caseID, String caseTitle, String caseType,
            Date filingDate, Date courtDate, int plaintiffID, int defendantID, String caseState, int Lawyerid) {

        this(caseID, caseTitle, caseType, caseState, filingDate, courtDate, plaintiffID, defendantID,
                new ArrayList<>());
        this.Lawyerid = Lawyerid;
        this.judgements = new ArrayList<>();
    }

    // Constructor with files and caseState
    public Case(int caseID, String caseTitle, String caseType, String caseStatus,
            Date filingDate, Date courtDate, int plaintiffID, int defendantID, List<CaseFile> files) {
        this.caseID = caseID;
        this.caseTitle = caseTitle;
        this.caseType = caseType;
        this.caseStatus = caseStatus;
        this.filingDate = filingDate;
        this.courtDate = courtDate;
        this.plaintiffID = plaintiffID;
        this.defendantID = defendantID;
        this.files = files;
        this.judgements = new ArrayList<>();
    }

    public Case() {

    }

    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
        this.caseStatus = caseStatus;
    }

    public Date getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    public Date getCourtDate() {
        return courtDate;
    }

    public void setCourtDate(Date courtDate) {
        this.courtDate = courtDate;
    }

    public int getPlaintiffID() {
        return plaintiffID;
    }

    public void setPlaintiffID(int plaintiffID) {
        this.plaintiffID = plaintiffID;
    }

    public int getDefendantID() {
        return defendantID;
    }

    public void setDefendantID(int defendantID) {
        this.defendantID = defendantID;
    }

    public List<CaseFile> getFiles() {
        return files;
    }

    public void setFiles(List<CaseFile> files) {
        this.files = files;
    }

    public List<CaseFile> getJudgements() {
        return judgements;
    }

    public void setJudgements(List<CaseFile> judgements) {
        this.judgements = judgements;
    }

    public void addJudgement(CaseFile file) {
        this.judgements.add(file);
    }

    // Adds a new file to the case's list of files, representing a document or item
    // associated with the case.
    public void addFile(CaseFile file) {
        this.files.add(file);
    }

    public void setLawyerId(int id) {
        this.Lawyerid = id;
    }

    // Updates case files by first saving the files through the FileHandler,
    // then saving or updating the case record through DatabaseHandler.
    public void updateCaseFiles(FileHandler filehandler, DatabaseHandler dbHandler) {
        filehandler.saveFileForCase(this);
        dbHandler.saveOrUpdateCase(this);
    }

    // Converts the case details, including associated file details, into a readable
    // string format.
    @Override
    public String toString() {
        StringBuilder fileDetails = new StringBuilder();
        for (CaseFile file : files) {
            fileDetails.append("\n").append(file.toString());
        }

        StringBuilder judgementDetails = new StringBuilder();
        if (judgements != null) {
            judgementDetails.append("\nAssociated Judgements: ");
            boolean exists = false;
            for (CaseFile judgement : judgements) {
                judgementDetails.append("\n").append(judgement.toString());
                exists = true;
            }

            if (exists == false) {
                judgementDetails.append("NONE\n");
            }
        } else {
            judgementDetails.append("");
        }

        return "Case ID: " + caseID + "\nTitle: " + caseTitle + "\nType: " + caseType +
                "\nStatus: " + caseStatus + "\nFiled On: " + filingDate + "\nCourt Date: " + courtDate +
                "\nPlaintiff ID: " + plaintiffID + "\nDefendant ID: " + defendantID + "\nLawyer Id" + Lawyerid +
                "\nAssociated Files: " + fileDetails.toString() + judgementDetails.toString()
                + "\n\n--------------------------------\n";
    }

    public int getLawyerId() {
        return this.Lawyerid;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #4CAF50; " + // Green background
                "-fx-text-fill: white; " + // White text
                "-fx-font-size: 14px; " + // Font size
                "-fx-font-weight: bold; " + // Bold font
                "-fx-padding: 6px 20px; " + // Padding for better size
                "-fx-background-radius: 5px; " + // Rounded corners
                "-fx-border-radius: 5px; " +
                "-fx-min-width:250px;" + // Rounded border
                "-fx-effect: innershadow( gauss , rgba(0,0,0,0.3), 10, 0, 0, 1);"); // Subtle shadow effect

        // Add hover effects
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #45a049; " + // Darker green on hover
                "-fx-text-fill: white; " +
                "-fx-font-size: 14px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 6px 20px; " +
                "-fx-background-radius: 5px; " +
                "-fx-min-width:250px;" +
                "-fx-border-radius: 5px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4CAF50; " + // Revert to original green
                "-fx-text-fill: white; " +
                "-fx-font-size: 14px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 6px 20px; " +
                "-fx-background-radius: 5px; " +
                "-fx-min-width:250px;" +
                "-fx-border-radius: 5px;"));
        return button;
    }

    public void updateCaseData(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> allCases,
            List<Slot> allSlots, List<Clients> allclients, List<Judge> allJudges, List<Lawyer> allLawyers,
            List<Witness> allWitnesses, List<Court> allCourts, Stage primaryStage, GUI_Menu gui,
            CourtsManagementSystem system) {

        // Main layout with larger spacing
        HBox mainLayout = new HBox(20);
        mainLayout.setPadding(new Insets(20));

        // Left side: Form to edit case attributes
        VBox formLayout = new VBox(20);
        formLayout.setPadding(new Insets(20));
        formLayout.setAlignment(Pos.TOP_LEFT);

        Label formTitle = new Label("Edit Case Details");
        formTitle.setStyle("-fx-font-size: 46px; -fx-font-weight: bold;");

        // Form fields with validation and dropdowns
        TextField caseTitleField = new TextField(caseTitle != null ? caseTitle : "");
        caseTitleField.setPromptText("Case Title");

        TextField caseTypeField = new TextField(caseType != null ? caseType : "");
        caseTypeField.setPromptText("Case Type");

        TextField caseStatusField = new TextField(caseStatus != null ? caseStatus : "");
        caseStatusField.setPromptText("Case Status");

        // Dropdown for Plaintiff
        ComboBox<Clients> plaintiffComboBox = new ComboBox<>();
        plaintiffComboBox.getItems().addAll(allclients);
        plaintiffComboBox.setPromptText("Select Plaintiff");

        // Dropdown for Defendant
        ComboBox<Clients> defendantComboBox = new ComboBox<>();
        defendantComboBox.getItems().addAll(allclients);
        defendantComboBox.setPromptText("Select Defendant");

        // Dropdown for Lawyer
        ComboBox<Lawyer> lawyerComboBox = new ComboBox<>();
        lawyerComboBox.getItems().addAll(allLawyers);
        lawyerComboBox.setPromptText("Select Lawyer");

        Button saveButton = new Button("Save Changes");
        saveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        Button fileButton = new Button("Upload new File");
        fileButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");

        Button closeButton = new Button("Return");
        closeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        // Save button action
        saveButton.setOnAction(e -> {
            try {
                // Validate input fields
                String newCaseTitle = caseTitleField.getText();
                if (newCaseTitle.isEmpty()) {
                    throw new IllegalArgumentException("Case title cannot be empty.");
                }

                String newCaseType = caseTypeField.getText();
                if (newCaseType.isEmpty()) {
                    throw new IllegalArgumentException("Case type cannot be empty.");
                }

                String newCaseStatus = caseStatusField.getText();
                if (newCaseStatus.isEmpty()) {
                    throw new IllegalArgumentException("Case status cannot be empty.");
                }

                // Get selected client IDs
                Clients selectedPlaintiff = plaintiffComboBox.getValue();
                Clients selectedDefendant = defendantComboBox.getValue();
                Lawyer selectedLawyer = lawyerComboBox.getValue();

                if (selectedPlaintiff == null || selectedDefendant == null || selectedLawyer == null) {
                    throw new IllegalArgumentException("Please select valid Plaintiff, Defendant, and Lawyer.");
                }

                // Update case details
                setCaseTitle(newCaseTitle);
                setCaseType(newCaseType);
                setCaseStatus(newCaseStatus);
                setPlaintiffID(selectedPlaintiff.getclientID());
                setDefendantID(selectedDefendant.getclientID());
                setLawyerId(selectedLawyer.getLawyerID());

                // Save updated case
                dbHandler.saveOrUpdateCase(this);

                // Show success message
                gui.showMessage("Case updated successfully!");

            } catch (IllegalArgumentException ex) {
                gui.showMessage("Error: " + ex.getMessage());
            } catch (Exception ex) {
                gui.showMessage("Unexpected error occurred: " + ex.getMessage());
            }
        });

        // Add fields to form layout
        HBox buttonBox = new HBox(25, saveButton, closeButton);
        closeButton.setOnAction(e -> {
            system.TrackCase(primaryStage, gui);
        });

        formLayout.getChildren().addAll(formTitle, caseTitleField, caseTypeField, caseStatusField,
                plaintiffComboBox, defendantComboBox, lawyerComboBox, buttonBox);
        formLayout.setStyle("-fx-max-width:600px;");

        // Right side: Buttons for additional actions
        VBox buttonLayout = new VBox(20);
        buttonLayout.setPadding(new Insets(20));
        buttonLayout.setAlignment(Pos.TOP_CENTER);
        buttonLayout.setStyle(
                "-fx-border-radius:20px;-fx-background-radius:20px;-fx-background-color:#E7E7E7;-fx-min-width:350px;");
        Label actionsTitle = new Label("Actions");
        actionsTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;-fx-text-fill:blue");

        Button showSchedulesButton = createStyledButton("Manage Schedules");
        Button showWitnessesButton = createStyledButton("Manage Witnesses");
        // Button showFilesButton = createStyledButton("Manage Files");
        // fileButton.setOnAction(e-> fileHandler.saveFileForCase(this));
        // Uncomment when methods are available
        showSchedulesButton.setOnAction(e -> displaySchedules(dbHandler, fileHandler, allCases, allSlots, allclients,
                allJudges, allLawyers, allWitnesses, allCourts,
                primaryStage, gui, system));
        showWitnessesButton.setOnAction(e -> displayWitnesses(dbHandler, fileHandler, allCases, allSlots, allclients,
                allJudges, allLawyers, allWitnesses, allCourts,
                primaryStage, gui, system, this));
        // showFilesButton.setOnAction(e -> displayFiles(getFiles()));

        // Add action buttons
        buttonLayout.getChildren().addAll(actionsTitle, showSchedulesButton, showWitnessesButton);

        // Add ScrollPane for Slot details
        ScrollPane scrollPane = new ScrollPane();
        VBox slotDetailsLayout = new VBox(10);
        slotDetailsLayout.setPadding(new Insets(10));
        slotDetailsLayout.setAlignment(Pos.CENTER);
        Label slotTitle = new Label("Slot Details");
        slotTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;-fx-text-fill:blue");
        int i = 0;
        // Populate the slot details
        for (Slot slot : allSlots) {
            // Format the slot label with date, time, and courtroom
            if (slot.getCaseID() != null && slot.getCaseID() == caseID) {
                String slotText = "Slot: " + slot.getDayName() + " / " + "Time : " +
                        slot.getStartTime() + " - " + slot.getEndTime() +
                        "\nCourtroom: " + slot.getCourtId() + "\n";

                Label slotLabel = new Label(slotText);

                // Style the slot labels
                slotLabel.setStyle("-fx-font-size: 14px; " + // Font size
                        "-fx-font-weight: normal; " + // Normal weight
                        "-fx-padding: 10px; " + // Padding around the text
                        "-fx-border-radius: 5px; " + // Rounded corners
                        "-fx-border-color: #B0BEC5; " + // Light border color
                        "-fx-border-width: 1px; " + // Border width

                        "-fx-background-color: " + (i % 2 == 0 ? "#f0f0f0" : "#ffffff") + "; " + // Alternating row
                                                                                                 // color
                        "-fx-effect: innershadow(gauss, rgba(0, 0, 0, 0.3), 5, 0, 0, 1);"); // Inner shadow effect

                // Add the label to the VBox (slotDetailsLayout)
                slotDetailsLayout.getChildren().add(slotLabel);
                i++;
            }
        }

        // Add VBox to ScrollPane
        scrollPane.setContent(slotDetailsLayout);
        scrollPane.setFitToWidth(true); // Ensures the content fits the width of the ScrollPane

        // Add the scrollPane to the buttonLayout
        buttonLayout.getChildren().addAll(slotTitle, scrollPane);

        // Combine form and button layout
        mainLayout.getChildren().addAll(formLayout, buttonLayout);

        // Set up scene and stage
        Scene scene = new Scene(mainLayout, 1000, 600); // Increased width for better layout
        primaryStage.setTitle("Update Case Data");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void displaySchedules(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> allCases,
            List<Slot> allSlots, List<Clients> allclients, List<Judge> allJudges, List<Lawyer> allLawyers,
            List<Witness> allWitnesses, List<Court> allCourts, Stage primaryStage, GUI_Menu gui,
            CourtsManagementSystem system) {

        // Filter slots that match the given caseID
        List<Slot> filteredSlots = new ArrayList<>();
        for (Slot slot : allSlots) {
            if (slot.getCaseID() != null && slot.getCaseID() == caseID) {
                filteredSlots.add(slot);
            }
        }

        // Create the VBox for the slot details (no need to worry about overflow here)
        VBox slotDetailLayout = new VBox(15);
        slotDetailLayout.setPadding(new Insets(20));
        slotDetailLayout.setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Title Section
        Label titleLabel = new Label("Scheduled Slots for Case ID: " + caseID);
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");
        titleLabel.setPadding(new Insets(10));
        titleLabel.setAlignment(Pos.CENTER);

        // Check if there are any slots for the given case
        if (filteredSlots.isEmpty()) {
            Label noSlotsLabel = new Label("No slots found for this case.");
            noSlotsLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #999;");
            noSlotsLabel.setPadding(new Insets(20));
            slotDetailLayout.getChildren().add(noSlotsLabel);
        } else {
            // Display each slot's details
            for (Slot nextSlot : filteredSlots) {
                // Create a card-like VBox for each slot with reduced spacing
                VBox slotInfoBox = new VBox(10); // Reduced spacing to 10px
                slotInfoBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-radius: 10; -fx-background-radius: 10; "
                        + "-fx-padding: 10 20 10 20; -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.2), 10, 0.5, 0, 5);");

                // Title Section with smaller font size
                Label slotTitleLabel = new Label("Slot: " + nextSlot.getSlotID());
                slotTitleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");

                // Create HBoxes for each label to keep them aligned
                HBox dateHBox = new HBox(5); // Less spacing between elements
                Label dateLabel = new Label("Date: " + nextSlot.getDayName().getDayOfMonth());
                dateLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");
                dateHBox.getChildren().add(dateLabel);

                HBox timeHBox = new HBox(5);
                Label startTimeLabel = new Label("Start Time: " + nextSlot.getStartTime());
                startTimeLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");
                Label endTimeLabel = new Label("End Time: " + nextSlot.getEndTime());
                endTimeLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");
                timeHBox.getChildren().addAll(startTimeLabel, endTimeLabel);

                HBox judgeWitnessHBox = new HBox(5);
                Label judgeLabel = new Label("Judge: " + nextSlot.getJudgeID());
                judgeLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");
                Label witnessLabel = new Label("Witness: " + nextSlot.getWitnessID());
                witnessLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");
                judgeWitnessHBox.getChildren().addAll(judgeLabel, witnessLabel);

                // Add HBoxes to the slotInfoBox (VBox)
                slotInfoBox.getChildren().addAll(slotTitleLabel, dateHBox, timeHBox, judgeWitnessHBox);

                // Add a hover effect to make it more interactive
                slotInfoBox.setOnMouseEntered(e -> slotInfoBox.setStyle(
                        "-fx-background-color: #e8f5e9; -fx-border-radius: 10; -fx-background-radius: 10; "
                                + "-fx-padding: 10 20 10 20; -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.1), 5, 0.5, 0, 3);"));
                slotInfoBox.setOnMouseExited(e -> slotInfoBox.setStyle(
                        "-fx-background-color: #FFFFFF; -fx-border-radius: 10; -fx-background-radius: 10; "
                                + "-fx-padding: 10 20 10 20; -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.2), 10, 0.5, 0, 5);"));

                // Add the card to the main layout
                slotDetailLayout.getChildren().add(slotInfoBox);
            }
        }

        // Buttons Section
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-padding: 20;");

        Button returnButton = new Button("Return");
        returnButton.setStyle(
                "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        returnButton.setOnMouseEntered(e -> returnButton
                .setStyle("-fx-background-color: #45a049;-fx-font-size: 16px; -fx-padding: 10px 20px;"));
        returnButton.setOnMouseExited(e -> returnButton
                .setStyle("-fx-background-color: #4CAF50;-fx-font-size: 16px; -fx-padding: 10px 20px;"));
        returnButton.setOnAction(e -> updateCaseData(dbHandler, fileHandler, allCases, allSlots, allclients, allJudges,
                allLawyers, allWitnesses, allCourts, primaryStage, gui, system));

        Button scheduleNextButton = new Button("Schedule Next Slot");
        scheduleNextButton.setStyle(
                "-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        scheduleNextButton.setOnMouseEntered(e -> scheduleNextButton
                .setStyle("-fx-background-color: #1976D2;-fx-font-size: 16px; -fx-padding: 10px 20px;"));
        scheduleNextButton.setOnMouseExited(e -> scheduleNextButton
                .setStyle("-fx-background-color: #2196F3;-fx-font-size: 16px; -fx-padding: 10px 20px;"));
        scheduleNextButton.setOnAction(e -> {
            Slot.newSlotScheduler(allSlots, allJudges, allCourts, allWitnesses, this, dbHandler, primaryStage,
                    gui, system, fileHandler, allCases, allclients, allLawyers);
        });

        buttonBox.getChildren().addAll(returnButton, scheduleNextButton);

        // Create an outer layout
        VBox outerLayout = new VBox(25);
        outerLayout.setPadding(new Insets(10));

        // Wrap the VBox inside a ScrollPane for the slot details
        ScrollPane scrollPane = new ScrollPane(slotDetailLayout); // Pass only the slotDetailLayout to ScrollPane
        scrollPane.setFitToWidth(true); // Ensures the content fits the width of the ScrollPane
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Always show vertical scrollbar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide horizontal scrollbar

        // Add the title, scrollPane, and buttonBox to the outerLayout
        outerLayout.getChildren().addAll(titleLabel, scrollPane, buttonBox);

        // Scene and stage setup for slot details view with 1000x600 dimensions
        Scene slotDetailScene = new Scene(outerLayout, 1000, 600); // Use outerLayout
        primaryStage.setScene(slotDetailScene);
        primaryStage.setTitle("Case Slot Details");
        primaryStage.show();
    }

    public void displayWitnesses(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> allCases,
            List<Slot> allSlots, List<Clients> allclients, List<Judge> allJudges, List<Lawyer> allLawyers,
            List<Witness> allWitnesses, List<Court> allCourts, Stage primaryStage, GUI_Menu gui,
            CourtsManagementSystem system, Case currentCase) {

        // Main layout container with a modern light background and padding
        VBox mainLayout = new VBox(30);
        mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #f5f5f5); " +
                "-fx-padding: 30; -fx-alignment: center;");

        // Add Title for the page with large font and modern design
        Label pageTitle = new Label("Witness Management for Case: " + currentCase.getCaseID());
        pageTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: 600; -fx-text-fill: #2c3e50; " +
                "-fx-font-family: 'Segoe UI', sans-serif; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0, 0, 3);");
        mainLayout.getChildren().add(pageTitle);

        // Content layout with left and right panes
        HBox contentLayout = new HBox(20);
        contentLayout.setStyle("-fx-alignment: center; -fx-spacing: 20;");

        // Fetch linked witnesses for the current case
        List<Witness> linkedWitnesses = getLinkedWitnesses(allWitnesses);

        // Left Pane: Display linked witnesses with "-" button
        VBox leftPane = new VBox(10);
        leftPane.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 15; -fx-padding: 20;-fx-min-width:400px;");
        Label leftTitle = new Label("Linked Witnesses");
        leftTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: 600; -fx-text-fill: #34495e;");
        leftPane.getChildren().add(leftTitle);

        // Add ScrollPane to the left pane for better scrolling
        VBox linkedWitnessesVBox = new VBox(15);
        for (Witness witness : linkedWitnesses) {
            HBox witnessLayout = new HBox(15);
            witnessLayout.setStyle("-fx-background-color: #ecf0f1; " +
                    "-fx-padding: 15; " +
                    "-fx-border-radius: 15; " +
                    "-fx-effect: dropshadow(gaussian, #bdc3c7, 8, 0, 0, 5); " +
                    "-fx-alignment: left;");

            // Adding the witness's name with a polished font style
            Label witnessName = new Label(witness.getFirstName() + " " + witness.getLastName());
            witnessName.setStyle("-fx-font-size: 16px; " +
                    "-fx-font-weight: bold; " +
                    "-fx-text-fill: #2c3e50; " +
                    "-fx-font-family: 'Segoe UI', sans-serif; " +
                    "-fx-padding: 0 10px;");

            // Adding a Remove button with a smooth transition effect on hover
            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-background-color: #e74c3c; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-padding: 10px 20px; " +
                    "-fx-border-radius: 25px; " +
                    "-fx-font-size: 14px; " +
                    "-fx-transition: background-color 0.3s ease, scale 0.3s ease;");
            removeButton.setOnMouseEntered(e -> {
                removeButton.setStyle("-fx-background-color: #c0392b; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 25px; " +
                        "-fx-font-size: 14px; " +
                        "-fx-scale-x: 1.1; " +
                        "-fx-scale-y: 1.1;");
            });
            removeButton.setOnMouseExited(e -> {
                removeButton.setStyle("-fx-background-color: #e74c3c; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 25px; " +
                        "-fx-font-size: 14px;");
            });
            removeButton.setOnAction(e -> {
                removeWitnessFromCase(witness, currentCase, linkedWitnesses, allWitnesses, dbHandler);
                displayWitnesses(dbHandler, fileHandler, allCases, allSlots, allclients,
                        allJudges, allLawyers, allWitnesses, allCourts,
                        primaryStage, gui, system, currentCase);
            });

            witnessLayout.getChildren().add(removeButton);
            witnessLayout.getChildren().add(witnessName);
            // Adding the layout to the VBox for linked witnesses
            linkedWitnessesVBox.getChildren().add(witnessLayout);

        }

        ScrollPane leftScrollPane = new ScrollPane(linkedWitnessesVBox);
        leftScrollPane.setFitToWidth(true);
        leftScrollPane.setPrefHeight(300); // Limit the height of the ScrollPane
        leftPane.getChildren().add(leftScrollPane);

        // Right Pane: Display all available witnesses with "+" button
        VBox rightPane = new VBox(10);
        rightPane
                .setStyle("-fx-background-color: #ffffff; -fx-border-radius: 15; -fx-padding: 20;-fx-min-width:400px;");
        Label rightTitle = new Label("Available Witnesses");
        rightTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: 600; -fx-text-fill: #34495e;");
        rightPane.getChildren().add(rightTitle);

        // Add ScrollPane to the right pane
        VBox availableWitnessesVBox = new VBox(15);
        for (Witness witness : allWitnesses) {
            if (!linkedWitnesses.contains(witness)) { // Only show unlinked witnesses
                HBox witnessLayout = new HBox(15);
                witnessLayout.setStyle("-fx-background-color: #ecf0f1; " +
                        "-fx-padding: 15; " +
                        "-fx-border-radius: 15; " +
                        "-fx-effect: dropshadow(gaussian, #bdc3c7, 8, 0, 0, 5); " +
                        "-fx-alignment: right;");
                witnessLayout.setAlignment(Pos.CENTER_RIGHT);

                // Adding the witness's name with a modern font and style
                Label witnessName = new Label(witness.getFirstName() + " " + witness.getLastName());
                witnessName.setStyle("-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #2c3e50; " +
                        "-fx-font-family: 'Segoe UI', sans-serif; " +
                        "-fx-padding: 0 10px;");

                // Adding Add button with hover effects and smooth transitions
                Button addButton = new Button("Add");
                addButton.setStyle("-fx-background-color: #27ae60; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-border-radius: 25px; " +
                        "-fx-font-size: 14px; " +
                        "-fx-transition: background-color 0.3s ease, scale 0.3s ease;");

                // Hover Effect for Add Button
                addButton.setOnMouseEntered(e -> {
                    addButton.setStyle("-fx-background-color: #2ecc71; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-weight: bold; " +
                            "-fx-padding: 10px 20px; " +
                            "-fx-border-radius: 25px; " +
                            "-fx-font-size: 14px; " +
                            "-fx-scale-x: 1.1; " +
                            "-fx-scale-y: 1.1;");
                });
                addButton.setOnMouseExited(e -> {
                    addButton.setStyle("-fx-background-color: #27ae60; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-weight: bold; " +
                            "-fx-padding: 10px 20px; " +
                            "-fx-border-radius: 25px; " +
                            "-fx-font-size: 14px;");
                });

                // Add Action for Add Button
                addButton.setOnAction(e -> {
                    addWitnessToCase(witness, currentCase, linkedWitnesses, allWitnesses, dbHandler);
                    displayWitnesses(dbHandler, fileHandler, allCases, allSlots, allclients,
                            allJudges, allLawyers, allWitnesses, allCourts,
                            primaryStage, gui, system, currentCase);
                });
                witnessLayout.getChildren().add(witnessName);
                witnessLayout.getChildren().add(addButton);
                availableWitnessesVBox.getChildren().add(witnessLayout);
            }

        }

        ScrollPane rightScrollPane = new ScrollPane(availableWitnessesVBox);
        rightScrollPane.setFitToWidth(true);
        rightScrollPane.setPrefHeight(300); // Limit the height of the ScrollPane
        rightPane.getChildren().add(rightScrollPane);

        // Add both panes to the content layout
        contentLayout.getChildren().addAll(leftPane, rightPane);
        mainLayout.getChildren().add(contentLayout);

        // Bottom Button Layout: Register and Close buttons
        HBox buttonLayout = new HBox(20);
        buttonLayout.setStyle("-fx-alignment: center; -fx-padding: 20 0 10 0;");
        Button registerNewButton = new Button("Register New Witness");
        registerNewButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-padding: 12px 20px; " +
                "-fx-font-size: 16px; -fx-border-radius: 25; -fx-font-weight: bold;");
        registerNewButton.setOnAction(e -> {
            registerRegister(dbHandler, fileHandler, allCases, allSlots, allclients,
                    allJudges, allLawyers, allWitnesses, allCourts,
                    primaryStage, gui, system, this);
        });

        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 12px 20px; " +
                "-fx-font-size: 16px; -fx-border-radius: 25; -fx-font-weight: bold;");
        closeButton.setOnAction(e -> updateCaseData(dbHandler, fileHandler, allCases, allSlots, allclients, allJudges,
                allLawyers, allWitnesses, allCourts, primaryStage, gui, system));

        buttonLayout.getChildren().addAll(registerNewButton, closeButton);
        mainLayout.getChildren().add(buttonLayout);

        // Set up the scene and the stage
        Scene scene = new Scene(mainLayout, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Witness Management");
        primaryStage.show();
    }

    // Add witness to the case
    private void addWitnessToCase(Witness witness, Case currentCase, List<Witness> linkedWitnesses,
            List<Witness> allWitnesses, DatabaseHandler dbHandler) {
        linkedWitnesses.add(witness); // Add witness to the linked list
        witness.addCaseID(currentCase.getCaseID()); // Link the witness to the case
        dbHandler.updateWitness(witness, this.caseID);
        // Optionally persist changes to the database or system here
    }

    // Remove witness from the case
    private void removeWitnessFromCase(Witness witness, Case currentCase, List<Witness> linkedWitnesses,
            List<Witness> allWitnesses, DatabaseHandler dbHandler) {
        linkedWitnesses.remove(witness); // Remove witness from linked list
        witness.CaseID.remove(Integer.valueOf(currentCase.getCaseID())); // Unlink the witness from the case
        dbHandler.deleteWitness(witness, this.caseID);
        // Optionally persist changes to the database or system here
    }

    // Fetch linked witnesses for the case
    private List<Witness> getLinkedWitnesses(List<Witness> allWitnesses) {
        List<Witness> witnesslist = new ArrayList<>();
        for (Witness w : allWitnesses) {
            if (w.CaseID.contains(this.caseID)) {
                witnesslist.add(w);
            }
        }
        return witnesslist; // Assuming Case class has a method to get linked witnesses
    }

    public void registerRegister(DatabaseHandler dbHandler, FileHandler fileHandler, List<Case> allCases,
            List<Slot> allSlots, List<Clients> allclients, List<Judge> allJudges, List<Lawyer> allLawyers,
            List<Witness> allWitnesses, List<Court> allCourts, Stage primaryStage, GUI_Menu gui,
            CourtsManagementSystem system, Case currentCase) {
        // Create UI elements for witness registration
        Label titleLabel = new Label("Register Witness");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Witness form fields
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label dobLabel = new Label("Date of Birth:");
        DatePicker dobPicker = new DatePicker();

        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female", "Other");

        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();

        Label phoneNumberLabel = new Label("Phone Number:");
        TextField phoneNumberField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        // Register button
        Button registerButton = new Button("Register");

        // Setup form layout
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(lastNameLabel, 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(dobLabel, 0, 2);
        grid.add(dobPicker, 1, 2);
        grid.add(genderLabel, 0, 3);
        grid.add(genderComboBox, 1, 3);
        grid.add(addressLabel, 0, 4);
        grid.add(addressField, 1, 4);
        grid.add(phoneNumberLabel, 0, 5);
        grid.add(phoneNumberField, 1, 5);
        grid.add(emailLabel, 0, 6);
        grid.add(emailField, 1, 6);
        grid.add(registerButton, 1, 7);

        // Handle register button click
        registerButton.setOnAction(e -> {
            // Validate inputs
            if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || dobPicker.getValue() == null
                    ||
                    genderComboBox.getValue() == null || addressField.getText().isEmpty()
                    || phoneNumberField.getText().isEmpty() || emailField.getText().isEmpty()) {
                showAlert("All fields are required!", Alert.AlertType.ERROR);
                return;
            }

            // Create witness object with entered data
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            Date dateOfBirth = java.sql.Date.valueOf(dobPicker.getValue());
            String gender = genderComboBox.getValue();
            String address = addressField.getText();
            String phoneNumber = phoneNumberField.getText();
            String email = emailField.getText();

            // Assuming you have a User class constructor to pass user data
            int userID = generateUserID(); // You need a method to generate unique user IDs
            int witnessID = generateWitnessID(); // Similarly, generate a unique witness ID

            // Create a new Witness object (add your necessary parameters like userID)
            Witness newWitness = new Witness(userID, "username", "password", "Witness", email, phoneNumber, true,
                    witnessID, firstName, lastName, dateOfBirth, gender, address);

            // You would save the newWitness object to your database or system here
            newWitness.addCaseID(caseID);
            dbHandler.addWitness(newWitness);
            dbHandler.updateWitness(newWitness, this.caseID);
            showAlert("Witness Registered Successfully!", Alert.AlertType.INFORMATION);
            clearForm(firstNameField, lastNameField, dobPicker, genderComboBox, addressField, phoneNumberField,
                    emailField);
            displayWitnesses(dbHandler, fileHandler, allCases, allSlots, allclients,
                    allJudges, allLawyers, allWitnesses, allCourts,
                    primaryStage, gui, system, currentCase);
        });

        // Create main layout and scene
        VBox mainLayout = new VBox(20, titleLabel, grid);
        Scene scene = new Scene(mainLayout, 400, 400);

        // Set up the stage
        primaryStage.setTitle("Witness Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Show alert dialog
    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Clear form fields after registration
    private void clearForm(TextField firstNameField, TextField lastNameField, DatePicker dobPicker,
            ComboBox<String> genderComboBox,
            TextField addressField, TextField phoneNumberField, TextField emailField) {
        firstNameField.clear();
        lastNameField.clear();
        dobPicker.setValue(null);
        genderComboBox.getSelectionModel().clearSelection();
        addressField.clear();
        phoneNumberField.clear();
        emailField.clear();
    }

    // Generate unique user ID (for example, could be from a database or UUID)
    private int generateUserID() {
        return (int) (Math.random() * 10000); // Example random user ID
    }

    // Generate unique witness ID (could be from database or sequence)
    private int generateWitnessID() {
        return (int) (Math.random() * 10000); // Example random witness ID
    }

    // // Function to display files (simple representation)
    // private void displayFiles(Stage primaryStage, List<String> allFiles) {
    // StackPane filePane = new StackPane();
    // VBox fileLayout = new VBox(10);

    // for (String file : allFiles) {
    // fileLayout.getChildren().add(new Label("File: " + file));
    // }

    // Button returnButton = new Button("Return");
    // returnButton.setOnAction(e -> primaryStage.setScene(new Scene(new VBox(10,
    // new Button("Go back to Main")))));

    // fileLayout.getChildren().add(returnButton);
    // filePane.getChildren().add(fileLayout);

    // Scene fileScene = new Scene(filePane, 400, 400);
    // primaryStage.setScene(fileScene);
    // }

    // Method to schedule the next slot (for demonstration, it moves to the next
    // slot)
    // private void scheduleNextSlot(Stage primaryStage, List<Slot> allSlots) {
    // // Simulate scheduling the next slot (in this case, we just move to the next
    // // slot in the list)
    // Slot nextSlot = allSlots.get(1); // Schedule the second slot for
    // demonstration

    // StackPane nextSlotPane = new StackPane();
    // VBox nextSlotLayout = new VBox(10);
    // Label nextSlotLabel = new Label("Next Slot: " + nextSlot.() +
    // "\nLocation: " + nextSlot.getLocation() +
    // "\nTime: " + nextSlot.getTime() +
    // "\nAssigned To: " + nextSlot.getAssignedTo());

    // Button returnButton = new Button("Return");
    // returnButton.setOnAction(e -> primaryStage.setScene(new Scene(new VBox(10,
    // new Button("Go back to Main")))));

    // nextSlotLayout.getChildren().addAll(nextSlotLabel, returnButton);
    // nextSlotPane.getChildren().add(nextSlotLayout);

    // Scene nextSlotScene = new Scene(nextSlotPane, 400, 400);
    // primaryStage.setScene(nextSlotScene);
    // }

    /**
     * Checks if a case with the given caseID exists in the AllCases list.
     *
     * @param caseID The unique identifier of the case to search for.
     * @return true if a case with the specified caseID exists; false otherwise.
     */
    public boolean doesCaseExist(int caseID, List<Case> AllCases) {
        // Iterate through the list of all cases
        for (Case c : AllCases) {
            // Check if the current case's ID matches the provided caseID
            if (c.getCaseID() == caseID) {
                return true; // Case found
            }
        }
        // Case not found after iterating through the list
        return false;
    }

    public Case getCasebyID(int caseID, List<Case> AllCases) {
        // Iterate through the list of all cases
        for (Case c : AllCases) {
            // Check if the current case's ID matches the provided caseID
            if (c.getCaseID() == caseID) {
                return c; // Case found
            }
        }
        // Case not found after iterating through the list
        return null;
    }

    public List<Integer> getStakeholders(List<Clients> AllClients, List<Slot> AllSlots, List<Judge> AllJudges,
            List<Lawyer> AllLawyers) {
        List<Integer> output = new ArrayList<>();

        for (Clients c : AllClients) {

            if (plaintiffID != 0 && this.plaintiffID == c.getclientID()) {
                output.add(c.getUserID());
            }
            if (defendantID != 0 && this.defendantID == c.getclientID()) {
                output.add(c.getUserID());
            }
        }
        for (Lawyer l : AllLawyers) {

            if (Lawyerid != 0 && this.Lawyerid == l.getLawyerID()) {
                output.add(l.getUserID());
                break;
            }

        }
        for (Slot s : AllSlots) {
            if (s.getJudgeID() != null && s.getCaseID() == this.caseID) {
                for (Judge j : AllJudges) {
                    if (s.getJudgeID().equals(j.getJudgeID())) {
                        output.add(j.getUserID());
                        return output;
                    }
                }
            }
        }

        return output;
    }

}
