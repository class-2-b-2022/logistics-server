package controllers.DeliveryModule;
import services.DeliveryModule.VehicleService;
import models.DeliveryModule.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleManagementActions<ResponseStatus> {
    private VehicleService vehicleService = new VehicleService();

    public List<Object> registerVehicle(Vehicle vehicle) throws SQLException {
        List<Object> vehicleObject = new ArrayList<>();
        vehicleService.insertIntoVehicles(vehicle);
        vehicleObject.add((Object) vehicle);
        return vehicleObject;
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
