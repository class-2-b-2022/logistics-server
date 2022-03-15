package controllers.user_management;
import models.ClientRequest;
import models.user_model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController {
	/**
	 * @Author Ntagungira Ali
	 */
    public static String mainMethod(ClientRequest req) throws Exception {
        String action = req.getAction();
        userActions UserActions = new userActions();
        Object res = new Object();
        User user=new User();
        
        switch (action) {
            case "register":
               user.setNames(req.getData().next().toString().split("=")[1]);
               user.setEmail(req.getData().next().toString().split("=")[1]);
               user.setPhone(Integer.parseInt(req.getData().next().toString().split("=")[1]));
               user.setPassword(req.getData().next().toString().split("=")[1]);
               user.setRole(Integer.parseInt(req.getData().next().toString().split("=")[1]));
               res =UserActions.registerUser(user);
                break;
            case "view":
            	break;
            case "login":
               user.setEmail(req.getData().next().toString().split("=")[1]);
               user.setPassword(req.getData().next().toString().split("=")[1]);
               user.setEmail(user.getEmail().substring(1,user.getEmail().length()-1));
               user.setPassword(user.getPassword().substring(1,user.getPassword().length()-1));
               res = UserActions.loginUser(user);
                break;
            case "updateUser":
               String userIdAsString=req.getData().next().toString().split("=")[1];
               String userIdWithNoQuotes=userIdAsString.substring(1,userIdAsString.length()-1);
               user.setUserId(Integer.parseInt(userIdWithNoQuotes));
               user.setEmail(req.getData().next().toString().split("=")[1]);
               user.setNames(req.getData().next().toString().split("=")[1]);
               user.setEmail(user.getEmail().substring(1,user.getEmail().length()-1));
               user.setNames(user.getNames().substring(1,user.getNames().length()-1));
               String phoneAsString=req.getData().next().toString().split("=")[1];
               String phoneWithNoQuotes=phoneAsString.substring(1,phoneAsString.length()-1);
               user.setPhone(Integer.parseInt(phoneWithNoQuotes));
               String roleAsString=req.getData().next().toString().split("=")[1];
               String roleWithNoQuotes=roleAsString.substring(1,roleAsString.length()-1);
               user.setRole(Integer.parseInt(roleWithNoQuotes));
               user.setEmail(user.getEmail().substring(1,user.getEmail().length()-1));
               user.setPassword(user.getPassword().substring(1,user.getPassword().length()-1));
               res = UserActions.updateUser(user);
                break;
            case "deleteUser":
               user.setEmail(req.getData().next().toString().split("=")[1]);
               user.setEmail(user.getEmail().substring(1,user.getEmail().length()-1));
               res=UserActions.deleteUser(user);
               break;
        }
        return new ObjectMapper().writeValueAsString(res);
   }
}