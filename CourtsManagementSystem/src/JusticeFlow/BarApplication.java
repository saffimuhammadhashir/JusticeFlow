package JusticeFlow;

public class BarApplication {
    private int applicationTableId;
    private int lawyerId;
    private String applicationTime;
    private int status;

    // Constructor
    public BarApplication(int applicationTableId, int lawyerId, String applicationTime, int status) {
        this.applicationTableId = applicationTableId;
        this.lawyerId = lawyerId;
        this.applicationTime = applicationTime;
        this.status = status;
    }

    // Getters and toString for debugging
    public int getApplicationTableId() { return applicationTableId; }
    public int getLawyerId() { return lawyerId; }
    public String getApplicationTime() { return applicationTime; }
    public int getStatus() { return status; }
    public void setStatus(int val){
        this.status=val;
    }
    @Override
    public String toString() {
        return "BarApplication{" +
               "applicationTableId=" + applicationTableId +
               ", lawyerId=" + lawyerId +
               ", applicationTime='" + applicationTime + '\'' +
               ", status=" + status +
               '}';
    }
}
