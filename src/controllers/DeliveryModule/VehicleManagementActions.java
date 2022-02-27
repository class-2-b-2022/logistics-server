package controllers.DeliveryModule;

import Services.DeliveryModule.VehicleService;
import models.DeliveryModule.Vehicle;

import Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleManagementActions {
    private VehicleService vehicleService = new VehicleService();

    public List<Object> registerVehicle(Vehicle vehicle) throws SQLException {
        List<Object> vehicleObject = new ArrayList<>();
        vehicleService.insertIntoVehicles(vehicle);
        vehicleObject.add((Object) vehicle);
        return vehicleObject;
    }


    public List<Object> getVehicles(Integer vehicleId) throws SQLException {
        List<Object> vehiclesObject = getListOfVehicles();
        vehicleService.getListOfVehicles(vehicleId);

    }


}
