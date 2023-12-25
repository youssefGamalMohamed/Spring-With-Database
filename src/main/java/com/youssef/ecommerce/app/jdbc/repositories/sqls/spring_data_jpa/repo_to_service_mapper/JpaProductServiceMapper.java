package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.repo_to_service_mapper;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models.JpaCategory;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models.JpaProduct;
import com.youssef.ecommerce.app.jdbc.services.models.Product;

import java.util.HashSet;
import java.util.stream.Collectors;

public class JpaProductServiceMapper {

    public static Product toServiceModel(JpaProduct jpaProduct) {
        return Product.builder()
                .id(jpaProduct.getId())
                .name(jpaProduct.getName())
                .price(jpaProduct.getPrice())
                .quantity(jpaProduct.getQuantity())
                .categories(
                        JpaCategoryServiceMapper.toServiceModels(jpaProduct.getCategories())
                )
                .build();
    }

    public static JpaProduct toEntity(Product product) {

        return JpaProduct.builder()
                                    .name(product.getName())
                                    .price(product.getPrice())
                                    .quantity(product.getQuantity())
                                    .categories(
                                            new HashSet<>()
                                    )
                                    .build();
    }

}
