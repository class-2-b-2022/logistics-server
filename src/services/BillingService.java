package services;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BillingService  {
    DatabaseConnection dbConn = new DatabaseConnection();
    Connection conn = dbConn.getConnection();
    Statement statement = conn.createStatement();

    public BillingService() throws SQLException {
    }

    public Object updateDistributorWallet(int newAmount, int userId ) throws SQLException {
        Object rs = null;
        try {
            rs = (Object) statement.executeQuery("update payments set `balance`=" + newAmount + "WHERE `id`=" + userId);
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
}