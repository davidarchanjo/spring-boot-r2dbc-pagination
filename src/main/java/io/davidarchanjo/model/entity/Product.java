package io.davidarchanjo.model.entity;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Product {

    @Id
    private Integer id;
    private Integer price;
    private String description;

    @Builder
    public Product(Integer id, Integer price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

}
