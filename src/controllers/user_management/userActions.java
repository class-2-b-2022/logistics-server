package controllers.user_management;

import models.user_model.User;
import services.UserService;

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

    /**
     *
     * @author: Isite Yves
     */
    public List<Object> loginUser(User user) throws SQLException {
        List<Object> userToLogin = new ArrayList<>();
        User foundUser = userService.findUser(user);
        userToLogin.add((Object) user);
        return userToLogin;
    }
}
