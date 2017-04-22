/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILITY;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clarissa Poedjiono
 */
public class ConnectionManager {

    /**
     * Path of connection.properties file of ConnectionManager
     */
    private static final String PROPS_FILENAME = "/connection.properties";
    /**
     * Database user of the ConnectionManager
     */
    private static String dbUser;
    /**
     * Password of the database user
     */
    private static String dbPassword;
    /**
     * Database URL of the database
     */
    private static String dbURL;

    static {
        readDatabaseProperties();
        initDBDriver();
    }
     /**
     * Reads the database properties from the connection.properties file
     */
    private static void readDatabaseProperties() {
        InputStream is = null;
        try {
            is = ConnectionManager.class.getResourceAsStream(PROPS_FILENAME);
            Properties props = new Properties();
            props.load(is);

            String host = props.getProperty("db.host");
            String port = props.getProperty("db.port");
            String dbName = props.getProperty("db.name");
            dbUser = props.getProperty("db.user");

            String username = System.getProperty("os.name");
            if (username.equals("Linux")) {
                dbPassword = props.getProperty("aws.db.password");
            } else {
                dbPassword = props.getProperty("db.password");
            }

            dbURL = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

        } catch (Exception e) {
            String message = "Unable to load '" + PROPS_FILENAME + "'.";

            System.out.println(message);
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, message, e);
            throw new RuntimeException(message, e);

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Logger.getLogger(ConnectionManager.class.getName()).log(Level.WARNING, "Unable to close connection.properties", e);
                }
            }
        }
    }
     /**
     * Initialize database driver 
     */
    private static void initDBDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            String message = "Unable to find JDBC driver for MySQL.";

            System.out.println(message);
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, message, ex);
            throw new RuntimeException(message, ex);
        }
    }
    /**
     * Gets the database connection
     * @return Connection the connection to the database
     * @throws SQLException if the URL username or password is/are invalid
     */
    public static Connection getConnection() throws SQLException {
        String message = "dbURL: " + dbURL + " ,dbUser: " + dbUser + " ,dbPassword: " + dbPassword;
        Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, message);

        return DriverManager.getConnection(dbURL, dbUser, dbPassword);
    }
     /**
     * Closes the Database Connection, Statement and ResultSet
     * @param conn the Connection to be closed
     * @param stmt the Statement to be closed
     * @param rs the ResultSet to be closed
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.WARNING, "Unable to close ResultSet", e);
        }

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.WARNING, "Unable to close Statement", e);

        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.WARNING, "Unable to close Connection", e);
        }
    }
}
