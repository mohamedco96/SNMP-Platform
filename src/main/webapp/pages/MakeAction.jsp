<%-- 
    Document   : MakeAction
    Created on : Jun 20, 2020, 8:44:44 PM
    Author     : moham
--%>

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

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>SNMP Platform</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!-- Bootstrap core CSS -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="../css/mdb.min.css" rel="stylesheet">
        <link href="../css/style.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>


        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/e46fb9d55b.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
    </head>

    <style>
        .pt-5,
        .py-5 {
            margin-bottom: 100px;
        }
    </style>

    <body class="grey lighten-3">
        <!--Main Navigation-->
        <header>
            <!-- Sidebar -->
            <div class="sidebar-fixed position-fixed">
                <a class="logo-wrapper waves-effect">
                    <img src="../img/logo.png" class="img-fluid" alt="">
                </a>
                <div class="list-group list-group-flush">
                    <a href="../index.jsp" class="list-group-item active waves-effect">
                        <i class="fas fa-chart-pie mr-3"></i>Dashboard
                    </a>
                    <a href="./adminList.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-user mr-3"></i>List of Admins</a>

                    <a href="./Nodes.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-server mr-3"></i>Nodes</a>

                    <a href="./action.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-radiation-alt mr-3"></i>Actions</a>
                </div>
            </div>
            <!-- Sidebar -->
        </header>

        <!--Main Navigation-->
        <!--Main layout-->
        <main class="pt-5 mx-lg-5">
            <!--Navbar -->
            <nav class="mb-1 navbar navbar-expand-lg navbar-dark info-color">
                <a class="navbar-brand" href="../index.jsp">Dashboard</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent-4"
                        aria-controls="navbarSupportedContent-4" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

            </nav>

            <%
                AlarmsDAO alarmsDAO = new AlarmsDAO();
                ActionDAO actionDAO = new ActionDAO();
                NodesDAO nodesDAO = new NodesDAO();

                ArrayList<Alarms> listOfAlarms = alarmsDAO.ViewAlarms(request.getParameter("node_id"));
                ArrayList<Action> listOfActions = actionDAO.ActionByID(request.getParameter("node_id"),request.getParameter("Alarm_type"));
                ArrayList<Nodes> listOfNodes = nodesDAO.NodesByID(request.getParameter("node_id"));
             
            %>

            <form  action="./RunAction.jsp" method="POST">

                <p class="h4 mb-4 text-center">Make Action</p>
                <%
                    for (int i = 0; i < listOfNodes.size(); i++) {
                %>
                <input type="text" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="Node Name" name="node_name" value="<%=listOfNodes.get(i).getName()%>">
                <input type="text" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="Node IP" name="node_ip" value="<%=listOfNodes.get(i).getIp()%>">
                <input type="text" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="Status" name="status" value="<%=listOfNodes.get(i).isStatus()%>">
                <%}%>

                <input type="text" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="Alarm Type" name="Alarm_type" value="<%=request.getParameter("Alarm_type")%>">
                <input type="text" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="OID" name="oid" value="<%=request.getParameter("oid")%>">



                <select class="browser-default custom-select mb-4" name="action">
                    <option selected>Actions</option>
                    <%
                        for (int i = 0; i < listOfActions.size(); i++) {
                    %>
                    <option value="<%=listOfActions.get(i).getAction()%>"><%=listOfActions.get(i).getAction()%></option>
                    <%}%>
                </select>

                <input type="text" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="Email" name="email">
                <input type="text" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="Phone Number" name="phone">

                <button class="btn btn-info btn-block my-4" type="submit">submit</button>
            </form>

        </main>
        <!--Main layout-->
        <!--Footer-->
        <footer class="page-footer text-center font-small primary-color-dark darken-2 mt-4 wow fadeIn">
            <hr class="my-4">
            <!-- Social icons -->
            <div class="pb-4">
                <a href="#" target="_blank">
                    <i class="fab fa-facebook-f mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-twitter mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-youtube mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-google-plus mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-dribbble mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-pinterest mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-github mr-3"></i>
                </a>

                <a href="#" target="_blank">
                    <i class="fab fa-codepen mr-3"></i>
                </a>
            </div>
            <!-- Social icons -->
            <!--Copyright-->
            <div class="footer-copyright py-3">
                TELECOM TRACK - INTAKE 40
            </div>
            <!--/.Copyright-->
        </footer>
        <!--/.Footer-->
        <!-- SCRIPTS -->
    </body>
</html>
