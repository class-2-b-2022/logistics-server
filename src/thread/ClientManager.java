package thread;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.DeliveryModule.VehicleManagementController;
import controllers.TestingController;
import models.BillingModel;
import models.ClientRequest;
import java.io.*;
import java.net.Socket;
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
             String response = null;
                switch (route){
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
//                        responseData = vehicleManagementController.mainMethod(clientRequest);
                        break;
                    case "/reporting":
//                        logic related to reporting
                        break;
                    case "/testing":
                      response = TestingController.test(client);
                        break;
                }

                //return response to the client;
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







