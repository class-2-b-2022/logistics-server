package controllers.CompanyModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import services.CompanyService;
import services.CompanyService.*;

import java.util.ArrayList;
import java.util.List;

public class CompanyManagementController {

    ResponseBody responseBody = new ResponseBody();
    ObjectMapper objectMapper = new ObjectMapper();

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
}
