package controllers.user_management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.ClientRequest;
import models.user_model.User;

public class UserController {
	public static List<Object> mainMethod(ClientRequest req)throws IOException{
		String action=req.getAction();
		userActions UserActions=new userActions();
		List <Object>res=new ArrayList();
		try {
			switch(action) {
			case "register":
				res=UserActions.registerUser((User) req.getData());
				break;
			}
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return res;
	}
}
