package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.repo_to_service_mapper;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models.JdbcTemplateCategory;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models.JdbcTemplateProduct;
import com.youssef.ecommerce.app.jdbc.services.models.Category;
import com.youssef.ecommerce.app.jdbc.services.models.Product;

import java.util.stream.Collectors;

public class JdbcTemplateProductServiceMapper {

    public static Product toServiceModel(JdbcTemplateProduct jdbcTemplateProduct) {
        return Product.builder()
                .id(jdbcTemplateProduct.getId())
                .name(jdbcTemplateProduct.getName())
                .quantity(jdbcTemplateProduct.getQuantity())
                .price(jdbcTemplateProduct.getPrice())
                .build();
    }

    public static Product toServiceModelWithCategories(JdbcTemplateProduct jdbcTemplateProduct) {
        return Product.builder()
                .id(jdbcTemplateProduct.getId())
                .name(jdbcTemplateProduct.getName())
                .quantity(jdbcTemplateProduct.getQuantity())
                .price(jdbcTemplateProduct.getPrice())
                .categories(
                        jdbcTemplateProduct.getCategories()
                                .stream()
                                .map(JdbcTemplateCategoryServiceMapper::toServiceModel)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
