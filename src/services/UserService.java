package services;

import models.user_model.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * @author: Isite Yves
 * @author: Ntagungira Ali Rashid
 */
public class UserService {
	static Utils.DatabaseConnection conn = new Utils.DatabaseConnection();

	public void insertUser(User user) throws SQLException {
		boolean userCheck = checkIfUserExists(user.getEmail());
		if(!userCheck){
			String sql = "INSERT INTO user(names,email,phone,password,role)values(?,?,?,?,?)";
			PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
			stmt.setString(1, user.getNames());
			stmt.setString(2, user.getEmail());
			stmt.setInt(3, user.getPhone());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, user.getRole());
			int Created=stmt.executeUpdate();
			if(Created==1){
				System.out.println("User Created Successfully");
			}else{
System.out.println("Error creating the user.");
			}
		}else{
			System.out.println("User already Exists");
		}
	}

	public String findUser(User user) throws SQLException {
		boolean userCheck = checkIfUserExists(user.getEmail());
		if (!userCheck)
			return "User not found";
		else {
			String sql2 = "SELECT role FROM roles WHERE role_id=?";
			PreparedStatement roleIdStmt = conn.getConnection().prepareStatement(sql2);
			roleIdStmt.setInt(1, 5);
			ResultSet rs2 = roleIdStmt.executeQuery();
			String returnValue="not found";
			while (rs2.next())
				returnValue=rs2.getString(1);
			return returnValue;
		}
	}

	public static boolean checkIfUserExists(String email) throws SQLException {
		String sql = "SELECT role FROM users WHERE email=?";
		PreparedStatement selectStmt = conn.getConnection().prepareStatement(sql);
		selectStmt.setString(1, email);
		ResultSet rs = selectStmt.executeQuery();
		boolean userExists = false;
		if (rs.next()) {
			System.out.println("Found...."+rs.getInt(1));
			userExists= true;
		}
		return userExists;
	}

	public static void main(String[] args) throws IOException, SQLException {
		// Test Login
		UserService s1=new UserService();
		User u1=new User();
		u1.setEmail("yvesisite@gmail.com");
		u1.setNames("Isite");
		u1.setPassword("pass123");
		System.out.println(s1.findUser(u1));
	}
}
