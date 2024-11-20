package JusticeFlow;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Witness extends User {
    private int witnessID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private int userID;
    public List<Integer> CaseID=new ArrayList<>(); 

    public Witness(int userID, String username, String password, String role, String email, String phoneNumber,
            boolean activate,
            int witnessID, String firstName, String lastName, Date dateOfBirth, String gender, String address) {
        super(userID, username, password, role, email, phoneNumber, activate); // Calling the constructor of User class

        this.witnessID = witnessID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userID = userID;
        
    }

    public int getWitnessID() {
        return witnessID;
    }

    public void setWitnessID(int witnessID) {
        this.witnessID = witnessID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
