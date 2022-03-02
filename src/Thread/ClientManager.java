package Thread;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.DeliveryModule.VehicleManagementController;
import models.ClientRequest;
import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: Mudahemuka Manzi
 * @author: Ntagungira Ali Rashid
 */
public class ClientManager implements Runnable{
    private Socket clientSocket;
    private String json_data;
    private ClientRequest clientRequest = new ClientRequest();
    private VehicleManagementController vehicleManagementController = new VehicleManagementController();
    public ClientManager(Socket socket){
        this.clientSocket = socket;
    }
    @Override
    public void run() {
        DataOutputStream responseStream = null;
        ObjectInputStream requestStream = null;
        try {
            requestStream = new ObjectInputStream(clientSocket.getInputStream());
            responseStream = new DataOutputStream(clientSocket.getOutputStream());
            String jsonString = (String) requestStream.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNodeRoot = objectMapper.readTree(jsonString);
            JsonNode requestData = jsonNodeRoot.get("data");
            Iterator<Map.Entry<String, JsonNode>> iterator = requestData.fields();
            clientRequest.setRoute(jsonNodeRoot.get("route").asText());
            clientRequest.setData(iterator);
            clientRequest.setAction((jsonNodeRoot.get("action").asText()));
                String responseData = null;
                switch (jsonNodeRoot.get("route").asText()){
                    case "/companyregistration":
//                        logic related to company registration
                        break;
                    case "/users":
//                        logic related to user management
                        break;
                    case "/inventory":
//                        logic related to inventory
                        break;
                    case "/delivery/vehicles":
                        responseData = vehicleManagementController.mainMethod(clientRequest);
                        break;
                    case "/reporting":
//                        logic related to reporting
                        break;
                }
                //return response to the client;
                 System.out.println(responseData);
                responseStream.writeUTF(responseData);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}







