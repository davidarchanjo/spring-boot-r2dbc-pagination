package io.davidarchanjo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.davidarchanjo.model.entity.Product;
import io.davidarchanjo.repository.ProductRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {
    
    private final ProductRepository repository;

    public Mono<Page<Product>> getProducts(PageRequest pageRequest) {
        return this.repository.findAllBy(pageRequest/* .withSort(org.springframework.data.domain.Sort.by("price").descending()) */)
            .collectList()
            .zipWith(this.repository.count())
            .map(t -> new PageImpl<>(t.getT1(), pageRequest, t.getT2()));
    }

}
