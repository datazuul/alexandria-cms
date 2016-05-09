package de.alexandria.cms.model.impl.content;

import de.alexandria.cms.model.api.content.Author;
import de.alexandria.cms.model.api.content.Article;
import de.alexandria.cms.model.api.content.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ralf
 */
public class AuthorImpl implements Serializable, Author {

  private long id = 0;

  private String dayOfBirth = null;

  private String dayOfDeath = null;

  private String firstname = null;

  private String surname = null;

  private Image image = null;

  private Article article = null;

  private List<Article> articles = new ArrayList<>();

  private List images = new ArrayList();

  private List texts = new ArrayList();

  private List videos = new ArrayList();

  public AuthorImpl() {

  }

  public AuthorImpl(String firstname, String surname) {
    this.firstname = firstname;
    this.surname = surname;
  }

  public AuthorImpl(String firstname, String surname, String dayOfBirth, String dayOfDeath) {
    this.dayOfBirth = dayOfBirth;
    this.dayOfDeath = dayOfDeath;
    this.firstname = firstname;
    this.surname = surname;
  }

  public AuthorImpl(long id) {
    this.id = id;
  }

  public boolean isNew() {
    return (id < 1);
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

  /**
   * @return the firstname
   */
  @Override
  public String getFirstname() {
    return firstname;
  }

  /**
   * @param firstname the firstname to set
   */
  @Override
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  /**
   * @return the surname
   */
  @Override
  public String getSurname() {
    return surname;
  }

  /**
   * @param surname the surname to set
   */
  @Override
  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Override
  public String getDayOfDeath() {
    return dayOfDeath;
  }

  @Override
  public void setDayOfDeath(String dayOfDeath) {
    this.dayOfDeath = dayOfDeath;
  }

  @Override
  public String getDayOfBirth() {
    return dayOfBirth;
  }

  @Override
  public void setDayOfBirth(String dayOfBirth) {
    this.dayOfBirth = dayOfBirth;
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
  public Article getArticle() {
    return article;
  }

  @Override
  public void setArticle(Article article) {
    this.article = article;
  }

  @Override
  public Image getImage() {
    return image;
  }

  @Override
  public void setImage(Image image) {
    this.image = image;
  }

  @Override
  public List getArticles() {
    return articles;
  }

  @Override
  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }
}
