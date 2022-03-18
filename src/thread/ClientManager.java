package thread;
<<<<<<< HEAD

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.user_management.*;
import controllers.DeliveryModule.*;
import models.ClientRequest;
import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: Mudahemuka Manzi
 * @author: Ntagungira Ali Rashid
 */
public class ClientManager implements Runnable{
    private Socket clientSocket;
    private String json_data;
    private ClientRequest clientRequest = new ClientRequest();
    private UserController userController = new UserController();
    private VehicleManagementController vehicleManagementController = new VehicleManagementController();
    public ClientManager(Socket socket){
=======
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.BillingController;
import controllers.DeliveryModule.VehicleManagementController;
import controllers.InventoryController;
import controllers.ProductController;
import controllers.TestingController;
import controllers.DeliveryModule.VehicleManagementController;
import controllers.user_management.UserController;

import models.*;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

/**
 * @author : Mudahemuka Manzi
 * @author : Ntagungira Ali Rashid
 */
public class ClientManager implements Runnable{
    private Socket clientSocket;
    private final VehicleManagementController vehicleManagementController = new VehicleManagementController();
     private final BillingController billingController = new BillingController();
    UserController userController=new UserController();

    public ClientManager(Socket socket) throws SQLException {

>>>>>>> 768c3f3addc3d44775ad4f3e9ad1125eefcef0d8
        this.clientSocket = socket;
    }
    @Override
    public void run() {
        DataOutputStream responseStream = null;
        ObjectInputStream requestStream = null;
        try {
            requestStream = new ObjectInputStream(clientSocket.getInputStream());
            responseStream = new DataOutputStream(clientSocket.getOutputStream());
<<<<<<< HEAD
            String jsonString = (String) requestStream.readObject();
            System.out.println(jsonString);
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
                   responseData = userController.mainMethod(clientRequest);
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
=======
//            System.out.println("New client with adresss: "+ clientSocket.getInetAddress().getHostAddress());
            ObjectMapper objectMapper = new ObjectMapper();
         
              List<String> json = (List) requestStream.readObject();
                ClientRequest client = objectMapper.readValue(json.get(0), ClientRequest.class);
                String route = client.getRoute();
                String action = client.getAction();
             String response = null;
                switch (route){
                    case "/companyregistration":
//                        logic related to company registration
                        break;
                    case "/users":
					response = userController.mainMethod(client);
                        break;
                    case "/products":
                        ProductController productController = new ProductController();
                 response=productController.processProduct(client);
                    		
                        break;
                    case "/inventory":
                        InventoryController inventoryController = new InventoryController();
                        if (action.equals("POST")){
                            InventoryModel inventoryModel = objectMapper.convertValue(client.getData(), InventoryModel.class);
                            response = inventoryController.addInventory(inventoryModel);
                        }
                        else if(action.equals("GET")){
                            int clientData = (int) client.getData(); // the user id entered
                            response = inventoryController.getInventory(clientData);
                            System.out.println(response);
                            break;
                        }
                        else if(action.equals("DELETE")){
                            int clientData = (int) client.getData(); // the inventory Id entered
                            response = inventoryController.deleteInventory(clientData);
                            System.out.println(response);
                            break;
                        }
                        else if(action.equals("VIEW")){
                            int clientData = (int) client.getData(); // Inventory id entered
                            response = inventoryController.viewSingleRecord(clientData);
                            System.out.println(response);
                            break;
                        }
                        else if(action.equals("UPDATE")){
                            int clientData = (int) client.getData(); // the inventory id where we are going to update

                        }
                        else if(action.equals("VIEW QUANTITY")){
                            List<Integer> requestBody = (List<Integer>) client.getData();
                            int branchId = requestBody.get(0);
                            int productId = requestBody.get(1);
                            response = inventoryController.checkCurrentProductQuantityInStock(productId, branchId);
                        }
                        break;
                    case "/delivery/vehicles":
                       response = vehicleManagementController.mainMethod(client);
                        break;
                    case "/reporting":
//                        logic related to reporting
                        break;
                    case "/testing":
                      response = TestingController.test(client);
                        break;
                     case "/billing":
                      response = billingController.processPayment(client);
                      break;
                }

            //return response to the client;
            assert response != null;
            responseStream.writeUTF(response);


        } catch (EOFException e){
            System.out.println("received data");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
>>>>>>> 768c3f3addc3d44775ad4f3e9ad1125eefcef0d8
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

<<<<<<< HEAD
}

=======
}
>>>>>>> 768c3f3addc3d44775ad4f3e9ad1125eefcef0d8
