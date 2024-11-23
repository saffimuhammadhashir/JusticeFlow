package JusticeFlow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import JusticeFlow.CourtsManagementSystem.GUI_Menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Slot {
    private int slotID;
    public LocalDate dayName;
    private LocalTime startTime;
    private LocalTime endTime;
    private int slotNumber;
    private Integer caseID;
    private Integer judgeID;
    private Integer witnessID;
    private Integer CourtID;

    // Constructor
    public Slot() {
    }

    public Slot(int slotID, LocalDate dayName, LocalTime startTime, LocalTime endTime, int slotNumber,
            Integer caseID, Integer judgeID, Integer witnessID, Integer CourtID) {
        this.slotID = slotID;
        this.dayName = dayName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotNumber = slotNumber;
        this.caseID = caseID;
        this.judgeID = judgeID;
        this.witnessID = witnessID;
        this.CourtID = CourtID;
    }

    public Slot(Slot other) {
        this.slotID = other.slotID;
        this.dayName = other.dayName;
        this.startTime = other.startTime;
        this.endTime = other.endTime;
        this.slotNumber = other.slotNumber;
        this.caseID = other.caseID;
        this.judgeID = other.judgeID;
        this.witnessID = other.witnessID;
        this.CourtID = other.CourtID;
    }

    // Custom implementation of isBefore
    public boolean isBefore(LocalTime otherTime) {
        return this.startTime.isBefore(otherTime);
    }

    // Custom implementation of isAfter
    public boolean isAfter(LocalTime otherTime) {
        return this.startTime.isAfter(otherTime);
    }

    // Getters and Setters
    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public LocalDate getDayName() {
        return dayName;
    }

    public LocalDate getDate() {
        return dayName;
    }

    public void setDayName(LocalDate dayName) {
        this.dayName = dayName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Integer getCaseID() {
        return caseID;
    }

    public void setCaseID(Integer caseID) {
        this.caseID = caseID;
    }

    public Integer getJudgeID() {
        return judgeID;
    }

    public void setJudgeID(Integer judgeID) {
        this.judgeID = judgeID;
    }

    public Integer getWitnessID() {
        return witnessID;
    }

    public Integer getCourtId() {
        return this.CourtID;
    }

    public void setCourtId(Integer courtid) {
        this.CourtID = courtid;
    }

    public void setWitnessID(Integer witnessID) {
        this.witnessID = witnessID;
    }

    public static void newSlotCreation(List<Slot> AllSlot, List<Judge> AllJudges, List<Court> AllCourt,
            List<Witness> AllWitnesses, Case cases, DatabaseHandler dbHandler,
            Stage primaryStage, GUI_Menu gui, CourtsManagementSystem system) {

        for (Slot s : AllSlot) {
            System.out.println(s.toString());
        }

        // Create UI components
        VBox outerbox = new VBox(25);

        Label headingLabel = new Label("Slot Scheduler");
        headingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 44));
        headingLabel.setTextAlignment(TextAlignment.CENTER);

        Label dateLabel = new Label("Select Date:");
        dateLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label timeSlotLabel = new Label("Select Time Slot:");
        timeSlotLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label courtLabel = new Label("Available Courts:");
        courtLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label judgeLabel = new Label("Available Judges:");
        judgeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label WitnessLabel = new Label("Available Witnesses:");
        WitnessLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        DatePicker datePicker = new DatePicker();
        ComboBox<String> timeSlotComboBox = new ComboBox<>();
        ComboBox<Integer> courtComboBox = new ComboBox<>();
        ComboBox<Integer> judgeComboBox = new ComboBox<>();
        ComboBox<Integer> WitnessComboBox = new ComboBox<>();

        Button searchButton = new Button("Search Slot");
        Button scheduleButton = new Button("Schedule");
        Button rejectButton = new Button("Cancel");

        // Style buttons
        searchButton.setStyle(
                "-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 15;");
        scheduleButton.setStyle(
                "-fx-background-color: #28A745; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 15;");
        rejectButton.setStyle(
                "-fx-background-color: #DC3545; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 15;");

        // Set uniform widths for ComboBoxes and buttons
        timeSlotComboBox.setPrefWidth(300);
        courtComboBox.setPrefWidth(300);
        judgeComboBox.setPrefWidth(300);
        WitnessComboBox.setPrefWidth(300);
        searchButton.setPrefWidth(120);
        scheduleButton.setPrefWidth(120);
        rejectButton.setPrefWidth(120);

        // Restrict DatePicker to future dates
        datePicker.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now().plusDays(1))); // Disable past dates
            }
        });

        // Populate time slots when a date is selected
        datePicker.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            if (selectedDate != null) {
                List<String> timeSlots = generateTimeSlots(selectedDate);
                timeSlotComboBox.getItems().setAll(timeSlots);
            }
        });
        courtComboBox.setVisible(false);
        courtLabel.setVisible(false);
        judgeComboBox.setVisible(false);
        judgeLabel.setVisible(false);
        WitnessComboBox.setVisible(false);
        WitnessLabel.setVisible(false);
        rejectButton.setOnAction(e -> {
            system.ReviewUpcomingCaseRequests(primaryStage, gui);
        });
        // Populate available courts and judges based on the selected date and time slot
        searchButton.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            String selectedTimeSlot = timeSlotComboBox.getValue();
            if (selectedDate != null && selectedTimeSlot != null) {
                String[] times = selectedTimeSlot.split(" - ");
                LocalTime startTime = LocalTime.parse(times[0]);
                LocalTime endTime = LocalTime.parse(times[1]);

                List<Integer> availableCourts = new ArrayList<>();
                List<Integer> availableJudges = new ArrayList<>();
                List<Integer> availablewitnesses = new ArrayList<>();
                searchAvailableSlots(AllSlot, AllJudges, AllCourt, AllWitnesses, selectedDate, startTime, endTime,
                        availableCourts,
                        availableJudges, availablewitnesses, cases.getCaseID());

                courtComboBox.getItems().setAll(availableCourts);
                judgeComboBox.getItems().setAll(availableJudges);
                WitnessComboBox.getItems().setAll(availablewitnesses);

                // Make the court and judge dropdowns visible
                courtComboBox.setVisible(true);
                courtLabel.setVisible(true);
                judgeComboBox.setVisible(true);
                judgeLabel.setVisible(true);
                WitnessComboBox.setVisible(true);
                WitnessLabel.setVisible(true);
            }
        });

        // Schedule the slot when user selects and clicks "Schedule"
        scheduleButton.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            String selectedTimeSlot = timeSlotComboBox.getValue();
            Integer selectedCourt = courtComboBox.getValue();
            Integer selectedJudge = judgeComboBox.getValue();
            Integer selectedwitness = WitnessComboBox.getValue();

            if (selectedDate != null && selectedTimeSlot != null && selectedCourt != null && selectedJudge != null) {
                String[] times = selectedTimeSlot.split(" - ");
                LocalTime startTime = LocalTime.parse(times[0]);
                LocalTime endTime = LocalTime.parse(times[1]);

                Slot newSlot = new Slot(AllSlot.size() + 1, selectedDate, startTime, endTime, 0, cases.getCaseID(),
                        selectedJudge, selectedwitness, selectedCourt);

                AllSlot.add(newSlot);
                dbHandler.updateOrInsertSlots(AllSlot);
                cases.setCaseStatus("Opened");
                dbHandler.saveOrUpdateCase(cases);
                system.ReviewUpcomingCaseRequests(primaryStage, gui);
                System.out.println("New slot scheduled: " + newSlot);
            }
        });

        // Layout for the scene
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);

        // Group components into sections
        VBox dd1 = new VBox(25, courtLabel, courtComboBox), dd2 = new VBox(25, judgeLabel, judgeComboBox),
                dd3 = new VBox(25, WitnessLabel, WitnessComboBox);
        HBox dropdowns = new HBox(25, dd1, dd2, dd3);
        VBox dateSection = new VBox(10, dateLabel, datePicker, timeSlotLabel, timeSlotComboBox, searchButton);
        VBox courtSection = new VBox(10, dropdowns);
        HBox buttonLayout = new HBox(15, scheduleButton, rejectButton);
        outerbox.setAlignment(Pos.CENTER);
        dateSection.setAlignment(Pos.CENTER_LEFT);
        courtSection.setAlignment(Pos.CENTER_LEFT);
        buttonLayout.setAlignment(Pos.CENTER);
        outerbox.getChildren().addAll(headingLabel, dateSection, courtSection, buttonLayout);
        mainLayout.getChildren().add(outerbox);
        outerbox.setStyle(
                "-fx-padding: 20px 150px;" +
                        "-fx-background-color: #E8F0FE;" + // Light blue background for a soft touch
                        "-fx-border-color: #0078D7;" + // Border color for emphasis
                        "-fx-border-width: 2px;" + // Subtle border width
                        "-fx-border-radius: 25px;" + // Rounded corners for border
                        "-fx-background-radius: 25px;" // Matching rounded corners for background
        );

        mainLayout.setStyle(
                "-fx-padding: 20px 50px;" +
                        "-fx-background-color: #F0F0F0;" + // Clean white background for content
                        "-fx-background-radius: 15px;" + // Rounded corners for background
                        "-fx-border-radius: 15px;" + // Matching rounded corners for border
                        "-fx-border-width: 1px;" + // Thin border for a clean outline
                        "-fx-border-color: #D6D6D6;" + // Soft gray border for subtle separation
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 10, 0.5, 0, 5);" // Light shadow for depth
        );
        Scene scene = new Scene(mainLayout, 1000, 600);

        primaryStage.setTitle("Slot Scheduler");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void newSlotScheduler(
            List<Slot> AllSlot, List<Judge> AllJudges, List<Court> AllCourt,
            List<Witness> AllWitnesses, Case cases, DatabaseHandler dbHandler,
            Stage primaryStage, GUI_Menu gui, CourtsManagementSystem system,
            FileHandler fileHandler, List<Case> allCases,
            List<Clients> allclients, List<Lawyer> allLawyers) {

        for (Slot s : AllSlot) {
            System.out.println(s.toString());
        }

        // Create UI components
        VBox outerbox = new VBox(25);

        Label headingLabel = new Label("Slot Scheduler");
        headingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 44));
        headingLabel.setTextAlignment(TextAlignment.CENTER);

        Label dateLabel = new Label("Select Date:");
        dateLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label timeSlotLabel = new Label("Select Time Slot:");
        timeSlotLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label courtLabel = new Label("Available Courts:");
        courtLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label judgeLabel = new Label("Available Judges:");
        judgeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label WitnessLabel = new Label("Available Witnesses:");
        WitnessLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        DatePicker datePicker = new DatePicker();
        ComboBox<String> timeSlotComboBox = new ComboBox<>();
        ComboBox<Integer> courtComboBox = new ComboBox<>();
        ComboBox<Integer> judgeComboBox = new ComboBox<>();
        ComboBox<Integer> WitnessComboBox = new ComboBox<>();

        Button searchButton = new Button("Search Slot");
        Button scheduleButton = new Button("Schedule");
        Button rejectButton = new Button("Cancel");

        // Style buttons
        searchButton.setStyle(
                "-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 15;");
        scheduleButton.setStyle(
                "-fx-background-color: #28A745; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 15;");
        rejectButton.setStyle(
                "-fx-background-color: #DC3545; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 15;");

        // Set uniform widths for ComboBoxes and buttons
        timeSlotComboBox.setPrefWidth(300);
        courtComboBox.setPrefWidth(300);
        judgeComboBox.setPrefWidth(300);
        WitnessComboBox.setPrefWidth(300);
        searchButton.setPrefWidth(120);
        scheduleButton.setPrefWidth(120);
        rejectButton.setPrefWidth(120);

        // Restrict DatePicker to future dates
        datePicker.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now().plusDays(1))); // Disable past dates
            }
        });

        // Populate time slots when a date is selected
        datePicker.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            if (selectedDate != null) {
                List<String> timeSlots = generateTimeSlots(selectedDate);
                timeSlotComboBox.getItems().setAll(timeSlots);
            }
        });
        courtComboBox.setVisible(false);
        courtLabel.setVisible(false);
        judgeComboBox.setVisible(false);
        judgeLabel.setVisible(false);
        WitnessComboBox.setVisible(false);
        WitnessLabel.setVisible(false);
        rejectButton.setOnAction(e -> {
            system.ReviewUpcomingCaseRequests(primaryStage, gui);
        });
        // Populate available courts and judges based on the selected date and time slot
        searchButton.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            String selectedTimeSlot = timeSlotComboBox.getValue();
            if (selectedDate != null && selectedTimeSlot != null) {
                String[] times = selectedTimeSlot.split(" - ");
                LocalTime startTime = LocalTime.parse(times[0]);
                LocalTime endTime = LocalTime.parse(times[1]);

                List<Integer> availableCourts = new ArrayList<>();
                List<Integer> availableJudges = new ArrayList<>();
                List<Integer> availablewitnesses = new ArrayList<>();
                searchAvailableSlots(AllSlot, AllJudges, AllCourt, AllWitnesses, selectedDate, startTime, endTime,
                        availableCourts,
                        availableJudges, availablewitnesses, cases.getCaseID());

                courtComboBox.getItems().setAll(availableCourts);
                judgeComboBox.getItems().setAll(availableJudges);
                WitnessComboBox.getItems().setAll(availablewitnesses);

                // Make the court and judge dropdowns visible
                courtComboBox.setVisible(true);
                courtLabel.setVisible(true);
                judgeComboBox.setVisible(true);
                judgeLabel.setVisible(true);
                WitnessComboBox.setVisible(true);
                WitnessLabel.setVisible(true);
            }
        });

        // Schedule the slot when user selects and clicks "Schedule"
        scheduleButton.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            String selectedTimeSlot = timeSlotComboBox.getValue();
            Integer selectedCourt = courtComboBox.getValue();
            Integer selectedJudge = judgeComboBox.getValue();
            Integer selectedwitness = WitnessComboBox.getValue();

            if (selectedDate != null && selectedTimeSlot != null && selectedCourt != null && selectedJudge != null) {
                String[] times = selectedTimeSlot.split(" - ");
                LocalTime startTime = LocalTime.parse(times[0]);
                LocalTime endTime = LocalTime.parse(times[1]);

                Slot newSlot = new Slot(AllSlot.size() + 1, selectedDate, startTime, endTime, 0, cases.getCaseID(),
                        selectedJudge, selectedwitness, selectedCourt);

                AllSlot.add(newSlot);
                dbHandler.updateOrInsertSlots(AllSlot);
                cases.setCaseStatus("Opened");
                dbHandler.saveOrUpdateCase(cases);
                cases.displaySchedules(dbHandler, fileHandler, allCases, AllSlot, allclients,
                        AllJudges, allLawyers, AllWitnesses, AllCourt,
                        primaryStage, gui, system);
                System.out.println("New slot scheduled: " + newSlot);
            }
        });
        rejectButton.setOnAction(e -> cases.displaySchedules(dbHandler, fileHandler, allCases, AllSlot, allclients,
                AllJudges, allLawyers, AllWitnesses, AllCourt,
                primaryStage, gui, system));
        // Layout for the scene
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);

        // Group components into sections
        VBox dd1 = new VBox(25, courtLabel, courtComboBox), dd2 = new VBox(25, judgeLabel, judgeComboBox),
                dd3 = new VBox(25, WitnessLabel, WitnessComboBox);
        HBox dropdowns = new HBox(25, dd1, dd2, dd3);
        VBox dateSection = new VBox(10, dateLabel, datePicker, timeSlotLabel, timeSlotComboBox, searchButton);
        VBox courtSection = new VBox(10, dropdowns);
        HBox buttonLayout = new HBox(15, scheduleButton, rejectButton);
        outerbox.setAlignment(Pos.CENTER);
        dateSection.setAlignment(Pos.CENTER_LEFT);
        courtSection.setAlignment(Pos.CENTER_LEFT);
        buttonLayout.setAlignment(Pos.CENTER);
        outerbox.getChildren().addAll(headingLabel, dateSection, courtSection, buttonLayout);
        mainLayout.getChildren().add(outerbox);
        outerbox.setStyle(
                "-fx-padding: 20px 150px;" +
                        "-fx-background-color: #E8F0FE;" + // Light blue background for a soft touch
                        "-fx-border-color: #0078D7;" + // Border color for emphasis
                        "-fx-border-width: 2px;" + // Subtle border width
                        "-fx-border-radius: 25px;" + // Rounded corners for border
                        "-fx-background-radius: 25px;" // Matching rounded corners for background
        );

        mainLayout.setStyle(
                "-fx-padding: 20px 50px;" +
                        "-fx-background-color: #F0F0F0;" + // Clean white background for content
                        "-fx-background-radius: 15px;" + // Rounded corners for background
                        "-fx-border-radius: 15px;" + // Matching rounded corners for border
                        "-fx-border-width: 1px;" + // Thin border for a clean outline
                        "-fx-border-color: #D6D6D6;" + // Soft gray border for subtle separation
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 10, 0.5, 0, 5);" // Light shadow for depth
        );
        Scene scene = new Scene(mainLayout, 1000, 600);

        primaryStage.setTitle("Slot Scheduler");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static List<String> generateTimeSlots(LocalDate date) {
        List<String> timeSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(9, 0); // Start at 9 AM
        LocalTime endTime = LocalTime.of(20, 0); // End at 8 PM
        LocalTime currentTime = startTime;

        while (currentTime.isBefore(endTime)) {
            String timeSlot = currentTime.toString() + " - " + currentTime.plusMinutes(30).toString();
            timeSlots.add(timeSlot);
            currentTime = currentTime.plusMinutes(30);
        }
        return timeSlots;
    }

    private static void searchAvailableSlots(
            List<Slot> allSlots,
            List<Judge> AllJudges,
            List<Court> AllCourt,
            List<Witness> AllWitnesses,
            LocalDate selectedDate,
            LocalTime startTime,
            LocalTime endTime,
            List<Integer> availableCourtIds,
            List<Integer> availableJudgeIds,
            List<Integer> availableWitnessIds,
            int caseId) {

        // Initialize available courts, judges, and witnesses
        for (Court court : AllCourt) {
            availableCourtIds.add(court.getCourtID());
        }
        for (Judge judge : AllJudges) {
            availableJudgeIds.add(judge.getJudgeID());
        }
        for (Witness witness : AllWitnesses) {
            // Only consider witnesses affiliated with the given case
            if (witness.CaseID.contains(caseId)) {
                availableWitnessIds.add(witness.getWitnessID());
            }
        }

        // Traverse through all slots and check for availability
        for (Slot slot : allSlots) {
            // Check if the slot overlaps with the selected date and time
            if (slot.getDate().equals(selectedDate) &&
                    (slot.startTime.equals(startTime) || slot.endTime.equals(endTime) ||
                            (slot.startTime.isBefore(endTime) && slot.endTime.isAfter(startTime)))) {

                // Remove courts, judges, and witnesses already booked in the slot
                availableCourtIds.remove(Integer.valueOf(slot.getCourtId()));
                availableJudgeIds.remove(Integer.valueOf(slot.judgeID));
                if (slot.getWitnessID() != null) {
                    availableWitnessIds.remove(Integer.valueOf(slot.getWitnessID()));
                }
            }
        }

        // Debugging output to check results
        System.out.println("Available Courts: " + availableCourtIds);
        System.out.println("Available Judges: " + availableJudgeIds);
        System.out.println("Available Witnesses: " + availableWitnessIds);
    }

    // toString() method
    @Override
    public String toString() {
        return "Slot{" +
                "slotID=" + slotID +
                ", dayName='" + dayName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", slotNumber=" + slotNumber +
                ", caseID=" + caseID +
                ", judgeID=" + judgeID +
                ", witnessID=" + witnessID + ", CourtId=" + CourtID +
                "}\n\n------------------------------------------\n";
    }

    public static void manageslot(List<Slot> AllSlot, DatabaseHandler dbHandler) {

        int count = 0;
        for (Slot s : AllSlot) {
            if (s == null) {
                continue;
            }
            if (s.getCaseID() == null && s.getJudgeID() == null && s.getWitnessID() == null) {
                count++;
            }
        }
        if (count < 15) {
            count = 50 - count;
            while (count > 0) {
                // Slot newslot = createNextSlot(AllSlot);
                // AllSlot.add(newslot);
                count--;
            }
        }
        dbHandler.updateOrInsertSlots(AllSlot);

        return;
    }

    // private static Slot createNextSlot(List<Slot> allSlots) {

    // Time startOfDay = Time.valueOf("09:00:00");
    // Time endOfDay = Time.valueOf("19:00:00");

    // LocalDate currentDate = LocalDate.now();
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // String dayName = currentDate.format(formatter);

    // // if (allSlots.isEmpty()) {
    // // return new Slot(1, dayName, startOfDay, Time.valueOf("09:30:00"), 0, null,
    // null, null, null);
    // // }

    // Slot lastSlot = allSlots.get(allSlots.size() - 1);
    // LocalDate lastDate = LocalDate.parse(lastSlot.getDayName());
    // LocalTime lastEndTime = lastSlot.getEndTime().toLocalTime();

    // LocalTime nextStartTime = lastEndTime.isBefore(endOfDay.toLocalTime()) ?
    // lastEndTime : LocalTime.of(9, 0);
    // LocalDate nextDate = lastEndTime.isBefore(endOfDay.toLocalTime()) ? lastDate
    // : lastDate.plusDays(1);

    // LocalTime nextEndTime = nextStartTime.plusMinutes(30);
    // if (nextEndTime.isAfter(endOfDay.toLocalTime())) {

    // nextStartTime = LocalTime.of(9, 0);
    // nextEndTime = nextStartTime.plusMinutes(30);
    // nextDate = nextDate.plusDays(1);
    // }

    // if (nextEndTime.isAfter(endOfDay.toLocalTime())) {
    // return null;
    // }

    // int maxSlotID = 0;
    // for (Slot slot : allSlots) {
    // if (slot != null && slot.getSlotID() > maxSlotID) {
    // maxSlotID = slot.getSlotID();
    // }
    // }
    // int newSlotID = maxSlotID + 1;

    // String nextDayName = nextDate.format(formatter);

    // Time nextStartTimeSQL = Time.valueOf(nextStartTime);
    // Time nextEndTimeSQL = Time.valueOf(nextEndTime);

    // return allSlots;
    // }

    public static Slot getLastSlotAtFarthestTime(List<Slot> allSlots) {
        if (allSlots == null || allSlots.isEmpty()) {
            return null;
        }

        Slot lastSlot = allSlots.get(0);
        // for (Slot slot : allSlots) {
        // if (slot.getEndTime().after(lastSlot.getEndTime())) {
        // lastSlot = slot;
        // }
        // }
        return lastSlot;
    }

    public static void PossibleSchedule(List<Slot> AllSlot, Judge judge, Witness witness, Court court,
            List<Slot> possibleSlots) {

        for (Slot s : AllSlot) {
            if (s == null) {
                break;
            }
            if (s.getCaseID() == null && s.getJudgeID() == null && s.getWitnessID() == null
                    && s.getCourtId() == null) {
                Slot new_s = new Slot(s);
                new_s.setJudgeID(judge.getJudgeID());
                if (witness != null) {
                    new_s.setWitnessID(witness.getWitnessID());
                }
                new_s.setCourtId(court.getCourtID());
                possibleSlots.add(new_s);
                System.out.println(s.toString());
            } else if (!(s.getCourtId().equals(null) || s.getCourtId().equals(court.getCourtID()))
                    && s.getJudgeID() == null) {
                Slot new_s = new Slot(s);
                new_s.setJudgeID(judge.getJudgeID());
                if (witness != null) {
                    new_s.setWitnessID(witness.getWitnessID());
                }
                new_s.setCourtId(court.getCourtID());
                possibleSlots.add(new_s);
                System.out.println(s.toString());
            }
        }
        if (!possibleSlots.isEmpty()) {
            return;
        }

    }

    public static void removeSlotsWithSameIDOneByOne(Slot targetSlot, List<Slot> slotList) {
        if (targetSlot == null || slotList == null) {
            return; // Handle null inputs gracefully
        }

        Iterator<Slot> iterator = slotList.iterator(); // Initialize iterator
        // // Get current time and add 12 hours to it
        // LocalDateTime currentTime = LocalDateTime.now();
        // LocalDateTime cutoffTime = currentTime.plusHours(12);
        // System.out.println("Cutoff Time: " + cutoffTime); // For debugging purposes

        // // First, remove all slots whose start time is before the current time + 12
        // hours
        // while (iterator.hasNext()) {
        // Slot currentSlot = iterator.next();

        // // Convert currentSlot's startTime (java.sql.Time) to LocalDateTime
        // LocalTime startTime = currentSlot.getStartTime().toLocalTime();

        // // Parse the date from dayName (assuming dayName is in the format
        // "yyyy-MM-dd")
        // LocalDateTime slotStartTime = parseSlotStartTime(currentSlot.getDayName(),
        // startTime);

        // // Remove slots where the start time is before current time and before cutoff
        // time
        // if (slotStartTime.isBefore(currentTime) &&
        // slotStartTime.isBefore(cutoffTime)) {
        // iterator.remove();
        // System.out.println("Removed slot with ID (cutoff time condition): " +
        // currentSlot.getSlotID());
        // }
        // }

        // Now, remove slots with the same slot ID as the target slot
        iterator = slotList.iterator(); // Reinitialize iterator to remove slots by targetSlot ID
        while (iterator.hasNext()) {
            Slot currentSlot = iterator.next();

            if (currentSlot.getSlotID() == targetSlot.getSlotID()) {
                iterator.remove();
                // System.out.println("Removed slot with ID (matching target): " +
                // currentSlot.getSlotID());
            }
        }
    }

    private static LocalDateTime parseSlotStartTime(String dayName, LocalTime startTime) {
        // Format the startTime into a full string like "09:00:00"
        String formattedStartTime = String.format("%02d:%02d:%02d",
                startTime.getHour(),
                startTime.getMinute(),
                startTime.getSecond());

        // Combine the dayName and formattedStartTime into a full datetime string
        String fullDateTimeString = dayName + "T" + formattedStartTime;

        // Define a DateTimeFormatter to parse the full date-time string
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        // Convert the dayName + startTime to LocalDateTime
        return LocalDateTime.parse(fullDateTimeString, formatter);
    }

    public static void removeprevious(List<Slot> AllSlot, Case cases, Slot replaced) {
        for (Slot s : AllSlot) {
            if (s.getCaseID() != null && s.getCaseID().equals(cases.getCaseID())) {
                Integer tempCaseID = replaced.getCaseID();
                Integer tempCourtID = replaced.getCourtId();
                Integer tempJudgeID = replaced.getJudgeID();
                Integer tempWitnessID = replaced.getWitnessID();
                replaced.setCaseID(s.getCaseID());
                replaced.setCourtId(s.getCourtId());
                replaced.setJudgeID(s.getJudgeID());
                replaced.setWitnessID(s.getWitnessID());
                s.setCaseID(tempCaseID);
                s.setCourtId(tempCourtID);
                s.setJudgeID(tempJudgeID);
                s.setWitnessID(tempWitnessID);
            }
        }
    }

}
