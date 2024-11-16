package JusticeFlow;

public class Court {
    private int courtID;
    private String courtName;
    private String courtType;
    private String address;
    private String phoneNumber;
    private String email;

    public Court(int courtID, String courtName, String courtType, String address, String phoneNumber, String email) {
        this.courtID = courtID;
        this.courtName = courtName;
        this.courtType = courtType;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getCourtID() {
        return courtID;
    }

    public void setCourtID(int courtID) {
        this.courtID = courtID;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getCourtType() {
        return courtType;
    }

    public void setCourtType(String courtType) {
        this.courtType = courtType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
