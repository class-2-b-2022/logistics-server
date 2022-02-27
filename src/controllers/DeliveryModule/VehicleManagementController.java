package controllers.DeliveryModule;

import models.ClientRequest;

import java.util.List;

public class VehicleManagementController {
    public void mainMethod(ClientRequest clientRequest){
        String action = clientRequest.getAction();

        switch(action){
            case "register":
                ///
                break;
            case "view":
                //method in vMactions
                break;
            case "update":
                //method in vmActions
                break;
            case "delete":
                //method in vmActions
                break;
        }
    }
}
