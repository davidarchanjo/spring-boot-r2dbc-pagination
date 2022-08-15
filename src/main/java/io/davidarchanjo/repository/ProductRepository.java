package io.davidarchanjo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import io.davidarchanjo.model.entity.Product;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
    Flux<Product> findAllBy(Pageable pageable);

    @Query("SELECT * FROM product")
    Flux<Product> findAllByNativeQuery();
}
