package de.alexandria.cms.backend.impl.file.xml.repository;

import de.alexandria.cms.backend.api.repository.VideoRepository;
import de.alexandria.cms.backend.impl.file.xml.model.XMLVideo;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FilePathFactory;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FileWriter;
import de.alexandria.cms.model.api.content.DomainName;
import de.alexandria.cms.model.api.content.Video;
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
public class VideoRepositoryImpl implements VideoRepository {

  @Override
  public void save(DomainName dn, Video video) {
    saveBinary(dn, video);
    saveMetadata(dn, video);
  }

  @Override
  public Video load(DomainName dn, Video video) {
    Video result = loadMetadata(dn, video);
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
  protected void saveBinary(final DomainName dn, final Video video) {
    // {id}.{mimetype}
    final String filepath = FilePathFactory.getInstance().getFilePath(dn, video);
    final File file = new File(filepath);
    FileWriter.writeContent(file, video.getProperties().getBytes());
  }

  protected void saveMetadata(final DomainName dn, final Video video) {
    final String filepath = FilePathFactory.getInstance().getMetaFilePath(dn, video);
    final File file = new File(filepath);
    final String content = new XMLVideo(video).toString();
    FileWriter.writeContent(file, content, PersistenceRepositoryImpl.DEFAULT_ENCODING);
  }

  protected void loadBinary(final DomainName dn, final Video video) {
    final String filepath = FilePathFactory.getInstance().getFilePath(dn, video);
    final File file = new File(filepath);
    FileImageInputStream fiis;
    try {
      fiis = new FileImageInputStream(file);
      final byte[] bytes = new byte[(int) fiis.length()];
      fiis.read(bytes);
      video.getProperties().setBytes(bytes);
      fiis.close();
    } catch (final FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (final IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  protected Video loadMetadata(final DomainName dn, final Video video) {
    Video result = null;
    final String filepath = FilePathFactory.getInstance().getMetaFilePath(dn, video);
    final File file = new File(filepath);
    if (file.exists()) {
      final SAXBuilder builder = new SAXBuilder();
      try {
        final Document metaDoc = builder.build(file);
        final XMLVideo xml = new XMLVideo(metaDoc);

        video.setAuthor(xml.getAuthor());
        video.setCategories(xml.getCategories());
        video.setDescription(xml.getDescription());
        video.setFormat(xml.getFormat());
        video.setTitle(xml.getTitle());

        result = video;
      } catch (final JDOMException | IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
}
