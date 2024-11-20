package JusticeFlow;
import java.util.List;
public class Notification {
    private int notificationID;
    private int caseID;
    private int recipientsID; // Changed to int to match SQL schema
    private int senderID;
    private String senderType;
    private String notification; // Renamed to match SQL field

    public Notification() {
    }

    public Notification(int notificationID, int caseID, int recipientsID, int senderID, String senderType, String notification) {
        this.notificationID = notificationID;
        this.caseID = caseID;
        this.recipientsID = recipientsID;
        this.senderID = senderID;
        this.senderType = senderType;
        this.notification = notification;
    }

    // Getters and Setters
    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    public int getRecipientsID() {
        return recipientsID;
    }

    public void setRecipientsID(int recipientsID) {
        this.recipientsID = recipientsID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
    public void display(DatabaseHandler dbhandler,List<Case> Allcases) {
        Case cases=dbhandler.findCaseByID(Allcases,caseID);
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                   NOTIFICATION                   ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.printf("║ %-15s : %-30d ║%n", "Notification ID", notificationID);
        System.out.printf("║ %-15s : %-30s ║%n", "Case Title", cases.getCaseTitle());
        System.out.printf("║ %-15s : %-30s ║%n", "Recipients ID", dbhandler.getUserById(recipientsID).getUsername());
        System.out.printf("║ %-15s : %-30s ║%n", "Sender ID", dbhandler.getUserById(senderID).getUsername());
        System.out.printf("║ %-15s : %-30s ║%n", "Sender Type", senderType);
        System.out.printf("║ %-15s : %-30s ║%n", "Message", notification);
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
    
}
