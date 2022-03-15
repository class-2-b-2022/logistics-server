package controllers;

import models.Wallet;
import models.ClientRequest;
import services.BillingService;

import java.sql.SQLException;

/**
 * @author : Mudahemuka Manzi
 * @author : Gasaro leila
 */
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

    public Object BillingController(ClientRequest request) throws SQLException {
        this.request = request;
        return null;
    }

    public static  Object getDistWallet(Wallet billing) throws SQLException {
       return billService.viewUserWallet(billing.getUserId());
    }

    public static Object updateUserWallet(Wallet billModel) throws SQLException {
        return billService.updateDistributorWallet(billModel);
    }

    public Object main(String[] args) throws SQLException {
        Object response = null;
        switch(this.request.getAction()){
            case "updatedistributorwallet":
                response = updateUserWallet((Wallet) this.request.getData());
                break;
            case "getdistributorwallet":
                response = getDistWallet((Wallet) this.request.getData());
                break;
        }
        return response;
    }
}
