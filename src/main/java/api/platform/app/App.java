package api.platform.app;

import api.apps.wunderlist.Wunderlist;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class App {

    private static Logger logger = Logger.getLogger(App.class);

    public Wunderlist wunderlist = new Wunderlist();

    private HashMap<String, String> readProperties() {
        Properties properties = new Properties();
        InputStream input;
        HashMap<String, String> appProperties = new HashMap<>();

        try {
            String propertiesFile = "config.properties";
            logger.info("Reading properties from " + propertiesFile);
            input = getClass().getClassLoader().getResourceAsStream(propertiesFile);

            if (input == null) {
                throw new FileNotFoundException("Unable to find config.properties");
            }
            properties.load(input);

            Enumeration<?> e = properties.propertyNames();

            while (e.hasMoreElements()) {
                String key = e.nextElement().toString();
                String value = properties.getProperty(key);
                appProperties.put(key, value);
                logger.info("Property: " + key + " has value " + value);
            }

        } catch (IOException e) {
            logger.error("Unable to load file", e);
        }
        return appProperties;
    }

    public String getProperty(String prop) {
        HashMap<String, String> appProperties = readProperties();

        if (appProperties.containsKey(prop)) {
            logger.info("Property found. " + prop + " : " + appProperties.get(prop));
            return appProperties.get(prop);
        } else {
            logger.info("Property " + prop + " not found");
            return null;
        }
    }
}
