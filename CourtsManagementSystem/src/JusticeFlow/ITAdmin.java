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

    public void CaseReport(List<Case> cases, List<Slot> slots) {
        try (PDDocument document = new PDDocument()) {
            // Load the custom font
            File fontFile = new File("lib/fonts/Roboto-Black.ttf");
            PDType0Font font = PDType0Font.load(document, fontFile);

            // Create a new page for the first case
            PDPage page = new PDPage();
            document.addPage(page);

            // Create the content stream
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(font, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750); // Start at the top of the page

            float yPosition = 750; // Track the current Y position on the page

            // Title
            contentStream.showText("Case Report");
            yPosition -= 20; // Adjust Y position after printing title

            // Write Case details (one per page)
            contentStream.setFont(font, 10);
            for (Case c : cases) {
                // Start a new page for each case
                // if (yPosition < 50) {
                // contentStream.endText(); // End the current text block
                // contentStream.close(); // Close the current content stream
                // page = new PDPage(); // Create a new page
                // document.addPage(page);
                // contentStream = new PDPageContentStream(document, page);
                // contentStream.setFont(font, 10);
                // contentStream.beginText(); // Begin a new text block
                // contentStream.newLineAtOffset(50, 750);
                // yPosition = 750; // Reset Y position for the new page
                // }

                // Add Case details
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Case Title: " + c.getCaseTitle());
                yPosition -= 15;
                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Case Type: " + c.getCaseType());
                yPosition -= 15;
                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Case Status: " + c.getCaseStatus());
                yPosition -= 15;
                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Case Filing Date: " + c.getFilingDate());
                yPosition -= 15;
                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Case Court Date: " + c.getCourtDate());
                yPosition -= 15;
                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Plaintiff ID: " + c.getPlaintiffID());
                yPosition -= 15;
                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Defendant ID: " + c.getDefendantID());

                List<CaseFile> f = c.getFiles();
                if (!f.isEmpty()) {
                    yPosition -= 15;
                    contentStream.newLineAtOffset(0, -15); // Move to next line
                    contentStream.showText("Case Files: ");
                    for (CaseFile file : f) {
                        contentStream.newLineAtOffset(15, -15); // Indent each file name
                        contentStream.showText(file.getFileName());
                        contentStream.newLineAtOffset(-15, 0); // Reset indentation
                    }

                } else {
                    // yPosition -= 15;
                    contentStream.newLineAtOffset(0, -15); // Move to next line
                    contentStream.showText("No Files Uploaded.");
                }

                List<CaseFile> j = c.getJudgements();
                if (!j.isEmpty()) {
                    yPosition -= 15;
                    contentStream.newLineAtOffset(0, -15); // Move to next line
                    contentStream.showText("Case Judgements: ");
                    for (CaseFile file : j) {
                        contentStream.newLineAtOffset(15, -15); // Indent each judgment file name
                        contentStream.showText(file.getFileName());
                        contentStream.newLineAtOffset(-15, 0); // Reset indentation
                    }
                } else {
                    // yPosition -= 15;
                    contentStream.newLineAtOffset(0, -15); // Move to next line
                    contentStream.showText("No Judgements Uploaded.");
                }

                yPosition -= 15;
                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Lawyer ID: " + c.getLawyerId());
                contentStream.newLineAtOffset(0, -15); // Move to next line

                Slot slot = null;
                for (Slot s : slots) {
                    if (s.getCaseID() != null && s.getCaseID().equals(c.getCaseID())) {
                        slot = s;
                        break;
                    }
                }

                if (slot != null) {
                    contentStream.showText("Slot Day: " + slot.getDayName());
                    yPosition -= 15;
                    contentStream.newLineAtOffset(0, -15); // Move to next line
                    contentStream.showText("Slot ID: " + slot.getSlotID());
                    yPosition -= 15;
                    contentStream.newLineAtOffset(0, -15); // Move to next line
                    contentStream.showText("Start Time: " + slot.getStartTime());
                    yPosition -= 15;
                    contentStream.newLineAtOffset(0, -15); // Move to next line
                    contentStream.showText("End Time: " + slot.getEndTime());
                    if (slot.getCaseID() != null) {
                        yPosition -= 15;
                        contentStream.newLineAtOffset(0, -15); // Move to next line
                        contentStream.showText("Case ID: " + slot.getCaseID());
                    }
                    if (slot.getJudgeID() != null) {
                        yPosition -= 15;
                        contentStream.newLineAtOffset(0, -15); // Move to next line
                        contentStream.showText("Judge ID: " + slot.getJudgeID());
                    }
                    if (slot.getWitnessID() != null) {
                        yPosition -= 15;
                        contentStream.newLineAtOffset(0, -15); // Move to next line
                        contentStream.showText("Witness ID: " + slot.getWitnessID());
                    }
                    if (slot.getCourtId() != null) {
                        yPosition -= 15;
                        contentStream.newLineAtOffset(0, -15); // Move to next line
                        contentStream.showText("Court ID: " + slot.getCourtId());
                    }
                    yPosition -= 50; // Adjust Y position after each slot
                } else {
                    yPosition -= 15;
                    contentStream.newLineAtOffset(0, -15); // Move to next line
                    contentStream.showText("CASE NOT SCHEDULED");
                }

                yPosition -= 50; // Adjust Y position after each case
                contentStream.endText(); // End the current text block
                contentStream.close(); // Close the current content stream
                page = new PDPage(); // Create a new page
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                contentStream.setFont(font, 10);
                contentStream.beginText(); // Begin a new text block
                contentStream.newLineAtOffset(50, 750);
                yPosition = 750; // Reset Y position for the new page
            }

            contentStream.endText(); // End the text block after all content is added
            contentStream.close(); // Close the content stream after adding all the content

            // Save the document
            document.save("CaseReport.pdf");
            System.out.println("Case Report PDF created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LawyerReport(List<Case> cases, List<Slot> slots, List<Lawyer> lawyers) {
        try (PDDocument document = new PDDocument()) {
            // Load the custom font
            File fontFile = new File("lib/fonts/Roboto-Black.ttf");
            PDType0Font font = PDType0Font.load(document, fontFile);

            // Create a new page
            PDPage page = new PDPage();
            document.addPage(page);

            // Create the content stream
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(font, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750); // Start at the top of the page

            float yPosition = 750; // Track the current Y position on the page

            // Title
            contentStream.showText("Lawyer Report");
            yPosition -= 20; // Adjust Y position after printing title

            // Iterate through each lawyer
            contentStream.setFont(font, 10);
            for (Lawyer lawyer : lawyers) {
                contentStream.newLineAtOffset(0, -30);
                // Check if the lawyer has any cases
                boolean hasCase = false;
                for (Case c : cases) {
                    if (c.getLawyerId() > 0 && c.getLawyerId() == lawyer.getLawyerID()) {
                        hasCase = true;
                        break;
                    }
                }

                contentStream.showText("Lawyer Name: " + lawyer.getFirstName() + " " + lawyer.getLastName());
                yPosition -= 15;
                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("License Number: " + lawyer.getLicenseNumber());
                yPosition -= 15;
                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Bar Association: " + lawyer.getBarAssociationID());
                yPosition -= 15;
                contentStream.newLineAtOffset(0, -15); // Move to next line
                // If the lawyer has a case, add their details
                if (hasCase) {
                    // Iterate through all the cases and slots related to this lawyer
                    for (Case c : cases) {
                        if (c.getLawyerId() > 0 && c.getLawyerId() == lawyer.getLawyerID()) {
                            // Check for page overflow and create a new page if necessary
                            if (yPosition < 50) {
                                contentStream.endText();
                                contentStream.close();
                                page = new PDPage();
                                document.addPage(page);
                                contentStream = new PDPageContentStream(document, page);
                                contentStream.setFont(font, 10);
                                contentStream.beginText();
                                contentStream.newLineAtOffset(50, 750);
                                yPosition = 750; // Reset Y position for the new page
                            }

                            // Add case details
                            contentStream.showText("Case Title: " + c.getCaseTitle());
                            yPosition -= 15;
                            contentStream.newLineAtOffset(0, -15); // Move to next line
                            contentStream.showText("Case Type: " + c.getCaseType());
                            yPosition -= 15;
                            contentStream.newLineAtOffset(0, -15); // Move to next line
                            contentStream.showText("Case Status: " + c.getCaseStatus());
                            yPosition -= 15;
                            contentStream.newLineAtOffset(0, -15); // Move to next line
                            contentStream.showText("Case Filing Date: " + c.getFilingDate());
                            yPosition -= 15;
                            contentStream.newLineAtOffset(0, -15); // Move to next line
                            contentStream.showText("Case Court Date: " + c.getCourtDate());
                            yPosition -= 15;
                            contentStream.newLineAtOffset(0, -15); // Move to next line
                            contentStream.showText("Plaintiff ID: " + c.getPlaintiffID());
                            yPosition -= 15;
                            contentStream.newLineAtOffset(0, -15); // Move to next line
                            contentStream.showText("Defendant ID: " + c.getDefendantID());
                            List<CaseFile> f = c.getFiles();
                            if (!f.isEmpty()) {
                                yPosition -= 15;
                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Case Files: ");
                                for (CaseFile file : f) {
                                    contentStream.newLineAtOffset(15, -15); // Indent each file name
                                    contentStream.showText(file.getFileName());
                                    contentStream.newLineAtOffset(-15, 0); // Reset indentation
                                }

                            } else {
                                // yPosition -= 15;
                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("No Files Uploaded.");
                            }

                            List<CaseFile> j = c.getJudgements();
                            if (!j.isEmpty()) {
                                yPosition -= 15;
                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Case Judgements: ");
                                for (CaseFile file : j) {
                                    contentStream.newLineAtOffset(15, -15); // Indent each judgment file name
                                    contentStream.showText(file.getFileName());
                                    contentStream.newLineAtOffset(-15, 0); // Reset indentation
                                }
                            } else {
                                // yPosition -= 15;
                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("No Judgements Uploaded.");
                            }
                            yPosition -= 15;
                            contentStream.newLineAtOffset(0, -15); // Move to next line

                            // Add slot details if available
                            Slot slot = null;
                            for (Slot s : slots) {
                                if (s.getCaseID() != null && s.getCaseID().equals(c.getCaseID())) {
                                    slot = s;
                                    break;
                                }
                            }

                            if (slot != null) {
                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Slot Day: " + slot.getDayName());
                                yPosition -= 15;
                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Slot ID: " + slot.getSlotID());
                                yPosition -= 15;
                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Start Time: " + slot.getStartTime());
                                yPosition -= 15;
                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("End Time: " + slot.getEndTime());
                                if (slot.getCaseID() != null) {
                                    yPosition -= 15;
                                    contentStream.newLineAtOffset(0, -15); // Move to next line
                                    contentStream.showText("Case ID: " + slot.getCaseID());
                                }
                                if (slot.getJudgeID() != null) {
                                    yPosition -= 15;
                                    contentStream.newLineAtOffset(0, -15); // Move to next line
                                    contentStream.showText("Judge ID: " + slot.getJudgeID());
                                }
                                if (slot.getWitnessID() != null) {
                                    yPosition -= 15;
                                    contentStream.newLineAtOffset(0, -15); // Move to next line
                                    contentStream.showText("Witness ID: " + slot.getWitnessID());
                                }
                                if (slot.getCourtId() != null) {
                                    yPosition -= 15;
                                    contentStream.newLineAtOffset(0, -15); // Move to next line
                                    contentStream.showText("Court ID: " + slot.getCourtId());
                                }
                                yPosition -= 30; // Adjust Y position after each slot
                            } else {
                                contentStream.showText("No Slot Assigned");
                                yPosition -= 15;
                                contentStream.newLineAtOffset(0, -15); // Move to next line
                            }

                            yPosition -= 50; // Adjust Y position after each case
                        }
                    }
                } else {
                    // If the lawyer has no cases, print a message
                    contentStream.showText("Lawyer has no cases assigned.");
                    yPosition -= 15;
                    contentStream.newLineAtOffset(0, -15); // Move to next line
                }

                contentStream.endText(); // End the current text block
                contentStream.close(); // Close the current content stream
                page = new PDPage(); // Create a new page
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                contentStream.setFont(font, 10);
                contentStream.beginText(); // Begin a new text block
                contentStream.newLineAtOffset(50, 750);
                yPosition = 750; // Reset Y position for the new page
            }

            contentStream.endText();
            contentStream.close(); // Close the content stream after adding all the content

            // Save the document
            document.save("LawyerReport.pdf");
            System.out.println("Lawyer Report PDF created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void JudgeReport(List<Case> cases, List<Slot> slots, List<Judge> judges) {
        try (PDDocument document = new PDDocument()) {
            // Load the custom font
            File fontFile = new File("lib/fonts/Roboto-Black.ttf");
            PDType0Font font = PDType0Font.load(document, fontFile);

            // Create a new page for the first judge
            PDPage page = new PDPage();
            document.addPage(page);

            // Create the content stream
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(font, 14);
            contentStream.beginText();

            // Define left and right margins
            float leftMargin = 50;
            float rightMargin = 50;
            float pageWidth = page.getMediaBox().getWidth(); // Page width
            float contentWidth = pageWidth - leftMargin - rightMargin; // Width for content
            float xPosition = leftMargin; // Start at left margin
            float yPosition = 750; // Start at top of the page

            // Title
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText("Judge Report");
            yPosition -= 20; // Adjust Y position after printing title

            // Write Judge details (one per page)
            contentStream.setFont(font, 10);
            for (Judge j : judges) {
                // Check for page overflow and create a new page if necessary
                if (yPosition < 50) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(font, 10);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(leftMargin, 750); // Start at top of new page
                    yPosition = 750; // Reset Y position for new page
                }

                // Add Judge details
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Judge Name: " + j.getFirstName() + " " + j.getLastName());
                yPosition -= 15;

                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Judge ID: " + j.getJudgeID());
                yPosition -= 15;

                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Date of Birth: " + j.getDateOfBirth());
                yPosition -= 15;

                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Appointment Date: " + j.getAppointmentDate());
                yPosition -= 15;

                contentStream.newLineAtOffset(0, -15); // Move to next line
                contentStream.showText("Court ID: " + j.getCourtID());
                yPosition -= 15;

                // Check if the judge has any associated case
                boolean hasCases = false;
                for (Slot s : slots) {
                    if (s.getJudgeID() != null && s.getJudgeID().equals(j.getJudgeID())) {
                        hasCases = true;
                        break;
                    }
                }

                // If the judge has cases, add them to the report
                if (hasCases) {
                    contentStream.newLineAtOffset(0, -30); // Move to next line
                    contentStream.showText("Assigned Cases:");
                    yPosition -= 20;

                    for (Slot s : slots) {
                        if (s.getJudgeID() != null && s.getJudgeID().equals(j.getJudgeID())) {
                            // Find the case associated with this slot
                            Case caseForSlot = null;
                            for (Case c : cases) {
                                if (s.getCaseID() != null) {
                                    if (c.getCaseID() > 0 && c.getCaseID() == s.getCaseID()) {
                                        caseForSlot = c;
                                        break;
                                    }
                                }
                            }

                            if (caseForSlot != null) {
                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Case Title: " + caseForSlot.getCaseTitle());
                                yPosition -= 15;

                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Case Type: " + caseForSlot.getCaseType());
                                yPosition -= 15;

                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Case Status: " + caseForSlot.getCaseStatus());
                                yPosition -= 15;

                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Case Filing Date: " + caseForSlot.getFilingDate());
                                yPosition -= 15;

                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Case Court Date: " + caseForSlot.getCourtDate());
                                yPosition -= 15;

                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Plaintiff ID: " + caseForSlot.getPlaintiffID());
                                yPosition -= 15;

                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Defendant ID: " + caseForSlot.getDefendantID());
                                List<CaseFile> f = caseForSlot.getFiles();
                                if (!f.isEmpty()) {
                                    yPosition -= 15;
                                    contentStream.newLineAtOffset(0, -15); // Move to next line
                                    contentStream.showText("Case Files: ");
                                    for (CaseFile file : f) {
                                        contentStream.newLineAtOffset(15, -15); // Indent each file name
                                        contentStream.showText(file.getFileName());
                                        contentStream.newLineAtOffset(-15, 0); // Reset indentation
                                    }

                                } else {
                                    // yPosition -= 15;
                                    // contentStream.newLineAtOffset(0, -15); // Move to next line
                                    contentStream.showText("No Files Uploaded.");
                                }

                                List<CaseFile> jj = caseForSlot.getJudgements();
                                if (!jj.isEmpty()) {
                                    yPosition -= 15;
                                    contentStream.newLineAtOffset(0, -15); // Move to next line
                                    contentStream.showText("Case Judgements: ");
                                    for (CaseFile file : jj) {
                                        contentStream.newLineAtOffset(15, -15); // Indent each judgment file name
                                        contentStream.showText(file.getFileName());
                                        contentStream.newLineAtOffset(-15, 0); // Reset indentation
                                    }
                                } else {
                                    // yPosition -= 15;
                                    contentStream.newLineAtOffset(0, -15); // Move to next line
                                    contentStream.showText("No Judgements Uploaded.");
                                }
                                yPosition -= 15;

                                contentStream.newLineAtOffset(0, -15); // Move to next line
                                contentStream.showText("Lawyer ID: " + caseForSlot.getLawyerId());
                                yPosition -= 15;
                            }
                        }
                    }
                } else {
                    contentStream.newLineAtOffset(0, -15); // Move to next line
                    contentStream.showText("No Assigned Cases");
                    yPosition -= 20;
                }

                contentStream.endText(); // End the current text block
                contentStream.close(); // Close the current content stream
                page = new PDPage(); // Create a new page
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                contentStream.setFont(font, 10);
                contentStream.beginText(); // Begin a new text block
                contentStream.newLineAtOffset(50, 750);
                yPosition = 750; // Reset Y position for the new page
            }

            contentStream.endText();
            contentStream.close(); // Close the content stream after adding all the content

            // Save the document
            document.save("JudgeReport.pdf");
            System.out.println("Judge Report PDF created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
