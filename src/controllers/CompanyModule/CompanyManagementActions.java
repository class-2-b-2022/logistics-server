package controllers.CompanyModule;

import models.Company;
import services.CompanyService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyManagementActions<ResponseStatus> {
    private CompanyService companyService = new CompanyService();

    public List<Object> registerCompany(Company company) throws SQLException{
        List<Object> companyObject = new ArrayList<>();
        companyService.InsertIntoCompanies(company);
        companyObject.add((Object) company);
        return companyObject;
    }

    public List<Object> getCompanies(Integer TIN) throws SQLException{
        return companyService.getListOfCompanies();
    }

    public List<Object> getVehicles() throws SQLException {
        return companyService.getListOfCompanies();
    }
}
