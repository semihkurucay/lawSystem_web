package com.skyazilim.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan(basePackages = "com.skyazilim")
@ComponentScan(basePackages = "com.skyazilim")
@EnableJpaRepositories(basePackages = "com.skyazilim")
@EnableScheduling
@SpringBootApplication
public class LawSystemApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(LawSystemApplicationStarter.class, args);
	}

}
