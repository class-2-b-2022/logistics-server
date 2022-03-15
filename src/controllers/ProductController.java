package controllers;

import models.ProductModel;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductController {


    DatabaseConnection connection = new DatabaseConnection();
    Connection con = connection.getConnection();
    PreparedStatement p;
    ResultSet rs;

    public List getProducts(int userId) {
        List result = new ArrayList();
        try {
            Statement statement = con.createStatement();
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
