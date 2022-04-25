package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.ResultSet;

public class LocalDBConnection {
	static String url = "jdbc:mysql://localhost:3306/logistics?characterEncoding=utf8";
	static String username = "root";
	static String password = "Borntopraise@10";
	
	public static void main(String[] args) throws IOException, SQLException {
			//1.Load Driver
			try {
				Class.forName("com.mysql.jdbc.Driver");
				 Connection conn = DriverManager.getConnection(url, username, password);
				if(conn!=null) {
					System.out.println("Connected to MYSQL!");
//					Statement stmnt = conn.createStatement();
//					ResultSet rs = stmnt.executeQuery("select * from company");
				}
				else{
					System.out.println("Connection failed.");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
	}
}

