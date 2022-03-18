package services;

<<<<<<< HEAD

import utils.*;
import models.BillingModel;
import java.sql.*;

/**
 * @author : Gasaro leila
 */
public class BillingService  {
    DatabaseConnection dbConn = new DatabaseConnection();
    Connection conn = dbConn.getConnection();
    Statement statement = conn.createStatement();
=======
import models.Wallet;
import utils.*;
import java.sql.*;

	/**
	 * @author : Gasaro leila
	 */
	public class BillingService  {
	    DatabaseConnection dbConn = new DatabaseConnection();
	    Connection conn = dbConn.getConnection();
	    Statement statement = conn.createStatement();
>>>>>>> 768c3f3addc3d44775ad4f3e9ad1125eefcef0d8

    public BillingService() throws SQLException {
    }

<<<<<<< HEAD
    public Object updateDistributorWallet(BillingModel billing) throws SQLException {
        Object rs = null;
        try {
            rs = (Object) statement.executeQuery("update payments set `balance`=" + billing.getAmount() + "WHERE `id`=" + billing.getUserId());
        }catch(SQLException e) {
            e.printStackTrace();
        }
       return rs;
    }


    public Object viewUserWallet(int userId) throws SQLException {
        Object rs = null;
        try {
            rs = (Object) statement.executeQuery("select * from payments where `id`="+userId);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
=======
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
        try {
            Wallet currentUserWallet = viewUserWallet(wallet);
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
                return viewUserWallet(wallet);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return wallet;
    }


    public Wallet viewUserWallet(Wallet wallet) throws SQLException {
        try {
            Wallet fetchedWallet = new Wallet();
            String query = "select * from wallet where userId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, wallet.getUserId());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                fetchedWallet.setId(result.getInt("id"));
                fetchedWallet.setUserId(result.getInt("userId"));
                fetchedWallet.setAmount(result.getDouble("amount"));
                fetchedWallet.setDateOfCreation(String.valueOf(result.getDate("dateOfCreation")));
                return fetchedWallet;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallet;
    }

>>>>>>> 768c3f3addc3d44775ad4f3e9ad1125eefcef0d8
}
