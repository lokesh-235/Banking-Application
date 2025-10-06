package models;


import java.sql.*;
import java.util.*;

public class Transactions {
    private int transactionId;
    private Integer fromAccountNumber; 
    private Integer toAccountNumber;   
    private String transactionType; 
    private double amount;
    private String description;
    private Timestamp transactionDate;

  
    public Transactions(int transactionId, Integer fromaccountNumber, Integer toaccountNumber,
                        String transactionType, double amount, String description,
                        Timestamp transactionDate) {
        this.transactionId = transactionId;
        this.fromAccountNumber = fromaccountNumber;
        this.toAccountNumber = toaccountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
       
    }

    // Getters
    public int getTransactionId() { return transactionId; }
    public Integer getFromAccountNumber() { return fromAccountNumber; }
    public Integer getToAccountNumber() { return toAccountNumber; }
    public String getTransactionType() { return transactionType; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public Timestamp getTransactionDate() { return transactionDate; }
    
    
    
    public static Transactions getTransactionById(int transactionId) {
        Transactions transaction = null;

        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";

        try (Connection con = connection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setInt(1, transactionId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                transaction = new Transactions(
                    rs.getInt("transaction_id"),
                    (Integer) rs.getObject("from_account_id"),
                    (Integer) rs.getObject("to_account_id"),
                    rs.getString("transaction_type"),
                    rs.getDouble("amount"),
                    rs.getString("description"),
                    rs.getTimestamp("transaction_date")
              
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return transaction;
    }
    
    public static List<Transactions> findTransactionsByAccountNumber(int accountNumber) {
        List<Transactions> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE from_account_id = ? OR to_account_id = ? ORDER BY transaction_date DESC";

        try (Connection con = connection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, accountNumber);
            pstmt.setInt(2, accountNumber);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Transactions t = new Transactions(
                		rs.getInt("transaction_id"),
                        (Integer) rs.getObject("from_account_id"),
                        (Integer) rs.getObject("to_account_id"),
                        rs.getString("transaction_type"),
                        rs.getDouble("amount"),
                        rs.getString("description"),
                        rs.getTimestamp("transaction_date")
                      
                );
                
                transactions.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
    
    public String toString() {
        return "Transaction ID: " + transactionId +
               "\nFrom Account: " + fromAccountNumber +
               "\nTo Account: " + toAccountNumber +
               "\nType: " + transactionType +
               "\nAmount: " + amount +
               "\nDescription: " + description +
               "\nDate: " + transactionDate +"\n";
    }
}

