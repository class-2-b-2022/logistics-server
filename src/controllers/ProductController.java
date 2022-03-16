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
	ResponseBody res = new ResponseBody();
	ParserObj parse = new ParserObj();
    ObjectMapper mapper = new ObjectMapper();
 String getProducts(int companyId) {
        String resultFromReponseObject = "";
        try{
            List<Object> result = new ProductService().getProducts(companyId);
            res.setData(result);
            res.setMessage("successfully fetched products");
            res.setStatus("200");
            ObjectMapper objectMapper = new ObjectMapper();
            resultFromReponseObject = objectMapper.writeValueAsString(res);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return  resultFromReponseObject;
        }
    }
    
    public String processProduct(ClientRequest clientRequest) throws SQLException, JsonProcessingException  {
        
        
        String result = "";
        System.out.println("-------action-----CREATE"+clientRequest.getAction());
        switch (clientRequest.getAction()) {
            case "POST":
              ProductModel product=parse.parseData(clientRequest.getData(), ProductModel.class);
              result=createProduct(product);

    break;
            case "GET":
            	int companyId = (int) clientRequest.getData();
            	result =getProducts(companyId);
            	
                break;

            case "UPDATE":
              
                break;


            case "DELETE":
             
                break;
            default :
            	System.out.println("Select better option");
            	break;
        }

//        System.out.println(mapper.writeValueAsString(res));
        return result;
        
  
    }
    public String createProduct(ProductModel product) throws SQLException, JsonProcessingException {
    	ProductService productService=new ProductService();
    	Boolean result = productService.createProduct(product);
    	if(result) {
            res.setStatus("201");
             res.setMessage("Product Created Successfully!");
         }
    	else {
    		res.setMessage("Product failed to create");
    	}
    	return mapper.writeValueAsString(res);
    }
  

}
