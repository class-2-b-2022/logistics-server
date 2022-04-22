package controllers.DeliveryModule;

import models.DeliveryModule.*;
import models.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleManagementController {
    public List<Object> mainMethod(ClientRequest clientRequest) throws Exception {
        String action = clientRequest.getAction();
        VehicleManagementActions actions = new VehicleManagementActions();
        List<Object> responseObject = new ArrayList<>();

        switch(action){
            case "register":
                responseObject = actions.registerVehicle( (Vehicle) clientRequest.getData());
                break;
            case "view":
                responseObject = actions.getVehicles();
                break;
            case "update":
                responseObject = actions.updatedVehicles((Vehicle) clientRequest.getData());
                break;
            case "delete":
                responseObject = actions.deleteVehicle((Vehicle) clientRequest.getData());
                break;
        }
        return responseObject;
    }
}
