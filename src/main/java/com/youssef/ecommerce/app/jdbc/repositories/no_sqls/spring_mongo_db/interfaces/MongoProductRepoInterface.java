package com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.interfaces;

import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.MongoProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoProductRepoInterface extends MongoRepository<MongoProduct,String> {

}
