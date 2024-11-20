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
    private int plaintiffID = 0;
    private int defendantID = 0;
    private List<CaseFile> files;
    private List<CaseFile> judgements;
    private int Lawyerid=0;
    // Changed to CaseFile from File
    // New attribute for CaseState (e.g., Pending, Filed)

    // Constructor initializes all attributes of a case, including details like case
    // title,
    // type, status, filing and court dates, IDs of plaintiff and defendant,
    // associated files, and case state.
    public Case(int caseID, String caseTitle, String caseType,
            Date filingDate, Date courtDate, int plaintiffID, int defendantID, String caseState) {

        this(caseID, caseTitle, caseType, caseState, filingDate, courtDate, plaintiffID, defendantID,
                new ArrayList<>());
        this.judgements = new ArrayList<>();
    }

    public Case(int caseID, String caseTitle, String caseType,
            Date filingDate, Date courtDate, int plaintiffID, int defendantID, String caseState, int Lawyerid) {

        this(caseID, caseTitle, caseType, caseState, filingDate, courtDate, plaintiffID, defendantID,
                new ArrayList<>());
        this.Lawyerid = Lawyerid;
        this.judgements = new ArrayList<>();
    }

    // Constructor with files and caseState
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
        this.judgements = new ArrayList<>();
    }

    public Case() {

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

    public List<CaseFile> getJudgements() {
        return judgements;
    }

    public void setJudgements(List<CaseFile> judgements) {
        this.judgements = judgements;
    }

    public void addJudgement(CaseFile file) {
        this.judgements.add(file);
    }

    // Adds a new file to the case's list of files, representing a document or item
    // associated with the case.
    public void addFile(CaseFile file) {
        this.files.add(file);
    }

    // Updates case files by first saving the files through the FileHandler,
    // then saving or updating the case record through DatabaseHandler.
    public void updateCaseFiles(FileHandler filehandler, DatabaseHandler dbHandler) {
        filehandler.saveFileForCase(this);
        dbHandler.saveOrUpdateCase(this);
    }

    // Converts the case details, including associated file details, into a readable
    // string format.
    @Override
    public String toString() {
        StringBuilder fileDetails = new StringBuilder();
        for (CaseFile file : files) {
            fileDetails.append("\n").append(file.toString());
        }

        StringBuilder judgementDetails = new StringBuilder();
        if (judgements != null) {
            judgementDetails.append("\nAssociated Judgements: ");
            boolean exists = false;
            for (CaseFile judgement : judgements) {
                judgementDetails.append("\n").append(judgement.toString());
                exists = true;
            }

            if (exists == false) {
                judgementDetails.append("NONE\n");
            }
        } else {
            judgementDetails.append("");
        }

        return "Case ID: " + caseID + "\nTitle: " + caseTitle + "\nType: " + caseType +
                "\nStatus: " + caseStatus + "\nFiled On: " + filingDate + "\nCourt Date: " + courtDate +
                "\nPlaintiff ID: " + plaintiffID + "\nDefendant ID: " + defendantID + "\nLawyer Id" + Lawyerid +
                "\nAssociated Files: " + fileDetails.toString() + judgementDetails.toString()
                + "\n\n--------------------------------\n";
    }

    public int getLawyerId() {
        return this.Lawyerid;
    }

    /**
     * Checks if a case with the given caseID exists in the AllCases list.
     *
     * @param caseID The unique identifier of the case to search for.
     * @return true if a case with the specified caseID exists; false otherwise.
     */
    public boolean doesCaseExist(int caseID, List<Case> AllCases) {
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

    public Case getCasebyID(int caseID, List<Case> AllCases) {
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

    public List<Integer> getStakeholders(List<Clients> AllClients, List<Slot> AllSlots,List<Judge> AllJudges,List<Lawyer> AllLawyers) {
        List<Integer> output = new ArrayList<>();
        
        for (Clients c : AllClients) {

            if (plaintiffID != 0 && this.plaintiffID == c.getclientID()) {
                output.add(c.getUserID());
            }
            if (defendantID != 0 && this.defendantID == c.getclientID()) {
                output.add(c.getUserID());
            }
        }
        for (Lawyer l : AllLawyers) {

            if (Lawyerid != 0 && this.Lawyerid == l.getLawyerID()) {
                output.add(l.getUserID());
                break;
            }

        }
        for(Slot s: AllSlots){
            if(s.getJudgeID()!=null && s.getCaseID()==this.caseID){
                for(Judge j:AllJudges){
                    if(s.getJudgeID().equals(j.getJudgeID())){
                        output.add(j.getUserID());
                        return output;
                    }
                }
            }
        }


        return output;
    }
}
