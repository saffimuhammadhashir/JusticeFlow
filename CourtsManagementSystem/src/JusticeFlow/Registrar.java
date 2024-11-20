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
        // System.out.println("\nCases:");
        for (Case caseObj : AllCases) {
            if (caseObj.getFiles() != null) {
                for (CaseFile fileObj : caseObj.getFiles()) {
                    if (fileObj.getStatus() == 0) {
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
                    System.out.println(caseObj.toString());
                }
            }

            System.out.print("\nEnter CaseID: ");
            int cid = scanner.nextInt();

            Case c = new Case();
            c = c.getCasebyID(cid, AllCases);
            List<CaseFile> files = c.getFiles();

            int count = 1;
            System.out.print("Files in this Case: ");
            for (CaseFile file : files) {
                StringBuilder fileDetails = new StringBuilder();
                fileDetails.append("").append(file.toString());
                System.out.println(count + ". " + fileDetails.toString());
                count++;
            }

            System.out.print("Enter Document Number to approve: ");
            int id = scanner.nextInt();

            count = 1;
            for (CaseFile file : files) {
                if (id == count) {
                    if (file.getStatus() == 0) {
                        file.setStatus(1);
                        DatabaseHandler d = new DatabaseHandler();
                        d.updateFileDetails(c.getCaseID(), file.getFileName(), file.getFileHash(), false, true);
                        System.out.println("File Status updated in Database.");
                        return;
                    }
                }
                count++;
            }

        } else {
            System.out.println("No Document To Approve.");
            return;
        }
    }

    public void ApproveJudgement(Scanner scanner, List<Case> AllCases, FileHandler fileHandler) {
        // Print
        boolean exists = false;
        // System.out.println("\nCases:");
        for (Case caseObj : AllCases) {
            if (caseObj.getJudgements() != null) {
                for (CaseFile fileObj : caseObj.getJudgements()) {
                    if (fileObj.getStatus() == 2) {
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
                if (caseObj.getJudgements() != null) {
                    System.out.println(caseObj.toString());
                }
            }

            System.out.print("\nEnter CaseID: ");
            int cid = scanner.nextInt();

            Case c = new Case();
            c = c.getCasebyID(cid, AllCases);
            List<CaseFile> judgements = c.getJudgements();

            int count = 1;
            System.out.println("Judgements in this Case: ");
            for (CaseFile judgement : judgements) {
                StringBuilder fileDetails = new StringBuilder();
                fileDetails.append("").append(judgement.toString());
                System.out.println(count + ". " + fileDetails.toString());
                count++;
            }

            System.out.print("Enter Document Number to approve: ");
            int id = scanner.nextInt();

            count = 1;
            for (CaseFile judgement : judgements) {
                if (id == count) {
                    if (judgement.getStatus() == 2) {
                        judgement.setStatus(3);
                        DatabaseHandler d = new DatabaseHandler();
                        d.updateJudgementDetails(c.getCaseID(), judgement.getFileName(), judgement.getFileHash(), 2, 3);
                        System.out.println("File Status updated in Database.");
                        return;
                    }
                }
                count++;
            }

        } else {
            System.out.println("No Judgement To Approve.");
            return;
        }
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
                if(possibleSlots.isEmpty()){
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

}