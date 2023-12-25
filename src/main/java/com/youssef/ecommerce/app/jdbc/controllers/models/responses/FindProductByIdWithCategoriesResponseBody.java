package com.youssef.ecommerce.app.jdbc.controllers.models.responses;

import com.youssef.ecommerce.app.jdbc.services.models.Category;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class FindProductByIdWithCategoriesResponseBody {

    private Integer id;
    private String name;
    private Integer quantity;
    private Double price;
    private Set<Category> categories;
}
