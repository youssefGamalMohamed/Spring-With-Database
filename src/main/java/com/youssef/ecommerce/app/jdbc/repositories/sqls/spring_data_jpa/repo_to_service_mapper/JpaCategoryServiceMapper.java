package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.repo_to_service_mapper;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models.JpaCategory;
import com.youssef.ecommerce.app.jdbc.services.models.Category;

import java.util.Set;
import java.util.stream.Collectors;

public class JpaCategoryServiceMapper {

    private static Category toServiceModel(JpaCategory jpaCategory) {
        return Category.builder()
                .id(jpaCategory.getId())
                .name(jpaCategory.getName())
                .build();
    }

    public static Set<Category> toServiceModels(Set<JpaCategory> jpaCategories) {
        return jpaCategories.stream()
                .map(
                        jpaCategory -> Category.builder()
                            .id(jpaCategory.getId())
                            .name(jpaCategory.getName())
                            .build()
                )
                .collect(Collectors.toSet());
    }

    public static JpaCategory toEntity(Category category) {
        return JpaCategory.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Set<JpaCategory> toEntities(Set<Category> categories) {
        return categories.stream()
                .map(
                        category -> JpaCategory.builder()
                                .id(category.getId())
                                .name(category.getName())
                                .build()
                )
                .collect(Collectors.toSet());
    }
}
