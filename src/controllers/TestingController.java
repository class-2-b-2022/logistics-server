package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Wallet;
import models.ClientRequest;
import models.ResponseBody;
import utils.*;

import java.util.ArrayList;
import java.util.List;

public class TestingController {
    public static  String test(ClientRequest clientRequest) throws JsonProcessingException {
       ParserObj parserObj = new ParserObj();
        Wallet wallet = parserObj.parseData(clientRequest.getData(), Wallet.class);
        System.out.println(wallet.getAmount());
        System.out.println(wallet.getUserId());
        ObjectMapper mapper = new ObjectMapper();
        ResponseBody res = new ResponseBody();
//        String json = mapper.writeValueAsString(billingModel);
      List<Object> obj = new ArrayList<>();
      obj.add(wallet);
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
