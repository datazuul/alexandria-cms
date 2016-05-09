package de.alexandria.cms.backend.impl.file.xml.repository.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author ralf
 */
public class FileReader {

  public static String getContent(File file, String encoding) {
    StringBuilder sb = new StringBuilder();
    try {
      FileInputStream fis = new FileInputStream(file);
      InputStreamReader isr = new InputStreamReader(fis, encoding);
      while (isr.ready()) {
        Character c = (char) isr.read();
        sb.append(c);
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return sb.toString();
  }
}
