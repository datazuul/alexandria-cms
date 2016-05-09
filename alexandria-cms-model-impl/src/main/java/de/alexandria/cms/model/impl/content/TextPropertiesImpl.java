package de.alexandria.cms.model.impl.content;

import de.alexandria.cms.model.api.content.TextProperties;
import java.io.Serializable;

/**
 * @author ralf
 */
public class TextPropertiesImpl implements Serializable, TextProperties {

  private String id;

  private String filename;

  private byte[] bytes;

  public String getId() {
    return id;
  }

  public TextPropertiesImpl(String id) {
    this.id = id;
  }

  @Override
  public void setFilename(String filename) {
    this.filename = filename;
  }

  @Override
  public String getFilename() {
    return filename;
  }

  @Override
  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

  @Override
  public byte[] getBytes() {
    return bytes;
  }
}
