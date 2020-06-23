/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.servlets;

import com.snmp.daos.NodesDAO;
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
@WebServlet(value = "/deleteNode")
public class NodeDeleteServlet extends HttpServlet {

    NodesDAO nodeDao = new NodesDAO();
    Nodes node = new Nodes();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            node.setId(Integer.parseInt(req.getParameter("NodeId")));
            nodeDao.deleteNode(node);
            resp.getWriter().print("success");
        } catch (Exception e) {
            e.getMessage();
        }

    }

}
