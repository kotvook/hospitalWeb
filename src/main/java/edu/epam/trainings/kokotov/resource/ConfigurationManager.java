package edu.epam.trainings.kokotov.resource;

import java.util.ResourceBundle;

/**
 * The class retrieves information from the dbSetings.properties
 *
 * @author    Artem Kokotov
 */
public class ConfigurationManager {

    /** Bundle dbSetings.properties */
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("dbSetings");

    /**
     *  empty private constructor {@link ConfigurationManager}
     *
     */
    private ConfigurationManager() { }

    /**
     * getting value from dbSetings.properties by key
     *
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
