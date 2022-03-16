package controllers;


import models.InventoryModel;
import models.ProductModel;
import utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Author: Sarah*/
public class InventoryController {
    DatabaseConnection connection = new DatabaseConnection();
    Connection con = connection.getConnection();
    PreparedStatement p;
    ResultSet rs;

    public int addInventory(InventoryModel inv){
        List<Number> list = new ArrayList();
        int rowsInserted = 0;
        try{
            long millis = System.currentTimeMillis();
            String sql = "insert into Inventory(quantity,status,productId,userId,Date) values (" +
                    "?,?,?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,inv.getQuantity());
            statement.setString(2, inv.getStatus());
            statement.setInt(3, inv.getProductId());
            statement.setInt(4,inv.getUserId());
            statement.setDate(5, new java.sql.Date(millis));

            rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("successfully created inventory");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            return rowsInserted;
        }


    }
    public List getInventory(int userId){
        List result = new ArrayList();
        try {
            Statement statement = con.createStatement();
            Scanner scanner = new Scanner(System.in);
            String getProductsQuery = ("select * from inventory where userId = " + userId);
            this.p = con.prepareStatement(getProductsQuery);
            this.rs = p.executeQuery();

            while (rs.next()){
                InventoryModel inventoryModel = new InventoryModel();
                inventoryModel.setProductId(rs.getInt("productId"));
                inventoryModel.setQuantity(rs.getInt("quantity"));
                inventoryModel.setUserId(rs.getInt("userId"));
                inventoryModel.setStatus(rs.getString("Status"));

                result.add(inventoryModel);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return  result;
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
