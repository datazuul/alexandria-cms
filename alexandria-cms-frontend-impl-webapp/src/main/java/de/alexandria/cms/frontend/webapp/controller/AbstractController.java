package de.alexandria.cms.frontend.webapp.controller;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

/**
 * Created by ralf on 13.08.14.
 */
public abstract class AbstractController {

    protected void verifyBinding(BindingResult br) {
        String[] suppressedFields = br.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind suppressed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
    }
}
