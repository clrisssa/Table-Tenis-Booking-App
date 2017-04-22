/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import MODEL.Employee;
import UTILITY.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Clarissa Poedjiono
 */
public class EmployeeDAO {
    public static Employee getEmployeeByUsername(String username) {

        if (username == null || username.equals("")) {
            return null;
        }
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                String userid = rs.getString(1);
                String password = rs.getString(2);
                String name = rs.getString(3);
               
               

                Employee employee = new Employee(userid, password, name);
                return employee;
            }
        } catch (Exception e) {
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
        
        return null;
        
    }
}
