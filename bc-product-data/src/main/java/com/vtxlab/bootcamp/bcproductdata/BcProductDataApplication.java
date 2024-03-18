package com.vtxlab.bootcamp.bcproductdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BcProductDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcProductDataApplication.class, args);
	}

}
