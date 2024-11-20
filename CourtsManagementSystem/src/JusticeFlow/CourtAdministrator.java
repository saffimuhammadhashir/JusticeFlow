package JusticeFlow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
            List<Slot> AllSlots, List<Judge> AllJudges, List<Witness> AllWitnesses, Scanner scanner) {
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
                        if (caseid == cases.getCaseID()) {
                            for (Judge j : AllJudges) {
                                Slot.PossibleSchedule(AllSlots, j, w, possibleSlots);
                            }
                        }
                    }
                }
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

    public void scheduleHearingWitness(Scanner scanner, List<Case> AllCases, List<Slot> AllSlots, List<Judge> AllJudges,
            List<Witness> AllWitnesses, FileHandler fileHandler, DatabaseHandler databaseHandler) {

        System.out.println("1. Schedule Hearing");
        System.out.println("2. Schedule Witness");
        int choice = scanner.nextInt();

        if (choice == 1) {
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

            Slot s = new Slot();
            for (Slot slotObj : AllSlots) {
                if (slotObj.getSlotID() == sid) {
                    s = slotObj;
                }
            }

            Case c = new Case();
            for (Case caseObj : AllCases) {
                if (caseObj.getCaseID() == cid) {
                    c = caseObj;
                }
            }

            if (s.getJudgeID() == null) {
                s.setCaseID(cid);
                // s.setJudgeID(c.get)

                databaseHandler.updateOrInsertSlots(AllSlots);
            } else {
                System.out.println("Slot not free.");
            }
        } else if (choice == 2) {
            System.out.println("\nCases:");
            for (Case caseObj : AllCases) {
                System.out.println(caseObj.toString());
            }

            System.out.print("\nEnter CaseID: ");
            int cid = scanner.nextInt();

            Slot s = new Slot();
            boolean scheduled = false;
            for (Slot slotObj : AllSlots) {
                if (slotObj.getCaseID() == cid) {
                    s = slotObj;
                    scheduled = true;
                }
            }

            if (scheduled) {
                System.out.println("1. Add a new Witness.");
                System.out.println("2. Add from an existing Witness.");
                int choice2 = scanner.nextInt();

                Witness w;
                // if (choice2 == 1) {
                    System.out.print("Enter User ID: ");
                    int userID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over

                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();

                    System.out.print("Enter Role: ");
                    String role = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Is the account activated? (true/false): ");
                    boolean activate = scanner.nextBoolean();
                    scanner.nextLine(); // Consume newline left-over

                    // Taking user input for Witness-specific fields
                    System.out.print("Enter Witness ID: ");
                    int witnessID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over

                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();

                    Date dateOfBirth = null;

                    while (dateOfBirth == null) {
                        System.out.print("Enter Date of Birth (year-month-day): ");
                        String dobInput = scanner.nextLine();
                        try {
                            dateOfBirth = java.sql.Date.valueOf(dobInput); // Convert String to Date
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
                        }
                    }

                    System.out.print("Enter Gender: ");
                    String gender = scanner.nextLine();

                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();

                    // Creating Witness object
                    w = new Witness(
                            userID,
                            username,
                            password,
                            role,
                            email,
                            phoneNumber,
                            activate,
                            witnessID,
                            firstName,
                            lastName,
                            dateOfBirth,
                            gender,
                            address);

                    AllWitnesses.add(w);
                    System.out.println("New Witness created with witnessID " + w.getWitnessID());
                // } 
                if (choice2 == 2) {
                    System.out.println("\nExisting Witnesses:");
                    for (Witness caseObj : AllWitnesses) {
                        System.out.println(caseObj.toString());
                    }

                    System.out.print("\nEnter WitnessID: ");
                    int wid = scanner.nextInt();

                    for (Witness caseObj : AllWitnesses) {
                        if (caseObj.getWitnessID() == wid) {
                            w = caseObj;
                            System.out.println("Selected Witness with witnessID " + w.getWitnessID());
                            s.setWitnessID(w.getWitnessID());
                            w.addCaseID(cid);
                            databaseHandler.updateWitness(w,s);
                            break;
                        }
                    }
                }

            } else {
                System.out.println("Case not Sheduled.");
            }
        } else {
            System.out.println("You didn't enter a valid choice.");
        }

    }

}
