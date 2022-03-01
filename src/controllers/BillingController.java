package controllers;
import models.billing.BillingModel;
import models.ClientRequest;
import models.billing.Wallet;
import services.BillingService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    public static void createWallet(Wallet wallet) throws SQLException {
        billService.createWallet(wallet);
    }

    public static Wallet getDistWallet(Wallet wallet) throws SQLException {
       return billService.viewUserWallet(wallet.getUserId());
    }

    public static Wallet updateUserWallet(Wallet wallet) throws SQLException {
        return billService.updateDistributorWallet(wallet);
    }

    public List<Object> mainMethod(ClientRequest clientRequest) throws SQLException {
        List<Object> response = null;
        switch (clientRequest.getAction()) {
            case "createwallet" -> createWallet((Wallet) this.request.getData());
            case "updatedistributorwallet" -> response.add(updateUserWallet((Wallet) this.request.getData()));
            case "getdistributorwallet" -> response.add(getDistWallet((Wallet) this.request.getData()));
        }
        return response;
    }
}
