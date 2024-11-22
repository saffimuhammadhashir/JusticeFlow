package JusticeFlow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;

public class User {
    private int userID;
    private String username;
    private String password;
    private String role;
    private String email;
    private String phoneNumber;
    private boolean activate;

    public User() {

    }

    public User(int userID, String username, String password, String role, String email, String phoneNumber,
            boolean activate) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.activate = activate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    /*
     * Searches for a lawyer in the provided list based on the given UserID.
     *
     * @param AllLawyers The list of all lawyers to search through.
     * 
     * @param UserID The unique identifier of the lawyer to search for.
     * 
     * @return The Lawyer object with the matching LawyerID, or null if no match is
     * found.
     */

    public Registrar getRelevantRegistrar(List<Registrar> AllRegistrar, User user) {
        // Iterate through the list of all lawyers
        for (Registrar registrar : AllRegistrar) {
            // Check if the current lawyer's ID matches the provided UserID
            System.out.println(registrar.getUserID());
            if (registrar.getUserID() == user.getUserID()) {
                return registrar; // Return the matched lawyer
            }
        }

        return null;
    }

    public CourtAdministrator getRelevantCourtAdministrators(List<CourtAdministrator> AllCourt_Administrators,
            User user) {
        // Iterate through the list of all lawyers
        for (CourtAdministrator CourtAdmin : AllCourt_Administrators) {
            // Check if the current lawyer's ID matches the provided UserID
            System.out.println(CourtAdmin.getUserID());
            if (CourtAdmin.getUserID() == user.getUserID()) {
                return CourtAdmin; // Return the matched lawyer
            }
        }

        return null;
    }

    /**
     * Searches for a lawyer in the provided list based on the given UserID.
     *
     * @param AllLawyers The list of all lawyers to search through.
     * @param UserID     The unique identifier of the lawyer to search for.
     * @return The Lawyer object with the matching LawyerID, or null if no match is
     *         found.
     */
    public Lawyer getRelevantLawyer(List<Lawyer> AllLawyers, User user) {
        // Iterate through the list of all lawyers
        for (Lawyer lawyer : AllLawyers) {
            // Check if the current lawyer's ID matches the provided UserID
            if (lawyer.getUserID() == user.getUserID()) {
                return lawyer; // Return the matched lawyer
            }
        }
        // Return null if no matching lawyer is found
        return null;
    }

    public Witness getRelevantWitness(List<Witness> AllWitnesses, User user) {
        // Iterate through the list of all lawyers
        for (Witness witness : AllWitnesses) {
            // Check if the current lawyer's ID matches the provided UserID
            if (witness.getUserID() == user.getUserID()) {
                return witness; // Return the matched lawyer
            }
        }
        // Return null if no matching lawyer is found
        return null;
    }

    public ProbationOfficer getRelevantProbationOfficer(List<ProbationOfficer> AllProbationOfficers, User user) {
        // Iterate through the list of all lawyers
        for (ProbationOfficer p : AllProbationOfficers) {
            // Check if the current PO's ID matches the provided UserID
            if (p.getUserID() == user.getUserID()) {
                return p; // Return the matched lawyer
            }
        }
        // Return null if no matching PO is found
        return null;
    }

    public Judge getRelevantJudge(List<Judge> AllJudges, User user) {
        // Iterate through the list of all lawyers
        for (Judge j : AllJudges) {
            // Check if the current Judge's ID matches the provided UserID
            if (j.getUserID() == user.getUserID()) {
                return j; // Return the matched lawyer
            }
        }
        // Return null if no matching judge is found
        return null;
    }

    public Juror getRelevantJuror(List<Juror> AllJurors, User user) {
        // Iterate through the list of all lawyers
        for (Juror j : AllJurors) {
            // Check if the current Judge's ID matches the provided UserID
            if (j.getUserID() == user.getUserID()) {
                return j; // Return the matched juror
            }
        }
        // Return null if no matching juror is found
        return null;
    }

    public void print(Object val) {
        System.out.println(val);
    }

    public Object GetInput(Scanner scanner) {
        String input = scanner.nextLine().trim(); // Read and trim the input

        // Try to parse the input as an Integer
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e1) {
            // Not an Integer
        }

