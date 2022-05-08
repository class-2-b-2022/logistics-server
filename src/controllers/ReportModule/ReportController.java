package controllers.ReportModule;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.ClientRequest;
import models.ReportModel;
import models.ResponseObject;
import services.ReportModule.ReportService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportController {

    public String mainMethod(ClientRequest clientRequest) throws Exception{
        String action = clientRequest.getAction();
        ReportActions actions = new ReportActions();
        List<Object> responseObject = new ArrayList<>();
        ResponseObject responseObject1 = new ResponseObject();

        switch (action){
            case "view":
                responseObject = actions.getInventoryReports();
                responseObject1.setData(responseObject);
                responseObject1.setStatus("201");
                responseObject1.setMessage("reports retrieved");
        }
        return new ObjectMapper().writeValueAsString(responseObject1);
    }
    public static void main(String[] args) throws Exception {
        ReportService reportService = new ReportService();
        ReportController reportController = new ReportController();
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setRoute("/reports");
        clientRequest.setAction("view");
        System.out.println(reportController.mainMethod(clientRequest));
//        ObjectMapper inputMapper = new ObjectMapper();
//        List<ReportModel> reportModels = Arrays.asList(inputMapper.readValue((JsonParser) reports, ReportModel[].class));

//        for (ReportModel reportModel: reportModels){
//            System.out.println(reportModel.getDate());
//            System.out.println(reportModel.getProduct());
//            System.out.println(reportModel.getStatus());
//            System.out.println(reportModel.getQuantity());
//            System.out.println(reportModel.getCompanyName());
//        }

    }
}
