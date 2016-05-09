package de.alexandria.cms.business.impl.service.content;

import de.alexandria.cms.business.api.service.content.ImageService;
import de.alexandria.cms.backend.api.repository.CmsRepository;
import de.alexandria.cms.model.api.content.DomainName;
import de.alexandria.cms.model.api.content.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ralf
 */
@Service
public class ImageServiceImpl implements ImageService {

  @Autowired
  private CmsRepository cmsRepository;

  @Override
  public void delete(final DomainName dn, final Image image) {
    cmsRepository.deleteImage(dn, image);
    cmsRepository.unIndex(dn, image);
  }

  @Override
  public Image load(final DomainName dn, final long id) {
    return cmsRepository.getImageById(dn, id);
  }
}
