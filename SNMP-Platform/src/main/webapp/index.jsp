<%-- 
    Document   : index
    Created on : Jun 7, 2020, 9:05:33 AM
    Author     : moham
--%>

<%@page import="java.util.Vector"%>
<%@page import="com.snmp.entities.Nodes"%>
<%@page import="com.snmp.daos.NodesDAO"%>
<%@page import="com.snmp.entities.Users"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.snmp.daos.UsersDAO"%>
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
        <link href="./css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="./css/mdb.min.css" rel="stylesheet">
        <link href="./css/style.min.css" rel="stylesheet">
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

        .btn-primary {
            background-color: #ffffff!important;
            color: #343a40;
        }
    </style>

    <body class="grey lighten-3">
        <!--Main Navigation-->
        <header>
            <!-- Sidebar -->
            <div class="sidebar-fixed position-fixed">
                <a class="logo-wrapper waves-effect">
                    <img src="./img/logo.png" class="img-fluid" alt="">
                </a>
                <div class="list-group list-group-flush">
                    <a href="index.jsp" class="list-group-item active waves-effect">
                        <i class="fas fa-chart-pie mr-3"></i>Dashboard
                    </a>
                    <a href="./pages/adminList.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-user mr-3"></i>List of Admins</a>

                    <a href="./pages/Nodes.jsp" class="list-group-item list-group-item-action waves-effect">
                        <i class="fas fa-server mr-3"></i>Nodes</a>


                </div>
            </div>
            <!-- Sidebar -->
        </header>

        <!--Main Navigation-->
        <!--Main layout-->
        <main class="pt-5 mx-lg-5">
            <!--Navbar -->
            <nav class="mb-1 navbar navbar-expand-lg navbar-dark info-color">
                <a class="navbar-brand" href="./index.jsp">Dashboard</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent-4"
                        aria-controls="navbarSupportedContent-4" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent-4">
                    <ul class="navbar-nav ml-auto">


                        <%Cookie ck[] = request.getCookies();
