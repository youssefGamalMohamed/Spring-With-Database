package com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.repo_to_service_mapper;

import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.MongoCategory;
import com.youssef.ecommerce.app.jdbc.services.models.Category;

import java.util.Set;
import java.util.stream.Collectors;

public class MongoCategoryServiceMapper {

    public static Category toServiceModel(MongoCategory entity) {
        return Category.builder()
                .id(Integer.valueOf(entity.getId()))
                .name(entity.getName())
                .build();
    }

    public static Set<Category> toServiceModels(Set<MongoCategory> entities) {
        return entities.stream().map(entity -> Category.builder()
                        .id(Integer.valueOf(entity.getId()))
                        .name(entity.getName())
                        .build())
                .collect(Collectors.toSet());
    }

    public static MongoCategory toEntity(Category model) {
        return MongoCategory.builder()
                .id(String.valueOf(model.getId()))
                .name(model.getName())
                .build();
    }


    public static Set<MongoCategory> toEntities(Set<Category> models) {
        return models.stream().map(model ->
                        MongoCategory.builder()
                                .id(String.valueOf(model.getId()))
                                .name(model.getName())
                                .build()
                )
                .collect(Collectors.toSet());
    }

}
