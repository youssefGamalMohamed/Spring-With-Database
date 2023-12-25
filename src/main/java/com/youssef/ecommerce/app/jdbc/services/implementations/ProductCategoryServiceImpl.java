package com.youssef.ecommerce.app.jdbc.services.implementations;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.implementations.JdbcTemplateJdbcTemplateProductCategoryRepoImpl;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models.JdbcTemplateProductCategory;
import com.youssef.ecommerce.app.jdbc.services.interfaces.ProductCategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryServiceInterface {

    @Autowired
    private JdbcTemplateJdbcTemplateProductCategoryRepoImpl productCategoryRepo;


    @Override
    public void assignCategoriesToProduct(Integer productId, Set<Integer> categoriesId) {
        Set<JdbcTemplateProductCategory> productCategories = new HashSet<>();
        categoriesId.forEach(
                categoryId -> productCategories.add(
                        JdbcTemplateProductCategory.builder()
                                .productId(productId)
                                .categoryId(categoryId)
                                .build()
                )
        );
        productCategoryRepo.assignCategoriesToProduct(productCategories);
    }

    @Override
    public boolean deleteAllByCategoryId(Integer categoryId) {
        return productCategoryRepo.deleteAllByCategoryId(categoryId);
    }

    @Override
    public boolean deleteAllByProductId(Integer productId) {
        return productCategoryRepo.deleteAllByProductId(productId);
    }

}
