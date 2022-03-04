package controllers;
import java.sql.*;

import utils.DatabaseConnection;



import models.RegisterProduct;

public class ProductRegister {

	public static void main(String[] args) throws SQLException {RegisterProduct product=new RegisterProduct();
DatabaseConnection connection=new DatabaseConnection();
Connection con=connection.getConnection();
product.setProductName("Jewelery");
product.setProductType("Wear");
product.setPricePBulk(20000);
String pname=product.getProductName();
String ptype=product.getProductType();
Integer price =product.getPricePBulk();
String sql="insert into products(productName,productType,userId,pricePBulk)values(?,?,?,?)";
PreparedStatement statement=con.prepareStatement(sql);
statement.setString(1,pname);
statement.setString(2,ptype);
statement.setInt(3,1);
statement.setInt(4,price);
int inserted=statement.executeUpdate();
if(inserted==1) {
	System.out.println("Inserted");
}
else {
	System.out.println("Erroe");
}
	}

}
