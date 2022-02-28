package main;
import Thread.ClientManager;
import utils.*;
import Utils.ErrorMessageLogger;
import Utils.SuccessMessageLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/***
@author: Mudahemuka Manzi
 */
public class LogisticsServer {
    private static final int serverPort = 5450;
    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        ErrorMessageLogger logError = new ErrorMessageLogger();
        SuccessMessageLogger successLog = new SuccessMessageLogger();
        DatabaseConnection dbConn = new DatabaseConnection();
        try {
//            initializing server on port 5450
            server = new ServerSocket(serverPort);
            server.setReuseAddress(true);
            successLog.log("                   -----------------------   server is listening on port "+serverPort+"  --------------------------------");
            dbConn.init();
            while(true){
                //accepting new client request
                Socket client = server.accept();
                System.out.println("Client with address: "+
                        client.getInetAddress().getHostAddress()+" is connected");
                //assigning client request to a new thread
                ClientManager clientManager = new ClientManager(client);
                new Thread(clientManager).start();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(server !=null){
                //close server
                try {
                    server.close();
                }catch (Exception e){
                    e.printStackTrace();
                    logError.log(e.getMessage());
                }
            }
        }

    }
}


