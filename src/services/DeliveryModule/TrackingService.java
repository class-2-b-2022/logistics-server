package services.DeliveryModule;

import models.DeliveryModule.Tracking;
import models.DeliveryModule.Vehicle;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackingService {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.getConnection();
    public void insertIntoTrackedVehicles(Tracking tracking) throws SQLException {
        String sql = "INSERT INTO tracked(driverName,currentLocation,sourceLocation,destination,fuelLevel,nearestStation,departureTime,arrivalTime) values(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tracking.getDriverName());
        preparedStatement.setString(2, tracking.getCurrentLocation());
        preparedStatement.setString(3, tracking.getSourceLocation());
        preparedStatement.setString(4, tracking.getDestination());
        preparedStatement.setString(5, tracking.getFuelLevel());
        preparedStatement.setString(6, tracking.getNearestStation());
        preparedStatement.setString(7, tracking.getDepartTime());
        preparedStatement.setString(8, tracking.getArriveTime());
        preparedStatement.setDate(8, tracking.getCreatedAt());
        preparedStatement.executeUpdate();
    }

    public List<Object> getTrackedVehicles() throws SQLException {
        List<Object> trackedVehicles = new ArrayList();
        String sql = "SELECT * FROM tracking";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        Tracking tracker = new Tracking();
        for(int i=0;i<20;i++){

            tracker.setTrackerId(i);
            tracker.setCurrentLocation("Nyabihu");
            tracker.setArriveTime("now");
            tracker.setDestination("Kigali");
            tracker.setDepartTime("then");
            tracker.setSourceLocation("Rubavu");
            tracker.setDriverName("Prince");
            tracker.setNearestStation("Musanze");
            trackedVehicles.add((Object) tracker);
        }


        return trackedVehicles;
    }

    public List<Object> updateVehicle(Vehicle vehicle)  throws Exception {
        List<Object> updatedVehicle = new ArrayList();
        String updateQuery = "UPDATE vehicles set model=?,plateNbr=?,brand=?,owner=?,description=? WHERE vehicleId=?";

        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

        preparedStatement.setString(1, vehicle.getModel());
        preparedStatement.setString(2, vehicle.getPlateNbr());
        preparedStatement.setString(3, vehicle.getBrand());
        preparedStatement.setString(4, vehicle.getOwner());
        preparedStatement.setString(5, vehicle.getDescription());
        preparedStatement.setInt(6, vehicle.getVehicleId());

        int rowsUpdated=preparedStatement.executeUpdate();
        if(rowsUpdated==1){
            updatedVehicle.add((Object) vehicle);
        }
        return updatedVehicle;
    }

    public boolean findVehicleById(Integer vehicleId) throws  Exception{
        String sql="SELECT * FROM vehicles WHERE vehicleId=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,vehicleId);
        ResultSet result=preparedStatement.executeQuery();
        boolean vehicleExists=false;
        if(result.next()){
            vehicleExists=true;
        }
        return vehicleExists;
    }

    public List<Object> deleteVehicle(Vehicle vehicle) throws Exception{
        List<Object> deletedVehicle = new ArrayList();
        String deleteSQL = "DELETE FROM vehicles WHERE vehicleId=?";

        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
        preparedStatement.setInt(1,vehicle.getVehicleId());

        int delete = preparedStatement.executeUpdate();

        if (delete == 1) {
            deletedVehicle.add(vehicle);
        }
        return deletedVehicle;
    }

}