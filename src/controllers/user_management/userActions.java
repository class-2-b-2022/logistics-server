package controllers.user_management;

import models.user_model.User;
import services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/***
 @author: Isite Yves
 @author: Ntagungira Ali Rashid
 */
public class userActions {
    UserService userService = new UserService();

    public List<Object> registerUser(User user) {
        List<Object> newUser = new ArrayList<>();
        newUser.add((Object) user);
        return newUser;
    }

    public List<Object> loginUser(User user) throws SQLException {
        List<Object> userToLogin = new ArrayList<>();
        userService.findUser(user);
        userToLogin.add((Object) user);
        return userToLogin;
    }
}
