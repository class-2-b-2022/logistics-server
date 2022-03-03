package services.user_services;
import models.user_model.User;
import utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
  
  private String hashPassword(String password){
      System.out.println(BCrypt.gensalt(12));
      return BCrypt.hashpw(password,BCrypt.gensalt(12));

  }
  
  public boolean insertUser(User user) throws Exception {
	   boolean userExists=checkIfUserExists(user.getEmail());
	   if(!userExists) {
		   String sql="INSERT INTO users(names,email,phone,password,role)values(?,?,?,?,?)";
		   String hashedPswd=hashPassword(user.getPassword());
		   PreparedStatement stmt=connection.prepareStatement(sql);
		   stmt.setString(1, user.getNames());
		   stmt.setString(2, user.getEmail());
		   stmt.setInt(3, user.getPhone());
		   stmt.setString(4,hashedPswd);
		   stmt.setInt(5, user.getRole());
		   int inserted=stmt.executeUpdate();
		   if(inserted==1) {
			  System.out.println("Registered Successfully");
			  return true;
		   }else {
			   System.out.println("Error Occured");
			   return false;
		   }
	   }else {
		   System.out.println("User Already Exists");
		   System.exit(0);
	   }
	   return false;
  }
  public void findUser(User user) throws  SQLException {
	   String sql="SELECT * FROM ";
	   PreparedStatement stmt=connection.prepareStatement(sql);
	   stmt.setString(1, user.getNames());
	   stmt.setString(2, user.getEmail());
	   stmt.setInt(3, user.getPhone());
	   stmt.setString(4,user.getPassword());
	   stmt.setInt(5, user.getRole());
	   stmt.executeUpdate();
  }
	public static void main(String[] args) {
		System.out.println("Hello World");

	}

}
