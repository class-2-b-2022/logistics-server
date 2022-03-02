package controllers.DeliveryModule;

import Services.DeliveryModule.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import models.DeliveryModule.Vehicle;
import models.ResponseObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleManagementActions {
    private VehicleService vehicleService = new VehicleService();
    private ResponseObject responseObject = new ResponseObject();
    public Object registerVehicle(Vehicle vehicle) throws SQLException, JsonProcessingException {
        List<Object> vehicleObject = new ArrayList<>();
        vehicleService.insertIntoVehicles(vehicle);
        vehicleObject.add((Object) vehicle);
        responseObject.setStatus("200");
        responseObject.setMessage("Vehicle registered");
        responseObject.setData(vehicleObject);
        return responseObject;
    }

    public List<Object> getVehicles(Integer vehicleId) throws SQLException {
        return vehicleService.getListOfVehicles();
    }

    public List<Object> updatedVehicles(Vehicle vehicle) throws Exception {
        List<Object> responseObject = new ArrayList();
        if(vehicleService.findVehicleById(vehicle.getVehicleId())){
            responseObject = vehicleService.updateVehicle(vehicle);
        }
        return  responseObject;
    }



    public List<Object> deleteVehicle(Vehicle vehicle) throws Exception {
        List<Object> responseObject = new ArrayList();
        if(vehicleService.findVehicleById(vehicle.getVehicleId())){
            responseObject = vehicleService.deleteVehicle(vehicle);
        }
        return  responseObject;
    }

    public List<Object> getVehicles() throws SQLException {
       return vehicleService.getListOfVehicles();
    }

}
