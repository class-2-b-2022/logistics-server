package services;

import models.ReportModel;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportService {
//    connecting to db
    private static DatabaseConnection databaseConnection = new DatabaseConnection();
    static Connection connection = databaseConnection.getConnection();

    public static List<Object> getReportInformation() throws SQLException {
        List<Object> reportsObject = new ArrayList();
        String sql = "SELECT * FROM Inventory";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            Date date =result.getDate("Date");
            String status =result.getString("Status");
            Integer quantity = result.getInt("quantity");
            Integer tin = (result.getInt("companyTIN"));
            String company = "Select * from companies where TIN = "+tin+";";
            PreparedStatement statement2 = connection.prepareStatement(company);
            ResultSet resultSet1 = statement2.executeQuery();
            String companyName = null;
            while(resultSet1.next()){
                companyName = resultSet1.getString("name");
            }
            Integer productId = (result.getInt("productId"));
            String product = "select * from products where productId = "+productId+";";
            PreparedStatement statement1 = connection.prepareStatement(product);
            ResultSet resultSet = statement1.executeQuery();
            String productName = null;
            while(resultSet.next()) {
                productName = resultSet.getString("productName");
            }

            ReportModel report = new ReportModel();
            report.setDate(date);
            report.setStatus(status);
            report.setProduct(productName);
            report.setCompanyName(companyName);
            report.setQuantity(quantity);

            reportsObject.add((Object) report);

//            System.out.println(date+"\t\t\t"+status+"\t\t\t"+quantity+"\t\t\t\t"+productName+"\t\t\t"+companyName);
        }
        return  reportsObject;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("date\t\t\t\tstatus\t\tquantity\t\tproductId\t\tcompanyTIN");


        getReportInformation();
    }
}
