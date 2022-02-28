package services;

import models.user_model.User;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
	 private DatabaseConnection databaseConnection = new DatabaseConnection();
	    Connection connection = databaseConnection.getConnection();
   
   public boolean checkIfUserExists(String email) throws Exception {
       String sql = "SELECT * FROM users WHERE email = ?";
       PreparedStatement stmt=connection.prepareStatement(sql);
       stmt.setString(1,email);
       ResultSet rs = stmt.executeQuery();
       boolean checkUser = false;
       if (rs.next()){
           checkUser = true;
       }
       return checkUser;
   }
   
   public void insertUser(User user) throws Exception {
	   boolean userExists=checkIfUserExists(user.getEmail());
	   if(!userExists) {
		   String sql="INSERT INTO users(names,email,phone,password,role)values(?,?,?,?,?)";
		   PreparedStatement stmt=connection.prepareStatement(sql);
		   stmt.setString(1, user.getNames());
		   stmt.setString(2, user.getEmail());
		   stmt.setInt(3, user.getPhone());
		   stmt.setString(4,user.getPassword());
		   stmt.setString(5, user.getRole());
		   int inserted=stmt.executeUpdate();
		   if(inserted==1) {
			  System.out.println("Registered Successfully");
		   }else {
			   System.out.println("Error Occured"); 
		   }
	   }else {
		   System.out.println("User Already Exists");
		   System.exit(0);
	   }
   }
   public void findUser(User user) throws  SQLException {
	   String sql="SELECT * FROM ";
	   PreparedStatement stmt=connection.prepareStatement(sql);
	   stmt.setString(1, user.getNames());
	   stmt.setString(2, user.getEmail());
	   stmt.setInt(3, user.getPhone());
	   stmt.setString(4,user.getPassword());
	   stmt.setString(5, user.getRole());
	   stmt.executeUpdate();
   }
}
