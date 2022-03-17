package controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.ClientRequest;
import models.ResponseBody;
import models.Wallet;
import services.BillingService;
import utils.ParserObj;
import java.sql.SQLException;

/**
 * @author : Mudahemuka Manzi
 * @author : Gasaro leila
 */
public class BillingController {
    static BillingService billService;

    static {
        try {
            billService = new BillingService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BillingController() throws SQLException {
    }

    public static boolean createWallet(Wallet wallet) throws SQLException {
        return billService.createWallet(wallet);
    }

    public static Wallet getWallet(Wallet wallet) throws SQLException {
       return billService.viewUserWallet(wallet);
    }

    public static Wallet updateUserWallet(Wallet wallet, String action) throws SQLException {
        return billService.updateUserWallet(wallet, action);
    }

    public String processPayment(ClientRequest clientRequest) throws JsonProcessingException, SQLException {
        ParserObj parse = new ParserObj();
        Wallet wallet = parse.parseData(clientRequest.getData(), Wallet.class);
        ObjectMapper mapper = new ObjectMapper();
        ResponseBody res = new ResponseBody();
        String result;
        switch (clientRequest.getAction()) {
            case "CreateWallet":
                if(createWallet(wallet)) {
                    res.setStatus("201");
                    res.setMessage("Wallet Created Successfully!");
                }
                break;
            case "Deposit":
                wallet = parse.parseData(updateUserWallet(wallet, clientRequest.getAction()), Wallet.class);
                result = mapper.writeValueAsString(wallet);
                res.setStatus("204");
                res.setMessage("Amount deposited successfully!");
                res.setData(result);
                break;

            case "Withdraw":
                wallet = parse.parseData(updateUserWallet(wallet, clientRequest.getAction()), Wallet.class);
                result = mapper.writeValueAsString(wallet);
                res.setStatus("204");
                res.setMessage("Amount Withdrawn successfully!");
                res.setData(result);
                break;

            case "GetWallet":
                wallet = parse.parseData(getWallet(wallet), Wallet.class);
                result = mapper.writeValueAsString(wallet);
                res.setMessage("Current User Wallet!");
                res.setStatus("200");
                res.setData(result);
                break;
        }


        return mapper.writeValueAsString(res);
    }
}