        // Try to parse the input as a Double
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e2) {
            // Not a Double
        }

        // Try to parse the input as a Boolean
        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(input); // Successfully parsed as Boolean
        }

        // Try to parse the input as a Date
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false); // Strict date parsing
            return dateFormat.parse(input); // Successfully parsed as Date
        } catch (ParseException e3) {
            // Not a Date
        }

        // If all parsing fails, return the input as a String
        return input;
    }

    public void viewMyNotifications(List<Notification> AllNotifications, List<Case> AllCases,
            DatabaseHandler dbHandler) {
        List<Notification> mynotifications = new ArrayList<>();
        for (Notification n : AllNotifications) {
            if (n.getRecipientsID() == this.userID) {
                mynotifications.add(n);
            }
        }
        print("All Notifications are here!");
        for (Notification n : mynotifications) {
            n.display(dbHandler, AllCases);
        }

    }

    private static final String BASE_STYLE = "-fx-background-color: rgba(255, 255, 255, 0.1); "
            + "-fx-text-fill: white; "
            + "-fx-font-size: 18px; "
            + "-fx-font-family: 'Segoe UI', sans-serif; "
            + "-fx-padding: 10px 15px; "
            + "-fx-border-radius: 10px; "
            + "-fx-border-color: rgba(255, 255, 255, 0.2); "
            + "-fx-border-width: 1px; "
            + "-fx-background-radius: 10px; "
            + "-fx-effect: dropshadow(gaussian, #000000, 6, 0, 0, 8); "
            + "-fx-transition: all 0.3s ease-in-out;";

    private static final String FOCUSED_STYLE = "-fx-background-color: rgba(255, 255, 255, 0.2); "
            + "-fx-text-fill: white; "
            + "-fx-font-size: 18px; "
            + "-fx-font-family: 'Segoe UI', sans-serif; "
            + "-fx-padding: 10px 15px; "
            + "-fx-border-radius: 20px; "
            + "-fx-border-color: rgba(255, 255, 255, 0.7); "
            + "-fx-border-width: 1px; "
            + "-fx-background-radius: 20px; "
            + "-fx-effect: dropshadow(gaussian, #000000, 6, 0, 0, 10); "
            + "-fx-transition: all 0.3s ease-in-out;";

    private static final String COMBOBOX_BASE_STYLE = "-fx-background-color: rgba(255, 255, 255, 0.5); "
            + "-fx-text-fill: white; "
            + "-fx-font-size: 18px; "
            + "-fx-font-family: 'Segoe UI', sans-serif; "
            + "-fx-padding: 5px 15px; "
            + "-fx-border-radius: 10px; "
            + "-fx-border-color: rgba(255, 255, 255, 0.2); "
            + "-fx-border-width: 1px; "
            + "-fx-background-radius: 10px; "
            + "-fx-effect: dropshadow(gaussian, #000000, 6, 0, 0, 8); "
            + "-fx-transition: all 0.3s ease-in-out;"
            + "-fx-prompt-text-fill: white;";

    private static final String COMBOBOX_FOCUSED_STYLE = "-fx-background-color: rgba(255, 255, 255, 0.7); "
            + "-fx-text-fill: white; "
            + "-fx-font-size: 18px; "
            + "-fx-font-family: 'Segoe UI', sans-serif; "
            + "-fx-padding: 5px 15px; "
            + "-fx-border-radius: 20px; "
            + "-fx-border-color: rgba(255, 255, 255, 0.7); "
            + "-fx-border-width: 1px; "
            + "-fx-background-radius: 20px; "
            + "-fx-effect: dropshadow(gaussian, #000000, 6, 0, 0, 10); "
            + "-fx-transition: all 0.3s ease-in-out;"
            + "-fx-prompt-text-fill: white;";

    public void designComboBox(ComboBox<String> comboBox) {
        comboBox.setStyle(COMBOBOX_BASE_STYLE);

        // On focus gained (when the user clicks into the ComboBox)
        comboBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                comboBox.setStyle(COMBOBOX_FOCUSED_STYLE);
            } else {
                comboBox.setStyle(COMBOBOX_BASE_STYLE);
            }
        });

        // Optional: To add focus style when a dropdown item is selected
        comboBox.getEditor().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                comboBox.setStyle(COMBOBOX_FOCUSED_STYLE);
            } else {
                comboBox.setStyle(COMBOBOX_BASE_STYLE);
            }
        });
    }

    public void designTextField(TextField textField) {
        textField.setStyle(BASE_STYLE);

        // On focus gained (when the user clicks into the field)
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                textField.setStyle(FOCUSED_STYLE);
            } else {
                textField.setStyle(BASE_STYLE);
            }
        });
    }

    public void designPasswordField(PasswordField passwordField) {
        passwordField.setStyle(BASE_STYLE);

        // On focus gained (when the user clicks into the field)
        passwordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                passwordField.setStyle(FOCUSED_STYLE);
            } else {
                passwordField.setStyle(BASE_STYLE);
            }
        });
    }

    public void designButton(Button button) {
        // Initial button style (Glassmorphism)
        button.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.7); " // Semi-transparent white background for glass
                                                                   // effect
                        + "-fx-text-fill: black; " // White text color
                        + "-fx-font-size: 20px; " // Font size
                        + "-fx-font-weight: bold; " // Bold text
                        + "-fx-padding: 6px 30px; " // Padding for spacing
                        + "-fx-border-radius: 15px; " // Rounded corners for smooth look
                        + "-fx-border-color: rgba(255, 255, 255, 0.6); " // Light border with slight transparency
                        + "-fx-border-width: 1px; " // Thin border for subtle effect
                        + "-fx-background-radius: 15px; " // Rounded corners for background
                        + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 10); " // Shadow effect for depth
                        + "-fx-transition: all 1.5s ease; "
                        + "-fx-min-width: 200px; " // Smooth transition for hover effect

        );

        // Hover effect with animation
        button.setOnMouseEntered(e -> {
            button.setStyle(
                    "-fx-background-color: rgba(255, 255, 255, 1.0); " // Lighter transparency on hover
                            + "-fx-text-fill: black; " // Text color changes to blue
                            + "-fx-font-size: 20px; "
                            + "-fx-font-weight: bold; "
                            + "-fx-padding: 6px 30px; "
                            + "-fx-border-radius: 20px; "
                            + "-fx-border-color: rgba(255, 255, 255, 0.8); " // Lighter border on hover
                            + "-fx-border-width: 1px; "
                            + "-fx-background-radius: 20px; "
                            + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 15); " // Enhanced shadow on
                            + "-fx-min-width: 200px; " // hover
                            + "-fx-transition: all 1.5s ease; "

            );
        });

        // Reset style when mouse exits
        button.setOnMouseExited(e -> {
            button.setStyle(
                    "-fx-background-color: rgba(255, 255, 255,0.7); " // Reset to original glass effect
                            + "-fx-text-fill: black; "
                            + "-fx-font-size: 20px; "
                            + "-fx-font-weight: bold; "
                            + "-fx-padding: 6px 30px; "
                            + "-fx-border-radius: 15px; "
                            + "-fx-border-color: rgba(255, 255, 255, 0.6); "
                            + "-fx-border-width: 1px; "
                            + "-fx-background-radius: 15px; "
                            + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 10); "
                            + "-fx-transition: all 1.5s ease; "
                            + "-fx-min-width: 200px; "

            );
        });
    }

    public void designButton_smaller(Button button) {
        // Initial button style (Glassmorphism)
        button.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.7); " // Semi-transparent white background for glass
                                                                   // effect
                        + "-fx-text-fill: black; " // White text color
                        + "-fx-font-size: 16px; " // Font size
                        + "-fx-font-weight: bold; " // Bold text
                        + "-fx-padding: 3px 30px; " // Padding for spacing
                        + "-fx-border-radius: 15px; " // Rounded corners for smooth look
                        + "-fx-border-color: rgba(255, 255, 255, 0.6); " // Light border with slight transparency
                        + "-fx-border-width: 1px; " // Thin border for subtle effect
                        + "-fx-background-radius: 15px; " // Rounded corners for background
                        + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 10); " // Shadow effect for depth
                        + "-fx-transition: all 1.5s ease; "
                        + "-fx-min-width: 300px; " // Smooth transition for hover effect

        );

        // Hover effect with animation
        button.setOnMouseEntered(e -> {
            button.setStyle(
                    "-fx-background-color: rgba(255, 255, 255, 1.0); " // Lighter transparency on hover
                            + "-fx-text-fill: black; " // Text color changes to blue
                            + "-fx-font-size: 16px; "
                            + "-fx-font-weight: bold; "
                            + "-fx-padding: 3px 30px; "
                            + "-fx-border-radius: 20px; "
                            + "-fx-border-color: rgba(255, 255, 255, 0.8); " // Lighter border on hover
                            + "-fx-border-width: 1px; "
                            + "-fx-background-radius: 20px; "
                            + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 15); " // Enhanced shadow on
                            + "-fx-min-width: 300px; " // hover
                            + "-fx-transition: all 1.5s ease; "

            );
        });

        // Reset style when mouse exits
        button.setOnMouseExited(e -> {
            button.setStyle(
                    "-fx-background-color: rgba(255, 255, 255,0.7); " // Reset to original glass effect
                            + "-fx-text-fill: black; "
                            + "-fx-font-size: 16px; "
                            + "-fx-font-weight: bold; "
                            + "-fx-padding: 3px 30px; "
                            + "-fx-border-radius: 15px; "
                            + "-fx-border-color: rgba(255, 255, 255, 0.6); "
                            + "-fx-border-width: 1px; "
                            + "-fx-background-radius: 15px; "
                            + "-fx-effect: dropshadow(gaussian, #000000, 10, 0, 0, 10); "
                            + "-fx-transition: all 1.5s ease; "
                            + "-fx-min-width: 300px; "

            );
        });
    }

    public void styleLogoutButton(Button button) {
        button.setText("Logout"); // Set button text
        button.setStyle(
                "-fx-background-color: rgba(255, 69, 58, 0.7); " // Semi-transparent red background
                        + "-fx-text-fill: white; " // White text color
                        + "-fx-font-size: 16px; " // Font size
                        + "-fx-font-weight: bold; " // Bold text
                        + "-fx-padding: 8px 20px; " // Padding for spacing
                        + "-fx-border-radius: 10px; " // Rounded corners for smooth look
                        + "-fx-border-color: rgba(255, 69, 58, 0.9); " // Subtle border in red
                        + "-fx-border-width: 1px; " // Thin border for a minimal effect
                        + "-fx-background-radius: 10px; " // Rounded corners for background
                        + "-fx-cursor: hand; " // Change cursor to pointer
                        + "-fx-transition: all 0.3s ease-in-out;" // Smooth animation for hover
        );

        // Set hover effect
        button.setOnMouseEntered(event -> button.setStyle(
                "-fx-background-color: rgba(255, 69, 58, 1); " // Solid red background on hover
                        + "-fx-text-fill: white; " // White text on hover
                        + "-fx-font-size: 16px; "
                        + "-fx-font-weight: bold; "
                        + "-fx-padding: 8px 20px; "
                        + "-fx-border-radius: 10px; "
                        + "-fx-border-color: rgba(255, 255, 255, 0.7); " // White border on hover
                        + "-fx-border-width: 1px; "
                        + "-fx-background-radius: 10px; "
                        + "-fx-cursor: hand; "
                        + "-fx-transition: all 0.3s ease-in-out;"));

        // Revert to original style on mouse exit
        button.setOnMouseExited(event -> button.setStyle(
                "-fx-background-color: rgba(255, 69, 58, 0.7); " // Semi-transparent red
                        + "-fx-text-fill: white; "
                        + "-fx-font-size: 16px; "
                        + "-fx-font-weight: bold; "
                        + "-fx-padding: 8px 20px; "
                        + "-fx-border-radius: 10px; "
                        + "-fx-border-color: rgba(255, 69, 58, 0.9); "
                        + "-fx-border-width: 1px; "
                        + "-fx-background-radius: 10px; "
                        + "-fx-cursor: hand; "
                        + "-fx-transition: all 0.3s ease-in-out;"));
    }

}
