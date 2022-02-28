package controllers;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InventoryController {
    DatabaseConnection connection = new DatabaseConnection();
    Connection con = connection.getConnection();
    PreparedStatement p;
    ResultSet rs;
    public void checkProductAvailability(int userId){
        try{
            String getProductsQuery = "select * from Inventory where userId="+userId;
            this.p = con.prepareStatement(getProductsQuery);
            this.rs = p.executeQuery();
            if (!rs.next()){
                System.out.println("no data to read");
            }
            while(rs.next()){
                System.out.println(rs.getInt("InventoryId"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
