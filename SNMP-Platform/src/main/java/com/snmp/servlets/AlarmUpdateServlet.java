/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.servlets;

import com.snmp.daos.AlarmsDAO;
import com.snmp.daos.UsersDAO;
import com.snmp.entities.Alarms;
import com.snmp.entities.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
@WebServlet(value = "/AlarmUpdateServlet")
public class AlarmUpdateServlet extends HttpServlet {

    AlarmsDAO alarmsDAO = new AlarmsDAO();
    Alarms alarm = new Alarms();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
            alarm.setId(Integer.parseInt(req.getParameter("id")));
            alarmsDAO.update(alarm);
            resp.sendRedirect("./index.jsp");
    }
}
