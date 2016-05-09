package de.alexandria.cms.model.impl.content;

import de.alexandria.cms.model.api.content.Video;
import de.alexandria.cms.model.api.content.Author;
import de.alexandria.cms.model.api.content.VideoProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VideoImpl implements Serializable, Video {

  private long id = 0;

  private Author author;

  private List categories = new ArrayList();

  private VideoProperties properties = new VideoPropertiesImpl("properties");

  private String description;

  private String format;

  private String title;

  public VideoImpl(String title, byte[] bytes, String format) {
    this.format = format;
    this.title = title;

    properties.setBytes(bytes);
  }

  public VideoImpl(long id) {
    this.id = id;
  }

  public VideoImpl() {
  }

  @Override
  public Author getAuthor() {
    return author;
  }

  @Override
  public void setAuthor(Author author) {
    this.author = author;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isNew() {
    return (id < 1);
  }

  @Override
  public VideoProperties getProperties() {
    return properties;
  }

  @Override
  public void setProperties(VideoProperties properties) {
    this.properties = properties;
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public List getCategories() {
    return categories;
  }

  @Override
  public void setCategories(List categories) {
    this.categories = categories;
  }

  @Override
  public String getFormat() {
    return format;
  }

  @Override
  public void setFormat(String format) {
    this.format = format;
  }
}
