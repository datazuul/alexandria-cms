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

import de.alexandria.cms.model.api.ContentView;
import de.alexandria.cms.model.api.enums.ViewType;
import de.alexandria.cms.model.api.Resource;

/**
 * An HTML content.
 *
 * @author ralf
 */
public class ContentViewImpl implements ContentView<Long> {

    private Long id;
    private ViewType viewType = ViewType.FULL;
    private Resource resource;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Resource getResource() {
        return resource;
    }

    @Override
    public ViewType getViewType() {
        return viewType;
    }

    @Override
    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }

}
