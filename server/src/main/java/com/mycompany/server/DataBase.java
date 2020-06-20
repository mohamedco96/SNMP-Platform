/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M Gamal
 */
public class DataBase {

    private final String url = "jdbc:postgresql://localhost:5544/snmp";
    private final String user = "postgres";
    private final String password = "mohamed";
    private Connection connection;
    private String sqlcommand;
    private PreparedStatement preparedstatement;
    private ResultSet result;

    private void connect() {

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to Database Succeeded");

        } catch (SQLException | ClassNotFoundException ex) {

            System.out.println("databaseconnection.dataBase.connect()error");
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void stop() {

        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String get_id(String ip) throws SQLException {
        connect();
        sqlcommand = "select * from nodes where ip = ? ";
        preparedstatement = connection.prepareStatement(sqlcommand);
        preparedstatement.setString(1,ip );
        result=preparedstatement.executeQuery();
        System.out.println(result);
        result.next();
        String ip_=result.getString("id");
        return ip_;
    }

    public void insert_alarm(String ip, String oid, String alarm_type,String des ,boolean state) throws SQLException {
        connect();
                System.out.println(get_id(ip));

        PreparedStatement sql=connection.prepareStatement("insert into alarms(node_id,alarm_type,oid,des,status) values(?,?,?,?,?)");
        sql.setInt(1, Integer.parseInt(get_id(ip)));
        sql.setString( 2, alarm_type);
        sql.setString(3, oid);
        sql.setString(4, des);
        sql.setBoolean(5, state);
          System.out.println(sql.execute());
        PreparedStatement sql_update=connection.prepareStatement("UPDATE nodes set status=?  where id=?;");
        sql_update.setInt(2, Integer.parseInt(get_id(ip)));
        sql_update.setBoolean(1, state);
        System.out.println(sql_update.execute());

    }
}
