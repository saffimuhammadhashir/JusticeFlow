package JusticeFlow;
import java.util.List;

public class Notification {
    private int notificationID;
    private int caseID;
    private List<Integer> recipientsID;
    private List<String> recipientsType;
    private int senderID;
    private String senderType;

    public Notification(int notificationID, int caseID, List<Integer> recipientsID, List<String> recipientsType, int senderID, String senderType) {
        this.notificationID = notificationID;
        this.caseID = caseID;
        this.recipientsID = recipientsID;
        this.recipientsType = recipientsType;
        this.senderID = senderID;
        this.senderType = senderType;
    }

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

    public List<Integer> getRecipientsID() {
        return recipientsID;
    }

    public void setRecipientsID(List<Integer> recipientsID) {
        this.recipientsID = recipientsID;
    }

    public List<String> getRecipientsType() {
        return recipientsType;
    }

    public void setRecipientsType(List<String> recipientsType) {
        this.recipientsType = recipientsType;
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
}
