<%-- 
    Document   : RunAction
    Created on : Jun 20, 2020, 11:19:29 PM
    Author     : asalah
--%>

<%@page import="com.snmp.messaging.RunScript"%>
<%@page import="com.snmp.messaging.nxSms"%>
<%@page import="com.snmp.messaging.email"%>
<%@page import="com.snmp.entities.Nodes"%>
<%@page import="com.snmp.daos.NodesDAO"%>
<%@page import="com.snmp.entities.Action"%>
<%@page import="com.snmp.daos.ActionDAO"%>
<%@page import="com.snmp.entities.Alarms"%>
<%@page import="com.snmp.daos.AlarmsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.snmp.daos.UsersDAO"%>
<%@page import="com.snmp.entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    email e = new email();
    nxSms sms = new nxSms();
    RunScript run =new RunScript();

    if (request.getParameter("action").equals("email")) {
        e.sendMail(request.getParameter("email"), "We found Alarm type is " + request.getParameter("Alarm_type"));
        System.out.println("Email is send to " + request.getParameter("email"));
        response.sendRedirect("../index.jsp");
        return;
    } else if (request.getParameter("action").equals("sms")) {
        System.out.println("########## send SMS");
        sms.sendSMS(request.getParameter("phone"), request.getParameter("Alarm_type"));
        System.out.println("Message is send to " + request.getParameter("phone"));
        response.sendRedirect("../index.jsp");
        return;
    } else if (request.getParameter("action").equals("script")) {
        System.out.println("########## script");
        if (request.getParameter("Alarm_type").equals("hard is full")) {
             
            run.Script("./src/test.sh");
            System.out.println("########## script for hard is full");
            response.sendRedirect("../index.jsp");
            return;
        }
        else if (request.getParameter("Alarm_type").equals("memory is full")) {
             
            run.Script("./src/test1.sh");
            System.out.println("########## script for memory is full");
            response.sendRedirect("../index.jsp");
            return;
        }

    }
%>