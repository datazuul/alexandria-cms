package de.alexandria.cms.model.impl.content;

import de.alexandria.cms.model.api.content.Category;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ralf
 */
public class CategoryImpl implements Serializable, Category {

  private long id = 0;

  private String name;

  private Category parent;

  private List subcategories = new ArrayList();

  private List articles = new ArrayList();

  private List images = new ArrayList();

  private List texts = new ArrayList();

  private List videos = new ArrayList();

  public CategoryImpl() {
  }

  public CategoryImpl(String name) {
    this.name = name;
  }

  public CategoryImpl(long id) {
    this.id = id;
  }

  public boolean isNew() {
    return (id < 1);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Category getParent() {
    return parent;
  }

  @Override
  public void setParent(Category category) {
    this.parent = category;
  }

  @Override
  public List getSubcategories() {
    return subcategories;
  }

  @Override
  public void setSubcategories(List subcategories) {
    this.subcategories = subcategories;
  }

  @Override
  public List getArticles() {
    return articles;
  }

  @Override
  public void setArticles(List articles) {
    this.articles = articles;
  }

  @Override
  public List getImages() {
    return images;
  }

  @Override
  public void setImages(List images) {
    this.images = images;
  }

  @Override
  public List getTexts() {
    return texts;
  }

  @Override
  public void setTexts(List texts) {
    this.texts = texts;
  }

  @Override
  public List getVideos() {
    return videos;
  }

  @Override
  public void setVideos(List videos) {
    this.videos = videos;
  }

  @Override
  public String toString() {
    String result = "category=" + getId() + "/" + getName();
    if (getParent() != null) {
      result += "; parent=" + getParent().getName();
    }
    if (getSubcategories() != null && !getSubcategories().isEmpty()) {
      List subcategories = getSubcategories();
      result += "; subcategories: ";
      for (Iterator iterator = subcategories.iterator(); iterator
              .hasNext();) {
        CategoryImpl subcategory = (CategoryImpl) iterator.next();
        result += subcategory.getId() + "/";
        result += subcategory.getName() + ", ";
      }
    }
    return result;
  }

  @Override
  public void removeChild(Category category) {
    if (subcategories != null && !subcategories.isEmpty()) {
      List newSubcategories = new ArrayList<>();
      for (Iterator iterator = subcategories.iterator(); iterator.hasNext();) {
        CategoryImpl subcategory = (CategoryImpl) iterator.next();
        if (subcategory.getId() != ((CategoryImpl) category).getId()) {
          newSubcategories.add(subcategory);
        }
      }
      setSubcategories(newSubcategories);
    }
  }
}
