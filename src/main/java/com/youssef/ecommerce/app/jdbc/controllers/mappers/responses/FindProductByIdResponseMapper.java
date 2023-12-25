package com.youssef.ecommerce.app.jdbc.controllers.mappers.responses;


import com.youssef.ecommerce.app.jdbc.services.models.Product;
import com.youssef.ecommerce.app.jdbc.controllers.models.responses.FindProductByIdResponseBody;


public class FindProductByIdResponseMapper {

    public static FindProductByIdResponseBody toControllerModel(Product product) {
        return FindProductByIdResponseBody.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
