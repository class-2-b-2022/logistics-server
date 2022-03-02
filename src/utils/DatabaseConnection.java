package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author : Nkubito Pacis
 * @author : Mudahemuka Manzi
 */
public class DatabaseConnection {
    // private static final String dbUrl =
    // "jdbc:mysql://remotemysql.com/XuJYb3DkoB";
    // private static final String dbUser = "XuJYb3DkoB";
    // private static final String dbPassword = "l43QuoJSlB";
    private static final String dbUrl = "jdbc:mysql://localhost:3306/logistics?characterEncoding=latin1";
    private static final String dbUser = "ntagungira";
    private static final String dbPassword = "real0987";
    public static Connection myConnection;
    private utils.ErrorMessageLogger error = new utils.ErrorMessageLogger();
    private utils.SuccessMessageLogger success = new utils.SuccessMessageLogger();

    public DatabaseConnection() {
        try {
            myConnection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        } catch (Exception ex) {
            error.log("DATABASE LOG: " + ex.getMessage());
            System.exit(-1);
        }
    }

    public Connection getConnection() {
        return myConnection;
    }

    public void init() {
        if (myConnection != null)
            success.log(
                    "========================================= CONNECTED TO DATABASE SUCCESSFULLY!!! ===================================================");
    }

}