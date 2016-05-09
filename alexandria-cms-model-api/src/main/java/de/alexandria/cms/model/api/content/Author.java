/*
 * Copyright 2016 Alexandria.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alexandria.cms.model.api.content;

import java.util.List;

/**
 *
 * @author ralf
 */
public interface Author {
  long getId();

  Article getArticle();

  List getArticles();

  String getDayOfBirth();

  String getDayOfDeath();

  /**
   * @return the firstname
   */
  String getFirstname();

  Image getImage();

  List getImages();

  /**
   * @return the surname
   */
  String getSurname();

  List getTexts();

  List getVideos();

  void setArticle(Article article);

  void setArticles(List<Article> articles);

  void setDayOfBirth(String dayOfBirth);

  void setDayOfDeath(String dayOfDeath);

  /**
   * @param firstname the firstname to set
   */
  void setFirstname(String firstname);

  void setImage(Image image);

  void setImages(List images);

  /**
   * @param surname the surname to set
   */
  void setSurname(String surname);

  void setTexts(List texts);

  void setVideos(List videos);

  public void setId(long nextId);

}
