package controllers;

import models.ClientRequest;
import models.InventoryModel;
import models.ProductModel;
import utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class InventoryController {
    DatabaseConnection connection = new DatabaseConnection();
    Connection con = connection.getConnection();
    PreparedStatement p;
    ResultSet rs;

    public  List getProducts(){
        List result = new ArrayList();
        try {
            Statement statement = con.createStatement();
            Scanner scanner = new Scanner(System.in);
            String getProductsQuery = ("select * from products");
            this.p = con.prepareStatement(getProductsQuery);
            this.rs = p.executeQuery();

            while (rs.next()){
                ProductModel product = new ProductModel();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductType(rs.getString("productType"));
                product.setPricePerBulk(rs.getString("pricePBulk"));

                result.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return  result;
        }
    }

    public boolean addInventory(InventoryModel inv){
        List<Number> list = new ArrayList();
        try{
            System.out.println("request body reached heeee");
            Statement inventorySave = con.createStatement();
            boolean result = inventorySave.execute("insert into Inventory(quantity,status,productId,userId,Date) values (" +
                    inv.getQuantity() + ",\'" + inv.getStatus() + "\'," + inv.getProductId() + "," +inv.getUserId() + "," + "\'2022-02-28\'"
                    + ");");
            System.out.println("successfully created inventory");
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            return true;
        }
    }
}
