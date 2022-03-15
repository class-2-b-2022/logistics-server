package Services;


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

    public BillingService() throws SQLException {
    }


    public Object updateDistributorWallet(Wallet billing) throws SQLException {
        Object rs = null;
        try {
            rs = (Object) statement.executeQuery("update payments set `balance`=" + billing.getAmount() + "WHERE `id`=" + billing.getUserId());
        }catch(SQLException e) {
            e.printStackTrace();
        }
       return rs;
    }


//    public Object viewUserWallet(int userId) throws SQLException {
//        Object rs = null;
//        try {
//            rs = (Object) statement.executeQuery("select * from payments where `id`="+userId);
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return rs;
//    }

    public boolean createWallet(Wallet wallet) throws SQLException {
        try {
            String sql = "INSERT INTO wallet(userId) values(?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, wallet.getUserId());
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }


    public Wallet updateUserWallet(Wallet wallet, String action) throws SQLException {
        try {
            String updateQuery = "UPDATE wallet set balance=? WHERE id=?";
            Wallet currentUserWallet = viewUserWallet(wallet.getUserId());
            double oldBalance = currentUserWallet.getAmount();
            double newBalance = 0.0;


            switch (action) {
                case "Withdraw" -> {
                    if (oldBalance >= wallet.getAmount()) {
                        newBalance = oldBalance - wallet.getAmount();
                    }
                    ;
                }
                case "Deposit" -> newBalance = oldBalance + wallet.getAmount();
            }

            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);

            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setInt(2, wallet.getId());

            int rowsUpdated=preparedStatement.executeUpdate();
            if(rowsUpdated==1){
                return wallet;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return wallet;
    }


    public Wallet viewUserWallet(int userId) throws SQLException {
        Wallet wallet = null;
        try {
            wallet = new Wallet();
            String query = "select * from wallet where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                wallet.setId(result.getInt("id"));
                wallet.setUserId(result.getInt("userId"));
                wallet.setAmount(result.getDouble("amount"));
                wallet.setDateOfCreation(result.getString("dateOfCreation"));

                return wallet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallet;
    }


}
