package de.alexandria.cms.backend.api.repository;

import de.alexandria.cms.model.api.content.DomainName;
import java.util.List;

public interface SearchRepository {

  void index(DomainName dn, Object obj);

  boolean isIndexEmpty(DomainName dn);

  void deleteIndex(DomainName dn) throws Exception;

  void unIndex(DomainName dn, Object obj);

  /**
   * Perform the search operation and returns a list of found items.
   *
   * @param query string containing search keywords (and operands)
   * @param searchableObject searchable object that has been indexed and now should be used in search
   * @return list of found items
   */
  List search(DomainName dn, String query, Object searchableObject);
}
