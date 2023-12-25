package com.youssef.ecommerce.app.jdbc.services.implementations;


import com.youssef.ecommerce.app.jdbc.services.models.Category;
import com.youssef.ecommerce.app.jdbc.controllers.mappers.requests.AddCategoryRequestBodyMapper;
import com.youssef.ecommerce.app.jdbc.controllers.mappers.requests.UpdateCategoryRequestBodyMapper;
import com.youssef.ecommerce.app.jdbc.controllers.models.requests.AddCategoryRequestBody;
import com.youssef.ecommerce.app.jdbc.controllers.models.requests.UpdateCategoryRequestBody;
import com.youssef.ecommerce.app.jdbc.controllers.models.responses.FindCategoryByIdResponse;
import com.youssef.ecommerce.app.jdbc.repositories.core_interfaces.CategoryRepoInterface;
import com.youssef.ecommerce.app.jdbc.services.interfaces.CategoryServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryServiceInterface {


    // REPO
    @Autowired
    @Qualifier("jpaCategoryRepoImpl")
    private CategoryRepoInterface categoryRepo;



    @Override
    public Category addNewCategory(AddCategoryRequestBody categoryRequestBody) {
        if(categoryRepo.isExistCategoryByNameIgnoreCase(categoryRequestBody.getName())) {
            log.error(">>>>> JpaCategory With Name = " + categoryRequestBody.getName() + " IS Already EXIST");
            return null;
        }
        Category category = AddCategoryRequestBodyMapper.toServiceModel(categoryRequestBody);
        category = categoryRepo.saveCategory(category);

        return category;
    }

    @Override
    public boolean isCategoryExistsById(Integer categoryId) {
        return categoryRepo.existsCategoryById(categoryId);
    }

    @Override
    public Category findById(Integer categoryId) {
        Optional<Category> category = categoryRepo.findCategoryById(categoryId);
        if(category.isEmpty()) {
            return null;
        }
        log.info(">>>>> JpaCategory = " + category);
        return category.get();
    }

    @Override
    public boolean deleteById(Integer categoryId) {
        return categoryRepo.deleteCategoryById(categoryId);
    }

    @Override
    public boolean updateCategoryById(Integer categoryId, UpdateCategoryRequestBody updateCategoryRequestBody) {
        return categoryRepo.updateCategoryById(categoryId , UpdateCategoryRequestBodyMapper.toServiceModel(updateCategoryRequestBody));
    }

    @Override
    public Optional<Category> findCategoryById(Integer categoryId) {
        return categoryRepo.findCategoryById(categoryId);
    }


}
