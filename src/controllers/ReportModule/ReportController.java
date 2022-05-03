package controllers.ReportModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.ClientRequest;
import models.ResponseObject;

import java.util.ArrayList;
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
}
