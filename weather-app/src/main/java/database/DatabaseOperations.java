package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Calendar;

import client.WeatherRecord;
import constants.Constants;



public class DatabaseOperations {

    private static final String DB_URL = "jdbc:mysql://localhost/"+Constants.getDbName();

    private static Connection conn;
    public static DatabaseOperations instance;
    
    public static DatabaseOperations getInstance() {
        if(instance == null){
            instance = new DatabaseOperations();
        }
        return instance;
    }
    
    private DatabaseOperations() {
      
    }
    
    private static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    private static void preliminaries() {
        try {
            Class.forName(Constants.getJdbcDriver());
            conn = DriverManager.getConnection(DB_URL, Constants.getUSER(), Constants.getPASS());
        } catch (SQLException se) {
            // Handle errors for JDBC   
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    
    public String insertWeatherRecord(WeatherRecord weatherRecord){
    	preliminaries();
        String result = "OK";
    	java.sql.PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO weather_history"
            		+ " (date, city, temperature,pressure,humidity,description) "
            		+ " values ( ? , ? , ? , ? , ? , ?);";
            preparedStatement = conn.prepareStatement(sql);
           
            java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
            weatherRecord.setTimestamp(timestamp);
            preparedStatement.setTimestamp(1, weatherRecord.getTimestamp());
            preparedStatement.setString(2, weatherRecord.getCity());
            preparedStatement.setFloat(3, weatherRecord.getTemperature());
            preparedStatement.setFloat(4, weatherRecord.getPressure());
            preparedStatement.setFloat(5, weatherRecord.getHumidity());
            preparedStatement.setString(6, weatherRecord.getDescription());
            
            preparedStatement.execute(); 
          

        }catch(SQLIntegrityConstraintViolationException e){
        	//e.printStackTrace();
        	result = "Username already exists.";
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                	 preparedStatement.close();
                }
            } catch (SQLException se2) {
                closeConnection();
                return result;
            }
        }
        closeConnection();
        return result;
	}
    

	    
}
