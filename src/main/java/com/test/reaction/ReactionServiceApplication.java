package com.test.reaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ReactionServiceApplication {
	@SuppressWarnings("unchecked")
    public static void main(String[] args) {
        SpringApplication.run(ReactionServiceApplication.class, args);
    }

}
