package edu.epam.trainings.kokotov.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The enum for work with locale
 *
 * @author    Artem Kokotov
 */
public enum ResourceManager {

    INSTANCE;

    private ResourceBundle resourceBundle;

    private final String resourceName = "resources";

    private ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
