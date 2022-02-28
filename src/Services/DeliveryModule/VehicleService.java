package services.DeliveryModule;

import utils.DatabaseConnection;
import models.DeliveryModule.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleService {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.getConnection();

    public void insertIntoVehicles(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicles(model,plateNbr,brand,owner,description,createdAt) values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, vehicle.getModel());
        preparedStatement.setString(2, vehicle.getPlateNbr());
        preparedStatement.setString(3, vehicle.getBrand());
        preparedStatement.setString(4, vehicle.getOwner());
        preparedStatement.setString(5, vehicle.getDescription());
        preparedStatement.setDate(6, vehicle.getCreatedAt());
        preparedStatement.executeUpdate();
    }

    public List<Object> getListOfVehicles() throws SQLException {
        List<Object> vehiclesObject = new ArrayList();
        String sql = "SELECT * FROM vehicles";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
            Vehicle vehicle = new Vehicle();
            vehicle.setModel(result.getString("model"));
            vehicle.setPlateNbr(result.getString("plateNbr"));
            vehicle.setBrand(result.getString("brand"));
            vehicle.setOwner(result.getString("owner"));
            vehicle.setDescription(result.getString("description"));
            vehicle.setCreatedAt(result.getDate("createdAt"));
            vehiclesObject.add((Object) vehicle);
        }
        return vehiclesObject;
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
        preparedStatement.setDate(6, vehicle.getCreatedAt());

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
