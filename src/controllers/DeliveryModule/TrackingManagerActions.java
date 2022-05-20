package controllers.DeliveryModule;
import services.DeliveryModule.TrackingService;
import services.DeliveryModule.VehicleService;
import models.DeliveryModule.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackingManagerActions<ResponseStatus> {
    private TrackingService trackingService = new TrackingService();

    public List<Object> trackVehicle(Tracking tracking) throws SQLException {
        List<Object> vehicleObject = new ArrayList<>();
        trackingService.insertIntoTrackedVehicles(tracking);
        vehicleObject.add((Object) tracking);
        return vehicleObject;
    }

    public List<Object> getVehicles(Integer vehicleId) throws SQLException {
        return trackingService.getTrackedVehicles();
    }

    public List<Object> updatedVehicles(Vehicle vehicle) throws Exception {
        List<Object> responseObject = new ArrayList();
        if(trackingService.findVehicleById(vehicle.getVehicleId())){
            responseObject = trackingService.updateVehicle(vehicle);
        }
        return  responseObject;
    }



    public List<Object> deleteVehicle(Vehicle vehicle) throws Exception {
        List<Object> responseObject = new ArrayList();
        if(trackingService.findVehicleById(vehicle.getVehicleId())){
            responseObject = trackingService.deleteVehicle(vehicle);
        }
        return  responseObject;
    }

    public List<Object> getTrackedVehicles() throws SQLException {
        return trackingService.getTrackedVehicles();
    }

}