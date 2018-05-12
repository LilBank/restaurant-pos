package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Provide access to application configuration data.
 * 
 * @author Piyawat & Vichaphol & Teacher James
 *
 */
public class PropertyManager {
	
	public static final String RESTAURANT_CONFIG = "restaurant.config";
	private static PropertyManager pm;
	private Properties properties;

	private PropertyManager() {
		loadProperties(RESTAURANT_CONFIG);
	}

	/**
	 * Get a singleton instance of this class
	 * 
	 * @return the property manager object
	 */
	public static PropertyManager getInstance() {
		if (pm == null)
			pm = new PropertyManager();
		return pm;
	}

	private void loadProperties(String filename) {
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream in = loader.getResourceAsStream(filename);
		if (in == null) {
			System.out.println("Could not open config file " + filename);
			return;
		}
		properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			System.out.println("Exception reading config file " + filename);
			System.out.println(e.getMessage());
		} finally {
			try {
				in.close();
			} catch (IOException ex2) {
				/* ignore it */ }
		}

	}

	/**
	 * Get the value of a property read from the config file.
	 * 
	 * @param propName
	 * @return the value of the property or empty string if not found
	 */
	public String getProperty(String propName) {
		return properties.getProperty(propName, "");
	}

	/**
	 * Get all the properties
	 */
	public Properties getProperties() {
		return this.properties;
	}

}
