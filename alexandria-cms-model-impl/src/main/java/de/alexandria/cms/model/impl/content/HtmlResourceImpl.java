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
package de.alexandria.cms.model.impl.content;

import de.alexandria.cms.model.api.enums.MimeTypes;
import de.alexandria.cms.model.api.Resource;

/**
 * An HTML resource.
 * @author ralf
 */
public class HtmlResourceImpl implements Resource<Long, String>{
    private String html;
    private Long id;

    @Override
    public String getContent() {
        return html;
    }

    @Override
    public String getContentType() {
        return MimeTypes.MIME_TEXT_HTML;
    }

    @Override
    public void setContent(String html) {
        this.html = html;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
}
