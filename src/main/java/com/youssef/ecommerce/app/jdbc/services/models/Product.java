package com.youssef.ecommerce.app.jdbc.services.models;



import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Product {

    private Integer id;

    private String name;

    private Integer quantity;

    private Double price;

    private Set<Category> categories;

}
