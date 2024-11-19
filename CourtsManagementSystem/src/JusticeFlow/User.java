package JusticeFlow;

import java.util.Date;
import java.util.List;

import java.util.List;

public class User {
    private int userID;
    private String username;
    private String password;
    private String role;
    private String email;
    private String phoneNumber;
    private boolean activate;

    public User() {

    }

    public User(int userID, String username, String password, String role, String email, String phoneNumber,
            boolean activate) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.activate = activate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    /*
     * Searches for a lawyer in the provided list based on the given UserID.
     *
     * @param AllLawyers The list of all lawyers to search through.
     * 
     * @param UserID The unique identifier of the lawyer to search for.
     * 
     * @return The Lawyer object with the matching LawyerID, or null if no match is
     * found.
     */


    public Registrar getRelevantRegistrar(List<Registrar> AllRegistrar, User user) {
        // Iterate through the list of all lawyers
        for (Registrar registrar : AllRegistrar) {
            // Check if the current lawyer's ID matches the provided UserID
            System.out.println(registrar.getUserID());
            if (registrar.getUserID() == user.getUserID()) {
                return registrar; // Return the matched lawyer
            }
        }

        return null;
    }

    public CourtAdministrator getRelevantCourtAdministrators(List<CourtAdministrator> AllCourt_Administrators,
            User user) {
        // Iterate through the list of all lawyers
        for (CourtAdministrator CourtAdmin : AllCourt_Administrators) {
            // Check if the current lawyer's ID matches the provided UserID
            System.out.println(CourtAdmin.getUserID());
            if (CourtAdmin.getUserID() == user.getUserID()) {
                return CourtAdmin; // Return the matched lawyer
            }
        }

        return null;
    }


    /**
     * Searches for a lawyer in the provided list based on the given UserID.
     *
     * @param AllLawyers The list of all lawyers to search through.
     * @param UserID     The unique identifier of the lawyer to search for.
     * @return The Lawyer object with the matching LawyerID, or null if no match is
     *         found.
     */
    public Lawyer getRelevantLawyer(List<Lawyer> AllLawyers, User user) {
        // Iterate through the list of all lawyers
        for (Lawyer lawyer : AllLawyers) {
            // Check if the current lawyer's ID matches the provided UserID
            if (lawyer.getUserID() == user.getUserID()) {
                return lawyer; // Return the matched lawyer
            }
        }
        // Return null if no matching lawyer is found
        return null;
    }


    public ProbationOfficer getRelevantProbationOfficer(List<ProbationOfficer> AllProbationOfficers, User user) {
        // Iterate through the list of all lawyers
        for (ProbationOfficer p : AllProbationOfficers) {
            // Check if the current PO's ID matches the provided UserID
            if (p.getUserID() == user.getUserID()) {
                return p; // Return the matched lawyer
            }
        }
        // Return null if no matching lawyer is found
        return null;
    }

    public Registrar getRelevantRegistrar(List<Registrar> AllRegistrar, User user) {
        // Iterate through the list of all lawyers
        for (Registrar r : AllRegistrar) {
            // Check if the current Registrar's ID matches the provided UserID
            if (r.getUserID() == user.getUserID()) {
                return r; // Return the matched lawyer
            }
        }
        // Return null if no matching lawyer is found
        return null;
    }

    
}
