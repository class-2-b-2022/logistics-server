package controllers;
import models.billing.BillingModel;
import models.ClientRequest;
import services.BillingService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author : Mudahemuka Manzi
 * @author : Gasaro leila
 */
import models.billing.BillingModel;
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


    public static  Object getDistWallet(BillingModel billing) throws SQLException {
       return billService.viewUserWallet(billing.getUserId());
    }

    public static Object updateUserWallet(BillingModel billModel) throws SQLException {
        return billService.updateDistributorWallet(billModel);
    }

    public List<Object> mainMethod(ClientRequest clientRequest) throws SQLException {
        List<Object> response = null;
        switch (clientRequest.getAction()) {
            case "updatedistributorwallet" -> response.add(updateUserWallet((BillingModel) this.request.getData()));
            case "getdistributorwallet" -> response.add(getDistWallet((BillingModel) this.request.getData()));
        }
        return response;
    }
}
