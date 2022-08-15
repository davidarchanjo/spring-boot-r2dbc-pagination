package io.davidarchanjo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

@Configuration
public class InitSqlConfig {

    @Value("classpath:sql/init-h2.sql")
    private Resource h2InitSql;

    @Value("classpath:sql/init-postgres.sql")
    private Resource postgresInitSql;
    
    @Profile("default")
    @Bean("initSql")
    public Resource h2InitSql() {
        return h2InitSql;
    }

    @Profile("postgres")
    @Bean("initSql")
    public Resource postgresInitSql() {
        return postgresInitSql;
    }
    
}