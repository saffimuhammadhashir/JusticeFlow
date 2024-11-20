package JusticeFlow;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class ITAdmin extends User {
    private int adminID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Date hireDate;
    private String email;
    private String phoneNumber;
    private int userID;

    public ITAdmin() {

    }

    public ITAdmin(int userID, String username, String password, String role, String email, String phoneNumber,
            boolean activate,
            int adminID, String firstName, String lastName, Date dateOfBirth, String gender, Date hireDate,
            String emailAddress, String phoneNum) {
        super(userID, username, password, role, email, phoneNumber, activate); // Calling the constructor of User class

        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.hireDate = hireDate;
        this.email = emailAddress;
        this.phoneNumber = phoneNum;
        this.userID = userID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
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
        return "IT Admin {" +
                "judgeID=" + adminID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userID=" + userID +
                '}';
    }

    // public void CaseReport(Scanner scanner, List<Case> AllCases, FileHandler
    // fileHandler, DatabaseHandler dbHandler){
    // String outputFilePath = "VariableTextPDF.pdf";

    // // Text content
    // String shortText = "This is a short piece of text.";
    // String longText = "This is a much longer piece of text that will
    // automatically wrap into multiple lines if it exceeds the page width.";
    // String dynamicText = "Dynamic text handling is great for creating reports,
    // invoices, and documents with varying content lengths.";

    // try (PDDocument document = new PDDocument()) {
    // // Add a blank page
    // PDPage page = new PDPage();
    // document.addPage(page);

    // // Prepare to write content
    // try (PDPageContentStream contentStream = new PDPageContentStream(document,
    // page)) {
    // // Set font and font size
    // contentStream.setFont(PDType1Font.HELVETICA, 12);

    // // Begin the text block
    // contentStream.beginText();
    // contentStream.setLeading(14.5f); // Line spacing
    // contentStream.newLineAtOffset(50, 700); // Start position (x: 50, y: 700)

    // // Write text with wrapping
    // addWrappedText(contentStream, shortText, 500);
    // contentStream.newLine();
    // addWrappedText(contentStream, longText, 500);
    // contentStream.newLine();
    // addWrappedText(contentStream, dynamicText, 500);

    // // End the text block
    // contentStream.endText();
    // }

    // // Save the document
    // document.save(outputFilePath);
    // System.out.println("PDF created successfully at " + outputFilePath);
    // } catch (IOException e) {
    // System.err.println("Error while creating the PDF: " + e.getMessage());
    // }
    // }

    // private static void addWrappedText(PDPageContentStream contentStream, String
    // text, float maxWidth) throws IOException {
    // float currentWidth = 0;
    // for (String word : text.split(" ")) {
    // float wordWidth = PDType1Font.HELVETICA.getStringWidth(word + " ") / 1000 *
    // 12;
    // if (currentWidth + wordWidth > maxWidth) {
    // contentStream.newLine();
    // currentWidth = 0;
    // }
    // contentStream.showText(word + " ");
    // currentWidth += wordWidth;
    // }
    // }

    public static void CaseReport() {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            // Load the custom font
            File fontFile = new File("lib/fonts/Roboto-Black.ttf");
            PDType0Font font = PDType0Font.load(document, fontFile);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(font, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("PDFBox with PDType0Font!");
                contentStream.endText();
            }

            document.save("CaseReport.pdf");
            System.out.println("PDF created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
