package io.davidarchanjo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import io.davidarchanjo.model.entity.Product;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveSortingRepository<Product, Integer> {
    Flux<Product> findAllBy(Pageable pageable);
}
