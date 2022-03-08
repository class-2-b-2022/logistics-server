package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.ProductModel;
import models.ResponseBody;
import services.ProductService;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
}
