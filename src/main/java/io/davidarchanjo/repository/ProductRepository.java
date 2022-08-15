package io.davidarchanjo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import io.davidarchanjo.model.entity.Product;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveSortingRepository<Product, Integer> {
    // Spring Data R2DBC provides support to work with native query and pagination
    @Query("SELECT * FROM product WHERE id < 2")
    Flux<Product> findAllBy(Pageable pageable);
}
