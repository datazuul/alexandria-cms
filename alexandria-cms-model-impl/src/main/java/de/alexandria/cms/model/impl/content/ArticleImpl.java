package de.alexandria.cms.model.impl.content;

import de.alexandria.cms.model.api.content.Article;
import de.alexandria.cms.model.api.content.Author;
import de.alexandria.cms.model.api.content.Category;
import java.io.Serializable;

/**
 * @author ralf
 */
public class ArticleImpl implements Serializable, Article {

  private long id = 0;

  private String title = null;

  private String htmlContent = null;

  private Author author = null;

  private Category category = null;

  public ArticleImpl() {

  }

  public ArticleImpl(long id) {
    this.id = id;
  }

  public boolean isNew() {
    return (id < 1);
  }

  /**
   * @return the author
   */
  @Override
  public Author getAuthor() {
    return author;
  }

  /**
   * @param author the author to set
   */
  @Override
  public void setAuthor(Author author) {
    this.author = author;
  }

  /**
   * @return the htmlContent
   */
  @Override
  public String getHtmlContent() {
    return htmlContent;
  }

  /**
   * @param htmlContent the htmlContent to set
   */
  @Override
  public void setHtmlContent(String htmlContent) {
    this.htmlContent = htmlContent;
  }

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return ("[id=" + this.id + ", htmlContent=" + this.htmlContent + "]");
  }

  /**
   * @return the title
   */
  @Override
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the parent category
   */
  @Override
  public Category getCategory() {
    return category;
  }

  @Override
  public void setCategory(Category category) {
    this.category = category;
  }
}
