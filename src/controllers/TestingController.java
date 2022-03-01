package controllers;

import models.ClientRequest;

public class TestingController {
    public static  void test(ClientRequest clientRequest){
        switch (clientRequest.getAction()){
            case "testing":
                System.out.println("request body");
                System.out.println(clientRequest.getData());
                break;
        }
    }
}
