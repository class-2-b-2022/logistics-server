package services;

import models.ProductModel;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/* Author : Sarah */
public class ProductService {
    //    connect to DB
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection con = databaseConnection.getConnection();
    PreparedStatement p;
    ResultSet rs;

    public List<Object> getProducts(int userId){
        List<Object> result = new ArrayList();
        try {
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
            return result;
        }
    }
}
