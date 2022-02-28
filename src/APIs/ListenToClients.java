package APIs;

import java.io.*;
import java.net.*;

public class ListenToClients {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(5000);
        System.out.println("Attempting to connect to "+serverSocket.getLocalSocketAddress());
        try {
            while(true) {
                Socket server = serverSocket.accept();
                System.out.println("Connected to "+server.getRemoteSocketAddress());
                DataInputStream inFromClient=new DataInputStream(server.getInputStream());
                System.out.println(inFromClient.readUTF());
                DataOutputStream outToClient=new DataOutputStream(server.getOutputStream());
                outToClient.writeUTF("From server...");
                server.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
