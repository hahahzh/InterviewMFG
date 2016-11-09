package com.mfg;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface GoodsRepository extends MongoRepository<GoodModel, Long> {
	
	@Query("{[{$skip : ?0}, {$limit : ?1}]}")
	public List<GoodModel> findByRecent(Integer page, Integer size);
	
	@Query("{'_id' :  ?0}")
	public GoodModel findById(String Id);
	
	@Query(delete=true, value="{'_id' :  ?0}")
	public void remove(String id);
}
