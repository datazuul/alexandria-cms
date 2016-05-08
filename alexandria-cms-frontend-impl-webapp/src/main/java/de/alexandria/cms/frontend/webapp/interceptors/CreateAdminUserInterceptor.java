/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alexandria.cms.frontend.webapp.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.alexandria.cms.business.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Check if admin user exists before login dialog.
 *
 * @author ralf
 */
public class CreateAdminUserInterceptor extends HandlerInterceptorAdapter implements MessageSourceAware {

    private MessageSource messageSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAdminUserInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("checking if admin user exists...");
        boolean activeAdminUserExists = userService.doesActiveAdminUserExist();
        if (!activeAdminUserExists) {
            request.setAttribute("createAdminUser", true);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        final Object doCreateAdminUser = request.getAttribute("createAdminUser");
        if (doCreateAdminUser != null) {
            boolean createAdminUser = (boolean) doCreateAdminUser;
            if (createAdminUser) {
                modelAndView.setView(new RedirectView("/setup/adminUser"));
                String message = messageSource.getMessage("msg.create_a_new_admin_user", null, LocaleContextHolder.getLocale());
                modelAndView.addObject("info_message", message);
                LOGGER.info("Admin user does not exist. Create a new administrator user.");
            }
        }
    }
}
