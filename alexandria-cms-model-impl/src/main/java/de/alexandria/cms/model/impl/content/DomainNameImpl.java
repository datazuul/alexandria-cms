package de.alexandria.cms.model.impl.content;

import de.alexandria.cms.model.api.content.DomainName;

public class DomainNameImpl implements DomainName {

  private String fullyQualifiedDomainName = null;

  public DomainNameImpl(String fullyQualifiedDomainName) {
    super();
    this.fullyQualifiedDomainName = fullyQualifiedDomainName;
  }

  @Override
  public String getFullyQualifiedDomainName() {
    return fullyQualifiedDomainName;
  }

  @Override
  public String getTopLevelDomain() {
    if (this.fullyQualifiedDomainName == null) {
      return null;
    }
    return getFullyQualifiedDomainName().substring(getFullyQualifiedDomainName().lastIndexOf(".") + 1);
  }
}
