package de.alexandria.cms.backend.impl.repository;

import de.alexandria.cms.backend.api.repository.CmsRepository;
import de.alexandria.cms.backend.api.repository.PersistenceRepository;
import de.alexandria.cms.backend.api.repository.SearchRepository;
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
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CmsRepositoryImpl implements CmsRepository {

  private PersistenceRepository pm;

  private static final HashMap<String, SearchRepository> searchRepositoryMap = new HashMap<>();

  private static CmsRepositoryImpl _instance;

  private CmsRepositoryImpl() {
  }

  public static CmsRepositoryImpl getInstance() {
    if (_instance == null) {
      _instance = new CmsRepositoryImpl();
    }
    return _instance;
  }

  // init repository if needed
  public void init(final DomainName dn) {
    // TODO: get init values (author, titles, etc.) from web.xml
    // root category

    // create root category "1"
    Category rootCategory = this.getCategoryById(dn, 1);
    if (rootCategory != null) {
      // init already done before...
      return;
    } else {
      rootCategory = new CategoryImpl(1);
      rootCategory.setName("Startseite");
      this.save(dn, rootCategory);
    }

    List rootCategories = this.getRootCategories(dn);
    if (rootCategories == null || rootCategories.isEmpty()) {
      rootCategories = new ArrayList();
      rootCategories.add(rootCategory);
      this.saveRootCategories(dn, rootCategories);
    }

    // master author (admin)
    Author author = this.getAuthorById(dn, 1);
    if (author == null) {
      // create default author "1"
      author = new AuthorImpl(1);
      author.setFirstname("Ralf");
      author.setSurname("Eichinger");
      this.save(dn, author);
    }

    // // root article under root category
    Article rootArticle = this.getArticleById(dn, 1);
    if (rootArticle == null) {
      // create default article "1"
      rootArticle = new ArticleImpl(1);
      rootArticle.setTitle("Willkommen");
      rootArticle.setHtmlContent("<h1>Willkommen auf der Startseite!</h1><p>Hier kommt Ihr Text...</p>");
      rootArticle.setCategory(rootCategory);
      rootArticle.setAuthor(author);
      this.save(dn, rootArticle);

      rootCategory.getArticles().add(rootArticle);
      this.save(dn, rootCategory);

      author.getArticles().add(rootArticle);
      this.save(dn, author);
    }
  }

  public void setPersistenceRepository(final PersistenceRepository pm) {
    this.pm = pm;
  }

  public void setSearchRepository(final DomainName dn, final SearchRepository searchRepository) {
    searchRepositoryMap.put(dn.getFullyQualifiedDomainName(), searchRepository);
  }

  // common for all objects
  @Override
  public void save(final DomainName dn, final Object obj) {
    pm.save(dn, obj);
  }

  // Article
  @Override
  public List getArticles(final DomainName dn) {
    return pm.findAll(dn, Article.class);
  }

  @Override
  public Article getArticleById(final DomainName dn, final long id) {
    return (Article) pm.getById(dn, Article.class, id);
  }

  @Override
  public void deleteArticle(final DomainName dn, final Article article) {
    pm.delete(dn, article);
  }

  // Author
  @Override
  public List getAuthors(final DomainName dn) {
    return pm.findAll(dn, Author.class);
  }

  @Override
  public Author getAuthorById(final DomainName dn, final long id) {
    return (Author) pm.getById(dn, Author.class, id);
  }

  @Override
  public void deleteAuthor(final DomainName dn, final Author author) {
    pm.delete(dn, author);
  }

  // Category
  @Override
  public List getRootCategories(final DomainName dn) {
    return pm.getRootCategories(dn);
  }

  @Override
  public void saveRootCategories(final DomainName dn, final List<Category> rootCategories) {
    pm.saveRootCategories(dn, rootCategories);
  }

  @Override
  public List getCategories(final DomainName dn) {
    return pm.findAll(dn, Category.class);
  }

  @Override
  public Category getCategoryById(final DomainName dn, final long id) {
    return (Category) pm.getById(dn, Category.class, id);
  }

  @Override
  public void deleteCategory(final DomainName dn, final Category category) {
    pm.delete(dn, category);
  }

  // Image
  @Override
  public List getImages(final DomainName dn) {
    return pm.findAll(dn, Image.class);
  }

  @Override
  public Image getImageById(final DomainName dn, final long id) {
    return (Image) pm.getById(dn, Image.class, id);
  }

  @Override
  public void deleteImage(final DomainName dn, final Image image) {
    pm.delete(dn, image);
  }

  // Text
  @Override
  public List getTexts(final DomainName dn) {
    return pm.findAll(dn, Text.class);
  }

  @Override
  public Text getTextById(final DomainName dn, final long id) {
    return (Text) pm.getById(dn, Text.class, id);
  }

  @Override
  public void deleteText(final DomainName dn, final Text text) {
    pm.delete(dn, text);
  }

  // Video
  @Override
  public List getVideos(final DomainName dn) {
    return pm.findAll(dn, Video.class);
  }

  @Override
  public Video getVideoById(final DomainName dn, final long id) {
    return (Video) pm.getById(dn, Video.class, id);
  }

  @Override
  public void deleteVideo(final DomainName dn, final Video video) {
    pm.delete(dn, video);
  }

  public void index(final DomainName dn, final Object obj) {
    getSearchRepository(dn).index(dn, obj);
  }

  @Override
  public void unIndex(final DomainName dn, final Object obj) {
    getSearchRepository(dn).unIndex(dn, obj);
  }

  /**
   * create index for all objects
   *
   * @param force true: delete index if present before reindexing, false: check if present
   */
  public void reIndex(final DomainName dn, final boolean force) {
    final SearchRepository sr = getSearchRepository(dn);
    if (force) {
      try {
        sr.deleteIndex(dn);
      } catch (final Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    if (sr.isIndexEmpty(dn)) {
      // TODO logging: reindexing...

      // index articles
      final long lastArticleId = pm.getLastId(dn, Article.class);
      for (long i = 1; i <= lastArticleId; i++) {
        final Article article = (Article) pm.getById(dn, Article.class, i);
        if (article != null) {
          index(dn, article);
        }
      }

      // index authors
      final long lastAuthorId = pm.getLastId(dn, Author.class);
      for (long i = 1; i <= lastAuthorId; i++) {
        final Author author = (Author) pm.getById(dn, Author.class, i);
        if (author != null) {
          index(dn, author);
        }
      }

      // index categories
      final long lastCategoryId = pm.getLastId(dn, Category.class);
      for (long i = 1; i <= lastCategoryId; i++) {
        final Category category = (Category) pm.getById(dn, Category.class, i);
        if (category != null) {
          index(dn, category);
        }
      }

      // index images
      final long lastImageId = pm.getLastId(dn, Image.class);
      for (long i = 1; i <= lastImageId; i++) {
        final Image image = (Image) pm.getById(dn, Image.class, i);
        if (image != null) {
          index(dn, image);
        }
      }

      // index texts
      final long lastTextId = pm.getLastId(dn, Text.class);
      for (long i = 1; i <= lastTextId; i++) {
        final Text text = (Text) pm.getById(dn, Text.class, i);
        if (text != null) {
          index(dn, text);
        }
      }

      // index videos
      final long lastVideoId = pm.getLastId(dn, Video.class);
      for (long i = 1; i <= lastVideoId; i++) {
        final Video video = (Video) pm.getById(dn, Video.class, i);
        if (video != null) {
          index(dn, video);
        }
      }
    }
  }

  @Override
  public List search(final DomainName dn, final String query, final Object searchableObject) {
    return getSearchRepository(dn).search(dn, query, searchableObject);
  }

  private SearchRepository getSearchRepository(final DomainName dn) {
    return searchRepositoryMap.get(dn.getFullyQualifiedDomainName());
  }

  @Override
  public List getLatest(final DomainName dn, final int count, final Class objectClass) {
    final List result = new ArrayList();
    final long lastObjectId = pm.getLastId(dn, objectClass);
    for (long i = lastObjectId; i > (lastObjectId - count); i--) {
      final Object obj = pm.getById(dn, objectClass, i);
      if (obj != null) {
        result.add(obj);
      }
    }
    return result;
  }

  /**
   * @return the pm
   */
  public PersistenceRepository getPm() {
    return pm;
  }

  @Override
  public File getPreviewFile(final DomainName dn, final Object obj) {
    return pm.getPreviewFile(dn, obj);
  }

  @Override
  public File getThumbnailFile(final DomainName dn, final Object obj) {
    return pm.getThumbnailFile(dn, obj);
  }

  @Override
  public List getSiteCategories(final DomainName dn) {
    return pm.getSiteCategories(dn);
  }

  @Override
  public void saveSiteCategories(final DomainName dn, final List<Category> categories) {
    pm.saveSiteCategories(dn, categories);
  }

}
