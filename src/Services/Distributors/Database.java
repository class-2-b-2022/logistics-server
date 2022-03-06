package services.Distributors;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
        private static final String dbUrl = "jdbc:mysql://localhost:3306/logistics_server";
        private static final String dbUser = "root";
        private static final String dbPassword = "";
        public static Connection myConnection;
        private Utils.ErrorMessageLogger error = new Utils.ErrorMessageLogger();
        private Utils.SuccessMessageLogger success = new Utils.SuccessMessageLogger();

        public Database() {
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
