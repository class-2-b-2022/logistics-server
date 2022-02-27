package controllers.user_management;

import java.util.ArrayList;
import java.util.List;

import models.user_model.User;
import services.UserService;

public class userActions {
	  UserService userService=new UserService();
	  public List<Object> registerUser(User user){
        List<Object>newUser=new ArrayList<>();
        newUser.add((Object) user);
	    	 return newUser;
	     }
}
