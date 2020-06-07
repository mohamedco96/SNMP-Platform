/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.servlets;

//import com.billingsystem.entities.Users;
//import com.billingsystem.database.OldDatabase;
import com.snmp.daos.*;
import com.snmp.entities.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moham
 */
@WebServlet(value = "/addAdmin")
public class CustomerUpdateServlet extends HttpServlet {
    
    UsersDAO ud = new UsersDAO();
    Users user = new Users();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user.setUname(req.getParameter("uname"));
        user.setFname(req.getParameter("fname"));
        user.setLname(req.getParameter("lname"));
        user.setEmail(req.getParameter("email"));
        user.setPhone(req.getParameter("phone"));
        user.setPasswd(req.getParameter("passwd"));
        ud.save(user);
        resp.sendRedirect("./index.jsp");
    }
}
