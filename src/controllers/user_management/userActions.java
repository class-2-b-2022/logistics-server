package controllers.user_management;
import models.ResponseBody;
import models.ResponseObject;
import models.user_model.User;
import services.user_services.UserService;

import java.util.ArrayList;
import java.util.List;
/***
 * @author: Isite Yves
 * @author: Ntagungira Ali Rashid
 */

public class userActions {

    private ResponseObject resObj = new ResponseObject();
    private ResponseBody resbody = new ResponseBody();
    UserService userService = new UserService();

    public Object registerUser(User user) throws Exception {
        List<Object> newUser = new ArrayList<Object>();
        userService.insertUser(user);
        newUser.add((Object) user);
        resObj.setStatus("200");
        resObj.setMessage("User registered successfully");
        resObj.setData(newUser);
        return resObj;  
    }

    /**
     * @author: Isite Yves
     */
    public Object loginUser(User user) throws Exception {
//        List<Object> users = new ArrayList<Object>();
        User loggedInUser=userService.findUser(user);
//        users.add((Object) loggedInUser);
        if(loggedInUser.getEmail() == null) {
            resbody.setStatus("400");
            resbody.setMessage("Invalid email or password.");
        }else{
                resbody.setStatus("200");
                resbody.setMessage("logged in correctly.");
        }
        resbody.setData(loggedInUser);
        return resbody;
    }

    public Object updateUser(User user) throws Exception {
        List<Object> updatedUser = new ArrayList<Object>();
        User updatedUserInfo=userService.updateUser(user);
        updatedUser.add((Object) updatedUserInfo);
        if(updatedUserInfo.getEmail() == null) {
            resbody.setStatus("400");
            resbody.setMessage("New email was already taken by another user.");
        }else{
            resbody.setStatus("200");
            resbody.setMessage("The User's info were successfully updated.");
            System.out.println("The User's info were successfully updated.");
        }
        resbody.setData(updatedUser);
        return resbody;
    }

    public Object deleteUser(User user) throws Exception {
        List<Object> deletedUser = new ArrayList<Object>();
        User deletedUserInfo=userService.deleteUser(user);
        deletedUser.add((Object) deletedUserInfo);
        if(deletedUserInfo.getEmail() == null) {
            resbody.setStatus("400");
            resbody.setMessage("User not found in the database.");
            System.out.println("User not found in the database.");
        }else{
            resbody.setStatus("200");
            resbody.setMessage("The User was successfully deleted from the database.");
            System.out.println("The User was successfully deleted from the database.");
        }
        resbody.setData(deletedUser);
        return resbody;
    }
}
