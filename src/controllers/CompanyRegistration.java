package controllers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CompanyRegistration {
    public static void main(String[] args) throws IOException {
        try {
                while(1==1) {
                    ServerSocket serverSocket = new ServerSocket(8987);
//                    serverSocket.setSoTimeout(1000000);
                    Socket socket = serverSocket.accept();
                    InputStream inFromClient = socket.getInputStream();
                    DataInputStream request = new DataInputStream(inFromClient);
                    System.out.println(request.readUTF());
                    
                    //RESPONSE Part
                    OutputStream responseToClient = socket.getOutputStream();
                    DataOutputStream response = new DataOutputStream(responseToClient);
                    response.writeUTF("Server processing your request.....Please wait.......");
                    socket.close();
                    inFromClient.close();
                    request.close();
                    response.close();
                    serverSocket.close();
                }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}