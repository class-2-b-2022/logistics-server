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
        ObjectOutputStream responseStream = null;
        ObjectInputStream requestStream = null;
        ClientRequest req= null;
        try {
            requestStream = new ObjectInputStream(clientSocket.getInputStream());
            responseStream = new ObjectOutputStream(clientSocket.getOutputStream());
//            ClientRequest clientRequest;
            List<String> clientRequest;
            System.out.println("New client with adresss: "+ clientSocket.getInetAddress().getHostAddress());
            ObjectMapper objectMapper = new ObjectMapper();
            while((clientRequest =(List) requestStream.readObject()) !=null){
                ClientRequest client = objectMapper.readValue(clientRequest.get(0), ClientRequest.class);
                String route = client.getRoute();
                List<Object> responseData = null;
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
                        TestingController.test(client);
                        break;
                }
                //return response to the client;
//                }
//                //return response to the client;
//                responseStream.writeObject(responseData);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}







