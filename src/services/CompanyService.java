package services;

import models.Company;
import utils.*;
import java.sql.*;


public class CompanyService {
    // connecting to database
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.getConnection();
    int rowsInserted = 0;

    public int insertIntoCompanies(Company company){
        try {
            String sql = "INSERT INTO companies(TIN,name,email,type,phone,description,createdAt) values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            long millis = System.currentTimeMillis();
            preparedStatement.setInt(1, company.getTIN());
            preparedStatement.setString(2, company.getName());
            preparedStatement.setString(3, company.getEmail());
            preparedStatement.setString(4, company.getType());
            preparedStatement.setInt(5, company.getPhone());
            preparedStatement.setString(6, company.getDescription());
            preparedStatement.setDate(7, new java.sql.Date(millis));

            rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("successfully created inventory");
            }
        }catch(Exception e) {
                e.printStackTrace();
        }
        return rowsInserted;
    }
}

//    public List<Object> getListOfCompanies() throws SQLException{
//        List<Object> companiesObject = new ArrayList();
//        String sql = "SELECT * FROM companies";
//        Statement statement = connection.createStatement();
//        ResultSet result = statement.executeQuery(sql);
//
//        while (result.next()){
//            Company company = new Company();
//            company.setTIN(result.getInt(1));
//            company.setName(result.getString(2));
//            company.setEmail(result.getString(3));
//            company.setType(result.getString(4));
//            company.setPhone(result.getInt(5));
//            company.setDescription(result.getString(6));
//            company.setCreatedAt(result.getDate(7));
//            companiesObject.add((Object) company);
//        }
//
//        return companiesObject;
//    }

//}