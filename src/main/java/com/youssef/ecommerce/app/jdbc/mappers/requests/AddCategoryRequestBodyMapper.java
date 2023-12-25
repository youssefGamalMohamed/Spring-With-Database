package com.youssef.ecommerce.app.jdbc.mappers.requests;

import com.youssef.ecommerce.app.jdbc.services.models.Category;
import com.youssef.ecommerce.app.jdbc.models.requests.AddCategoryRequestBody;

public class AddCategoryRequestBodyMapper {

    public static Category toServiceModel(AddCategoryRequestBody addCategoryRequestBody) {
        return Category.builder()
                .name(addCategoryRequestBody.getName())
                .build();
    }
}
