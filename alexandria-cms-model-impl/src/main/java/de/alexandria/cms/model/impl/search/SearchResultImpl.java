package de.alexandria.cms.model.impl.search;

import de.alexandria.cms.model.api.search.SearchResult;
import java.io.Serializable;

public class SearchResultImpl implements Serializable, SearchResult {

  Long handle = null;
  String highlightedText = null;

  /**
   * @return the handle
   */
  @Override
  public Long getHandle() {
    return handle;
  }

  /**
   * @param handle the handle to set
   */
  @Override
  public void setHandle(Long handle) {
    this.handle = handle;
  }

  /**
   * @return the highlightedText
   */
  @Override
  public String getHighlightedText() {
    return highlightedText;
  }

  /**
   * @param highlightedText the highlightedText to set
   */
  @Override
  public void setHighlightedText(String highlightedText) {
    this.highlightedText = highlightedText;
  }
}
