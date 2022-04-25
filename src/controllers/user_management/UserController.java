
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
        User user = null;
        if(!req.getAction().equals("GET")){
            user = parse.parseData(req.getData(), User.class);
        }
        Object res = new Object();
        switch (action) {
            case "register":
                res =UserActions.registerUser(user);
                break;
            case "users":
            	 res= UserActions.getUsers();
            	break;	
            case "GET":
           	 res= UserActions.getUser(Integer.parseInt(req.getData().toString()));
           	break;
            case "login":
                res = UserActions.loginUser(user);
                break;
            case "updateUser":
                res = UserActions.updateUser(user);
                break;
            case "deleteuser":
                res=UserActions.deleteUser(user);
                break;
        }
        return new ObjectMapper().writeValueAsString(res);
   }
}
