/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.database;

import com.snmp.database.Database;
import com.snmp.entities.Action;
import com.snmp.entities.Alarms;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author asalah
 */
public class DataBaseDAO {

    private final Connection conn = Database.getConnection();
    private ResultSet result = null;

    public ArrayList<Alarms> getAllAlarms() {
        ArrayList<Alarms> allAlarms = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from alarms where status=true";
//        String customerJoinRatePlanQuery = "select * from alarms where node_id=" + node_id + "";


        try (Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Alarms alarms = new Alarms();
                alarms.setId(rs1.getInt("id"));
                alarms.setNode_id(rs1.getInt("node_id"));
                alarms.setAlarm_type(rs1.getString("alarm_type"));
                alarms.setOid(rs1.getString("oid"));
                alarms.setDes(rs1.getString("des"));
                alarms.setStatus(rs1.getBoolean("status"));

                allAlarms.add(alarms);
            }
        } catch (SQLException ex) {
            System.out.println("##### Alarms get all faild: \n" + ex.getMessage());
        }
        return allAlarms;
    }
    
    public ArrayList<Action> getAllAction(Integer node_id,String alarm_type) {
        ArrayList<Action> allAction = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from action where node_id="+ node_id +" AND alarm_type='"+ alarm_type +"' ";


        try (Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Action action = new Action();
                action.setId(rs1.getInt("id"));
                action.setNode_id(rs1.getInt("node_id"));
                action.setAlarm_type(rs1.getString("alarm_type"));
                action.setAction(rs1.getString("action"));
                action.setDes(rs1.getString("dis"));

                allAction.add(action);
            }
        } catch (SQLException ex) {
            System.out.println("##### Action get all faild: \n" + ex.getMessage());
        }
        return allAction;
    }
    
    public boolean update(Alarms t) {
        boolean operationSuccess = true;
        String sqlCommand = "update alarms set status = false where id = ?";
               
        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, t.getId());
            preparedStatment.executeUpdate();
            

        } catch (Exception ex) {
            System.out.println("##### alarms Update faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

}
