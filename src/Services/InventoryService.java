package Services;

import models.InventoryModel;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InventoryService {
    //    connect to DB
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection con = databaseConnection.getConnection();
    PreparedStatement p;
    int rowsInserted = 0;
    public int createInventory(InventoryModel inv){
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
}
