package com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models;

import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Document("products")
public class MongoProduct {


    @Id
    private String id;

    private String name;

    private Integer quantity;

    private Double price;

    @DBRef(lazy = true)
    private Set<MongoCategory> categories;
}
