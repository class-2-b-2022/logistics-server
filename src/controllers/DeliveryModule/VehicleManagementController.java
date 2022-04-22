package controllers.DeliveryModule;

<<<<<<< HEAD
import models.DeliveryModule.Vehicle;
import models.user_model.User;
import utils.ParserObj;
import models.ClientRequest;

import java.sql.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
=======
import models.DeliveryModule.*;
import models.*;
import java.util.ArrayList;
import java.util.List;
>>>>>>> de9f252aa394f2d7ca7aceb028c578bbd4ab9120

public class VehicleManagementController {
    public String mainMethod(ClientRequest clientRequest) throws Exception {
        String action = clientRequest.getAction();
        ParserObj parse = new ParserObj();
        VehicleManagementActions actions = new VehicleManagementActions();
<<<<<<< HEAD
        Object responseObject = new Object();
        Vehicle vehicle = parse.parseData(clientRequest.getData(), Vehicle.class);
        switch (action) {
=======
        List<Object> responseObject = new ArrayList<>();

        switch(action){
>>>>>>> de9f252aa394f2d7ca7aceb028c578bbd4ab9120
            case "register":
//                vehicle.setPlateNbr(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setBrand(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setDescription(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setOwner("owner");
                vehicle.setModel("model");
                vehicle.setCreatedAt(new Date(2020,02,03));
                responseObject = actions.registerVehicle(vehicle);
                break;
            case "view":
                responseObject = actions.getVehicles();
                break;
            case "update":
//                vehicle.setVehicleId(Integer.valueOf(clientRequest.getData().next().toString().split("=")[1]));
//                vehicle.setPlateNbr(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setBrand(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setDescription(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setOwner("owner");
                vehicle.setModel("model");
                vehicle.setCreatedAt(new Date(2020, 02, 03));
                responseObject = actions.updatedVehicles(vehicle);
                break;
            case "delete":
//                vehicle.setVehicleId(Integer.valueOf(clientRequest.getData().next().toString().split("=")[1]));
//                vehicle.setPlateNbr(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setBrand(clientRequest.getData().next().toString().split("=")[1]);
//                vehicle.setDescription(clientRequest.getData().next().toString().split("=")[1]);
                vehicle.setOwner("owner");
                vehicle.setModel("model");
                vehicle.setCreatedAt(new Date(2020, 02, 03));
                responseObject = actions.deleteVehicle(vehicle);
                break;
        }
        return new ObjectMapper().writeValueAsString(responseObject);
    }
}