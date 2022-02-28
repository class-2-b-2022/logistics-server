package controllers;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
public class BillingController {

=======
=======
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
//    public Object BillingController(ClientRequest request) throws SQLException {
//        this.request = request;
//        return null;
//    }
=======
    public Object BillingController(ClientRequest request) throws SQLException {
        this.request = request;
        return null;
    }
>>>>>>> Stashed changes

    public static  Object getDistWallet(BillingModel billing) throws SQLException {
       return billService.viewUserWallet(billing.getUserId());
    }

    public static Object updateUserWallet(BillingModel billModel) throws SQLException {
        return billService.updateDistributorWallet(billModel);
    }

<<<<<<< Updated upstream
    public List<Object> main(ClientRequest clientRequest) throws SQLException {
        List<Object> response = new ArrayList<Object>();
        switch(clientRequest.getAction()){
            case "updatedistributorwallet":
                response.add(updateUserWallet((BillingModel) clientRequest.getData()));
                break;
            case "getdistributorwallet":
                response.add(getDistWallet((BillingModel) clientRequest.getData()));
=======

    public List<Object> main(ClientRequest clientRequest) throws SQLException {
        List<Object> response = null;
        switch(this.request.getAction()){
            case "updatedistributorwallet":
                response.add(updateUserWallet((BillingModel) this.request.getData()));
                break;
            case "getdistributorwallet":
                response.add(getDistWallet((BillingModel) this.request.getData()));
>>>>>>> Stashed changes
                break;
        }
        return response;
    }
>>>>>>> Stashed changes
}
