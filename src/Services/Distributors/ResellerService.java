package services.Distributors;

import models.Distributors.Reseller;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResellerService {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.getConnection();

    public void insertIntoResellers(Reseller reseller) throws SQLException {
        String sql = "INSERT INTO resellers(first_name,last_name,telephone,email,business,createdAt) values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, reseller.getFirst_name());
        preparedStatement.setString(2, reseller.getLast_name());
        preparedStatement.setInt(3, reseller.getTelephone());
        preparedStatement.setString(4, reseller.getEmail());
        preparedStatement.setString(5, reseller.getBusiness_name());
        preparedStatement.setDate(6,reseller.getCreatedAt());
        preparedStatement.executeUpdate();
    }

    public List<Object> getListOfResellers() throws SQLException {
        List<Object> resellersObject = new ArrayList();
        String sql = "SELECT * FROM resellers";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while(result.next()){
            String first_name = result.getString("first_name");
            String last_name = result.getString("last_name");
            Integer telephone = result.getInt("telephone");
            String email = result.getString("email");
            String business_name = result.getString("business_name");
            Date Date = result.getDate("createdAt");

            String output = "User #%d: %s -%s - %s -%s";
            System.out.println(String.format(output,++count,first_name,last_name,telephone,email,business_name));
        }
        return resellersObject;
    }

    public List<Object> updateResellers(Reseller reseller)  throws Exception {
        List<Object> updatedReseller = new ArrayList();
        String sql = " UPDATE resellers SET first_name=?, last_name=?, telephone=?, email=?, business_name=? WHERE first_name=?,last_name=? && business_name=? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, reseller.getFirst_name());
        statement.setString(2, reseller.getLast_name());        statement.setString(1, reseller.getFirst_name());
        statement.setInt(3, reseller.getTelephone());
        statement.setString(4, reseller.getEmail());
        statement.setString(5, reseller.getBusiness_name());
        //        attention! will statements for where conditions

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("The user has been updated successful");
        }

        return updatedReseller;
    }

    public List<Object> deleteResellers(Reseller reseller)  throws Exception {
        List<Object> deletedReseller = new ArrayList();
        String sql = "DELETE FROM resellers WHERE first_name=?, last_name=?, business_name=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,"");
        statement.setString(2,"");
        statement.setString(3,"");

        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0){
            System.out.println("User deleted successfully");
        }

        return deletedReseller;
    }

}
