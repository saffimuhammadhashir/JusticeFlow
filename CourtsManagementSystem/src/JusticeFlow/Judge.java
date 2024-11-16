package JusticeFlow;

import java.util.Date;

public class Judge extends User {
    private int judgeID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Date appointmentDate;
    private int courtID;
    private String email;
    private String phoneNumber;
    private int userID;

    public Judge(int userID, String username, String password, String role, String email, String phoneNumber,
            boolean activate,
            int judgeID, String firstName, String lastName, Date dateOfBirth, String gender,
            Date appointmentDate, int courtID) {
        super(userID, username, password, role, email, phoneNumber, activate); // Calling the constructor of User class

        this.judgeID = judgeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.appointmentDate = appointmentDate;
        this.courtID = courtID;
    }

    public int getJudgeID() {
        return judgeID;
    }

    public void setJudgeID(int judgeID) {
        this.judgeID = judgeID;
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

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
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
        return "Judge {" +
                "judgeID=" + judgeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", courtID=" + courtID +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userID=" + userID +
                '}';
    }

}
