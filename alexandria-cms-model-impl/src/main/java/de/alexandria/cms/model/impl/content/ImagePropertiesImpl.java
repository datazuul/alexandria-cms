package de.alexandria.cms.model.impl.content;

import de.alexandria.cms.model.api.content.ImageProperties;
import java.io.Serializable;

/**
 * @author ralf
 */
public class ImagePropertiesImpl implements Serializable, ImageProperties {

  private String id;

  private int height;

  private int width;

  private String filename;

  private byte[] bytes;

  public String getId() {
    return id;
  }

  public ImagePropertiesImpl(String id) {
    this.id = id;
  }

  public ImagePropertiesImpl(int width, int height, String filename) {
    this.filename = filename;
    this.height = height;
    this.width = width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public void setWidth(int width) {
    this.width = width;
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
