package com.youssef.ecommerce.app.jdbc.repositories.core_interfaces;

import com.youssef.ecommerce.app.jdbc.services.models.Product;

public interface ProductRepoInterface {

    boolean isExistProductByNameIgnoreCase(String productName);

    Product save(Product product);

    Product findById(Integer productId);

    Product findByIdWithCategories(Integer productId);

    boolean deleteById(Integer productId);

    boolean updateProductById(Integer productId, Product product);
}
