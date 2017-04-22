/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import UTILITY.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    
}
