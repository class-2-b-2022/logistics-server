package controllers;

import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.ClientRequest;
import models.ProductModel;
import models.ResponseBody;
import services.ProductService;
import utils.ParserObj;

public class ProductController {
    public String getProducts(int companyId) {
        String resultFromReponseObject = "";
        try{
            List result = new ProductService().getProducts(companyId);
            ResponseBody responseBody = new ResponseBody();
            responseBody.setData(result);
            responseBody.setMessage("successfully fetched products");
            responseBody.setStatus("200");
            ObjectMapper objectMapper = new ObjectMapper();
            resultFromReponseObject = objectMapper.writeValueAsString(responseBody);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return  resultFromReponseObject;
        }
    }
    
    public static String processProduct(ClientRequest clientRequest) throws SQLException, JsonProcessingException  {
        ObjectMapper mapper = new ObjectMapper();
        ResponseBody res = new ResponseBody();
        String result = "";
        ProductController productController = new ProductController();
        switch (clientRequest.getAction()) {
            case "CREATE":
                break;
            case "GET":
                int companyId = (int) clientRequest.getData();
                result = productController.getProducts(companyId);
                break;

            case "UPDATE":
              
                break;


            case "DELETE":
             
                break;
        }


        return result;
  
    }
    public static boolean createProduct(ProductModel product) throws SQLException {
    	ProductService productService=new ProductService();
    	return productService.createProduct(product);
    }
}
