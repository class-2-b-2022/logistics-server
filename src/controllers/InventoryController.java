package controllers;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.InventoryModel;
import models.ProductModel;
import models.ResponseBody;
import services.*;
import utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Author: Sarah*/
public class InventoryController {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection con = databaseConnection.getConnection();
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

    public String getInventory(int userId) {
        try{
            List result = new InventoryService().readInventory(userId);
            if(result.isEmpty()){
                responseBody.setMessage("Failed to fetch inventory");
                responseBody.setData(result);
                responseBody.setStatus("500");
            }
            else {
                responseBody.setData(result);
                responseBody.setMessage("successfully fetched inventory");
                responseBody.setStatus("200");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            resultFromReponseObject = objectMapper.writeValueAsString(responseBody);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return  resultFromReponseObject;
        }

    }
    public String deleteInventory(int inventoryId){
        try{
            int result = new InventoryService().deleteInventory(inventoryId);
            if(result > 0){
                responseBody.setStatus("201");
                responseBody.setMessage("Successfully deleted inventory");
                responseBody.setData("");
            }else{
                responseBody.setStatus("500");
                responseBody.setMessage("Failed to delete inventory");
                responseBody.setData("");
            }
            resultFromReponseObject = objectMapper.writeValueAsString(responseBody);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return resultFromReponseObject;
        }
    }

    public String viewSingleRecord(int inventoryId){
        List result = new InventoryService().returnSingleRecord(inventoryId);
        try{
            if(result.isEmpty()){
                responseBody.setMessage("Failed to fetch inventory");
                responseBody.setData(result);
                responseBody.setStatus("500");
            }
            else {
                responseBody.setData(result);
                responseBody.setMessage("successfully fetched inventory");
                responseBody.setStatus("200");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            resultFromReponseObject = objectMapper.writeValueAsString(responseBody);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return resultFromReponseObject;
        }
    }
}
