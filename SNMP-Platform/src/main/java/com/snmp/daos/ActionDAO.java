/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.daos;

import com.snmp.database.Database;
import com.snmp.entities.Action;
import com.snmp.entities.Nodes;
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
public class ActionDAO implements DAO<Action> {

    private ResultSet result = null;
    private final Connection conn = Database.getConnection();

    @Override
    public Action get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Action> getAll() {
        ArrayList<Action> allAction = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from Action order by id";

        try (Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Action action = new Action();
                action.setId(rs1.getInt("id"));
                action.setNode_id(rs1.getInt("node_id"));
                action.setAlarm_type(rs1.getString("alarm_type"));
                action.setAction(rs1.getString("action"));
                action.setDate(rs1.getDate("date"));

                allAction.add(action);
            }
        } catch (SQLException ex) {
            System.out.println("##### Action get all faild: \n" + ex.getMessage());
        }
        return allAction;
    }

    @Override
    public boolean save(Action t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void insert(Action t) {
        boolean operationSuccess = true;
        String sqlCommand = " insert into action(node_id,alarm_type,action) values (?,?,?)";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, t.getNode_id());
            preparedStatment.setString(2, t.getAlarm_type());
            preparedStatment.setString(3, t.getAction());

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Action insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
    }

    @Override
    public boolean update(Action t) {
        boolean operationSuccess = true;
        String sqlCommand = "update action set node_id = ?, alarm_type = ?, action = ?"
                + " where id = ?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, t.getNode_id());
            preparedStatment.setString(2, t.getAlarm_type());
            preparedStatment.setString(3, t.getAction());
            preparedStatment.setInt(4, t.getId());

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Action Update faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }
    
    public boolean deleteAction(Action t) {
        boolean operationSuccess = true;
        String sqlCommand = "delete from action where id=?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, t.getId());
            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Action delete faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }
    
    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
