package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.implementations;

import com.youssef.ecommerce.app.jdbc.repositories.core_interfaces.CategoryRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.interfaces.JpaCategoryRepo;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models.JpaCategory;
import com.youssef.ecommerce.app.jdbc.services.models.Category;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class JpaCategoryRepoImpl implements CategoryRepoInterface {

    @Lazy
    @Autowired
    private  JpaCategoryRepo jpaCategoryRepo;

    @Override
    public boolean isExistCategoryByNameIgnoreCase(String categoryName) {
        return jpaCategoryRepo.existsByNameIgnoreCase(categoryName);
    }

    @Override
    public Category saveCategory(Category category) {
        JpaCategory entity = JpaCategory.builder()
                .name(category.getName())
                .build();
        entity = jpaCategoryRepo.save(entity);
        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public boolean existsCategoryById(Integer categoryId) {
        return jpaCategoryRepo.existsById(categoryId);
    }

    @Override
    public Optional<Category> findCategoryById(Integer categoryId) {
        Optional<JpaCategory> entity = jpaCategoryRepo.findById(categoryId);
        return entity.map(
                e -> Category.builder()
                        .id(entity.get().getId())
                        .name(entity.get().getName())
                        .build()
        );
    }

    @Override
    public boolean deleteCategoryById(Integer categoryId) {
        jpaCategoryRepo.deleteById(categoryId);
        return true;
    }

    @Override
    public boolean updateCategoryById(Integer categoryId, Category category) {
        Optional<JpaCategory> entity = jpaCategoryRepo.findById(categoryId);
        if(entity.isEmpty())
            return false;
        entity.get().setName(category.getName());
        jpaCategoryRepo.save(entity.get());
        return true;
    }
}
