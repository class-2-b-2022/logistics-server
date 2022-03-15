package models;



import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ResponseBody implements Serializable {
    private String message;//created
    private String status;//201
    private Object data;
    public ResponseBody(){}
    public ResponseBody(String message, String data,String status){
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }



    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
