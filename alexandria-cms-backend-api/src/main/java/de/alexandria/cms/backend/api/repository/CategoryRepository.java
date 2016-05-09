package de.alexandria.cms.backend.api.repository;

import de.alexandria.cms.model.api.content.Category;
import de.alexandria.cms.model.api.content.DomainName;

public interface CategoryRepository {

  public void save(final DomainName dn, final Category category);

  public Category load(DomainName dn, Category category);

}
