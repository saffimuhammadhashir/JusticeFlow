package JusticeFlow;

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
    private List<File> files;

    // Constructor initializes all attributes of a case, including details like case title,
    // type, status, filing and court dates, IDs of plaintiff and defendant, and associated files.
    public Case(int caseID, String caseTitle, String caseType, String caseStatus,
            Date filingDate, Date courtDate, int plaintiffID, int defendantID, List<File> files) {
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

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    // Adds a new file to the case's list of files, representing a document or item associated with the case.
    public void addFile(File file) {
        this.files.add(file);
    }

    // Updates case files by first saving the files through the FileHandler,
    // then saving or updating the case record through DatabaseHandler.
    public void updateCaseFiles() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        FileHandler.saveFileForCase(this);
        dbHandler.saveOrUpdateCase(this);
    }

    // Converts the case details, including associated file details, into a readable string format.
    @Override
    public String toString() {
        String fileDetails = "";
        for (File file : files) {
            fileDetails += "\n" + file.toString();
        }

        return "Case ID: " + caseID + "\nTitle: " + caseTitle + "\nType: " + caseType +
                "\nStatus: " + caseStatus + "\nFiled On: " + filingDate + "\nCourt Date: " + courtDate +
                "\nPlaintiff ID: " + plaintiffID + "\nDefendant ID: " + defendantID +
                "\nAssociated Files: " + fileDetails;
    }

    // Inner class File represents individual files associated with a case, storing both file name and its hash.
    public static class File {
        private String fileName;
        private String fileHash;

        // Constructor initializes the file with its name and hash value.
        public File(String fileName, String fileHash) {
            this.fileName = fileName;
            this.fileHash = fileHash;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileHash() {
            return fileHash;
        }

        public void setFileHash(String fileHash) {
            this.fileHash = fileHash;
        }

        // Converts the file details into a readable string format.
        @Override
        public String toString() {
            return "File Name: " + fileName + ", Hash: " + fileHash;
        }
    }
}
