package controllers.ReportModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.ClientRequest;

import java.util.ArrayList;
import java.util.List;

public class ReportController {

    public String mainMethod(ClientRequest clientRequest) throws Exception{
        String action = clientRequest.getAction();
        ReportActions actions = new ReportActions();
        List<Object> responseObject = new ArrayList<>();

        switch (action){
            case "view":
                responseObject = actions.getInventoryReports();
        }
        return new ObjectMapper().writeValueAsString(responseObject);
    }
}
