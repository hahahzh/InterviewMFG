package com.mfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class GoodsPagingRepository {

	@Autowired  
    MongoOperations mongoOperations; 
	
	public List<GoodModel> goodsPaging(Integer page, Integer size) {
		return mongoOperations.find(new Query(new Criteria()).skip((page-1) * size).limit(size), GoodModel.class);   
	}  
}
