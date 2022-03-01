package services;

import Utils.DatabaseConnection;
import models.DeliveryModule.Vehicle;
import models.billing.BillingModel;
import models.billing.Wallet;

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

    public void createWallet(Wallet wallet) throws SQLException {
        try {
            String sql = "INSERT INTO wallet(userId) values(?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, wallet.getUserId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Wallet updateDistributorWallet(Wallet wallet) throws SQLException {
        try {
            String updateQuery = "UPDATE wallet set balance=? WHERE id=?";

            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);

            preparedStatement.setDouble(1, wallet.getAmount());
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
