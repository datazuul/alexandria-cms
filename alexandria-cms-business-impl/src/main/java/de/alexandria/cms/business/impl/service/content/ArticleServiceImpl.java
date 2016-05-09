package de.alexandria.cms.business.impl.service.content;

import de.alexandria.cms.business.api.service.content.ArticleService;
import de.alexandria.cms.backend.api.repository.CmsRepository;
import de.alexandria.cms.model.api.content.Article;
import de.alexandria.cms.model.api.content.DomainName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ralf Eichinger
 */
@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  private CmsRepository cmsRepository;

  @Override
  public void delete(final DomainName dn, final Article article) {
    cmsRepository.deleteArticle(dn, article);
    cmsRepository.unIndex(dn, article);
  }

  @Override
  public Article load(final DomainName dn, final long id) {
    return cmsRepository.getArticleById(dn, id);
  }

  @Override
  public void save(final DomainName dn, final Article article) {
    cmsRepository.save(dn, article);
  }
}
