/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import MODEL.*;
import DAO.BookingDAO;
import java.time.*;
import java.util.ArrayList;
/**
 *
 * @author Clarissa Poedjiono
 */
@WebServlet(name = "AddBookingController", urlPatterns = {"/AddBookingController"})
public class AddBookingController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        
        String dateStr = request.getParameter("date");
        String startTimeStr = request.getParameter("time");
        Long duration = Long.parseLong(request.getParameter("duration"));
        
        //booking time and date converted to local time and date format
        LocalDate date = LocalDate.parse(dateStr);
        LocalTime startTime = LocalTime.parse(startTimeStr);
        LocalTime endTime = startTime.plusMinutes(duration);
        
        System.out.println("Date: " + date);
        System.out.println("Time: " + startTime);
        
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        
        System.out.println("Current Date: " + currentDate);
        System.out.println("Current Time: " + currentTime);
        //booking should be >= current date
        boolean afterCurrentTime = false;
        if(!date.isBefore(currentDate)){
            //booking should be >= current time
            afterCurrentTime = true;
        }
        if(date.equals(currentDate)){
            if(!startTime.isBefore(currentTime)){
                afterCurrentTime = true;
            }
        }
        if(afterCurrentTime){
            //check if slot is available if time input is valid
            System.out.println("Time input is valid");
            ArrayList<Booking> bookings = BookingDAO.getBookingsByDate(dateStr);
            if(!bookings.isEmpty() && bookings!=null){
                for(Booking booking: bookings){
                    LocalTime bStartTime = LocalTime.parse(booking.getStartTime()).minusMinutes(30);
                    LocalTime bEndTime = LocalTime.parse(booking.getEndTime()).plusMinutes(30);
                    if((bStartTime.isAfter(endTime)||bStartTime.equals(endTime))||(bEndTime.isBefore(startTime)||bEndTime.equals(startTime))){
                        Employee employee = (Employee) session.getAttribute("userSession");
                        String username = employee.getUsername();
                        //add the booking details to the database 
                        BookingDAO.addBooking(username, dateStr, ""+startTime, ""+endTime);
                    }else{
                        session.setAttribute("bookingError", "Booking time clashes with an existing booking");
                        response.sendRedirect("addBooking.jsp");

                        System.out.println("Booking clashes");
                    }            

                }
            }else{
                session.setAttribute("bookingSuccessful","Booking successful");
                Employee employee = (Employee) session.getAttribute("userSession");
                String username = employee.getUsername();
                //add the booking details to the database 
                BookingDAO.addBooking(username, dateStr, ""+startTime, ""+endTime);
                response.sendRedirect("addBooking.jsp");

            }
            
        }else{
            session.setAttribute("bookingError", "Invalid booking time");
            response.sendRedirect("addBooking.jsp");

            System.out.println("Invalid booking time");
        }
        
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
