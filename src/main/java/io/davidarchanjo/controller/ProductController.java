package io.davidarchanjo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.davidarchanjo.model.dto.PaginationDTO;
import io.davidarchanjo.model.entity.Product;
import io.davidarchanjo.service.ProductService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {
    
    private final ProductService productService;

    @GetMapping
    public Mono<Page<Product>> getAll(PaginationDTO dto){
        return this.productService.getProducts(PageRequest.of(dto.getPage(), dto.getSize()));
    }

}
