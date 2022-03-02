package controllers;

<<<<<<< HEAD
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
=======
import models.ClientRequest;

public class TestingController {
    public static  void test(ClientRequest clientRequest){
        switch (clientRequest.getAction()){
            case "testing":
                System.out.println("request body");
                System.out.println(clientRequest.getData());
>>>>>>> d4df132b4dda6ab281e5de9c1899803d24a8c402
                break;
        }
    }
}
