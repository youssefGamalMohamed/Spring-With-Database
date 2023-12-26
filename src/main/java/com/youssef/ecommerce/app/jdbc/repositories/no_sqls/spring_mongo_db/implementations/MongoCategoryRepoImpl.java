package com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.implementations;

import com.youssef.ecommerce.app.jdbc.repositories.core_interfaces.CategoryRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.core_interfaces.ProductRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.generators.NextSequenceGeneratorService;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.interfaces.MongoCategoryRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.interfaces.MongoProductRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.MongoCategory;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.MongoProduct;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.sequences.CustomSequence;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.repo_to_service_mapper.MongoCategoryServiceMapper;
import com.youssef.ecommerce.app.jdbc.services.models.Category;
import com.youssef.ecommerce.app.jdbc.services.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoCategoryRepoImpl implements CategoryRepoInterface {

    @Autowired
    private MongoCategoryRepoInterface categoryRepo;

    @Autowired
    private NextSequenceGeneratorService nextSequenceGeneratorService;

    @Override
    public boolean isExistCategoryByNameIgnoreCase(String categoryName) {
        return categoryRepo.existsByNameIgnoreCase(categoryName);
    }

    @Override
    public Category saveCategory(Category category) {
        MongoCategory entity = MongoCategory.builder()
                .id(String.valueOf(nextSequenceGeneratorService.getNextSequence(CustomSequence.CUSTOM_SEQUENCE_COLUMN_NAME)))
                .name(category.getName())
                .build();

        entity = categoryRepo.save(entity);

        return MongoCategoryServiceMapper.toServiceModel(entity);
    }

    @Override
    public boolean existsCategoryById(Integer categoryId) {
        return categoryRepo.findById(String.valueOf(categoryId)).isPresent();
    }

    @Override
    public Optional<Category> findCategoryById(Integer categoryId) {
        Optional<MongoCategory> entity = categoryRepo.findById(String.valueOf(categoryId));
        if(entity.isEmpty())
            return Optional.empty();
        return Optional.of(MongoCategoryServiceMapper.toServiceModel(entity.get()));
    }

    @Override
    public boolean deleteCategoryById(Integer categoryId) {
        categoryRepo.deleteById(String.valueOf(categoryId));
        return true;
    }

    @Override
    public boolean updateCategoryById(Integer categoryId, Category category) {
        Optional<MongoCategory> entity = categoryRepo.findById(String.valueOf(categoryId));
        if(entity.isEmpty())
            return false;
        entity.get().setName(category.getName());
        categoryRepo.save(entity.get());
        return true;
    }
}
