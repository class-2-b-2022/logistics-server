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
                res =UserActions.registerUser(user);
                break;
            case "profile":
                break;
            case "login":
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
<<<<<<< HEAD
    }
=======
   }
>>>>>>> 62e47edbd1416f80b555002075c9f09bcefd4e81
}