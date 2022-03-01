package main;

import Utils.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseConnection dbConn = new DatabaseConnection();

        System.out.println("Hello world!!!");
    }
}
