package controllers;

import models.BillingModel;
import models.ClientRequest;
import Services.BillingService;

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

    public static  Object getDistWallet(BillingModel billing) throws SQLException {
       return billService.viewUserWallet(billing.getUserId());
    }

    public static Object updateUserWallet(BillingModel billModel) throws SQLException {
        return billService.updateDistributorWallet(billModel);
    }

    public Object main(String[] args) throws SQLException {
        Object response = null;
        switch(this.request.getAction()){
            case "updatedistributorwallet":
                response = updateUserWallet((BillingModel) this.request.getData());
                break;
            case "getdistributorwallet":
                response = getDistWallet((BillingModel) this.request.getData());
                break;
        }
        return response;
    }
}
