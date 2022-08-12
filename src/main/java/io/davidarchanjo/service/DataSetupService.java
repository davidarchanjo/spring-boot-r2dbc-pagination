package io.davidarchanjo.service;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import io.davidarchanjo.model.entity.Product;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DataSetupService implements CommandLineRunner {

    @Value("classpath:init.sql")
    private Resource initSql;
    
    @Autowired
    private R2dbcEntityTemplate entityTemplate;

    @Override
    public void run(String... args) throws Exception {
        var query = StreamUtils.copyToString(initSql.getInputStream(), StandardCharsets.UTF_8);
        this.entityTemplate.getDatabaseClient()
            .sql(query)
            .then()
            .then(insertProducts())
            .subscribe();
    }

    private Mono<Void> insertProducts() {
        return Flux.range(1, 100)
            .map(i -> Product.builder()
                .id(null)
                .description("product - " + i)
                .price(ThreadLocalRandom.current().nextInt(1, 1000))
                .build())
            .flatMap(this.entityTemplate::insert)
            .doOnComplete(() -> log.info("Inserted all records"))
            .then();
    }

}