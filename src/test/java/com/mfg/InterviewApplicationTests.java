package com.mfg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class) 
@SpringApplicationConfiguration(classes = InterviewApplication.class)
@WebAppConfiguration 
public class InterviewApplicationTests{

	private TestRestTemplate template = new TestRestTemplate();

	@Autowired
	private GoodsRepository goodsRepository;
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	final static String URL = "http://127.0.0.1:8080/api/good";
	
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD HH/mm:ss");
	
	@Test
	@Rollback(true)
	public void testFindByRecent() {
		
		mongoTemplate.dropCollection(GoodModel.class);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		int i = 1;
		while(true){
			if(i==13)break;
	        GoodModel gm = new GoodModel();
	        gm.setName("pen"+i);
	        gm.setAge(i);
	        gm.setProductionDate(new Date());
	        goodsRepository.insert(gm);
	        i++;
		}
		ResponseEntity<String> response = template.getForEntity(URL+"?page=1&size=10", String.class);
		String s = response.getBody().toString();
		String[] sArr = response.getBody().toString().substring(10, s.length()-2).split("}");
		assertEquals(10, sArr.length);
		
		ResponseEntity<String> responseNext = template.getForEntity(URL+"?page=2&size=10", String.class);
		String s2 = responseNext.getBody().toString();
		System.out.println(s2);
		String[] sArr2 = responseNext.getBody().toString().substring(10, s2.length()-2).split("}");
		assertEquals(2, sArr2.length);
		
	}

	
	@Test
	@Rollback(true)
	public void testCreate() throws Exception {
		
		mongoTemplate.dropCollection(GoodModel.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
                
        GoodModel gm = new GoodModel();
        gm.setName("pen");
        gm.setAge(1);
        gm.setProductionDate(new Date());
        HttpEntity<GoodModel> entity = new HttpEntity<GoodModel>(gm, headers);
        template.postForEntity(URL, entity, GoodModel.class);
                
        GoodModel gm1 = new GoodModel();
        gm1.setName("apple");
        gm1.setAge(2);
        gm1.setProductionDate(new Date());
        entity = new HttpEntity<GoodModel>(gm1, headers);
        template.postForEntity(URL, entity, GoodModel.class);
        
        GoodModel gm2 = new GoodModel();
        gm2.setName("pineapple");
        gm2.setAge(3);
        gm2.setProductionDate(new Date());
        entity = new HttpEntity<GoodModel>(gm2, headers);
        template.postForEntity(URL, entity, GoodModel.class);

		List<GoodModel> l = goodsRepository.findAll();
		
		GoodModel gmr = l.get(0);
		assertEquals("pen", gmr.getName());
		assertEquals("1", gmr.getAge()+"");
		assertTrue(gmr.getProductionDate().getTime() < new Date().getTime());
		
		GoodModel gmr1 = l.get(1);
		assertEquals("apple", gmr1.getName());
		assertEquals("2", gmr1.getAge()+"");
		assertTrue(gmr1.getProductionDate().getTime() < new Date().getTime());
		
		GoodModel gmr2 = l.get(2);
		assertEquals("pineapple", gmr2.getName());
		assertEquals("3", gmr2.getAge()+"");
		assertTrue(gmr2.getProductionDate().getTime() < new Date().getTime());
	}

	@Test
	@Rollback(true)
	public void testUpdate() {
		mongoTemplate.dropCollection(GoodModel.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
                
        GoodModel gm = new GoodModel();
        gm.setName("pen");
        gm.setAge(1);
        gm.setProductionDate(new Date());
        HttpEntity<GoodModel> entity = new HttpEntity<GoodModel>(gm, headers);
        template.put(URL, entity, GoodModel.class);
                
        GoodModel gm1 = new GoodModel();
        gm1.setName("apple");
        gm1.setAge(2);
        gm1.setProductionDate(new Date());
        entity = new HttpEntity<GoodModel>(gm1, headers);
        template.put(URL, entity, GoodModel.class);
        
        GoodModel gm2 = new GoodModel();
        gm2.setName("pineapple");
        gm2.setAge(3);
        gm2.setProductionDate(new Date());
        entity = new HttpEntity<GoodModel>(gm2, headers);
        template.put(URL, entity, GoodModel.class);

		List<GoodModel> l = goodsRepository.findAll();
		System.out.println(l.get(0).get_id());
		GoodModel gm3 = new GoodModel();
		gm3.set_id(l.get(0).get_id());
        gm3.setName("pen1");
        gm3.setAge(11);
        gm3.setProductionDate(new Date());
        HttpEntity<GoodModel> entity2 = new HttpEntity<GoodModel>(gm3, headers);
        template.put(URL, entity2, GoodModel.class);
                
        GoodModel gm4 = new GoodModel();
        gm4.set_id(l.get(1).get_id());
        gm4.setName("apple1");
        gm4.setAge(21);
        gm4.setProductionDate(new Date());
        entity2 = new HttpEntity<GoodModel>(gm4, headers);
        template.put(URL, entity2, GoodModel.class);
        
        GoodModel gm5 = new GoodModel();
        gm5.set_id(l.get(2).get_id());
        gm5.setName("pineapple1");
        gm5.setAge(31);
        gm5.setProductionDate(new Date());
        entity2 = new HttpEntity<GoodModel>(gm5, headers);
        template.put(URL, entity2, GoodModel.class);
	        
	        
        List<GoodModel> l2 = goodsRepository.findAll();
        
		GoodModel gmr = l2.get(0);
		System.out.println(gmr.get_id());
		
		assertEquals("pen1", gmr.getName());
		assertEquals("11", gmr.getAge()+"");
		assertTrue(gmr.getProductionDate().getTime() < new Date().getTime());
		
		GoodModel gmr1 = l2.get(1);
		assertEquals("apple1", gmr1.getName());
		assertEquals("21", gmr1.getAge()+"");
		assertTrue(gmr1.getProductionDate().getTime() < new Date().getTime());
		
		GoodModel gmr2 = l2.get(2);
		assertEquals("pineapple1", gmr2.getName());
		assertEquals("31", gmr2.getAge()+"");
		assertTrue(gmr2.getProductionDate().getTime() < new Date().getTime());
	}

	@Test
	@Rollback(true)
	public void testDelete() {
		mongoTemplate.dropCollection(GoodModel.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
                
        GoodModel gm = new GoodModel();
        gm.setName("pen");
        gm.setAge(1);
        gm.setProductionDate(new Date());
        HttpEntity<GoodModel> entity = new HttpEntity<GoodModel>(gm, headers);
        template.postForEntity(URL, entity, GoodModel.class);
        
        List<GoodModel> l = goodsRepository.findAll();
        template.delete(URL+"/"+l.get(0).get_id());
        assertEquals(0, goodsRepository.count());
	}

	@Test
	@Rollback(true)
	public void testFindone() {
		mongoTemplate.dropCollection(GoodModel.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
                
        GoodModel gm = new GoodModel();
        gm.setName("pen");
        gm.setAge(1);
        gm.setProductionDate(new Date());
        HttpEntity<GoodModel> entity = new HttpEntity<GoodModel>(gm, headers);
        template.postForEntity(URL, entity, GoodModel.class);
        
        List<GoodModel> l = goodsRepository.findAll();
        
        ResponseEntity<GoodModel> rusult = template.getForEntity(URL+"/"+l.get(0).get_id(), GoodModel.class);
        assertEquals("pen", rusult.getBody().getName());
        assertEquals(1+"", rusult.getBody().getAge()+"");
        assertTrue(rusult.getBody().getProductionDate().getTime() < new Date().getTime());
	}

}
