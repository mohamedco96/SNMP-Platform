/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.daos;

import com.snmp.database.Database;
import com.snmp.entities.Alarms;
import com.snmp.entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author moham
 */
public class AlarmsDAO implements DAO<Alarms> {

    private final Connection conn = Database.getConnection();
    
   
   
   
    @Override
    public Alarms get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Alarms> AlarmsHistory(String node_id) {
        ArrayList<Alarms> allAlarms = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from alarms where node_id=" + node_id + "";
//        String customerJoinRatePlanQuery = "select * from alarms where node_id=" + id + "";

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

    public ArrayList<Alarms> ViewAlarms(String node_id) {
        ArrayList<Alarms> allAlarms = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from alarms where node_id=" + node_id + " and status=true order by id";
//        String customerJoinRatePlanQuery = "select * from alarms where node_id=" + id + "";

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

    @Override
    public boolean save(Alarms t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Alarms t) {
        boolean operationSuccess = true;
        String sqlCommand = "update alarms set status = false"
                + " where id = ?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, t.getId());

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### alarms Update faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Alarms> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
