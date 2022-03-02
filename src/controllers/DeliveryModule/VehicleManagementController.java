package controllers.DeliveryModule;

import models.DeliveryModule.Vehicle;
import models.ClientRequest;

import java.sql.Date;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class VehicleManagementController {
    public String mainMethod(ClientRequest clientRequest) throws Exception {
        String action = clientRequest.getAction();
        VehicleManagementActions actions = new VehicleManagementActions();
        Object responseObject = new Object();
        Vehicle vehicle = new Vehicle();
        switch(action){
            case "register":
                vehicle.setPlateNbr(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setBrand(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setDescription(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setOwner("owner");
                vehicle.setModel("model");
                vehicle.setCreatedAt(new Date(2020,02,03));
                System.out.println(vehicle.getPlateNbr());
                responseObject = actions.registerVehicle(vehicle);
                System.out.println(responseObject);
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
        return new ObjectMapper().writeValueAsString(responseObject);
    }
}
