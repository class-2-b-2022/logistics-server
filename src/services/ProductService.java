package services;

import models.ProductModel;
import models.Wallet;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* Author : Sarah */
public class ProductService {
    //    connect to DB
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection con = databaseConnection.getConnection();
    PreparedStatement p;
    ResultSet rs;

    @SuppressWarnings("finally")
	public List<Object> getProducts(int companyId){
        List<Object> result = new ArrayList();
        try {
            String getProductsQuery = ("select * from products where companyId = " +  companyId);
            this.p = con.prepareStatement(getProductsQuery);
            this.rs = p.executeQuery();

            while (rs.next()){
                ProductModel product = new ProductModel();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductType(rs.getString("productType"));
                product.setPricePerBulk(rs.getString("pricePBulk"));
                product.setCompanyId(rs.getInt("companyId"));

                result.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }
    public boolean createProduct(ProductModel product) throws SQLException {
        try {
        	String sql="INSERT INTO products(productName,productType,companyId,pricePBulk)values(?,?,?,?)";
          PreparedStatement preparedStatement = con.prepareStatement(sql);
          preparedStatement.setString(1,product.getProductName());
          preparedStatement.setString(2,product.getProductType());
          preparedStatement.setInt(3,product.getCompanyId());
          preparedStatement.setInt(4,product.getPricePerBulk());
          preparedStatement.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }
}
