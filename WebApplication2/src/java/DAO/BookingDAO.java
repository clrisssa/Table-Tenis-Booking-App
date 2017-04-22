/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import UTILITY.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import MODEL.*;
import java.time.*;
/**
 *
 * @author Clarissa Poedjiono
 */
public class BookingDAO {
    public static void addBooking(String username, String date, String startTime, String endTime){
        
        Connection conn = null;        
        PreparedStatement stmt = null;

        try {
            conn = ConnectionManager.getConnection();

            //insert bid to database
            stmt = conn.prepareStatement("Insert into booking values (?, ?, ?, ?)");
            
            //set username
            stmt.setString(1, username);

            //set booking date
            stmt.setString(2, date);

            //set booking start time
            stmt.setString(3, startTime);

            //set booking end time
            stmt.setString(4, endTime);
            int numRecordsUpdated = stmt.executeUpdate();
            System.out.println("database updated");


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (conn != null) {
                    conn.close();

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static ArrayList<Booking> getBookingsByDate(String date) {
        ArrayList<Booking> bookings = new ArrayList<>();
        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Booking where date = ?");
            
            stmt.setString(1, date);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString(1);
                rs.getString(2);
                String startTime = rs.getString(3);
                String endTime = rs.getString(4);

                Booking booking = new Booking(username, date, startTime, endTime);
                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
