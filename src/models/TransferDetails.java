package models;

import java.sql.Date;

public class TransferDetails {
    private int id;
    private int IdDebitorTransaction;
    private int IdCreditorTransaction;
    private Date transferDate;

    public TransferDetails(int id, int idDebitorTransaction, int idCreditorTransaction, Date transferDate) {
        this.id = id;
        IdDebitorTransaction = idDebitorTransaction;
        IdCreditorTransaction = idCreditorTransaction;
        this.transferDate = transferDate;
    }    

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdDebitorTransaction() {
        return IdDebitorTransaction;
    }
    public void setIdDebitorTransaction(int idDebitorTransaction) {
        IdDebitorTransaction = idDebitorTransaction;
    }
    public int getIdCreditorTransaction() {
        return IdCreditorTransaction;
    }
    public void setIdCreditorTransaction(int idCreditorTransaction) {
        IdCreditorTransaction = idCreditorTransaction;
    }
    public Date getTransferDate() {
        return transferDate;
    }
    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }


}
