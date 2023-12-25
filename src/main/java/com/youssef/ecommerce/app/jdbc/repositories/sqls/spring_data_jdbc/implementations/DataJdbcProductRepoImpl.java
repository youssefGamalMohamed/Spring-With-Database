package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.implementations;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models.DataJdbcProduct;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models.DataJdbcProductCategory;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.repo_to_service_mapper.DataJdbcProductServiceMapper;
import com.youssef.ecommerce.app.jdbc.services.models.Product;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.interfaces.DataJdbcProductRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.core_interfaces.ProductRepoInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;


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
        DataJdbcProduct dataJdbcProduct = DataJdbcProduct.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .categoriesIds(
                        product.getCategories()
                                .stream()
                                .map(
                                        category -> DataJdbcProductCategory.builder()
                                                .categoryId(category.getId())
                                                .productId(product.getId())
                                                .build()
                                )
                                .collect(Collectors.toSet())
                )
                .build();
        dataJdbcProduct = productRepo.save(dataJdbcProduct);

        return DataJdbcProductServiceMapper.toServiceModel(dataJdbcProduct);
    }

    @Override
    public Product findById(Integer productId) {
        return DataJdbcProductServiceMapper.toServiceModel(productRepo.findById(productId).orElse(null));
    }

    @Override
    public Product findByIdWithCategories(Integer productId) {
        return DataJdbcProductServiceMapper.toServiceModelWithCategories(productRepo.findProductByIdWithCategories(productId));
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
        DataJdbcProduct productFromDb = productRepo.findById(productId).orElse(null);
        if(productFromDb == null)
            return false;

        productFromDb.setName(product.getName());
        productFromDb.setQuantity(product.getQuantity());
        productFromDb.setPrice(product.getPrice());

        productRepo.save(productFromDb);

        return true;
    }
}
