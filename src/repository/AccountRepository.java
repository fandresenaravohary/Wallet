package repository;

import models.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private static final Connection connection = PostgresqlConnection.getConnection();

    public AccountRepository() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection();
             Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "id SERIAL PRIMARY KEY," +
                    "username VARCHAR(255)," +
                    "password VARCHAR(255)," +
                    "email VARCHAR(255)," +
                    "balance DOUBLE PRECISION)";
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Account newAccount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO accounts (username, password, email, balance) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, newAccount.getUsername());
            preparedStatement.setString(2, newAccount.getPassword());
            preparedStatement.setString(3, newAccount.getEmail());
            preparedStatement.setDouble(4, newAccount.getBalance());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccountById(int id) {
        Account account = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM accounts WHERE id=?")) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account = new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts")) {

            while (resultSet.next()) {
                Account account = new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getDouble("balance")
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }


    public void updateAccount(Account updatedAccount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE accounts SET username=?, password=?, email=?, balance=? WHERE id=?")) {

            preparedStatement.setString(1, updatedAccount.getUsername());
            preparedStatement.setString(2, updatedAccount.getPassword());
            preparedStatement.setString(3, updatedAccount.getEmail());
            preparedStatement.setDouble(4, updatedAccount.getBalance());
            preparedStatement.setInt(5, updatedAccount.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteAccount(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM accounts WHERE id=?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
