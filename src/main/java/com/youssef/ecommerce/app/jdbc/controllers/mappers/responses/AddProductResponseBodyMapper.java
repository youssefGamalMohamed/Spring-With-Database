package com.youssef.ecommerce.app.jdbc.controllers.mappers.responses;

import com.youssef.ecommerce.app.jdbc.controllers.models.responses.AddProductResponseBody;
import com.youssef.ecommerce.app.jdbc.services.models.Product;

public class AddProductResponseBodyMapper {

    public static AddProductResponseBody toControllerModel(Product product) {
        return AddProductResponseBody.builder()
                .id(product.getId())
                .build();
    }
}
