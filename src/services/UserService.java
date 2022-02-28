package services;

import models.user_model.User;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {
   static DatabaseConnection conn=new DatabaseConnection();
   public static void insertUser(User user) throws SQLException {
	   String sql="INSERT INTO user(names,email,phone,password,role)values(?,?,?,?,?)";
	   PreparedStatement stmt=conn.getConnection().prepareStatement(sql);
	   stmt.setString(1, user.getNames());
	   stmt.setString(2, user.getEmail());
	   stmt.setInt(3, user.getPhone());
	   stmt.setString(4,user.getPassword());
	   stmt.setString(5, user.getRole());
	   stmt.executeUpdate();
   }
   public static void findUser(User user) throws  SQLException {
	   String sql="SELECT * FROM ";
	   PreparedStatement stmt=conn.getConnection().prepareStatement(sql);
	   stmt.setString(1, user.getNames());
	   stmt.setString(2, user.getEmail());
	   stmt.setInt(3, user.getPhone());
	   stmt.setString(4,user.getPassword());
	   stmt.setString(5, user.getRole());
	   stmt.executeUpdate();
   }
}
