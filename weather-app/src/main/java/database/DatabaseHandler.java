package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import constants.Constants;

public class DatabaseHandler {

    Connection conn;
    
    private static DatabaseHandler instance;
    
    private DatabaseHandler() {
   
        if (createDatabase() == true){
            createTables();
        }
    }

    
    public static DatabaseHandler getInstance() {
        if(instance == null){
            instance = new DatabaseHandler();
        }
        return instance;
    }


    /**
     * Register JDBC driver and open a connection
     */
    private void preliminaries() {
        try {
            Class.forName(Constants.getJdbcDriver());
            conn = DriverManager.getConnection(Constants.getDbUrl(), Constants.getUSER(), Constants.getPASS());
        } catch (SQLException se) {
            // Handle errors for JDBC   
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * Create the database
     *
     * @return true if the database has been created, otherwise return false
     */
    private boolean createDatabase() {
        preliminaries();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

            String sql = "CREATE DATABASE " + Constants.getDbName();
            stmt.executeUpdate(sql);

        }  catch (SQLException sqlException) {
            //if the database already exist
            if (sqlException.getErrorCode() == 1007) {
                closeConnection();
                return false;
            } else {
                closeConnection();
                sqlException.printStackTrace();
                return false;
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                closeConnection();
                return false;
            }
        }
        closeConnection();
        return true;
    }
    private void createTables() {
        preliminaries();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

            String sqlQuery = "USE "+ Constants.getDbName();
            stmt.executeUpdate(sqlQuery);
            
            //create WEATHER_HISTORY
            sqlQuery = "CREATE TABLE WEATHER_HISTORY "
                    + "(id INTEGER NOT NULL AUTO_INCREMENT,"
                    + " date TIMESTAMP, "
                    + " city VARCHAR(255) not NULL, "
                    + " temperature FLOAT(10), "
                    + " pressure FLOAT(10), "
                    + " humidity FLOAT(10), "
                    + " description VARCHAR(255),"
                    + " PRIMARY KEY (id))";
            stmt.executeUpdate(sqlQuery);

          
            
        } catch (SQLException ex) {
            closeConnection();
            ex.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                closeConnection();
            }
        }
        closeConnection();
    }
    
}
