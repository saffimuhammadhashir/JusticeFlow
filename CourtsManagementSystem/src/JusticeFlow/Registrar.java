package JusticeFlow;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Registrar extends User {
    private int RegistrarID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Date hireDate;
    private int courtID;
    private String email;
    private String phoneNumber;
    private int userID;

    public Registrar() {
        super();
    }

    public Registrar(int userID, String username, String password, String role, String email, String phoneNumber,
            boolean activate, int RegistrarID, String firstName, String lastName, Date dateOfBirth,
            String gender, Date hireDate, int courtID) {
        super(userID, username, password, role, email, phoneNumber, activate); // Call to User class constructor

        this.RegistrarID = RegistrarID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.hireDate = hireDate;
        this.courtID = courtID;
        this.userID = userID;
    }

    public int getRegistrarID() {
        return RegistrarID;
    }

    public void setRegistrarID(int RegistrarID) {
        this.RegistrarID = RegistrarID;
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
        return "Registrar {" +
                "judgeID=" + RegistrarID +
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

    public void ApproveDocument(Scanner scanner, List<Case> AllCases, FileHandler fileHandler) {
        // Print
        boolean exists = false;
        System.out.println("\nCases:");
        for (Case caseObj : AllCases) {
            if (caseObj.getFiles() != null) {
                for (CaseFile fileObj : caseObj.getFiles()) {
                    if (!fileObj.getStatus()) {
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    break;
                }
            }
        }

        if (exists) {
            System.out.println("\nCases:");
            for (Case caseObj : AllCases) {
                if (caseObj.getFiles() != null) {
                    for (CaseFile fileObj : caseObj.getFiles()) {
                        if (!fileObj.getStatus()) {
                            System.out.println(fileObj.toString());
                        }
                    }
                }
            }
        } else {
            System.out.println("No Document To Approve.");
        }
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

}