package de.alexandria.cms.model.impl.content;

import de.alexandria.cms.model.api.search.SearchTarget;
import java.io.Serializable;

public class SearchTargetImpl implements Serializable, SearchTarget {

  public static final int AUTHORS_AND_TITLES = 0;
  public static final int CATEGORIES_AND_ARTICLES = 1;

  // TODO use Enumeration instead...
  int id;
  String label;

  public SearchTargetImpl(int id, String label) {
    super();
    this.id = id;
    this.label = label;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getLabel() {
    return label;
  }

  @Override
  public String toString() {
    return label;
  }
}
