package services.ReportModule;

import models.ReportModel;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class ReportService {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.getConnection();

    public List<Object> getListOfInventoryReports() throws SQLException{
        List<Object> reportsObject = new ArrayList<>();
        String sql = "SELECT * FROM Inventory";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);


        while(resultSet.next()){
            Integer TIN = resultSet.getInt("companyTIN");
            Integer productId = resultSet.getInt("productId");
            Date date = resultSet.getDate("Date");

            String getProductName = "SELECT * FROM products WHERE productId = "+productId+";";
            String getCompanyName = "SELECT * FROM companies WHERE TIN = "+ TIN +";";

            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();

            ResultSet resultSet1 = statement1.executeQuery(getProductName);
            ResultSet resultSet2 = statement2.executeQuery(getCompanyName);

            String companyName = null;
            String productName = null;

            ReportModel reportModel = new ReportModel();
            reportModel.setDate(resultSet.getDate("Date"));

            while(resultSet1.next()){
                reportModel.setProduct(resultSet1.getString("productName"));
            }

            reportModel.setStatus(resultSet.getString("Status"));
            reportModel.setQuantity(resultSet.getInt("quantity"));

            while(resultSet2.next()){
                reportModel.setCompanyName(resultSet2.getString("name"));
            }
            reportsObject.add( reportModel);
        }
        return reportsObject;

    }
}
