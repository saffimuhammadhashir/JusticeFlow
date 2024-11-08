package JusticeFlow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseHandler {
    String url = "jdbc:mysql://localhost:3306/sda_project";
    String dbUsername = "root";
    String dbPassword = "test12345";

    // This Login method authenticates a user and returns their role if successful,
    // otherwise returns "None".
    public String Login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        scanner.close();
        String query = "SELECT Role FROM Users WHERE username = ? AND PASSWORD = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("Role");
                System.out.println("Login successful! Role: " + role);
                return role;
            } else {
                System.out.println("Invalid username or password.");
                return "None";
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        return "None";
    }

    private boolean createUserWithRole(String username, String password, String role, String email, String phoneNumber,
            String firstName, String lastName, String dateOfBirth, String gender,
            String extraField1, String extraField2, Integer courtID, Integer barAssociationID) {

        String insertUserQuery = "INSERT INTO Users (Username, Password, Role, Email, PhoneNumber) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            conn.setAutoCommit(false);

            PreparedStatement userStmt = conn.prepareStatement(insertUserQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, username);
            userStmt.setString(2, password);
            userStmt.setString(3, role);
            userStmt.setString(4, email);
            userStmt.setString(5, phoneNumber);

            int rowsInserted = userStmt.executeUpdate();
            if (rowsInserted == 0) {
                conn.rollback();
                System.out.println("User creation failed.");
                return false;
            }

            int userID;
            try (ResultSet generatedKeys = userStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userID = generatedKeys.getInt(1);
                } else {
                    conn.rollback();
                    System.out.println("Failed to obtain user ID.");
                    return false;
                }
            }

            String roleSpecificInsert;
            PreparedStatement roleStmt;

            switch (role) {
                case "Judge":
                    roleSpecificInsert = "INSERT INTO Judges (FirstName, LastName, DateOfBirth, Gender, CourtID, Email, PhoneNumber, UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setInt(5, courtID);
                    roleStmt.setString(6, email);
                    roleStmt.setString(7, phoneNumber);
                    roleStmt.setInt(8, userID);
                    break;

                case "Lawyer":
                    roleSpecificInsert = "INSERT INTO Lawyers (FirstName, LastName, LicenseNumber, DateOfBirth, Gender, BarAssociationID, Email, PhoneNumber, UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, extraField1); // LicenseNumber
                    roleStmt.setString(4, dateOfBirth);
                    roleStmt.setString(5, gender);
                    roleStmt.setInt(6, barAssociationID);
                    roleStmt.setString(7, email);
                    roleStmt.setString(8, phoneNumber);
                    roleStmt.setInt(9, userID);
                    break;

                // Add similar cases for other roles, such as "Clerk", "Plaintiff", "Defendant",
                // etc.
                // You will need to adapt the columns to the specific tables and fields as per
                // each role's requirements.

                default:
                    System.out.println("Unsupported role.");
                    conn.rollback();
                    return false;
            }

            int roleRowsInserted = roleStmt.executeUpdate();
            if (roleRowsInserted == 0) {
                conn.rollback();
                System.out.println("Role-specific insert failed.");
                return false;
            }

            conn.commit();
            System.out.println("User and role-specific data created successfully.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void Register() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Scanner scanner = new Scanner(System.in);

        // Create a user with role
        System.out.println("Creating a new user...");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter role (e.g., Judge, Lawyer, Clerk): ");
        String role = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Enter gender (Male, Female, Other): ");
        String gender = scanner.nextLine();

        // Additional fields based on role
        String extraField1 = null;
        Integer courtID = null;
        Integer barAssociationID = null;

        if (role.equalsIgnoreCase("Judge") || role.equalsIgnoreCase("Clerk")) {
            System.out.print("Enter Court ID: ");
            courtID = Integer.parseInt(scanner.nextLine());
        } else if (role.equalsIgnoreCase("Lawyer") || role.equalsIgnoreCase("Prosecutor")) {
            System.out.print("Enter License Number: ");
            extraField1 = scanner.nextLine();

            System.out.print("Enter Bar Association ID: ");
            barAssociationID = Integer.parseInt(scanner.nextLine());
        }

        // Call createUserWithRole to add user to database
        boolean isUserCreated = dbHandler.createUserWithRole(username, password, role, email, phoneNumber,
                firstName, lastName, dateOfBirth, gender,
                extraField1, null, courtID, barAssociationID);

        if (isUserCreated) {
            System.out.println("User created successfully.");
        } else {
            System.out.println("User creation failed.");
        }
        scanner.close();
    }
}
