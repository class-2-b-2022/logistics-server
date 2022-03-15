package Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import models.BillingModel;
import java.io.DataInput;
import java.util.*;

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
