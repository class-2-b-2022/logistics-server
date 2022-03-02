package controllers.user_management;
import models.ClientRequest;
import models.user_model.User;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    public static List<Object> mainMethod(ClientRequest req) throws Exception {
        String action = req.getAction();
        userActions UserActions = new userActions();
        List<Object> res = new ArrayList<Object>();
        switch (action) {
            case "register":
                res = UserActions.registerUser((User) req.getData());
                break;
            case "login":
               // res = UserActions.loginUser((User) req.getData());
        }
        return res;
    }
}
