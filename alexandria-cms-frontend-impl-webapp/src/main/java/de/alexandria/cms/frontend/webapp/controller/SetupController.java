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
package de.alexandria.cms.frontend.webapp.controller;

import javax.validation.Valid;
import de.alexandria.cms.business.api.service.RoleService;
import de.alexandria.cms.business.api.service.UserService;
import de.alexandria.cms.model.api.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for system setup tasks.
 *
 * @author ralf
 */
@Controller
@RequestMapping(value = {"/setup"})
@SessionAttributes(value = {"user"})
public class SetupController extends AbstractController implements MessageSourceAware {

    private MessageSource messageSource;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ModelAttribute("createAdmin")
    public boolean adminFlag() {
        return true;
    }
    
    @ModelAttribute("isNew")
    public boolean newFlag() {
        return true;
    }

    @RequestMapping(value = "adminUser", method = RequestMethod.GET)
    public String adminUser(Model model) {
        model.addAttribute("user", userService.createAdminUser());
        return "users/edit";
    }

    @RequestMapping(value = "adminUser", method = RequestMethod.POST)
    public String adminUser(@RequestParam("pwd1") String password1, @RequestParam("pwd2") String password2, @ModelAttribute @Valid User user, BindingResult results, Model model, SessionStatus status, RedirectAttributes redirectAttributes) {
        verifyBinding(results);
        if (results.hasErrors()) {
            return "users/edit";
        }
        userService.create(user, password1, password2, (Errors) results);
        if (results.hasErrors()) {
            return "users/edit";
        }
        status.setComplete();
        String message = messageSource.getMessage("msg.created_successfully", null, LocaleContextHolder.getLocale());
        redirectAttributes.addFlashAttribute("success_message", message);
        return "redirect:/";
    }
}
