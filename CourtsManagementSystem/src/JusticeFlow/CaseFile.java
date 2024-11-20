package JusticeFlow;

public class CaseFile { // Renamed from File to CaseFile
    private String fileName;
    private String fileHash;
    private int status; // 0 for pending, 1 for approved, 2 for judgement(unapproved), 3 for
                        // judgement(approved)

    // Constructor initializes the file with its name and hash value.
    public CaseFile(String fileName, String fileHash) {
        this.fileName = fileName;
        this.fileHash = fileHash;
        this.status = 0;
    }

    public CaseFile(String fileName, String fileHash, int status) {
        this.fileName = fileName;
        this.fileHash = fileHash;
        this.status = status;
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

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Converts the file details into a readable string format.
    @Override
    public String toString() {
        String status_String;
        if (status == 1) {
            status_String = "Document (Approved)";
        } else if (status == 0) {
            status_String = "Document (Waiting)";
        } else if (status == 2) {
            status_String = "Judgement (Waiting)";
        } else {
            status_String = "Judgement (Approved)";
        }
        return "File Name: " + fileName + ", Hash: " + fileHash + ", Status: " + status_String;
    }
}
