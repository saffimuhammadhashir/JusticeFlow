package JusticeFlow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.xdevapi.Client;

public class DatabaseHandler {
    private String url = "jdbc:mysql://localhost:3306/sda_project?useSSL=false";
    private final String dbUsername = "root";
    private final String dbPassword = "test12345";

    public void getAllFiles(List<Case> AllCases) {
        // SQL query to retrieve all case files
        String selectSQL = "SELECT CaseID, FileName, FileHash, status FROM CaseFiles";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectSQL)) {

            // Iterate through each result from the database
            while (resultSet.next()) {
                int caseID = resultSet.getInt("CaseID");
                String fileName = resultSet.getString("FileName");
                String fileHash = resultSet.getString("FileHash");
                int status = resultSet.getInt("status");

                // boolean status;
                // if(status_num==0){
                // status=false;
                // }
                // else{
                // status=true;
                // }
                // Find the corresponding case in the AllCases list by CaseID
                Case caseToUpdate = findCaseByID(AllCases, caseID);

                if (caseToUpdate != null) {

                    CaseFile Filedata = new CaseFile(fileName, fileHash, status);
                    if (status < 2) {
                        caseToUpdate.addFile(Filedata);
                        System.out.println("Case " + caseID + " updated with file: " + fileName);
                    } else if (status >= 2) {
                        caseToUpdate.addJudgement(Filedata);
                        System.out.println("Case " + caseID + " updated with judgement: " + fileName);
                    }
                } else {
                    System.out.println("No case found with CaseID: " + caseID);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Case findCaseByID(List<Case> allCases, int caseID) {
        // Iterate through all cases to find the one with the matching CaseID
        for (Case caseObj : allCases) {
            if (caseObj.getCaseID() == caseID) {
                return caseObj;
            }
        }
        return null; // Return null if no matching CaseID is found
    }

    public User getUserById(int userID) {
        // SQL query to retrieve user data
        String query = "SELECT * FROM Users WHERE UserID = " + userID;

        // Use try-with-resources to ensure proper closing of resources
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            // Execute the query and process the result set
            if (resultSet.next()) {
                // Extract user data from the result set
                int id = resultSet.getInt("UserID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                String role = resultSet.getString("Role");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                boolean isActive = resultSet.getBoolean("Activate");

                // Return a new User object with the extracted data
                return new User(id, username, password, role, email, phoneNumber, isActive);
            } else {
                System.out.println("User not found with ID: " + userID); // Log message for debugging
            }
        } catch (SQLException e) {
            // Log error message for debugging
            System.err.println("Error retrieving user data: " + e.getMessage());
            e.printStackTrace();
        }

        // Return null if user is not found
        return null;
    }

    /**
     * This method authenticates a user based on the username and password
     * provided. If authentication succeeds, it returns the user's role;
     * otherwise, it returns "None".
     * 
     * @return String representing the user's role or "None" if authentication
     *         fails.
     */
    public Integer Login(Scanner scanner) {

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // SQL query to retrieve user information (including userID, role, and
        // activation status)
        String query = "SELECT userID, Role, Activate FROM Users WHERE username = ? AND Password = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("userID"); // Retrieve userID
                String role = rs.getString("Role");
                boolean status = rs.getBoolean("Activate");
                if (!status) {
                    role = "Request Pending"; // If the account is not activated
                }
                System.out.println("Login successful! User ID: " + userID + " | Role: " + role);
                return userID; // Return the userID
            } else {
                System.out.println("Invalid username or password.");
                return null; // Return null if login fails
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        return null; // Return null if there was an error or no matching user
    }

    public void getAllSlots(List<Slot> allSlots) {
        String query = "SELECT * FROM Slots";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int slotID = resultSet.getInt("SlotID");
                String dayName = resultSet.getString("DayName");
                Time startTime = resultSet.getTime("startTime");
                Time endTime = resultSet.getTime("endTime");
                int slotNumber = resultSet.getInt("SlotNumber");
                Integer caseID = resultSet.getObject("CaseID") != null ? resultSet.getInt("CaseID") : null;
                Integer judgeID = resultSet.getObject("JudgeID") != null ? resultSet.getInt("JudgeID") : null;
                Integer witnessID = resultSet.getObject("WitnessID") != null ? resultSet.getInt("WitnessID") : null;
                Integer courtid = resultSet.getObject("CourtId") != null ? resultSet.getInt("CourtId") : null;

                Slot slot = new Slot(slotID, dayName, startTime, endTime, slotNumber, caseID, judgeID, witnessID,
                        courtid);
                allSlots.add(slot);
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
                int lawyerid = resultSet.getInt("Lawyerid");
                Case newCase = new Case(caseID, caseTitle, caseType, filingDate, courtDate, plaintiffID,
                        defendantID, caseStatus, lawyerid);
                AllCases.add(newCase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllLawyers(List<Lawyer> AllLawyers) {
        String query = "SELECT * FROM lawyers";
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
                int userID = resultSet.getInt("UserID");
                System.out.println(userID);
                // Fetch User details
                User inherit = getUserById(userID);
                if (inherit != null) {
                    Lawyer lawyer = new Lawyer(
                            inherit.getUserID(), inherit.getUsername(), inherit.getPassword(), "Lawyer",
                            inherit.getEmail(), inherit.getPhoneNumber(), inherit.isActivate(),
                            lawyerID, firstName, lastName, licenseNumber, dateOfBirth, gender, barAssociationID);
                    AllLawyers.add(lawyer);
                } else {
                    System.err.println("User with ID " + userID + " not found for Lawyer ID " + lawyerID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllJudges(List<Judge> AllJudges) {
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
                Date appointmentDate = resultSet.getDate("AppointmentDate");
                int courtID = resultSet.getInt("CourtID");
                int userID = resultSet.getInt("UserID");

                // Fetch User details
                User inherit = getUserById(userID);
                if (inherit != null) {
                    Judge judge = new Judge(
                            inherit.getUserID(),
                            inherit.getUsername(),
                            inherit.getPassword(),
                            "Judge",
                            inherit.getEmail(),
                            inherit.getPhoneNumber(),
                            inherit.isActivate(),
                            judgeID,
                            firstName,
                            lastName,
                            dateOfBirth,
                            gender,
                            appointmentDate,
                            courtID);

                    AllJudges.add(judge);
                } else {
                    System.err.println("User with ID " + userID + " not found for Judge ID " + judgeID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllCourtAdministrators(List<CourtAdministrator> AllCourt_Administrators) {
        String query = "SELECT * FROM CourtAdministrators";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int adminID = resultSet.getInt("AdminID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                Date dateOfBirth = resultSet.getDate("DateOfBirth");
                String gender = resultSet.getString("Gender");
                Date hireDate = resultSet.getDate("HireDate");
                int courtID = resultSet.getInt("CourtID");
                int userID = resultSet.getInt("UserID");

                // Fetch User details using userID
                User inherit = getUserById(userID);
                if (inherit == null) {
                    System.err.println("Skipping CourtAdministrator with missing User data for userID: " + userID);
                    continue;
                }

                // Create a CourtAdministrator object
                CourtAdministrator admin = new CourtAdministrator(
                        inherit.getUserID(),
                        inherit.getUsername(),
                        inherit.getPassword(),
                        inherit.getRole(),
                        inherit.getEmail(),
                        inherit.getPhoneNumber(),
                        inherit.isActivate(),
                        adminID,
                        firstName,
                        lastName,
                        dateOfBirth,
                        gender,
                        hireDate,
                        courtID);

                // Add to the list
                AllCourt_Administrators.add(admin);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching CourtAdministrators: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void getAllJurors(List<Juror> AllJurors) {
        String query = "SELECT * FROM Jurors";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int jurorID = resultSet.getInt("JurorID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                Date dateOfBirth = resultSet.getDate("DateOfBirth");
                String gender = resultSet.getString("Gender");
                String address = resultSet.getString("Address");
                int userID = resultSet.getInt("UserID");

                // Fetch User details using userID
                User inherit = getUserById(userID);

                if (inherit != null) {
                    // Create Juror object using User and Juror-specific fields
                    Juror juror = new Juror(
                            inherit.getUserID(),
                            inherit.getUsername(),
                            inherit.getPassword(),
                            inherit.getRole(),
                            inherit.getEmail(),
                            inherit.getPhoneNumber(),
                            inherit.isActivate(),
                            jurorID,
                            firstName,
                            lastName,
                            dateOfBirth,
                            gender,
                            address);

                    // Add to the list
                    AllJurors.add(juror);
                } else {
                    System.out.println("User with ID " + userID + " not found!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllRegistrar(List<Registrar> AllRegistrar) {
        String query = "SELECT * FROM Registrar";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int bailiffID = resultSet.getInt("RegistrarID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                Date dateOfBirth = resultSet.getDate("DateOfBirth");
                String gender = resultSet.getString("Gender");
                Date hireDate = resultSet.getDate("HireDate");
                int courtID = resultSet.getInt("CourtID");
                int userID = resultSet.getInt("UserID");

                // Fetch User details using userID
                User inherit = getUserById(userID);

                if (inherit != null) {
                    // Create Bailiff object using User and Bailiff-specific fields
                    Registrar registrar = new Registrar(
                            inherit.getUserID(),
                            inherit.getUsername(),
                            inherit.getPassword(),
                            inherit.getRole(),
                            inherit.getEmail(),
                            inherit.getPhoneNumber(),
                            inherit.isActivate(),
                            bailiffID,
                            firstName,
                            lastName,
                            dateOfBirth,
                            gender,
                            hireDate,
                            courtID);

                    // Add to the list
                    AllRegistrar.add(registrar);
                } else {
                    System.out.println("User with ID " + userID + " not found!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllClients(List<Clients> AllClients) {
        String query = "SELECT * FROM Clients";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int clientID = resultSet.getInt("clientID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                Date dateOfBirth = resultSet.getDate("DateOfBirth");
                String gender = resultSet.getString("Gender");
                String address = resultSet.getString("Address");
                int userID = resultSet.getInt("UserID");

                // Fetch User details using userID
                User inherit = getUserById(userID);

                if (inherit != null) {
                    // Create Defendant object using User and Defendant-specific fields
                    Clients client = new Clients(
                            inherit.getUserID(),
                            inherit.getUsername(),
                            inherit.getPassword(),
                            inherit.getRole(),
                            inherit.getEmail(),
                            inherit.getPhoneNumber(),
                            inherit.isActivate(),
                            clientID,
                            firstName,
                            lastName,
                            dateOfBirth,
                            gender,
                            address);

                    // Add to the list
                    AllClients.add(client);
                } else {
                    System.out.println("User with ID " + userID + " not found!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllCourts(List<Court> allCourts) {
        String query = "SELECT * FROM Courts"; // Assuming your table name is 'Courts'

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            // Loop through result set and create Court objects
            while (rs.next()) {
                int courtID = rs.getInt("courtID");
                String courtName = rs.getString("courtName");
                String courtType = rs.getString("courtType");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");

                // Create Court object and add it to the list
                Court court = new Court(courtID, courtName, courtType, address, phoneNumber, email);
                allCourts.add(court);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllProbationOfficers(List<ProbationOfficer> AllProbationOfficers) {
        String query = "SELECT * FROM ProbationOfficers";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int officerID = resultSet.getInt("OfficerID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                Date dateOfBirth = resultSet.getDate("DateOfBirth");
                String gender = resultSet.getString("Gender");
                Date hireDate = resultSet.getDate("HireDate");
                int courtID = resultSet.getInt("CourtID");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                int userID = resultSet.getInt("UserID");

                // Fetch User details using userID
                User inherit = getUserById(userID);

                if (inherit != null) {
                    // Create ProbationOfficer object using User and ProbationOfficer-specific
                    // fields
                    ProbationOfficer officer = new ProbationOfficer(
                            inherit.getUserID(),
                            inherit.getUsername(),
                            inherit.getPassword(),
                            inherit.getRole(),
                            inherit.getEmail(),
                            inherit.getPhoneNumber(),
                            inherit.isActivate(),
                            officerID,
                            firstName,
                            lastName,
                            dateOfBirth,
                            gender,
                            hireDate,
                            courtID);

                    // Add to the list
                    AllProbationOfficers.add(officer);
                } else {
                    System.out.println("User with ID " + userID + " not found!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllWitnesses(List<Witness> AllWitnesses) {
        String query = "SELECT * FROM Witnesses";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int witnessID = resultSet.getInt("WitnessID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                Date dateOfBirth = resultSet.getDate("DateOfBirth");
                String gender = resultSet.getString("Gender");
                String address = resultSet.getString("Address");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                int userID = resultSet.getInt("UserID");

                // Fetch User details using userID
                User inherit = getUserById(userID);

                if (inherit != null) {
                    // Create Witness object using User and Witness-specific fields
                    Witness witness = new Witness(
                            inherit.getUserID(),
                            inherit.getUsername(),
                            inherit.getPassword(),
                            inherit.getRole(),
                            inherit.getEmail(),
                            inherit.getPhoneNumber(),
                            inherit.isActivate(),
                            witnessID,
                            firstName,
                            lastName,
                            dateOfBirth,
                            gender,
                            address);

                    // Add to the list
                    AllWitnesses.add(witness);
                } else {
                    System.out.println("User with ID " + userID + " not found!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllBarAssociations(List<BarAssociation> AllBarAssociations) {

        String query = "SELECT * FROM BarAssociations"; // Modify table name if necessary

        // Establish connection, execute query, and process the result
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            // Loop through the result set and create BarAssociation objects
            while (resultSet.next()) {
                BarAssociation barAssociation = new BarAssociation();
                barAssociation.setBarAssociationID(resultSet.getInt("BarAssociationID"));
                barAssociation.setAssociationName(resultSet.getString("AssociationName"));
                barAssociation.setAddress(resultSet.getString("Address"));
                barAssociation.setPhoneNumber(resultSet.getString("PhoneNumber"));
                barAssociation.setEmail(resultSet.getString("Email"));

                // Add the BarAssociation object to the list
                AllBarAssociations.add(barAssociation);
            }
        } catch (SQLException e) {
            System.out.println("Error reading bar associations from database: " + e.getMessage());
        }

    }

    public void getAllNotifications(List<Notification> AllNotifications) {

        String query = "SELECT * FROM Notifications"; // Modify table name if necessary

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            // Loop through the result set and create Notification objects
            while (resultSet.next()) {
                int notificationID = resultSet.getInt("NotificationID");
                int caseID = resultSet.getInt("CaseID");
                int senderID = resultSet.getInt("SenderID");
                String senderType = resultSet.getString("SenderType");

                // For recipients, assuming they are stored as comma-separated values
                String recipientsIDString = resultSet.getString("RecipientsID");
                String recipientsTypeString = resultSet.getString("RecipientsType");

                // Split recipientsID and recipientsType into lists
                List<Integer> recipientsID = parseRecipientsID_notification(recipientsIDString);
                List<String> recipientsType = parseRecipientsType_notification(recipientsTypeString);

                Notification notification = new Notification(notificationID, caseID, recipientsID, recipientsType,
                        senderID, senderType);

                AllNotifications.add(notification);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving notifications: " + e.getMessage());
        }
    }

    private List<Integer> parseRecipientsID_notification(String recipientsIDString) {
        List<Integer> recipientsID = new ArrayList<>();
        if (recipientsIDString != null && !recipientsIDString.isEmpty()) {
            // Remove any unwanted characters like brackets or extra quotes
            recipientsIDString = recipientsIDString.replaceAll("[\\[\\]\"\\s]", "");

            // Split the string by commas
            String[] ids = recipientsIDString.split(",");

            for (String id : ids) {
                try {
                    // Parse each ID and add to the list
                    recipientsID.add(Integer.parseInt(id.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid recipient ID format: " + id);
                }
            }
        }
        return recipientsID;
    }

    private List<String> parseRecipientsType_notification(String recipientsTypeString) {
        List<String> recipientsType = new ArrayList<>();
        if (recipientsTypeString != null && !recipientsTypeString.isEmpty()) {
            String[] types = recipientsTypeString.split(",");
            for (String type : types) {
                recipientsType.add(type.trim()); // Trim spaces and add to the list
            }
        }
        return recipientsType;
    }

    public void getAllUserApplications(List<UserApplication> AllUserApplications) {

        String query = "SELECT * FROM UserApplication"; // Modify table name if necessary

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            // Loop through the result set and create UserApplication objects
            while (resultSet.next()) {
                int applicationID = resultSet.getInt("ApplicationID");
                int userID = resultSet.getInt("UserID");
                int applicationStatus = resultSet.getInt("ApplicationStatus");
                Date submissionDate = resultSet.getDate("SubmissionDate");

                UserApplication userApplication = new UserApplication(applicationID, userID, applicationStatus,
                        submissionDate);
                AllUserApplications.add(userApplication);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user applications: " + e.getMessage());
        }

    }

    // Helper method to parse recipientsID (stored as comma-separated string)
    private List<Integer> parseRecipientsID(String recipientsIDString) {
        List<Integer> recipientsID = new ArrayList<>();
        if (recipientsIDString != null && !recipientsIDString.isEmpty()) {
            String[] ids = recipientsIDString.split(",");
            for (String id : ids) {
                recipientsID.add(Integer.parseInt(id.trim()));
            }
        }
        return recipientsID;
    }

    private List<String> parseRecipientsType(String recipientsTypeString) {
        List<String> recipientsType = new ArrayList<>();
        if (recipientsTypeString != null && !recipientsTypeString.isEmpty()) {
            String[] types = recipientsTypeString.split(",");
            for (String type : types) {
                recipientsType.add(type.trim());
            }
        }
        return recipientsType;
    }
   
    public void updateOrInsertSlots(List<Slot> allSlots) {
        // SQL queries for update and insert
        String updateQuery = "UPDATE slots SET dayName = ?, startTime = ?, endTime = ?, slotNumber = ?, caseID = ?, judgeID = ?, witnessID = ?, CourtId = ? WHERE slotID = ?";
        String insertQuery = "INSERT INTO slots (slotID, dayName, startTime, endTime, slotNumber, caseID, judgeID, witnessID, CourtId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
    
            // Iterate through all slots to either update or insert
            for (Slot slot : allSlots) {
                // Check if the slot already exists (based on slotID)
                if (doesSlotExist(slot.getSlotID(), connection)) {
                    // If slot exists, update it
                    updateStmt.setString(1, slot.getDayName());
                    updateStmt.setTime(2, slot.getStartTime());
                    updateStmt.setTime(3, slot.getEndTime());
                    updateStmt.setInt(4, slot.getSlotNumber());
                    updateStmt.setObject(5, slot.getCaseID());
    
                    // If the caseID is null, set the other values to null
                    if (slot.getCaseID() == null) {
                        updateStmt.setObject(6, null); // Set JudgeID as null
                        updateStmt.setObject(7, null); // Set WitnessID as null
                        updateStmt.setObject(8, null); // Set CourtID as null
                    } else {
                        updateStmt.setObject(6, slot.getJudgeID());
                        updateStmt.setObject(7, slot.getWitnessID() != null ? slot.getWitnessID() : null);
                        updateStmt.setObject(8, slot.getCourtId());
                    }
    
                    updateStmt.setInt(9, slot.getSlotID()); // Set slotID for update
                    updateStmt.executeUpdate(); // Execute the update statement
                } else {
                    // If slot does not exist, insert it
                    insertStmt.setInt(1, slot.getSlotID());
                    insertStmt.setString(2, slot.getDayName());
                    insertStmt.setTime(3, slot.getStartTime());
                    insertStmt.setTime(4, slot.getEndTime());
                    insertStmt.setInt(5, slot.getSlotNumber());
                    insertStmt.setObject(6, slot.getCaseID());
    
                    // If the caseID is null, set the other values to null
                    if (slot.getCaseID() == null) {
                        insertStmt.setObject(7, null); // Set JudgeID as null
                        insertStmt.setObject(8, null); // Set WitnessID as null
                        insertStmt.setObject(9, null); // Set CourtID as null
                    } else {
                        insertStmt.setObject(7, slot.getJudgeID());
                        insertStmt.setObject(8, slot.getWitnessID() != null ? slot.getWitnessID() : null);
                        insertStmt.setObject(9, slot.getCourtId());
                    }
    
                    insertStmt.executeUpdate(); // Execute the insert statement
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private boolean doesSlotExist(int slotID, Connection connection) {
        String checkQuery = "SELECT 1 FROM slots WHERE slotID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(checkQuery)) {
            stmt.setInt(1, slotID);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // If a result is found, the slot exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getWitnessData(List<Witness> allWitnesses) {
        String selectQuery = "SELECT CaseId, WitnessID FROM WitnessTable";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery)) {

            // Process each row from the WitnessTable
            while (resultSet.next()) {
                int caseId = resultSet.getInt("CaseId");
                int witnessId = resultSet.getInt("WitnessID");

                // Find the corresponding Witness object from the allWitnesses list
                Witness witness = findWitnessById(allWitnesses, witnessId);
                if (witness != null) {

                    witness.CaseID.add(caseId);
                }
            }

            System.out.println("Data transferred successfully and CaseIDs added to corresponding Witnesses.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error handling database operations: " + e.getMessage());
        }
    }

    public Witness findWitnessById(List<Witness> allWitnesses, int witnessId) {
        for (Witness witness : allWitnesses) {
            if (witness.getWitnessID() == witnessId) {
                return witness;
            }
        }
        return null;
    }

    // public void getAllClients() {
    // String query = "SELECT * FROM Clients";
    // try (Connection connection = DriverManager.getConnection(url, dbUsername,
    // dbPassword);
    // Statement statement = connection.createStatement();
    // ResultSet resultSet = statement.executeQuery(query)) {

    // while (resultSet.next()) {
    // int clientID = resultSet.getInt("ClientID");
    // String firstName = resultSet.getString("FirstName");
    // String lastName = resultSet.getString("LastName");
    // String email = resultSet.getString("Email");
    // String phoneNumber = resultSet.getString("PhoneNumber");
    // int userID = resultSet.getInt("UserID");

    // // Create Client object and add it to AllClients list
    // Client client = new Client(clientID, firstName, lastName, email, phoneNumber,
    // userID);
    // AllClients.add(client);
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    // Saves or updates a case in the database based on its existence. First, it
    // checks if a case with the
    // specified CaseID exists in the database. If it does, it updates the existing
    // record with new data;
    // otherwise, it inserts a new record. If a new case is created, the generated
    // CaseID is set on the
    // case object to maintain the ID for any further references or associations.

    public void saveOrUpdateCase(Case caseObj) {
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            // Check if the case exists
            String checkSQL = "SELECT COUNT(*) FROM Cases WHERE CaseID = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSQL)) {
                checkStatement.setInt(1, caseObj.getCaseID());
                ResultSet resultSet = checkStatement.executeQuery();

                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    // Case exists, update it
                    String updateSQL = "UPDATE Cases SET CaseTitle = ?, CaseType = ?, CaseStatus = ?, " +
                            "FilingDate = ?, CourtDate = ?, PlaintiffID = ?, DefendantID = ?,Lawyerid = ? WHERE CaseID = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSQL)) {
                        updateStatement.setString(1, caseObj.getCaseTitle());
                        updateStatement.setString(2, caseObj.getCaseType());
                        updateStatement.setString(3, caseObj.getCaseStatus());
                        updateStatement.setDate(4, new java.sql.Date(caseObj.getFilingDate().getTime()));
                        updateStatement.setDate(5, new java.sql.Date(caseObj.getCourtDate().getTime()));
                        updateStatement.setInt(8, caseObj.getLawyerId());

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

                        updateStatement.setInt(9, caseObj.getCaseID());

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
    public void saveFileDetails(int caseID, String fileName, String fileHash, boolean status) {
        // SQL query to insert file details into CaseFiles
        String insertSQL = "INSERT INTO CaseFiles (CaseID, FileName, FileHash, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {

            // Check if connection is valid (optional step to verify the connection)
            if (connection != null) {
                System.out.println("Connection successful!");
            }

            // Set the values for the prepared statement
            insertStatement.setInt(1, caseID);
            insertStatement.setString(2, fileName);
            insertStatement.setString(3, fileHash);
            if (status) {
                insertStatement.setInt(4, 1);
            } else {
                insertStatement.setInt(4, 0);
            }

            // Execute the update to insert the data
            int rowsAffected = insertStatement.executeUpdate();

            // Check if the insert was successful
            if (rowsAffected > 0) {
                System.out.println("File details saved successfully!");
            } else {
                System.out.println("Failed to save file details.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFileDetails(int caseID, String fileName, String fileHash, boolean oldStatus, boolean newStatus) {
        // SQL query to update the status in CaseFiles
        String updateSQL = "UPDATE CaseFiles SET status = ? WHERE CaseID = ? AND FileName = ? AND FileHash = ? AND status = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                PreparedStatement updateStatement = connection.prepareStatement(updateSQL)) {

            // Check if the connection is valid (optional)
            if (connection != null) {
                System.out.println("Connection successful!");
            }

            // Set the values for the prepared statement
            updateStatement.setInt(1, newStatus ? 1 : 0); // New status
            updateStatement.setInt(2, caseID); // CaseID
            updateStatement.setString(3, fileName); // FileName
            updateStatement.setString(4, fileHash); // FileHash
            updateStatement.setInt(5, oldStatus ? 1 : 0); // Old status

            // Execute the update query
            int rowsAffected = updateStatement.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("File status updated successfully!");
            } else {
                System.out.println("No matching record found to update.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addJudgement(int caseID, String fileName, String fileHash, int status) {
        // SQL query to insert file details into CaseFiles
        String insertSQL = "INSERT INTO CaseFiles (CaseID, FileName, FileHash, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {

            // Check if connection is valid (optional step to verify the connection)
            if (connection != null) {
                System.out.println("Connection successful!");
            }

            // Set the values for the prepared statement
            insertStatement.setInt(1, caseID);
            insertStatement.setString(2, fileName);
            insertStatement.setString(3, fileHash);
            insertStatement.setInt(4, status);

            // Execute the update to insert the data
            int rowsAffected = insertStatement.executeUpdate();

            // Check if the insert was successful
            if (rowsAffected > 0) {
                System.out.println("Judgement details saved successfully!");
            } else {
                System.out.println("Failed to save file details.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateJudgementDetails(int caseID, String fileName, String fileHash, int oldStatus, int newStatus) {
        // SQL query to update the status in CaseFiles
        String updateSQL = "UPDATE CaseFiles SET status = ? WHERE CaseID = ? AND FileName = ? AND FileHash = ? AND status = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                PreparedStatement updateStatement = connection.prepareStatement(updateSQL)) {

            // Check if the connection is valid (optional)
            if (connection != null) {
                System.out.println("Connection successful!");
            }

            // Set the values for the prepared statement
            updateStatement.setInt(1, newStatus); // New status
            updateStatement.setInt(2, caseID); // CaseID
            updateStatement.setString(3, fileName); // FileName
            updateStatement.setString(4, fileHash); // FileHash
            updateStatement.setInt(5, oldStatus); // Old status

            // Execute the update query
            int rowsAffected = updateStatement.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Judgement status updated successfully!");
            } else {
                System.out.println("No matching record found to update.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWitness(Witness w, Slot s){
        String updateSQL = "UPDATE Slot SET WitnessID = ? WHERE CaseID = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                PreparedStatement updateStatement = connection.prepareStatement(updateSQL)) {

            // Check if the connection is valid (optional)
            if (connection != null) {
                System.out.println("Connection successful!");
            }

            // Set the values for the prepared statement
            updateStatement.setInt(1, w.getWitnessID()); // WitnessID
            updateStatement.setInt(2, s.getCaseID()); // CaseID

            // Execute the update query
            int rowsAffected = updateStatement.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Case's Witness updated successfully!");
            } else {
                System.out.println("No matching record found to update.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

                case "Client":
                    roleSpecificInsert = "INSERT INTO Client (FirstName, LastName, DateOfBirth, Gender,Address,PhoneNumber, Email,  UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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

                case "Registrar":
                    roleSpecificInsert = "INSERT INTO Registrar (FirstName, LastName, DateOfBirth, Gender,HireDate,CourtID,Email,PhoneNumber,   UserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
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

        if (role.equalsIgnoreCase("Judge")
                || role.equalsIgnoreCase("Court Administrator")
                || role.equalsIgnoreCase("Registrar")
                || role.equalsIgnoreCase("Probation Officer")) {
            System.out.print("Enter Court ID: ");
            courtID = Integer.parseInt(scanner.nextLine());
        } else if (role.equalsIgnoreCase("Lawyer")) {
            System.out.print("Enter License Number: ");
            license = scanner.nextLine();
            System.out.print("Enter Bar Association ID: ");
            barAssociationID = Integer.parseInt(scanner.nextLine());

        }

        if (role.equalsIgnoreCase("Judge")) {

            System.out.print("Enter date of Appointment (YYYY-MM-DD): ");
            dateOfAppointment = scanner.nextLine();

        }

        if (role.equalsIgnoreCase("Registrar") || role.equalsIgnoreCase("Court Administrator")) {

            System.out.print("Enter date of hiring (YYYY-MM-DD): ");
            dateOfhiring = scanner.nextLine();

        }
        if (role.equalsIgnoreCase("client")
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
