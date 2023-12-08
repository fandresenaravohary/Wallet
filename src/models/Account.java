package models;

import java.sql.Date;
import java.util.List;

public class Account {
    private int id;
    private String name;
    private double balance;
    private Date lastUpdateDate;
    private List<Transaction> transactions;
    private Currency currency;
    private AccountType type;

    public Account(int id, String name, double balance, Date lastUpdateDate, List<Transaction> transactions, Currency currency, AccountType type) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.lastUpdateDate = lastUpdateDate;
        this.transactions = transactions;
        this.currency = currency;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", lastUpdateDate=" + lastUpdateDate +
                ", transactions=" + transactions +
                ", currency=" + currency +
                ", type=" + type +
                '}';
    }
}
