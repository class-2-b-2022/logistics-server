package thread;

import models.ClientRequest;

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
            while((clientRequest =(ClientRequest) requestStream.readObject()) !=null){

                  //Get request route
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
                //return response to the client;
                responseStream.writeObject(responseData);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
