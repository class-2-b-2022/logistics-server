package controllers;

import models.ClientRequest;
import services.BillingService;

import java.sql.SQLException;

public class BillingController {
    ClientRequest request;
    static BillingService billService;

    static {
        try {
            billService = new BillingService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BillingController(ClientRequest request) throws SQLException {
        this.request = request;
    }

    public static  Object getDistWallet(int userId) throws SQLException {
       return billService.viewUserWallet(userId);
    }

    public static Object updateUserWallet(int userId, int amount) throws SQLException {
        return billService.updateDistributorWallet(userId,amount);
    }

//    public Object main(String[] args) {
//        Object response = null;
//        switch(this.request.getAction()){
//            case "updatedistributorwallet":
//                response = updateUserWallet(this.request.getData().userId, this.request.getData().amount);
//                break;
//            case "getdistributorwallet":
//                response = getDistWallet(this.request.getData().userId);
//                break;
//        }
//        return response;
//    }
}
