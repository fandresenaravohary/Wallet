package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.TransferDetails;

public class TransfertHistoryservice {
    public List<TransferDetails> getTransferHistoryByDateRange(Date startDate, Date endDate) {
        List<TransferDetails> transferHistory = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(Env.DB_URL,Env.DB_USERNAME, Env.DB_PASSWORD)) {
            String query = "SELECT th.id, debitor.username AS debitor_username, creditor.username AS creditor_username, " +
                    "th.transfer_date, debitor_transaction.amount AS transfer_amount " +
                    "FROM TransferHistory th " +
                    "JOIN Transaction debitor_transaction ON th.debitor_transaction_id = debitor_transaction.id " +
                    "JOIN Account debitor ON debitor_transaction.account_id = debitor.id " +
                    "JOIN Transaction creditor_transaction ON th.creditor_transaction_id = creditor_transaction.id " +
                    "JOIN Account creditor ON creditor_transaction.account_id = creditor.id " +
                    "WHERE th.transfer_date BETWEEN ? AND ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setTimestamp(1, new Timestamp(startDate.getTime()));
                preparedStatement.setTimestamp(2, new Timestamp(endDate.getTime()));

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    TransferDetails entry = new TransferDetails(
                            resultSet.getInt("id"),
                            resultSet.getString("debitor_username"),
                            resultSet.getString("creditor_username"),
                            resultSet.getTimestamp("transfer_date"),
                            resultSet.getDouble("transfer_amount")
                    );
                    transferHistory.add(entry);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transferHistory;
    }
}
