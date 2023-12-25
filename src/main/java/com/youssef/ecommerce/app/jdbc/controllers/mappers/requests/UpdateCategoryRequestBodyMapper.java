package com.youssef.ecommerce.app.jdbc.controllers.mappers.requests;

import com.youssef.ecommerce.app.jdbc.services.models.Category;
import com.youssef.ecommerce.app.jdbc.controllers.models.requests.UpdateCategoryRequestBody;

public class UpdateCategoryRequestBodyMapper {


    public static Category toServiceModel(UpdateCategoryRequestBody requestBody) {
        return Category.builder()
                .name(requestBody.getName())
                .build();
    }
}
