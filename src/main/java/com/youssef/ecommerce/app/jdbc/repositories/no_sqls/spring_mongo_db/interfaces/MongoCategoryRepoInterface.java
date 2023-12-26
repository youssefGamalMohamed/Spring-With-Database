package com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.interfaces;

import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.MongoCategory;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.MongoProduct;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoCategoryRepoInterface extends MongoRepository<MongoCategory,String> {

    boolean existsByNameIgnoreCase(String name);
}
