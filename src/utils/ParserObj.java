package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ParserObj{


    public <T> T parseData(Object data, Class<T> obj) {
        ObjectMapper mapper = new ObjectMapper();
        T convertedData = null;
        try {
//            System.out.println(convertedData);
            byte[] dataToparse = mapper.writeValueAsBytes(data);
            convertedData = (T) mapper.readValue(dataToparse, obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertedData;
    }
}