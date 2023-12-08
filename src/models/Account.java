package models;

import java.sql.Date;
import java.util.ArrayList;
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

    public Account performTransaction(Transaction transaction) {
        if (getType() == AccountType.BANK || getBalance() >= transaction.getAmount()) {
            Transaction newTransaction = new Transaction(
                    transaction.getId(),
                    transaction.getLabel(),
                    transaction.getAmount(),
                    transaction.getTransactionDate(),
                    transaction.getType()
            );


            // Ajout de la nouvelle transaction à la liste des transactions
            getTransactions().add(newTransaction);

            // Mise à jour du solde du compte
            if (transaction.getType() == TransactionType.DEBIT) {
                updateBalance(-transaction.getAmount());
            } else {
                updateBalance(transaction.getAmount());
            }

            return new Account(
                    getId(),
                    getName(),
                    getBalance(),
                    getLastUpdateDate(),
                    getTransactions(),
                    getCurrency(),
                    getType()
            );
        } else {
            System.out.println("Solde insuffisant pour effectuer la transaction.");
            return this;
        }
    }
    private void updateBalance(double amount) {
        setBalance(getBalance() + amount);
    }

    public double getBalanceAtDateTime(Date dateTime) {
        double balance = 0.0;

        // Supposez que transactions soit une liste des transactions du compte
        for (Transaction transaction : transactions) {
            // Vérifiez si la date de la transaction est antérieure à la date spécifiée
            if (transaction.getTransactionDate().before(dateTime) || transaction.getTransactionDate().equals(dateTime)) {
                // Mettez à jour le solde en fonction du type de transaction
                if (transaction.getType() == TransactionType.CREDIT) {
                    balance += transaction.getAmount();
                } else if (transaction.getType() == TransactionType.DEBIT) {
                    balance -= transaction.getAmount();
                }
            }
        }

        return balance;
    }

    public List<Double> getBalanceHistoryInDateTimeRange(Date startDateTime, Date endDateTime) {
        List<Double> balanceHistory = new ArrayList<>();
        double currentBalance = 0.0;

        for (Transaction transaction : transactions) {
            if ((transaction.getTransactionDate().after(startDateTime) || transaction.getTransactionDate().equals(startDateTime))
                    && transaction.getTransactionDate().before(endDateTime)) {

                // Mettez à jour le solde en fonction du type de transaction
                if (transaction.getType() == TransactionType.CREDIT) {
                    currentBalance += transaction.getAmount();
                } else if (transaction.getType() == TransactionType.DEBIT) {
                    currentBalance -= transaction.getAmount();
                }

                // Ajoutez le solde actuel à l'historique
                balanceHistory.add(currentBalance);
            }
        }

        return balanceHistory;
    }   
}
