
package controllers.user_management;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.ClientRequest;
import models.user_model.User;
import utils.ParserObj;

public class UserController {
	/**
	 * @Author Ntagungira Ali
	 */
    public String mainMethod(ClientRequest req) throws Exception {
        String action = req.getAction();
        userActions UserActions = new userActions();
        ParserObj parse = new ParserObj();
        User user=parse.parseData(req.getData(), User.class);
        Object res = new Object();
        switch (action) {
            case "register":
<<<<<<< HEAD
                user.setNames(req.getData().next().toString().split("=")[1].replace("\"", ""));
                user.setEmail(req.getData().next().toString().split("=")[1].replace("\"", ""));
                user.setPhone(Integer.parseInt(req.getData().next().toString().split("=")[1].replace("\"", "")));
                user.setPassword(req.getData().next().toString().split("=")[1].replace("\"", ""));
                user.setRole(Integer.parseInt(req.getData().next().toString().split("=")[1].replace("\"", "")));
                res = UserActions.registerUser(user);
=======
                res =UserActions.registerUser(user);
>>>>>>> 768c3f3addc3d44775ad4f3e9ad1125eefcef0d8
                break;
            case "profile":
            	break;
            case "login":
<<<<<<< HEAD
                user.setEmail(req.getData().next().toString().split("=")[1].replace("\"", ""));
                user.setPassword(req.getData().next().toString().split("=")[1].replace("\"", ""));
=======
>>>>>>> 768c3f3addc3d44775ad4f3e9ad1125eefcef0d8
                res = UserActions.loginUser(user);
                break;
            case "updateUser":
//                String userIdAsString=req.getData().next().toString().split("=")[1];
//                String userIdWithNoQuotes=userIdAsString.substring(1,userIdAsString.length()-1);
//                user.setUserId(Integer.parseInt(userIdWithNoQuotes));
//                user.setEmail(req.getData().next().toString().split("=")[1]);
//                user.setNames(req.getData().next().toString().split("=")[1]);
//                user.setEmail(user.getEmail().substring(1,user.getEmail().length()-1));
//                user.setNames(user.getNames().substring(1,user.getNames().length()-1));
//                String phoneAsString=req.getData().next().toString().split("=")[1];
//                String phoneWithNoQuotes=phoneAsString.substring(1,phoneAsString.length()-1);
//                user.setPhone(Integer.parseInt(phoneWithNoQuotes));
//                String roleAsString=req.getData().next().toString().split("=")[1];
//                String roleWithNoQuotes=roleAsString.substring(1,roleAsString.length()-1);
//                user.setRole(Integer.parseInt(roleWithNoQuotes));
//                user.setEmail(user.getEmail().substring(1,user.getEmail().length()-1));
//                user.setPassword(user.getPassword().substring(1,user.getPassword().length()-1));
                res = UserActions.updateUser(user);
                break;
            case "deleteuser":
                res=UserActions.deleteUser(user);
                break;
        }
        return new ObjectMapper().writeValueAsString(res);
   }
}
