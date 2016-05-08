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
package de.alexandria.cms.frontend.webapp.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Session configuration.
 * 
 * @author ralf
 */
public class SessionListener implements HttpSessionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        LOGGER.info("==== Session " + session.getId() + " is created ====");
        session.setMaxInactiveInterval(10 * 60 * 60); // seconds: 36000s = 10h
        
        /*
        replaces web.xml config:
        <session-config>
            <session-timeout>600</session-timeout> <!-- minutes: 60 * 10 h -->
        </session-config>
        */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        LOGGER.info("==== Session " + session.getId() + " is destroyed ====");
    }
}
