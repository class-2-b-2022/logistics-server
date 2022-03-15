package services;

import models.InventoryModel;
import models.ProductModel;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    //    connect to DB
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection con = databaseConnection.getConnection();
    PreparedStatement p;
    ResultSet rs;
    int rowsAffected = 0;
    public int createInventory(InventoryModel inv){
        try{
            long millis = System.currentTimeMillis();
            String sql = "insert into Inventory(quantity,status,productId,Date,branchId) values (" +
                    "?,?,?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,inv.getQuantity());
            statement.setString(2, inv.getStatus());
            statement.setInt(3, inv.getProductId());
            statement.setDate(4, new java.sql.Date(millis));
            statement.setInt(5,inv.getBranchId());

            rowsAffected = statement.executeUpdate();
//            System.out.println(rowsAffected);
            if (rowsAffected > 0) {
                System.out.println("successfully created inventory");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            return rowsAffected;
        }
    }

    public List readInventory(int branchId){
        List <Object> result = new ArrayList();
        try{
            String getInventoryQuery = "SELECT * FROM Inventory where branchId = "+ branchId;
            this.p = con.prepareStatement(getInventoryQuery);
            this.rs = p.executeQuery();

            while (rs.next()){
                InventoryModel inventory = new InventoryModel();
                inventory.setProductId(rs.getInt("productId"));
                inventory.setQuantity(rs.getInt("quantity"));
                inventory.setStatus(rs.getString("status"));
                inventory.setBranchId(rs.getInt("branchId"));
                result.add(inventory);
            }
            if(result.isEmpty()){
                System.out.println("Failed to fetch inventory");
            }
            else{
                System.out.println("Fetched inventory successfully");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    public List returnSingleRecord(int inventoryId){
        List <Object> result = new ArrayList();
        try{
            String getInventoryQuery = "SELECT * FROM Inventory where inventoryId = "+ inventoryId;
            this.p = con.prepareStatement(getInventoryQuery);
            this.rs = p.executeQuery();
            // it will return only a single record
            while(rs.next()) {
                InventoryModel inventory = new InventoryModel();
                inventory.setProductId(rs.getInt("productId"));
                inventory.setQuantity(rs.getInt("quantity"));
                inventory.setStatus(rs.getString("status"));
                inventory.setBranchId(rs.getInt("branchId"));
                result.add(inventory);
            }
            if(result.isEmpty()){
                System.out.println("Failed to fetch inventory");
            }
            else{
                System.out.println("Fetched inventory successfully");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    public int deleteInventory(int inventoryId){
        try {
            String sql = "DELETE from Inventory where inventoryId = " + inventoryId;
            PreparedStatement statement = con.prepareStatement(sql);
            rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Successfully deleted a record");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            return rowsAffected;
        }
    }
}