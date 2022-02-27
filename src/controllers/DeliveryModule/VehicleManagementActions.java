package controllers.DeliveryModule;

import Services.DeliveryModule.VehicleService;
import models.DeliveryModule.Vehicle;

import Utils.DatabaseConnection;

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
        vehicleService.getListOfVehicles();

    }

    public List<Object> updatedVehicles(Vehicle vehicle) throws Exception {
        if(vehicleService.findVehicleById(vehicle.gegit))
    }



    public void deleteVehicle(Integer vehicleId) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String deleteSQL = "DELETE FROM vehicles WHERE id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
        int delete = preparedStatement.executeUpdate();

        if (delete == 1) {
//            return new ResponseStatus(200,"VEHICLE DELETED","You have updated the vehicles");
            System.out.println("Vehicle deleted successfully");
        }
    }


}
