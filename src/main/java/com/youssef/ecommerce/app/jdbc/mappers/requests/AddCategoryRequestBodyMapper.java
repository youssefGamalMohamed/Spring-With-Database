package com.youssef.ecommerce.app.jdbc.mappers.requests;

import com.youssef.ecommerce.app.jdbc.entities.Category;
import com.youssef.ecommerce.app.jdbc.models.requests.AddCategoryRequestBody;

public class AddCategoryRequestBodyMapper {

    public static Category toEntity(AddCategoryRequestBody addCategoryRequestBody) {
        return Category.builder()
                .name(addCategoryRequestBody.getName())
                .build();
    }
}
