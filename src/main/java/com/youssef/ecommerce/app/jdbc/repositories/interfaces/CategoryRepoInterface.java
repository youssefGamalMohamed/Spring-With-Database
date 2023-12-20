package com.youssef.ecommerce.app.jdbc.repositories.interfaces;

import com.youssef.ecommerce.app.jdbc.entities.Category;

import java.util.Optional;

public interface CategoryRepoInterface {

    boolean isExistByNameIgnoreCase(String categoryName);

    Category save(Category category) ;

    boolean existsById(Integer categoryId);

    Optional<Category> findById(Integer categoryId);

    boolean deleteById(Integer categoryId);

    boolean updateCategoryById(Integer categoryId , Category category);

}
