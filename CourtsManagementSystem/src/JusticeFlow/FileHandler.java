package JusticeFlow;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.file.*;
import java.awt.*;

public class FileHandler {

    private DatabaseHandler dbHandler;

    // Constructor where the DatabaseHandler is injected

    public FileHandler(DatabaseHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    // Saves a selected file for a given case, including hashing the file, copying it to a
    // dedicated storage directory, and adding file details to the case's file list.
    public void saveFileForCase(Case caseObj) {
        File selectedFile = openFileDialog();

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();

            try {
                String fileHash = getFileHash(filePath);
                File storageDirectory = new File("File Storage");
                if (!storageDirectory.exists()) {
                    storageDirectory.mkdirs();
                }

                String folderName = caseObj.getCaseID() + "_" + caseObj.getCaseTitle();
                File caseDirectory = new File(storageDirectory, folderName);
                if (!caseDirectory.exists()) {
                    caseDirectory.mkdirs();
                }

                File destinationFile = new File(caseDirectory, selectedFile.getName());
                copyFile(filePath, destinationFile.getAbsolutePath());

                CaseFile file = new CaseFile(selectedFile.getName(), fileHash);  // Changed to CaseFile
                dbHandler.saveFileDetails(caseObj.getCaseID(), selectedFile.getAbsolutePath(), fileHash);
                caseObj.addFile(file);

                System.out.println("File saved and hash generated successfully!");

            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    // Opens a native file selection dialog for the user to select a file, returning the chosen file
    // or null if the user cancels the selection.
    private File openFileDialog() {
        FileDialog fileDialog = new FileDialog((Frame) null, "Select a File", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String selectedFileName = fileDialog.getFile();
        String selectedDirectory = fileDialog.getDirectory();

        if (selectedFileName != null && selectedDirectory != null) {
            return new File(selectedDirectory, selectedFileName);
        } else {
            return null;
        }
    }

    // Computes and returns the SHA-256 hash of a file specified by its path, generating a
    // hexadecimal string representation of the hash.
    private String getFileHash(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (InputStream is = Files.newInputStream(Paths.get(filePath))) {
            byte[] byteArray = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = is.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesRead);
            }
        }

        byte[] hashBytes = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    // Copies a file from a specified source path to a destination path, replacing the existing
    // file if it already exists.
    private void copyFile(String sourcePath, String destinationPath) throws IOException {
        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    }
}
