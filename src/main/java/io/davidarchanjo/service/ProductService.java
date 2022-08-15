package io.davidarchanjo.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.davidarchanjo.model.entity.Product;
import io.davidarchanjo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {
    
    private final ProductRepository repository;

    public Mono<Page<Product>> getAllProducts(PageRequest pageRequest, boolean nativeQuery) {
        return nativeQuery 
            ? this.repository.findAllByNativeQuery()
                .buffer(pageRequest.getPageSize(), (pageRequest.getPageNumber() + 1))
                .elementAt(pageRequest.getPageNumber(), new ArrayList<>())
                .flatMapMany(Flux::fromIterable)
                .collectList()
                .map(t -> new PageImpl<>(t, pageRequest, t.size()))
            : this.repository.findAllBy(pageRequest)
                .collectList()
                .zipWith(this.repository.count())
                .map(t -> new PageImpl<>(t.getT1(), pageRequest, t.getT2()));
    }

}
