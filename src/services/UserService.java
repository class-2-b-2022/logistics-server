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

	public static void insertUser(User user) throws SQLException {
		String sql = "INSERT INTO user(names,email,phone,password,role)values(?,?,?,?,?)";
		PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
		stmt.setString(1, user.getNames());
		stmt.setString(2, user.getEmail());
		stmt.setInt(3, user.getPhone());
		stmt.setString(4, user.getPassword());
		stmt.setString(5, user.getRole());
		stmt.executeUpdate();
	}

	public static String findUser(User user) throws SQLException {
		int userCheck = (checkIfUserExists(user.getEmail(), user.getPassword()));
		if (userCheck != -1)
			return "User not found";
		else {
			String sql2 = "SELECT role FROM roles WHERE role_id=?";
			PreparedStatement roleIdStmt = conn.getConnection().prepareStatement(sql2);
			roleIdStmt.setInt(1, userCheck);
			ResultSet rs2 = roleIdStmt.executeQuery();
			while (rs2.next())
				return rs2.getString(1);
			return "not found";
		}
	}

	public static int checkIfUserExists(String email, String password) throws SQLException {
		String sql = "SELECT role FROM users WHERE email=? AND password=?";
		PreparedStatement selectStmt = conn.getConnection().prepareStatement(sql);
		selectStmt.setString(1, email);
		selectStmt.setString(2, password);
		ResultSet rs = selectStmt.executeQuery();
		int userCount = 0;
		while (rs.next()) {
			userCount++;
		}
		if (userCount == 0)
			return -1;
		else
			return rs.getInt(1);
	}

	public static void main(String[] args) throws IOException, SQLException {
		findUser(new User());
	}
}
