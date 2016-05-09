package de.alexandria.cms.model.impl.content;

import de.alexandria.cms.model.api.content.Text;
import de.alexandria.cms.model.api.content.Author;
import de.alexandria.cms.model.api.content.TextProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TextImpl implements Serializable, Text {

  private long id = 0;

  private Author author;

  private List categories = new ArrayList();

  private TextProperties properties = new TextPropertiesImpl("properties");

  private String description;

  private String format;

  private String title;

  public TextImpl(String title, byte[] bytes, String format) {
    this.format = format;
    this.title = title;

    properties.setBytes(bytes);
  }

  public TextImpl(long id) {
    this.id = id;
  }

  public TextImpl() {
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
  public TextProperties getProperties() {
    return properties;
  }

  @Override
  public void setProperties(TextProperties properties) {
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
