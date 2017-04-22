<%-- 
    Document   : addBooking
    Created on : 22 Apr, 2017, 5:56:05 PM
    Author     : Clarissa Poedjiono
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Table Tennis Booking App</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="home.jsp">Table Tennis Booking</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="home.jsp">Home</a></li>
                    <li class="active" class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Manage Bookings
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="addBooking.jsp">Book a Table</a></li>
                            <li><a href="cancelBooking.jsp">Cancel a Booking</a></li>
                        </ul>
                    </li>
                    <li><a href="viewBooking.jsp">Booking History</a></li> 
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Sign Out</a></li>
                </ul>

            </div>
        </nav>
        <div class ="container">
            <form action="AddBookingController" method="post">

                <div class="form-group">
                    <label for="sel1">Date: </label>
                    <input type="date" name="date"/><br/>
                    
                    <label for="sel1">Time: </label>
                    <input type ="time" name="time"/><br/>
                    
                    <label for="sel1">Duration: </label>
                    
                            <input type="radio" name="duration" value="20">20
                            <input type="radio" name="duration" value="30">30
                            <input type="radio" name="duration" value="40">40
                            <input type="radio" name="duration" value="50">50
                            <input type="radio" name="duration" value="60">60
                    minutes
                    <button type='submit' name='button' value='Submit Bid' class='btn btn-primary btn-lg btn-block' style='width:25%'>Book</button>
                    
 
   </div>
</body>
</html>
