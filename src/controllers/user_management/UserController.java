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
        }
        return new ObjectMapper().writeValueAsString(res);
   }
}
