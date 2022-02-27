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
            String getProductsQuery = "select * from products where userId="+userId;
            this.p = con.prepareStatement(getProductsQuery);
            this.rs = p.executeQuery();

            while(rs.next()){
                System.out.println(rs.getInt("id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
