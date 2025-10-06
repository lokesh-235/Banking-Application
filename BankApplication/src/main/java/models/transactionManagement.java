package models;

import java.sql.*;
import BankApplication.connection;

public class transactionManagement {

    // Transfer Amount: internally calls transfer_amount procedure
    public static String handleTransferAmount(int senderAccountNumber, int receiverAccountNumber, double amount) {
        String sql = "{CALL transfer_amount(?, ?, ?, ?)}";
        String message = "";
        try (Connection con = connection.getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            // Set input parameters
            cstmt.setInt(1, senderAccountNumber);
            cstmt.setInt(2, receiverAccountNumber);
            cstmt.setDouble(3, amount);

            // Register OUT parameter
            cstmt.registerOutParameter(4, Types.VARCHAR);

            // Execute procedure
            cstmt.execute();

            // Get message
            message = cstmt.getString(4);

        } catch (SQLException e) {
            e.printStackTrace();
            message = "Database error occurred!";
        }
        return message;
    }

    // Debit Amount: calls debit_amount procedure
    public static String handleDebitAmount(int accountNumber, double amount) {
        String sql = "{CALL debit_amount(?, ?, ?)}";
        String message = "";
        try (Connection con = connection.getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            // Set input parameters
            cstmt.setInt(1, accountNumber);
            cstmt.setDouble(2, amount);

            // Register OUT parameter
            cstmt.registerOutParameter(3, Types.VARCHAR);

            cstmt.execute();

            message = cstmt.getString(3);

        } catch (SQLException e) {
            e.printStackTrace();
            message = "Database error occurred!";
        }
        return message;
    }

    // Credit Amount: calls credit_amount procedure
    public static String handleCreditAmount(int accountNumber, double amount) {
        String sql = "{CALL credit_amount(?, ?, ?)}";
        String message = "";
        try (Connection con = connection.getConnection();
             CallableStatement cstmt = con.prepareCall(sql)) {

            // Set input parameters
            cstmt.setInt(1, accountNumber);
            cstmt.setDouble(2, amount);

            // Register OUT parameter
            cstmt.registerOutParameter(3, Types.VARCHAR);

            cstmt.execute();

            message = cstmt.getString(3);

        } catch (SQLException e) {
            e.printStackTrace();
            message = "Database error occurred!";
        }
        return message;
    }
}
