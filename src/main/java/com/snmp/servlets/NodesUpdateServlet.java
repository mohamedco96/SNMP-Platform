/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.servlets;

import com.snmp.daos.NodesDAO;
import com.snmp.entities.Nodes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
@WebServlet(value = "/addNodes")
public class NodesUpdateServlet extends HttpServlet {

    NodesDAO nodeDao = new NodesDAO();
    Nodes node = new Nodes();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        node.setId(Integer.parseInt(req.getParameter("NodeId")));
        node.setName(req.getParameter("NodeName"));
        node.setIp(req.getParameter("ip"));
        node.setDes(req.getParameter("des"));
        node.setAlarm_type(req.getParameter("alarm_type"));
//        nodeDao.save(node);

        int newId = 0;
        boolean updateSuccess = false;

        if (node.getId() == 0) {
            nodeDao.insert(node);
        } else {
            updateSuccess = nodeDao.update(node);
        }

        resp.setContentType("text/plain");
        if (newId != 0) {
            resp.getWriter().print(Integer.toString(newId));
        } else if (updateSuccess) {
            resp.getWriter().print(Integer.toString(node.getId()));
        }

    }

}
