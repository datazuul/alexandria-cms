package de.alexandria.cms.backend.impl.file.xml.repository;

import de.alexandria.cms.backend.api.repository.TextRepository;
import de.alexandria.cms.backend.impl.file.xml.model.XMLText;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FilePathFactory;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FileWriter;
import de.alexandria.cms.model.api.content.DomainName;
import de.alexandria.cms.model.api.content.Text;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.stream.FileImageInputStream;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Repository;

/**
 * @author ralf
 */
@Repository
public class TextRepositoryImpl implements TextRepository {

  @Override
  public void save(final DomainName dn, final Text text) {
    saveBinary(dn, text);
    saveMetadata(dn, text);
  }

  @Override
  public Text load(final DomainName dn, final Text text) {
    final Text result = loadMetadata(dn, text);
    if (result != null) {
      loadBinary(dn, result);
    }
    return result;
  }

  /*
     * (non-Javadoc)
     * 
     * @see
     * de.pixotec.webapps.kms.backend.TextPersistenceManager#saveBinary(de.pixotec
     * .webapps.kms.model.Text)
   */
  protected void saveBinary(final DomainName dn, final Text text) {
    // {id}.{mimetype}
    final String filepath = FilePathFactory.getInstance().getFilePath(dn, text);
    final File file = new File(filepath);
    FileWriter.writeContent(file, text.getProperties().getBytes());
  }

  /*
     * (non-Javadoc)
     * 
     * @see
     * de.pixotec.webapps.kms.backend.TextPersistenceManager#saveMetadata(de
     * .pixotec.webapps.kms.model.Text)
   */
  protected void saveMetadata(final DomainName dn, final Text text) {
    final String filepath = FilePathFactory.getInstance().getMetaFilePath(dn, text);
    final File file = new File(filepath);
    final String content = new XMLText(text).toString();
    FileWriter.writeContent(file, content, PersistenceRepositoryImpl.DEFAULT_ENCODING);
  }

  protected void loadBinary(final DomainName dn, final Text text) {
    final String filepath = FilePathFactory.getInstance().getFilePath(dn, text);
    final File file = new File(filepath);
    FileImageInputStream fiis;
    try {
      fiis = new FileImageInputStream(file);
      final byte[] bytes = new byte[(int) fiis.length()];
      fiis.read(bytes);
      text.getProperties().setBytes(bytes);
      fiis.close();
    } catch (final FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (final IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  protected Text loadMetadata(final DomainName dn, final Text text) {
    Text result = null;
    final String filepath = FilePathFactory.getInstance().getMetaFilePath(dn, text);
    final File file = new File(filepath);
    if (file.exists()) {
      final SAXBuilder builder = new SAXBuilder();
      try {
        final Document metaDoc = builder.build(file);
        final XMLText xml = new XMLText(metaDoc);

        text.setAuthor(xml.getAuthor());
        text.setCategories(xml.getCategories());
        text.setDescription(xml.getDescription());
        text.setFormat(xml.getFormat());
        text.setTitle(xml.getTitle());

        result = text;
      } catch (final JDOMException | IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
}
