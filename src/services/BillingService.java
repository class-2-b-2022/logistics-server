package services;

import models.Wallet;
import utils.*;
import java.sql.*;
import java.util.Objects;

/**
 * @author : Gasaro leila
 */
public class BillingService  {
    DatabaseConnection dbConn = new DatabaseConnection();
    Connection conn = dbConn.getConnection();
    Statement statement = conn.createStatement();

    public BillingService() throws SQLException {
    }

    public boolean createWallet(Wallet wallet) throws SQLException {
        try {
            String sql = "INSERT INTO wallet(userId) values(?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, wallet.getUserId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }


    public Wallet updateUserWallet(Wallet wallet, String action) throws SQLException {
        Wallet currentUserWallet = new Wallet();
        try {
            currentUserWallet = viewUserWallet(wallet);
            System.out.println(currentUserWallet.getStatus());
            if(Objects.equals(currentUserWallet.getStatus(), "Active"))  {
                System.out.println(currentUserWallet.getAmount());
                double oldBalance = currentUserWallet.getAmount();
                double amountToUpdate = wallet.getAmount();
                double newBalance = 0.0;

                switch (action) {
                    case "Withdraw" -> {
                        if (oldBalance >= amountToUpdate) {
                            newBalance = oldBalance - amountToUpdate;
                        };
                    }
                    case "Deposit" -> newBalance = oldBalance + amountToUpdate;
                }


                String updateQuery = "UPDATE wallet set amount=? WHERE userId=?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);

                preparedStatement.setDouble(1, newBalance);
                preparedStatement.setInt(2, wallet.getUserId());

                if(preparedStatement.executeUpdate() == 1){
                    System.out.println("there");
                    return viewUserWallet(wallet);
                }}
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return currentUserWallet;
    }


    public Wallet viewUserWallet(Wallet wallet) throws SQLException {
        Wallet fetchedWallet = new Wallet();
        try {
            String query = "select * from wallet where userId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, wallet.getUserId());
            ResultSet result = preparedStatement.executeQuery();

            result.next();
            System.out.println(result.getString("status"));
            if(Objects.equals(result.getString("status"), "Active")) {
                System.out.println("Inside while");
                fetchedWallet.setId(result.getInt("id"));
                fetchedWallet.setUserId(result.getInt("userId"));
                fetchedWallet.setAmount(result.getDouble("amount"));
                fetchedWallet.setDateOfCreation(String.valueOf(result.getDate("dateOfCreation")));
                fetchedWallet.setStatus(result.getString("status"));
                return fetchedWallet;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fetchedWallet;
    }

    public Wallet deleteWallet(Wallet wallet) throws SQLException {
        try {
            String updateQuery = "UPDATE wallet set status=? WHERE userId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
            preparedStatement.setString(1, "Inactive");
            preparedStatement.setInt(2, wallet.getUserId());
            if(preparedStatement.executeUpdate() == 1){
                return viewUserWallet(wallet);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return wallet;
    }

};
