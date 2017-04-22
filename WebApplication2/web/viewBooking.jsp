<%-- 
    Document   : viewBooking
    Created on : 23 Apr, 2017, 1:39:08 AM
    Author     : Clarissa Poedjiono
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.*"%>
<%@page import="DAO.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Table Tennis Booking App</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="home.jsp">Table Tennis Booking App</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="home.jsp">Home</a></li>
                    <li class="active"><a href="addBooking.jsp" >Add Booking</a></li>
                    <li class="active"><a href="viewBooking.jsp">Booking History</a></li> 
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Sign Out</a></li>
                </ul>

            </div>
        </nav>
        <div class="page-header">
            <h1>Booking History</h1>
            <%
                Employee employee = (Employee) session.getAttribute("userSession");
                String username = employee.getUsername();
                ArrayList<Booking> bookingsByUser = BookingDAO.getBookingsByUsername(username);
                System.out.println("DayBookings: " + bookingsByUser);
                if(bookingsByUser != null && !bookingsByUser.isEmpty()){

                  out.println("<table class='table table-bordered' style='text-align:center'>");
                  out.println("<tr style='text-align:center ; background-color:#B8B8B8'>");
                  out.println("<th>Date</th>");
                  out.println("<th>Start Time</th>");
                  out.println("<th>End Time</th>");
              
                  out.println("</tr>");

                  for(Booking b : bookingsByUser){
                  System.out.println("DATE2: "+b.getDate());
                      out.println("<tr>"
                              + "<td>" + b.getDate() + "</td>"
                              + "<td>" + b.getStartTime() + "</td>"
                              + "<td>" + b.getEndTime() + "</td>");
                  }
                  out.println("</table>");
                  session.removeAttribute("BookingsOnTheDay");
                  session.removeAttribute("tempDate");
                }else{
                    out.println("You have no existing bookings.");
                }
               
            %>
        </div>      
        
    </body>
</html>
