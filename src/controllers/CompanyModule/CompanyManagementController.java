package controllers.CompanyModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import services.CompanyService;

import java.sql.SQLException;
import java.util.List;

/**@author  Teta Butera Nelly*/

public class CompanyManagementController {

    ResponseBody responseBody = new ResponseBody();
    ObjectMapper objectMapper = new ObjectMapper();
    CompanyService companyService = new CompanyService();

    public String addCompany(Company company){
        String resultFromResponseObject = "";
        try {
            int result = new CompanyService().insertIntoCompanies(company);
            if (result > 0) {
                responseBody.setStatus("201");
                responseBody.setMessage("Company successfully registered");
                responseBody.setData("");
            } else {
                responseBody.setStatus("500");
                responseBody.setMessage("Failed to register company");
                responseBody.setData("");
            }
            resultFromResponseObject = objectMapper.writeValueAsString(responseBody);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return resultFromResponseObject;
        }
    }
    public List<Object> getCompanies() throws SQLException{
        return companyService.getListOfCompanies();
    }
}
