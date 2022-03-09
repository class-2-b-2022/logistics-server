package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.InventoryModel;
import models.ProductModel;
import models.ResponseBody;
import services.InventoryService;
import utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Author: Sarah*/
public class InventoryController {
    ResponseBody responseBody = new ResponseBody();
    ObjectMapper objectMapper = new ObjectMapper();
    public String addInventory(InventoryModel inv){
        String resultFromReponseObject = "";
        try{
            int result = new InventoryService().createInventory(inv);
            if(result > 0){
                responseBody.setStatus("201");
                responseBody.setMessage("Successfully created inventory");
                responseBody.setData("");
            }else{
                responseBody.setStatus("500");
                responseBody.setMessage("Failed to register inventory");
                responseBody.setData("");
            }
            resultFromReponseObject = objectMapper.writeValueAsString(responseBody);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return resultFromReponseObject;
        }
    }
//    public List getInventory(int userId){
//        List result = new ArrayList();
//        try {
//            Statement statement = con.createStatement();
//            Scanner scanner = new Scanner(System.in);
//            String getProductsQuery = ("select * from inventory where userId = " + userId);
//            this.p = con.prepareStatement(getProductsQuery);
//            this.rs = p.executeQuery();
//
//            while (rs.next()){
//                InventoryModel inventoryModel = new InventoryModel();
//                inventoryModel.setProductId(rs.getInt("productId"));
//                inventoryModel.setQuantity(rs.getInt("quantity"));
//                inventoryModel.setUserId(rs.getInt("userId"));
//                inventoryModel.setStatus(rs.getString("Status"));
//
//                result.add(inventoryModel);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            return  result;
//        }
//    }
}
