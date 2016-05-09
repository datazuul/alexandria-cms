package de.alexandria.cms.backend.impl.file.xml.repository.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Utility for loading a *.properties-file from classpath and provide the key/values as Properties.
 *
 * @author ralf
 */
public class PropertiesLoader {

  /**
   * This method Loads a ResourceBundle and creates Properties from it
   */
  public static Properties loadParams(String file) throws IOException {

    // Loads a ResourceBundle and creates Properties from it
    Properties prop = new Properties();
    ResourceBundle bundle = ResourceBundle.getBundle(file);
    Enumeration en = bundle.getKeys();
    String key = null;

    while (en.hasMoreElements()) {
      key = (String) en.nextElement();
      prop.put(key, bundle.getObject(key));
    }

    return prop;
  }
}
