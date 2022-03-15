package controllers.DeliveryModule;

import Services.DeliveryModule.*;
import models.DeliveryModule.Vehicle;
import models.ResponseObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleManagementActions {
    private VehicleService vehicleService = new VehicleService();
    private ResponseObject responseObject = new ResponseObject();
   
    public Object registerVehicle(Vehicle vehicle) throws SQLException {
        List<Object> vehicleObject = new ArrayList<>();
        vehicleService.insertIntoVehicles(vehicle);
        vehicleObject.add((Object) vehicle);
        responseObject.setStatus("200");
        responseObject.setMessage("Vehicle registered");
        responseObject.setData(vehicleObject);
        return responseObject;
    }

    public Object getVehicles() throws SQLException {
        List<Object> vehicles =  vehicleService.getListOfVehicles();
        responseObject.setStatus("201");
        responseObject.setMessage("Vehicles");
        responseObject.setData(vehicles);
        return responseObject;
    }

    public Object updatedVehicles(Vehicle vehicle) throws Exception {
        List<Object> vehicles = new ArrayList<Object>();
        if(vehicleService.findVehicleById(vehicle.getVehicleId())){
            vehicles = vehicleService.updateVehicle(vehicle);
            responseObject.setStatus("200");
            responseObject.setMessage("Vehicle updated successfully");
            responseObject.setData(vehicles);
        }
        else{
            responseObject.setStatus("404");
            responseObject.setMessage("Vehicle not found");
            responseObject.setData(vehicles);
        }
        return responseObject;
    }

    public Object deleteVehicle(Vehicle vehicle) throws Exception {
        List<Object> vehicles = new ArrayList<Object>();
        if(vehicleService.findVehicleById(vehicle.getVehicleId())){
            vehicles = vehicleService.deleteVehicle(vehicle);
            responseObject.setStatus("200");
            responseObject.setMessage("Vehicle deleted successfully");
            responseObject.setData(vehicles);
        }
        else{
            responseObject.setStatus("404");
            responseObject.setMessage("Vehicle not found");
            responseObject.setData(vehicles);
        }
        return responseObject;
    }

}
