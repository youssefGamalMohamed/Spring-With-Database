package com.youssef.ecommerce.app.jdbc.repositories.interfaces;

import com.youssef.ecommerce.app.jdbc.entities.Category;

import java.util.Optional;

public interface CategoryRepoInterface {

    boolean isExistCategoryByNameIgnoreCase(String categoryName);

    Category saveCategory(Category category) ;

    boolean existsCategoryById(Integer categoryId);

    Optional<Category> findCategoryById(Integer categoryId);

    boolean deleteCategoryById(Integer categoryId);

    boolean updateCategoryById(Integer categoryId , Category category);

}
