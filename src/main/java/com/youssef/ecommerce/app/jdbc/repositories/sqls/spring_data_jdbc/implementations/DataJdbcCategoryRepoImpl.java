package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.implementations;


import com.youssef.ecommerce.app.jdbc.repositories.core_interfaces.CategoryRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.interfaces.DataJdbcCategoryRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models.DataJdbcCategory;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.repo_to_service_mapper.DataJdbcCategoryServiceMapper;
import com.youssef.ecommerce.app.jdbc.services.models.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        DataJdbcCategory entity = DataJdbcCategory.builder()
                .name(category.getName())
                .build();

        return DataJdbcCategoryServiceMapper.toServiceModel(
                categoryRepo.save(entity)
        );
    }

    @Override
    public boolean existsCategoryById(Integer categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }

    @Override
    public Optional<Category> findCategoryById(Integer categoryId) {
        Optional<DataJdbcCategory> entity = categoryRepo.findById(categoryId);
        if(entity.isEmpty())
            return Optional.empty();
        return Optional.of(
                DataJdbcCategoryServiceMapper.toServiceModel(entity.get())
        );
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
//        Category categoryFromDB = categoryRepo.findById(categoryId).orElse(null);
//        if(categoryFromDB == null)
//            return false;
//        categoryFromDB.setName(category.getName());
//        categoryRepo.save(categoryFromDB);

        categoryRepo.updateCategoryById(categoryId , category.getName());
        return true;
    }
}
