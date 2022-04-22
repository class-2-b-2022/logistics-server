package controllers.CompanyModule;

import models.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyManagementController {

    public List<Object> mainMethod(ClientRequest clientRequest) throws Exception {
        String action = clientRequest.getAction();
        CompanyManagementActions actions = new CompanyManagementActions();
        List<Object> responseObject = new ArrayList<>();
        switch (action){
            case "register":
                responseObject = actions.registerCompany( (Company) clientRequest.getData());
                break;
            case "view":
                responseObject= actions.getVehicles();
                break;
        }
        return responseObject;
    }
}
