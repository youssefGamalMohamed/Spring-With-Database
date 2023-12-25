package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.repo_to_service_mapper;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models.DataJdbcCategory;
import com.youssef.ecommerce.app.jdbc.services.models.Category;

import java.util.Set;
import java.util.stream.Collectors;

public class DataJdbcCategoryServiceMapper {

    public static Category toServiceModel(DataJdbcCategory dataJdbcCategory) {
        return Category.builder()
                .id(dataJdbcCategory.getId())
                .name(dataJdbcCategory.getName())
                .build();
    }

    public static Set<Category> toServiceModel(Set<DataJdbcCategory> dataJdbcCategories) {
        return dataJdbcCategories.stream()
                .map(DataJdbcCategoryServiceMapper::toServiceModel)
                .collect(Collectors.toSet());
    }


    public static DataJdbcCategory toEntity(Category category) {
        return DataJdbcCategory.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
