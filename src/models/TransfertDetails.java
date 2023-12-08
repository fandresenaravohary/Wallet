package models;

import java.sql.Timestamp;

public static class TransferDetails {
    private int id;
    private String debitorUsername;
    private String creditorUsername;
    private Timestamp transferDate;
    private double transferAmount;

    public TransferDetails(int id, String debitorUsername, String creditorUsername, Timestamp transferDate, double transferAmount) {
        this.id = id;
        this.debitorUsername = debitorUsername;
        this.creditorUsername = creditorUsername;
        this.transferDate = transferDate;
        this.transferAmount = transferAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDebitorUsername() {
        return debitorUsername;
    }

    public void setDebitorUsername(String debitorUsername) {
        this.debitorUsername = debitorUsername;
    }

    public String getCreditorUsername() {
        return creditorUsername;
    }

    public void setCreditorUsername(String creditorUsername) {
        this.creditorUsername = creditorUsername;
    }

    public Timestamp getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Timestamp transferDate) {
        this.transferDate = transferDate;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }
}
