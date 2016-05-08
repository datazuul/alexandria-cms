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
import de.alexandria.cms.backend.api.repository.OperationRepository;
import de.alexandria.cms.business.api.service.OperationService;
import de.alexandria.cms.model.api.security.Operation;
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
public class OperationServiceImpl implements OperationService<Operation, Long> {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public List<Operation> getAll() {
        return operationRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

}
