package io.davidarchanjo.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.davidarchanjo.model.dto.PaginationDTO;
import io.davidarchanjo.service.ProductService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Mono<?> getAllByPageable(PaginationDTO dto) {
        return this.productService.getAllProducts(PageRequest.of(dto.getPage(), dto.getSize()), false);
    }

    @GetMapping("nativeQuery")
    public Mono<?> getAllByNativeQuery(PaginationDTO dto) {
        return this.productService.getAllProducts(PageRequest.of(dto.getPage(), dto.getSize()), true);
    }

}
