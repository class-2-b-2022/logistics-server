package controllers.DeliveryModule;

import models.ClientRequest;
import models.DeliveryModule.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleManagementController {
    public List<Object> mainMethod(ClientRequest clientRequest) throws SQLException {
        String action = clientRequest.getAction();
        VehicleManagementActions actions = new VehicleManagementActions();
        List<Object> responseObject = new ArrayList();

        switch(action){
            case "register":
                responseObject = actions.registerVehicle( (Vehicle) clientRequest.getData());
                break;
            case "view":
                responseObject = actions.getVehicles();
                break;
            case "update":
                //method in vmActions
                break;
            case "delete":
                //method in vmActions
                break;
        }
        return responseObject;
    }
}
