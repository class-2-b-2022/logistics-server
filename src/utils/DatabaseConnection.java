package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author : Nkubito Pacis
 * @author : Mudahemuka Manzi
 */
public class DatabaseConnection {
    private static final String dbUrl = "jdbc:mysql://remotemysql.com/XuJYb3DkoB";
    private static final String dbUser = "XuJYb3DkoB";
    private static final String dbPassword = "l43QuoJSlB";
    public static Connection myConnection;
    private ErrorMessageLogger error = new ErrorMessageLogger();
    private SuccessMessageLogger success = new SuccessMessageLogger();

    public DatabaseConnection() {
        try{
            myConnection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);

        }
        catch(Exception ex){
            error.log("DATABASE LOG: "+ex.getMessage());
            System.exit(-1);
        }
    }
    public  Connection getConnection() {
        return myConnection;
    }
    public void init(){
        if(myConnection !=null) success.log("========================================= CONNECTED TO DATABASE SUCCESSFULLY!!! ===================================================");
    }

}
