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
public interface Category {
  long getId();

  /**
   * @return all articles in this category
   */
  List getArticles();

  List getImages();

  /**
   * @return the name
   */
  String getName();

  /**
   * @return The parent category of this category.
   */
  Category getParent();

  List getSubcategories();

  List getTexts();

  List getVideos();

  void removeChild(Category category);

  /**
   * For any persistable property, there should also be a setter method specified.
   *
   * @param articles
   */
  void setArticles(List articles);

  void setImages(List images);

  /**
   * @param name the name to set
   */
  void setName(String name);

  void setParent(Category category);

  void setSubcategories(List subcategories);

  void setTexts(List texts);

  void setVideos(List videos);

  public void setId(long nextId);
  
}
