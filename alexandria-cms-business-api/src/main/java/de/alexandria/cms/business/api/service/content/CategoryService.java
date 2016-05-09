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
package de.alexandria.cms.business.api.service.content;

import de.alexandria.cms.model.api.content.Category;
import de.alexandria.cms.model.api.content.DomainName;

/**
 *
 * @author ralf
 */
public interface CategoryService {

  void delete(final DomainName dn, final Category category, final boolean recursively);

  Category load(final DomainName dn, final long id);

  void save(final DomainName dn, final Category parent);
  
}
