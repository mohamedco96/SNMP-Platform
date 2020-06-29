/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.servlets;

import com.snmp.daos.AlarmsDAO;
import com.snmp.entities.Alarms;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
@WebServlet(value = "/ClearAlarmServlet")
public class ClearAlarmServlet extends HttpServlet {

    AlarmsDAO alarmsDAO = new AlarmsDAO();
    Alarms alarm = new Alarms();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("#######"+req.getParameter("node_id"));
            alarm.setNode_id(Integer.parseInt(req.getParameter("node_id")));
            alarmsDAO.ClearAlarms(alarm);
            resp.sendRedirect("./index.jsp");
    }
}
