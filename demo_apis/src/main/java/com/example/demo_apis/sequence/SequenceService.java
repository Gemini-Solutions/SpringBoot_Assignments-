package com.example.demo_apis.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceService {
    @Autowired
    MongoOperations mongoOperations;

//    @Autowired
//    SequenceModel sequenceModel;

    Query query = new Query();

    public Long generateSequence(String seqName) {
        SequenceModel counter = mongoOperations.findAndModify(query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                SequenceModel.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
