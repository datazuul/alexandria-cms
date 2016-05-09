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
import de.alexandria.cms.model.api.content.DomainName;

/**
 *
 * @author ralf
 */
public interface ArticleRepository {

  /**
   * Load article from storage.
   *
   * @param article contains id
   * @return article null if error occurs or no valid id
   */
  Article load(DomainName dn, Article article);

  void save(final DomainName dn, final Article article);
  
}
