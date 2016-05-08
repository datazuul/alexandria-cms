package de.alexandria.cms.frontend.webapp.propertyeditor;

import java.beans.PropertyEditorSupport;
import de.alexandria.cms.business.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleEditor extends PropertyEditorSupport {

    @Autowired
    RoleService roleService;

    @Override
    public void setAsText(String text) {
        long id = Long.parseLong(text);
        setValue(roleService.get(id));
    }
}
