package com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.sequences;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customSequences")
@Getter
@Setter
public class CustomSequence {

    @Id
    private String id;
    private int seq;


    public static String CUSTOM_SEQUENCE_COLUMN_NAME = "customSequences";
}
