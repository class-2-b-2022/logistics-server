package thread;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.BillingController;
import controllers.DeliveryModule.VehicleManagementController;
import controllers.TestingController;
import models.ClientRequest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

/**
 * @author : Mudahemuka Manzi
 * @author : Ntagungira Ali Rashid
 */
public class ClientManager implements Runnable{
    private Socket clientSocket;
    private VehicleManagementController vehicleManagementController = new VehicleManagementController();
    private BillingController billingController = new BillingController();

    public ClientManager(Socket socket) throws SQLException {

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
//                        int data = (int) client.getData();
//                        response = new ProductController().getProducts(data);
//                        System.out.println(response);
                        break;
                    case "/inventory":
//                        InventoryController inventoryController = new InventoryController();
//                        if (action.equals("POST")){
//                            InventoryModel inventoryModel = objectMapper.convertValue(client.getData(), InventoryModel.class);
//                            response = inventoryController.addInventory(inventoryModel);
//                        }
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
//                        response = billingController.processPayment(client);
                }

                //return response to the client;
                responseStream.writeUTF(response);


                } catch (JsonMappingException jsonMappingException) {
            jsonMappingException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }



    }

}







