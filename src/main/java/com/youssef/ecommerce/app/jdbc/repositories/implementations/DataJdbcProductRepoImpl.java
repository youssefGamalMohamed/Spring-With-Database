package com.youssef.ecommerce.app.jdbc.repositories.implementations;

import com.youssef.ecommerce.app.jdbc.entities.Product;
import com.youssef.ecommerce.app.jdbc.repositories.interfaces.DataJdbcProductRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.interfaces.ProductRepoInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
@Slf4j
public class DataJdbcProductRepoImpl implements ProductRepoInterface {


    @Autowired
    private DataJdbcProductRepoInterface productRepo;

    @Override
    public boolean isExistProductByNameIgnoreCase(String productName) {
        return productRepo.existsByNameIgnoreCase(productName);
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product findById(Integer productId) {
        return productRepo.findById(productId).orElse(null);
    }

    @Override
    public Product findByIdWithCategories(Integer productId) {
        return productRepo.findProductByIdWithCategories(productId);
    }

    @Override
    public boolean deleteById(Integer productId) {
        try {
            productRepo.deleteById(productId);

        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateProductById(Integer productId, Product product) {
        Product productFromDb = productRepo.findById(productId).orElse(null);
        if(productFromDb == null)
            return false;

        productFromDb.setName(product.getName());
        productFromDb.setQuantity(product.getQuantity());
        productFromDb.setPrice(product.getPrice());

        productRepo.save(productFromDb);

        return true;
    }
}
