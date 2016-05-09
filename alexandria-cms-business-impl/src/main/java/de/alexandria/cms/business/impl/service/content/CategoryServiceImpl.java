package de.alexandria.cms.business.impl.service.content;

import de.alexandria.cms.business.api.service.content.CategoryService;
import java.util.Iterator;
import java.util.List;

import de.alexandria.cms.backend.api.repository.CmsRepository;
import de.alexandria.cms.model.api.content.Article;
import de.alexandria.cms.model.api.content.Category;
import de.alexandria.cms.model.api.content.DomainName;
import de.alexandria.cms.model.api.content.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ralf
 */
@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CmsRepository cmsRepository;

  @Override
  public void delete(final DomainName dn, final Category category,
          final boolean recursively) {
    // delete all subcategories of category recursively
    final List subcategories = category.getSubcategories();
    if (recursively == true && subcategories != null
            && !subcategories.isEmpty()) {
      for (final Iterator iterator = subcategories.iterator(); iterator
              .hasNext();) {
        Category subcat = (Category) iterator.next();
        subcat = load(dn, subcat.getId());
        delete(dn, subcat, true);
      }
    }

    // delete all articles of category
    final List articles = category.getArticles();
    if (articles != null && !articles.isEmpty()) {
      for (final Iterator iterator = articles.iterator(); iterator
              .hasNext();) {
        Article article = (Article) iterator.next();
        final ArticleServiceImpl as = new ArticleServiceImpl();
        article = as.load(dn, article.getId());
        as.delete(dn, article);
      }
    }

    // delete all images of category
    final List images = category.getImages();
    if (images != null && !images.isEmpty()) {
      for (final Iterator iterator = images.iterator(); iterator
              .hasNext();) {
        Image image = (Image) iterator.next();
        final ImageServiceImpl is = new ImageServiceImpl();
        image = is.load(dn, image.getId());
        is.delete(dn, image);
      }
    }

    // delete category from parent
    Category parent = category.getParent();
    parent = load(dn, parent.getId());
    parent.removeChild(category);
    save(dn, parent);

    // at least delete category itself
    cmsRepository.deleteCategory(dn, category);
    cmsRepository.unIndex(dn, category);
  }

  @Override
  public Category load(final DomainName dn, final long id) {
    return cmsRepository.getCategoryById(dn, id);
  }

  @Override
  public void save(final DomainName dn, final Category parent) {
    cmsRepository.save(dn, parent);
  }
}
