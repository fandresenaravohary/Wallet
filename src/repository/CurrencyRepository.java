package repository;

import java.sql.*;
import models.Currency;
import models.CurrencyCode;

public class CurrencyRepository {
    private static final Connection connection = PostgresqlConnection.getConnection();
    public Currency getCurrency(int id) {
        String SQL = "SELECT * FROM currency WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(SQL)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Currency(
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    CurrencyCode.valueOf(rs.getString("code"))
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void createCurrency(Currency currency) {
        String SQL = "INSERT INTO currency (id, name, code) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(SQL)) {

            pstmt.setInt(1, currency.getId());
            pstmt.setString(2, currency.getName());
            pstmt.setString(3, currency.getCode().toString());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateCurrency(Currency currency) {
        String SQL = "UPDATE currency SET name = ?, code = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(SQL)) {

            pstmt.setString(1, currency.getName());
            pstmt.setString(2, currency.getCode().toString());
            pstmt.setInt(3, currency.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
