package de.alexandria.cms.backend.impl.file.xml.repository.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author ralf
 */
public class FileWriter {

  public static void writeContent(File file, String content, String encoding) {
    try {
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      FileOutputStream fos = new FileOutputStream(file);
      OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
      osw.write(content);
      osw.close();
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

  }

  public static void writeContent(File file, byte[] content) {
    try {
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      FileOutputStream fos = new FileOutputStream(file);
      fos.write(content);
      fos.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
