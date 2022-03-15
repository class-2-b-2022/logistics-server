package controllers;

import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.ClientRequest;
import models.ProductModel;
import models.ResponseBody;
import models.Wallet;
import services.ProductService;
import utils.ParserObj;

public class ProductController {
	

    public String getProducts(int userId) {
        String resultFromReponseObject = "";
        try{
            List result = new ProductService().getProducts(userId);
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
    
    public String processProduct(ClientRequest clientRequest) throws SQLException, JsonProcessingException  {
        ParserObj parse = new ParserObj();
//        Wallet wallet = parse.parseData(clientRequest.getData(), Wallet.class);
        ProductModel product=parse.parseData(clientRequest.getData(), ProductModel.class);
        ObjectMapper mapper = new ObjectMapper();
        ResponseBody res = new ResponseBody();
        String result;
        System.out.println("-------action-----CREATE"+clientRequest.getAction());
        switch (clientRequest.getAction()) {
            case "POST":
            	
                if(createProduct(product)) {
                    res.setStatus("201");
                    res.setMessage("Product Created Successfully!");
                }
                break;
            case "READ":
            	product = parse.parseData(getProducts(product),ProductModel.class);
                 result = mapper.writeValueAsString(product);
                 res.setStatus("200");
                 res.setData(result);
                break;

            case "UPDATE":
              
                break;


            case "DELETE":
             
                break;
//            case :
//            	System.out.println("Select better option");
//            	break;
        }

        System.out.println(mapper.writeValueAsString(res));
        return mapper.writeValueAsString(res);
        
  
    }
    public static boolean createProduct(ProductModel product) throws SQLException {
    	ProductService productService=new ProductService();
    	return productService.createProduct(product);
    }
    public static ProductModel getProducts(ProductModel product) throws SQLException {
    	ProductService productService=new ProductService();
    	return productService.viewProducts(product.getCompanyId());
     }
}
