package com.youssef.ecommerce.app.jdbc.mappers.responses;

import com.youssef.ecommerce.app.jdbc.services.models.Product;
import com.youssef.ecommerce.app.jdbc.models.responses.FindProductByIdWithCategoriesResponseBody;

public class FindProductByIdWithCategoriesResponseMapper {

    public static FindProductByIdWithCategoriesResponseBody toResponse(Product product) {
        return FindProductByIdWithCategoriesResponseBody.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categories(product.getCategories())
                .build();
    }
}
