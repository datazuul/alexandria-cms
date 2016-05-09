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
public interface Video {
  long getId();

  Author getAuthor();

  List getCategories();

  String getDescription();

  String getFormat();

  VideoProperties getProperties();

  String getTitle();

  void setAuthor(Author author);

  void setCategories(List categories);

  void setDescription(String description);

  void setFormat(String format);

  void setProperties(VideoProperties properties);

  void setTitle(String title);

  public void setId(long nextId);
  
}
