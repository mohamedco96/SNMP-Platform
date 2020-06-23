/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.servlets;

import com.snmp.daos.ActionDAO;
import com.snmp.daos.NodesDAO;
import com.snmp.entities.Action;
import com.snmp.entities.Nodes;
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
@WebServlet(value = "/deleteAction")
public class ActionDeleteServlet extends HttpServlet {

    ActionDAO actionDao = new ActionDAO();
    Action action = new Action();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            action.setId(Integer.parseInt(req.getParameter("NodeId")));
            actionDao.deleteAction(action);
            resp.getWriter().print("success");
        } catch (Exception e) {
            e.getMessage();
        }

    }

}
