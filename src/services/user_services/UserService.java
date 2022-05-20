package services.user_services;

import models.user_model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.getConnection();

    private String hashPassword(String password) {
        //       System.out.println(BCrypt.gensalt(12));
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public User findUser(User user) throws Exception {
        User returnObject = new User();
        if (isEmailRegistered(user.getEmail()).getNames() == null)
            System.out.println("Unregistered email.");
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
        String sql = "SELECT names,phone,password FROM users WHERE email=? LIMIT 1";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        String dbPswd;
        while (rs.next()) {
            dbPswd=rs.getString(3);
            returnObject.setRoleAsString(getRoleAsString(email));
            boolean arePswdsTheSame=BCrypt.checkpw(password,dbPswd);
            if(arePswdsTheSame) {
                returnObject.setNames(rs.getString(1));
                returnObject.setEmail(email);
                returnObject.setPhone(rs.getInt(2));
            }
            break;
        }
        return returnObject;
    }
    public User isEmailRegistered(String email) throws SQLException {
        User returnObject = new User();
        String sql = "SELECT names,phone FROM users WHERE email=? LIMIT 1";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
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
        User returnObject;
        returnObject = isEmailRegistered(user.getEmail());
        String sql = "DELETE FROM users WHERE email=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getEmail());
        stmt.execute();
        return returnObject;
    }

    public User updateUser(User user) throws  SQLException {
        User returnObject;
        returnObject = isEmailRegistered(user.getEmail());
        if(returnObject.getEmail() != null) {
            String sql = "UPDATE users SET email=?,names=?,phone=?,role=? WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getNames());
            stmt.setInt(3, user.getPhone());
            stmt.setInt(4, user.getRole());
            stmt.setInt(5, user.getUserId());
            stmt.executeUpdate();
            return user;
        }else{
            return returnObject;
        }
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

    public boolean insertUser(User user) throws Exception {
        boolean userExists = checkIfUserExists(user.getEmail());
        if (!userExists) {
            String sql = "INSERT INTO users(names,email,phone,password,role)values(?,?,?,?,?)";
            String hashedPswd = hashPassword(user.getPassword());
//           System.out.println(BCrypt.checkpw(user.getPassword(),"$2a$12$Da8MomfA5DXHcJpGXnX7e.VNne3B7NDHGcJlU3dFL7cMd0efQ1DLC"));
//           System.out.println(hashedPswd);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getNames());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getPhone());
            stmt.setString(4,hashedPswd);
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


    public Object getUser(Integer id) {
        return id;
    }
}