package com.youssef.ecommerce.app.jdbc.repositories.interfaces;

import com.youssef.ecommerce.app.jdbc.entities.ProductCategory;

import java.util.Set;

public interface ProductCategoryRepoInterface {

    void assignCategoriesToProduct(Integer productId , Set<Integer> categoriesIds);

    void assignCategoriesToProduct(Set<ProductCategory> productCategories);

    boolean deleteAllByCategoryId(Integer categoryId);

    boolean deleteAllByProductId(Integer productId);
}
