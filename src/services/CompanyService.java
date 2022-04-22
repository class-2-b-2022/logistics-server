package services;
import models.Company;
import utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyService {
    // connecting to database
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.getConnection();

    public void InsertIntoCompanies(Company company) throws SQLException{
        String sql = "INSERT INTO company(TIN,name,email,type,phone,description,createdAt) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,company.getTIN());
        preparedStatement.setString(2,company.getName());
        preparedStatement.setString(3, company.getEmail());
        preparedStatement.setString(4,company.getType());
        preparedStatement.setInt(5,company.getPhone());
        preparedStatement.setString(6,company.getDescription());
        preparedStatement.setDate(7, company.getCreatedAt());
        preparedStatement.executeUpdate();
    }

    public List<Object> getListOfCompanies() throws SQLException{
        List<Object> companiesObject = new ArrayList();
        String sql = "SELECT * FROM companies";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
            Company company = new Company();
            company.setTIN(result.getInt(1));
            company.setName(result.getString(2));
            company.setEmail(result.getString(3));
            company.setType(result.getString(4));
            company.setPhone(result.getInt(5));
            company.setDescription(result.getString(6));
            company.setCreatedAt(result.getDate(7));
            companiesObject.add((Object) company);
        }

        return companiesObject;
    }

}