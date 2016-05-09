package de.alexandria.cms.backend.impl.file.xml.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.alexandria.cms.backend.api.repository.ArticleRepository;
import de.alexandria.cms.backend.api.repository.AuthorRepository;
import de.alexandria.cms.backend.api.repository.CategoryRepository;
import de.alexandria.cms.backend.api.repository.ImageRepository;
import de.alexandria.cms.backend.api.repository.RootCategoriesRepository;
import de.alexandria.cms.backend.api.repository.SiteCategoriesRepository;
import de.alexandria.cms.backend.api.repository.TextRepository;
import de.alexandria.cms.backend.api.repository.VideoRepository;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FilePathFactory;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FileReader;
import de.alexandria.cms.backend.impl.file.xml.repository.util.FileWriter;
import de.alexandria.cms.model.api.content.Article;
import de.alexandria.cms.model.api.content.Author;
import de.alexandria.cms.model.api.content.Category;
import de.alexandria.cms.model.api.content.DomainName;
import de.alexandria.cms.model.api.content.Image;
import de.alexandria.cms.model.api.content.Text;
import de.alexandria.cms.model.api.content.Video;
import de.alexandria.cms.model.impl.content.ArticleImpl;
import de.alexandria.cms.model.impl.content.AuthorImpl;
import de.alexandria.cms.model.impl.content.CategoryImpl;
import de.alexandria.cms.model.impl.content.ImageImpl;
import de.alexandria.cms.model.impl.content.TextImpl;
import de.alexandria.cms.model.impl.content.VideoImpl;
import org.springframework.stereotype.Repository;
import de.alexandria.cms.backend.api.repository.PersistenceRepository;

@Repository
public class PersistenceRepositoryImpl implements PersistenceRepository {

  private static PersistenceRepositoryImpl _instance = null;

  boolean useCache = false;

  public static final String DEFAULT_ENCODING = "UTF-8";

  private static final String LAST_ID_FILENAME = "lastId.txt";

  private static HashMap ARTICLES = new HashMap();

  private static HashMap AUTHORS = new HashMap();

  private static HashMap CATEGORIES = new HashMap();

  private static HashMap IMAGES = new HashMap();

  private static HashMap ROOT_CATEGORIES = new HashMap();

  private static HashMap SITE_CATEGORIES = new HashMap();

  private static HashMap TEXTS = new HashMap();

  private static HashMap VIDEOS = new HashMap();

  private PersistenceRepositoryImpl() {
  }

  public static PersistenceRepositoryImpl getInstance(final DomainName dn) {
    if (_instance == null) {
      _instance = new PersistenceRepositoryImpl();
    }
    // init persistence manager if needed
    _instance.init(dn);
    return _instance;
  }

  @Override
  public void save(final DomainName dn, final Object obj) {
    if (obj instanceof Article) {
      final Article article = (Article) obj;
      if (article.getId() == 0) {
        article.setId(getNextId(dn, Article.class));
      }
      final ArticleRepository pm = new ArticleRepositoryImpl();
      pm.save(dn, article);
      if (useCache()) {
        ARTICLES.put(getDomainNamePrefix(dn) + article.getId(), article);
      }
    } else if (obj instanceof Author) {
      final Author author = (Author) obj;
      if (author.getId() == 0) {
        author.setId(getNextId(dn, Author.class));
      }
      final AuthorRepository pm = new AuthorRepositoryImpl();
      pm.save(dn, author);
      if (useCache()) {
        AUTHORS.put(getDomainNamePrefix(dn) + author.getId(), author);
      }
    } else if (obj instanceof Category) {
      final Category category = (Category) obj;
      if (category.getId() == 0) {
        category.setId(getNextId(dn, Category.class));
      }
      final CategoryRepository pm = new CategoryRepositoryImpl();
      pm.save(dn, category);
      if (useCache()) {
        CATEGORIES.put(getDomainNamePrefix(dn) + category.getId(), category);
      }
    } else if (obj instanceof Image) {
      final Image image = (Image) obj;
      if (image.getId() == 0) {
        image.setId(getNextId(dn, Image.class));
      }
      final ImageRepository pm = new ImageRepositoryImpl();
      pm.save(dn, image);
      if (useCache()) {
        IMAGES.put(getDomainNamePrefix(dn) + image.getId(), image);
      }
    } else if (obj instanceof Text) {
      final Text text = (Text) obj;
      if (text.getId() == 0) {
        text.setId(getNextId(dn, Text.class));
      }
      final TextRepository pm = new TextRepositoryImpl();
      pm.save(dn, text);
      if (useCache()) {
        TEXTS.put(getDomainNamePrefix(dn) + text.getId(), text);
      }
    } else if (obj instanceof Video) {
      final Video video = (Video) obj;
      if (video.getId() == 0) {
        video.setId(getNextId(dn, Video.class));
      }
      final VideoRepository pm = new VideoRepositoryImpl();
      pm.save(dn, video);
      if (useCache()) {
        VIDEOS.put(getDomainNamePrefix(dn) + video.getId(), video);
      }
    }
  }

