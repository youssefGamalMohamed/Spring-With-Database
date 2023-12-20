package com.youssef.ecommerce.app.jdbc.services.interfaces;

import com.youssef.ecommerce.app.jdbc.entities.ProductCategory;

import java.util.Set;

public interface ProductCategoryServiceInterface {

    void assignCategoriesToProduct(Integer productId , Set<Integer> categoriesId);

    boolean deleteAllByCategoryId(Integer categoryId);

    boolean deleteAllByProductId(Integer productId);

}
