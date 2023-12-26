package com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.repo_to_service_mapper;

import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.MongoCategory;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.MongoProduct;
import com.youssef.ecommerce.app.jdbc.services.models.Category;
import com.youssef.ecommerce.app.jdbc.services.models.Product;

public class MongoProductServiceMapper {

    public static Product toServiceModel(MongoProduct entity) {
        return Product.builder()
                .id(Integer.valueOf(entity.getId()))
                .name(entity.getName())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .build();
    }

    public static Product toServiceModelWithCategories(MongoProduct entity) {
        return Product.builder()
                .id(Integer.valueOf(entity.getId()))
                .name(entity.getName())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .categories(MongoCategoryServiceMapper.toServiceModels(entity.getCategories()))
                .build();
    }

}
