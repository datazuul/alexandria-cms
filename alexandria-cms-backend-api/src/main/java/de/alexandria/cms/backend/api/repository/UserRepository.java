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
package de.alexandria.cms.backend.api.repository;

import java.io.Serializable;
import java.util.List;
import de.alexandria.cms.model.api.security.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository for User persistence handling.
 *
 * @author ralf
 * @param <T> entity instance
 * @param <ID> unique id
 */
public interface UserRepository<T extends User, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    T create();

    @Override
    List<T> findAll(Sort sort);

    T findByEmail(String email);

    List<T> findActiveAdminUsers();
}
