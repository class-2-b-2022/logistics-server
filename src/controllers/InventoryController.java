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
    String resultFromReponseObject = "";
    public String addInventory(InventoryModel inv){
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
    public String getInventory(int userId){
        try{
            List<Object> result = new InventoryService().getInventories(userId);
            responseBody.setStatus("200");
            responseBody.setMessage("Successfully fetched the inventories");
            responseBody.setData(result);
            resultFromReponseObject = objectMapper.writeValueAsString(responseBody);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return resultFromReponseObject;
        }
    }
}
