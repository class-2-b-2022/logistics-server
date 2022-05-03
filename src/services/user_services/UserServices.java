package services.user_services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.security.crypto.bcrypt.BCrypt;

import utils.DatabaseConnection;
import models.user_model.User;


public class UserServices {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.getConnection();

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
        String sql = "SELECT names,phone FROM users WHERE email=? LIMIT 1";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getEmail());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            returnObject.setNames(rs.getString(1));
            returnObject.setEmail(user.getEmail());
            returnObject.setPhone(rs.getInt(2));
            returnObject.setRoleAsString(getRoleAsString(user.getEmail()));
            break;
        }
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
    /**
     * @author Ntagungira  Ali
     * @description hashing user password
     */
    private String hashPassword(String password){
        //System.out.println(BCrypt.gensalt(12));
        return BCrypt.hashpw(password,BCrypt.gensalt(12));

    }

    public boolean insertUser(User user) throws Exception {
        boolean val=false;
        boolean userExists=checkIfUserExists(user.getEmail());
        if(!userExists) {
            String sql="INSERT INTO users(names,email,phone,password,role)values(?,?,?,?,?)";
            String hashedPswd=hashPassword(user.getPassword().trim());
            System.out.println(hashedPswd);
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1, user.getNames());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getPhone());
            stmt.setString(4,user.getPassword());
            stmt.setInt(5, user.getRole());
            int inserted=stmt.executeUpdate();
            if(inserted==1) {
                System.out.println("Registered Successfully");
                val=true;
            }else {
                System.out.println("Error Occured");
                System.exit(0);
            }
        }else {
            System.out.println("User Already Exists");
            val=false;
        }
        return val;
    }


    public static void main(String[] args) throws Exception {
        User u1 = new User();
        u1.setEmail("yvesisite@gmail.com");
        u1.setPassword("pass123");
        UserServices us = new UserServices();
        System.out.println(us.findUser(u1).getPhone());
    }
}