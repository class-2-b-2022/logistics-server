package services.Distributors;

import models.Distributors.Reseller;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
