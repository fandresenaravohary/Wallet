package controllers;

import models.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionController {
    private List<Transaction> transactions;

    public TransactionController() {
        this.transactions = new ArrayList<>();

        transactions.add(new Transaction(1, LocalDate.now(), 100.0, "Dépôt", "Dépôt d'argent"));
        transactions.add(new Transaction(2, LocalDate.now(), -50.0, "Retrait", "Retrait d'argent"));
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    public Optional<Transaction> getTransactionById(int id) {
        return transactions.stream()
                .filter(transaction -> transaction.getTransactionId() == id)
                .findFirst();
    }

    public void createTransaction(Transaction transaction) {
        // Gérer la logique pour créer une transaction
        transactions.add(transaction);
    }

    public void updateTransaction(Transaction updatedTransaction) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getTransactionId() == updatedTransaction.getTransactionId()) {
                transactions.set(i, updatedTransaction);
                break;
            }
        }
    }

    public void deleteTransaction(int id) {
        transactions.removeIf(transaction -> transaction.getTransactionId() == id);
    }

    public static void main(String[] args) {
        // Exemple d'utilisation du contrôleur
        TransactionController transactionController = new TransactionController();

        System.out.println("All transactions :");
        transactionController.getAllTransactions().forEach(System.out::println);

        int transactionIdToFind = 1;
        System.out.println("\nTransaction by ID " + transactionIdToFind + " :");
        transactionController.getTransactionById(transactionIdToFind)
                .ifPresent(System.out::println);

        Transaction newTransaction = new Transaction(3, LocalDate.now(), 200.0, "Dépôt", "Nouveau dépôt d'argent");
        transactionController.createTransaction(newTransaction);
        System.out.println("\nNew transaction created :");
        transactionController.getAllTransactions().forEach(System.out::println);

        int transactionIdToUpdate = 2;
        Transaction updatedTransaction = transactionController.getTransactionById(transactionIdToUpdate)
                .orElse(null);
        if (updatedTransaction != null) {
            updatedTransaction.setAmount(-60.0);
            transactionController.updateTransaction(updatedTransaction);
            System.out.println("\nTransaction updated :");
            transactionController.getAllTransactions().forEach(System.out::println);
        }

        int transactionIdToDelete = 1;
        transactionController.deleteTransaction(transactionIdToDelete);
        System.out.println("\nTransaction deleted :");
        transactionController.getAllTransactions().forEach(System.out::println);
    }
}