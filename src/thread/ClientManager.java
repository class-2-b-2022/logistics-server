package thread;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.BillingController;
import controllers.DeliveryModule.VehicleManagementController;
import controllers.InventoryController;
import controllers.ProductController;
import controllers.TestingController;
import models.BillingModel;
import models.ClientRequest;
import models.InventoryModel;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author : Mudahemuka Manzi
 * @author : Ntagungira Ali Rashid
 */
public class ClientManager implements Runnable{
    private Socket clientSocket;
    private VehicleManagementController vehicleManagementController = new VehicleManagementController();

    private BillingController billingController;

    {
        try {
            billingController = new BillingController();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    public ClientManager(Socket socket) throws SQLException {
    public ClientManager(Socket socket){
        this.clientSocket = socket;
    }
    @Override
    public void run() {
        DataOutputStream responseStream = null;
        ObjectInputStream requestStream = null;
        ClientRequest req= null;
        try {
            requestStream = new ObjectInputStream(clientSocket.getInputStream());
            responseStream = new DataOutputStream(clientSocket.getOutputStream());
            System.out.println("New client with adresss: "+ clientSocket.getInetAddress().getHostAddress());
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> clientRequest;
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
//                        logic related to user management
                        break;
                    case "/products":
                        int data = (int) client.getData();
                        response = new ProductController().getProducts(data);
                        System.out.println(response);
                        break;
                    case "/inventory":
                        InventoryController inventoryController = new InventoryController();
                        if (action.equals("POST")){
                            InventoryModel inventoryModel = objectMapper.convertValue(client.getData(), InventoryModel.class);
                            response = inventoryController.addInventory(inventoryModel);
                        }
                        if(action.equals("GET")){
                            int clientData = (int) client.getData(); // the user id entered
                            response = new InventoryController().getInventory(clientData);
                            System.out.println(response);
                            break;
                        }
                        if(action.equals("DELETE")){
                            int clientData = (int) client.getData(); // the inventory Id entered
                            response = new InventoryController().deleteInventory(clientData);
                            System.out.println(response);
                            break;
                        }
                        if(action.equals("VIEW")){
                            int clientData = (int) client.getData(); // Inventory id entered
                            response = new InventoryController().viewSingleRecord(clientData);
                            System.out.println(response);
                            break;
                        }
                        if(action.equals("UPDATE")){
                            int clientData = (int) client.getData(); // the inventory id where we are going to update

                        }
                        break;
                    case "/delivery/vehicles":
//                        responseData = vehicleManagementController.mainMethod(clientRequest);
                        break;
                    case "/reporting":
//                        logic related to reporting
                        break;
                    case "/testing":
                      response = TestingController.test(client);
                        break;
                     case "/billing":
                       response = billingController.processPayment(client);
                }

                //return response to the client;
            assert response != null;
            responseStream.writeUTF(response);


        } catch (EOFException e){
            System.out.println("received data");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}







