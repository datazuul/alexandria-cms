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
package de.alexandria.cms.model.api;

import java.io.Serializable;

/**
 * A resource representing the raw data for content.
 * @author ralf
 * @param <ID> unique id
 * @param <C> the resource data
 */
public interface Resource<ID extends Serializable, C extends Object> extends Identifiable<ID> {
    C getContent();

    String getContentType();

    void setContent(C content);
}
