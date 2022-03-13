package controllers.user_management;

import models.ResponseObject;
import models.user_model.User;
import java.util.ArrayList;
import java.util.List;

import Services.user_services.UserServices;

/***
 * @author: Isite Yves
 * @author: Ntagungira Ali Rashid
 */

public class userActions {
	 /**
     * @author Ntagungira Ali Rashid
     */
    private ResponseObject resObj = new ResponseObject();
    UserServices userService = new UserServices();
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
     public List<Object> loginUser(User user) throws Exception {
         System.out.println("User info..."+user.getEmail());
         List<Object> userObject = new ArrayList<>();
         User foundUser = userService.findUser(user);
         userObject.add((Object) foundUser);
         return userObject;
     }
}
