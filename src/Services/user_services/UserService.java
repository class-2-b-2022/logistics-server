package services.user_services;

import models.user_model.User;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserService {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.getConnection();

    public static void main(String[] args) throws Exception {
        // Test login
        User u1 = new User();
        u1.setEmail("yvesisite@gmail.com");
        u1.setPassword("pass123");
        UserService service1 = new UserService();
        System.out.println("Found user...." + service1.findUser(u1).getRoleAsString());
    }

    public boolean checkIfUserExists(String email) throws Exception {
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        boolean checkUser = false;
        if (rs.next()) {
            checkUser = true;
        }
        return checkUser;
    }

    public User findUser(User user) throws Exception {
        User returnObject = new User();
        if (!checkIfUserExists(user.getEmail()))
            System.out.println("User not found.");
        else
            returnObject = getUserInfo(user.getEmail(), user.getPassword());
        return returnObject;
    }

    public String getRoleAsString(String email) throws SQLException {
        String sql = "SELECT roles.role FROM roles,users WHERE roles.role_id=users.role AND email=? LIMIT 1";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            return rs.getString(1);
        }
        return "";
    }

    public User getUserInfo(String email, String password) throws SQLException {
        User returnObject = new User();
        String sql = "SELECT names,phone FROM users WHERE email=? AND password=? LIMIT 1";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            returnObject.setNames(rs.getString(1));
            returnObject.setEmail(email);
            returnObject.setPhone(rs.getInt(2));
            returnObject.setRoleAsString(getRoleAsString(email));
            break;
        }
        return returnObject;
    }

    // Returns an object instance of the deleted user
    public User deleteUser(User user) throws SQLException {
        User returnObject = new User();
        returnObject = getUserInfo(user.getEmail(), user.getPassword());
        String sql = "DELETE FROM users WHERE email=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getEmail());
        stmt.execute();
        return returnObject;
    }

    private String hashPassword(String password) {
        System.out.println(BCrypt.gensalt(12));
        return BCrypt.hashpw(password, BCrypt.gensalt(12));

    }

    public boolean insertUser(User user) throws Exception {
        boolean userExists = checkIfUserExists(user.getEmail());
        if (!userExists) {
            String sql = "INSERT INTO users(names,email,phone,password,role)values(?,?,?,?,?)";
            String hashedPswd = hashPassword(user.getPassword());
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getNames());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getPhone());
            stmt.setString(4, hashedPswd);
            stmt.setInt(5, user.getRole());
            int inserted = stmt.executeUpdate();
            if (inserted == 1) {
                System.out.println("Registered Successfully");
                return true;
            } else {
                System.out.println("Error Occured");
                return false;
            }
        } else {
            System.out.println("User Already Exists");
            System.exit(0);
        }
        return false;
    }

}
