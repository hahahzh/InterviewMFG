package com.mfg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration 
public class InterviewApplication implements CommandLineRunner {

	  
	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		 new RestTemplate();  
//		 template.getForObject("http://localhost:8080/api/good?page=1&size=10", GoodModel.class);  
		
	}

}
