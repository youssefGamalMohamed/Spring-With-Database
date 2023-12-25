package com.youssef.ecommerce.app.jdbc.services.interfaces;


import java.util.Set;

public interface ProductCategoryServiceInterface {

    void assignCategoriesToProduct(Integer productId , Set<Integer> categoriesId);

    boolean deleteAllByCategoryId(Integer categoryId);

    boolean deleteAllByProductId(Integer productId);

}
