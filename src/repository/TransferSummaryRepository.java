package repository;

import models.Transaction;
import models.TransactionSummary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static models.Account.*;

public class TransferSummaryRepository {
    private static final Connection connection = PostgresqlConnection.getConnection();

    public TransactionSummary getTransactionSummary(int accountId, String startDatetime, String endDatetime) {
        TransactionSummary result = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "{CALL get_category_sum(?, ?, ?)}")) {

            preparedStatement.setInt(1, accountId);
            preparedStatement.setString(2, startDatetime);
            preparedStatement.setString(3, endDatetime);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double restaurant = resultSet.getDouble("restaurant");
                double salary = resultSet.getDouble("salary");
                result = new TransactionSummary(restaurant, salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public TransactionSummary getTransactionSummary(Date startDatetime, Date endDatetime) {
        List<Transaction> transactions = getBalanceHistoryInDateTimeRange((java.sql.Date) startDatetime, (java.sql.Date) endDatetime);

        double restaurantSum = 0;
        double salarySum = 0;

        for (Transaction transaction : transactions) {
            if ("Restaurant".equals(transaction.getCategory())) {
                restaurantSum += transaction.getAmount();
            } else if ("Salary".equals(transaction.getCategory())) {
                salarySum += transaction.getAmount();
            }
            // Ajoutez d'autres catégories au besoin
        }

        return new TransactionSummary(restaurantSum, salarySum);
    }
}
