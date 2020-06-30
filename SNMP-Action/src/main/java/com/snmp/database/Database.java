package com.snmp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asalah
 */
public class Database {

    private static final String url = "jdbc:postgresql://rogue.db.elephantsql.com:5432/gcfnairp";
    private static final String user = "gcfnairp";
    private static final String password = "Le7UbRU3FwVaAMZdGPFGCYwsCj63_Zm1";
    private static Connection conn = null;
//    private static Database database = null;

    private Database() {
    }



    public static void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is made successfully");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed() || !conn.isValid(3)) {
                connect();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }

    public static void disconnect() {
        try {
            conn.close();
            System.out.println("Connection is closed successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
