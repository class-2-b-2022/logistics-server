package Thread;

import controllers.BillingController;

import controllers.DeliveryModule.VehicleManagementController;
import models.BillingModel;
import models.ClientRequest;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * @author : Mudahemuka Manzi
 * @author : Ntagungira Ali Rashid
 */
public class ClientManager implements Runnable{
    private Socket clientSocket;
    private VehicleManagementController vehicleManagementController = new VehicleManagementController();
    private BillingController billingController = new BillingController();

    public ClientManager(Socket socket){
        this.clientSocket = socket;
    }
    @Override
    public void run() {
        ObjectOutputStream responseStream = null;
        ObjectInputStream requestStream = null;
//        System.out.println("This is where all logic for handling client request will be managed");
        //get client request;
        try {
            //get client request stream
            requestStream = new ObjectInputStream(clientSocket.getInputStream());

            //get stream to respond to client
            responseStream = new ObjectOutputStream(clientSocket.getOutputStream());
            ClientRequest clientRequest;
            System.out.println(requestStream.readObject());
            while((clientRequest =(ClientRequest) requestStream.readObject()) !=null){
                  //Get request route
                System.out.println(clientRequest.getRoute());
                BillingModel bill = (BillingModel) clientRequest.getData();
                System.out.println(bill.getAmount());
                String route = clientRequest.getRoute();
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
                    case "/billing":
                        responseData = billingController.mainMethod(clientRequest);

                    case "/testing":
//                        TestingController.test(clientRequest);
                        break;
                }
                //return response to the client;
//                responseStream.writeObject(responseData);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
