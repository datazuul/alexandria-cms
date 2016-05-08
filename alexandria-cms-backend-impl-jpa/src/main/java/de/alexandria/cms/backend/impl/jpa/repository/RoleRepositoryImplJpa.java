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
package de.alexandria.cms.backend.impl.jpa.repository;

import java.util.List;
import de.alexandria.cms.backend.api.repository.RoleRepository;
import de.alexandria.cms.backend.impl.jpa.entity.RoleImplJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ralf
 */
@Repository
public class RoleRepositoryImplJpa extends AbstractPagingAndSortingRepositoryImplJpa<RoleImplJpa, Long, RoleRepositoryJpa> implements RoleRepository<RoleImplJpa, Long> {

    @Autowired
    @Override
    void setJpaRepository(RoleRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public RoleImplJpa create() {
        return new RoleImplJpa();
    }

    @Override
    public List<RoleImplJpa> findAll(Sort sort) {
        return jpaRepository.findAll(sort);
    }

    @Override
    public RoleImplJpa findByName(String name) {
        return jpaRepository.findByName(name);
    }


}
