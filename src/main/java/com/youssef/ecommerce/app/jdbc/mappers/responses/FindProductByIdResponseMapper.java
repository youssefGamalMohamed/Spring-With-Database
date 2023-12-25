package com.youssef.ecommerce.app.jdbc.mappers.responses;


import com.youssef.ecommerce.app.jdbc.services.models.Product;
import com.youssef.ecommerce.app.jdbc.models.responses.FindProductByIdResponseBody;
import lombok.*;


public class FindProductByIdResponseMapper {

    public static FindProductByIdResponseBody toResponse(Product product) {
        return FindProductByIdResponseBody.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
