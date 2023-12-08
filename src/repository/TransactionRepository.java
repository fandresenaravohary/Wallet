package repository;

import models.Transaction;
import models.TransactionType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private static final Connection connection = PostgresqlConnection.getConnection();

    public void addTransaction(Transaction transaction) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO transactions (label, amount, TransactionDate, type) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, transaction.getLabel());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setDate(3, new java.sql.Date(transaction.getTransactionDate().getTime())); // Utilisation de java.sql.Date pour les dates SQL
            preparedStatement.setString(4, String.valueOf(transaction.getType()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions")) {

            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                        resultSet.getInt("transaction_id"),
                        resultSet.getString("label"),
                        resultSet.getDouble("amount"),
                        resultSet.getDate("TransactionDate"),
                        TransactionType.valueOf(resultSet.getString("type"))
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }


    public void updateTransaction(Transaction updatedTransaction) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE transactions SET label=?, amount=?, TransactionDate=?, type=? WHERE transaction_id=?")) {

            preparedStatement.setString(1, updatedTransaction.getLabel());
            preparedStatement.setDouble(2, updatedTransaction.getAmount());
            preparedStatement.setDate(3, new java.sql.Date(updatedTransaction.getTransactionDate().getTime()));
            preparedStatement.setString(4, updatedTransaction.getType().toString());
            preparedStatement.setInt(5, updatedTransaction.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}