package controllers;
 import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import models.*;
import utils.DatabaseConnection;

public class ProductController{
	   DatabaseConnection connection = new DatabaseConnection();
	    Connection con = connection.getConnection();
	    PreparedStatement p;
	    ResultSet rs;
@SuppressWarnings("finally")
public

/*Author Michaella INEZA*/

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



@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
/*author Michaella INEZA*/

public List getProducts(int userId) {
        List result = new ArrayList();
        try {
            @SuppressWarnings("unused")
			Statement statement = con.createStatement();
			@SuppressWarnings({ "unused", "resource" })
			Scanner scanner = new Scanner(System.in);
            String getProductsQuery = ("select * from products where userId = " + userId);
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
}
