package repository;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private static final Connection connection = PostgresqlConnection.getConnection();

    public void addAccount(Account newAccount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Account (name, balance, last_update_date, currency, type) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, newAccount.getName());
            preparedStatement.setDouble(2, newAccount.getBalance());
            preparedStatement.setDate(3, new Date(newAccount.getLastUpdateDate().getTime()));
            preparedStatement.setString(4, newAccount.getCurrency().getCode().name());
            preparedStatement.setString(5, newAccount.getType().name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Account getAccountById(int id) {
        Account account = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Account WHERE id=?")) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account = new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("balance"),
                        resultSet.getDate("last_update_date"),
                        getTransactionsForAccount(id),
                        new Currency(resultSet.getInt("currency_id"), "Euro", CurrencyCode.EUR),
                        AccountType.valueOf(resultSet.getString("type"))
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
                int accountId = resultSet.getInt("id");
                Account account = new Account(
                        accountId,
                        resultSet.getString("name"),
                        resultSet.getDouble("balance"),
                        resultSet.getDate("last_update_date"),
                        getTransactionsForAccount(accountId),
                        new Currency(resultSet.getInt("currency_id"), "Euro", CurrencyCode.EUR),
                        AccountType.valueOf(resultSet.getString("type"))
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }



    private List<Transaction> getTransactionsForAccount(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM transactions WHERE account_id=?")) {

            preparedStatement.setInt(1, accountId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                        resultSet.getInt("id"),
                        resultSet.getString("label"),
                        resultSet.getDouble("amount"),
                        resultSet.getDate("transaction_date"),
                        TransactionType.valueOf(resultSet.getString("type")),
                        resultSet.getInt("transactionHour"),
                        Category.valueOf(resultSet.getString("category"))
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
