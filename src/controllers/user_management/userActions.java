package controllers.user_management;
import java.util.ArrayList;
import java.util.List;
import models.user_model.User;
import services.user_services.UserService;


public class userActions {

	  UserService userService=new UserService();
	  public List<Object> registerUser(User user) throws Exception{
        List<Object>newUser=new ArrayList<>();
        userService.insertUser(user);
        newUser.add((Object) user);
        return newUser;
    }

    public List<Object> loginUser(User user) {
        List<Object> userToLogin = new ArrayList<>();
        userToLogin.add((Object) user);
        return userToLogin;
    }
}
