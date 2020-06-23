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
@WebServlet(value = "/addAction")
public class ActionUpdateServlet extends HttpServlet {

    ActionDAO actionDAO = new ActionDAO();
    Action action = new Action();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        action.setId(Integer.parseInt(req.getParameter("id")));
        action.setNode_id(Integer.parseInt(req.getParameter("NodeID")));
        action.setAlarm_type(req.getParameter("Alarm_type"));
        action.setAction(req.getParameter("action"));
//        action.setDate(req.getParameter("des"));
//        nodeDao.save(node);

        int newId = 0;
        boolean updateSuccess = false;

        if (action.getId() == 0) {
            actionDAO.insert(action);
        } else {
            updateSuccess = actionDAO.update(action);
        }

        resp.setContentType("text/plain");
        if (newId != 0) {
            resp.getWriter().print(Integer.toString(newId));
        } else if (updateSuccess) {
            resp.getWriter().print(Integer.toString(action.getId()));
        }

    }

}
