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
package de.alexandria.cms.business.impl.service;

import java.util.List;
import de.alexandria.cms.backend.api.repository.RoleRepository;
import de.alexandria.cms.business.api.service.RoleService;
import de.alexandria.cms.model.api.enums.WmsRole;
import de.alexandria.cms.model.api.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ralf
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService<Role, Long> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create() {
        return roleRepository.create();
    }

    @Override
    public Role getAdminRole() {
        return roleRepository.findByName(Role.PREFIX + WmsRole.ADMIN);
    }

    @Override
    public Role get(Long id) {
        return (Role) roleRepository.findOne(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

}
