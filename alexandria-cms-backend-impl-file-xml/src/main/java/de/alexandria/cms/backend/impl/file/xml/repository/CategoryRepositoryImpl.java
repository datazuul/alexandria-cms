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
package de.alexandria.cms.backend.impl.file.xml.repository;

import de.alexandria.cms.backend.api.repository.CategoryRepository;
import de.alexandria.cms.backend.impl.file.xml.model.XMLCategory;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FilePathFactory;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FileWriter;
import de.alexandria.cms.model.api.content.Category;
import de.alexandria.cms.model.api.content.DomainName;
import java.io.File;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ralf
 */
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

  @Override
  public void save(DomainName dn, Category category) {
    saveMetadata(dn, category);
  }

  @Override
  public Category load(DomainName dn, Category category) {
    return loadMetadata(dn, category);
  }

  protected Category loadMetadata(DomainName dn, Category category) {
    Category result = null;
    final String filepath = FilePathFactory.getInstance().getMetaFilePath(dn, category);
    final File file = new File(filepath);
    if (file.exists()) {
      final SAXBuilder builder = new SAXBuilder();
      try {
        final Document metaDoc = builder.build(file);
        final XMLCategory xml = new XMLCategory(metaDoc);

        category.setArticles(xml.getArticles());
        category.setImages(xml.getImages());
        category.setName(xml.getName());
        category.setParent(xml.getParent());
        category.setSubcategories(xml.getSubcategories());
        category.setTexts(xml.getTexts());
        category.setVideos(xml.getVideos());

        result = category;
      } catch (final JDOMException | IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  protected void saveMetadata(DomainName dn, Category category) {
    final String filepath = FilePathFactory.getInstance().getMetaFilePath(dn, category);
    final File file = new File(filepath);
    final String content = new XMLCategory(category).toString();
    FileWriter.writeContent(file, content, PersistenceRepositoryImpl.DEFAULT_ENCODING);
  }

}
