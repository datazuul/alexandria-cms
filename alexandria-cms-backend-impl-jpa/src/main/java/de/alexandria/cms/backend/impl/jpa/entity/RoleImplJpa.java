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
package de.alexandria.cms.backend.impl.jpa.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import de.alexandria.cms.model.api.security.Operation;
import de.alexandria.cms.model.api.security.Role;

/**
 *
 * @author ralf
 */
@Entity
@Table(name = "roles") // "role" is a reserved keyword
public class RoleImplJpa implements Role<Long> {

    @Id
    @TableGenerator(
            name = SequenceConstants.GENERATOR_NAME, table = SequenceConstants.TABLE_NAME,
            pkColumnName = SequenceConstants.PK_COLUMN_NAME, valueColumnName = SequenceConstants.VALUE_COLUMN_NAME,
            allocationSize = SequenceConstants.ALLOCATION_SIZE,
            pkColumnValue = "ROLE_SEQ"
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SequenceConstants.GENERATOR_NAME)
    @Column(name = "id")
    private Long id;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles", targetEntity = UserImplJpa.class)
//    private List<User> users;

    @NotEmpty
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = OperationImplJpa.class)
    @JoinTable(name = "role_operation",
            joinColumns = {
                @JoinColumn(name = "role_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                @JoinColumn(name = "operation_id", nullable = false, updatable = false)
            }
    )
    private List<Operation> operations = new ArrayList<>(0);

    public RoleImplJpa() {

    }

    public RoleImplJpa(String role) {
        this.name = role;
    }

    @Override
    public List<Operation> getAllowedOperations() {
        return operations;
    }

    @Override
    public void setAllowedOperations(List<Operation> allowedOperations) {
        this.operations = allowedOperations;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

//    public List<User> getUsers() {
//        return this.users;
//    }
//
//    public void setUser(List<User> users) {
//        this.users = users;
//    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
