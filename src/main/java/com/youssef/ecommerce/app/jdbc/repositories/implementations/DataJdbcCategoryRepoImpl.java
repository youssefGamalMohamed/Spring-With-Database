package com.youssef.ecommerce.app.jdbc.repositories.implementations;

import com.youssef.ecommerce.app.jdbc.entities.Category;
import com.youssef.ecommerce.app.jdbc.repositories.interfaces.CategoryRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.interfaces.DataJdbcCategoryRepoInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;


@Repository
@Slf4j
public class DataJdbcCategoryRepoImpl implements CategoryRepoInterface {

    @Autowired
    private DataJdbcCategoryRepoInterface categoryRepo;

    @Override
    public boolean isExistCategoryByNameIgnoreCase(String categoryName) {
        return categoryRepo.existsByNameIgnoreCase(categoryName);
    }

    @Override
    public Category saveCategory(Category category) {
        category.setProducts(new HashSet<>());
        return categoryRepo.save(category);
    }

    @Override
    public boolean existsCategoryById(Integer categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }

    @Override
    public Optional<Category> findCategoryById(Integer categoryId) {
        return categoryRepo.findById(categoryId);
    }

    @Override
    public boolean deleteCategoryById(Integer categoryId) {
        log.info(">>>>>>>>>>>> HERE" + categoryId);
        try {
            categoryRepo.deleteById(categoryId);
        }
        catch (IllegalArgumentException exception) {
            log.error(">>>> Exception " + exception.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCategoryById(Integer categoryId, Category category) {
        Category categoryFromDB = categoryRepo.findById(categoryId).orElse(null);
        if(categoryFromDB == null)
            return false;
        categoryFromDB.setName(category.getName());
        categoryRepo.save(categoryFromDB);
        return true;
    }
}
