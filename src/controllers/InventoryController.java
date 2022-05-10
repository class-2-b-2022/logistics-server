package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.InventoryModel;
import models.ResponseBody;
import services.*;

import java.util.List;

/* Author: Sarah*/
public class InventoryController {
    ResponseBody responseBody = new ResponseBody();
    ObjectMapper objectMapper = new ObjectMapper();
    String resultFromReponseObject = "";
    InventoryService inventoryService = new InventoryService();
    public String addInventory(InventoryModel inv){
        try{
            int result = inventoryService.createInventory(inv);
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
        }
        finally {
            return  resultFromReponseObject;
        }
    }
    public String getInventory(int userId) {
        try{
            List result = inventoryService.readInventory(userId);
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
            resultFromReponseObject = objectMapper.writeValueAsString(responseBody);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return  resultFromReponseObject;
        }

    }
    public String deleteInventory(int inventoryId){
        try{
            int result = inventoryService.deleteInventory(inventoryId);
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
        List result = inventoryService.returnSingleRecord(inventoryId);
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
            resultFromReponseObject = objectMapper.writeValueAsString(responseBody);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return resultFromReponseObject;
        }
    }
    public String checkCurrentProductQuantityInStock(int productId, int branchId){
       try{
           String result = inventoryService.getCurrentProductQuantityInStock(productId, branchId);
           responseBody.setMessage("fetched quantity successfully");
           responseBody.setData(result);
           responseBody.setStatus("200");
           resultFromReponseObject = objectMapper.writeValueAsString(responseBody);
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           return resultFromReponseObject;
       }
    }
}
