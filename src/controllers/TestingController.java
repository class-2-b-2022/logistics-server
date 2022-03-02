package controllers;

import models.BillingModel;
import models.ClientRequest;
import utils.*;

public class TestingController {
    public static  void test(ClientRequest clientRequest){
       ParserObj parserObj = new ParserObj();
        BillingModel billingModel = parserObj.parseData(clientRequest.getData(), BillingModel.class);
        System.out.println(billingModel.getAmount());
        System.out.println(billingModel.getUserId());
        switch (clientRequest.getAction()){
            case "testing":
                System.out.println("request body");
                break;
        }
    }
}
