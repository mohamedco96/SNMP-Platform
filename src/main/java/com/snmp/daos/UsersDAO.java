/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.daos;

import com.snmp.database.Database;
import com.snmp.entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersDAO implements DAO<Users> {
private ResultSet result = null;
    private final Connection conn = Database.getConnection();

    @Override
    public Users get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Users> getAll() {
        ArrayList<Users> allUsers = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from users";

        try (Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Users user = new Users();
                user.setId(rs1.getInt("id"));
                user.setUname(rs1.getString("username"));
                user.setFname(rs1.getString("firstname"));
                user.setLname(rs1.getString("lastname"));
                user.setEmail(rs1.getString("email"));
                user.setPhone(rs1.getString("phone"));
                user.setPasswd(rs1.getString("passwd"));

                allUsers.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("##### Users get all faild: \n" + ex.getMessage());
        }
        return allUsers;
    }

    @Override
    public boolean save(Users t) {
        boolean operationSuccess = true;
        String sqlCommand = " insert into users(username,firstname,lastname,email,phone,passwd) values (?,?,?,?,?,?)";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, t.getUname());
            preparedStatment.setString(2, t.getFname());
            preparedStatment.setString(3, t.getLname());
            preparedStatment.setString(4, t.getEmail());
            preparedStatment.setString(5, t.getPhone());
            preparedStatment.setString(6, t.getPasswd());

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### user insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    public boolean login(Users t) {
        boolean operationSuccess = false;
        String sqlCommand = " select * from users where username = ? AND passwd = ?";
        try(PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, t.getUname());
            preparedStatment.setString(2, t.getPasswd());
            result = preparedStatment.executeQuery();

            while (result.next()) {
                operationSuccess = true;
            }
        } catch (SQLException ex) {
            System.out.println("##### user login faild: \n" + ex.getMessage());
        } finally {
            
            return operationSuccess;
        }
    }

    @Override
    public boolean update(Users t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
