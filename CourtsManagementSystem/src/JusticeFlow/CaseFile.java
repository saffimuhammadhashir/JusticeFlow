package JusticeFlow;

public class CaseFile { // Renamed from File to CaseFile
    private String fileName;
    private String fileHash;
    private boolean status; // 0 for pending, 1 for approved

    // Constructor initializes the file with its name and hash value.
    public CaseFile(String fileName, String fileHash) {
        this.fileName = fileName;
        this.fileHash = fileHash;
        this.status = false;
    }

    public CaseFile(String fileName, String fileHash, boolean status) {
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

    public boolean getStatus(){
        return this.status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    // Converts the file details into a readable string format.
    @Override
    public String toString() {
        return "File Name: " + fileName + ", Hash: " + fileHash + ", Status: " + (status?"Approved":"Waiting");
    }
}
