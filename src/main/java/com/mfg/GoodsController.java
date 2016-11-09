package com.mfg;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/good")
public class GoodsController {

	@Autowired
	private GoodsRepository goodsRepository;
	
	@Autowired
	private GoodsPagingRepository goodsPagingRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	
	public @ResponseBody String findByRecent(Integer page, Integer size) {
		String results = null;
		
		List<GoodModel> ls = this.goodsPagingRepository.goodsPaging(page, size);
		
		StringBuffer sb = new StringBuffer(64);
		sb.append("{");
		sb.append("\"array\":[");
		int n = ls.size();
		for(GoodModel gm : ls){
			sb.append("{");
			sb.append("\"_id\":\""+gm.get_id()+"\",");
			sb.append("\"name\":\""+gm.getName()+"\",");
			sb.append("\"age\":\""+gm.getAge()+"\",");
			sb.append("\"productionDate\":\""+gm.getProductionDate()+"\"");
			sb.append("}");
			n--;
			if(n==0)break;
			sb.append(",");
		}
		sb.append("]");
		sb.append("}");
		results = sb.toString();
		return results;
	 }

	  @RequestMapping(value = "", method = RequestMethod.POST)
	  public void create(@RequestBody GoodModel goodModel) {
		  this.goodsRepository.insert(goodModel);
	  }
	  
	  @RequestMapping(value = "", method = RequestMethod.PUT)
	  public void update(@RequestBody GoodModel goodModel) {
		  this.goodsRepository.save(goodModel);
	  }
	  
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public void delete(@PathVariable("id") String id) {
		  this.goodsRepository.remove(id);
	  }
	  
	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public GoodModel findone(@PathVariable("id") String id) {
		  return this.goodsRepository.findById(id);
	  }
}
