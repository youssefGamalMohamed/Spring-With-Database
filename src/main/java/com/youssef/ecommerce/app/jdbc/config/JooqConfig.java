package com.youssef.ecommerce.app.jdbc.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JooqConfig {

    @Bean
    public DSLContext dsl(DataSource dataSource) {
        DataSourceConnectionProvider connectionProvider = new DataSourceConnectionProvider(dataSource);
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.set(connectionProvider);
        configuration.set(SQLDialect.MYSQL); // Set your SQLDialect here
        return configuration.dsl();
    }
}
