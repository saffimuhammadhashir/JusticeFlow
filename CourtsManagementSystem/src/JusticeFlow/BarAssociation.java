package JusticeFlow;

import java.util.List;
import java.util.Date;

public class BarAssociation {
    private int barAssociationID;
    private String associationName;
    private String address;
    private String phoneNumber;
    private String email;

    public int getBarAssociationID() {
        return barAssociationID;
    }

    public void setBarAssociationID(int barAssociationID) {
        this.barAssociationID = barAssociationID;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
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

    public static BarAssociation getbar(List<BarAssociation> bars, int id) {
        for (BarAssociation b : bars) {
            if (b.getBarAssociationID() == id) {
                return b;
            }
        }
        return null;
    }
}
