package main;

import models.ReportInfo;
import models.ReportModel;
import thread.ClientManager;
import utils.DatabaseConnection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args){

        try {

            ServerSocket server = new ServerSocket(9080);
            Socket client = server.accept();

            DatabaseConnection db = new DatabaseConnection();
            db.init();

            while(true) {
                ClientManager clientManager = new ClientManager(client);
                new Thread(clientManager).start();
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}

