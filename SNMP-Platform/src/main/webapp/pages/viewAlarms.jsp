<%-- 
    Document   : viewAlarms
    Created on : Jun 19, 2020, 11:43:06 AM
    Author     : moham
--%>

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
                ArrayList<Alarms> listOfAlarms = alarmsDAO.ViewAlarms(request.getParameter("node_id"));
            %>

            <!-- Table with panel -->
            <div class="card card-cascade narrower" style="margin-top: 50px">
                <!--Card image-->
                <div class="view view-cascade gradient-card-header blue-gradient narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center rounded text-center">
                    <p href="" class="white-text mx-3">Alarms for <%=request.getParameter("node_name")%> </p>
                </div>
                <!--/Card image-->
                <div class="px-4">
                    <div class="table-wrapper">
                        <!--Table-->
                        <table class="table table-hover mb-0">
                            <!--Table head-->
                            <thead>
                                <tr>
                                    <th>
                                        #
                                    </th>
                                    <th class="th-lg">
                                        Node ID
                                    </th>
                                    <th class="th-lg">
                                        Alarm Type
                                    </th>
                                    <th class="th-lg">
                                        OID	
                                    </th>
                                    <th class="th-lg">
                                        Description
                                    </th>
                                    <th class="th-lg">
                                        status
                                    </th>
                                </tr>
                            </thead>
                            <!--Table head-->
                            <!--Table body-->
                            <tbody>
                                <%
                                    for (Alarms alarm : listOfAlarms) {
                                %>
                                <tr>
                                    <th scope="row"><%=alarm.getId()%></th>
                                    <td><%=alarm.getNode_id()%></td>
                                    <td><%=alarm.getAlarm_type()%></td>
                                    <td><%=alarm.getOid()%></td>
                                    <td><%=alarm.getDes()%></td>
                                    <td><%=alarm.isStatus()%></td>
                                    <td>
                                        <form  action="./MakeAction.jsp" method="POST">
                                            <input type="hidden" name="node_id" value="<%=alarm.getNode_id()%>">
                                            <input type="hidden" name="Alarm_type" value="<%=alarm.getAlarm_type()%>">
                                            <input type="hidden" name="oid" value="<%=alarm.getOid()%>">
                                            <button class="btn btn-primary btn-md " type="submit">Make Action</button>
                                        </form>
                                    </td>
                                </tr>
                                <% }%> 
                            </tbody>
                            <!--Table body-->
                        </table>
                        <!--Table-->
                    </div>
                </div>
            </div>
            <!-- Table with panel -->



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
