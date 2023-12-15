package models;

import java.sql.Date;

public class Transaction {
    private int id;
    private String label;
    private double amount;
    private Date transactionDate;
    private TransactionType type;
    private int transactionHour;

    public Transaction(int id, String label, double amount, Date transactionDate, TransactionType type, int transactionHour) {
        this.id = id;
        this.label = label;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.type = type;
        this.transactionHour = transactionHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getTransactionHour() {
        return transactionHour;
    }

    public void setTransactionHour(int transactionHour) {
        this.transactionHour = transactionHour;
    }
}
