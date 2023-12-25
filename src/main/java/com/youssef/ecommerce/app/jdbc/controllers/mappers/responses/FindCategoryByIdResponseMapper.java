package com.youssef.ecommerce.app.jdbc.controllers.mappers.responses;

import com.youssef.ecommerce.app.jdbc.controllers.models.responses.FindCategoryByIdResponse;
import com.youssef.ecommerce.app.jdbc.services.models.Category;

public class FindCategoryByIdResponseMapper {

    public static FindCategoryByIdResponse toControllerModel(Category category) {
        return FindCategoryByIdResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
