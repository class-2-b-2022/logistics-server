package controllers;

import models.ClientRequest;
import models.Wallet;
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

    public static  Object getDistWallet(Wallet billing) throws SQLException {
       return billService.viewUserWallet(billing.getUserId());
    }

    public static Object updateUserWallet(Wallet billModel) throws SQLException {
        return billService.updateDistributorWallet(billModel);
    }

    public Object main(String[] args) throws SQLException {
        Object response = null;
        switch (this.request.getAction()) {
            case "updatedistributorwallet":
                response = updateUserWallet((Wallet) this.request.getData());
                break;
            case "getdistributorwallet":
                response = getDistWallet((Wallet) this.request.getData());
                break;
        }
        return response;
    }
//    public BillingController() throws SQLException {
//    }
//
//    public static boolean createWallet(Wallet wallet) throws SQLException {
//        return billService.createWallet(wallet);
//    }
//
//    public static Wallet getDistWallet(Wallet wallet) throws SQLException {
//       return billService.viewUserWallet(wallet.getUserId());
//    }
//
//    public static Wallet updateUserWallet(Wallet wallet, String action) throws SQLException {
//        return billService.updateUserWallet(wallet, action);
//    }
//
//    public String processPayment(ClientRequest clientRequest) throws JsonProcessingException, SQLException {
//        ParserObj parse = new ParserObj();
//        Wallet wallet = parse.parseData(clientRequest.getData(), Wallet.class);
//        ObjectMapper mapper = new ObjectMapper();
//        ResponseBody res = new ResponseBody();
//        String result;
//        switch (clientRequest.getAction()) {
//            case "CreateWallet":
//                if(createWallet(wallet)) {
//                    res.setStatus("201");
//                    res.setMessage("Wallet Created Successfully!");
//                }
//                break;
//            case "Deposit":
//                wallet = parse.parseData(updateUserWallet(wallet, clientRequest.getAction()), Wallet.class);
//                result = mapper.writeValueAsString(wallet);
//                res.setStatus("204");
//                res.setMessage("Wallet updated successfully!");
//                res.setData(result);
//                break;
//
//            case "Withdraw":
//                wallet = parse.parseData(updateUserWallet(wallet, clientRequest.getAction()), Wallet.class);
//                result = mapper.writeValueAsString(wallet);
//                res.setStatus("204");
//                res.setMessage("Wallet updated successfully!");
//                res.setData(result);
//                break;
//
//
//            case "GetWallet":
//                wallet = parse.parseData(getDistWallet(wallet), Wallet.class);
//                result = mapper.writeValueAsString(wallet);
//                res.setStatus("200");
//                res.setData(result);
//                break;
//        }
//
//
//        return mapper.writeValueAsString(res);
//    }
}
