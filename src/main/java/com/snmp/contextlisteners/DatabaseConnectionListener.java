/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.contextlisteners;

import com.snmp.database.Database;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;


public class DatabaseConnectionListener implements ServletContextListener {

    Connection conn = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Database.connect();
        conn = Database.getConnection();
        sce.getServletContext().setAttribute("dbConnection", conn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Database.disconnect();
    }
}
