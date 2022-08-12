package io.davidarchanjo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import io.davidarchanjo.model.entity.Product;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveSortingRepository<Product, Integer> {
    Flux<Product> findAll(Pageable pageable);

    @Query("SELECT * FROM product WHERE id < 3")
    Flux<Product> findAll2(Pageable pageable);
}
