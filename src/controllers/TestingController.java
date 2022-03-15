package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.ParserObj;
import models.BillingModel;
import models.ClientRequest;
import models.ResponseBody;

import java.util.ArrayList;
import java.util.List;

public class TestingController {
    public static  String test(ClientRequest clientRequest) throws JsonProcessingException {
       ParserObj parserObj = new ParserObj();
        BillingModel billingModel = parserObj.parseData(clientRequest.getData(), BillingModel.class);
        System.out.println(billingModel.getAmount());
        System.out.println(billingModel.getUserId());
        ObjectMapper mapper = new ObjectMapper();
        ResponseBody res = new ResponseBody();
//        String json = mapper.writeValueAsString(billingModel);
      List<Object> obj = new ArrayList<>();
      obj.add(billingModel);
        String response;
        switch (clientRequest.getAction()){
            case "testing":
               res.setStatus("200");
               res.setMessage("Received data successfully");
               res.setData(obj);
               response = mapper.writeValueAsString(res);
                System.out.println(response);
                System.out.println("assigned response");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + clientRequest.getAction());
        }
        return response;
    }
}