//                            System.out.println("####" + ck[1].getName());
                        %>

                        <li class="nav-item active">
                            <a class="nav-link" href="javascript:void(0)" data-toggle="modal" data-target="#login">
                                <i class="fas fa-sign-in-alt"></i> Login
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0)" data-toggle="modal" data-target="#register">
                                <i class="fas fa-user-plus"></i> Add Admin</a>
                        </li>
                        <%
                            if (ck[1].getValue().equals("yes")) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0)" data-toggle="modal" data-target="#register">
                                <i class="fas fa-user-plus"></i>Sign Out</a>
                        </li>
                        <%}%>
                    </ul>
                </div>
            </nav>

            <%
                UsersDAO usersDAO = new UsersDAO();
                NodesDAO NodesDAO = new NodesDAO();

                ArrayList<Users> listOfUsers = usersDAO.getAll();
                ArrayList<Nodes> listOfNodes = NodesDAO.getAll();
                ArrayList<Nodes> listOfActiveNode = NodesDAO.ActiveNode();
                ArrayList<Nodes> listOfDeactivateNode = NodesDAO.DeactivateNode();
            %>
            <!-- Counter -->
            <div class="container" style="margin-top: 50px">
                <div class="row">
                    <div class="four col-md-3">
                        <div class="counter-box "> <i class="fa fa-user"></i> <span class="counter"><%=listOfUsers.size()%></span>
                            <p>Admins</p>
                        </div>
                    </div>
                    <div class="four col-md-3">
                        <div class="counter-box"> <i class="fa fa-user"></i> <span class="counter"><%=listOfNodes.size()%></span>
                            <p>Nodes</p>
                        </div>
                    </div>
                    <div class="four col-md-3">
                        <div class="counter-box"> <i class="fa fa-user"></i> <span class="counter"><%=listOfActiveNode.size()%></span>
                            <p>Active Nodes</p>
                        </div>
                    </div>
                    <div class="four col-md-3">
                        <div class="counter-box"> <i class="fa fa-user"></i> <span class="counter"><%=listOfDeactivateNode.size()%></span>
                            <p>Deactivate Nodes</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Login Modal -->
            <div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">

                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <!-- Default form login -->
                            <form class="text-center border border-light p-5" action="./addAdmin" method="POST">

                                <p class="h4 mb-4">Sign in</p>

                                <!-- Email -->
                                <input type="text" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="User name" name="uname">

                                <!-- Password -->
                                <input type="password" id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Password" name="passwd">

                                <input type="hidden" name="operation" value="login">

                                <!-- Sign in button -->
                                <button class="btn btn-info btn-block my-4" type="submit">Sign in</button>


                            </form>
                            <!-- Default form login -->
                        </div>

                    </div>
                </div>
            </div>

            <!-- Register Modal -->
            <div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <!-- Default form register -->
                            <form class="text-center border border-light p-5" action="./addAdmin" method="POST">

                                <p class="h4 mb-4">Sign up</p>

                                <div class="form-row mb-4">
                                    <div class="col">
                                        <!-- First name -->
                                        <input type="text" id="defaultRegisterFormFirstName" class="form-control" placeholder="First name" name="fname">
                                    </div>
                                    <div class="col">
                                        <!-- Last name -->
                                        <input type="text" id="defaultRegisterFormLastName" class="form-control" placeholder="Last name" name="lname">
                                    </div>
                                </div>

                                <!-- user name -->
                                <input type="text" id="defaultRegisterFormEmail" class="form-control mb-4" placeholder="User Name" name="uname">

                                <!-- E-mail -->
                                <input type="email" id="defaultRegisterFormEmail" class="form-control mb-4" placeholder="E-mail" name="email">

                                <!-- PHONE -->
                                <input type="text" id="defaultRegisterFormEmail" class="form-control mb-4" placeholder="Phone Number" name="phone">

                                <!-- Password -->
                                <input type="password" id="defaultRegisterFormPassword" class="form-control" placeholder="Password" aria-describedby="defaultRegisterFormPasswordHelpBlock" name="passwd">
                                <input type="hidden" name="operation" value="addAdmin">
                                <!-- Sign up button -->
                                <button class="btn btn-info my-4 btn-block" type="submit">Sign UP</button>

                            </form>
                            <!-- Default form register -->
                        </div>
                    </div>
                </div>
            </div>


            <div class="row row-cols-1 row-cols-md-3" style="margin-top: 50px">
                <%
                    for (Nodes node : listOfNodes) {
                %>

                <%
                    if (node.isStatus() == true) {
                %>
                <!-- Card -->
                <div class="col mb-4">
                    <div class="card text-white bg-success mb-4" style="max-width: 20rem;">
                        <div class="card-header"><%=node.getName()%></div>
                        <div class="card-body">
                            <h5 class="card-title"><%=node.getIp()%></h5>
                            <p class="card-text text-white"><%=node.getDes()%></p>
                        </div>
                        <div class="card-footer text-muted text-center mt-4">
                            <!--<button type="button" class="btn btn-primary btn-md">Read more</button>-->
                            <form  action="./addAdmin" method="POST">
                                <input type="hidden" name="operation" value="login">
                                <button class="btn btn-info  my-4" type="submit">Sign in</button>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Card -->
                <%}%>

                <%
                    if (node.isStatus() == false) {
                %>
                <!-- Card -->
                <div class="col mb-4">
                    <div class="card text-white bg-danger mb-4" style="max-width: 20rem;">
                        <div class="card-header"><%=node.getName()%></div>
                        <div class="card-body">
                            <h5 class="card-title"><%=node.getIp()%></h5>
                            <p class="card-text text-white"><%=node.getDes()%></p>
                        </div>
                        <div class="card-footer text-muted text-center mt-4">
                            <!--<button type="button" class="btn btn-primary btn-md">Read more</button>-->
                            <div class="d-flex justify-content-center">
                                <form  action="./addAdmin" method="POST">
                                    <input type="hidden" name="operation" value="login">
                                    <button class="btn btn-primary btn-md" type="submit">View Alarms</button>
                                </form>
                                <form  action="./addAdmin" method="POST">
                                    <input type="hidden" name="operation" value="login">
                                    <button class="btn btn-primary btn-md " type="submit">Alarms History</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Card -->
                <%}%>

                <%}%>
            </div>

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
        <script>
            $(document).ready(function () {

                $('.counter').each(function () {
                    $(this).prop('Counter', 0).animate({
                        Counter: $(this).text()
                    }, {
                        duration: 4000,
                        easing: 'swing',
                        step: function (now) {
                            $(this).text(Math.ceil(now));
                        }
                    });
                });

            });
        </script>
        <!-- SCRIPTS -->
    </body>
</html>