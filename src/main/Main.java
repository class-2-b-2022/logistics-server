package main;

import utils.DatabaseConnection;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseConnection dbConn = new DatabaseConnection();

        System.out.println("Hello world!!!");
    }
}
