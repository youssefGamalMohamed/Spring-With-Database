package com.youssef.ecommerce.app.jdbc.services.implementations;

import com.youssef.ecommerce.app.jdbc.entities.ProductCategory;
import com.youssef.ecommerce.app.jdbc.repositories.implementations.ProductCategoryRepoImpl;
import com.youssef.ecommerce.app.jdbc.services.interfaces.ProductCategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryServiceInterface {

    @Autowired
    private ProductCategoryRepoImpl productCategoryRepo;


    @Override
    public void assignCategoriesToProduct(Integer productId, Set<Integer> categoriesId) {
        Set<ProductCategory> productCategories = new HashSet<>();
        categoriesId.forEach(
                categoryId -> productCategories.add(
                        ProductCategory.builder()
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
