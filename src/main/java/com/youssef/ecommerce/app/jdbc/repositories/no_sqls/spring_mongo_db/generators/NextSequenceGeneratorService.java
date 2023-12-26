package com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.generators;


import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.sequences.CustomSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;


import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Component
public class NextSequenceGeneratorService {

    @Autowired
    private MongoOperations mongo;

    public int getNextSequence(String seqName) {
        CustomSequence counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                CustomSequence.class);
        return counter.getSeq();
    }

}
