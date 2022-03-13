package Services;

import Utils.DatabaseConnection;
import models.BillingModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author : Gasaro leila
 */
public class BillingService  {
    DatabaseConnection dbConn = new DatabaseConnection();
    Connection conn = dbConn.getConnection();
    Statement statement = conn.createStatement();

    public BillingService() throws SQLException {
    }

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
}
