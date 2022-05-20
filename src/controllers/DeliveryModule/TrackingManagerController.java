package controllers.DeliveryModule;

import models.DeliveryModule.Tracking;
import models.DeliveryModule.Vehicle;
import models.user_model.User;
import utils.ParserObj;
import models.ClientRequest;

import java.sql.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TrackingManagerController {
    public static String mainMethod(ClientRequest clientRequest) throws Exception {
        String action = clientRequest.getAction();
        ParserObj parse = new ParserObj();
        TrackingManagerActions actions = new TrackingManagerActions();
        Object responseObject = new Object();
        Tracking tracking = parse.parseData(clientRequest.getData(), Tracking.class);
        switch (action) {
            case "register":
//                vehicle.setPlateNbr(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setBrand(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setDescription(clientRequest.getData().next().toString().split("=")[1]);

                tracking.setCreatedAt(new Date(2020,02,03));
                responseObject = actions.trackVehicle(tracking);
                break;
            case "view":
                responseObject = actions.getTrackedVehicles();
                break;
            case "update":
//                vehicle.setVehicleId(Integer.valueOf(clientRequest.getData().next().toString().split("=")[1]));
//                vehicle.setPlateNbr(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setBrand(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setDescription(clientRequest.getData().next().toString().split("=")[1]);
/*                vehicle.setOwner("owner");
                vehicle.setModel("model");
                vehicle.setCreatedAt(new Date(2020, 02, 03));
                responseObject = actions.updatedVehicles(vehicle);*/
                break;
            case "delete":
//                vehicle.setVehicleId(Integer.valueOf(clientRequest.getData().next().toString().split("=")[1]));
//                vehicle.setPlateNbr(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setBrand(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setDescription(clientRequest.getData().next().toString().split("=")[1]);
               /* vehicle.setOwner("owner");
                vehicle.setModel("model");
                vehicle.setCreatedAt(new Date(2020, 02, 03));
                responseObject = actions.deleteVehicle(vehicle);*/
                break;
        }
        return new ObjectMapper().writeValueAsString(responseObject);
    }
}