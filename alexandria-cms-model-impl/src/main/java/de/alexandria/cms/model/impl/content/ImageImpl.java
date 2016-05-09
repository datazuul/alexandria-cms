package de.alexandria.cms.model.impl.content;

import de.alexandria.cms.model.api.content.Author;
import de.alexandria.cms.model.api.content.Image;
import de.alexandria.cms.model.api.content.ImageProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageImpl implements Serializable, Image {

  private long id = 0;

  public ImageProperties propsOriginal = new ImagePropertiesImpl("original");

  public ImageProperties propsPreview = new ImagePropertiesImpl("preview");

  public ImageProperties propsThumbnail = new ImagePropertiesImpl("thumbnail");

  private Author author;

  private List categories = new ArrayList();

  private String align;

  private String format;

  private String title;

  private String valign;

  private String description;

  public ImageImpl(byte[] bytes, String format) {
    propsOriginal.setBytes(bytes);
    this.format = format;
  }

  public ImageImpl(long id) {
    this.id = id;
  }

  public ImageImpl() {
  }

  @Override
  public Author getAuthor() {
    return author;
  }

  @Override
  public List getCategories() {
    return categories;
  }

  @Override
  public void setCategories(List categories) {
    this.categories = categories;
  }

  public long getId() {
    return this.id;
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
  public void setAuthor(Author author) {
    this.author = author;
  }

  @Override
  public String getFormat() {
    return this.format;
  }

  @Override
  public void setFormat(String format) {
    this.format = format;
  }

  public boolean isNew() {
    return (id < 1);
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

  @Override
  public String getAlign() {
    return align;
  }

  @Override
  public void setAlign(String align) {
    this.align = align;
  }

  @Override
  public String getValign() {
    return valign;
  }

  @Override
  public void setValign(String valign) {
    this.valign = valign;
  }

  @Override
  public ImageProperties getPropsOriginal() {
    return propsOriginal;
  }

  @Override
  public void setPropsOriginal(ImageProperties propsOriginal) {
    this.propsOriginal = propsOriginal;
  }

  @Override
  public ImageProperties getPropsPreview() {
    return propsPreview;
  }

  @Override
  public void setPropsPreview(ImageProperties propsPreview) {
    this.propsPreview = propsPreview;
  }

  @Override
  public ImageProperties getPropsThumbnail() {
    return propsThumbnail;
  }

  @Override
  public void setPropsThumbnail(ImageProperties propsThumbnail) {
    this.propsThumbnail = propsThumbnail;
  }
}
