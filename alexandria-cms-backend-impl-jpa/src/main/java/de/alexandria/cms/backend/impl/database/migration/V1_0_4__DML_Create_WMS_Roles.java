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
package de.alexandria.cms.backend.impl.database.migration;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import de.alexandria.cms.model.api.enums.WmsRole;
import de.alexandria.cms.model.api.security.Role;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author ralf
 */
public class V1_0_4__DML_Create_WMS_Roles implements SpringJdbcMigration {

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        WmsRole[] values = WmsRole.values();
        int i = 0;
        for (WmsRole value : values) {
            jdbcTemplate.update("INSERT into roles (id, name) values (?,?)", ++i, Role.PREFIX + value);
        }
    }
}
