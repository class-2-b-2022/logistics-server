package controllers.user_management;

import models.user_model.User;
import services.user_services.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***
 * @author: Isite Yves
 * @author: Ntagungira Ali Rashid
 */

public class userActions {

    UserService userService = new UserService();

    public List<Object> registerUser(User user) throws Exception {
        List<Object> newUser = new ArrayList<>();
        userService.insertUser(user);
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
