/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.daos;

import com.snmp.database.Database;
import com.snmp.entities.Users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersDAO implements DAO<Users> {

    private final Connection conn = Database.getConnection();

    @Override
    public Users get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Users> getAll() {
        ArrayList<Users> allUsers = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from users";

        try (Statement stmt1 = conn.createStatement();){
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
