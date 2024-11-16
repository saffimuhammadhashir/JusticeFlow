package JusticeFlow;

import java.util.Date;

public class Lawyer extends User {
    private int lawyerID;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private Date dateOfBirth;
    private String gender;
    private int barAssociationID;
    private String email;
    private String phoneNumber;
    private int userID;

    public Lawyer(
        int userID,
        String username,
        String password,
        String role,
        String email,
        String phoneNumber,
        boolean activate,
        int lawyerID,
        String firstName,
        String lastName,
        String licenseNumber,
        Date dateOfBirth,
        String gender,
        int barAssociationID) {
    // Call the superclass constructor
    super(userID, username, password, role, email, phoneNumber, activate);

    // Initialize Lawyer-specific fields
    this.lawyerID = lawyerID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.licenseNumber = licenseNumber;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.barAssociationID = barAssociationID;
}


    public int getLawyerID() {
        return lawyerID;
    }

    public void setLawyerID(int lawyerID) {
        this.lawyerID = lawyerID;
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

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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

    public int getBarAssociationID() {
        return barAssociationID;
    }

    public void setBarAssociationID(int barAssociationID) {
        this.barAssociationID = barAssociationID;
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
        return "Lawyer {" +
                "lawyerID=" + lawyerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", BarID=" + barAssociationID +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userID=" + userID +
                '}';
    }
}
