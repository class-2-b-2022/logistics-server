package controllers.user_management;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.user_model.User;
import services.UserService;

public class userActions {
	  UserService userService=new UserService();
	  public List<Object> registerUser(User user) throws SQLException{
        List<Object>newUser=new ArrayList<>();
        userService.insertUser(user);
        newUser.add((Object) user);
	    	 return newUser;
	     }
}
