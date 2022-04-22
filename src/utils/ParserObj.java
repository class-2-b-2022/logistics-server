package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< HEAD
=======
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import models.*;
import java.io.DataInput;
import java.util.*;
>>>>>>> de9f252aa394f2d7ca7aceb028c578bbd4ab9120

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
