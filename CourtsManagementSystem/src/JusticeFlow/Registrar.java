package JusticeFlow;

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
}