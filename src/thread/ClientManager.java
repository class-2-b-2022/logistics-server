package thread;

import controllers.ProductController;
import models.*;
import controllers.InventoryController;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientManager implements Runnable{
    private Socket clientSocket;
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

            while ((clientRequest =(ClientRequest) requestStream.readObject()) !=null){
                  //Get request route
                String route = clientRequest.getRoute();
                switch (route){
                    case "/companyregistration":
//                        logic related to company registration
                        break;
                    case "/users":
//                        logic related to user management
                        break;
                    case "/products":
                        if (clientRequest.getAction().equals("GET")){
                            List<Object> responseData = null;
                            ProductController product = new ProductController();
                            responseData = product.getProducts((int) clientRequest.getData());
                            //return response to the client;
                            responseStream.writeObject(responseData);
                            break;
                        }
                    case "/inventory":
                        if (clientRequest.getAction().equals("POST")){
                             InventoryController inv = new InventoryController();
                            Object inventoryObject;
                            inventoryObject = clientRequest.getData();
                            InventoryModel inventoryModel = (InventoryModel) inventoryObject;
                            DataOutputStream saveResult = new DataOutputStream(clientSocket.getOutputStream());
                            int res = inv.addInventory(inventoryModel);
                            saveResult.writeInt(res);
                        }
                        if (clientRequest.getAction().equals("GET")){
                                List<Object> responseData = null;
                                ProductController product = new ProductController();
                                responseData = product.getProducts((int) clientRequest.getData());
                                //return response to the client;
                                responseStream.writeObject(responseData);
                                break;
                        }
                        break;
                    case "/billing":
//                        logic related to billing
                        break;
                    case "/delivery":
//                        logic related to shipping
                        break;
                    case "/reporting":
//                        logic related to reporting
                        break;

                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }

    }
}
