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


/**
 *
 * @author ralf
 */
public interface Article {

  long getId();
  
  /**
   * @return the author
   */
  Author getAuthor();

  /**
   * @return the parent category
   */
  Category getCategory();

  /**
   * @return the htmlContent
   */
  String getHtmlContent();

  /**
   * @return the title
   */
  String getTitle();

  /**
   * @param author the author to set
   */
  void setAuthor(Author author);

  void setCategory(Category category);

  /**
   * @param htmlContent the htmlContent to set
   */
  void setHtmlContent(String htmlContent);

  /**
   * @param title the title to set
   */
  void setTitle(String title);

  public void setId(long nextId);

}
