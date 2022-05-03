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
            String status = resultSet.getString("Status");
            Integer quantity= resultSet.getInt("quantity");
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

            while(resultSet1.next()){
                productName = resultSet1.getString("productName");
            }
            while(resultSet2.next()){
                companyName = resultSet2.getString("name");
            }

            ReportModel reportModel = new ReportModel();
            reportModel.setCompanyName(companyName);
            reportModel.setProduct(productName);
            reportModel.setDate(date);
            reportModel.setStatus(status);
            reportModel.setQuantity(quantity);
            reportsObject.add((Object) reportModel);
        }
        return reportsObject;

    }
}
