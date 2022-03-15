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
    
    public static String processProduct(ClientRequest clientRequest) throws SQLException, JsonProcessingException  {
        ParserObj parse = new ParserObj();
//        Wallet wallet = parse.parseData(clientRequest.getData(), Wallet.class);
        ProductModel product=parse.parseData(clientRequest.getData(), ProductModel.class);
        ObjectMapper mapper = new ObjectMapper();
        ResponseBody res = new ResponseBody();
        String result;
        switch (clientRequest.getAction()) {
            case "CREATE":
                if(createProduct(product)) {
                    res.setStatus("201");
                    res.setMessage("Wallet Created Successfully!");
                }
                break;
            case "READ":
              
                break;

            case "UPDATE":
              
                break;


            case "DELETE":
             
                break;
        }


        return mapper.writeValueAsString(res);
  
    }
    public static boolean createProduct(ProductModel product) throws SQLException {
    	ProductService productService=new ProductService();
    	return productService.createProduct(product);
    }
}
