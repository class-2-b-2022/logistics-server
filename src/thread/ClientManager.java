package thread;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.*;
import controllers.CompanyModule.CompanyManagementController;
import controllers.DeliveryModule.VehicleManagementController;
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
    private VehicleManagementController vehicleManagementController = new VehicleManagementController();
    private BillingController billingController = new BillingController();
    private ReportController reportController = new ReportController();

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
             List<String> clientRequest = null;
             List<String> json = (List) requestStream.readObject();
             List<Object> companyList = null;

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
                        break;
                    case "/delivery/vehicles":
//                        responseData = vehicleManagementController.mainMethod(clientRequest);
                        break;
                    case "/company":
                        CompanyManagementController companyManagementController = new CompanyManagementController();
                        if(action.equals("INSERT")){
                            Company company = objectMapper.convertValue(client.getData(),Company.class);
                            response = companyManagementController.addCompany(company);
                        }else if(action.equals("GET")){
//                             companyList = companyManagementController.getCompanies();
                        }
                        break;
                    case "/reports":
//                      response = reportController.getReports();
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







