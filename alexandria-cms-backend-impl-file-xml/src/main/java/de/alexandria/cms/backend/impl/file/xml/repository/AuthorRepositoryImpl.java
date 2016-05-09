package de.alexandria.cms.backend.impl.file.xml.repository;

import de.alexandria.cms.backend.api.repository.AuthorRepository;
import de.alexandria.cms.backend.impl.file.xml.model.XMLAuthor;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FilePathFactory;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FileWriter;
import de.alexandria.cms.model.api.content.Article;
import de.alexandria.cms.model.api.content.Author;
import de.alexandria.cms.model.api.content.DomainName;
import de.alexandria.cms.model.api.content.Image;
import java.io.File;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

  @Override
  public void save(final DomainName dn, final Author author) {
    saveArticle(dn, author.getArticle());
    saveImage(dn, author.getImage());
    saveMetadata(dn, author);
  }

  /**
   * Load author from storage.
   *
   * @param author contains id
   * @return author null if error occurs or no valid id
   */
  @Override
  public Author load(final DomainName dn, final Author author) {
    return loadMetadata(dn, author);
  }
  

  /*
     * (non-Javadoc)
     * 
     * @see
     * de.pixotec.webapps.kms.backend.AuthorPersistenceManager#saveArticle(de
     * .pixotec.webapps.kms.model.Article)
   */
  protected void saveArticle(final DomainName dn, final Article article) {
    PersistenceRepositoryImpl.getInstance(dn).save(dn, article);
  }

  /*
     * (non-Javadoc)
     * 
     * @see
     * de.pixotec.webapps.kms.backend.AuthorPersistenceManager#saveImage(de.
     * pixotec.webapps.kms.model.Image)
   */
  protected void saveImage(final DomainName dn, final Image image) {
    PersistenceRepositoryImpl.getInstance(dn).save(dn, image);
  }

  /*
     * (non-Javadoc)
     * 
     * @see
     * de.pixotec.webapps.kms.backend.AuthorPersistenceManager#saveMetadata(
     * de.pixotec.webapps.kms.model.Author)
   */
  protected void saveMetadata(final DomainName dn, final Author author) {
    final String filepath = FilePathFactory.getInstance().getMetaFilePath(dn, author);
    final File file = new File(filepath);
    final String content = new XMLAuthor(author).toString();
    FileWriter.writeContent(file, content, PersistenceRepositoryImpl.DEFAULT_ENCODING);
  }

  /*
     * (non-Javadoc)
     * 
     * @see
     * de.pixotec.webapps.kms.backend.AuthorPersistenceManager#loadMetadata(
     * de.pixotec.webapps.kms.model.Author)
   */
  protected Author loadMetadata(final DomainName dn, final Author author) {
    Author result = null;
    final String filepath = FilePathFactory.getInstance().getMetaFilePath(dn, author);
    final File file = new File(filepath);
    if (file.exists()) {
      final SAXBuilder builder = new SAXBuilder();
      try {
        final Document metaDoc = builder.build(file);
        final XMLAuthor xml = new XMLAuthor(metaDoc);

        author.setArticle(xml.getArticle());
        author.setArticles(xml.getArticles());
        author.setDayOfBirth(xml.getDayOfBirth());
        author.setDayOfDeath(xml.getDayOfDeath());
        author.setFirstname(xml.getFirstname());
        author.setImage(xml.getImage());
        author.setImages(xml.getImages());
        author.setSurname(xml.getSurname());
        author.setTexts(xml.getTexts());
        author.setVideos(xml.getVideos());

        result = author;
      } catch (final JDOMException e) {
      } catch (final IOException e) {
      }
    }
    return result;
  }
}
