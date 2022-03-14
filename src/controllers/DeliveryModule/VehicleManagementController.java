package controllers.DeliveryModule;

import models.DeliveryModule.Vehicle;
import models.ClientRequest;

import java.sql.Date;
import com.fasterxml.jackson.databind.ObjectMapper;


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
                vehicle.setModel(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setCreatedAt(new Date(2020,02,03));
                responseObject = actions.registerVehicle(vehicle);
                break;
            case "view":
                responseObject = actions.getVehicles();
                break;
            case "update":
                vehicle.setPlateNbr(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setBrand(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setOwner(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setDescription(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setModel(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setVehicleId(Integer.valueOf(clientRequest.getData().next().toString().split("=")[1]));
                String createdAt = clientRequest.getData().next().toString().split("=")[1];
                vehicle.setCreatedAt(new Date(2020,02,03));
                responseObject = actions.updatedVehicles(vehicle);
                break;
            case "delete":
                vehicle.setPlateNbr(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setBrand(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setOwner(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setDescription(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setModel(clientRequest.getData().next().toString().split("=")[1]);
                String created = clientRequest.getData().next().toString().split("=")[1];
                vehicle.setVehicleId(Integer.valueOf(clientRequest.getData().next().toString().split("=")[1]));
                responseObject = actions.deleteVehicle(vehicle);
                break;
        }
        return new ObjectMapper().writeValueAsString(responseObject);
    }
}