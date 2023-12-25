package com.youssef.ecommerce.app.jdbc.services.interfaces;

import com.youssef.ecommerce.app.jdbc.services.models.Category;
import com.youssef.ecommerce.app.jdbc.controllers.models.requests.AddCategoryRequestBody;
import com.youssef.ecommerce.app.jdbc.controllers.models.requests.UpdateCategoryRequestBody;
import com.youssef.ecommerce.app.jdbc.controllers.models.responses.FindCategoryByIdResponse;

import java.util.Optional;

public interface CategoryServiceInterface {
    Category addNewCategory(AddCategoryRequestBody categoryRequestBody);

    boolean isCategoryExistsById(Integer categoryId);

    Category findById(Integer categoryId);

    boolean deleteById(Integer categoryId);

    boolean updateCategoryById(Integer categoryId, UpdateCategoryRequestBody updateCategoryRequestBody);

    Optional<Category> findCategoryById(Integer categoryId);
}
