package de.alexandria.cms.backend.impl.file.xml.repository;

import de.alexandria.cms.backend.api.repository.SiteCategoriesRepository;
import de.alexandria.cms.backend.impl.file.xml.model.XMLRoot;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FilePathFactory;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FileWriter;
import de.alexandria.cms.model.api.content.Category;
import de.alexandria.cms.model.api.content.DomainName;
import de.alexandria.cms.model.impl.content.CategoryImpl;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class SiteCategoriesRepositoryImpl implements SiteCategoriesRepository {

  @Override
  public List<Category> load(final DomainName dn) {
    final List<Category> result = new ArrayList<>();
    final String filepath = FilePathFactory.getInstance().getSiteFilePath(dn, Category.class);

    final File file = new File(filepath);
    if (file.exists()) {
      final SAXBuilder builder = new SAXBuilder();
      try {
        final Document metaDoc = builder.build(file);
        final XMLRoot xml = new XMLRoot(metaDoc);

        final List<Long> children = xml.getChildren();
        for (final Iterator iterator = children.iterator(); iterator.hasNext();) {
          final Long id = (Long) iterator.next();
          final Category child = new CategoryImpl(id);
          result.add(child);
        }
      } catch (final JDOMException | IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  @Override
  public void save(final DomainName dn, final List<Category> categories) {
    final String filepath = FilePathFactory.getInstance().getSiteFilePath(dn, Category.class);
    final File file = new File(filepath);
    final List<Long> children = new ArrayList<>();
    for (final Iterator iterator = categories.iterator(); iterator.hasNext();) {
      final Category category = (Category) iterator.next();
      final Long id = category.getId();
      children.add(id);
    }
    final String content = new XMLRoot(children).toString();
    FileWriter.writeContent(file, content, PersistenceRepositoryImpl.DEFAULT_ENCODING);

  }
}
