package com.youssef.ecommerce.app.jdbc.models.requests;


import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UpdateProductRequestBody {

    private String name;
    private Integer quantity;
    private Double price;
    private Set<Integer> removedCategoriesIds;
    private Set<Integer> addedCategoriesIds;
}