  private String getDomainNamePrefix(final DomainName dn) {
    String result = "";
    if (dn != null) {
      result = dn.getFullyQualifiedDomainName() + "-";
    }
    return result;
  }

  @Override
  public Object load(final DomainName dn, final Object obj) {
    Object result = null;
    if (obj instanceof Article) {
      final Article article = (Article) obj;
      final String id = String.valueOf(article.getId());
      if (useCache()) {
        result = ARTICLES.get(getDomainNamePrefix(dn) + id);
      }
      if (result == null) {
        final ArticleRepository pm = new ArticleRepositoryImpl();
        result = pm.load(dn, article);
        if (result != null) {
          ARTICLES.put(getDomainNamePrefix(dn) + id, result);
        }
      }
    } else if (obj instanceof Author) {
      final Author author = (Author) obj;
      final String id = String.valueOf(author.getId());
      if (useCache()) {
        result = AUTHORS.get(getDomainNamePrefix(dn) + id);
      }
      if (result == null) {
        final AuthorRepository pm = new AuthorRepositoryImpl();
        result = pm.load(dn, author);
        if (result != null) {
          AUTHORS.put(getDomainNamePrefix(dn) + id, result);
        }
      }
    } else if (obj instanceof Category) {
      final Category category = (Category) obj;
      final String id = String.valueOf(category.getId());
      if (useCache()) {
        result = CATEGORIES.get(getDomainNamePrefix(dn) + id);
      }
      if (result == null) {
        final CategoryRepository pm = new CategoryRepositoryImpl();
        result = pm.load(dn, category);
        if (result != null) {
          CATEGORIES.put(getDomainNamePrefix(dn) + id, result);
        }
      }
    } else if (obj instanceof Image) {
      final Image image = (Image) obj;
      final String id = String.valueOf(image.getId());
      if (useCache()) {
        result = IMAGES.get(getDomainNamePrefix(dn) + id);
      }
      if (result == null) {
        final ImageRepository pm = new ImageRepositoryImpl();
        result = pm.load(dn, image);
        if (result != null) {
          IMAGES.put(getDomainNamePrefix(dn) + id, result);
        }
      }
    } else if (obj instanceof Text) {
      final Text text = (Text) obj;
      final String id = String.valueOf(text.getId());
      if (useCache()) {
        result = TEXTS.get(getDomainNamePrefix(dn) + id);
      }
      if (result == null) {
        final TextRepository pm = new TextRepositoryImpl();
        result = pm.load(dn, text);
        if (result != null) {
          TEXTS.put(getDomainNamePrefix(dn) + id, result);
        }
      }
    } else if (obj instanceof Video) {
      final Video video = (Video) obj;
      final String id = String.valueOf(video.getId());
      if (useCache()) {
        result = VIDEOS.get(getDomainNamePrefix(dn) + id);
      }
      if (result == null) {
        final VideoRepository pm = new VideoRepositoryImpl();
        result = pm.load(dn, video);
        if (result != null) {
          VIDEOS.put(getDomainNamePrefix(dn) + id, result);
        }
      }
    }
    return result;
  }

  @Override
  public void delete(final DomainName dn, final Object obj) {
    if (obj instanceof Article) {
      final Article article = (Article) obj;
      delete(dn, article);
    } else if (obj instanceof Author) {
      final Author author = (Author) obj;
      delete(dn, author);
    } else if (obj instanceof Category) {
      final Category category = (Category) obj;
      delete(dn, category);
    } else if (obj instanceof Image) {
      final Image image = (Image) obj;
      delete(dn, image);
    } else if (obj instanceof Text) {
      final Text text = (Text) obj;
      delete(dn, text);
    } else if (obj instanceof Video) {
      final Video video = (Video) obj;
      delete(dn, video);
    }
  }

