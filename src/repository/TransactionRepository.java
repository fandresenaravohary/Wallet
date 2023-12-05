package repository;

import models.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private static final Connection connection = PostgresqlConnection.getConnection();

    public TransactionRepository() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS transactions (" +
                    "transaction_id SERIAL PRIMARY KEY," +
                    "date DATE," +
                    "amount DOUBLE PRECISION," +
                    "type VARCHAR(255)," +
                    "description VARCHAR(255))";
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTransaction(Transaction transaction) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO transactions (date, amount, type, description) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setDate(1, Date.valueOf(transaction.getDate()));
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setString(3, transaction.getType());
            preparedStatement.setString(4, transaction.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transaction getTransactionById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM transactions WHERE transaction_id = ?")) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Transaction(
                        resultSet.getInt("transaction_id"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getDouble("amount"),
                        resultSet.getString("type"),
                        resultSet.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions")) {

            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                        resultSet.getInt("transaction_id"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getDouble("amount"),
                        resultSet.getString("type"),
                        resultSet.getString("description")
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
                "UPDATE transactions SET date=?, amount=?, type=?, description=? WHERE transaction_id=?")) {

            preparedStatement.setDate(1, Date.valueOf(updatedTransaction.getDate()));
            preparedStatement.setDouble(2, updatedTransaction.getAmount());
            preparedStatement.setString(3, updatedTransaction.getType());
            preparedStatement.setString(4, updatedTransaction.getDescription());
            preparedStatement.setInt(5, updatedTransaction.getTransactionId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTransaction(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM transactions WHERE transaction_id=?")) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}