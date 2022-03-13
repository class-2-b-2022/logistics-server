package controllers.user_management;
import models.ClientRequest;
import models.user_model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
public class userController {
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
                user.setPhone(Integer.parseInt(req.getData().next().toString().split("=")[1].trim()));
                user.setPassword(req.getData().next().toString().split("=")[1].trim());
                user.setRole(Integer.parseInt(req.getData().next().toString().split("=")[1]));
                res =UserActions.registerUser(user);
                break;
            case "view":
            	break;
            case "login":
                res = UserActions.loginUser((User) req.getData());
        }
        return new ObjectMapper().writeValueAsString(res);
   }
}
