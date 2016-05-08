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

import com.mysema.query.types.expr.BooleanExpression;
import java.util.List;
import de.alexandria.cms.backend.api.repository.UserRepository;
import de.alexandria.cms.backend.impl.jpa.entity.QUserImplJpa;
import de.alexandria.cms.backend.impl.jpa.entity.UserImplJpa;
import de.alexandria.cms.model.api.enums.WmsRole;
import de.alexandria.cms.model.api.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ralf
 */
@Repository
public class UserRepositoryImplJpa extends AbstractPagingAndSortingRepositoryImplJpa<UserImplJpa, Long, UserRepositoryJpa> implements UserRepository<UserImplJpa, Long> {

    QUserImplJpa user = QUserImplJpa.userImplJpa;
    BooleanExpression userIsEnabled = user.enabled.eq(true);
    BooleanExpression userIsAdmin = user.roles.isNotEmpty().and(user.roles.any().name.equalsIgnoreCase(Role.PREFIX + WmsRole.ADMIN.name()));

    @Override
    public UserImplJpa create() {
        return new UserImplJpa();
    }

    @Override
    public List<UserImplJpa> findActiveAdminUsers() {
        Iterable result = jpaRepository.findAll(userIsEnabled.and(userIsAdmin));
        return (List<UserImplJpa>) result;
    }

    @Override
    public List<UserImplJpa> findAll(Sort sort) {
        return jpaRepository.findAll(sort);
    }

    @Override
    public UserImplJpa findByEmail(String email) {
        return jpaRepository.findByEmail(email);
    }

    @Autowired
    @Override
    void setJpaRepository(UserRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
}
