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
package de.alexandria.cms.business.api.service;

import java.io.Serializable;
import java.util.List;
import de.alexandria.cms.model.api.security.Role;

/**
 * 
 * @author ralf
 * 
 * @param <T> domain object
 * @param <ID> unique id
 */
public interface RoleService<T extends Role, ID extends Serializable> {

    T create();
    
    T getAdminRole();
    
    T get(ID id);
    
    List<T> getAll();
}
