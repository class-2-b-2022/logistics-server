package controllers.user_management;

import models.ResponseObject;
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
    /**
     * @author Ntagungira Ali Rashid
     */
    private ResponseObject resObj = new ResponseObject();
    UserService userService = new UserService();

    public List<Object> registerUser(User user) throws Exception {
        List<Object> newUser = new ArrayList<>();
        userService.insertUser(user);
        newUser.add((Object) user);
        resObj.setStatus("200");
        resObj.setMessage("User registered successfully");
        resObj.setData(newUser);
        return newUser;
    }

    /**
     * @author: Isite Yves
     */
    public Object loginUser(User user) throws Exception {
        List<Object> users = new ArrayList();
        users.add((Object) userService.findUser(user));
        resObj.setStatus("200");
        resObj.setMessage("User info processed successfully.");
        resObj.setData(users);
        return resObj;
    }

    public Object deleteUser(User user) throws Exception {
        List<Object> deletedUser = new ArrayList();
        deletedUser.add((Object) userService.deleteUser(user));
        resObj.setStatus("200");
        resObj.setMessage("User info processed successfully.");
        resObj.setData(deletedUser);
        return resObj;
    }
}
