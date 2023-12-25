package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.repo_to_service_mapper;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models.DataJdbcProduct;
import com.youssef.ecommerce.app.jdbc.services.models.Product;

public class DataJdbcProductServiceMapper {
    public static Product toServiceModel(DataJdbcProduct dataJdbcProduct) {
        if(dataJdbcProduct == null)
            return null;
        return Product.builder()
                .id(dataJdbcProduct.getId())
                .name(dataJdbcProduct.getName())
                .quantity(dataJdbcProduct.getQuantity())
                .price(dataJdbcProduct.getPrice())
                .build();
    }

    public static Product toServiceModelWithCategories(DataJdbcProduct dataJdbcProduct) {
        if(dataJdbcProduct == null)
            return null;
        return Product.builder()
                .id(dataJdbcProduct.getId())
                .name(dataJdbcProduct.getName())
                .quantity(dataJdbcProduct.getQuantity())
                .price(dataJdbcProduct.getPrice())
                .categories(DataJdbcCategoryServiceMapper.toServiceModel(dataJdbcProduct.getCategories()))
                .build();
    }
}
