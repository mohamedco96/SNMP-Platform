/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.daos;

import com.snmp.database.Database;
import com.snmp.entities.Nodes;
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
public class NodesDAO implements DAO<Nodes> {

    private ResultSet result = null;
    private final Connection conn = Database.getConnection();

    @Override
    public Nodes get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Nodes> getAll() {
        ArrayList<Nodes> allNodes = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from nodes order by id";

        try (Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Nodes node = new Nodes();
                node.setId(rs1.getInt("id"));
                node.setName(rs1.getString("name"));
                node.setIp(rs1.getString("ip"));
                node.setDes(rs1.getString("des"));
//                node.setAlarm_type(rs1.getString("alarm_type"));
                node.setStatus(rs1.getBoolean("status"));
                
                allNodes.add(node);
            }
        } catch (SQLException ex) {
            System.out.println("##### Nodes get all faild: \n" + ex.getMessage());
        }
        return allNodes;
    }
    
    public ArrayList<Nodes> NodesByID(String node_id) {
        ArrayList<Nodes> allNodes = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from nodes where id=" + node_id + "";
//        String customerJoinRatePlanQuery = "select * from alarms where node_id=" + id + "";

        try (Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Nodes node = new Nodes();
                node.setId(rs1.getInt("id"));
                node.setName(rs1.getString("name"));
                node.setIp(rs1.getString("ip"));
                node.setDes(rs1.getString("des"));
//                node.setAlarm_type(rs1.getString("alarm_type"));
                node.setStatus(rs1.getBoolean("status"));
                

                allNodes.add(node);
            }
        } catch (SQLException ex) {
            System.out.println("##### NodesByID faild: \n" + ex.getMessage());
        }
        return allNodes;
    }
    public ArrayList<Nodes> ActiveNode() {
        ArrayList<Nodes> allNodes = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from nodes where status=false";

        try (Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Nodes node = new Nodes();
                node.setId(rs1.getInt("id"));
                node.setName(rs1.getString("name"));
                node.setIp(rs1.getString("ip"));
//                node.setDes(rs1.getString("des"));
//                node.setAlarm_type(rs1.getString("alarm_type"));
                node.setStatus(rs1.getBoolean("status"));
                
                allNodes.add(node);
            }
        } catch (SQLException ex) {
            System.out.println("##### ActiveNode get all faild: \n" + ex.getMessage());
        }
        return allNodes;
    }
    
    public ArrayList<Nodes> DeactivateNode() {
        ArrayList<Nodes> allNodes = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from nodes where status=true";

        try (Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Nodes node = new Nodes();
                node.setId(rs1.getInt("id"));
                node.setName(rs1.getString("name"));
                node.setIp(rs1.getString("ip"));
//                node.setDes(rs1.getString("des"));
//                node.setAlarm_type(rs1.getString("alarm_type"));
                node.setStatus(rs1.getBoolean("status"));
                
                allNodes.add(node);
            }
        } catch (SQLException ex) {
            System.out.println("##### ActiveNode get all faild: \n" + ex.getMessage());
        }
        return allNodes;
    }
    
    public void insert(Nodes t) {
        boolean operationSuccess = true;
        String sqlCommand = " insert into nodes(name,ip,des) values (?,?,?)";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, t.getName());
            preparedStatment.setString(2, t.getIp());
            preparedStatment.setString(3, t.getDes());

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Nodes insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
    }

    @Override
    public boolean update(Nodes t) {
        boolean operationSuccess = true;
        String sqlCommand = "update nodes set name = ?, ip = ?, des = ?"
                + " where id = ?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, t.getName());
            preparedStatment.setString(2, t.getIp());
            preparedStatment.setString(3, t.getDes());
            preparedStatment.setInt(4, t.getId());

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Nodes Update faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    public boolean deleteNode(Nodes t) {
        boolean operationSuccess = true;
        String sqlCommand = "delete from nodes where id=?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, t.getId());
            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Node delete faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(Nodes t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
