package JusticeFlow;

public class BarApplication {
    private int applicationTableId;
    private int lawyerId;
    private String applicationTime;
    private int status;
    private int barid;

    // Constructor
    public BarApplication(int applicationTableId, int lawyerId, String applicationTime, int status,int barid) {
        this.applicationTableId = applicationTableId;
        this.lawyerId = lawyerId;
        this.applicationTime = applicationTime;
        this.status = status;
        this.barid=barid;
    }

    // Getters and toString for debugging
    public int getApplicationTableId() { return applicationTableId; }
    public int getLawyerId() { return lawyerId; }
    public String getApplicationTime() { return applicationTime; }
    public int getStatus() { return status; }
    public void setStatus(int val){
        this.status=val;
    }
    public int getBarId(){return barid;}
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
