package controllers;
 import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import models.*;
import utils.DatabaseConnection;

public class ProductController{
	   DatabaseConnection connection = new DatabaseConnection();
	    Connection con = connection.getConnection();
	    PreparedStatement p;
	    ResultSet rs;
@SuppressWarnings("finally")
public
boolean registerProduct(ProductModel product) {
     try{
    	 
    	 String sql="insert into products(productName,productType,userId,pricePBulk) values(?,?,?,?)";
         PreparedStatement preparedStatement=con.prepareStatement(sql);
         preparedStatement.setString(1,product.getProductName());
         preparedStatement.setString(2,product.getProductType());
         preparedStatement.setInt(3,product.getUserId());
         preparedStatement.setString(4,product.getPricePerBulk());
         int inserted = preparedStatement.executeUpdate();
         if(inserted==1) {
        	 System.out.println("Product Added successfully");
         }else {
        	 System.out.println("and error occured");
         }
         
     }catch(Exception e){
         e.printStackTrace();
     }
     finally {
         return true;
     }
}
}



