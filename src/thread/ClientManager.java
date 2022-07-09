package thread;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.BillingController;
import controllers.DeliveryModule.VehicleManagementController;
import controllers.InventoryController;
import controllers.ProductController;
import controllers.ReportModule.ReportController;
import controllers.TestingController;
import controllers.CompanyModule.CompanyManagementController;
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
    private VehicleManagementController vehicleManagementController1 = new VehicleManagementController();
    private BillingController billingController1 = new BillingController();
    private ReportController reportController1 = new ReportController();
    private final ReportController reportController = new ReportController();
    private final VehicleManagementController vehicleManagementController = new VehicleManagementController();
    private final BillingController billingController = new BillingController();
    UserController userController=new UserController();
    public ClientManager(Socket socket) throws SQLException {

        this.clientSocket = socket;
    }
    @Override
    public void run() {
        DataOutputStream responseStream = null;
        ObjectInputStream requestStream = null;
        try {
            requestStream = new ObjectInputStream(clientSocket.getInputStream());
            responseStream = new DataOutputStream(clientSocket.getOutputStream());
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
                    case "/company":
                        CompanyManagementController companyManagementController = new CompanyManagementController();
                        if(action.equals("INSERT")){
                            Company company = objectMapper.convertValue(client.getData(),Company.class);
                            response = companyManagementController.addCompany(company);
                        }else if(action.equals("GET")){
//                             companyList = companyManagementController.getCompanies();
                        	break;
                        }
                        else if(action.equals("UPDATE")){
                        	int clientData = (int) client.getData(); // the inventory id where we are going to update
                        	break;
                        }
                        else if(action.equals("VIEW QUANTITY")){
                        List<Integer> requestBody = (List<Integer>) client.getData();
                        int branchId = requestBody.get(0);
                        int productId = requestBody.get(1);
                        InventoryController inventoryController1 = new InventoryController();
						response = inventoryController1.checkCurrentProductQuantityInStock(productId, branchId);
						break;
                    }
                case "/delivery/vehicles":
                    response = vehicleManagementController1.mainMethod(client);
                    break;
                case "/reports":
                   response = reportController1.mainMethod(client);
                    break;
                case "/testing":
                    response = TestingController.test(client);
                    break;
                case "/billing":
                    response = billingController1.processPayment(client);
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
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

}