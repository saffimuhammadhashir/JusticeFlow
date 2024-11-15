package JusticeFlow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

import com.mysql.cj.xdevapi.Client;

public class DatabaseHandler {
    private final String url = "jdbc:mysql://localhost:3306/sda_project";
    private final String dbUsername = "root";
    private final String dbPassword = "test12345";

    public void getAllUsers(List<ITAdmin> AllITAdmins,
            List<Judge> AllJudges,
            List<Juror> AllJurors,
            List<Lawyer> AllLawyers,
            List<CourtAdministrator> AllCourt_Administrators,
            List<Case> AllCases,
            List<Plaintiff> AllPlaintiff,
            List<Defendant> AllDefendant) {
        String query = "SELECT * FROM Users";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int userID = resultSet.getInt("UserID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                String role = resultSet.getString("Role");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                boolean isActive = resultSet.getBoolean("Activate");

                // Create User object and add it to the appropriate list
                User user = new User(userID, username, password, role, email, phoneNumber, isActive);

                switch (role) {
                    case "Judge":
                        AllJudges.add((Judge) user);
                        break;
                    case "Lawyer":
                        AllLawyers.add((Lawyer) user);
                        break;
                    case "ITAdmin":
                        AllITAdmins.add((ITAdmin) user);
                        break;
                    case "Juror":
                        AllJurors.add((Juror) user);
                        break;
                    case "CourtAdministrator":
                        AllCourt_Administrators.add((CourtAdministrator) user);
                        break;
                    case "Client":
                         AllDefendant.add((Defendant) user);
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllCases(List<Case> AllCases) {
        String query = "SELECT * FROM Cases";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int caseID = resultSet.getInt("CaseID");
                String caseTitle = resultSet.getString("CaseTitle");
                String caseType = resultSet.getString("CaseType");
                String caseStatus = resultSet.getString("CaseStatus");
                Date filingDate = resultSet.getDate("FilingDate");
                Date courtDate = resultSet.getDate("CourtDate");
                int plaintiffID = resultSet.getInt("PlaintiffID");
                int defendantID = resultSet.getInt("DefendantID");

                // Create Case object and add it to AllCases
                Case newCase = new Case(caseID, caseTitle, caseType, caseStatus, filingDate, courtDate, plaintiffID,
                        defendantID);
                AllCases.add(newCase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllLawyers() {
        String query = "SELECT * FROM Lawyers";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int lawyerID = resultSet.getInt("LawyerID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String licenseNumber = resultSet.getString("LicenseNumber");
                Date dateOfBirth = resultSet.getDate("DateOfBirth");
                String gender = resultSet.getString("Gender");
                int barAssociationID = resultSet.getInt("BarAssociationID");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                int userID = resultSet.getInt("UserID");

                // Create Lawyer object and add it to AllLawyers list
                Lawyer lawyer = new Lawyer(lawyerID, firstName, lastName, licenseNumber, dateOfBirth, gender,
                        barAssociationID, email, phoneNumber, userID);
                AllLawyers.add(lawyer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllJudges() {
        String query = "SELECT * FROM Judges";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int judgeID = resultSet.getInt("JudgeID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                Date dateOfBirth = resultSet.getDate("DateOfBirth");
                String gender = resultSet.getString("Gender");
                String courtAssigned = resultSet.getString("CourtAssigned");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                int userID = resultSet.getInt("UserID");

                // Create Judge object and add it to AllJudges list
                Judge judge = new Judge(judgeID, firstName, lastName, dateOfBirth, gender, courtAssigned, email,
                        phoneNumber, userID);
                AllJudges.add(judge);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllCourtAdministrators() {
        String query = "SELECT * FROM CourtAdministrators";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int adminID = resultSet.getInt("AdminID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String department = resultSet.getString("Department");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                int userID = resultSet.getInt("UserID");

                // Create CourtAdministrator object and add it to the list
                CourtAdministrator admin = new CourtAdministrator(adminID, firstName, lastName, department, email,
                        phoneNumber, userID);
                AllCourt_Administrators.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllJurors() {
        String query = "SELECT * FROM Jurors";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int jurorID = resultSet.getInt("JurorID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                int userID = resultSet.getInt("UserID");

                // Create Juror object and add it to AllJurors list
                Juror juror = new Juror(jurorID, firstName, lastName, email, phoneNumber, userID);
                AllJurors.add(juror);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllClients() {
        String query = "SELECT * FROM Clients";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int clientID = resultSet.getInt("ClientID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                int userID = resultSet.getInt("UserID");

                // Create Client object and add it to AllClients list
                Client client = new Client(clientID, firstName, lastName, email, phoneNumber, userID);
                AllClients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Saves or updates a case in the database based on its existence. First, it
    // checks if a case with the
    // specified CaseID exists in the database. If it does, it updates the existing
    // record with new data;
    // otherwise, it inserts a new record. If a new case is created, the generated
    // CaseID is set on the
    // case object to maintain the ID for any further references or associations.
    public void saveOrUpdateCase(Case caseObj) {
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String checkSQL = "SELECT COUNT(*) FROM Cases WHERE CaseID = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSQL)) {
                checkStatement.setInt(1, caseObj.getCaseID());
                ResultSet resultSet = checkStatement.executeQuery();

                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    // Case exists, update it
                    String updateSQL = "UPDATE Cases SET CaseTitle = ?, CaseType = ?, CaseStatus = ?, " +
                            "FilingDate = ?, CourtDate = ?, PlaintiffID = ?, DefendantID = ? WHERE CaseID = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSQL)) {
                        updateStatement.setString(1, caseObj.getCaseTitle());
                        updateStatement.setString(2, caseObj.getCaseType());
                        updateStatement.setString(3, caseObj.getCaseStatus());
                        updateStatement.setDate(4, new java.sql.Date(caseObj.getFilingDate().getTime()));
                        updateStatement.setDate(5, new java.sql.Date(caseObj.getCourtDate().getTime()));

                        // Check if PlaintiffID or DefendantID is provided, if not, set to NULL
                        if (caseObj.getPlaintiffID() == 0) {
                            updateStatement.setNull(6, java.sql.Types.INTEGER);
                        } else {
                            updateStatement.setInt(6, caseObj.getPlaintiffID());
                        }

                        if (caseObj.getDefendantID() == 0) {
                            updateStatement.setNull(7, java.sql.Types.INTEGER);
                        } else {
                            updateStatement.setInt(7, caseObj.getDefendantID());
                        }

                        updateStatement.setInt(8, caseObj.getCaseID());

                        int rowsUpdated = updateStatement.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("Case updated successfully.");
                        } else {
                            System.out.println("Failed to update case.");
                        }
                    }
                } else {
                    // Case does not exist, insert a new one
                    String insertSQL = "INSERT INTO Cases (CaseTitle, CaseType, CaseStatus, FilingDate, CourtDate, PlaintiffID, DefendantID) "
                            +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL,
                            Statement.RETURN_GENERATED_KEYS)) {
                        insertStatement.setString(1, caseObj.getCaseTitle());
                        insertStatement.setString(2, caseObj.getCaseType());
                        insertStatement.setString(3, caseObj.getCaseStatus());
                        insertStatement.setDate(4, new java.sql.Date(caseObj.getFilingDate().getTime()));
                        insertStatement.setDate(5, new java.sql.Date(caseObj.getCourtDate().getTime()));

                        // Check if PlaintiffID or DefendantID is provided, if not, set to NULL
                        if (caseObj.getPlaintiffID() == 0) {
                            insertStatement.setNull(6, java.sql.Types.INTEGER);
                        } else {
                            insertStatement.setInt(6, caseObj.getPlaintiffID());
                        }

                        if (caseObj.getDefendantID() == 0) {
                            insertStatement.setNull(7, java.sql.Types.INTEGER);
                        } else {
                            insertStatement.setInt(7, caseObj.getDefendantID());
                        }

                        int rowsInserted = insertStatement.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("New case saved successfully.");
                            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    int newCaseID = generatedKeys.getInt(1);
                                    caseObj.setCaseID(newCaseID);
                                }
                            }
                        } else {
                            System.out.println("Failed to save new case.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Inserts file details (filename and file hash) into the database, linking the
    // file to a case
    // through the specified CaseID. If the file information is saved successfully,
    // a confirmation message
    // is printed. This method ensures that file data is securely stored and
    // accessible by related cases.
    public void saveFileDetails(int caseID, String fileName, String fileHash) {
        String insertSQL = "INSERT INTO CaseFiles (CaseID, FileName, FileHash) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setInt(1, caseID);
            preparedStatement.setString(2, fileName);
            preparedStatement.setString(3, fileHash);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("File details saved successfully!");
            } else {
                System.out.println("Failed to save file details.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method authenticates a user based on the username and password
     * provided. If authentication succeeds, it returns the user's role;
     * otherwise, it returns "None".
     * 
     * @return String representing the user's role or "None" if authentication
     *         fails.
     */
    public String Login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        scanner.close();
        String query = "SELECT Role, Activate FROM Users WHERE username = ? AND Password = ?"; // Correct column names

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("Role");
                boolean status = rs.getBoolean("Activate");
                if (!status) {

                    role = "Request Pending";
                }
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

    /**
     * This method creates a user entry in the Users table and inserts additional
     * role-specific data into a designated table based on the user's role.
     * 
     * @param username         The username of the user
     * @param password         The password of the user
     * @param role             The role of the user (e.g., Judge, Lawyer)
     * @param email            The email address of the user
     * @param phoneNumber      The phone number of the user
     * @param firstName        The first name of the user
     * @param lastName         The last name of the user
     * @param dateOfBirth      The birth date of the user
     * @param gender           The gender of the user
     * @param extraField1      An additional field that varies by role (e.g.,
     *                         LicenseNumber for Lawyer)
     * @param extraField2      Placeholder for future fields if needed
     * @param courtID          The court ID, applicable to roles like Judge
     * @param barAssociationID The bar association ID, applicable to roles like
     *                         Lawyer
     * @return boolean Returns true if the user and role-specific entry were created
     *         successfully, otherwise false.
     */
    private int createUserWithRole(String username, String password, String role, String email, String phoneNumber,
            String firstName, String lastName, String dateOfBirth, String gender,
            String dateOfAppointment, String dateOfhiring, String license, String address,
            Integer courtID, Integer barAssociationID) {

        String insertUserQuery = "INSERT INTO Users (Username, Password, Role, Email, PhoneNumber) VALUES (?, ?, ?, ?, ?)";
        int userID;
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
                return -1;
            }

            try (ResultSet generatedKeys = userStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userID = generatedKeys.getInt(1);

                } else {
                    conn.rollback();
                    System.out.println("Failed to obtain user ID.");
                    return -1;
                }
            }

            String roleSpecificInsert;
            PreparedStatement roleStmt;

            // Check if CourtID exists for Judge
            if (role.equalsIgnoreCase("Judge") && courtID != null) {
                String courtCheckQuery = "SELECT CourtID FROM courts WHERE CourtID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(courtCheckQuery)) {
                    stmt.setInt(1, courtID);
                    ResultSet rs = stmt.executeQuery();
                    if (!rs.next()) {
                        conn.rollback();
                        System.out.println("Invalid CourtID.");
                        return -1;
                    }
                }
            }

            switch (role) {
                case "Judge":
                    roleSpecificInsert = "INSERT INTO Judges (FirstName, LastName, DateOfBirth, Gender,AppointmentDate, CourtID, Email, PhoneNumber, UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setString(5, dateOfAppointment);
                    roleStmt.setInt(6, courtID);
                    roleStmt.setString(7, email);
                    roleStmt.setString(8, phoneNumber);
                    roleStmt.setInt(9, userID);
                    break;
                case "Clerk":
                    roleSpecificInsert = "INSERT INTO Clerks (FirstName, LastName, DateOfBirth, Gender,HireDate, CourtID, Email, PhoneNumber, UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setString(5, dateOfhiring);
                    roleStmt.setInt(6, courtID);
                    roleStmt.setString(7, email);
                    roleStmt.setString(8, phoneNumber);
                    roleStmt.setInt(9, userID);

                    break;

                case "Lawyer":
                    roleSpecificInsert = "INSERT INTO Lawyers (FirstName, LastName, LicenseNumber, DateOfBirth, Gender, BarAssociationID, Email, PhoneNumber, UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, license);
                    roleStmt.setString(4, dateOfBirth);
                    roleStmt.setString(5, gender);
                    roleStmt.setInt(6, barAssociationID);
                    roleStmt.setString(7, email);
                    roleStmt.setString(8, phoneNumber);
                    roleStmt.setInt(9, userID);
                    break;

                case "Court Administrator":
                    roleSpecificInsert = "INSERT INTO CourtAdministrators (FirstName, LastName, DateOfBirth, Gender,HireDate, CourtID, Email, PhoneNumber, UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setString(5, dateOfhiring);
                    roleStmt.setInt(6, courtID);
                    roleStmt.setString(7, email);
                    roleStmt.setString(8, phoneNumber);
                    roleStmt.setInt(9, userID);

                    break;

                case "Plaintiff":
                    roleSpecificInsert = "INSERT INTO Plaintiffs (FirstName, LastName, DateOfBirth, Gender,Address,PhoneNumber, Email,  UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setString(5, address);
                    roleStmt.setString(6, phoneNumber);
                    roleStmt.setString(7, email);
                    roleStmt.setInt(8, userID);
                    break;

                case "Defendant":
                    roleSpecificInsert = "INSERT INTO Defendants (FirstName, LastName, DateOfBirth, Gender,Address,PhoneNumber, Email,  UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setString(5, address);
                    roleStmt.setString(6, phoneNumber);
                    roleStmt.setString(7, email);
                    roleStmt.setInt(8, userID);
                    break;

                case "Witness":
                    roleSpecificInsert = "INSERT INTO Witnesses (FirstName, LastName, DateOfBirth, Gender,Address,PhoneNumber, Email,  UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setString(5, address);
                    roleStmt.setString(6, phoneNumber);
                    roleStmt.setString(7, email);
                    roleStmt.setInt(8, userID);
                    break;

                case "Bailiff":
                    roleSpecificInsert = "INSERT INTO Bailiffs (FirstName, LastName, DateOfBirth, Gender,HireDate,CourtID,Email,PhoneNumber,   UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setString(5, dateOfhiring);
                    roleStmt.setInt(6, courtID);
                    roleStmt.setString(7, email);
                    roleStmt.setString(8, phoneNumber);
                    roleStmt.setInt(9, userID);
                    break;

                case "Juror":
                    roleSpecificInsert = "INSERT INTO Jurors (FirstName, LastName, DateOfBirth, Gender,Address,PhoneNumber, Email,  UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setString(5, address);
                    roleStmt.setString(6, phoneNumber);
                    roleStmt.setString(7, email);
                    roleStmt.setInt(8, userID);
                    break;

                case "Court Reporter":
                    roleSpecificInsert = "INSERT INTO CourtReporters (FirstName, LastName, DateOfBirth, Gender,HireDate,CourtID,Email,PhoneNumber,   UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setString(5, dateOfhiring);
                    roleStmt.setInt(6, courtID);
                    roleStmt.setString(7, email);
                    roleStmt.setString(8, phoneNumber);
                    roleStmt.setInt(9, userID);
                    break;

                case "Probation Officer":
                    roleSpecificInsert = "INSERT INTO ProbationOfficers (FirstName, LastName, DateOfBirth, Gender,HireDate,CourtID,Email,PhoneNumber,   UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setString(5, dateOfhiring);
                    roleStmt.setInt(6, courtID);
                    roleStmt.setString(7, email);
                    roleStmt.setString(8, phoneNumber);
                    roleStmt.setInt(9, userID);
                    break;

                case "IT Admin":
                    roleSpecificInsert = "INSERT INTO ITAdmins (FirstName, LastName, DateOfBirth, Gender,HireDate,Email,PhoneNumber,   UserID) VALUES ( ?, ?, ?, ?, ?, ?, ?,?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, dateOfBirth);
                    roleStmt.setString(4, gender);
                    roleStmt.setString(5, dateOfhiring);
                    roleStmt.setString(6, email);
                    roleStmt.setString(7, phoneNumber);
                    roleStmt.setInt(8, userID);
                    break;

                case "Prosecutor":
                    roleSpecificInsert = "INSERT INTO Prosecutors (FirstName, LastName,LicenseNumber, DateOfBirth, Gender,BarAssociationID,Email,PhoneNumber,   UserID) VALUES ( ?, ?, ?, ?, ?, ?, ?,?,?)";
                    roleStmt = conn.prepareStatement(roleSpecificInsert);
                    roleStmt.setString(1, firstName);
                    roleStmt.setString(2, lastName);
                    roleStmt.setString(3, license);
                    roleStmt.setString(4, dateOfBirth);
                    roleStmt.setString(5, gender);
                    roleStmt.setInt(6, barAssociationID);
                    roleStmt.setString(7, email);
                    roleStmt.setString(8, phoneNumber);
                    roleStmt.setInt(9, userID);
                    break;

                default:
                    System.out.println("Unsupported role.");
                    conn.rollback();
                    return -1;
            }

            int roleRowsInserted = roleStmt.executeUpdate();
            if (roleRowsInserted == 0) {
                conn.rollback();
                System.out.println("Role-specific insert failed.");
                return -1;
            }

            conn.commit();
            System.out.println("User and role-specific data created successfully.");
            return userID;

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * This method captures user input to register a new user by collecting
     * information like username, password, role, and other relevant details,
     * then calls createUserWithRole to add the user to the database.
     */
    public void Register() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Scanner scanner = new Scanner(System.in);
        String username, password, role, email, firstName, lastName, dateOfBirth, gender, phoneNumber;
        String dateOfAppointment = null, dateOfhiring = null, license = null, address = null;
        Integer courtID = null, barAssociationID = null;
        // Create a user with role
        System.out.println("Creating a new user...");
        System.out.print("Enter username: ");
        username = scanner.nextLine();

        System.out.print("Enter password: ");
        password = scanner.nextLine();

        System.out.print("Enter role (e.g., Judge, Lawyer, Clerk): ");
        role = scanner.nextLine();

        System.out.print("Enter email: ");
        email = scanner.nextLine();

        System.out.print("Enter first name: ");
        firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        lastName = scanner.nextLine();

        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        dateOfBirth = scanner.nextLine();

        System.out.print("Enter gender (Male, Female, Other): ");
        gender = scanner.nextLine();

        System.out.print("Enter phone number: ");
        phoneNumber = scanner.nextLine();

        if (role.equalsIgnoreCase("Judge") || role.equalsIgnoreCase("Clerk")
                || role.equalsIgnoreCase("Court Administrator")
                || role.equalsIgnoreCase("Baliff") || role.equalsIgnoreCase("Court Reporter")
                || role.equalsIgnoreCase("Probation Officer")) {
            System.out.print("Enter Court ID: ");
            courtID = Integer.parseInt(scanner.nextLine());
        } else if (role.equalsIgnoreCase("Lawyer") || role.equalsIgnoreCase("Prosecutor")) {
            System.out.print("Enter License Number: ");
            license = scanner.nextLine();
            System.out.print("Enter Bar Association ID: ");
            barAssociationID = Integer.parseInt(scanner.nextLine());

        }

        if (role.equalsIgnoreCase("Judge")) {

            System.out.print("Enter date of Appointment (YYYY-MM-DD): ");
            dateOfAppointment = scanner.nextLine();

        }

        if (role.equalsIgnoreCase("Clerk") || role.equalsIgnoreCase("Court Administrator")
                || role.equalsIgnoreCase("Baliff")
                || role.equalsIgnoreCase("Probation Officer")) {

            System.out.print("Enter date of hiring (YYYY-MM-DD): ");
            dateOfhiring = scanner.nextLine();

        }
        if (role.equalsIgnoreCase("Plaintiff") || role.equalsIgnoreCase("Defendant")
                || role.equalsIgnoreCase("Witness") || role.equalsIgnoreCase("Juror")) {
            System.out.print("Enter your address: ");
            address = scanner.nextLine();
        }

        // Call createUserWithRole to add user to database
        int isUserCreated = dbHandler.createUserWithRole(username, password, role, email, phoneNumber,
                firstName, lastName, dateOfBirth, gender,
                dateOfAppointment, dateOfhiring, license, address, courtID, barAssociationID);

        if (isUserCreated != -1) {
            System.out.println("User created successfully.");
            createApplication(isUserCreated);
        } else {
            System.out.println("User creation failed.");
        }

    }

    /**
     * Creates a new application entry for a given user in the Applications table.
     * 
     * This method inserts a new row into the Applications table with the specified
     * UserID
     * and sets the ApplicationStatus to the default value of 0 (indicating the
     * initial status).
     * It also records the current timestamp for the SubmissionDate.
     * 
     * @param userID The ID of the user for whom the application is being created.
     */
    public void createApplication(int userID) {
        String query = "INSERT INTO UserApplication (UserID, ApplicationStatus) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userID);
            stmt.setInt(2, 0);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Application created successfully for UserID: " + userID);
            } else {
                System.out.println("Failed to create application.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
