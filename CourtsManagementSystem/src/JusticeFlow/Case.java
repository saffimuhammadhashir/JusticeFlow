package JusticeFlow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Case {

    private int caseID;
    private String caseTitle;
    private String caseType;
    private String caseStatus;
    private Date filingDate;
    private Date courtDate;
    private int plaintiffID;
    private int defendantID;
    private List<CaseFile> files;  // Changed to CaseFile from File

    // Constructor initializes all attributes of a case, including details like case title,
    // type, status, filing and court dates, IDs of plaintiff and defendant, and associated files.
    public Case(int caseID, String caseTitle, String caseType, String caseStatus,
                Date filingDate, Date courtDate, int plaintiffID, int defendantID) {
        this(caseID, caseTitle, caseType, caseStatus, filingDate, courtDate, plaintiffID, defendantID, new ArrayList<>());
    }

    public Case(int caseID, String caseTitle, String caseType, String caseStatus,
                Date filingDate, Date courtDate, int plaintiffID, int defendantID, List<CaseFile> files) {
        this.caseID = caseID;
        this.caseTitle = caseTitle;
        this.caseType = caseType;
        this.caseStatus = caseStatus;
        this.filingDate = filingDate;
        this.courtDate = courtDate;
        this.plaintiffID = plaintiffID;
        this.defendantID = defendantID;
        this.files = files;
    }

    public Case(){

    }

    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public Date getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    public Date getCourtDate() {
        return courtDate;
    }

    public void setCourtDate(Date courtDate) {
        this.courtDate = courtDate;
    }

    public int getPlaintiffID() {
        return plaintiffID;
    }

    public void setPlaintiffID(int plaintiffID) {
        this.plaintiffID = plaintiffID;
    }

    public int getDefendantID() {
        return defendantID;
    }

    public void setDefendantID(int defendantID) {
        this.defendantID = defendantID;
    }

    public List<CaseFile> getFiles() {
        return files;
    }

    public void setFiles(List<CaseFile> files) {
        this.files = files;
    }

    // Adds a new file to the case's list of files, representing a document or item associated with the case.
    public void addFile(CaseFile file) {
        this.files.add(file);
    }

    // Updates case files by first saving the files through the FileHandler,
    // then saving or updating the case record through DatabaseHandler.
    public void updateCaseFiles(FileHandler filehandler, DatabaseHandler dbHandler) {
        // filehandler.saveFileForCase(this);
        dbHandler.saveOrUpdateCase(this);
    }

    // Converts the case details, including associated file details, into a readable string format.
    @Override
    public String toString() {
        StringBuilder fileDetails = new StringBuilder();
        for (CaseFile file : files) {
            fileDetails.append("\n").append(file.toString());
        }

        return "Case ID: " + caseID + "\nTitle: " + caseTitle + "\nType: " + caseType +
                "\nStatus: " + caseStatus + "\nFiled On: " + filingDate + "\nCourt Date: " + courtDate +
                "\nPlaintiff ID: " + plaintiffID + "\nDefendant ID: " + defendantID +
                "\nAssociated Files: " + fileDetails.toString();
    }

    /**
     * Checks if a case with the given caseID exists in the AllCases list.
     *
     * @param caseID The unique identifier of the case to search for.
     * @return true if a case with the specified caseID exists; false otherwise.
     */
    public boolean doesCaseExist(int caseID,List<Case> AllCases) {
        // Iterate through the list of all cases
        for (Case c : AllCases) {
            // Check if the current case's ID matches the provided caseID
            if (c.getCaseID() == caseID) {
                return true; // Case found
            }
        }
        // Case not found after iterating through the list
        return false;
    }

    public Case getCasebyID(int caseID,List<Case> AllCases) {
        // Iterate through the list of all cases
        for (Case c : AllCases) {
            // Check if the current case's ID matches the provided caseID
            if (c.getCaseID() == caseID) {
                return c; // Case found
            }
        }
        // Case not found after iterating through the list
        return null;
    }
}
