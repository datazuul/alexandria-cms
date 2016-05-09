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
package de.alexandria.cms.backend.api.repository;

import de.alexandria.cms.model.api.content.Article;
import de.alexandria.cms.model.api.content.Author;
import de.alexandria.cms.model.api.content.Category;
import de.alexandria.cms.model.api.content.DomainName;
import de.alexandria.cms.model.api.content.Image;
import de.alexandria.cms.model.api.content.Text;
import de.alexandria.cms.model.api.content.Video;
import java.io.File;
import java.util.List;

/**
 *
 * @author ralf
 */
public interface CmsRepository {

  void deleteArticle(final DomainName dn, final Article article);

  void deleteAuthor(final DomainName dn, final Author author);

  void deleteCategory(final DomainName dn, final Category category);

  void deleteImage(final DomainName dn, final Image image);

  void deleteText(final DomainName dn, final Text text);

  void deleteVideo(final DomainName dn, final Video video);

  Article getArticleById(final DomainName dn, final long id);

  // Article
  List getArticles(final DomainName dn);

  Author getAuthorById(final DomainName dn, final long id);

  // Author
  List getAuthors(final DomainName dn);

  List getCategories(final DomainName dn);

  Category getCategoryById(final DomainName dn, final long id);

  Image getImageById(final DomainName dn, final long id);

  // Image
  List getImages(final DomainName dn);

  List getLatest(final DomainName dn, final int count, final Class objectClass);

  File getPreviewFile(final DomainName dn, final Object obj);

  // Category
  List getRootCategories(final DomainName dn);

  List getSiteCategories(final DomainName dn);

  Text getTextById(final DomainName dn, final long id);

  // Text
  List getTexts(final DomainName dn);

  File getThumbnailFile(final DomainName dn, final Object obj);

  Video getVideoById(final DomainName dn, final long id);

  // Video
  List getVideos(final DomainName dn);

  // common for all objects
  void save(final DomainName dn, final Object obj);

  void saveRootCategories(final DomainName dn, final List<Category> rootCategories);

  void saveSiteCategories(final DomainName dn, final List<Category> categories);

  List search(final DomainName dn, final String query, final Object searchableObject);
  
}
