package constants;
/**
 *  This class has only private static final fields with constants for the entire application.
 * @author Diana
 *
 */
public class Constants {
	
	public static String getJdbcDriver() {
		return JDBC_DRIVER;
	}
	public static String getDbUrl() {
		return DB_URL;
	}
	public static String getUSER() {
		return USER;
	}
	public static String getPASS() {
		return PASS;
	}
	public static String getDbName() {
		return DB_NAME;
	}
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";
    
    //  Database attributes
    private static String USER = "root";
    private static String PASS = "root";
    private static final String DB_NAME = "Weather_DB";
}
