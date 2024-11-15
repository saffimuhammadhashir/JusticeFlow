package JusticeFlow;

public class CaseFile {  // Renamed from File to CaseFile
    private String fileName;
    private String fileHash;

    // Constructor initializes the file with its name and hash value.
    public CaseFile(String fileName, String fileHash) {
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
