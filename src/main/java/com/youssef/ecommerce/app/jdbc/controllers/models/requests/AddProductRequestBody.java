package com.youssef.ecommerce.app.jdbc.controllers.models.requests;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddProductRequestBody {
    private String name;
    private Integer quantity;
    private Double price;
    private Set<Integer> categoriesIds;
}
