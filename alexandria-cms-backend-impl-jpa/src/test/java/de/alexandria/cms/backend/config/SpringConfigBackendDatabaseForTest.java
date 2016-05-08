package de.alexandria.cms.backend.config;

import de.alexandria.cms.config.SpringConfigBackendDatabase;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Created by ralf on 05.08.14.
 */
public class SpringConfigBackendDatabaseForTest extends SpringConfigBackendDatabase {

    @Bean
    @Override
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2);
        return builder.build();
    }
}
