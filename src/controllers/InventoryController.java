package controllers;

import utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

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

    public void addProduct(String userCategory){
        try{
            Statement statement = con.createStatement();
            Scanner scanner = new Scanner(System.in);
            String getProductsQuery = ("select * from products");
            this.p = con.prepareStatement(getProductsQuery);
            this.rs = p.executeQuery();

            while(rs.next()){
                System.out.println("Products in stock: \n" + rs.getInt("id")+ " " + rs.getString("name"));
            }
            System.out.println("Enter product name: ");
            String product = scanner.nextLine();

            System.out.println("Enter the quantity: ");
            int quantity = scanner.nextInt();

            System.out.println("Choose what you want to perform. 1. IN\n 2. OUT\n Enter your choice: ");
            String status;
            int choice = scanner.nextInt();
            if(choice == 1){
                status = "IN";
            }
            else{
                status = "OUT";
            }

            statement.execute("insert into inventory(user_category,product_name,quantity,status,Date) values (userCategory,product,quantity,status,now())");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