  @Override
  public Object getById(final DomainName dn, final Class cls, final long id) {
    Object result = null;
    if (cls == Article.class) {
      result = load(dn, new ArticleImpl(id));
    } else if (cls == Author.class) {
      result = load(dn, new AuthorImpl(id));
    } else if (cls == Category.class) {
      result = load(dn, new CategoryImpl(id));
    } else if (cls == Image.class) {
      result = load(dn, new ImageImpl(id));
    } else if (cls == Text.class) {
      result = load(dn, new TextImpl(id));
    } else if (cls == Video.class) {
      result = load(dn, new VideoImpl(id));
    }
    return result;
  }

  @Override
  public List findAll(final DomainName dn, final Class cls) {
    final ArrayList result = new ArrayList();
    final long lastId = getLastId(dn, cls);
    for (long i = 1; i <= lastId; i++) {
      final Object obj = getById(dn, cls, i);
      if (obj != null) {
        result.add(obj);
      }
    }
    return result;
  }

  private void init(final DomainName dn) {
    try {
      // create repository
      String filepath = FilePathFactory.getInstance().getRootPathArticles(dn);
      final File repositoryDir = new File(filepath);
      if (!repositoryDir.exists()) {
        repositoryDir.mkdirs();
      } else {
        return;
      }

      // create lastid.txt files
      filepath = FilePathFactory.getInstance().getRootPathArticles(dn);
      createLastIdFile(filepath);

      filepath = FilePathFactory.getInstance().getRootPathAuthors(dn);
      createLastIdFile(filepath);

      filepath = FilePathFactory.getInstance().getRootPathCategories(dn);
      createLastIdFile(filepath);

      filepath = FilePathFactory.getInstance().getRootPathImages(dn);
      createLastIdFile(filepath);

      filepath = FilePathFactory.getInstance().getRootPathTexts(dn);
      createLastIdFile(filepath);

      filepath = FilePathFactory.getInstance().getRootPathVideos(dn);
      createLastIdFile(filepath);
    } catch (final NullPointerException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private void createLastIdFile(final String filepath) {
    final File file = new File(filepath, LAST_ID_FILENAME);
    if (!file.exists()) {
      FileWriter.writeContent(file, "1", DEFAULT_ENCODING);
    }
  }

  public void dispose() {
    ARTICLES = new HashMap();
    AUTHORS = new HashMap();
    CATEGORIES = new HashMap();
    IMAGES = new HashMap();
    TEXTS = new HashMap();
    VIDEOS = new HashMap();
  }

  public long getNextId(final DomainName dn, final Class cls) {
    long result = 0;
    String filepath = null;
    if (cls == Article.class) {
      filepath = FilePathFactory.getInstance().getRootPathArticles(dn);
    } else if (cls == Author.class) {
      filepath = FilePathFactory.getInstance().getRootPathAuthors(dn);
    } else if (cls == Category.class) {
      filepath = FilePathFactory.getInstance().getRootPathCategories(dn);
    } else if (cls == Image.class) {
      filepath = FilePathFactory.getInstance().getRootPathImages(dn);
    } else if (cls == Text.class) {
      filepath = FilePathFactory.getInstance().getRootPathTexts(dn);
    } else if (cls == Video.class) {
      filepath = FilePathFactory.getInstance().getRootPathVideos(dn);
    }

    final File file = new File(filepath, LAST_ID_FILENAME);
    // TODO nicht immer lesend auf datei zugreifen! in variable cachen
    final String content = FileReader.getContent(file, DEFAULT_ENCODING).trim();
    result = Long.parseLong(content) + 1;
    FileWriter.writeContent(file, String.valueOf(result), DEFAULT_ENCODING);
    return result;
  }

  @Override
  public long getLastId(final DomainName dn, final Class cls) {
    long result = 0;
    String filepath = null;
    if (cls == Article.class) {
      filepath = FilePathFactory.getInstance().getRootPathArticles(dn);
    } else if (cls == Author.class) {
      filepath = FilePathFactory.getInstance().getRootPathAuthors(dn);
    } else if (cls == Category.class) {
      filepath = FilePathFactory.getInstance().getRootPathCategories(dn);
    } else if (cls == Image.class) {
      filepath = FilePathFactory.getInstance().getRootPathImages(dn);
    } else if (cls == Text.class) {
      filepath = FilePathFactory.getInstance().getRootPathTexts(dn);
    } else if (cls == Video.class) {
      filepath = FilePathFactory.getInstance().getRootPathVideos(dn);
    }

    final File file = new File(filepath, LAST_ID_FILENAME);
    // TODO nicht immer lesend auf datei zugreifen! in variable cachen
    final String content = FileReader.getContent(file, DEFAULT_ENCODING).trim();
    result = Long.parseLong(content);
    return result;
  }

  private void delete(final DomainName dn, final Article article) {
    // delete {id}.html
    String filepath = FilePathFactory.getInstance().getFilePath(dn, article);
    File file = new File(filepath);
    if (file.exists()) {
      file.delete();
    }

    // delete {id}.xml
    filepath = FilePathFactory.getInstance().getMetaFilePath(dn, article);
    file = new File(filepath);
    if (file.exists()) {
      file.delete();
    }

    // delete from cache
    final String key = String.valueOf(article.getId());
    ARTICLES.remove(getDomainNamePrefix(dn) + key);
  }

  private void delete(final DomainName dn, final Category category) {
    // delete {id}.xml
    final String filepath = FilePathFactory.getInstance().getMetaFilePath(dn, category);
    final File file = new File(filepath);
    if (file.exists()) {
      file.delete();
    }

    // delete from cache
    final String key = String.valueOf(category.getId());
    CATEGORIES.remove(getDomainNamePrefix(dn) + key);
  }

  private void delete(final DomainName dn, final Image image) {
    // delete {id}.xml
    String filepath = FilePathFactory.getInstance().getMetaFilePath(dn, image);
    delete(filepath);

    // delete binary data files (jpg, ...)
    filepath = FilePathFactory.getInstance().getFilePath(dn, image);
    delete(filepath);
    filepath = FilePathFactory.getInstance().getPreviewFilePath(dn, image);
    delete(filepath);
    filepath = FilePathFactory.getInstance().getThumbnailFilePath(dn, image);
    delete(filepath);

    // delete from cache
    final String key = String.valueOf(image.getId());
    IMAGES.remove(getDomainNamePrefix(dn) + key);
  }

  private void delete(final String filePath) {
    final File file = new File(filePath);
    if (file.exists()) {
      file.delete();
    }
  }

  @Override
  public File getPreviewFile(final DomainName dn, final Object obj) {
    if (obj instanceof Image) {
      final Image img = (Image) obj;
      final String filepath = FilePathFactory.getInstance().getPreviewFilePath(dn, img);
      return new File(filepath);
    }

    return null;
  }

  @Override
  public File getThumbnailFile(final DomainName dn, final Object obj) {
    if (obj instanceof Image) {
      final Image img = (Image) obj;
      final String filepath = FilePathFactory.getInstance().getThumbnailFilePath(dn, img);
      if (filepath == null) {
        return null;
      }
      return new File(filepath);
    }

    return null;
  }

  @Override
  public void setUseCache(final boolean useCache) {
    this.useCache = useCache;

  }

  @Override
  public boolean useCache() {
    return useCache;
  }

  @Override
  public List<Category> getRootCategories(final DomainName dn) {
    List<Category> result = null;

    if (useCache()) {
      result = (List<Category>) ROOT_CATEGORIES.get(getDomainNamePrefix(dn));
    }
    if (result == null || result.isEmpty()) {
      final RootCategoriesRepository pm = new RootCategoriesRepositoryImpl();
      result = pm.load(dn);
      if (result != null) {
        ROOT_CATEGORIES.put(getDomainNamePrefix(dn), result);
      }
    }
    return result;
  }

  @Override
  public void saveRootCategories(final DomainName dn, final List<Category> rootCategories) {
    final RootCategoriesRepository pm = new RootCategoriesRepositoryImpl();
    pm.save(dn, rootCategories);
    ROOT_CATEGORIES.put(getDomainNamePrefix(dn), rootCategories);
  }

  @Override
  public List<Category> getSiteCategories(final DomainName dn) {
    List<Category> result = null;

    if (useCache()) {
      result = (List<Category>) SITE_CATEGORIES.get(getDomainNamePrefix(dn));
    }
    if (result == null || result.isEmpty()) {
      final SiteCategoriesRepository pm = new SiteCategoriesRepositoryImpl();
      result = pm.load(dn);
      if (result != null) {
        SITE_CATEGORIES.put(getDomainNamePrefix(dn), result);
      }
    }
    return result;
  }

  @Override
  public void saveSiteCategories(final DomainName dn, final List<Category> categories) {
    final SiteCategoriesRepository pm = new SiteCategoriesRepositoryImpl();
    pm.save(dn, categories);
    SITE_CATEGORIES.put(getDomainNamePrefix(dn), categories);
  }
}
