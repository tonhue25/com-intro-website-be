package com.altek.intro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableAutoConfiguration
public class ComIntroWebsiteBeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ComIntroWebsiteBeApplication.class, args);
	}
}
