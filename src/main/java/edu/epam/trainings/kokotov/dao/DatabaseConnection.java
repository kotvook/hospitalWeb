package edu.epam.trainings.kokotov.dao;

import edu.epam.trainings.kokotov.resource.ConfigurationManager;
import edu.epam.trainings.kokotov.resource.ResourceManager;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * The class contains the necessary database connection controls
 *
 * @author    Artem Kokotov
 */
public class DatabaseConnection {

    /** Database user name. */
    final static String user = ConfigurationManager.getProperty("user");

    /** Database password.*/
    final static String password = ConfigurationManager.getProperty("password");

    /** Database url without settings. */
    final static String url = ConfigurationManager.getProperty("url");

    /** Literal for add database setings */
    final static String add = ConfigurationManager.getProperty("addSetings");

    /** add Unicode setings. */
    final static String useUnicode = ConfigurationManager.getProperty("useUnicode");

    /** add TimezoneShift setings. */
    final static String useTimezoneShift = ConfigurationManager.getProperty("useTimezoneShift");

    /** add LegacyDatetimeCode setings. */
    final static String useLegacyDatetimeCode = ConfigurationManager.getProperty("useLegacyDatetimeCode");

    /** add Timezone setings. */
    final static String serverTimezone = ConfigurationManager.getProperty("serverTimezone");

    /** add SSL setings. */
    final static String useSSL = ConfigurationManager.getProperty("useSSL");

    /** All database setings. */
    final static String allDbSetings = useUnicode
            +useTimezoneShift
            +useLegacyDatetimeCode
            +serverTimezone
            +useSSL;

    /** Connection of database.*/
    private static Connection connection = null;

    /** Logger */
    private static Logger log = Logger.getLogger(DatabaseConnection.class.getName());

    static ResourceManager manager = ResourceManager.INSTANCE;
    /**
     * establishing connection with Database.
     *
     */
    public static void  establishСonnection() throws SQLException {
        connection= DriverManager.getConnection(url + add +  allDbSetings, user, password);
        log.info(manager.getString("connectionON"));;
    }

    /**
     * closing connection to Database.
     *
     */
    public static void closeConnection () throws SQLException {
        if (connection == null){
            log.info("There was no database connection!");
        } else if (connection.isClosed()){
            log.info("DB connection was closed earlier");
        } else {
            connection.close();
            log.info("Database connection was closed");
        }
    }

    public static Connection getConnection (){
        return connection;
    }
}
