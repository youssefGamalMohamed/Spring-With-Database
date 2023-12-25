package com.youssef.ecommerce.app.jdbc.controllers.mappers.responses;

import com.youssef.ecommerce.app.jdbc.controllers.models.responses.AddCategoryResponseBody;
import com.youssef.ecommerce.app.jdbc.services.models.Category;

public class AddCategoryResponseMapper {

    public static AddCategoryResponseBody toControllerModel(Category category) {
        return  AddCategoryResponseBody.builder()
                .id(category.getId())
                .build();
    }
}
