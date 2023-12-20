package com.youssef.ecommerce.app.jdbc.mappers.requests;

import com.youssef.ecommerce.app.jdbc.entities.Category;
import com.youssef.ecommerce.app.jdbc.models.requests.UpdateCategoryRequestBody;

public class UpdateCategoryRequestBodyMapper {


    public static Category toEntity(UpdateCategoryRequestBody requestBody) {
        return Category.builder()
                .name(requestBody.getName())
                .build();
    }
}
