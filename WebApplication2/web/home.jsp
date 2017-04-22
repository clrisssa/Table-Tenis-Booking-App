<%-- 
    Document   : home
    Created on : 22 Apr, 2017, 5:26:58 PM
    Author     : Clarissa Poedjiono
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="MODEL.Employee"%>


<%@include file = "protect.jsp" %> 

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!DOCTYPE html>
        <title>Table Tennis Booking App</title>
    </head>
    <body background="img/home2.jpg" style="background-size:100%">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="home.jsp">Table Tennis Booking App</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="home.jsp">Home</a></li>
                    <li><a href="addBooking.jsp" >Add Booking</a></li>
                    <li><a href="viewBooking.jsp">Booking History</a></li> 
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Sign Out</a></li>
                </ul>

            </div>
        </nav>

        <%            
            Employee employee = (Employee) session.getAttribute("userSession");
            String name = employee.getName();
            
        %>
            <div class="container text-center">
        <div class="panel panel-primary">

            <div class="panel-heading"><b>Welcome to Table Tennis Booking App, <%=name%> </b></div>
                        <form action="addBooking.jsp" method="post">
            </div>
                        <button type='submit' name='button' class="btn btn-success btn-lg">Book A Table</button>
            </div>

    </body>
</html>
