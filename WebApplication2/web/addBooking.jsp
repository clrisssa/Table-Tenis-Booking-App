<%-- 
    Document   : addBooking
    Created on : 22 Apr, 2017, 5:56:05 PM
    Author     : Clarissa Poedjiono
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.*"%>
<%@page import="DAO.*"%>



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
        <div class="page-header">
            <h1>Book A Table</h1>
        </div>        
        <div class ="col-md-6">
            <form action="AddBookingController" method="post">

                <div class="form-group">
                    <label for="sel1">Date: </label>
                    <input type="date" name="date" id="date"
                               <%String 
                                tempDate = (String)session.getAttribute("tempDate");
                               System.out.println("tempDate: " + tempDate);
                                if(tempDate!=null){
                                    
                                       out.println("value =" + tempDate);
                                   }%>
                    />
                    <button type="submit" name="button" class="btn btn-default" value="submitDate">Select Date</button><br/>
                    
                    <label for="sel1">Time: </label>
                    <input type ="time" name="time"/><br/>
                    
                    <label for="sel1">Duration: </label>
                    
                            <input type="radio" name="duration" value="20">20
                            <input type="radio" name="duration" value="30">30
                            <input type="radio" name="duration" value="40">40
                            <input type="radio" name="duration" value="50">50
                            <input type="radio" name="duration" value="60">60
                    minutes<br/>
                    <p class="small">P.S. There has to be at least 30min interval between each of the bookings</p>
                    
                    <button type='submit' name='button' value='submitBooking' class='btn btn-primary btn-lg btn-block' style='width:25%'>Book</button>
                </div>
                <%
                    String bookingError = (String)session.getAttribute("bookingError");
                    if(bookingError!=null){
                        out.println("<font color = 'red'>" + bookingError + "</font> ");
                        session.removeAttribute("bookingError");
                    }
                    String bookingSuccess = (String)session.getAttribute("bookingSuccessful");
                    if(bookingSuccess!=null){
                        out.println("<font color = 'green'>" + bookingSuccess + "</font> ");
                        session.removeAttribute("bookingSuccessful");
                    }
                
        %> 
        </div>
        <div class="container">
        <div class="col-md-6">
            
            <%
              ArrayList<Booking> bookingsOnTheDay = (ArrayList<Booking>)session.getAttribute("BookingsOnTheDay");
              System.out.println("DayBookings: " + bookingsOnTheDay);
              if(bookingsOnTheDay != null && !bookingsOnTheDay.isEmpty()){
                  
                out.println("Existing Bookings on " +tempDate);
                    out.println("<table class='table table-bordered' >");
                    out.println("<tr style='text-align:center ; background-color:#B8B8B8'>");
                    out.println("<th>Start Time</th>");
                    out.println("<th>End Time</th>");
                    out.println("<th>Booked By</th>");
                    out.println("</tr>");
                    
                    for(Booking b : bookingsOnTheDay){
                        
                        out.println("<tr>"
                                + "<td>" + b.getStartTime() + "</td>"
                                + "<td>" + b.getEndTime() + "</td>"
                                + "<td>" + b.getUsername() + "</td>");
                    }
                    out.println("</table>");
                    session.removeAttribute("BookingsOnTheDay");
                    session.removeAttribute("tempDate");
                }  
                
            %>
        </div>
        </div>
        </p>
   </div>
</body>
</html>
